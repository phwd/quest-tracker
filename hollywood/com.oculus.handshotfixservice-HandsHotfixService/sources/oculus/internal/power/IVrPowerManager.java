package oculus.internal.power;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import oculus.internal.power.IVrPowerManagerClient;

public interface IVrPowerManager extends IInterface {
    public static final int DEFAULT_AUTOSLEEP_TIME_IN_SECONDS = 15;
    public static final int STATE_COUNT = 4;
    public static final int STATE_HEADSET_MOUNTED = 1;
    public static final int STATE_HEADSET_UNMOUNTED = 2;
    public static final int STATE_STANDBY = 3;
    public static final int STATE_WAITING_FOR_SLEEP_MSG = 4;

    void acquireMountWakeLock(IBinder iBinder) throws RemoteException;

    void notifyDeviceIdle() throws RemoteException;

    void notifyDeviceNotIdle() throws RemoteException;

    void registerClient(IVrPowerManagerClient iVrPowerManagerClient) throws RemoteException;

    void releaseMountWakeLock(IBinder iBinder) throws RemoteException;

    void unregisterClient(IVrPowerManagerClient iVrPowerManagerClient) throws RemoteException;

    public static class Default implements IVrPowerManager {
        @Override // oculus.internal.power.IVrPowerManager
        public void registerClient(IVrPowerManagerClient cb) throws RemoteException {
        }

        @Override // oculus.internal.power.IVrPowerManager
        public void unregisterClient(IVrPowerManagerClient cb) throws RemoteException {
        }

        @Override // oculus.internal.power.IVrPowerManager
        public void notifyDeviceIdle() throws RemoteException {
        }

        @Override // oculus.internal.power.IVrPowerManager
        public void notifyDeviceNotIdle() throws RemoteException {
        }

        @Override // oculus.internal.power.IVrPowerManager
        public void acquireMountWakeLock(IBinder token) throws RemoteException {
        }

        @Override // oculus.internal.power.IVrPowerManager
        public void releaseMountWakeLock(IBinder token) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IVrPowerManager {
        private static final String DESCRIPTOR = "oculus.internal.power.IVrPowerManager";
        static final int TRANSACTION_acquireMountWakeLock = 5;
        static final int TRANSACTION_notifyDeviceIdle = 3;
        static final int TRANSACTION_notifyDeviceNotIdle = 4;
        static final int TRANSACTION_registerClient = 1;
        static final int TRANSACTION_releaseMountWakeLock = 6;
        static final int TRANSACTION_unregisterClient = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVrPowerManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IVrPowerManager)) {
                return new Proxy(obj);
            }
            return (IVrPowerManager) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1598968902) {
                switch (code) {
                    case 1:
                        data.enforceInterface(DESCRIPTOR);
                        registerClient(IVrPowerManagerClient.Stub.asInterface(data.readStrongBinder()));
                        reply.writeNoException();
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        unregisterClient(IVrPowerManagerClient.Stub.asInterface(data.readStrongBinder()));
                        reply.writeNoException();
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        notifyDeviceIdle();
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        notifyDeviceNotIdle();
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        acquireMountWakeLock(data.readStrongBinder());
                        reply.writeNoException();
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        releaseMountWakeLock(data.readStrongBinder());
                        reply.writeNoException();
                        return true;
                    default:
                        return super.onTransact(code, data, reply, flags);
                }
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IVrPowerManager {
            public static IVrPowerManager sDefaultImpl;
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

            @Override // oculus.internal.power.IVrPowerManager
            public void registerClient(IVrPowerManagerClient cb) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().registerClient(cb);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.power.IVrPowerManager
            public void unregisterClient(IVrPowerManagerClient cb) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(cb != null ? cb.asBinder() : null);
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unregisterClient(cb);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.power.IVrPowerManager
            public void notifyDeviceIdle() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().notifyDeviceIdle();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // oculus.internal.power.IVrPowerManager
            public void notifyDeviceNotIdle() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().notifyDeviceNotIdle();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // oculus.internal.power.IVrPowerManager
            public void acquireMountWakeLock(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    if (this.mRemote.transact(5, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().acquireMountWakeLock(token);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.power.IVrPowerManager
            public void releaseMountWakeLock(IBinder token) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(token);
                    if (this.mRemote.transact(6, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().releaseMountWakeLock(token);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVrPowerManager impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IVrPowerManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
