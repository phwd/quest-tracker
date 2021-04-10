package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import oculus.internal.ISensorDataClient;

public interface ISensorDataProvider extends IInterface {
    void getAvailableFrameTypes(int[] iArr) throws RemoteException;

    ISensorDataClient getSensorDataClient(int i) throws RemoteException;

    public static class Default implements ISensorDataProvider {
        @Override // oculus.internal.ISensorDataProvider
        public void getAvailableFrameTypes(int[] frameTypes) throws RemoteException {
        }

        @Override // oculus.internal.ISensorDataProvider
        public ISensorDataClient getSensorDataClient(int frameType) throws RemoteException {
            return null;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ISensorDataProvider {
        private static final String DESCRIPTOR = "oculus.internal.ISensorDataProvider";
        static final int TRANSACTION_getAvailableFrameTypes = 1;
        static final int TRANSACTION_getSensorDataClient = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISensorDataProvider asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ISensorDataProvider)) {
                return new Proxy(obj);
            }
            return (ISensorDataProvider) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int[] _arg0;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                int _arg0_length = data.readInt();
                if (_arg0_length < 0) {
                    _arg0 = null;
                } else {
                    _arg0 = new int[_arg0_length];
                }
                getAvailableFrameTypes(_arg0);
                reply.writeNoException();
                reply.writeIntArray(_arg0);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                ISensorDataClient _result = getSensorDataClient(data.readInt());
                reply.writeNoException();
                reply.writeStrongBinder(_result != null ? _result.asBinder() : null);
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements ISensorDataProvider {
            public static ISensorDataProvider sDefaultImpl;
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

            @Override // oculus.internal.ISensorDataProvider
            public void getAvailableFrameTypes(int[] frameTypes) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (frameTypes == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(frameTypes.length);
                    }
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.readIntArray(frameTypes);
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getAvailableFrameTypes(frameTypes);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ISensorDataProvider
            public ISensorDataClient getSensorDataClient(int frameType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(frameType);
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSensorDataClient(frameType);
                    }
                    _reply.readException();
                    ISensorDataClient _result = ISensorDataClient.Stub.asInterface(_reply.readStrongBinder());
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISensorDataProvider impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ISensorDataProvider getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
