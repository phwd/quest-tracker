package com.oculus.userserver.managerservice;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface IOculusUserManager extends IInterface {

    public static class Default implements IOculusUserManager {
        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final boolean A17() throws RemoteException {
            return false;
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A1I() throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final Bundle A1y() throws RemoteException {
            return null;
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final int A23() throws RemoteException {
            return 0;
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final List<Bundle> A24() throws RemoteException {
            return null;
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A2J() throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A3C() throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A3E(String str, String str2) throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A3I() throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A3J() throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A3K(int i) throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A3h() throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A3r(Bundle bundle) throws RemoteException {
        }

        public final IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IOculusUserManager {
        public static final String DESCRIPTOR = "com.oculus.userserver.managerservice.IOculusUserManager";
        public static final int TRANSACTION_canAddMoreUsers = 8;
        public static final int TRANSACTION_createUserAndSwitch = 1;
        public static final int TRANSACTION_getSelf = 12;
        public static final int TRANSACTION_getUserCount = 5;
        public static final int TRANSACTION_getUsers = 4;
        public static final int TRANSACTION_lockScreen = 2;
        public static final int TRANSACTION_refreshUsers = 11;
        public static final int TRANSACTION_registerUserLogin = 10;
        public static final int TRANSACTION_removeSelf = 7;
        public static final int TRANSACTION_removeUnclaimedUser = 13;
        public static final int TRANSACTION_removeUser = 6;
        public static final int TRANSACTION_showUserSwitcher = 9;
        public static final int TRANSACTION_updateUser = 3;

        public final IBinder asBinder() {
            return this;
        }

        public static class Proxy implements IOculusUserManager {
            public static IOculusUserManager sDefaultImpl;
            public IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final boolean A17() throws RemoteException {
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && (iOculusUserManager = sDefaultImpl) != null) {
                        return iOculusUserManager.A17();
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

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final void A1I() throws RemoteException {
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iOculusUserManager.A1I();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final Bundle A1y() throws RemoteException {
                Bundle bundle;
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(12, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(obtain2);
                        } else {
                            bundle = null;
                        }
                    } else {
                        bundle = iOculusUserManager.A1y();
                    }
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final int A23() throws RemoteException {
                int i;
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                        i = obtain2.readInt();
                    } else {
                        i = iOculusUserManager.A23();
                    }
                    return i;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final List<Bundle> A24() throws RemoteException {
                List<Bundle> list;
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                        list = obtain2.createTypedArrayList(Bundle.CREATOR);
                    } else {
                        list = iOculusUserManager.A24();
                    }
                    return list;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final void A2J() throws RemoteException {
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iOculusUserManager.A2J();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final void A3C() throws RemoteException {
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(11, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iOculusUserManager.A3C();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final void A3E(String str, String str2) throws RemoteException {
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(10, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iOculusUserManager.A3E(str, str2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final void A3I() throws RemoteException {
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iOculusUserManager.A3I();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final void A3J() throws RemoteException {
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(13, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iOculusUserManager.A3J();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final void A3K(int i) throws RemoteException {
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(6, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iOculusUserManager.A3K(i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final void A3h() throws RemoteException {
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(9, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iOculusUserManager.A3h();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final void A3r(Bundle bundle) throws RemoteException {
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iOculusUserManager.A3r(bundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.mRemote;
            }
        }

        public static IOculusUserManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOculusUserManager)) {
                return new Proxy(iBinder);
            }
            return (IOculusUserManager) queryLocalInterface;
        }

        public static boolean setDefaultImpl(IOculusUserManager iOculusUserManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iOculusUserManager == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iOculusUserManager;
                return true;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOculusUserManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            Bundle bundle;
            int i3;
            int i4;
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        A1I();
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        A2J();
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        } else {
                            bundle = null;
                        }
                        A3r(bundle);
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<Bundle> A24 = A24();
                        parcel2.writeNoException();
                        parcel2.writeTypedList(A24);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        i4 = A23();
                        parcel2.writeNoException();
                        i3 = i4;
                        int i5 = i3 == 1 ? 1 : 0;
                        int i6 = i3 == 1 ? 1 : 0;
                        int i7 = i3 == 1 ? 1 : 0;
                        parcel2.writeInt(i5);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        A3K(parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        A3I();
                        parcel2.writeNoException();
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        i4 = A17();
                        parcel2.writeNoException();
                        i3 = i4;
                        int i52 = i3 == 1 ? 1 : 0;
                        int i62 = i3 == 1 ? 1 : 0;
                        int i72 = i3 == 1 ? 1 : 0;
                        parcel2.writeInt(i52);
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        A3h();
                        parcel2.writeNoException();
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        A3E(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case TRANSACTION_refreshUsers /*{ENCODED_INT: 11}*/:
                        parcel.enforceInterface(DESCRIPTOR);
                        A3C();
                        parcel2.writeNoException();
                        return true;
                    case TRANSACTION_getSelf /*{ENCODED_INT: 12}*/:
                        parcel.enforceInterface(DESCRIPTOR);
                        Bundle A1y = A1y();
                        parcel2.writeNoException();
                        if (A1y != null) {
                            parcel2.writeInt(1);
                            A1y.writeToParcel(parcel2, 1);
                            return true;
                        }
                        i3 = 0;
                        int i522 = i3 == 1 ? 1 : 0;
                        int i622 = i3 == 1 ? 1 : 0;
                        int i722 = i3 == 1 ? 1 : 0;
                        parcel2.writeInt(i522);
                        return true;
                    case TRANSACTION_removeUnclaimedUser /*{ENCODED_INT: 13}*/:
                        parcel.enforceInterface(DESCRIPTOR);
                        A3J();
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

    boolean A17() throws RemoteException;

    void A1I() throws RemoteException;

    Bundle A1y() throws RemoteException;

    int A23() throws RemoteException;

    List<Bundle> A24() throws RemoteException;

    void A2J() throws RemoteException;

    void A3C() throws RemoteException;

    void A3E(String str, String str2) throws RemoteException;

    void A3I() throws RemoteException;

    void A3J() throws RemoteException;

    void A3K(int i) throws RemoteException;

    void A3h() throws RemoteException;

    void A3r(Bundle bundle) throws RemoteException;
}
