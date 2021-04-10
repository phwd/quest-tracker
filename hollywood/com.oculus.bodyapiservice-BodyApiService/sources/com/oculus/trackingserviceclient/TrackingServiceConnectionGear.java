package com.oculus.trackingserviceclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.trackingserviceclient.ITrackingServiceConnection;
import com.oculus.vrapi.ITrackingService;
import com.oculus.vrapi.ITrackingServiceClient;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TrackingServiceConnectionGear implements ITrackingServiceConnection {
    private static final String DRIVER_TRACKING_SERVICE_CLASS = "com.oculus.vrapi.TrackingService";
    private static final String DRIVER_TRACKING_SERVICE_PACKAGE_NAME = "com.oculus.systemdriver";
    private static final String TAG = "TrackingServiceConnectionGear";
    private static final int TRACKING_SERVICE_CONNECTION_TIMEOUT_SEC = 5;
    private ITrackingServiceClient mClient;
    private Context mContext;
    private ITrackingServiceConnection.IDisconnectCallback mDisconnectCallback;
    private Handler mHandler = null;
    private HandlerThread mHandlerThread = null;
    private boolean mShouldUnbindFromTrackingService = false;
    private ITrackingService mTrackingService = null;
    private TrackingServiceConnection mTrackingServiceConnection = null;

    class TrackingServiceConnection implements ServiceConnection {
        CountDownLatch mSignalOnConnected = null;

        public TrackingServiceConnection(CountDownLatch signalOnConnected) {
            this.mSignalOnConnected = signalOnConnected;
        }

        public void onServiceConnected(ComponentName name, IBinder boundService) {
            Log.d(TrackingServiceConnectionGear.TAG, "TrackingServiceConnection.onServiceConnected()");
            TrackingServiceConnectionGear.this.mTrackingService = ITrackingService.Stub.asInterface(boundService);
            int fd = -1;
            try {
                Log.d(TrackingServiceConnectionGear.TAG, "TrackingService trying to registerClient");
                fd = TrackingServiceConnectionGear.this.mTrackingService.getSharedMemoryFileDescriptor(TrackingServiceConnectionGear.this.mClient).detachFd();
            } catch (RemoteException e) {
                Log.d(TrackingServiceConnectionGear.TAG, "RemoteException: " + e);
            }
            Log.d(TrackingServiceConnectionGear.TAG, "TrackingServiceConnection.onServiceConnected() connected, fd = " + fd);
            TrackingServiceClient.nativeSetTrackingServiceInterface(TrackingServiceConnectionGear.this.mTrackingService, fd);
            try {
                TrackingServiceConnectionGear.this.mTrackingService.registerClient(TrackingServiceConnectionGear.this.mClient);
            } catch (RemoteException e2) {
                Log.d(TrackingServiceConnectionGear.TAG, "RemoteException: " + e2);
            }
            if (this.mSignalOnConnected != null) {
                Log.d(TrackingServiceConnectionGear.TAG, "Signaling connection");
                this.mSignalOnConnected.countDown();
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            TrackingServiceConnectionGear.this.mTrackingService = null;
            Log.d(TrackingServiceConnectionGear.TAG, "TrackingServiceConnection.onServiceDisconnected() disconnected");
            TrackingServiceConnectionGear.this.doDisconnect();
        }
    }

    public TrackingServiceConnectionGear(Context context, ITrackingServiceConnection.IDisconnectCallback disconnectCallback, ITrackingServiceClient client) {
        this.mContext = context;
        this.mDisconnectCallback = disconnectCallback;
        this.mClient = client;
        try {
            BindServiceWrapper.init();
        } catch (Exception e) {
            Log.e(TAG, "Failed to initialize BindServiceWrapper ");
            e.printStackTrace();
        }
        this.mHandlerThread = new HandlerThread("trackingServiceConnectionThread");
        this.mHandlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper());
        context.startService(createTrackingServiceIntent());
    }

    @Override // com.oculus.trackingserviceclient.ITrackingServiceConnection
    public boolean onClientConnect() {
        Log.d(TAG, "onClientConnect");
        Intent intent = createTrackingServiceIntent();
        this.mContext.startService(intent);
        CountDownLatch connectedSignal = new CountDownLatch(1);
        this.mTrackingServiceConnection = new TrackingServiceConnection(connectedSignal);
        try {
            this.mShouldUnbindFromTrackingService = BindServiceWrapper.bindServiceWithHandler(this.mContext, intent, this.mTrackingServiceConnection, 0, this.mHandler);
            Log.d(TAG, "Waiting on connection");
            connectedSignal.await(5, TimeUnit.SECONDS);
            Log.d(TAG, "TrackingService client: bindService returned " + this.mShouldUnbindFromTrackingService);
            return true;
        } catch (InterruptedException e) {
            Log.e(TAG, "Timed out connecting to tracking service");
            e.printStackTrace();
            return false;
        } catch (Exception e2) {
            Log.e(TAG, "Failed to bind to TrackingService");
            e2.printStackTrace();
            return false;
        }
    }

    @Override // com.oculus.trackingserviceclient.ITrackingServiceConnection
    public void onClientDisconnect() {
        if (this.mShouldUnbindFromTrackingService) {
            ITrackingService iTrackingService = this.mTrackingService;
            if (iTrackingService != null) {
                try {
                    iTrackingService.unregisterClient(this.mClient);
                } catch (RemoteException e) {
                    Log.e(TAG, "Failed to unregister TrackingService client callback: " + e);
                }
            }
            Log.d(TAG, "unbinding trackingServiceConnection");
            this.mContext.unbindService(this.mTrackingServiceConnection);
            doDisconnect();
            this.mTrackingServiceConnection = null;
            this.mShouldUnbindFromTrackingService = false;
        }
    }

    @Override // com.oculus.trackingserviceclient.ITrackingServiceConnection
    public void close() {
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quitSafely();
            this.mHandlerThread = null;
        }
        this.mHandler = null;
    }

    @Override // com.oculus.trackingserviceclient.ITrackingServiceConnection
    public int getTrackingSocketFd() {
        Log.e(TAG, "not implemented");
        return -1;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void doDisconnect() {
        this.mDisconnectCallback.onDisconnect();
        TrackingServiceClient.nativeSetTrackingServiceInterface(null, -1);
    }

    private static Intent createTrackingServiceIntent() {
        Intent intent = new Intent();
        intent.setClassName(DRIVER_TRACKING_SERVICE_PACKAGE_NAME, DRIVER_TRACKING_SERVICE_CLASS);
        return intent;
    }
}
