package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public interface ILogCollector extends IInterface {
    boolean collectLogArchive(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    boolean collectScreenshot(ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    public static class Default implements ILogCollector {
        @Override // oculus.internal.ILogCollector
        public boolean collectLogArchive(ParcelFileDescriptor fd) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.ILogCollector
        public boolean collectScreenshot(ParcelFileDescriptor fd) throws RemoteException {
            return false;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ILogCollector {
        private static final String DESCRIPTOR = "oculus.internal.ILogCollector";
        static final int TRANSACTION_collectLogArchive = 1;
        static final int TRANSACTION_collectScreenshot = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILogCollector asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ILogCollector)) {
                return new Proxy(obj);
            }
            return (ILogCollector) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ParcelFileDescriptor _arg0;
            ParcelFileDescriptor _arg02;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                boolean collectLogArchive = collectLogArchive(_arg0);
                reply.writeNoException();
                reply.writeInt(collectLogArchive ? 1 : 0);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg02 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(data);
                } else {
                    _arg02 = null;
                }
                boolean collectScreenshot = collectScreenshot(_arg02);
                reply.writeNoException();
                reply.writeInt(collectScreenshot ? 1 : 0);
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements ILogCollector {
            public static ILogCollector sDefaultImpl;
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

            @Override // oculus.internal.ILogCollector
            public boolean collectLogArchive(ParcelFileDescriptor fd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (fd != null) {
                        _data.writeInt(1);
                        fd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().collectLogArchive(fd);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ILogCollector
            public boolean collectScreenshot(ParcelFileDescriptor fd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (fd != null) {
                        _data.writeInt(1);
                        fd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().collectScreenshot(fd);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
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

        public static boolean setDefaultImpl(ILogCollector impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ILogCollector getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
