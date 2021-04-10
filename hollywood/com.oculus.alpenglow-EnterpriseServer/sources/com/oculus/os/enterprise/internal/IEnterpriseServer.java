package com.oculus.os.enterprise.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
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

    public static abstract class Stub extends Binder implements IEnterpriseServer {
        public static final String DESCRIPTOR = "com.oculus.os.enterprise.internal.IEnterpriseServer";
        public static final int TRANSACTION_getAppInstallInfo = 3;
        public static final int TRANSACTION_getAppInstallInfos = 4;
        public static final int TRANSACTION_getConfiguration = 1;
        public static final int TRANSACTION_getConfigurationWithVersion = 2;
        public static final int TRANSACTION_registerCallback = 5;
        public static final int TRANSACTION_registerCallbackForAllApps = 6;
        public static final int TRANSACTION_unregisterCallback = 7;

        public static class Proxy implements IEnterpriseServer {
            public static IEnterpriseServer sDefaultImpl;
            public IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
            public AppInstallInfo getAppInstallInfo(String str, String str2) throws RemoteException {
                AppInstallInfo appInstallInfo;
                IEnterpriseServer iEnterpriseServer;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || (iEnterpriseServer = sDefaultImpl) == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            appInstallInfo = AppInstallInfo.CREATOR.createFromParcel(obtain2);
                        } else {
                            appInstallInfo = null;
                        }
                    } else {
                        appInstallInfo = iEnterpriseServer.getAppInstallInfo(str, str2);
                    }
                    return appInstallInfo;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
            public Map getAppInstallInfos() throws RemoteException {
                Map map;
                IEnterpriseServer iEnterpriseServer;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || (iEnterpriseServer = sDefaultImpl) == null) {
                        obtain2.readException();
                        map = obtain2.readHashMap(getClass().getClassLoader());
                    } else {
                        map = iEnterpriseServer.getAppInstallInfos();
                    }
                    return map;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
            public Configuration getConfiguration() throws RemoteException {
                Configuration configuration;
                IEnterpriseServer iEnterpriseServer;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || (iEnterpriseServer = sDefaultImpl) == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            configuration = Configuration.CREATOR.createFromParcel(obtain2);
                        } else {
                            configuration = null;
                        }
                    } else {
                        configuration = iEnterpriseServer.getConfiguration();
                    }
                    return configuration;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
            public Configuration getConfigurationWithVersion(Version version) throws RemoteException {
                Configuration configuration;
                IEnterpriseServer iEnterpriseServer;
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
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || (iEnterpriseServer = sDefaultImpl) == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            configuration = Configuration.CREATOR.createFromParcel(obtain2);
                        } else {
                            configuration = null;
                        }
                    } else {
                        configuration = iEnterpriseServer.getConfigurationWithVersion(version);
                    }
                    return configuration;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
            public AppInstallInfo registerCallback(String str, IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) throws RemoteException {
                IBinder iBinder;
                IEnterpriseServer iEnterpriseServer;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    AppInstallInfo appInstallInfo = null;
                    if (iAppInstallStatusChangeCallback != null) {
                        iBinder = iAppInstallStatusChangeCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(5, obtain, obtain2, 0) || (iEnterpriseServer = sDefaultImpl) == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            appInstallInfo = AppInstallInfo.CREATOR.createFromParcel(obtain2);
                        }
                    } else {
                        appInstallInfo = iEnterpriseServer.registerCallback(str, iAppInstallStatusChangeCallback);
                    }
                    return appInstallInfo;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
            public Map registerCallbackForAllApps(IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) throws RemoteException {
                IBinder iBinder;
                Map map;
                IEnterpriseServer iEnterpriseServer;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iAppInstallStatusChangeCallback != null) {
                        iBinder = iAppInstallStatusChangeCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(6, obtain, obtain2, 0) || (iEnterpriseServer = sDefaultImpl) == null) {
                        obtain2.readException();
                        map = obtain2.readHashMap(getClass().getClassLoader());
                    } else {
                        map = iEnterpriseServer.registerCallbackForAllApps(iAppInstallStatusChangeCallback);
                    }
                    return map;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.os.enterprise.internal.IEnterpriseServer
            public void unregisterCallback(String str, IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) throws RemoteException {
                IBinder iBinder;
                IEnterpriseServer iEnterpriseServer;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (iAppInstallStatusChangeCallback != null) {
                        iBinder = iAppInstallStatusChangeCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(7, obtain, obtain2, 0) || (iEnterpriseServer = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iEnterpriseServer.unregisterCallback(str, iAppInstallStatusChangeCallback);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IBinder asBinder() {
            return this;
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

        public static IEnterpriseServer getDefaultImpl() {
            return Proxy.sDefaultImpl;
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

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        /* JADX WARNING: Removed duplicated region for block: B:16:0x006d  */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x0098  */
        @Override // android.os.Binder
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r5, android.os.Parcel r6, android.os.Parcel r7, int r8) throws android.os.RemoteException {
            /*
            // Method dump skipped, instructions count: 186
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.os.enterprise.internal.IEnterpriseServer.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }
    }

    AppInstallInfo getAppInstallInfo(String str, String str2) throws RemoteException;

    Map getAppInstallInfos() throws RemoteException;

    Configuration getConfiguration() throws RemoteException;

    Configuration getConfigurationWithVersion(Version version) throws RemoteException;

    AppInstallInfo registerCallback(String str, IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) throws RemoteException;

    Map registerCallbackForAllApps(IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) throws RemoteException;

    void unregisterCallback(String str, IAppInstallStatusChangeCallback iAppInstallStatusChangeCallback) throws RemoteException;
}
