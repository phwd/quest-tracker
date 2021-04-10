package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import oculus.internal.IClientCamera;

public interface ICameraDataProvider extends IInterface {
    public static final int CAMERA_POSITION_BOTTOM_LEFT = 3;
    public static final int CAMERA_POSITION_BOTTOM_RIGHT = 4;
    public static final int CAMERA_POSITION_TOP_LEFT = 1;
    public static final int CAMERA_POSITION_TOP_RIGHT = 2;
    public static final int CAMERA_POSITION_UNKNOWN = 0;

    void getAvailableCameras(CameraInfo[] cameraInfoArr) throws RemoteException;

    IClientCamera getCamera(int i) throws RemoteException;

    public static class Default implements ICameraDataProvider {
        @Override // oculus.internal.ICameraDataProvider
        public void getAvailableCameras(CameraInfo[] cameras) throws RemoteException {
        }

        @Override // oculus.internal.ICameraDataProvider
        public IClientCamera getCamera(int cameraId) throws RemoteException {
            return null;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICameraDataProvider {
        private static final String DESCRIPTOR = "oculus.internal.ICameraDataProvider";
        static final int TRANSACTION_getAvailableCameras = 1;
        static final int TRANSACTION_getCamera = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICameraDataProvider asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ICameraDataProvider)) {
                return new Proxy(obj);
            }
            return (ICameraDataProvider) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            CameraInfo[] _arg0;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                int _arg0_length = data.readInt();
                if (_arg0_length < 0) {
                    _arg0 = null;
                } else {
                    _arg0 = new CameraInfo[_arg0_length];
                }
                getAvailableCameras(_arg0);
                reply.writeNoException();
                reply.writeTypedArray(_arg0, 1);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                IClientCamera _result = getCamera(data.readInt());
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
        public static class Proxy implements ICameraDataProvider {
            public static ICameraDataProvider sDefaultImpl;
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

            @Override // oculus.internal.ICameraDataProvider
            public void getAvailableCameras(CameraInfo[] cameras) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (cameras == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(cameras.length);
                    }
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.readTypedArray(cameras, CameraInfo.CREATOR);
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getAvailableCameras(cameras);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ICameraDataProvider
            public IClientCamera getCamera(int cameraId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(cameraId);
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCamera(cameraId);
                    }
                    _reply.readException();
                    IClientCamera _result = IClientCamera.Stub.asInterface(_reply.readStrongBinder());
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICameraDataProvider impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ICameraDataProvider getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
