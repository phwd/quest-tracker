package com.oculus.logging.sdk_logging;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface SDKLoggingInterface extends IInterface {

    public static class Default implements SDKLoggingInterface {
        public final IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.logging.sdk_logging.SDKLoggingInterface
        public final void log(String str) throws RemoteException {
        }

        @Override // com.oculus.logging.sdk_logging.SDKLoggingInterface
        public final void reportEvent(String str, String str2) throws RemoteException {
        }

        @Override // com.oculus.logging.sdk_logging.SDKLoggingInterface
        public final void reportLowLatencyEvent(String str, String str2) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements SDKLoggingInterface {
        public static final String DESCRIPTOR = "com.oculus.logging.sdk_logging.SDKLoggingInterface";
        public static final int TRANSACTION_log = 1;
        public static final int TRANSACTION_reportEvent = 2;
        public static final int TRANSACTION_reportLowLatencyEvent = 3;

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                log(parcel.readString());
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                reportEvent(parcel.readString(), parcel.readString());
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                reportLowLatencyEvent(parcel.readString(), parcel.readString());
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            parcel2.writeNoException();
            return true;
        }

        public static class Proxy implements SDKLoggingInterface {
            public static SDKLoggingInterface sDefaultImpl;
            public IBinder mRemote;

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public final IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.oculus.logging.sdk_logging.SDKLoggingInterface
            public final void log(String str) throws RemoteException {
                SDKLoggingInterface sDKLoggingInterface;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || (sDKLoggingInterface = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        sDKLoggingInterface.log(str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.logging.sdk_logging.SDKLoggingInterface
            public final void reportEvent(String str, String str2) throws RemoteException {
                SDKLoggingInterface sDKLoggingInterface;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || (sDKLoggingInterface = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        sDKLoggingInterface.reportEvent(str, str2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.logging.sdk_logging.SDKLoggingInterface
            public final void reportLowLatencyEvent(String str, String str2) throws RemoteException {
                SDKLoggingInterface sDKLoggingInterface;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || (sDKLoggingInterface = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        sDKLoggingInterface.reportLowLatencyEvent(str, str2);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static SDKLoggingInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof SDKLoggingInterface)) {
                return new Proxy(iBinder);
            }
            return (SDKLoggingInterface) queryLocalInterface;
        }

        public static boolean setDefaultImpl(SDKLoggingInterface sDKLoggingInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (sDKLoggingInterface == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = sDKLoggingInterface;
                return true;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static SDKLoggingInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }

    void log(String str) throws RemoteException;

    void reportEvent(String str, String str2) throws RemoteException;

    void reportLowLatencyEvent(String str, String str2) throws RemoteException;
}
