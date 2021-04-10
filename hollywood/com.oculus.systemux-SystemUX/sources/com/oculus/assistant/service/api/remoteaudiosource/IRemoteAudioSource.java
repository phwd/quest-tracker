package com.oculus.assistant.service.api.remoteaudiosource;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IRemoteAudioSource extends IInterface {

    public static class Default implements IRemoteAudioSource {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
        public void close() throws RemoteException {
        }

        @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
        public String getSourceName() throws RemoteException {
            return null;
        }

        @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
        public boolean isSourceAvailable() throws RemoteException {
            return false;
        }

        @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
        public void open() throws RemoteException {
        }

        @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
        public int read(byte[] bArr, int i, int i2) throws RemoteException {
            return 0;
        }
    }

    void close() throws RemoteException;

    String getSourceName() throws RemoteException;

    boolean isSourceAvailable() throws RemoteException;

    void open() throws RemoteException;

    int read(byte[] bArr, int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IRemoteAudioSource {
        private static final String DESCRIPTOR = "com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource";
        static final int TRANSACTION_close = 4;
        static final int TRANSACTION_getSourceName = 5;
        static final int TRANSACTION_isSourceAvailable = 2;
        static final int TRANSACTION_open = 3;
        static final int TRANSACTION_read = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteAudioSource asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IRemoteAudioSource)) {
                return new Proxy(iBinder);
            }
            return (IRemoteAudioSource) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                byte[] createByteArray = parcel.createByteArray();
                int read = read(createByteArray, parcel.readInt(), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeInt(read);
                parcel2.writeByteArray(createByteArray);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean isSourceAvailable = isSourceAvailable();
                parcel2.writeNoException();
                parcel2.writeInt(isSourceAvailable ? 1 : 0);
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                open();
                parcel2.writeNoException();
                return true;
            } else if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                close();
                parcel2.writeNoException();
                return true;
            } else if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                String sourceName = getSourceName();
                parcel2.writeNoException();
                parcel2.writeString(sourceName);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IRemoteAudioSource {
            public static IRemoteAudioSource sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
            public int read(byte[] bArr, int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().read(bArr, i, i2);
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readByteArray(bArr);
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
            public boolean isSourceAvailable() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSourceAvailable();
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

            @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
            public void open() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().open();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
            public void close() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().close();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
            public String getSourceName() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSourceName();
                    }
                    obtain2.readException();
                    String readString = obtain2.readString();
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoteAudioSource iRemoteAudioSource) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iRemoteAudioSource == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iRemoteAudioSource;
                return true;
            }
        }

        public static IRemoteAudioSource getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
