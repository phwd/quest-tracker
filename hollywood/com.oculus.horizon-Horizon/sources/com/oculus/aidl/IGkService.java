package com.oculus.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IGkService extends IInterface {

    public static class Default implements IGkService {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.aidl.IGkService
        public void clearGatekeeperOverride(String str) throws RemoteException {
        }

        @Override // com.oculus.aidl.IGkService
        public boolean deregisterGatekeeper(String str) throws RemoteException {
            return false;
        }

        @Override // com.oculus.aidl.IGkService
        public boolean fetchGatekeeper(String str) throws RemoteException {
            return false;
        }

        @Override // com.oculus.aidl.IGkService
        public boolean fetchGatekeepers(long j) throws RemoteException {
            return false;
        }

        @Override // com.oculus.aidl.IGkService
        public void fetchGatekeepersAsync() throws RemoteException {
        }

        @Override // com.oculus.aidl.IGkService
        public boolean getGatekeeper(String str) throws RemoteException {
            return false;
        }

        @Override // com.oculus.aidl.IGkService
        public boolean getGatekeeperDef(String str, boolean z) throws RemoteException {
            return false;
        }

        @Override // com.oculus.aidl.IGkService
        public void overrideGatekeeper(String str, boolean z) throws RemoteException {
        }

        @Override // com.oculus.aidl.IGkService
        public boolean registerGatekeeper(String str) throws RemoteException {
            return false;
        }
    }

    public static abstract class Stub extends Binder implements IGkService {
        public static final String DESCRIPTOR = "com.oculus.aidl.IGkService";
        public static final int TRANSACTION_clearGatekeeperOverride = 4;
        public static final int TRANSACTION_deregisterGatekeeper = 2;
        public static final int TRANSACTION_fetchGatekeeper = 7;
        public static final int TRANSACTION_fetchGatekeepers = 8;
        public static final int TRANSACTION_fetchGatekeepersAsync = 9;
        public static final int TRANSACTION_getGatekeeper = 5;
        public static final int TRANSACTION_getGatekeeperDef = 6;
        public static final int TRANSACTION_overrideGatekeeper = 3;
        public static final int TRANSACTION_registerGatekeeper = 1;

        public IBinder asBinder() {
            return this;
        }

        public static class Proxy implements IGkService {
            public static IGkService sDefaultImpl;
            public IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.oculus.aidl.IGkService
            public void clearGatekeeperOverride(String str) throws RemoteException {
                IGkService iGkService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || (iGkService = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iGkService.clearGatekeeperOverride(str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IGkService
            public boolean deregisterGatekeeper(String str) throws RemoteException {
                IGkService iGkService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && (iGkService = sDefaultImpl) != null) {
                        return iGkService.deregisterGatekeeper(str);
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

            @Override // com.oculus.aidl.IGkService
            public boolean fetchGatekeeper(String str) throws RemoteException {
                IGkService iGkService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && (iGkService = sDefaultImpl) != null) {
                        return iGkService.fetchGatekeeper(str);
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

            @Override // com.oculus.aidl.IGkService
            public boolean fetchGatekeepers(long j) throws RemoteException {
                IGkService iGkService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    boolean z = false;
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && (iGkService = sDefaultImpl) != null) {
                        return iGkService.fetchGatekeepers(j);
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

            @Override // com.oculus.aidl.IGkService
            public void fetchGatekeepersAsync() throws RemoteException {
                IGkService iGkService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(9, obtain, obtain2, 0) || (iGkService = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iGkService.fetchGatekeepersAsync();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IGkService
            public boolean getGatekeeper(String str) throws RemoteException {
                IGkService iGkService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && (iGkService = sDefaultImpl) != null) {
                        return iGkService.getGatekeeper(str);
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

            @Override // com.oculus.aidl.IGkService
            public boolean getGatekeeperDef(String str, boolean z) throws RemoteException {
                IGkService iGkService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z2 = true;
                    int i = 0;
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && (iGkService = sDefaultImpl) != null) {
                        return iGkService.getGatekeeperDef(str, z);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IGkService
            public void overrideGatekeeper(String str, boolean z) throws RemoteException {
                IGkService iGkService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    int i = 0;
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || (iGkService = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iGkService.overrideGatekeeper(str, z);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IGkService
            public boolean registerGatekeeper(String str) throws RemoteException {
                IGkService iGkService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && (iGkService = sDefaultImpl) != null) {
                        return iGkService.registerGatekeeper(str);
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
        }

        public static IGkService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IGkService)) {
                return new Proxy(iBinder);
            }
            return (IGkService) queryLocalInterface;
        }

        public static boolean setDefaultImpl(IGkService iGkService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iGkService == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iGkService;
                return true;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGkService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            boolean z;
            if (i != 1598968902) {
                boolean z2 = false;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        z = registerGatekeeper(parcel.readString());
                        parcel2.writeNoException();
                        int i3 = z ? 1 : 0;
                        int i4 = z ? 1 : 0;
                        int i5 = z ? 1 : 0;
                        int i6 = z ? 1 : 0;
                        int i7 = z ? 1 : 0;
                        int i8 = z ? 1 : 0;
                        int i9 = z ? 1 : 0;
                        parcel2.writeInt(i3);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        z = deregisterGatekeeper(parcel.readString());
                        parcel2.writeNoException();
                        int i32 = z ? 1 : 0;
                        int i42 = z ? 1 : 0;
                        int i52 = z ? 1 : 0;
                        int i62 = z ? 1 : 0;
                        int i72 = z ? 1 : 0;
                        int i82 = z ? 1 : 0;
                        int i92 = z ? 1 : 0;
                        parcel2.writeInt(i32);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString = parcel.readString();
                        if (parcel.readInt() != 0) {
                            z2 = true;
                        }
                        overrideGatekeeper(readString, z2);
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        clearGatekeeperOverride(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        z = getGatekeeper(parcel.readString());
                        parcel2.writeNoException();
                        int i322 = z ? 1 : 0;
                        int i422 = z ? 1 : 0;
                        int i522 = z ? 1 : 0;
                        int i622 = z ? 1 : 0;
                        int i722 = z ? 1 : 0;
                        int i822 = z ? 1 : 0;
                        int i922 = z ? 1 : 0;
                        parcel2.writeInt(i322);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString2 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            z2 = true;
                        }
                        z = getGatekeeperDef(readString2, z2);
                        parcel2.writeNoException();
                        int i3222 = z ? 1 : 0;
                        int i4222 = z ? 1 : 0;
                        int i5222 = z ? 1 : 0;
                        int i6222 = z ? 1 : 0;
                        int i7222 = z ? 1 : 0;
                        int i8222 = z ? 1 : 0;
                        int i9222 = z ? 1 : 0;
                        parcel2.writeInt(i3222);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        z = fetchGatekeeper(parcel.readString());
                        parcel2.writeNoException();
                        int i32222 = z ? 1 : 0;
                        int i42222 = z ? 1 : 0;
                        int i52222 = z ? 1 : 0;
                        int i62222 = z ? 1 : 0;
                        int i72222 = z ? 1 : 0;
                        int i82222 = z ? 1 : 0;
                        int i92222 = z ? 1 : 0;
                        parcel2.writeInt(i32222);
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        z = fetchGatekeepers(parcel.readLong());
                        parcel2.writeNoException();
                        int i322222 = z ? 1 : 0;
                        int i422222 = z ? 1 : 0;
                        int i522222 = z ? 1 : 0;
                        int i622222 = z ? 1 : 0;
                        int i722222 = z ? 1 : 0;
                        int i822222 = z ? 1 : 0;
                        int i922222 = z ? 1 : 0;
                        parcel2.writeInt(i322222);
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        fetchGatekeepersAsync();
                        parcel2.writeNoException();
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }
    }

    void clearGatekeeperOverride(String str) throws RemoteException;

    boolean deregisterGatekeeper(String str) throws RemoteException;

    boolean fetchGatekeeper(String str) throws RemoteException;

    boolean fetchGatekeepers(long j) throws RemoteException;

    void fetchGatekeepersAsync() throws RemoteException;

    boolean getGatekeeper(String str) throws RemoteException;

    boolean getGatekeeperDef(String str, boolean z) throws RemoteException;

    void overrideGatekeeper(String str, boolean z) throws RemoteException;

    boolean registerGatekeeper(String str) throws RemoteException;
}
