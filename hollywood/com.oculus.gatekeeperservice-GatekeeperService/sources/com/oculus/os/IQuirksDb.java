package com.oculus.os;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IQuirksDb extends IInterface {
    Bundle getQuirkForProcess(int i, int i2) throws RemoteException;

    void setQuirksForCaller(Bundle bundle) throws RemoteException;

    public static class Default implements IQuirksDb {
        @Override // com.oculus.os.IQuirksDb
        public void setQuirksForCaller(Bundle quirkBundle) throws RemoteException {
        }

        @Override // com.oculus.os.IQuirksDb
        public Bundle getQuirkForProcess(int pid, int quirkId) throws RemoteException {
            return null;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IQuirksDb {
        private static final String DESCRIPTOR = "com.oculus.os.IQuirksDb";
        static final int TRANSACTION_getQuirkForProcess = 2;
        static final int TRANSACTION_setQuirksForCaller = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IQuirksDb asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IQuirksDb)) {
                return new Proxy(obj);
            }
            return (IQuirksDb) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Bundle _arg0;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                setQuirksForCaller(_arg0);
                reply.writeNoException();
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                Bundle _result = getQuirkForProcess(data.readInt(), data.readInt());
                reply.writeNoException();
                if (_result != null) {
                    reply.writeInt(1);
                    _result.writeToParcel(reply, 1);
                } else {
                    reply.writeInt(0);
                }
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IQuirksDb {
            public static IQuirksDb sDefaultImpl;
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

            @Override // com.oculus.os.IQuirksDb
            public void setQuirksForCaller(Bundle quirkBundle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (quirkBundle != null) {
                        _data.writeInt(1);
                        quirkBundle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setQuirksForCaller(quirkBundle);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.os.IQuirksDb
            public Bundle getQuirkForProcess(int pid, int quirkId) throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(pid);
                    _data.writeInt(quirkId);
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getQuirkForProcess(pid, quirkId);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
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
        }

        public static boolean setDefaultImpl(IQuirksDb impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IQuirksDb getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
