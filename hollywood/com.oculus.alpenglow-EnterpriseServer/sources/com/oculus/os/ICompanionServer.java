package com.oculus.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.os.IRemoteWipeCallback;

public interface ICompanionServer extends IInterface {

    public static class Default implements ICompanionServer {
        public IBinder asBinder() {
            return null;
        }

        @Override // com.oculus.os.ICompanionServer
        public void claimDevice(String str) throws RemoteException {
        }

        @Override // com.oculus.os.ICompanionServer
        public String getUserId() throws RemoteException {
            return null;
        }

        @Override // com.oculus.os.ICompanionServer
        public void performAntiPiracyKillSwitchAction() throws RemoteException {
        }

        @Override // com.oculus.os.ICompanionServer
        public void performRemoteWipe(IRemoteWipeCallback iRemoteWipeCallback) throws RemoteException {
        }
    }

    public static abstract class Stub extends Binder implements ICompanionServer {
        public static final String DESCRIPTOR = "com.oculus.os.ICompanionServer";
        public static final int TRANSACTION_claimDevice = 3;
        public static final int TRANSACTION_getUserId = 1;
        public static final int TRANSACTION_performAntiPiracyKillSwitchAction = 4;
        public static final int TRANSACTION_performRemoteWipe = 2;

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 2) {
                    parcel.enforceInterface(DESCRIPTOR);
                    performRemoteWipe(IRemoteWipeCallback.Stub.asInterface(parcel.readStrongBinder()));
                } else if (i == 3) {
                    parcel.enforceInterface(DESCRIPTOR);
                    claimDevice(parcel.readString());
                } else if (i == 4) {
                    parcel.enforceInterface(DESCRIPTOR);
                    performAntiPiracyKillSwitchAction();
                } else if (i != 1598968902) {
                    return super.onTransact(i, parcel, parcel2, i2);
                } else {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                parcel2.writeNoException();
                return true;
            }
            parcel.enforceInterface(DESCRIPTOR);
            String userId = getUserId();
            parcel2.writeNoException();
            parcel2.writeString(userId);
            return true;
        }

        public static class Proxy implements ICompanionServer {
            public static ICompanionServer sDefaultImpl;
            public IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            public Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // com.oculus.os.ICompanionServer
            public void claimDevice(String str) throws RemoteException {
                ICompanionServer iCompanionServer;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (this.mRemote.transact(3, obtain, obtain2, 0) || (iCompanionServer = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iCompanionServer.claimDevice(str);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.os.ICompanionServer
            public String getUserId() throws RemoteException {
                String str;
                ICompanionServer iCompanionServer;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, obtain, obtain2, 0) || (iCompanionServer = sDefaultImpl) == null) {
                        obtain2.readException();
                        str = obtain2.readString();
                    } else {
                        str = iCompanionServer.getUserId();
                    }
                    return str;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.os.ICompanionServer
            public void performAntiPiracyKillSwitchAction() throws RemoteException {
                ICompanionServer iCompanionServer;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, obtain, obtain2, 0) || (iCompanionServer = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iCompanionServer.performAntiPiracyKillSwitchAction();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.os.ICompanionServer
            public void performRemoteWipe(IRemoteWipeCallback iRemoteWipeCallback) throws RemoteException {
                IBinder iBinder;
                ICompanionServer iCompanionServer;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iRemoteWipeCallback != null) {
                        iBinder = iRemoteWipeCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.mRemote.transact(2, obtain, obtain2, 0) || (iCompanionServer = sDefaultImpl) == null) {
                        obtain2.readException();
                    } else {
                        iCompanionServer.performRemoteWipe(iRemoteWipeCallback);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder asBinder() {
                return this.mRemote;
            }
        }

        public static ICompanionServer asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ICompanionServer)) {
                return new Proxy(iBinder);
            }
            return (ICompanionServer) queryLocalInterface;
        }

        public static boolean setDefaultImpl(ICompanionServer iCompanionServer) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iCompanionServer == null) {
                return false;
            } else {
                Proxy.sDefaultImpl = iCompanionServer;
                return true;
            }
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICompanionServer getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }

    void claimDevice(String str) throws RemoteException;

    String getUserId() throws RemoteException;

    void performAntiPiracyKillSwitchAction() throws RemoteException;

    void performRemoteWipe(IRemoteWipeCallback iRemoteWipeCallback) throws RemoteException;
}
