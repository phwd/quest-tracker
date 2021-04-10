package com.oculus.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;

public interface IUnifiedTelemetryService extends IInterface {
    void endFunnel(String str) throws RemoteException;

    void endFunnelWithId(String str, long j) throws RemoteException;

    void logExposure(String str, String str2, PersistableBundle persistableBundle, PersistableBundle persistableBundle2, boolean z) throws RemoteException;

    void registerFunnel(String str, int i) throws RemoteException;

    void reportEvents(PersistableBundle persistableBundle, boolean z) throws RemoteException;

    void reportFunnelAction(String str, String str2, String str3, PersistableBundle persistableBundle) throws RemoteException;

    void reportFunnelActionWithId(String str, long j, String str2, String str3, PersistableBundle persistableBundle) throws RemoteException;

    boolean shouldDuplicateLogging(String str) throws RemoteException;

    boolean shouldUseUnifiedTelemetryService(String str) throws RemoteException;

    void startFunnel(String str) throws RemoteException;

    void startFunnelWithId(String str, long j) throws RemoteException;

    void startSession(String str, String str2, int i) throws RemoteException;

    void stopSession(String str, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IUnifiedTelemetryService {
        public static IUnifiedTelemetryService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.oculus.aidl.IUnifiedTelemetryService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IUnifiedTelemetryService)) {
                return new Proxy(iBinder);
            }
            return (IUnifiedTelemetryService) queryLocalInterface;
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IUnifiedTelemetryService {
            public static IUnifiedTelemetryService sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public boolean shouldDuplicateLogging(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.IUnifiedTelemetryService");
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldDuplicateLogging(str);
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

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public boolean shouldUseUnifiedTelemetryService(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.IUnifiedTelemetryService");
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldUseUnifiedTelemetryService(str);
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

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void reportEvents(PersistableBundle persistableBundle, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.IUnifiedTelemetryService");
                    int i = 1;
                    if (persistableBundle != null) {
                        obtain.writeInt(1);
                        persistableBundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().reportEvents(persistableBundle, z);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void logExposure(String str, String str2, PersistableBundle persistableBundle, PersistableBundle persistableBundle2, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.IUnifiedTelemetryService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    int i = 1;
                    if (persistableBundle != null) {
                        obtain.writeInt(1);
                        persistableBundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (persistableBundle2 != null) {
                        obtain.writeInt(1);
                        persistableBundle2.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().logExposure(str, str2, persistableBundle, persistableBundle2, z);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void registerFunnel(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.IUnifiedTelemetryService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().registerFunnel(str, i);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void startFunnel(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.IUnifiedTelemetryService");
                    obtain.writeString(str);
                    if (this.mRemote.transact(6, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().startFunnel(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void startFunnelWithId(String str, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.IUnifiedTelemetryService");
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    if (this.mRemote.transact(7, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().startFunnelWithId(str, j);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void reportFunnelAction(String str, String str2, String str3, PersistableBundle persistableBundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.IUnifiedTelemetryService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (persistableBundle != null) {
                        obtain.writeInt(1);
                        persistableBundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(8, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().reportFunnelAction(str, str2, str3, persistableBundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void reportFunnelActionWithId(String str, long j, String str2, String str3, PersistableBundle persistableBundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.IUnifiedTelemetryService");
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (persistableBundle != null) {
                        obtain.writeInt(1);
                        persistableBundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(9, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().reportFunnelActionWithId(str, j, str2, str3, persistableBundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void endFunnel(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.IUnifiedTelemetryService");
                    obtain.writeString(str);
                    if (this.mRemote.transact(10, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().endFunnel(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void endFunnelWithId(String str, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.IUnifiedTelemetryService");
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    if (this.mRemote.transact(11, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().endFunnelWithId(str, j);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void startSession(String str, String str2, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.IUnifiedTelemetryService");
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(12, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().startSession(str, str2, i);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void stopSession(String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.aidl.IUnifiedTelemetryService");
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(13, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().stopSession(str, i);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IUnifiedTelemetryService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
