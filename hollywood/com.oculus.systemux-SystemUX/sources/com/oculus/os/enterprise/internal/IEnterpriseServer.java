package com.oculus.os.enterprise.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.os.enterprise.internal.IAppInstallStatusChangeCallback;
import java.util.HashMap;
import java.util.Map;

public interface IEnterpriseServer extends IInterface {

    public static class Default implements IEnterpriseServer {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
        public AppInstallInfo getAppInstallInfo(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
        public Map getAppInstallInfos() throws RemoteException {
            return null;
        }

        @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
        public Configuration getConfiguration() throws RemoteException {
            return null;
        }

        @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
        public Configuration getConfigurationWithVersion(Version version) throws RemoteException {
            return null;
        }

        @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
        public AppInstallInfo registerCallback(String str, IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) throws RemoteException {
            return null;
        }

        @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
        public Map registerCallbackForAllApps(IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) throws RemoteException {
            return null;
        }

        @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
        public void unregisterCallback(String str, IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) throws RemoteException {
        }
    }

    AppInstallInfo getAppInstallInfo(String str, String str2) throws RemoteException;

    Map getAppInstallInfos() throws RemoteException;

    Configuration getConfiguration() throws RemoteException;

    Configuration getConfigurationWithVersion(Version version) throws RemoteException;

    AppInstallInfo registerCallback(String str, IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) throws RemoteException;

    Map registerCallbackForAllApps(IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) throws RemoteException;

    void unregisterCallback(String str, IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IEnterpriseServer {
        private static final String DESCRIPTOR = "com.oculus.os.enterprise.internal.IEnterpriseServer";
        static final int TRANSACTION_getAppInstallInfo = 3;
        static final int TRANSACTION_getAppInstallInfos = 4;
        static final int TRANSACTION_getConfiguration = 1;
        static final int TRANSACTION_getConfigurationWithVersion = 2;
        static final int TRANSACTION_registerCallback = 5;
        static final int TRANSACTION_registerCallbackForAllApps = 6;
        static final int TRANSACTION_unregisterCallback = 7;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IEnterpriseServer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IEnterpriseServer)) {
                return new Proxy(iBinder);
            }
            return (IEnterpriseServer) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        Configuration configuration = getConfiguration();
                        parcel2.writeNoException();
                        if (configuration != null) {
                            parcel2.writeInt(1);
                            configuration.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        Configuration configurationWithVersion = getConfigurationWithVersion(parcel.readInt() != 0 ? Version.CREATOR.createFromParcel(parcel) : null);
                        parcel2.writeNoException();
                        if (configurationWithVersion != null) {
                            parcel2.writeInt(1);
                            configurationWithVersion.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        AppInstallInfo appInstallInfo = getAppInstallInfo(parcel.readString(), parcel.readString());
                        parcel2.writeNoException();
                        if (appInstallInfo != null) {
                            parcel2.writeInt(1);
                            appInstallInfo.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        Map appInstallInfos = getAppInstallInfos();
                        parcel2.writeNoException();
                        parcel2.writeMap(appInstallInfos);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        AppInstallInfo registerCallback = registerCallback(parcel.readString(), IAppInstallStatusChangeCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        if (registerCallback != null) {
                            parcel2.writeInt(1);
                            registerCallback.writeToParcel(parcel2, 1);
                        } else {
                            parcel2.writeInt(0);
                        }
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        Map registerCallbackForAllApps = registerCallbackForAllApps(IAppInstallStatusChangeCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeMap(registerCallbackForAllApps);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        unregisterCallback(parcel.readString(), IAppInstallStatusChangeCallback.Stub.asInterface(parcel.readStrongBinder()));
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
        public static class Proxy implements IEnterpriseServer {
            public static IEnterpriseServer sDefaultImpl;
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

            @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
            public Configuration getConfiguration() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConfiguration();
                    }
                    obtain2.readException();
                    Configuration createFromParcel = obtain2.readInt() != 0 ? Configuration.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
            public Configuration getConfigurationWithVersion(Version version) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (version != null) {
                        obtain.writeInt(1);
                        version.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConfigurationWithVersion(version);
                    }
                    obtain2.readException();
                    Configuration createFromParcel = obtain2.readInt() != 0 ? Configuration.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
            public AppInstallInfo getAppInstallInfo(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppInstallInfo(str, str2);
                    }
                    obtain2.readException();
                    AppInstallInfo createFromParcel = obtain2.readInt() != 0 ? AppInstallInfo.CREATOR.createFromParcel(obtain2) : null;
                    obtain2.recycle();
                    obtain.recycle();
                    return createFromParcel;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
            public Map getAppInstallInfos() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAppInstallInfos();
                    }
                    obtain2.readException();
                    HashMap readHashMap = obtain2.readHashMap(getClass().getClassLoader());
                    obtain2.recycle();
                    obtain.recycle();
                    return readHashMap;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
            public AppInstallInfo registerCallback(String str, IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    AppInstallInfo appInstallInfo = null;
                    obtain.writeStrongBinder(iAppInstallStatusChangeCallback != null ? iAppInstallStatusChangeCallback.asBinder() : null);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCallback(str, iAppInstallStatusChangeCallback);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        appInstallInfo = AppInstallInfo.CREATOR.createFromParcel(obtain2);
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return appInstallInfo;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
            public Map registerCallbackForAllApps(IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iAppInstallStatusChangeCallback != null ? iAppInstallStatusChangeCallback.asBinder() : null);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerCallbackForAllApps(iAppInstallStatusChangeCallback);
                    }
                    obtain2.readException();
                    HashMap readHashMap = obtain2.readHashMap(getClass().getClassLoader());
                    obtain2.recycle();
                    obtain.recycle();
                    return readHashMap;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
            public void unregisterCallback(String str, IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iAppInstallStatusChangeCallback != null ? iAppInstallStatusChangeCallback.asBinder() : null);
                    if (this.mRemote.transact(7, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unregisterCallback(str, iAppInstallStatusChangeCallback);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IEnterpriseServer iEnterpriseServer) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iEnterpriseServer == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iEnterpriseServer;
                return true;
            }
        }

        public static IEnterpriseServer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
