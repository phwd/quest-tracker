package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public interface ITrackedObjectService extends IInterface {
    void getSupportedObjects(TrackedObjectInfo[] trackedObjectInfoArr) throws RemoteException;

    void resetTrackedObjects() throws RemoteException;

    ParcelFileDescriptor startTrackingObject(int i) throws RemoteException;

    void startTrackingObjectLegacy(int i) throws RemoteException;

    void stopTrackingObject(int i) throws RemoteException;

    public static class Default implements ITrackedObjectService {
        @Override // oculus.internal.ITrackedObjectService
        public ParcelFileDescriptor startTrackingObject(int type) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.ITrackedObjectService
        public void startTrackingObjectLegacy(int type) throws RemoteException {
        }

        @Override // oculus.internal.ITrackedObjectService
        public void stopTrackingObject(int type) throws RemoteException {
        }

        @Override // oculus.internal.ITrackedObjectService
        public void getSupportedObjects(TrackedObjectInfo[] objects) throws RemoteException {
        }

        @Override // oculus.internal.ITrackedObjectService
        public void resetTrackedObjects() throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ITrackedObjectService {
        private static final String DESCRIPTOR = "oculus.internal.ITrackedObjectService";
        static final int TRANSACTION_getSupportedObjects = 4;
        static final int TRANSACTION_resetTrackedObjects = 5;
        static final int TRANSACTION_startTrackingObject = 1;
        static final int TRANSACTION_startTrackingObjectLegacy = 2;
        static final int TRANSACTION_stopTrackingObject = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITrackedObjectService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITrackedObjectService)) {
                return new Proxy(obj);
            }
            return (ITrackedObjectService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            TrackedObjectInfo[] _arg0;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                ParcelFileDescriptor _result = startTrackingObject(data.readInt());
                reply.writeNoException();
                if (_result != null) {
                    reply.writeInt(1);
                    _result.writeToParcel(reply, 1);
                } else {
                    reply.writeInt(0);
                }
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                startTrackingObjectLegacy(data.readInt());
                reply.writeNoException();
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                stopTrackingObject(data.readInt());
                reply.writeNoException();
                return true;
            } else if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                int _arg0_length = data.readInt();
                if (_arg0_length < 0) {
                    _arg0 = null;
                } else {
                    _arg0 = new TrackedObjectInfo[_arg0_length];
                }
                getSupportedObjects(_arg0);
                reply.writeNoException();
                reply.writeTypedArray(_arg0, 1);
                return true;
            } else if (code == 5) {
                data.enforceInterface(DESCRIPTOR);
                resetTrackedObjects();
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
        public static class Proxy implements ITrackedObjectService {
            public static ITrackedObjectService sDefaultImpl;
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

            @Override // oculus.internal.ITrackedObjectService
            public ParcelFileDescriptor startTrackingObject(int type) throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(type);
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startTrackingObject(type);
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

            @Override // oculus.internal.ITrackedObjectService
            public void startTrackingObjectLegacy(int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(type);
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().startTrackingObjectLegacy(type);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ITrackedObjectService
            public void stopTrackingObject(int type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(type);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().stopTrackingObject(type);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ITrackedObjectService
            public void getSupportedObjects(TrackedObjectInfo[] objects) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (objects == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(objects.length);
                    }
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.readTypedArray(objects, TrackedObjectInfo.CREATOR);
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getSupportedObjects(objects);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ITrackedObjectService
            public void resetTrackedObjects() throws RemoteException {
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
                    Stub.getDefaultImpl().resetTrackedObjects();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITrackedObjectService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ITrackedObjectService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
