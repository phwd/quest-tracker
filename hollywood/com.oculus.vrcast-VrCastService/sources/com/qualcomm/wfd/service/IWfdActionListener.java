package com.qualcomm.wfd.service;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IWfdActionListener extends IInterface {
    void notify(Bundle bundle, int i) throws RemoteException;

    void notifyEvent(int i, int i2) throws RemoteException;

    void onStateUpdate(int i, int i2) throws RemoteException;

    public static abstract class Stub extends Binder implements IWfdActionListener {
        private static final String DESCRIPTOR = "com.qualcomm.wfd.service.IWfdActionListener";
        static final int TRANSACTION_notify = 3;
        static final int TRANSACTION_notifyEvent = 2;
        static final int TRANSACTION_onStateUpdate = 1;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWfdActionListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IWfdActionListener)) {
                return new Proxy(iBinder);
            }
            return (IWfdActionListener) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                onStateUpdate(parcel.readInt(), parcel.readInt());
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                notifyEvent(parcel.readInt(), parcel.readInt());
                return true;
            } else if (i == 3) {
                parcel.enforceInterface(DESCRIPTOR);
                notify(parcel.readInt() != 0 ? (Bundle) Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements IWfdActionListener {
            public static IWfdActionListener sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }
        }

        public static boolean setDefaultImpl(IWfdActionListener iWfdActionListener) {
            if (Proxy.sDefaultImpl != null || iWfdActionListener == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWfdActionListener;
            return true;
        }

        public static IWfdActionListener getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
