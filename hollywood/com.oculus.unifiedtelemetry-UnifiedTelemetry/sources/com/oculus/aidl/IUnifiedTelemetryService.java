package com.oculus.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;

public interface IUnifiedTelemetryService extends IInterface {

    public static class Default implements IUnifiedTelemetryService {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void endFunnel(String str) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void endFunnelWithId(String str, long j) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void logExposure(String str, String str2, PersistableBundle persistableBundle, PersistableBundle persistableBundle2, boolean z) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void registerFunnel(String str, int i) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void reportEvents(PersistableBundle persistableBundle, boolean z) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void reportFunnelAction(String str, String str2, String str3, PersistableBundle persistableBundle) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void reportFunnelActionWithId(String str, long j, String str2, String str3, PersistableBundle persistableBundle) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public boolean shouldDuplicateLogging(String str) throws RemoteException {
            return false;
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public boolean shouldUseUnifiedTelemetryService(String str) throws RemoteException {
            return false;
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void startFunnel(String str) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void startFunnelWithId(String str, long j) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void startSession(String str, String str2, int i) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void stopSession(String str, int i) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements IUnifiedTelemetryService {
        public static final String DESCRIPTOR = "com.oculus.aidl.IUnifiedTelemetryService";
        public static final int TRANSACTION_endFunnel = 10;
        public static final int TRANSACTION_endFunnelWithId = 11;
        public static final int TRANSACTION_logExposure = 4;
        public static final int TRANSACTION_registerFunnel = 5;
        public static final int TRANSACTION_reportEvents = 3;
        public static final int TRANSACTION_reportFunnelAction = 8;
        public static final int TRANSACTION_reportFunnelActionWithId = 9;
        public static final int TRANSACTION_shouldDuplicateLogging = 1;
        public static final int TRANSACTION_shouldUseUnifiedTelemetryService = 2;
        public static final int TRANSACTION_startFunnel = 6;
        public static final int TRANSACTION_startFunnelWithId = 7;
        public static final int TRANSACTION_startSession = 12;
        public static final int TRANSACTION_stopSession = 13;

        public IBinder asBinder() {
            return this;
        }

        public static class Proxy implements IUnifiedTelemetryService {
            public static IUnifiedTelemetryService sDefaultImpl;
            public IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void endFunnel(String str) throws RemoteException {
                IUnifiedTelemetryService iUnifiedTelemetryService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(10, obtain, obtain2, 0) || (iUnifiedTelemetryService = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iUnifiedTelemetryService.endFunnel(str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void endFunnelWithId(String str, long j) throws RemoteException {
                IUnifiedTelemetryService iUnifiedTelemetryService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    if (this.mRemote.transact(11, obtain, obtain2, 0) || (iUnifiedTelemetryService = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iUnifiedTelemetryService.endFunnelWithId(str, j);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void logExposure(String str, String str2, PersistableBundle persistableBundle, PersistableBundle persistableBundle2, boolean z) throws RemoteException {
                IUnifiedTelemetryService iUnifiedTelemetryService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
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
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || (iUnifiedTelemetryService = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iUnifiedTelemetryService.logExposure(str, str2, persistableBundle, persistableBundle2, z);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void registerFunnel(String str, int i) throws RemoteException {
                IUnifiedTelemetryService iUnifiedTelemetryService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(5, obtain, obtain2, 0) || (iUnifiedTelemetryService = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iUnifiedTelemetryService.registerFunnel(str, i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void reportEvents(PersistableBundle persistableBundle, boolean z) throws RemoteException {
                IUnifiedTelemetryService iUnifiedTelemetryService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
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
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || (iUnifiedTelemetryService = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iUnifiedTelemetryService.reportEvents(persistableBundle, z);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void reportFunnelAction(String str, String str2, String str3, PersistableBundle persistableBundle) throws RemoteException {
                IUnifiedTelemetryService iUnifiedTelemetryService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (persistableBundle != null) {
                        obtain.writeInt(1);
                        persistableBundle.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(8, obtain, obtain2, 0) || (iUnifiedTelemetryService = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iUnifiedTelemetryService.reportFunnelAction(str, str2, str3, persistableBundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void reportFunnelActionWithId(String str, long j, String str2, String str3, PersistableBundle persistableBundle) throws RemoteException {
                IUnifiedTelemetryService iUnifiedTelemetryService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
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
                    if (this.mRemote.transact(9, obtain, obtain2, 0) || (iUnifiedTelemetryService = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iUnifiedTelemetryService.reportFunnelActionWithId(str, j, str2, str3, persistableBundle);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public boolean shouldDuplicateLogging(String str) throws RemoteException {
                IUnifiedTelemetryService iUnifiedTelemetryService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && (iUnifiedTelemetryService = sDefaultImpl) != null) {
                        return iUnifiedTelemetryService.shouldDuplicateLogging(str);
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
                IUnifiedTelemetryService iUnifiedTelemetryService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && (iUnifiedTelemetryService = sDefaultImpl) != null) {
                        return iUnifiedTelemetryService.shouldUseUnifiedTelemetryService(str);
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
            public void startFunnel(String str) throws RemoteException {
                IUnifiedTelemetryService iUnifiedTelemetryService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(6, obtain, obtain2, 0) || (iUnifiedTelemetryService = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iUnifiedTelemetryService.startFunnel(str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void startFunnelWithId(String str, long j) throws RemoteException {
                IUnifiedTelemetryService iUnifiedTelemetryService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeLong(j);
                    if (this.mRemote.transact(7, obtain, obtain2, 0) || (iUnifiedTelemetryService = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iUnifiedTelemetryService.startFunnelWithId(str, j);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void startSession(String str, String str2, int i) throws RemoteException {
                IUnifiedTelemetryService iUnifiedTelemetryService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(12, obtain, obtain2, 0) || (iUnifiedTelemetryService = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iUnifiedTelemetryService.startSession(str, str2, i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void stopSession(String str, int i) throws RemoteException {
                IUnifiedTelemetryService iUnifiedTelemetryService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(13, obtain, obtain2, 0) || (iUnifiedTelemetryService = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iUnifiedTelemetryService.stopSession(str, i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.mRemote;
            }
        }

        public static IUnifiedTelemetryService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IUnifiedTelemetryService)) {
                return new Proxy(iBinder);
            }
            return (IUnifiedTelemetryService) queryLocalInterface;
        }

        public static boolean setDefaultImpl(IUnifiedTelemetryService iUnifiedTelemetryService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iUnifiedTelemetryService == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iUnifiedTelemetryService;
                return true;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            boolean z;
            PersistableBundle persistableBundle;
            if (i != 1598968902) {
                boolean z2 = false;
                PersistableBundle persistableBundle2 = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        z = shouldDuplicateLogging(parcel.readString());
                        parcel2.writeNoException();
                        int i3 = z ? 1 : 0;
                        int i4 = z ? 1 : 0;
                        int i5 = z ? 1 : 0;
                        parcel2.writeInt(i3);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        z = shouldUseUnifiedTelemetryService(parcel.readString());
                        parcel2.writeNoException();
                        int i32 = z ? 1 : 0;
                        int i42 = z ? 1 : 0;
                        int i52 = z ? 1 : 0;
                        parcel2.writeInt(i32);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            persistableBundle2 = (PersistableBundle) PersistableBundle.CREATOR.createFromParcel(parcel);
                        }
                        if (parcel.readInt() != 0) {
                            z2 = true;
                        }
                        reportEvents(persistableBundle2, z2);
                        parcel2.writeNoException();
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString = parcel.readString();
                        String readString2 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            persistableBundle = (PersistableBundle) PersistableBundle.CREATOR.createFromParcel(parcel);
                        } else {
                            persistableBundle = null;
                        }
                        if (parcel.readInt() != 0) {
                            persistableBundle2 = (PersistableBundle) PersistableBundle.CREATOR.createFromParcel(parcel);
                        }
                        boolean z3 = false;
                        if (parcel.readInt() != 0) {
                            z3 = true;
                        }
                        logExposure(readString, readString2, persistableBundle, persistableBundle2, z3);
                        parcel2.writeNoException();
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        registerFunnel(parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        startFunnel(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        startFunnelWithId(parcel.readString(), parcel.readLong());
                        parcel2.writeNoException();
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString3 = parcel.readString();
                        String readString4 = parcel.readString();
                        String readString5 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            persistableBundle2 = (PersistableBundle) PersistableBundle.CREATOR.createFromParcel(parcel);
                        }
                        reportFunnelAction(readString3, readString4, readString5, persistableBundle2);
                        parcel2.writeNoException();
                        return true;
                    case 9:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString6 = parcel.readString();
                        long readLong = parcel.readLong();
                        String readString7 = parcel.readString();
                        String readString8 = parcel.readString();
                        if (parcel.readInt() != 0) {
                            persistableBundle2 = (PersistableBundle) PersistableBundle.CREATOR.createFromParcel(parcel);
                        }
                        reportFunnelActionWithId(readString6, readLong, readString7, readString8, persistableBundle2);
                        parcel2.writeNoException();
                        return true;
                    case 10:
                        parcel.enforceInterface(DESCRIPTOR);
                        endFunnel(parcel.readString());
                        parcel2.writeNoException();
                        return true;
                    case 11:
                        parcel.enforceInterface(DESCRIPTOR);
                        endFunnelWithId(parcel.readString(), parcel.readLong());
                        parcel2.writeNoException();
                        return true;
                    case TRANSACTION_startSession /*{ENCODED_INT: 12}*/:
                        parcel.enforceInterface(DESCRIPTOR);
                        startSession(parcel.readString(), parcel.readString(), parcel.readInt());
                        parcel2.writeNoException();
                        return true;
                    case TRANSACTION_stopSession /*{ENCODED_INT: 13}*/:
                        parcel.enforceInterface(DESCRIPTOR);
                        stopSession(parcel.readString(), parcel.readInt());
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

        public static IUnifiedTelemetryService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }

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
}
