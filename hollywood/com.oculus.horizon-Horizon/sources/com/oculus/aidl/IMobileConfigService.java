package com.oculus.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public interface IMobileConfigService extends IInterface {

    public static class Default implements IMobileConfigService {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.aidl.IMobileConfigService
        public void logExposure(String str, String str2, String str3) throws RemoteException {
        }

        @Override // com.oculus.aidl.IMobileConfigService
        public ParcelFileDescriptor updateMCs(String str, ParcelFileDescriptor parcelFileDescriptor, int i) throws RemoteException {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IMobileConfigService {
        public static final String DESCRIPTOR = "com.oculus.aidl.IMobileConfigService";
        public static final int TRANSACTION_logExposure = 2;
        public static final int TRANSACTION_updateMCs = 1;

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            ParcelFileDescriptor parcelFileDescriptor;
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                String readString = parcel.readString();
                if (parcel.readInt() != 0) {
                    parcelFileDescriptor = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(parcel);
                } else {
                    parcelFileDescriptor = null;
                }
                ParcelFileDescriptor updateMCs = updateMCs(readString, parcelFileDescriptor, parcel.readInt());
                parcel2.writeNoException();
                if (updateMCs != null) {
                    parcel2.writeInt(1);
                    updateMCs.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                logExposure(parcel.readString(), parcel.readString(), parcel.readString());
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        public static class Proxy implements IMobileConfigService {
            public static IMobileConfigService sDefaultImpl;
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

            @Override // com.oculus.aidl.IMobileConfigService
            public void logExposure(String str, String str2, String str3) throws RemoteException {
                IMobileConfigService iMobileConfigService;
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    obtain.writeString(str3);
                    if (!this.mRemote.transact(2, obtain, null, 1) && (iMobileConfigService = sDefaultImpl) != null) {
                        iMobileConfigService.logExposure(str, str2, str3);
                    }
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.IMobileConfigService
            public ParcelFileDescriptor updateMCs(String str, ParcelFileDescriptor parcelFileDescriptor, int i) throws RemoteException {
                ParcelFileDescriptor parcelFileDescriptor2;
                IMobileConfigService iMobileConfigService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (parcelFileDescriptor != null) {
                        obtain.writeInt(1);
                        parcelFileDescriptor.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || (iMobileConfigService = sDefaultImpl) == null) {
                        obtain2.readException();
                        if (obtain2.readInt() != 0) {
                            parcelFileDescriptor2 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(obtain2);
                        } else {
                            parcelFileDescriptor2 = null;
                        }
                    } else {
                        parcelFileDescriptor2 = iMobileConfigService.updateMCs(str, parcelFileDescriptor, i);
                    }
                    return parcelFileDescriptor2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IMobileConfigService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMobileConfigService)) {
                return new Proxy(iBinder);
            }
            return (IMobileConfigService) queryLocalInterface;
        }

        public static boolean setDefaultImpl(IMobileConfigService iMobileConfigService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iMobileConfigService == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iMobileConfigService;
                return true;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMobileConfigService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }

    void logExposure(String str, String str2, String str3) throws RemoteException;

    ParcelFileDescriptor updateMCs(String str, ParcelFileDescriptor parcelFileDescriptor, int i) throws RemoteException;
}
