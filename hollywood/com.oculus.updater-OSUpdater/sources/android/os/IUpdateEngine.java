package android.os;

import android.os.IUpdateEngineCallback;

public interface IUpdateEngine extends IInterface {
    void applyPayload(String str, long j, long j2, String[] strArr) throws RemoteException;

    boolean bind(IUpdateEngineCallback iUpdateEngineCallback) throws RemoteException;

    void cancel() throws RemoteException;

    void resetStatus() throws RemoteException;

    void resume() throws RemoteException;

    void suspend() throws RemoteException;

    public static abstract class Stub extends Binder implements IUpdateEngine {
        public Stub() {
            attachInterface(this, "android.os.IUpdateEngine");
        }

        public static IUpdateEngine asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("android.os.IUpdateEngine");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IUpdateEngine)) {
                return new Proxy(iBinder);
            }
            return (IUpdateEngine) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("android.os.IUpdateEngine");
                        applyPayload(parcel.readString(), parcel.readLong(), parcel.readLong(), parcel.createStringArray());
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface("android.os.IUpdateEngine");
                        boolean bind = bind(IUpdateEngineCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeInt(bind ? 1 : 0);
                        return true;
                    case 3:
                        parcel.enforceInterface("android.os.IUpdateEngine");
                        suspend();
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface("android.os.IUpdateEngine");
                        resume();
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel.enforceInterface("android.os.IUpdateEngine");
                        cancel();
                        parcel2.writeNoException();
                        return true;
                    case 6:
                        parcel.enforceInterface("android.os.IUpdateEngine");
                        resetStatus();
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("android.os.IUpdateEngine");
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IUpdateEngine {
            public static IUpdateEngine sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.os.IUpdateEngine
            public void applyPayload(String str, long j, long j2, String[] strArr) throws RemoteException {
                Throwable th;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.os.IUpdateEngine");
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeStringArray(strArr);
                    try {
                        if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                            obtain2.readException();
                            obtain2.recycle();
                            obtain.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().applyPayload(str, j, j2, strArr);
                        obtain2.recycle();
                        obtain.recycle();
                    } catch (Throwable th2) {
                        th = th2;
                        obtain2.recycle();
                        obtain.recycle();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            @Override // android.os.IUpdateEngine
            public boolean bind(IUpdateEngineCallback iUpdateEngineCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.os.IUpdateEngine");
                    obtain.writeStrongBinder(iUpdateEngineCallback != null ? iUpdateEngineCallback.asBinder() : null);
                    boolean z = false;
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().bind(iUpdateEngineCallback);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IUpdateEngine
            public void suspend() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.os.IUpdateEngine");
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().suspend();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IUpdateEngine
            public void resume() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.os.IUpdateEngine");
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().resume();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IUpdateEngine
            public void cancel() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.os.IUpdateEngine");
                    if (this.mRemote.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().cancel();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // android.os.IUpdateEngine
            public void resetStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("android.os.IUpdateEngine");
                    if (this.mRemote.transact(6, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().resetStatus();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IUpdateEngine getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
