package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICameraSettingsProvider extends IInterface {
    void setExposureSettings(int i, ExposureInfo[] exposureInfoArr) throws RemoteException;

    void setPhaseOffset(int i, long j) throws RemoteException;

    void startOverridingExposureSettings(int i) throws RemoteException;

    void stopOverridingExposureSettings(int i) throws RemoteException;

    public static class Default implements ICameraSettingsProvider {
        @Override // oculus.internal.ICameraSettingsProvider
        public void setPhaseOffset(int frameType, long offset) throws RemoteException {
        }

        @Override // oculus.internal.ICameraSettingsProvider
        public void startOverridingExposureSettings(int frameType) throws RemoteException {
        }

        @Override // oculus.internal.ICameraSettingsProvider
        public void stopOverridingExposureSettings(int frameType) throws RemoteException {
        }

        @Override // oculus.internal.ICameraSettingsProvider
        public void setExposureSettings(int frameType, ExposureInfo[] exposureSettings) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICameraSettingsProvider {
        private static final String DESCRIPTOR = "oculus.internal.ICameraSettingsProvider";
        static final int TRANSACTION_setExposureSettings = 4;
        static final int TRANSACTION_setPhaseOffset = 1;
        static final int TRANSACTION_startOverridingExposureSettings = 2;
        static final int TRANSACTION_stopOverridingExposureSettings = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICameraSettingsProvider asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ICameraSettingsProvider)) {
                return new Proxy(obj);
            }
            return (ICameraSettingsProvider) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                setPhaseOffset(data.readInt(), data.readLong());
                reply.writeNoException();
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                startOverridingExposureSettings(data.readInt());
                reply.writeNoException();
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                stopOverridingExposureSettings(data.readInt());
                reply.writeNoException();
                return true;
            } else if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                setExposureSettings(data.readInt(), (ExposureInfo[]) data.createTypedArray(ExposureInfo.CREATOR));
                reply.writeNoException();
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements ICameraSettingsProvider {
            public static ICameraSettingsProvider sDefaultImpl;
            private IBinder mRemote;

            Proxy(IBinder remote) {
                this.mRemote = remote;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            @Override // oculus.internal.ICameraSettingsProvider
            public void setPhaseOffset(int frameType, long offset) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(frameType);
                    _data.writeLong(offset);
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setPhaseOffset(frameType, offset);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ICameraSettingsProvider
            public void startOverridingExposureSettings(int frameType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(frameType);
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().startOverridingExposureSettings(frameType);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ICameraSettingsProvider
            public void stopOverridingExposureSettings(int frameType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(frameType);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().stopOverridingExposureSettings(frameType);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ICameraSettingsProvider
            public void setExposureSettings(int frameType, ExposureInfo[] exposureSettings) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(frameType);
                    _data.writeTypedArray(exposureSettings, 0);
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setExposureSettings(frameType, exposureSettings);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICameraSettingsProvider impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ICameraSettingsProvider getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
