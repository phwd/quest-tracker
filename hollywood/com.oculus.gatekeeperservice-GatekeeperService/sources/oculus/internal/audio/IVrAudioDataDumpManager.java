package oculus.internal.audio;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import oculus.internal.audio.IVrAudioDataDumpManagerClient;

public interface IVrAudioDataDumpManager extends IInterface {
    public static final int STATE_CHECKING_GK = 2;
    public static final int STATE_COUNT = 6;
    public static final int STATE_MANAGER_STOPPED = 1;
    public static final int STATE_PAUSED = 5;
    public static final int STATE_RUNNING = 3;
    public static final int STATE_STARTING = 0;
    public static final int STATE_ZIP_DUMP = 4;

    void registerClient(IVrAudioDataDumpManagerClient iVrAudioDataDumpManagerClient) throws RemoteException;

    void unregisterClient(IVrAudioDataDumpManagerClient iVrAudioDataDumpManagerClient) throws RemoteException;

    void zipAudioDataDumpFiles() throws RemoteException;

    public static class Default implements IVrAudioDataDumpManager {
        @Override // oculus.internal.audio.IVrAudioDataDumpManager
        public void registerClient(IVrAudioDataDumpManagerClient cb) throws RemoteException {
        }

        @Override // oculus.internal.audio.IVrAudioDataDumpManager
        public void unregisterClient(IVrAudioDataDumpManagerClient cb) throws RemoteException {
        }

        @Override // oculus.internal.audio.IVrAudioDataDumpManager
        public void zipAudioDataDumpFiles() throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IVrAudioDataDumpManager {
        private static final String DESCRIPTOR = "oculus.internal.audio.IVrAudioDataDumpManager";
        static final int TRANSACTION_registerClient = 1;
        static final int TRANSACTION_unregisterClient = 2;
        static final int TRANSACTION_zipAudioDataDumpFiles = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVrAudioDataDumpManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IVrAudioDataDumpManager)) {
                return new Proxy(obj);
            }
            return (IVrAudioDataDumpManager) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                registerClient(IVrAudioDataDumpManagerClient.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                unregisterClient(IVrAudioDataDumpManagerClient.Stub.asInterface(data.readStrongBinder()));
                reply.writeNoException();
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                zipAudioDataDumpFiles();
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
        public static class Proxy implements IVrAudioDataDumpManager {
            public static IVrAudioDataDumpManager sDefaultImpl;
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

            @Override // oculus.internal.audio.IVrAudioDataDumpManager
            public void registerClient(IVrAudioDataDumpManagerClient cb) throws RemoteException {
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

            @Override // oculus.internal.audio.IVrAudioDataDumpManager
            public void unregisterClient(IVrAudioDataDumpManagerClient cb) throws RemoteException {
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

            @Override // oculus.internal.audio.IVrAudioDataDumpManager
            public void zipAudioDataDumpFiles() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().zipAudioDataDumpFiles();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVrAudioDataDumpManager impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IVrAudioDataDumpManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
