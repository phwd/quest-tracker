package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import oculus.internal.IClientSurface;

public interface ISurfaceManagerService extends IInterface {
    public static final int FLAG_OPAQUE = 1024;
    public static final int PIXEL_FORMAT_BGRA_8888 = 2;
    public static final int PIXEL_FORMAT_RGBA_8888 = 0;
    public static final int PIXEL_FORMAT_RGBX_8888 = 1;

    IClientSurface create(String str, int i, int i2, int i3, int i4) throws RemoteException;

    public static class Default implements ISurfaceManagerService {
        @Override // oculus.internal.ISurfaceManagerService
        public IClientSurface create(String id, int width, int height, int pixelFormat, int flags) throws RemoteException {
            return null;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ISurfaceManagerService {
        private static final String DESCRIPTOR = "oculus.internal.ISurfaceManagerService";
        static final int TRANSACTION_create = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISurfaceManagerService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ISurfaceManagerService)) {
                return new Proxy(obj);
            }
            return (ISurfaceManagerService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                IClientSurface _result = create(data.readString(), data.readInt(), data.readInt(), data.readInt(), data.readInt());
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
        public static class Proxy implements ISurfaceManagerService {
            public static ISurfaceManagerService sDefaultImpl;
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

            @Override // oculus.internal.ISurfaceManagerService
            public IClientSurface create(String id, int width, int height, int pixelFormat, int flags) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(id);
                    _data.writeInt(width);
                    _data.writeInt(height);
                    _data.writeInt(pixelFormat);
                    _data.writeInt(flags);
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().create(id, width, height, pixelFormat, flags);
                    }
                    _reply.readException();
                    IClientSurface _result = IClientSurface.Stub.asInterface(_reply.readStrongBinder());
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISurfaceManagerService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ISurfaceManagerService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
