package com.oculus.trackingserviceclient;

import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.trackingserviceclient.ITrackingServiceConnection;
import com.oculus.vrapi.ITrackingService;
import com.oculus.vrapi.ITrackingServiceClient;

public class TrackingServiceConnectionNative extends BinderConnection<ITrackingService> implements ITrackingServiceConnection {
    private static final int CONNECTION_MAX_RETRIES = 300;
    private static final int CONNECTION_RETRY_INTERVAL_MS = 100;
    private static final String TAG = "TrackingServiceConnectionNative";
    private static final String TRACKING_SERVICE_NAME = "TrackingService";
    private final ITrackingServiceClient mClient;
    private final ITrackingServiceConnection.IDisconnectCallback mDisconnectCallback;
    private ITrackingService mTrackingService = null;

    @Override // com.oculus.trackingserviceclient.BinderConnection
    public /* bridge */ /* synthetic */ void binderDied() {
        super.binderDied();
    }

    @Override // com.oculus.trackingserviceclient.BinderConnection
    public /* bridge */ /* synthetic */ void disconnect() {
        super.disconnect();
    }

    /* Return type fixed from 'android.os.IInterface' to match base method */
    @Override // com.oculus.trackingserviceclient.BinderConnection
    public /* bridge */ /* synthetic */ ITrackingService getService() throws RemoteException {
        return super.getService();
    }

    public TrackingServiceConnectionNative(ITrackingServiceConnection.IDisconnectCallback disconnectCallback, ITrackingServiceClient client) {
        super(TRACKING_SERVICE_NAME, CONNECTION_MAX_RETRIES, CONNECTION_RETRY_INTERVAL_MS);
        this.mDisconnectCallback = disconnectCallback;
        this.mClient = client;
    }

    @Override // com.oculus.trackingserviceclient.ITrackingServiceConnection
    public boolean onClientConnect() {
        Log.d(TAG, "onClientConnect");
        setShouldReconnect(true);
        try {
            if (((ITrackingService) getService()) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.e(TAG, "Failed to connect to client");
            return false;
        }
    }

    @Override // com.oculus.trackingserviceclient.ITrackingServiceConnection
    public void onClientDisconnect() {
        Log.d(TAG, "onClientDisconnect");
        setShouldReconnect(false);
        ITrackingService iTrackingService = this.mTrackingService;
        if (iTrackingService != null) {
            try {
                iTrackingService.unregisterClient(this.mClient);
            } catch (Exception e) {
                Log.e(TAG, "Failed to unregister TrackingService client callback: " + e);
            }
        }
        disconnect();
    }

    @Override // com.oculus.trackingserviceclient.ITrackingServiceConnection
    public void close() {
    }

    @Override // com.oculus.trackingserviceclient.ITrackingServiceConnection
    public int getTrackingSocketFd() {
        try {
            ParcelFileDescriptor trackingPfd = this.mTrackingService.getTrackingSocketFileDescriptor();
            if (trackingPfd != null) {
                return trackingPfd.detachFd();
            }
            Log.e(TAG, "TrackingService doesn't support socket communication");
            return -1;
        } catch (Exception e) {
            Log.e(TAG, "Failed to create communication socket", e);
            return -1;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.trackingserviceclient.BinderConnection
    public ITrackingService asInterface(IBinder connection) {
        return ITrackingService.Stub.asInterface(connection);
    }

    /* access modifiers changed from: protected */
    public void onConnected(ITrackingService service) {
        Log.d(TAG, "onConnected");
        try {
            this.mTrackingService = service;
            TrackingServiceClient.nativeSetTrackingServiceInterface(this.mTrackingService, this.mTrackingService.getSharedMemoryFileDescriptor(this.mClient).detachFd());
            this.mTrackingService.registerClient(this.mClient);
        } catch (Exception e) {
            Log.d(TAG, "Failed to connect to native tracking service: " + e);
        }
    }

    /* access modifiers changed from: protected */
    public void onDisconnected(ITrackingService service) {
        Log.d(TAG, "onDisconnected");
        this.mDisconnectCallback.onDisconnect();
        TrackingServiceClient.nativeSetTrackingServiceInterface(null, -1);
    }
}
