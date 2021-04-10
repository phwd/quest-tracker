package com.oculus.os.yadi;

import android.content.IntentSender;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IDownloadService extends IInterface {
    String enqueueDownload(RemoteResource remoteResource, String str, IntentSender intentSender, Bundle bundle) throws RemoteException;

    String enqueueSizeOf(RemoteResource remoteResource, IntentSender intentSender) throws RemoteException;

    void enqueueTaggedDownload(String str, RemoteResource remoteResource, String str2, IntentSender intentSender, Bundle bundle) throws RemoteException;

    void enqueueTaggedSizeOf(String str, RemoteResource remoteResource, IntentSender intentSender) throws RemoteException;

    public static class Default implements IDownloadService {
        @Override // com.oculus.os.yadi.IDownloadService
        public String enqueueDownload(RemoteResource request, String destinationPath, IntentSender intent, Bundle resumeInfo) throws RemoteException {
            return null;
        }

        @Override // com.oculus.os.yadi.IDownloadService
        public String enqueueSizeOf(RemoteResource request, IntentSender intent) throws RemoteException {
            return null;
        }

        @Override // com.oculus.os.yadi.IDownloadService
        public void enqueueTaggedDownload(String requestTag, RemoteResource request, String destinationPath, IntentSender intent, Bundle resumeInfo) throws RemoteException {
        }

        @Override // com.oculus.os.yadi.IDownloadService
        public void enqueueTaggedSizeOf(String requestTag, RemoteResource request, IntentSender intent) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IDownloadService {
        private static final String DESCRIPTOR = "com.oculus.os.yadi.IDownloadService";
        static final int TRANSACTION_enqueueDownload = 1;
        static final int TRANSACTION_enqueueSizeOf = 2;
        static final int TRANSACTION_enqueueTaggedDownload = 3;
        static final int TRANSACTION_enqueueTaggedSizeOf = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IDownloadService)) {
                return new Proxy(obj);
            }
            return (IDownloadService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            RemoteResource _arg0;
            IntentSender _arg2;
            Bundle _arg3;
            RemoteResource _arg02;
            IntentSender _arg1;
            RemoteResource _arg12;
            IntentSender _arg32;
            Bundle _arg4;
            RemoteResource _arg13;
            IntentSender _arg22;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg0 = RemoteResource.CREATOR.createFromParcel(data);
                } else {
                    _arg0 = null;
                }
                String _arg14 = data.readString();
                if (data.readInt() != 0) {
                    _arg2 = (IntentSender) IntentSender.CREATOR.createFromParcel(data);
                } else {
                    _arg2 = null;
                }
                if (data.readInt() != 0) {
                    _arg3 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                } else {
                    _arg3 = null;
                }
                String _result = enqueueDownload(_arg0, _arg14, _arg2, _arg3);
                reply.writeNoException();
                reply.writeString(_result);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                if (data.readInt() != 0) {
                    _arg02 = RemoteResource.CREATOR.createFromParcel(data);
                } else {
                    _arg02 = null;
                }
                if (data.readInt() != 0) {
                    _arg1 = (IntentSender) IntentSender.CREATOR.createFromParcel(data);
                } else {
                    _arg1 = null;
                }
                String _result2 = enqueueSizeOf(_arg02, _arg1);
                reply.writeNoException();
                reply.writeString(_result2);
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                String _arg03 = data.readString();
                if (data.readInt() != 0) {
                    _arg12 = RemoteResource.CREATOR.createFromParcel(data);
                } else {
                    _arg12 = null;
                }
                String _arg23 = data.readString();
                if (data.readInt() != 0) {
                    _arg32 = (IntentSender) IntentSender.CREATOR.createFromParcel(data);
                } else {
                    _arg32 = null;
                }
                if (data.readInt() != 0) {
                    _arg4 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                } else {
                    _arg4 = null;
                }
                enqueueTaggedDownload(_arg03, _arg12, _arg23, _arg32, _arg4);
                reply.writeNoException();
                return true;
            } else if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                String _arg04 = data.readString();
                if (data.readInt() != 0) {
                    _arg13 = RemoteResource.CREATOR.createFromParcel(data);
                } else {
                    _arg13 = null;
                }
                if (data.readInt() != 0) {
                    _arg22 = (IntentSender) IntentSender.CREATOR.createFromParcel(data);
                } else {
                    _arg22 = null;
                }
                enqueueTaggedSizeOf(_arg04, _arg13, _arg22);
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
        public static class Proxy implements IDownloadService {
            public static IDownloadService sDefaultImpl;
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

            @Override // com.oculus.os.yadi.IDownloadService
            public String enqueueDownload(RemoteResource request, String destinationPath, IntentSender intent, Bundle resumeInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(destinationPath);
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (resumeInfo != null) {
                        _data.writeInt(1);
                        resumeInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enqueueDownload(request, destinationPath, intent, resumeInfo);
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

            @Override // com.oculus.os.yadi.IDownloadService
            public String enqueueSizeOf(RemoteResource request, IntentSender intent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().enqueueSizeOf(request, intent);
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

            @Override // com.oculus.os.yadi.IDownloadService
            public void enqueueTaggedDownload(String requestTag, RemoteResource request, String destinationPath, IntentSender intent, Bundle resumeInfo) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(requestTag);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(destinationPath);
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (resumeInfo != null) {
                        _data.writeInt(1);
                        resumeInfo.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().enqueueTaggedDownload(requestTag, request, destinationPath, intent, resumeInfo);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.os.yadi.IDownloadService
            public void enqueueTaggedSizeOf(String requestTag, RemoteResource request, IntentSender intent) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(requestTag);
                    if (request != null) {
                        _data.writeInt(1);
                        request.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (intent != null) {
                        _data.writeInt(1);
                        intent.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().enqueueTaggedSizeOf(requestTag, request, intent);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IDownloadService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
