package com.oculus.os.yadi;

import android.content.IntentSender;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IInstallService extends IInterface {
    String enqueueInstall(RemoteApp remoteApp, RemoteResource[] remoteResourceArr, IntentSender intentSender) throws RemoteException;

    void enqueueTaggedInstall(String str, RemoteApp remoteApp, RemoteResource[] remoteResourceArr, IntentSender intentSender) throws RemoteException;

    public static class Default implements IInstallService {
        @Override // com.oculus.os.yadi.IInstallService
        public String enqueueInstall(RemoteApp appRequest, RemoteResource[] resourceRequests, IntentSender statusIntent) throws RemoteException {
            return null;
        }

        @Override // com.oculus.os.yadi.IInstallService
        public void enqueueTaggedInstall(String requestTag, RemoteApp appRequest, RemoteResource[] resourceRequests, IntentSender statusIntent) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IInstallService {
        private static final String DESCRIPTOR = "com.oculus.os.yadi.IInstallService";
        static final int TRANSACTION_enqueueInstall = 1;
        static final int TRANSACTION_enqueueTaggedInstall = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInstallService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IInstallService)) {
                return new Proxy(obj);
            }
            return (IInstallService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            RemoteApp _arg0;
            IntentSender _arg2;
            RemoteApp _arg1;
            IntentSender _arg3;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = RemoteApp.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                RemoteResource[] _arg12 = (RemoteResource[]) data.createTypedArray(RemoteResource.CREATOR);
                if (data.readInt() != 0) {
                    _arg2 = (IntentSender) IntentSender.CREATOR.createFromParcel(data);
                } else {
                    _arg2 = null;
                }
                String _result = enqueueInstall(_arg0, _arg12, _arg2);
                reply.writeNoException();
                reply.writeString(_result);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                String _arg02 = data.readString();
                if (data.readInt() != 0) {
                    _arg1 = RemoteApp.CREATOR.createFromParcel(data);
                } else {
                    _arg1 = null;
                }
                RemoteResource[] _arg22 = (RemoteResource[]) data.createTypedArray(RemoteResource.CREATOR);
                if (data.readInt() != 0) {
                    _arg3 = (IntentSender) IntentSender.CREATOR.createFromParcel(data);
                } else {
                    _arg3 = null;
                }
                enqueueTaggedInstall(_arg02, _arg1, _arg22, _arg3);
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
        public static class Proxy implements IInstallService {
            public static IInstallService sDefaultImpl;
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

            @Override // com.oculus.os.yadi.IInstallService
            public String enqueueInstall(RemoteApp appRequest, RemoteResource[] resourceRequests, IntentSender statusIntent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (appRequest != null) {
                        _data.writeInt(1);
                        appRequest.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeTypedArray(resourceRequests, 0);
                    if (statusIntent != null) {
                        _data.writeInt(1);
                        statusIntent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enqueueInstall(appRequest, resourceRequests, statusIntent);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.os.yadi.IInstallService
            public void enqueueTaggedInstall(String requestTag, RemoteApp appRequest, RemoteResource[] resourceRequests, IntentSender statusIntent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(requestTag);
                    if (appRequest != null) {
                        _data.writeInt(1);
                        appRequest.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeTypedArray(resourceRequests, 0);
                    if (statusIntent != null) {
                        _data.writeInt(1);
                        statusIntent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().enqueueTaggedInstall(requestTag, appRequest, resourceRequests, statusIntent);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInstallService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IInstallService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
