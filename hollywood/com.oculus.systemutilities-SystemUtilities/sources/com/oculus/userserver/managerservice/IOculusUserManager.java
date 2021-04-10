package com.oculus.userserver.managerservice;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.systemutilities.R;
import java.util.List;

public interface IOculusUserManager extends IInterface {
    boolean canAddMoreUsers() throws RemoteException;

    void createUserAndSwitch() throws RemoteException;

    Bundle getSelf() throws RemoteException;

    int getUserCount() throws RemoteException;

    List<Bundle> getUsers() throws RemoteException;

    void lockScreen() throws RemoteException;

    void refreshUsers() throws RemoteException;

    void registerUserLogin(String str) throws RemoteException;

    void removeSelf() throws RemoteException;

    void removeUser(int i) throws RemoteException;

    void showUserSwitcher() throws RemoteException;

    void updateUser(Bundle bundle) throws RemoteException;

    public static abstract class Stub extends Binder implements IOculusUserManager {
        public static IOculusUserManager asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface("com.oculus.userserver.managerservice.IOculusUserManager");
            if (iin == null || !(iin instanceof IOculusUserManager)) {
                return new Proxy(obj);
            }
            return (IOculusUserManager) iin;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Bundle _arg0;
            int i = 0;
            switch (code) {
                case 1:
                    data.enforceInterface("com.oculus.userserver.managerservice.IOculusUserManager");
                    createUserAndSwitch();
                    reply.writeNoException();
                    return true;
                case 2:
                    data.enforceInterface("com.oculus.userserver.managerservice.IOculusUserManager");
                    lockScreen();
                    reply.writeNoException();
                    return true;
                case 3:
                    data.enforceInterface("com.oculus.userserver.managerservice.IOculusUserManager");
                    if (data.readInt() != 0) {
                        _arg0 = (Bundle) Bundle.CREATOR.createFromParcel(data);
                    } else {
                        _arg0 = null;
                    }
                    updateUser(_arg0);
                    reply.writeNoException();
                    return true;
                case 4:
                    data.enforceInterface("com.oculus.userserver.managerservice.IOculusUserManager");
                    List<Bundle> _result = getUsers();
                    reply.writeNoException();
                    reply.writeTypedList(_result);
                    return true;
                case 5:
                    data.enforceInterface("com.oculus.userserver.managerservice.IOculusUserManager");
                    int _result2 = getUserCount();
                    reply.writeNoException();
                    reply.writeInt(_result2);
                    return true;
                case 6:
                    data.enforceInterface("com.oculus.userserver.managerservice.IOculusUserManager");
                    removeUser(data.readInt());
                    reply.writeNoException();
                    return true;
                case 7:
                    data.enforceInterface("com.oculus.userserver.managerservice.IOculusUserManager");
                    removeSelf();
                    reply.writeNoException();
                    return true;
                case 8:
                    data.enforceInterface("com.oculus.userserver.managerservice.IOculusUserManager");
                    boolean _result3 = canAddMoreUsers();
                    reply.writeNoException();
                    if (_result3) {
                        i = 1;
                    }
                    reply.writeInt(i);
                    return true;
                case 9:
                    data.enforceInterface("com.oculus.userserver.managerservice.IOculusUserManager");
                    showUserSwitcher();
                    reply.writeNoException();
                    return true;
                case R.styleable.GradientColor_android_endX:
                    data.enforceInterface("com.oculus.userserver.managerservice.IOculusUserManager");
                    registerUserLogin(data.readString());
                    reply.writeNoException();
                    return true;
                case 11:
                    data.enforceInterface("com.oculus.userserver.managerservice.IOculusUserManager");
                    refreshUsers();
                    reply.writeNoException();
                    return true;
                case 12:
                    data.enforceInterface("com.oculus.userserver.managerservice.IOculusUserManager");
                    Bundle _result4 = getSelf();
                    reply.writeNoException();
                    if (_result4 != null) {
                        reply.writeInt(1);
                        _result4.writeToParcel(reply, 1);
                        return true;
                    }
                    reply.writeInt(0);
                    return true;
                case 1598968902:
                    reply.writeString("com.oculus.userserver.managerservice.IOculusUserManager");
                    return true;
                default:
                    return super.onTransact(code, data, reply, flags);
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IOculusUserManager {
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public void createUserAndSwitch() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.oculus.userserver.managerservice.IOculusUserManager");
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public void lockScreen() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.oculus.userserver.managerservice.IOculusUserManager");
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public void updateUser(Bundle oculusUser) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.oculus.userserver.managerservice.IOculusUserManager");
                    if (oculusUser != null) {
                        _data.writeInt(1);
                        oculusUser.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public List<Bundle> getUsers() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.oculus.userserver.managerservice.IOculusUserManager");
                    this.mRemote.transact(4, _data, _reply, 0);
                    _reply.readException();
                    return _reply.createTypedArrayList(Bundle.CREATOR);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public int getUserCount() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.oculus.userserver.managerservice.IOculusUserManager");
                    this.mRemote.transact(5, _data, _reply, 0);
                    _reply.readException();
                    return _reply.readInt();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public void removeUser(int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.oculus.userserver.managerservice.IOculusUserManager");
                    _data.writeInt(userId);
                    this.mRemote.transact(6, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public void removeSelf() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.oculus.userserver.managerservice.IOculusUserManager");
                    this.mRemote.transact(7, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public boolean canAddMoreUsers() throws RemoteException {
                boolean _result = false;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.oculus.userserver.managerservice.IOculusUserManager");
                    this.mRemote.transact(8, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public void showUserSwitcher() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.oculus.userserver.managerservice.IOculusUserManager");
                    this.mRemote.transact(9, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public void registerUserLogin(String deviceScopedAccessToken) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.oculus.userserver.managerservice.IOculusUserManager");
                    _data.writeString(deviceScopedAccessToken);
                    this.mRemote.transact(10, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public void refreshUsers() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.oculus.userserver.managerservice.IOculusUserManager");
                    this.mRemote.transact(11, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.userserver.managerservice.IOculusUserManager
            public Bundle getSelf() throws RemoteException {
                Bundle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken("com.oculus.userserver.managerservice.IOculusUserManager");
                    this.mRemote.transact(12, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (Bundle) Bundle.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
