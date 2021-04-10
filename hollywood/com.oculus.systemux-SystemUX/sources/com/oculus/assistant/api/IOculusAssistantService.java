package com.oculus.assistant.api;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.assistant.api.IOculusAssistantSubscriber;

public interface IOculusAssistantService extends IInterface {

    public static class Default implements IOculusAssistantService {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.assistant.api.IOculusAssistantService
        public void post(String str, Bundle bundle) throws RemoteException {
        }

        @Override // com.oculus.assistant.api.IOculusAssistantService
        public String subscribe(String str, IOculusAssistantSubscriber iOculusAssistantSubscriber) throws RemoteException {
            return null;
        }

        @Override // com.oculus.assistant.api.IOculusAssistantService
        public void unsubscribe(String str, String str2) throws RemoteException {
        }
    }

    void post(String str, Bundle bundle) throws RemoteException;

    String subscribe(String str, IOculusAssistantSubscriber iOculusAssistantSubscriber) throws RemoteException;

    void unsubscribe(String str, String str2) throws RemoteException;

    public static abstract class Stub extends Binder implements IOculusAssistantService {
        private static final String DESCRIPTOR = "com.oculus.assistant.api.IOculusAssistantService";
        static final int TRANSACTION_post = 3;
        static final int TRANSACTION_subscribe = 1;
        static final int TRANSACTION_unsubscribe = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOculusAssistantService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOculusAssistantService)) {
                return new Proxy(iBinder);
            }
            return (IOculusAssistantService) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                String subscribe = subscribe(parcel.readString(), IOculusAssistantSubscriber.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeString(subscribe);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                unsubscribe(parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                post(parcel.readString(), parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null);
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
        public static class Proxy implements IOculusAssistantService {
            public static IOculusAssistantService sDefaultImpl;
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

            @Override // com.oculus.assistant.api.IOculusAssistantService
            public String subscribe(String str, IOculusAssistantSubscriber iOculusAssistantSubscriber) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iOculusAssistantSubscriber != null ? iOculusAssistantSubscriber.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().subscribe(str, iOculusAssistantSubscriber);
                    }
                    obtain2.readException();
                    String readString = obtain2.readString();
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.api.IOculusAssistantService
            public void unsubscribe(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unsubscribe(str, str2);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.api.IOculusAssistantService
            public void post(String str, Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
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
                    Stub.getDefaultImpl().post(str, bundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOculusAssistantService iOculusAssistantService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iOculusAssistantService == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iOculusAssistantService;
                return true;
            }
        }

        public static IOculusAssistantService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
