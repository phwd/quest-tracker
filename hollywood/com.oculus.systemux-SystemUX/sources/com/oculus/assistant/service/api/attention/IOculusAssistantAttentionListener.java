package com.oculus.assistant.service.api.attention;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IOculusAssistantAttentionListener extends IInterface {

    public static class Default implements IOculusAssistantAttentionListener {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
        public boolean onAssistantResponse(String str) throws RemoteException {
            return false;
        }

        @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
        public boolean onError(AssistantErrorType assistantErrorType) throws RemoteException {
            return false;
        }

        @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
        public boolean onInteractionCompleted(boolean z) throws RemoteException {
            return false;
        }

        @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
        public boolean onInteractionId(String str) throws RemoteException {
            return false;
        }

        @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
        public boolean onInteractionPropertiesChanged(float f) throws RemoteException {
            return false;
        }

        @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
        public boolean onInteractionStateChanged(AssistantInteractionState assistantInteractionState) throws RemoteException {
            return false;
        }

        @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
        public boolean onTranscriptionChanged(String str, boolean z) throws RemoteException {
            return false;
        }
    }

    boolean onAssistantResponse(String str) throws RemoteException;

    boolean onError(AssistantErrorType assistantErrorType) throws RemoteException;

    boolean onInteractionCompleted(boolean z) throws RemoteException;

    boolean onInteractionId(String str) throws RemoteException;

    boolean onInteractionPropertiesChanged(float f) throws RemoteException;

    boolean onInteractionStateChanged(AssistantInteractionState assistantInteractionState) throws RemoteException;

    boolean onTranscriptionChanged(String str, boolean z) throws RemoteException;

    public static abstract class Stub extends Binder implements IOculusAssistantAttentionListener {
        private static final String DESCRIPTOR = "com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener";
        static final int TRANSACTION_onAssistantResponse = 4;
        static final int TRANSACTION_onError = 6;
        static final int TRANSACTION_onInteractionCompleted = 5;
        static final int TRANSACTION_onInteractionId = 7;
        static final int TRANSACTION_onInteractionPropertiesChanged = 2;
        static final int TRANSACTION_onInteractionStateChanged = 1;
        static final int TRANSACTION_onTranscriptionChanged = 3;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOculusAssistantAttentionListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOculusAssistantAttentionListener)) {
                return new Proxy(iBinder);
            }
            return (IOculusAssistantAttentionListener) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                boolean z = false;
                AssistantInteractionState assistantInteractionState = null;
                AssistantErrorType assistantErrorType = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            assistantInteractionState = AssistantInteractionState.CREATOR.createFromParcel(parcel);
                        }
                        boolean onInteractionStateChanged = onInteractionStateChanged(assistantInteractionState);
                        parcel2.writeNoException();
                        parcel2.writeInt(onInteractionStateChanged ? 1 : 0);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean onInteractionPropertiesChanged = onInteractionPropertiesChanged(parcel.readFloat());
                        parcel2.writeNoException();
                        parcel2.writeInt(onInteractionPropertiesChanged ? 1 : 0);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        String readString = parcel.readString();
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        boolean onTranscriptionChanged = onTranscriptionChanged(readString, z);
                        parcel2.writeNoException();
                        parcel2.writeInt(onTranscriptionChanged ? 1 : 0);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean onAssistantResponse = onAssistantResponse(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(onAssistantResponse ? 1 : 0);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            z = true;
                        }
                        boolean onInteractionCompleted = onInteractionCompleted(z);
                        parcel2.writeNoException();
                        parcel2.writeInt(onInteractionCompleted ? 1 : 0);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        if (parcel.readInt() != 0) {
                            assistantErrorType = AssistantErrorType.CREATOR.createFromParcel(parcel);
                        }
                        boolean onError = onError(assistantErrorType);
                        parcel2.writeNoException();
                        parcel2.writeInt(onError ? 1 : 0);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        boolean onInteractionId = onInteractionId(parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(onInteractionId ? 1 : 0);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IOculusAssistantAttentionListener {
            public static IOculusAssistantAttentionListener sDefaultImpl;
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

            @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
            public boolean onInteractionStateChanged(AssistantInteractionState assistantInteractionState) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (assistantInteractionState != null) {
                        obtain.writeInt(1);
                        assistantInteractionState.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onInteractionStateChanged(assistantInteractionState);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
            public boolean onInteractionPropertiesChanged(float f) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeFloat(f);
                    boolean z = false;
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onInteractionPropertiesChanged(f);
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

            @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
            public boolean onTranscriptionChanged(String str, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z2 = true;
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onTranscriptionChanged(str, z);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
            public boolean onAssistantResponse(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onAssistantResponse(str);
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

            @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
            public boolean onInteractionCompleted(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z2 = true;
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onInteractionCompleted(z);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
            public boolean onError(AssistantErrorType assistantErrorType) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = true;
                    if (assistantErrorType != null) {
                        obtain.writeInt(1);
                        assistantErrorType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onError(assistantErrorType);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
            public boolean onInteractionId(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    boolean z = false;
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onInteractionId(str);
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
        }

        public static boolean setDefaultImpl(IOculusAssistantAttentionListener iOculusAssistantAttentionListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iOculusAssistantAttentionListener == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iOculusAssistantAttentionListener;
                return true;
            }
        }

        public static IOculusAssistantAttentionListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
