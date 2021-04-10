package com.oculus.assistant.service.api.attention;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener;

public interface IOculusAssistantAttentionService extends IInterface {

    public static class Default implements IOculusAssistantAttentionService {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionService
        public String registerAttentionListener(IOculusAssistantAttentionListener iOculusAssistantAttentionListener) throws RemoteException {
            return null;
        }

        @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionService
        public void removeAttentionListener(String str) throws RemoteException {
        }
    }

    String registerAttentionListener(IOculusAssistantAttentionListener iOculusAssistantAttentionListener) throws RemoteException;

    void removeAttentionListener(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IOculusAssistantAttentionService {
        private static final String DESCRIPTOR = "com.oculus.assistant.service.api.attention.IOculusAssistantAttentionService";
        static final int TRANSACTION_registerAttentionListener = 1;
        static final int TRANSACTION_removeAttentionListener = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOculusAssistantAttentionService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOculusAssistantAttentionService)) {
                return new Proxy(iBinder);
            }
            return (IOculusAssistantAttentionService) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                String registerAttentionListener = registerAttentionListener(IOculusAssistantAttentionListener.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                parcel2.writeString(registerAttentionListener);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                removeAttentionListener(parcel.readString());
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
        public static class Proxy implements IOculusAssistantAttentionService {
            public static IOculusAssistantAttentionService sDefaultImpl;
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

            @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionService
            public String registerAttentionListener(IOculusAssistantAttentionListener iOculusAssistantAttentionListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iOculusAssistantAttentionListener != null ? iOculusAssistantAttentionListener.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerAttentionListener(iOculusAssistantAttentionListener);
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

            @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionService
            public void removeAttentionListener(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().removeAttentionListener(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOculusAssistantAttentionService iOculusAssistantAttentionService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iOculusAssistantAttentionService == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iOculusAssistantAttentionService;
                return true;
            }
        }

        public static IOculusAssistantAttentionService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
