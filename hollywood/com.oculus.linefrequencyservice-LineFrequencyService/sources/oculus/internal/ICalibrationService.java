package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import oculus.internal.ICalibrationClient;

public interface ICalibrationService extends IInterface {
    public static final int CONSUMER = 0;
    public static final int PRODUCER = 1;
    public static final int SLAM = 0;

    ICalibrationClient getCalibrationClient(int i, int i2) throws RemoteException;

    ParcelFileDescriptor getCalibrationFileFd(String str) throws RemoteException;

    byte[] loadCalibrationFile(String str) throws RemoteException;

    void writeCalibrationData(String str, byte[] bArr) throws RemoteException;

    public static class Default implements ICalibrationService {
        @Override // oculus.internal.ICalibrationService
        public byte[] loadCalibrationFile(String type) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.ICalibrationService
        public ParcelFileDescriptor getCalibrationFileFd(String type) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.ICalibrationService
        public void writeCalibrationData(String type, byte[] data) throws RemoteException {
        }

        @Override // oculus.internal.ICalibrationService
        public ICalibrationClient getCalibrationClient(int sensorGroup, int clientType) throws RemoteException {
            return null;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ICalibrationService {
        private static final String DESCRIPTOR = "oculus.internal.ICalibrationService";
        static final int TRANSACTION_getCalibrationClient = 4;
        static final int TRANSACTION_getCalibrationFileFd = 2;
        static final int TRANSACTION_loadCalibrationFile = 1;
        static final int TRANSACTION_writeCalibrationData = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICalibrationService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ICalibrationService)) {
                return new Proxy(obj);
            }
            return (ICalibrationService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                byte[] _result = loadCalibrationFile(data.readString());
                reply.writeNoException();
                reply.writeByteArray(_result);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                ParcelFileDescriptor _result2 = getCalibrationFileFd(data.readString());
                reply.writeNoException();
                if (_result2 != null) {
                    reply.writeInt(1);
                    _result2.writeToParcel(reply, 1);
                } else {
                    reply.writeInt(0);
                }
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                writeCalibrationData(data.readString(), data.createByteArray());
                reply.writeNoException();
                return true;
            } else if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                ICalibrationClient _result3 = getCalibrationClient(data.readInt(), data.readInt());
                reply.writeNoException();
                reply.writeStrongBinder(_result3 != null ? _result3.asBinder() : null);
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements ICalibrationService {
            public static ICalibrationService sDefaultImpl;
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

            @Override // oculus.internal.ICalibrationService
            public byte[] loadCalibrationFile(String type) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(type);
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().loadCalibrationFile(type);
                    }
                    _reply.readException();
                    byte[] _result = _reply.createByteArray();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ICalibrationService
            public ParcelFileDescriptor getCalibrationFileFd(String type) throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(type);
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCalibrationFileFd(type);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(_reply);
                    } else {
                        _result = null;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ICalibrationService
            public void writeCalibrationData(String type, byte[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(type);
                    _data.writeByteArray(data);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().writeCalibrationData(type, data);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ICalibrationService
            public ICalibrationClient getCalibrationClient(int sensorGroup, int clientType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(sensorGroup);
                    _data.writeInt(clientType);
                    if (!this.mRemote.transact(4, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCalibrationClient(sensorGroup, clientType);
                    }
                    _reply.readException();
                    ICalibrationClient _result = ICalibrationClient.Stub.asInterface(_reply.readStrongBinder());
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ICalibrationService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ICalibrationService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
