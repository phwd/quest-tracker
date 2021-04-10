package com.oculus.assistant.service.api.recording;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IRecordingStatusListener extends IInterface {

    public static class Default implements IRecordingStatusListener {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.assistant.service.api.recording.IRecordingStatusListener
        public void onRecordingStarted(String str) throws RemoteException {
        }

        @Override // com.oculus.assistant.service.api.recording.IRecordingStatusListener
        public void onRecordingStopped(String str) throws RemoteException {
        }
    }

    void onRecordingStarted(String str) throws RemoteException;

    void onRecordingStopped(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IRecordingStatusListener {
        private static final String DESCRIPTOR = "com.oculus.assistant.service.api.recording.IRecordingStatusListener";
        static final int TRANSACTION_onRecordingStarted = 1;
        static final int TRANSACTION_onRecordingStopped = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRecordingStatusListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IRecordingStatusListener)) {
                return new Proxy(iBinder);
            }
            return (IRecordingStatusListener) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onRecordingStarted(parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onRecordingStopped(parcel.readString());
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
        public static class Proxy implements IRecordingStatusListener {
            public static IRecordingStatusListener sDefaultImpl;
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

            @Override // com.oculus.assistant.service.api.recording.IRecordingStatusListener
            public void onRecordingStarted(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || Stub.getDefaultImpl() == null) {
                        obtain2.readException();
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().onRecordingStarted(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.recording.IRecordingStatusListener
            public void onRecordingStopped(String str) throws RemoteException {
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
                    Stub.getDefaultImpl().onRecordingStopped(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRecordingStatusListener iRecordingStatusListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iRecordingStatusListener == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iRecordingStatusListener;
                return true;
            }
        }

        public static IRecordingStatusListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
