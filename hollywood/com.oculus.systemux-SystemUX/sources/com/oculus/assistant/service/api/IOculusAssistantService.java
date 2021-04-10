package com.oculus.assistant.service.api;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.assistant.service.api.attention.IOculusAssistantAttentionService;
import com.oculus.assistant.service.api.control.IOculusAssistantControlService;
import com.oculus.assistant.service.api.transcription.IOculusAssistantTranscriptionService;

public interface IOculusAssistantService extends IInterface {

    public static class Default implements IOculusAssistantService {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.assistant.service.api.IOculusAssistantService
        public IOculusAssistantAttentionService getAttentionService() throws RemoteException {
            return null;
        }

        @Override // com.oculus.assistant.service.api.IOculusAssistantService
        public IOculusAssistantControlService getControlService() throws RemoteException {
            return null;
        }

        @Override // com.oculus.assistant.service.api.IOculusAssistantService
        public IOculusAssistantTranscriptionService getTranscriptionService() throws RemoteException {
            return null;
        }
    }

    IOculusAssistantAttentionService getAttentionService() throws RemoteException;

    IOculusAssistantControlService getControlService() throws RemoteException;

    IOculusAssistantTranscriptionService getTranscriptionService() throws RemoteException;

    public static abstract class Stub extends Binder implements IOculusAssistantService {
        private static final String DESCRIPTOR = "com.oculus.assistant.service.api.IOculusAssistantService";
        static final int TRANSACTION_getAttentionService = 2;
        static final int TRANSACTION_getControlService = 1;
        static final int TRANSACTION_getTranscriptionService = 3;

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
            IBinder iBinder = null;
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                IOculusAssistantControlService controlService = getControlService();
                parcel2.writeNoException();
                if (controlService != null) {
                    iBinder = controlService.asBinder();
                }
                parcel2.writeStrongBinder(iBinder);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                IOculusAssistantAttentionService attentionService = getAttentionService();
                parcel2.writeNoException();
                if (attentionService != null) {
                    iBinder = attentionService.asBinder();
                }
                parcel2.writeStrongBinder(iBinder);
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                IOculusAssistantTranscriptionService transcriptionService = getTranscriptionService();
                parcel2.writeNoException();
                if (transcriptionService != null) {
                    iBinder = transcriptionService.asBinder();
                }
                parcel2.writeStrongBinder(iBinder);
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

            @Override // com.oculus.assistant.service.api.IOculusAssistantService
            public IOculusAssistantControlService getControlService() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getControlService();
                    }
                    obtain2.readException();
                    IOculusAssistantControlService asInterface = IOculusAssistantControlService.Stub.asInterface(obtain2.readStrongBinder());
                    obtain2.recycle();
                    obtain.recycle();
                    return asInterface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.IOculusAssistantService
            public IOculusAssistantAttentionService getAttentionService() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAttentionService();
                    }
                    obtain2.readException();
                    IOculusAssistantAttentionService asInterface = IOculusAssistantAttentionService.Stub.asInterface(obtain2.readStrongBinder());
                    obtain2.recycle();
                    obtain.recycle();
                    return asInterface;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.IOculusAssistantService
            public IOculusAssistantTranscriptionService getTranscriptionService() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTranscriptionService();
                    }
                    obtain2.readException();
                    IOculusAssistantTranscriptionService asInterface = IOculusAssistantTranscriptionService.Stub.asInterface(obtain2.readStrongBinder());
                    obtain2.recycle();
                    obtain.recycle();
                    return asInterface;
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
