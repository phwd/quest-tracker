package com.oculus.assistant.service.api.control;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource;

public interface IOculusAssistantControlService extends IInterface {

    public static class Default implements IOculusAssistantControlService {
        @Override // com.oculus.assistant.service.api.control.IOculusAssistantControlService
        public void activateAssistant() throws RemoteException {
        }

        @Override // com.oculus.assistant.service.api.control.IOculusAssistantControlService
        public String addAudioSource(IRemoteAudioSource iRemoteAudioSource, int i) throws RemoteException {
            return null;
        }

        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.assistant.service.api.control.IOculusAssistantControlService
        public boolean isAutorecordingEnabled() throws RemoteException {
            return false;
        }

        @Override // com.oculus.assistant.service.api.control.IOculusAssistantControlService
        public void removeAudioSource(String str) throws RemoteException {
        }

        @Override // com.oculus.assistant.service.api.control.IOculusAssistantControlService
        public void stopAssistant() throws RemoteException {
        }
    }

    void activateAssistant() throws RemoteException;

    String addAudioSource(IRemoteAudioSource iRemoteAudioSource, int i) throws RemoteException;

    boolean isAutorecordingEnabled() throws RemoteException;

    void removeAudioSource(String str) throws RemoteException;

    void stopAssistant() throws RemoteException;

    public static abstract class Stub extends Binder implements IOculusAssistantControlService {
        private static final String DESCRIPTOR = "com.oculus.assistant.service.api.control.IOculusAssistantControlService";
        static final int TRANSACTION_activateAssistant = 1;
        static final int TRANSACTION_addAudioSource = 4;
        static final int TRANSACTION_isAutorecordingEnabled = 3;
        static final int TRANSACTION_removeAudioSource = 5;
        static final int TRANSACTION_stopAssistant = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOculusAssistantControlService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOculusAssistantControlService)) {
                return new Proxy(iBinder);
            }
            return (IOculusAssistantControlService) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                activateAssistant();
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                stopAssistant();
                parcel2.writeNoException();
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                boolean isAutorecordingEnabled = isAutorecordingEnabled();
                parcel2.writeNoException();
                parcel2.writeInt(isAutorecordingEnabled ? 1 : 0);
                return true;
            } else if (i == 4) {
                parcel.enforceInterface(DESCRIPTOR);
                String addAudioSource = addAudioSource(IRemoteAudioSource.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                parcel2.writeNoException();
                parcel2.writeString(addAudioSource);
                return true;
            } else if (i == 5) {
                parcel.enforceInterface(DESCRIPTOR);
                removeAudioSource(parcel.readString());
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
        public static class Proxy implements IOculusAssistantControlService {
            public static IOculusAssistantControlService sDefaultImpl;
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

            @Override // com.oculus.assistant.service.api.control.IOculusAssistantControlService
            public void activateAssistant() throws RemoteException {
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
                    Stub.getDefaultImpl().activateAssistant();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.control.IOculusAssistantControlService
            public void stopAssistant() throws RemoteException {
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
                    Stub.getDefaultImpl().stopAssistant();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.control.IOculusAssistantControlService
            public boolean isAutorecordingEnabled() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean z = false;
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isAutorecordingEnabled();
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

            @Override // com.oculus.assistant.service.api.control.IOculusAssistantControlService
            public String addAudioSource(IRemoteAudioSource iRemoteAudioSource, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iRemoteAudioSource != null ? iRemoteAudioSource.asBinder() : null);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addAudioSource(iRemoteAudioSource, i);
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

            @Override // com.oculus.assistant.service.api.control.IOculusAssistantControlService
            public void removeAudioSource(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(5, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().removeAudioSource(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOculusAssistantControlService iOculusAssistantControlService) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iOculusAssistantControlService == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iOculusAssistantControlService;
                return true;
            }
        }

        public static IOculusAssistantControlService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
