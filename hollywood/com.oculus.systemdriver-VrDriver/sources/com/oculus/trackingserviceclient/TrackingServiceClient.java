package com.oculus.trackingserviceclient;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import com.oculus.trackingserviceclient.ITrackingServiceConnection;
import com.oculus.vrapi.ITrackingService;
import com.oculus.vrapi.ITrackingServiceClient;
import java.util.Hashtable;

public class TrackingServiceClient {
    private static final String FEATURE_STANDALONE_VR = "oculus.hardware.standalone_vr";
    private static final String TAG = "TrackingServiceClient2";
    private static ITrackingServiceClient mClient = new ITrackingServiceClient.Stub() {
        /* class com.oculus.trackingserviceclient.TrackingServiceClient.AnonymousClass2 */

        @Override // com.oculus.vrapi.ITrackingServiceClient
        public int getTrackingModeFlags() {
            return TrackingServiceClient.mClientTrackingModeFlags;
        }

        @Override // com.oculus.vrapi.ITrackingServiceClient
        public void onButtonDown(long deviceIdentifier, int buttonData) {
            synchronized (TrackingServiceClient.mConnectedRemotes) {
                IConnectedRemote connectedRemote = (IConnectedRemote) TrackingServiceClient.mConnectedRemotes.get(Long.valueOf(deviceIdentifier));
                if (connectedRemote != null) {
                    connectedRemote.onButtonDownEvent(buttonData);
                } else if (TrackingServiceClient.mRemoteFactory != null) {
                    Log.e(TrackingServiceClient.TAG, "Received button event for remote " + Long.toHexString(deviceIdentifier) + " which is not registered");
                    StringBuilder sb = new StringBuilder();
                    sb.append("Registered remotes are: ");
                    sb.append(TrackingServiceClient.mConnectedRemotes);
                    Log.d(TrackingServiceClient.TAG, sb.toString());
                }
            }
        }

        @Override // com.oculus.vrapi.ITrackingServiceClient
        public void onRemoteConnected(long deviceIdentifier) {
            synchronized (TrackingServiceClient.mConnectedRemotes) {
                if (((IConnectedRemote) TrackingServiceClient.mConnectedRemotes.get(Long.valueOf(deviceIdentifier))) == null && TrackingServiceClient.mRemoteFactory != null) {
                    TouchRange outTouchRange = new TouchRange();
                    ModelIdWrapper outModelId = new ModelIdWrapper();
                    if (TrackingServiceClient.nativeSetRemoteConnected(deviceIdentifier, outTouchRange, outModelId)) {
                        TrackingServiceClient.mConnectedRemotes.put(Long.valueOf(deviceIdentifier), TrackingServiceClient.mRemoteFactory.createConnectedRemote(TrackingServiceClient.mContext, deviceIdentifier, outTouchRange.Width, outTouchRange.Height, outModelId.ModelId));
                    } else {
                        Log.e(TrackingServiceClient.TAG, "Failed to initialize remote " + Long.toHexString(deviceIdentifier));
                    }
                } else if (TrackingServiceClient.mRemoteFactory != null) {
                    Log.e(TrackingServiceClient.TAG, "Received notification of connection for remote " + Long.toHexString(deviceIdentifier) + " that is already registered ");
                }
            }
        }

        @Override // com.oculus.vrapi.ITrackingServiceClient
        public void onRemoteDisconnected(long deviceIdentifier) {
            synchronized (TrackingServiceClient.mConnectedRemotes) {
                IConnectedRemote connectedRemote = (IConnectedRemote) TrackingServiceClient.mConnectedRemotes.remove(Long.valueOf(deviceIdentifier));
                if (connectedRemote != null) {
                    connectedRemote.destroy();
                } else if (TrackingServiceClient.mRemoteFactory != null) {
                    Log.e(TrackingServiceClient.TAG, "Received notification of disconnect for unknown remote " + Long.toHexString(deviceIdentifier));
                }
            }
        }
    };
    private static ITrackingServiceConnection.IDisconnectCallback mClientDisconnectCallback = new ITrackingServiceConnection.IDisconnectCallback() {
        /* class com.oculus.trackingserviceclient.TrackingServiceClient.AnonymousClass1 */

        @Override // com.oculus.trackingserviceclient.ITrackingServiceConnection.IDisconnectCallback
        public void onDisconnect() {
            TrackingServiceClient.clearRemotes();
        }
    };
    private static int mClientTrackingModeFlags = 0;
    private static final Hashtable<Long, IConnectedRemote> mConnectedRemotes = new Hashtable<>();
    private static ITrackingServiceConnection mConnection = null;
    private static Context mContext = null;
    private static IConnectedRemoteFactory mRemoteFactory = null;

    public static native boolean nativeSetRemoteConnected(long j, TouchRange touchRange, ModelIdWrapper modelIdWrapper);

    public static native void nativeSetRemoteDisconnected(long j);

    public static native void nativeSetTrackingServiceInterface(ITrackingService iTrackingService, int i);

    static class TouchRange {
        public int Height;
        public int Width;

        TouchRange() {
        }

        public void setValues(int width, int height) {
            this.Width = width;
            this.Height = height;
        }
    }

    static class ModelIdWrapper {
        public int ModelId;

        ModelIdWrapper() {
        }

        public void setValue(int modelId) {
            this.ModelId = modelId;
        }
    }

    public static int getTrackingSocketFileDescriptor() {
        return mConnection.getTrackingSocketFd();
    }

    public static boolean onInitialize(Context context, IConnectedRemoteFactory remoteFactory) {
        mContext = context;
        mRemoteFactory = remoteFactory;
        boolean requestSecureTracking = false;
        boolean requestAuditSecureTracking = false;
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                Log.w(TAG, "No package metadata available for " + context.getPackageName());
            } else {
                requestSecureTracking = bundle.getBoolean("com.oculus.tracking_service.request_secure_tracking");
                requestAuditSecureTracking = bundle.getBoolean("com.oculus.tracking_service.request_audit_secure_tracking");
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Failed to find package: " + e.getMessage());
        }
        int trackingModeFlags = 0;
        if (requestSecureTracking) {
            ITrackingServiceClient iTrackingServiceClient = mClient;
            trackingModeFlags = 0 | 1;
        }
        if (requestAuditSecureTracking) {
            ITrackingServiceClient iTrackingServiceClient2 = mClient;
            trackingModeFlags |= 2;
        }
        mClientTrackingModeFlags = trackingModeFlags;
        if (context.getPackageManager().hasSystemFeature(FEATURE_STANDALONE_VR)) {
            mConnection = new TrackingServiceConnectionNative(mClientDisconnectCallback, mClient);
            return true;
        }
        mConnection = new TrackingServiceConnectionGear(context, mClientDisconnectCallback, mClient);
        return true;
    }

    public static void onShutdown() {
        mConnection.close();
        mConnection = null;
    }

    public static boolean onClientConnect() {
        return mConnection.onClientConnect();
    }

    public static void onClientDisconnect() {
        mConnection.onClientDisconnect();
    }

    public static void onRemoteTouch(long deviceIdentifier, int touchState, int touchX, int touchY) {
        synchronized (mConnectedRemotes) {
            IConnectedRemote connectedRemote = mConnectedRemotes.get(Long.valueOf(deviceIdentifier));
            if (connectedRemote != null) {
                connectedRemote.onTouch(touchState, touchX, touchY);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void clearRemotes() {
        synchronized (mConnectedRemotes) {
            for (IConnectedRemote connectedRemote : mConnectedRemotes.values()) {
                connectedRemote.destroy();
            }
            mConnectedRemotes.clear();
        }
    }
}
