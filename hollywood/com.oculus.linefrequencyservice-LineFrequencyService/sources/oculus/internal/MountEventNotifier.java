package oculus.internal;

import android.content.Context;
import android.os.IHwBinder;
import android.os.RemoteException;
import android.util.Log;
import oculus.internal.IMountEventNotifier;
import vendor.oculus.hardware.sensors_java.V1_0.IMount;
import vendor.oculus.hardware.sensors_java.V1_0.IMountCallback;

public class MountEventNotifier implements IMountEventNotifier {
    public static final String TAG = MountEventNotifier.class.getSimpleName();
    private Context mContext;
    private MountSensor mSensor = null;

    private class MountSensor extends IMountCallback.Stub implements IHwBinder.DeathRecipient {
        private IMountEventNotifier.State mCurrentState;
        private boolean mIsEnabled = false;
        private IMount mMountService = IMount.getService(true);
        private IMountEventNotifier.Listener mSensorCallback;

        public MountSensor(IMountEventNotifier.Listener callback) throws RemoteException {
            this.mMountService.asBinder().linkToDeath(this, 0);
            this.mSensorCallback = callback;
        }

        public synchronized void setEnabled(boolean enabled) throws RemoteException {
            if (enabled != this.mIsEnabled) {
                if (enabled) {
                    this.mMountService.registerMountCallback(this);
                } else {
                    this.mMountService.unregisterMountCallback(this);
                    this.mCurrentState = null;
                }
                this.mIsEnabled = enabled;
            }
        }

        public synchronized boolean isEnabled() {
            return this.mIsEnabled;
        }

        @Override // vendor.oculus.hardware.sensors_java.V1_0.IMountCallback
        public synchronized void notifyMountState(boolean mounted) {
            if (this.mIsEnabled) {
                IMountEventNotifier.State state = mounted ? IMountEventNotifier.State.DON : IMountEventNotifier.State.DOFF;
                if (state != this.mCurrentState) {
                    this.mCurrentState = state;
                    this.mSensorCallback.onSensorChanged(state);
                }
            }
        }

        public synchronized void serviceDied(long cookie) {
            Log.w(MountEventNotifier.TAG, "mount service died, attempting to reconnect");
            try {
                this.mMountService = IMount.getService(true);
                this.mMountService.asBinder().linkToDeath(this, 0);
                if (this.mIsEnabled) {
                    this.mMountService.registerMountCallback(this);
                }
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public MountEventNotifier(Context context) {
    }

    @Override // oculus.internal.IMountEventNotifier
    public void registerListener(IMountEventNotifier.Listener listener) {
        try {
            this.mSensor = new MountSensor(listener);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // oculus.internal.IMountEventNotifier
    public void startListening() {
        MountSensor mountSensor = this.mSensor;
        if (mountSensor != null) {
            try {
                mountSensor.setEnabled(true);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Mount sensor is unreachable");
        }
    }

    @Override // oculus.internal.IMountEventNotifier
    public void stopListening() {
        MountSensor mountSensor = this.mSensor;
        if (mountSensor != null) {
            try {
                mountSensor.setEnabled(false);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new RuntimeException("Mount sensor is unreachable");
        }
    }

    @Override // oculus.internal.IMountEventNotifier
    public boolean isListening() {
        MountSensor mountSensor = this.mSensor;
        if (mountSensor == null) {
            return false;
        }
        return mountSensor.isEnabled();
    }
}
