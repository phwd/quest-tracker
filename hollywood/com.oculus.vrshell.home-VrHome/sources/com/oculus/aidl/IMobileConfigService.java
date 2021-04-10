package com.oculus.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public interface IMobileConfigService extends IInterface {
    void logExposure(String str, String str2, String str3) throws RemoteException;

    ParcelFileDescriptor updateMCs(String str, ParcelFileDescriptor parcelFileDescriptor, int i) throws RemoteException;

    public static class Default implements IMobileConfigService {
        @Override // com.oculus.aidl.IMobileConfigService
        public ParcelFileDescriptor updateMCs(String hash, ParcelFileDescriptor fd, int clientVersion) throws RemoteException {
            return null;
        }

        @Override // com.oculus.aidl.IMobileConfigService
        public void logExposure(String configName, String paramName, String loggingId) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IMobileConfigService {
        private static final String DESCRIPTOR = "com.oculus.aidl.IMobileConfigService";
        static final int TRANSACTION_logExposure = 2;
        static final int TRANSACTION_updateMCs = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMobileConfigService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IMobileConfigService)) {
                return new Proxy(obj);
            }
            return (IMobileConfigService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ParcelFileDescriptor _arg1;
            switch (code) {
                case 1:
                    data.enforceInterface(DESCRIPTOR);
                    String _arg0 = data.readString();
                    if (data.readInt() != 0) {
                        _arg1 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(data);
                    } else {
                        _arg1 = null;
                    }
                    ParcelFileDescriptor _result = updateMCs(_arg0, _arg1, data.readInt());
                    reply.writeNoException();
                    if (_result != null) {
                        reply.writeInt(1);
                        _result.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 2:
                    data.enforceInterface(DESCRIPTOR);
                    logExposure(data.readString(), data.readString(), data.readString());
                    return true;
                case 1598968902:
                    reply.writeString(DESCRIPTOR);
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IMobileConfigService {
            public static IMobileConfigService sDefaultImpl;
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

            @Override // com.oculus.aidl.IMobileConfigService
            public ParcelFileDescriptor updateMCs(String hash, ParcelFileDescriptor fd, int clientVersion) throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(hash);
                    if (fd != null) {
                        _data.writeInt(1);
                        fd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeInt(clientVersion);
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            _result = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(_reply);
                        } else {
                            _result = null;
                        }
                        _reply.recycle();
                        _data.recycle();
                    } else {
                        _result = Stub.getDefaultImpl().updateMCs(hash, fd, clientVersion);
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IMobileConfigService
            public void logExposure(String configName, String paramName, String loggingId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(configName);
                    _data.writeString(paramName);
                    _data.writeString(loggingId);
                    if (this.mRemote.transact(2, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().logExposure(configName, paramName, loggingId);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IMobileConfigService impl) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (impl == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = impl;
                return true;
            }
        }

        public static IMobileConfigService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
