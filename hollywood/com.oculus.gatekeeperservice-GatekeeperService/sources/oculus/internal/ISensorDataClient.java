package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public interface ISensorDataClient extends IInterface {
    ParcelFileDescriptor addSignalListener() throws RemoteException;

    void getAvailableCameraIds(int[] iArr) throws RemoteException;

    void getBuffers(ParcelFileDescriptor[] parcelFileDescriptorArr) throws RemoteException;

    void startStream() throws RemoteException;

    void stopStream() throws RemoteException;

    public static class Default implements ISensorDataClient {
        @Override // oculus.internal.ISensorDataClient
        public void getAvailableCameraIds(int[] cameraIds) throws RemoteException {
        }

        @Override // oculus.internal.ISensorDataClient
        public void getBuffers(ParcelFileDescriptor[] buffers) throws RemoteException {
        }

        @Override // oculus.internal.ISensorDataClient
        public ParcelFileDescriptor addSignalListener() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.ISensorDataClient
        public void startStream() throws RemoteException {
        }

        @Override // oculus.internal.ISensorDataClient
        public void stopStream() throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ISensorDataClient {
        private static final String DESCRIPTOR = "oculus.internal.ISensorDataClient";
        static final int TRANSACTION_addSignalListener = 3;
        static final int TRANSACTION_getAvailableCameraIds = 1;
        static final int TRANSACTION_getBuffers = 2;
        static final int TRANSACTION_startStream = 4;
        static final int TRANSACTION_stopStream = 5;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISensorDataClient asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ISensorDataClient)) {
                return new Proxy(obj);
            }
            return (ISensorDataClient) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int[] _arg0;
            ParcelFileDescriptor[] _arg02;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                int _arg0_length = data.readInt();
                if (_arg0_length < 0) {
                    _arg0 = null;
                } else {
                    _arg0 = new int[_arg0_length];
                }
                getAvailableCameraIds(_arg0);
                reply.writeNoException();
                reply.writeIntArray(_arg0);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _arg0_length2 = data.readInt();
                if (_arg0_length2 < 0) {
                    _arg02 = null;
                } else {
                    _arg02 = new ParcelFileDescriptor[_arg0_length2];
                }
                getBuffers(_arg02);
                reply.writeNoException();
                reply.writeTypedArray(_arg02, 1);
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                ParcelFileDescriptor _result = addSignalListener();
                reply.writeNoException();
                if (_result != null) {
                    reply.writeInt(1);
                    _result.writeToParcel(reply, 1);
                } else {
                    reply.writeInt(0);
                }
                return true;
            } else if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                startStream();
                reply.writeNoException();
                return true;
            } else if (code == 5) {
                data.enforceInterface(DESCRIPTOR);
                stopStream();
                reply.writeNoException();
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements ISensorDataClient {
            public static ISensorDataClient sDefaultImpl;
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

            @Override // oculus.internal.ISensorDataClient
            public void getAvailableCameraIds(int[] cameraIds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cameraIds == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(cameraIds.length);
                    }
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.readIntArray(cameraIds);
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getAvailableCameraIds(cameraIds);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ISensorDataClient
            public void getBuffers(ParcelFileDescriptor[] buffers) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (buffers == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(buffers.length);
                    }
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.readTypedArray(buffers, ParcelFileDescriptor.CREATOR);
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getBuffers(buffers);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ISensorDataClient
            public ParcelFileDescriptor addSignalListener() throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addSignalListener();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ISensorDataClient
            public void startStream() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().startStream();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ISensorDataClient
            public void stopStream() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().stopStream();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISensorDataClient impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ISensorDataClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
