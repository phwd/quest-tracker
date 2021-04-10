package oculus.internal.remote;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IControllerCalibrationResult extends IInterface {
    boolean accept() throws RemoteException;

    float getFloat() throws RemoteException;

    public static class Default implements IControllerCalibrationResult {
        @Override // oculus.internal.remote.IControllerCalibrationResult
        public float getFloat() throws RemoteException {
            return 0.0f;
        }

        @Override // oculus.internal.remote.IControllerCalibrationResult
        public boolean accept() throws RemoteException {
            return false;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IControllerCalibrationResult {
        private static final String DESCRIPTOR = "oculus.internal.remote.IControllerCalibrationResult";
        static final int TRANSACTION_accept = 2;
        static final int TRANSACTION_getFloat = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IControllerCalibrationResult asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IControllerCalibrationResult)) {
                return new Proxy(obj);
            }
            return (IControllerCalibrationResult) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                float _result = getFloat();
                reply.writeNoException();
                reply.writeFloat(_result);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                boolean accept = accept();
                reply.writeNoException();
                reply.writeInt(accept ? 1 : 0);
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IControllerCalibrationResult {
            public static IControllerCalibrationResult sDefaultImpl;
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

            @Override // oculus.internal.remote.IControllerCalibrationResult
            public float getFloat() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFloat();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.remote.IControllerCalibrationResult
            public boolean accept() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().accept();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IControllerCalibrationResult impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IControllerCalibrationResult getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
