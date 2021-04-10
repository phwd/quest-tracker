package com.oculus.userserver.managerservice;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface IOculusUserManager extends IInterface {

    public static class Default implements IOculusUserManager {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public boolean canAddMoreUsers() throws RemoteException {
            return false;
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public void createUserAndSwitch() throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public Bundle getSelf() throws RemoteException {
            return null;
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public int getUserCount() throws RemoteException {
            return 0;
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public List<Bundle> getUsers() throws RemoteException {
            return null;
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public void lockScreen() throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public void refreshUsers() throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public void registerUserLogin(String str, String str2) throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public void removeSelf() throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public void removeUnclaimedUser() throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public void removeUser(int i) throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public void showUserSwitcher() throws RemoteException {
        }

        @Override // com.oculus.userserver.managerservice.IOculusUserManager
        public void updateUser(Bundle bundle) throws RemoteException {
        }
    }

    boolean canAddMoreUsers() throws RemoteException;

    void createUserAndSwitch() throws RemoteException;

    Bundle getSelf() throws RemoteException;

    int getUserCount() throws RemoteException;

    List<Bundle> getUsers() throws RemoteException;

    void lockScreen() throws RemoteException;

    void refreshUsers() throws RemoteException;

    void registerUserLogin(String str, String str2) throws RemoteException;

    void removeSelf() throws RemoteException;

    void removeUnclaimedUser() throws RemoteException;

    void removeUser(int i) throws RemoteException;

    void showUserSwitcher() throws RemoteException;

    void updateUser(Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements IOculusUserManager {
        private static final String DESCRIPTOR = "com.oculus.userserver.managerservice.IOculusUserManager";
        static final int TRANSACTION_canAddMoreUsers = 8;
        static final int TRANSACTION_createUserAndSwitch = 1;
        static final int TRANSACTION_getSelf = 12;
        static final int TRANSACTION_getUserCount = 5;
        static final int TRANSACTION_getUsers = 4;
        static final int TRANSACTION_lockScreen = 2;
        static final int TRANSACTION_refreshUsers = 11;
        static final int TRANSACTION_registerUserLogin = 10;
        static final int TRANSACTION_removeSelf = 7;
        static final int TRANSACTION_removeUnclaimedUser = 13;
        static final int TRANSACTION_removeUser = 6;
        static final int TRANSACTION_showUserSwitcher = 9;
        static final int TRANSACTION_updateUser = 3;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
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

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        createUserAndSwitch();
                        parcel2.writeNoException();
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        lockScreen();
                        parcel2.writeNoException();
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        updateUser(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        List<Bundle> users = getUsers();
                        parcel2.writeNoException();
                        parcel2.writeTypedList(users);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        int userCount = getUserCount();
                        parcel2.writeNoException();
                        parcel2.writeInt(userCount);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        removeUser(parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        removeSelf();
                        parcel2.writeNoException();
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean canAddMoreUsers = canAddMoreUsers();
                        parcel2.writeNoException();
                        parcel2.writeInt(canAddMoreUsers ? 1 : 0);
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        showUserSwitcher();
                        parcel2.writeNoException();
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        registerUserLogin(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 11:
                        parcel.enforceInterface(DESCRIPTOR);
                        refreshUsers();
                        parcel2.writeNoException();
                        return true;
                    case 12:
                        parcel.enforceInterface(DESCRIPTOR);
                        Bundle self = getSelf();
                        parcel2.writeNoException();
                        if (self != null) {
                            parcel2.writeInt(1);
                            self.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 13:
                        parcel.enforceInterface(DESCRIPTOR);
                        removeUnclaimedUser();
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

        /* access modifiers changed from: private */
        public static class Proxy implements IOculusUserManager {
            public static IOculusUserManager sDefaultImpl;
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

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public void createUserAndSwitch() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().createUserAndSwitch();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public void lockScreen() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().lockScreen();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public void updateUser(Bundle bundle) throws RemoteException {
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
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().updateUser(bundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public List<Bundle> getUsers() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUsers();
                    }
                    obtain2.readException();
                    ArrayList createTypedArrayList = obtain2.createTypedArrayList(Bundle.CREATOR);
                    obtain2.recycle();
                    obtain.recycle();
                    return createTypedArrayList;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public int getUserCount() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUserCount();
                    }
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.recycle();
                    obtain.recycle();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public void removeUser(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(6, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().removeUser(i);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public void removeSelf() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().removeSelf();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public boolean canAddMoreUsers() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canAddMoreUsers();
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
            public void showUserSwitcher() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(9, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().showUserSwitcher();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public void registerUserLogin(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(10, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().registerUserLogin(str, str2);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public void refreshUsers() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(11, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().refreshUsers();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public Bundle getSelf() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSelf();
                    }
                    obtain2.readException();
                    Bundle bundle = obtain2.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return bundle;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public void removeUnclaimedUser() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(13, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().removeUnclaimedUser();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
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

        public static IOculusUserManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
