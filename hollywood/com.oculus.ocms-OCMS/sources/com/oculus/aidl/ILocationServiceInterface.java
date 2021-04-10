package com.oculus.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.aidl.ILocationCallback;

public interface ILocationServiceInterface extends IInterface {

    public static class Default implements ILocationServiceInterface {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.aidl.ILocationServiceInterface
        public void getLocation(ILocationCallback iLocationCallback) throws RemoteException {
        }

        @Override // com.oculus.aidl.ILocationServiceInterface
        public void getLocationByType(ILocationCallback iLocationCallback, int i) throws RemoteException {
        }

        @Override // com.oculus.aidl.ILocationServiceInterface
        public void getLocationUsingIPOnly(ILocationCallback iLocationCallback) throws RemoteException {
        }

        @Override // com.oculus.aidl.ILocationServiceInterface
        public void getLocationWithTimezoneUsingIPOnly(ILocationCallback iLocationCallback) throws RemoteException {
        }
    }

    void getLocation(ILocationCallback iLocationCallback) throws RemoteException;

    void getLocationByType(ILocationCallback iLocationCallback, int i) throws RemoteException;

    void getLocationUsingIPOnly(ILocationCallback iLocationCallback) throws RemoteException;

    void getLocationWithTimezoneUsingIPOnly(ILocationCallback iLocationCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements ILocationServiceInterface {
        private static final String DESCRIPTOR = "com.oculus.aidl.ILocationServiceInterface";
        static final int TRANSACTION_getLocation = 1;
        static final int TRANSACTION_getLocationByType = 4;
        static final int TRANSACTION_getLocationUsingIPOnly = 2;
        static final int TRANSACTION_getLocationWithTimezoneUsingIPOnly = 3;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ILocationServiceInterface asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ILocationServiceInterface)) {
                return new Proxy(iBinder);
            }
            return (ILocationServiceInterface) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                getLocation(ILocationCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                getLocationUsingIPOnly(ILocationCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                getLocationWithTimezoneUsingIPOnly(ILocationCallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            } else if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                getLocationByType(ILocationCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements ILocationServiceInterface {
            public static ILocationServiceInterface sDefaultImpl;
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

            @Override // com.oculus.aidl.ILocationServiceInterface
            public void getLocation(ILocationCallback iLocationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iLocationCallback != null ? iLocationCallback.asBinder() : null);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getLocation(iLocationCallback);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.ILocationServiceInterface
            public void getLocationUsingIPOnly(ILocationCallback iLocationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iLocationCallback != null ? iLocationCallback.asBinder() : null);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getLocationUsingIPOnly(iLocationCallback);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.ILocationServiceInterface
            public void getLocationWithTimezoneUsingIPOnly(ILocationCallback iLocationCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iLocationCallback != null ? iLocationCallback.asBinder() : null);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getLocationWithTimezoneUsingIPOnly(iLocationCallback);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.aidl.ILocationServiceInterface
            public void getLocationByType(ILocationCallback iLocationCallback, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iLocationCallback != null ? iLocationCallback.asBinder() : null);
                    obtain.writeInt(i);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getLocationByType(iLocationCallback, i);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ILocationServiceInterface iLocationServiceInterface) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iLocationServiceInterface == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iLocationServiceInterface;
                return true;
            }
        }

        public static ILocationServiceInterface getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
