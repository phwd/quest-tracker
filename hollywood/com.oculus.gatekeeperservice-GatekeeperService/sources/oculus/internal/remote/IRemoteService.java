package oculus.internal.remote;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import oculus.internal.remote.IControllerCalibrationCallback;
import oculus.internal.remote.IRemoteServiceFirmwareUpdateProgressCallback;
import oculus.internal.remote.IRemoteServiceStatusCallback;

public interface IRemoteService extends IInterface {
    public static final int DEVICE_PRIMARY = 0;
    public static final int DEVICE_SECONDARY = 1;
    public static final int SCAN_AND_PAIR_ALREADY_IN_PROGRESS = 3;
    public static final int SCAN_AND_PAIR_FAILED_TO_PAIR = 2;
    public static final int SCAN_AND_PAIR_INTERNAL_ERROR = 4;
    public static final int SCAN_AND_PAIR_RESULT_COUNT = 5;
    public static final int SCAN_AND_PAIR_SUCCESS = 0;
    public static final int SCAN_AND_PAIR_TIMED_OUT = 1;
    public static final int VERIFY_FAIL_ALREADY_IN_PROGRESS = 4;
    public static final int VERIFY_FAIL_BATTERY_DEAD = 8;
    public static final int VERIFY_FAIL_BLOCKED_BY_UPDATE = 9;
    public static final int VERIFY_FAIL_NOT_PAIRED = 7;
    public static final int VERIFY_FAIL_SECURITY_ERROR = 6;
    public static final int VERIFY_FAIL_TIMED_OUT = 5;
    public static final int VERIFY_RESULT_COUNT = 10;
    public static final int VERIFY_SUCCESS_CONNECTED = 0;
    public static final int VERIFY_SUCCESS_RECENTLY_CONNECTED = 3;
    public static final int VERIFY_SUCCESS_UPDATE_PENDING = 2;
    public static final int VERIFY_SUCCESS_UPDATING = 1;

    void cancelInProgressCalibration() throws RemoteException;

    void debugCommand(int i, String[] strArr) throws RemoteException;

    String getDeviceTypeDescription(int i) throws RemoteException;

    RemoteStatus getPairedDeviceStatus(int i) throws RemoteException;

    int[] getSupportedDeviceTypes() throws RemoteException;

    boolean isCalibrationInProgress() throws RemoteException;

    void registerFirmwareUpdateProgressCallback(IRemoteServiceFirmwareUpdateProgressCallback iRemoteServiceFirmwareUpdateProgressCallback) throws RemoteException;

    void registerStatusCallback(IRemoteServiceStatusCallback iRemoteServiceStatusCallback) throws RemoteException;

    int scanAndPairDevice(int i, int i2) throws RemoteException;

    boolean startThumbstickOrientationCalibration(int i, int i2, int i3, IControllerCalibrationCallback iControllerCalibrationCallback) throws RemoteException;

    boolean unpairDevice(int i) throws RemoteException;

    void unregisterFirmwareUpdateProgressCallback(IRemoteServiceFirmwareUpdateProgressCallback iRemoteServiceFirmwareUpdateProgressCallback) throws RemoteException;

    void unregisterStatusCallback(IRemoteServiceStatusCallback iRemoteServiceStatusCallback) throws RemoteException;

    int[] verifyControllersConnectable(int[] iArr, int i, int i2) throws RemoteException;

    public static class Default implements IRemoteService {
        @Override // oculus.internal.remote.IRemoteService
        public int[] getSupportedDeviceTypes() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.remote.IRemoteService
        public String getDeviceTypeDescription(int deviceType) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.remote.IRemoteService
        public RemoteStatus getPairedDeviceStatus(int deviceType) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.remote.IRemoteService
        public boolean unpairDevice(int deviceType) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.remote.IRemoteService
        public int scanAndPairDevice(int deviceType, int timeoutMs) throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.remote.IRemoteService
        public void registerStatusCallback(IRemoteServiceStatusCallback statusCallback) throws RemoteException {
        }

        @Override // oculus.internal.remote.IRemoteService
        public void unregisterStatusCallback(IRemoteServiceStatusCallback statusCallback) throws RemoteException {
        }

        @Override // oculus.internal.remote.IRemoteService
        public void registerFirmwareUpdateProgressCallback(IRemoteServiceFirmwareUpdateProgressCallback callback) throws RemoteException {
        }

        @Override // oculus.internal.remote.IRemoteService
        public void unregisterFirmwareUpdateProgressCallback(IRemoteServiceFirmwareUpdateProgressCallback callback) throws RemoteException {
        }

        @Override // oculus.internal.remote.IRemoteService
        public void debugCommand(int deviceType, String[] args) throws RemoteException {
        }

        @Override // oculus.internal.remote.IRemoteService
        public int[] verifyControllersConnectable(int[] deviceTypes, int timeoutMs, int recentConnectionTimeLimit) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.remote.IRemoteService
        public boolean isCalibrationInProgress() throws RemoteException {
            return false;
        }

        @Override // oculus.internal.remote.IRemoteService
        public void cancelInProgressCalibration() throws RemoteException {
        }

        @Override // oculus.internal.remote.IRemoteService
        public boolean startThumbstickOrientationCalibration(int deviceType, int samplingPeriodMs, int timeoutMs, IControllerCalibrationCallback callback) throws RemoteException {
            return false;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IRemoteService {
        private static final String DESCRIPTOR = "oculus.internal.remote.IRemoteService";
        static final int TRANSACTION_cancelInProgressCalibration = 13;
        static final int TRANSACTION_debugCommand = 10;
        static final int TRANSACTION_getDeviceTypeDescription = 2;
        static final int TRANSACTION_getPairedDeviceStatus = 3;
        static final int TRANSACTION_getSupportedDeviceTypes = 1;
        static final int TRANSACTION_isCalibrationInProgress = 12;
        static final int TRANSACTION_registerFirmwareUpdateProgressCallback = 8;
        static final int TRANSACTION_registerStatusCallback = 6;
        static final int TRANSACTION_scanAndPairDevice = 5;
        static final int TRANSACTION_startThumbstickOrientationCalibration = 14;
        static final int TRANSACTION_unpairDevice = 4;
        static final int TRANSACTION_unregisterFirmwareUpdateProgressCallback = 9;
        static final int TRANSACTION_unregisterStatusCallback = 7;
        static final int TRANSACTION_verifyControllersConnectable = 11;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRemoteService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IRemoteService)) {
                return new Proxy(obj);
            }
            return (IRemoteService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1598968902) {
                switch (code) {
                    case 1:
                        data.enforceInterface(DESCRIPTOR);
                        int[] _result = getSupportedDeviceTypes();
                        reply.writeNoException();
                        reply.writeIntArray(_result);
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        String _result2 = getDeviceTypeDescription(data.readInt());
                        reply.writeNoException();
                        reply.writeString(_result2);
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        RemoteStatus _result3 = getPairedDeviceStatus(data.readInt());
                        reply.writeNoException();
                        if (_result3 != null) {
                            reply.writeInt(1);
                            _result3.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        boolean unpairDevice = unpairDevice(data.readInt());
                        reply.writeNoException();
                        reply.writeInt(unpairDevice ? 1 : 0);
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        int _result4 = scanAndPairDevice(data.readInt(), data.readInt());
                        reply.writeNoException();
                        reply.writeInt(_result4);
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        registerStatusCallback(IRemoteServiceStatusCallback.Stub.asInterface(data.readStrongBinder()));
                        reply.writeNoException();
                        return true;
                    case 7:
                        data.enforceInterface(DESCRIPTOR);
                        unregisterStatusCallback(IRemoteServiceStatusCallback.Stub.asInterface(data.readStrongBinder()));
                        reply.writeNoException();
                        return true;
                    case 8:
                        data.enforceInterface(DESCRIPTOR);
                        registerFirmwareUpdateProgressCallback(IRemoteServiceFirmwareUpdateProgressCallback.Stub.asInterface(data.readStrongBinder()));
                        reply.writeNoException();
                        return true;
                    case 9:
                        data.enforceInterface(DESCRIPTOR);
                        unregisterFirmwareUpdateProgressCallback(IRemoteServiceFirmwareUpdateProgressCallback.Stub.asInterface(data.readStrongBinder()));
                        reply.writeNoException();
                        return true;
                    case 10:
                        data.enforceInterface(DESCRIPTOR);
                        debugCommand(data.readInt(), data.createStringArray());
                        reply.writeNoException();
                        return true;
                    case 11:
                        data.enforceInterface(DESCRIPTOR);
                        int[] _result5 = verifyControllersConnectable(data.createIntArray(), data.readInt(), data.readInt());
                        reply.writeNoException();
                        reply.writeIntArray(_result5);
                        return true;
                    case 12:
                        data.enforceInterface(DESCRIPTOR);
                        boolean isCalibrationInProgress = isCalibrationInProgress();
                        reply.writeNoException();
                        reply.writeInt(isCalibrationInProgress ? 1 : 0);
                        return true;
                    case 13:
                        data.enforceInterface(DESCRIPTOR);
                        cancelInProgressCalibration();
                        reply.writeNoException();
                        return true;
                    case 14:
                        data.enforceInterface(DESCRIPTOR);
                        boolean startThumbstickOrientationCalibration = startThumbstickOrientationCalibration(data.readInt(), data.readInt(), data.readInt(), IControllerCalibrationCallback.Stub.asInterface(data.readStrongBinder()));
                        reply.writeNoException();
                        reply.writeInt(startThumbstickOrientationCalibration ? 1 : 0);
                        return true;
                    default:
                        return super.onTransact(code, data, reply, flags);
                }
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IRemoteService {
            public static IRemoteService sDefaultImpl;
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

            @Override // oculus.internal.remote.IRemoteService
            public int[] getSupportedDeviceTypes() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSupportedDeviceTypes();
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.remote.IRemoteService
            public String getDeviceTypeDescription(int deviceType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceType);
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceTypeDescription(deviceType);
                    }
                    _reply.readException();
                    String _result = _reply.readString();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.remote.IRemoteService
            public RemoteStatus getPairedDeviceStatus(int deviceType) throws RemoteException {
                RemoteStatus _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceType);
                    if (!this.mRemote.transact(3, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPairedDeviceStatus(deviceType);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = RemoteStatus.CREATOR.createFromParcel(_reply);
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

            @Override // oculus.internal.remote.IRemoteService
            public boolean unpairDevice(int deviceType) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceType);
                    boolean _result = false;
                    if (!this.mRemote.transact(4, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unpairDevice(deviceType);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.remote.IRemoteService
            public int scanAndPairDevice(int deviceType, int timeoutMs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceType);
                    _data.writeInt(timeoutMs);
                    if (!this.mRemote.transact(5, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().scanAndPairDevice(deviceType, timeoutMs);
                    }
                    _reply.readException();
                    int _result = _reply.readInt();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.remote.IRemoteService
            public void registerStatusCallback(IRemoteServiceStatusCallback statusCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(statusCallback != null ? statusCallback.asBinder() : null);
                    if (this.mRemote.transact(6, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().registerStatusCallback(statusCallback);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.remote.IRemoteService
            public void unregisterStatusCallback(IRemoteServiceStatusCallback statusCallback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(statusCallback != null ? statusCallback.asBinder() : null);
                    if (this.mRemote.transact(7, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unregisterStatusCallback(statusCallback);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.remote.IRemoteService
            public void registerFirmwareUpdateProgressCallback(IRemoteServiceFirmwareUpdateProgressCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (this.mRemote.transact(8, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().registerFirmwareUpdateProgressCallback(callback);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.remote.IRemoteService
            public void unregisterFirmwareUpdateProgressCallback(IRemoteServiceFirmwareUpdateProgressCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    if (this.mRemote.transact(9, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unregisterFirmwareUpdateProgressCallback(callback);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.remote.IRemoteService
            public void debugCommand(int deviceType, String[] args) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceType);
                    _data.writeStringArray(args);
                    if (this.mRemote.transact(10, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().debugCommand(deviceType, args);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.remote.IRemoteService
            public int[] verifyControllersConnectable(int[] deviceTypes, int timeoutMs, int recentConnectionTimeLimit) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(deviceTypes);
                    _data.writeInt(timeoutMs);
                    _data.writeInt(recentConnectionTimeLimit);
                    if (!this.mRemote.transact(11, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().verifyControllersConnectable(deviceTypes, timeoutMs, recentConnectionTimeLimit);
                    }
                    _reply.readException();
                    int[] _result = _reply.createIntArray();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.remote.IRemoteService
            public boolean isCalibrationInProgress() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    if (!this.mRemote.transact(12, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isCalibrationInProgress();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.remote.IRemoteService
            public void cancelInProgressCalibration() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(13, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().cancelInProgressCalibration();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.remote.IRemoteService
            public boolean startThumbstickOrientationCalibration(int deviceType, int samplingPeriodMs, int timeoutMs, IControllerCalibrationCallback callback) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(deviceType);
                    _data.writeInt(samplingPeriodMs);
                    _data.writeInt(timeoutMs);
                    _data.writeStrongBinder(callback != null ? callback.asBinder() : null);
                    boolean _result = false;
                    if (!this.mRemote.transact(14, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startThumbstickOrientationCalibration(deviceType, samplingPeriodMs, timeoutMs, callback);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRemoteService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IRemoteService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
