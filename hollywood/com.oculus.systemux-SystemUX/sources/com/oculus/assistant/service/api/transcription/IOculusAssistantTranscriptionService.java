package com.oculus.assistant.service.api.transcription;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IOculusAssistantTranscriptionService extends IInterface {

    public static class Default implements IOculusAssistantTranscriptionService {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.assistant.service.api.transcription.IOculusAssistantTranscriptionService
        public void configure(Bundle bundle) throws RemoteException {
        }

        @Override // com.oculus.assistant.service.api.transcription.IOculusAssistantTranscriptionService
        public void logEvent(String str, Bundle bundle) throws RemoteException {
        }

        @Override // com.oculus.assistant.service.api.transcription.IOculusAssistantTranscriptionService
        public void startTranscriptionSession() throws RemoteException {
        }

        @Override // com.oculus.assistant.service.api.transcription.IOculusAssistantTranscriptionService
        public void stopTranscriptionSession() throws RemoteException {
        }
    }

    void configure(Bundle bundle) throws RemoteException;

    void logEvent(String str, Bundle bundle) throws RemoteException;

    void startTranscriptionSession() throws RemoteException;

    void stopTranscriptionSession() throws RemoteException;

    public static abstract class Stub extends Binder implements IOculusAssistantTranscriptionService {
        private static final String DESCRIPTOR = "com.oculus.assistant.service.api.transcription.IOculusAssistantTranscriptionService";
        static final int TRANSACTION_configure = 3;
        static final int TRANSACTION_logEvent = 4;
        static final int TRANSACTION_startTranscriptionSession = 1;
        static final int TRANSACTION_stopTranscriptionSession = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOculusAssistantTranscriptionService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOculusAssistantTranscriptionService)) {
                return new Proxy(iBinder);
            }
            return (IOculusAssistantTranscriptionService) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                startTranscriptionSession();
                parcel2.writeNoException();
                return true;
            } else if (i != 2) {
                Bundle bundle = null;
                if (i == 3) {
                    parcel.enforceInterface(DESCRIPTOR);
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    configure(bundle);
                    parcel2.writeNoException();
                    return true;
                } else if (i == 4) {
                    parcel.enforceInterface(DESCRIPTOR);
                    String readString = parcel.readString();
                    if (parcel.readInt() != 0) {
                        bundle = (Bundle) Bundle.CREATOR.createFromParcel(parcel);
                    }
                    logEvent(readString, bundle);
                    parcel2.writeNoException();
                    return true;
                } else if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                } else {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
            } else {
                parcel.enforceInterface(DESCRIPTOR);
                stopTranscriptionSession();
                parcel2.writeNoException();
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IOculusAssistantTranscriptionService {
            public static IOculusAssistantTranscriptionService sDefaultImpl;
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

            @Override // com.oculus.assistant.service.api.transcription.IOculusAssistantTranscriptionService
            public void startTranscriptionSession() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().startTranscriptionSession();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.transcription.IOculusAssistantTranscriptionService
            public void stopTranscriptionSession() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().stopTranscriptionSession();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.transcription.IOculusAssistantTranscriptionService
            public void configure(Bundle bundle) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
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
                    Stub.getDefaultImpl().configure(bundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.transcription.IOculusAssistantTranscriptionService
            public void logEvent(String str, Bundle bundle) throws RemoteException {
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
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().logEvent(str, bundle);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOculusAssistantTranscriptionService iOculusAssistantTranscriptionService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iOculusAssistantTranscriptionService == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iOculusAssistantTranscriptionService;
                return true;
            }
        }

        public static IOculusAssistantTranscriptionService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
