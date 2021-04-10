package com.oculus.assistant.service.api.playback;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPlaybackStatusListener extends IInterface {

    public static class Default implements IPlaybackStatusListener {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.assistant.service.api.playback.IPlaybackStatusListener
        public void onPlaybackStarted(String str) throws RemoteException {
        }

        @Override // com.oculus.assistant.service.api.playback.IPlaybackStatusListener
        public void onPlaybackStopped(String str) throws RemoteException {
        }
    }

    void onPlaybackStarted(String str) throws RemoteException;

    void onPlaybackStopped(String str) throws RemoteException;

    public static abstract class Stub extends Binder implements IPlaybackStatusListener {
        private static final String DESCRIPTOR = "com.oculus.assistant.service.api.playback.IPlaybackStatusListener";
        static final int TRANSACTION_onPlaybackStarted = 1;
        static final int TRANSACTION_onPlaybackStopped = 2;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPlaybackStatusListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IPlaybackStatusListener)) {
                return new Proxy(iBinder);
            }
            return (IPlaybackStatusListener) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onPlaybackStarted(parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                onPlaybackStopped(parcel.readString());
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
        public static class Proxy implements IPlaybackStatusListener {
            public static IPlaybackStatusListener sDefaultImpl;
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

            @Override // com.oculus.assistant.service.api.playback.IPlaybackStatusListener
            public void onPlaybackStarted(String str) throws RemoteException {
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
                    Stub.getDefaultImpl().onPlaybackStarted(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.playback.IPlaybackStatusListener
            public void onPlaybackStopped(String str) throws RemoteException {
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
                    Stub.getDefaultImpl().onPlaybackStopped(str);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPlaybackStatusListener iPlaybackStatusListener) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iPlaybackStatusListener == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iPlaybackStatusListener;
                return true;
            }
        }

        public static IPlaybackStatusListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
