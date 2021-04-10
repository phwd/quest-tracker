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

        public void onServiceDisconnected(ComponentName componentName) {
        }

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
    };
    private Context mContext;
    private boolean mIsBinding = false;
    private IVrCastService mServiceImpl;
    private IVrCastCallback mVrCastCallback = new IVrCastCallback.Stub() {
        /* class com.oculus.vrcast.VrCastController.AnonymousClass1 */

        @Override // oculus.internal.IVrCastCallback
        public void onDeviceChanged(VrCastDevice[] vrCastDeviceArr) {
            ArrayList arrayList = new ArrayList();
            for (VrCastDevice vrCastDevice : vrCastDeviceArr) {
                arrayList.add(VrCastController.toShellCastDevice(vrCastDevice));
            }
            if (VrCastController.this.mVrShellCallback != null) {
                VrCastController.this.mVrShellCallback.onDevicesFound(arrayList);
            }
            if (VrCastController.this.mVrClientCallback != null) {
                VrCastController.this.mVrClientCallback.onDevicesFound(arrayList);
            }
        }

        @Override // oculus.internal.IVrCastCallback
        public void onDeviceStateChanged(VrCastDevice vrCastDevice) {
            Log.d(VrCastController.TAG, "onDeviceStateChanged " + vrCastDevice);
            if (vrCastDevice != null) {
                Log.d(VrCastController.TAG, "device [" + vrCastDevice.name + "] state changed: " + vrCastDevice.state);
            }
            VrShellCastDevice shellCastDevice = VrCastController.toShellCastDevice(vrCastDevice);
            if (VrCastController.this.mVrShellCallback != null) {
                VrCastController.this.mVrShellCallback.onDeviceStateUpdated(shellCastDevice);
            }
            if (VrCastController.this.mVrClientCallback != null) {
                VrCastController.this.mVrClientCallback.onDeviceStateUpdated(shellCastDevice);
            }
        }

        @Override // oculus.internal.IVrCastCallback
        public void onError(int i, Bundle bundle) throws RemoteException {
            Log.d(VrCastController.TAG, "onVrCastError " + i);
            if (VrCastController.this.mVrClientCallback != null) {
                VrCastController.this.mVrClientCallback.onError(i, bundle);
            }
        }
    };
    private final VrCastCallback mVrClientCallback;
    private final VrShellCastCallback mVrShellCallback;

    public enum DeviceType {
        Miracast,
        Chromecast
    }

    public static abstract class VrCastCallback {
        public abstract void onBindServiceFailed();

        public abstract void onBindServiceSucceeded(VrShellCastDevice vrShellCastDevice);

        public abstract void onDeviceStateUpdated(VrShellCastDevice vrShellCastDevice);

        public abstract void onDevicesFound(List<VrShellCastDevice> list);

        public void onError(int i, Bundle bundle) {
        }

        public abstract void onRemoteServiceDied();
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
    public VrCastController(Context context, VrShellCastCallback vrShellCastCallback) {
        this.mContext = context;
        this.mVrShellCallback = vrShellCastCallback;
        this.mVrClientCallback = null;
        init();
    }

    public VrCastController(Context context, VrCastCallback vrCastCallback) {
        this.mContext = context;
        this.mVrClientCallback = vrCastCallback;
        this.mVrShellCallback = null;
        init();
    }

    private void init() {
        ComponentName componentName = new ComponentName(SERVICE_IMPL_PKG, SERVICE_IMPL_CLS);
        Intent intent = new Intent();
        intent.setComponent(componentName);
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
    private void notifyRemoteServiceDied(RemoteException remoteException) {
        if (remoteException != null) {
            Log.e(TAG, "VrCastService stopped unexpectedly: ", remoteException);
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

    public void startDiscovery(String str) {
        try {
            this.mServiceImpl.startDiscovery(str);
        } catch (RemoteException e) {
            notifyRemoteServiceDied(e);
        }
    }

    public void stopDiscovery() {
        stopDiscovery(null);
    }

    public void stopDiscovery(String str) {
        try {
            this.mServiceImpl.stopDiscovery(str);
        } catch (RemoteException e) {
            notifyRemoteServiceDied(e);
        }
    }

    public void startCast(String str) {
        startCast(str, null, false);
    }

    public void startCast(String str, String str2) {
        startCast(str, str2, false);
    }

    public void startCast(String str, boolean z) {
        startCast(str, null, z);
    }

    public void startCast(String str, String str2, boolean z) {
        try {
            this.mServiceImpl.startCast(str, str2, z);
        } catch (RemoteException e) {
            notifyRemoteServiceDied(e);
        }
    }

    public void stopCast(String str) {
        stopCast(str, null);
    }

    public void stopCast(String str, String str2) {
        try {
            this.mServiceImpl.stopCast(str, str2);
        } catch (RemoteException e) {
            notifyRemoteServiceDied(e);
        }
    }

    public void stopCastWithError(String str, String str2) {
        stopCastWithError(str, str2, null);
    }

    public void stopCastWithError(String str, String str2, String str3) {
        try {
            this.mServiceImpl.stopCastWithError(str, str3, str2);
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
    public static VrShellCastDevice toShellCastDevice(VrCastDevice vrCastDevice) {
        if (vrCastDevice == null) {
            return null;
        }
        return new VrShellCastDevice(vrCastDevice.name, vrCastDevice.id, VrShellCastDevice.State.fromOrdinal(vrCastDevice.state), toDeviceType(vrCastDevice.type));
    }

    private static DeviceType toDeviceType(int i) {
        if (i == 1) {
            return DeviceType.Miracast;
        }
        if (i == 2) {
            return DeviceType.Chromecast;
        }
        throw new RuntimeException("Not supported device type " + i);
    }

    public static class VrShellCastDevice {
        public String id;
        public String name;
        public State state;
        public DeviceType type;

        public VrShellCastDevice(String str, String str2, State state2, DeviceType deviceType) {
            this.name = str;
            this.id = str2;
            this.state = state2;
            this.type = deviceType;
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

            public static State fromOrdinal(int i) {
                switch (i) {
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
