package oculus.internal.remote;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import oculus.internal.remote.IControllerCalibrationResult;

public interface IControllerCalibrationCallback extends IInterface {
    public static final int CALIBRATION_ENDED = 2;
    public static final int CALIBRATION_RESTARTED = 1;
    public static final int CALIBRATION_RESULT_SENT = 4;
    public static final int CALIBRATION_SAMPLING_STARTED = 3;
    public static final int CALIBRATION_STARTED = 0;
    public static final int CALIBRATION_STATUS_COUNT = 7;
    public static final int CALIBRATION_TIMED_OUT = 5;
    public static final int OBSERVED_CONTROLLER_DISCONNECTED = 6;

    void onResult(IControllerCalibrationResult iControllerCalibrationResult) throws RemoteException;

    void onStatus(int i) throws RemoteException;

    public static class Default implements IControllerCalibrationCallback {
        @Override // oculus.internal.remote.IControllerCalibrationCallback
        public void onStatus(int status) throws RemoteException {
        }

        @Override // oculus.internal.remote.IControllerCalibrationCallback
        public void onResult(IControllerCalibrationResult result) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IControllerCalibrationCallback {
        private static final String DESCRIPTOR = "oculus.internal.remote.IControllerCalibrationCallback";
        static final int TRANSACTION_onResult = 2;
        static final int TRANSACTION_onStatus = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IControllerCalibrationCallback asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IControllerCalibrationCallback)) {
                return new Proxy(obj);
            }
            return (IControllerCalibrationCallback) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                onStatus(data.readInt());
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                onResult(IControllerCalibrationResult.Stub.asInterface(data.readStrongBinder()));
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IControllerCalibrationCallback {
            public static IControllerCalibrationCallback sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // oculus.internal.remote.IControllerCalibrationCallback
            public void onStatus(int status) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(status);
                    if (this.mRemote.transact(1, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().onStatus(status);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // oculus.internal.remote.IControllerCalibrationCallback
            public void onResult(IControllerCalibrationResult result) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(result != null ? result.asBinder() : null);
                    if (this.mRemote.transact(2, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().onResult(result);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IControllerCalibrationCallback impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IControllerCalibrationCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
