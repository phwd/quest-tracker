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
        public final boolean A1d() throws RemoteException {
            return false;
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A27() throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final Bundle A4K() throws RemoteException {
            return null;
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final int A4d() throws RemoteException {
            return 0;
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final List<Bundle> A4e() throws RemoteException {
            return null;
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A5G() throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A82() throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A85(String str, String str2) throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A8C() throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A8D() throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A8E(int i) throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A93() throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public final void A9j(Bundle bundle) throws RemoteException {
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
            public final boolean A1d() throws RemoteException {
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && (iOculusUserManager = sDefaultImpl) != null) {
                        return iOculusUserManager.A1d();
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
            public final void A27() throws RemoteException {
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iOculusUserManager.A27();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final Bundle A4K() throws RemoteException {
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
                        bundle = iOculusUserManager.A4K();
                    }
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final int A4d() throws RemoteException {
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
                        i = iOculusUserManager.A4d();
                    }
                    return i;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final List<Bundle> A4e() throws RemoteException {
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
                        list = iOculusUserManager.A4e();
                    }
                    return list;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final void A5G() throws RemoteException {
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iOculusUserManager.A5G();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final void A82() throws RemoteException {
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(11, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iOculusUserManager.A82();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final void A85(String str, String str2) throws RemoteException {
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
                        iOculusUserManager.A85(str, str2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final void A8C() throws RemoteException {
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iOculusUserManager.A8C();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final void A8D() throws RemoteException {
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(13, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iOculusUserManager.A8D();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final void A8E(int i) throws RemoteException {
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(6, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iOculusUserManager.A8E(i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final void A93() throws RemoteException {
                IOculusUserManager iOculusUserManager;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(9, obtain, obtain2, 0) || (iOculusUserManager = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iOculusUserManager.A93();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public final void A9j(Bundle bundle) throws RemoteException {
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
                        iOculusUserManager.A9j(bundle);
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
                        A27();
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        A5G();
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                        } else {
                            bundle = null;
                        }
                        A9j(bundle);
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<Bundle> A4e = A4e();
                        parcel2.writeNoException();
                        parcel2.writeTypedList(A4e);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        i4 = A4d();
                        parcel2.writeNoException();
                        i3 = i4;
                        int i5 = i3 == 1 ? 1 : 0;
                        int i6 = i3 == 1 ? 1 : 0;
                        int i7 = i3 == 1 ? 1 : 0;
                        parcel2.writeInt(i5);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        A8E(parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        A8C();
                        parcel2.writeNoException();
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        i4 = A1d();
                        parcel2.writeNoException();
                        i3 = i4;
                        int i52 = i3 == 1 ? 1 : 0;
                        int i62 = i3 == 1 ? 1 : 0;
                        int i72 = i3 == 1 ? 1 : 0;
                        parcel2.writeInt(i52);
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        A93();
                        parcel2.writeNoException();
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        A85(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 11:
                        parcel.enforceInterface(DESCRIPTOR);
                        A82();
                        parcel2.writeNoException();
                        return true;
                    case 12:
                        parcel.enforceInterface(DESCRIPTOR);
                        Bundle A4K = A4K();
                        parcel2.writeNoException();
                        if (A4K != null) {
                            parcel2.writeInt(1);
                            A4K.writeToParcel(parcel2, 1);
                            return true;
                        }
                        i3 = 0;
                        int i522 = i3 == 1 ? 1 : 0;
                        int i622 = i3 == 1 ? 1 : 0;
                        int i722 = i3 == 1 ? 1 : 0;
                        parcel2.writeInt(i522);
                        return true;
                    case 13:
                        parcel.enforceInterface(DESCRIPTOR);
                        A8D();
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

    boolean A1d() throws RemoteException;

    void A27() throws RemoteException;

    Bundle A4K() throws RemoteException;

    int A4d() throws RemoteException;

    List<Bundle> A4e() throws RemoteException;

    void A5G() throws RemoteException;

    void A82() throws RemoteException;

    void A85(String str, String str2) throws RemoteException;

    void A8C() throws RemoteException;

    void A8D() throws RemoteException;

    void A8E(int i) throws RemoteException;

    void A93() throws RemoteException;

    void A9j(Bundle bundle) throws RemoteException;
}
