package com.oculus.vrcast;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.oculus.vrcast.VrCastController;
import java.util.ArrayList;
import java.util.List;
import oculus.internal.IVrCastCallback;
import oculus.internal.IVrCastService;
import oculus.internal.VrCastDevice;

public class VrCastController {
    private static final boolean DEBUG = true;
    private static final String SERVICE_IMPL_CLS = "com.oculus.vrcast.VrCastService";
    private static final String SERVICE_IMPL_PKG = "com.oculus.vrcast";
    private static final String TAG = "VrCastController";
    private ServiceConnection mConnection = new ServiceConnection() {
        /* class com.oculus.vrcast.VrCastController.AnonymousClass2 */

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            try {
                VrCastController.this.mServiceImpl = IVrCastService.Stub.asInterface(iBinder);
                VrCastController.this.mServiceImpl.registerCallback(VrCastController.this.mVrCastCallback);
                iBinder.linkToDeath(new IBinder.DeathRecipient() {
                    /* class com.oculus.vrcast.$$Lambda$VrCastController$2$_YKql2qx6e2Zz_fgzRrhhvyNdg8 */

                    public final void binderDied() {
                        VrCastController.AnonymousClass2.this.lambda$onServiceConnected$0$VrCastController$2();
                    }
                }, 0);
                if (VrCastController.this.mVrShellCallback != null) {
                    VrCastController.this.mVrShellCallback.onBindServiceSucceeded();
                }
                if (VrCastController.this.mVrClientCallback != null) {
                    VrCastController.this.mVrClientCallback.onBindServiceSucceeded(VrCastController.toShellCastDevice(VrCastController.this.mServiceImpl.getCurrentDevice()));
                }
            } catch (RemoteException e) {
                VrCastController.this.notifyRemoteServiceDied(e);
            }
        }

        public /* synthetic */ void lambda$onServiceConnected$0$VrCastController$2() {
            VrCastController.this.notifyRemoteServiceDied(null);
        }

        public void onServiceDisconnected(ComponentName componentName) {
        }
    };
    private Context mContext;
    private boolean mIsBinding = false;
    private IVrCastService mServiceImpl;
    private IVrCastCallback mVrCastCallback = new IVrCastCallback.Stub() {
        /* class com.oculus.vrcast.VrCastController.AnonymousClass1 */

        @Override // oculus.internal.IVrCastCallback
        public void onDeviceChanged(VrCastDevice[] list) {
            List<VrShellCastDevice> result = new ArrayList<>();
            for (VrCastDevice device : list) {
                result.add(VrCastController.toShellCastDevice(device));
            }
            if (VrCastController.this.mVrShellCallback != null) {
                VrCastController.this.mVrShellCallback.onDevicesFound(result);
            }
            if (VrCastController.this.mVrClientCallback != null) {
                VrCastController.this.mVrClientCallback.onDevicesFound(result);
            }
        }

        @Override // oculus.internal.IVrCastCallback
        public void onDeviceStateChanged(VrCastDevice device) {
            Log.d(VrCastController.TAG, "onDeviceStateChanged " + device);
            if (device != null) {
                Log.d(VrCastController.TAG, "device [" + device.name + "] state changed: " + device.state);
            }
            VrShellCastDevice vrShellDevice = VrCastController.toShellCastDevice(device);
            if (VrCastController.this.mVrShellCallback != null) {
                VrCastController.this.mVrShellCallback.onDeviceStateUpdated(vrShellDevice);
            }
            if (VrCastController.this.mVrClientCallback != null) {
                VrCastController.this.mVrClientCallback.onDeviceStateUpdated(vrShellDevice);
            }
        }

        @Override // oculus.internal.IVrCastCallback
        public void onError(int errorCode, Bundle extras) throws RemoteException {
            Log.d(VrCastController.TAG, "onVrCastError " + errorCode);
            if (VrCastController.this.mVrClientCallback != null) {
                VrCastController.this.mVrClientCallback.onError(errorCode, extras);
            }
        }
    };
    private final VrCastCallback mVrClientCallback;
    private final VrShellCastCallback mVrShellCallback;

    public enum DeviceType {
        Miracast,
        Chromecast
    }

    @Deprecated
    public interface VrShellCastCallback {
        void onBindServiceFailed();

        void onBindServiceSucceeded();

        void onDeviceStateUpdated(VrShellCastDevice vrShellCastDevice);

        void onDevicesFound(List<VrShellCastDevice> list);

        void onRemoteServiceDied();
    }

    @Deprecated
    public VrCastController(Context context, VrShellCastCallback callback) {
        this.mContext = context;
        this.mVrShellCallback = callback;
        this.mVrClientCallback = null;
        init();
    }

    public VrCastController(Context context, VrCastCallback callback) {
        this.mContext = context;
        this.mVrClientCallback = callback;
        this.mVrShellCallback = null;
        init();
    }

    private void init() {
        ComponentName component = new ComponentName(SERVICE_IMPL_PKG, SERVICE_IMPL_CLS);
        Intent intent = new Intent();
        intent.setComponent(component);
        this.mIsBinding = this.mContext.bindService(intent, this.mConnection, 1);
        if (!this.mIsBinding) {
            VrShellCastCallback vrShellCastCallback = this.mVrShellCallback;
            if (vrShellCastCallback != null) {
                vrShellCastCallback.onBindServiceFailed();
            }
            VrCastCallback vrCastCallback = this.mVrClientCallback;
            if (vrCastCallback != null) {
                vrCastCallback.onBindServiceFailed();
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void notifyRemoteServiceDied(RemoteException e) {
        if (e != null) {
            Log.e(TAG, "VrCastService stopped unexpectedly: ", e);
        }
        this.mServiceImpl = null;
        VrShellCastCallback vrShellCastCallback = this.mVrShellCallback;
        if (vrShellCastCallback != null) {
            vrShellCastCallback.onRemoteServiceDied();
        }
        VrCastCallback vrCastCallback = this.mVrClientCallback;
        if (vrCastCallback != null) {
            vrCastCallback.onRemoteServiceDied();
        }
    }

    public void startDiscovery() {
        startDiscovery(null);
    }

    public void startDiscovery(String callerContext) {
        try {
            this.mServiceImpl.startDiscovery(callerContext);
        } catch (RemoteException e) {
            notifyRemoteServiceDied(e);
        }
    }

    public void stopDiscovery() {
        stopDiscovery(null);
    }

    public void stopDiscovery(String callerContext) {
        try {
            this.mServiceImpl.stopDiscovery(callerContext);
        } catch (RemoteException e) {
            notifyRemoteServiceDied(e);
        }
    }

    public void startCast(String id) {
        startCast(id, null, false);
    }

    public void startCast(String id, String callerContext) {
        startCast(id, callerContext, false);
    }

    public void startCast(String id, boolean isDeviceAudioDest) {
        startCast(id, null, isDeviceAudioDest);
    }

    public void startCast(String id, String callerContext, boolean isDeviceAudioDest) {
        try {
            this.mServiceImpl.startCast(id, callerContext, isDeviceAudioDest);
        } catch (RemoteException e) {
            notifyRemoteServiceDied(e);
        }
    }

    public void stopCast(String id) {
        stopCast(id, null);
    }

    public void stopCast(String id, String callerContext) {
        try {
            this.mServiceImpl.stopCast(id, callerContext);
        } catch (RemoteException e) {
            notifyRemoteServiceDied(e);
        }
    }

    public void stopCastWithError(String id, String errorMessage) {
        stopCastWithError(id, errorMessage, null);
    }

    public void stopCastWithError(String id, String errorMessage, String callerContext) {
        try {
            this.mServiceImpl.stopCastWithError(id, callerContext, errorMessage);
        } catch (RemoteException e) {
            notifyRemoteServiceDied(e);
        }
    }

    public boolean isServiceBound() {
        if (this.mServiceImpl == null || !this.mIsBinding) {
            return false;
        }
        return DEBUG;
    }

    public void unbindVrCastService() {
        IVrCastService iVrCastService = this.mServiceImpl;
        if (iVrCastService != null) {
            try {
                iVrCastService.unregisterCallback(this.mVrCastCallback);
            } catch (RemoteException e) {
                notifyRemoteServiceDied(e);
            }
        }
        if (this.mIsBinding) {
            this.mContext.unbindService(this.mConnection);
            this.mIsBinding = false;
        }
    }

    /* access modifiers changed from: private */
    public static VrShellCastDevice toShellCastDevice(VrCastDevice device) {
        if (device == null) {
            return null;
        }
        return new VrShellCastDevice(device.name, device.id, VrShellCastDevice.State.fromOrdinal(device.state), toDeviceType(device.type));
    }

    private static DeviceType toDeviceType(int type) {
        if (type == 1) {
            return DeviceType.Miracast;
        }
        if (type == 2) {
            return DeviceType.Chromecast;
        }
        throw new RuntimeException("Not supported device type " + type);
    }

    public static abstract class VrCastCallback {
        public abstract void onBindServiceFailed();

        public abstract void onBindServiceSucceeded(VrShellCastDevice vrShellCastDevice);

        public abstract void onDeviceStateUpdated(VrShellCastDevice vrShellCastDevice);

        public abstract void onDevicesFound(List<VrShellCastDevice> list);

        public abstract void onRemoteServiceDied();

        public void onError(int errorCode, Bundle extras) {
        }
    }

    public static class VrShellCastDevice {
        public String id;
        public String name;
        public State state;
        public DeviceType type;

        public VrShellCastDevice(String name2, String id2, State state2, DeviceType type2) {
            this.name = name2;
            this.id = id2;
            this.state = state2;
            this.type = type2;
        }

        public enum State {
            FOUND,
            CONNECTING_TO_PEER,
            CONNECTION_INITIATED,
            CONNECTION_SUCCESS,
            STARTING_SESSION,
            CASTING,
            DISCONNECTING,
            INVALID;

            public static State fromOrdinal(int val) {
                switch (val) {
                    case 0:
                        return FOUND;
                    case 1:
                        return CONNECTING_TO_PEER;
                    case 2:
                        return CONNECTION_INITIATED;
                    case 3:
                        return CONNECTION_SUCCESS;
                    case 4:
                        return STARTING_SESSION;
                    case 5:
                        return CASTING;
                    case 6:
                        return DISCONNECTING;
                    default:
                        return INVALID;
                }
            }
        }

        public String toString() {
            String str;
            StringBuilder sb = new StringBuilder();
            sb.append("VrShellCastDevice ");
            sb.append(this.name);
            sb.append(" ");
            sb.append(this.id);
            sb.append(" ");
            State state2 = this.state;
            if (state2 != null) {
                str = state2.name();
            } else {
                str = "null type " + this.type;
            }
            sb.append(str);
            return sb.toString();
        }
    }
}
