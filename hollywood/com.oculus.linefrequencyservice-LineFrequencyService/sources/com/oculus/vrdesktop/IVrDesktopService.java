package com.oculus.vrdesktop;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;
import com.oculus.vrdesktop.IContainer;

public interface IVrDesktopService extends IInterface {
    IContainer startActivity(IBinder iBinder, Surface surface, int i, int i2, int i3, Intent intent) throws RemoteException;

    public static class Default implements IVrDesktopService {
        @Override // com.oculus.vrdesktop.IVrDesktopService
        public IContainer startActivity(IBinder parentActivityToken, Surface surface, int width, int height, int density, Intent intent) throws RemoteException {
            return null;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IVrDesktopService {
        private static final String DESCRIPTOR = "com.oculus.vrdesktop.IVrDesktopService";
        static final int TRANSACTION_startActivity = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVrDesktopService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IVrDesktopService)) {
                return new Proxy(obj);
            }
            return (IVrDesktopService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Surface _arg1;
            Intent _arg5;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                IBinder _arg0 = data.readStrongBinder();
                if (data.readInt() != 0) {
                    _arg1 = (Surface) Surface.CREATOR.createFromParcel(data);
                } else {
                    _arg1 = null;
                }
                int _arg2 = data.readInt();
                int _arg3 = data.readInt();
                int _arg4 = data.readInt();
                if (data.readInt() != 0) {
                    _arg5 = (Intent) Intent.CREATOR.createFromParcel(data);
                } else {
                    _arg5 = null;
                }
                IContainer _result = startActivity(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5);
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
        public static class Proxy implements IVrDesktopService {
            public static IVrDesktopService sDefaultImpl;
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

            @Override // com.oculus.vrdesktop.IVrDesktopService
            public IContainer startActivity(IBinder parentActivityToken, Surface surface, int width, int height, int density, Intent intent) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeStrongBinder(parentActivityToken);
                        if (surface != null) {
                            _data.writeInt(1);
                            surface.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        try {
                            _data.writeInt(width);
                        } catch (Throwable th2) {
                            th = th2;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(height);
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeInt(density);
                        if (intent != null) {
                            _data.writeInt(1);
                            intent.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            IContainer _result = IContainer.Stub.asInterface(_reply.readStrongBinder());
                            _reply.recycle();
                            _data.recycle();
                            return _result;
                        }
                        IContainer startActivity = Stub.getDefaultImpl().startActivity(parentActivityToken, surface, width, height, density, intent);
                        _reply.recycle();
                        _data.recycle();
                        return startActivity;
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }
        }

        public static boolean setDefaultImpl(IVrDesktopService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IVrDesktopService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
