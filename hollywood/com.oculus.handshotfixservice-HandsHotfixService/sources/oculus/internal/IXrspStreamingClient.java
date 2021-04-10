package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public interface IXrspStreamingClient extends IInterface {
    void onSessionStarted(ParcelFileDescriptor parcelFileDescriptor, ParcelFileDescriptor parcelFileDescriptor2) throws RemoteException;

    void onSessionStopped(int i) throws RemoteException;

    void onTCPSessionStarted(String str) throws RemoteException;

    public static class Default implements IXrspStreamingClient {
        @Override // oculus.internal.IXrspStreamingClient
        public void onSessionStarted(ParcelFileDescriptor bulkRead, ParcelFileDescriptor bulkWrite) throws RemoteException {
        }

        @Override // oculus.internal.IXrspStreamingClient
        public void onTCPSessionStarted(String ipAddr) throws RemoteException {
        }

        @Override // oculus.internal.IXrspStreamingClient
        public void onSessionStopped(int reason) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IXrspStreamingClient {
        private static final String DESCRIPTOR = "oculus.internal.IXrspStreamingClient";
        static final int TRANSACTION_onSessionStarted = 1;
        static final int TRANSACTION_onSessionStopped = 3;
        static final int TRANSACTION_onTCPSessionStarted = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IXrspStreamingClient asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IXrspStreamingClient)) {
                return new Proxy(obj);
            }
            return (IXrspStreamingClient) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ParcelFileDescriptor _arg0;
            ParcelFileDescriptor _arg1;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                if (data.readInt() != 0) {
                    _arg1 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(data);
                } else {
                    _arg1 = null;
                }
                onSessionStarted(_arg0, _arg1);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                onTCPSessionStarted(data.readString());
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                onSessionStopped(data.readInt());
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IXrspStreamingClient {
            public static IXrspStreamingClient sDefaultImpl;
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

            @Override // oculus.internal.IXrspStreamingClient
            public void onSessionStarted(ParcelFileDescriptor bulkRead, ParcelFileDescriptor bulkWrite) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bulkRead != null) {
                        _data.writeInt(1);
                        bulkRead.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (bulkWrite != null) {
                        _data.writeInt(1);
                        bulkWrite.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (this.mRemote.transact(1, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().onSessionStarted(bulkRead, bulkWrite);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IXrspStreamingClient
            public void onTCPSessionStarted(String ipAddr) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(ipAddr);
                    if (this.mRemote.transact(2, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().onTCPSessionStarted(ipAddr);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IXrspStreamingClient
            public void onSessionStopped(int reason) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(reason);
                    if (this.mRemote.transact(3, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().onSessionStopped(reason);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IXrspStreamingClient impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IXrspStreamingClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
