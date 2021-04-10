package com.oculus.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public interface IBugReporterService extends IInterface {

    public static class Default implements IBugReporterService {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.aidl.IBugReporterService
        public String getReportId() throws RemoteException {
            return null;
        }

        @Override // com.oculus.aidl.IBugReporterService
        public ParcelFileDescriptor openScreenshotFile() throws RemoteException {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IBugReporterService {
        public static final String DESCRIPTOR = "com.oculus.aidl.IBugReporterService";
        public static final int TRANSACTION_getReportId = 2;
        public static final int TRANSACTION_openScreenshotFile = 1;

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                ParcelFileDescriptor openScreenshotFile = openScreenshotFile();
                parcel2.writeNoException();
                if (openScreenshotFile != null) {
                    parcel2.writeInt(1);
                    openScreenshotFile.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                String reportId = getReportId();
                parcel2.writeNoException();
                parcel2.writeString(reportId);
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        public static class Proxy implements IBugReporterService {
            public static IBugReporterService sDefaultImpl;
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

            @Override // com.oculus.aidl.IBugReporterService
            public String getReportId() throws RemoteException {
                String str;
                IBugReporterService iBugReporterService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || (iBugReporterService = sDefaultImpl) == null) {
                        obtain2.readException();
                        str = obtain2.readString();
                    } else {
                        str = iBugReporterService.getReportId();
                    }
                    return str;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IBugReporterService
            public ParcelFileDescriptor openScreenshotFile() throws RemoteException {
                ParcelFileDescriptor parcelFileDescriptor;
                IBugReporterService iBugReporterService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || (iBugReporterService = sDefaultImpl) == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            parcelFileDescriptor = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(obtain2);
                        } else {
                            parcelFileDescriptor = null;
                        }
                    } else {
                        parcelFileDescriptor = iBugReporterService.openScreenshotFile();
                    }
                    return parcelFileDescriptor;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IBugReporterService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IBugReporterService)) {
                return new Proxy(iBinder);
            }
            return (IBugReporterService) queryLocalInterface;
        }

        public static boolean setDefaultImpl(IBugReporterService iBugReporterService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iBugReporterService == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iBugReporterService;
                return true;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IBugReporterService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }

    String getReportId() throws RemoteException;

    ParcelFileDescriptor openScreenshotFile() throws RemoteException;
}
