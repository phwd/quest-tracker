package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.oculus.os.DisplayParams;
import com.oculus.os.PerfRange;

public interface IVrApiService extends IInterface {
    public static final int CLOCK_DOMAIN_COUNT = 4;
    public static final int CLOCK_DOMAIN_CPU = 0;
    public static final int CLOCK_DOMAIN_CPUBW = 2;
    public static final int CLOCK_DOMAIN_GPU = 1;
    public static final int CLOCK_DOMAIN_GPUBW = 3;
    public static final int DVFS_POLICY_COUNT = 3;
    public static final int DVFS_POLICY_DYNAMIC = 0;
    public static final int DVFS_POLICY_MAX = 2;
    public static final int DVFS_POLICY_STATIC = 1;
    public static final int THREAD_POLICY_BACKGROUND = 0;
    public static final int THREAD_POLICY_COMPUTE = 1;
    public static final int THREAD_POLICY_COUNT = 6;
    public static final int THREAD_POLICY_ELEVATED = 3;
    public static final int THREAD_POLICY_ISOCHRONOUS = 4;
    public static final int THREAD_POLICY_LATENCY_CRITICAL = 5;
    public static final int THREAD_POLICY_LEVEL_COUNT = 3;
    public static final int THREAD_POLICY_LEVEL_HIGH = 2;
    public static final int THREAD_POLICY_LEVEL_LOW = 0;
    public static final int THREAD_POLICY_LEVEL_MEDIUM = 1;
    public static final int THREAD_POLICY_NORMAL = 2;

    void allowTidMaximumPriorityContext(int i) throws RemoteException;

    void allowUidHighPriorityContext(int i) throws RemoteException;

    void getClockFrequencies(int i, int i2, int[] iArr) throws RemoteException;

    int getCurrentClockFrequency(int i, int i2) throws RemoteException;

    int getCurrentClockPerfLevel(int i, int i2) throws RemoteException;

    String getDeviceProperty(String str) throws RemoteException;

    int getDisplayBrightness() throws RemoteException;

    DisplayParams getDisplayParams() throws RemoteException;

    PerfRange getPerformanceRange(int i) throws RemoteException;

    int getPowerLevelState() throws RemoteException;

    int[] getSupportedDisplayRefreshRates() throws RemoteException;

    int getThermalStatus() throws RemoteException;

    void lockVrPerformance(int i, int i2) throws RemoteException;

    void releaseVrPerformance() throws RemoteException;

    int setDeviceProperty(String str, String str2) throws RemoteException;

    void setDisplayBrightness(int i) throws RemoteException;

    void setDisplayRefreshRate(int i) throws RemoteException;

    void setThreadPolicy(int i, int i2, int i3) throws RemoteException;

    public static class Default implements IVrApiService {
        @Override // oculus.internal.IVrApiService
        public void setThreadPolicy(int policy, int level, int threadId) throws RemoteException {
        }

        @Override // oculus.internal.IVrApiService
        public PerfRange getPerformanceRange(int clockDomain) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.IVrApiService
        public void lockVrPerformance(int cpuLevel, int gpuLevel) throws RemoteException {
        }

        @Override // oculus.internal.IVrApiService
        public void releaseVrPerformance() throws RemoteException {
        }

        @Override // oculus.internal.IVrApiService
        public String getDeviceProperty(String propertyName) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.IVrApiService
        public DisplayParams getDisplayParams() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.IVrApiService
        public int setDeviceProperty(String name, String value) throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.IVrApiService
        public int getDisplayBrightness() throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.IVrApiService
        public void setDisplayBrightness(int brightness) throws RemoteException {
        }

        @Override // oculus.internal.IVrApiService
        public void getClockFrequencies(int clockDomain, int coreId, int[] freqs) throws RemoteException {
        }

        @Override // oculus.internal.IVrApiService
        public int getPowerLevelState() throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.IVrApiService
        public int[] getSupportedDisplayRefreshRates() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.IVrApiService
        public void setDisplayRefreshRate(int refreshRate) throws RemoteException {
        }

        @Override // oculus.internal.IVrApiService
        public int getCurrentClockFrequency(int clockDomain, int coreId) throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.IVrApiService
        public int getCurrentClockPerfLevel(int clockDomain, int coreId) throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.IVrApiService
        public void allowUidHighPriorityContext(int uid) throws RemoteException {
        }

        @Override // oculus.internal.IVrApiService
        public void allowTidMaximumPriorityContext(int tid) throws RemoteException {
        }

        @Override // oculus.internal.IVrApiService
        public int getThermalStatus() throws RemoteException {
            return 0;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IVrApiService {
        private static final String DESCRIPTOR = "oculus.internal.IVrApiService";
        static final int TRANSACTION_allowTidMaximumPriorityContext = 17;
        static final int TRANSACTION_allowUidHighPriorityContext = 16;
        static final int TRANSACTION_getClockFrequencies = 10;
        static final int TRANSACTION_getCurrentClockFrequency = 14;
        static final int TRANSACTION_getCurrentClockPerfLevel = 15;
        static final int TRANSACTION_getDeviceProperty = 5;
        static final int TRANSACTION_getDisplayBrightness = 8;
        static final int TRANSACTION_getDisplayParams = 6;
        static final int TRANSACTION_getPerformanceRange = 2;
        static final int TRANSACTION_getPowerLevelState = 11;
        static final int TRANSACTION_getSupportedDisplayRefreshRates = 12;
        static final int TRANSACTION_getThermalStatus = 18;
        static final int TRANSACTION_lockVrPerformance = 3;
        static final int TRANSACTION_releaseVrPerformance = 4;
        static final int TRANSACTION_setDeviceProperty = 7;
        static final int TRANSACTION_setDisplayBrightness = 9;
        static final int TRANSACTION_setDisplayRefreshRate = 13;
        static final int TRANSACTION_setThreadPolicy = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVrApiService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IVrApiService)) {
                return new Proxy(obj);
            }
            return (IVrApiService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int[] _arg2;
            if (code != 1598968902) {
                switch (code) {
                    case 1:
                        data.enforceInterface(DESCRIPTOR);
                        setThreadPolicy(data.readInt(), data.readInt(), data.readInt());
                        reply.writeNoException();
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        PerfRange _result = getPerformanceRange(data.readInt());
                        reply.writeNoException();
                        if (_result != null) {
                            reply.writeInt(1);
                            _result.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        lockVrPerformance(data.readInt(), data.readInt());
                        reply.writeNoException();
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        releaseVrPerformance();
                        reply.writeNoException();
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        String _result2 = getDeviceProperty(data.readString());
                        reply.writeNoException();
                        reply.writeString(_result2);
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        DisplayParams _result3 = getDisplayParams();
                        reply.writeNoException();
                        if (_result3 != null) {
                            reply.writeInt(1);
                            _result3.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 7:
                        data.enforceInterface(DESCRIPTOR);
                        int _result4 = setDeviceProperty(data.readString(), data.readString());
                        reply.writeNoException();
                        reply.writeInt(_result4);
                        return true;
                    case 8:
                        data.enforceInterface(DESCRIPTOR);
                        int _result5 = getDisplayBrightness();
                        reply.writeNoException();
                        reply.writeInt(_result5);
                        return true;
                    case 9:
                        data.enforceInterface(DESCRIPTOR);
                        setDisplayBrightness(data.readInt());
                        reply.writeNoException();
                        return true;
                    case 10:
                        data.enforceInterface(DESCRIPTOR);
                        int _arg0 = data.readInt();
                        int _arg1 = data.readInt();
                        int _arg2_length = data.readInt();
                        if (_arg2_length < 0) {
                            _arg2 = null;
                        } else {
                            _arg2 = new int[_arg2_length];
                        }
                        getClockFrequencies(_arg0, _arg1, _arg2);
                        reply.writeNoException();
                        reply.writeIntArray(_arg2);
                        return true;
                    case 11:
                        data.enforceInterface(DESCRIPTOR);
                        int _result6 = getPowerLevelState();
                        reply.writeNoException();
                        reply.writeInt(_result6);
                        return true;
                    case 12:
                        data.enforceInterface(DESCRIPTOR);
                        int[] _result7 = getSupportedDisplayRefreshRates();
                        reply.writeNoException();
                        reply.writeIntArray(_result7);
                        return true;
                    case 13:
                        data.enforceInterface(DESCRIPTOR);
                        setDisplayRefreshRate(data.readInt());
                        reply.writeNoException();
                        return true;
                    case 14:
                        data.enforceInterface(DESCRIPTOR);
                        int _result8 = getCurrentClockFrequency(data.readInt(), data.readInt());
                        reply.writeNoException();
                        reply.writeInt(_result8);
                        return true;
                    case 15:
                        data.enforceInterface(DESCRIPTOR);
                        int _result9 = getCurrentClockPerfLevel(data.readInt(), data.readInt());
                        reply.writeNoException();
                        reply.writeInt(_result9);
                        return true;
                    case 16:
                        data.enforceInterface(DESCRIPTOR);
                        allowUidHighPriorityContext(data.readInt());
                        reply.writeNoException();
                        return true;
                    case 17:
                        data.enforceInterface(DESCRIPTOR);
                        allowTidMaximumPriorityContext(data.readInt());
                        reply.writeNoException();
                        return true;
                    case 18:
                        data.enforceInterface(DESCRIPTOR);
                        int _result10 = getThermalStatus();
                        reply.writeNoException();
                        reply.writeInt(_result10);
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
        public static class Proxy implements IVrApiService {
            public static IVrApiService sDefaultImpl;
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

            @Override // oculus.internal.IVrApiService
            public void setThreadPolicy(int policy, int level, int threadId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(policy);
                    _data.writeInt(level);
                    _data.writeInt(threadId);
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setThreadPolicy(policy, level, threadId);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVrApiService
            public PerfRange getPerformanceRange(int clockDomain) throws RemoteException {
                PerfRange _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clockDomain);
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPerformanceRange(clockDomain);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = PerfRange.CREATOR.createFromParcel(_reply);
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

            @Override // oculus.internal.IVrApiService
            public void lockVrPerformance(int cpuLevel, int gpuLevel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(cpuLevel);
                    _data.writeInt(gpuLevel);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().lockVrPerformance(cpuLevel, gpuLevel);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVrApiService
            public void releaseVrPerformance() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().releaseVrPerformance();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVrApiService
            public String getDeviceProperty(String propertyName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(propertyName);
                    if (!this.mRemote.transact(5, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDeviceProperty(propertyName);
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

            @Override // oculus.internal.IVrApiService
            public DisplayParams getDisplayParams() throws RemoteException {
                DisplayParams _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisplayParams();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = DisplayParams.CREATOR.createFromParcel(_reply);
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

            @Override // oculus.internal.IVrApiService
            public int setDeviceProperty(String name, String value) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(name);
                    _data.writeString(value);
                    if (!this.mRemote.transact(7, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().setDeviceProperty(name, value);
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

            @Override // oculus.internal.IVrApiService
            public int getDisplayBrightness() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDisplayBrightness();
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

            @Override // oculus.internal.IVrApiService
            public void setDisplayBrightness(int brightness) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(brightness);
                    if (this.mRemote.transact(9, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setDisplayBrightness(brightness);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVrApiService
            public void getClockFrequencies(int clockDomain, int coreId, int[] freqs) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clockDomain);
                    _data.writeInt(coreId);
                    if (freqs == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(freqs.length);
                    }
                    if (this.mRemote.transact(10, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.readIntArray(freqs);
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getClockFrequencies(clockDomain, coreId, freqs);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVrApiService
            public int getPowerLevelState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPowerLevelState();
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

            @Override // oculus.internal.IVrApiService
            public int[] getSupportedDisplayRefreshRates() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(12, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSupportedDisplayRefreshRates();
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

            @Override // oculus.internal.IVrApiService
            public void setDisplayRefreshRate(int refreshRate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(refreshRate);
                    if (this.mRemote.transact(13, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setDisplayRefreshRate(refreshRate);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVrApiService
            public int getCurrentClockFrequency(int clockDomain, int coreId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clockDomain);
                    _data.writeInt(coreId);
                    if (!this.mRemote.transact(14, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentClockFrequency(clockDomain, coreId);
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

            @Override // oculus.internal.IVrApiService
            public int getCurrentClockPerfLevel(int clockDomain, int coreId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(clockDomain);
                    _data.writeInt(coreId);
                    if (!this.mRemote.transact(15, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentClockPerfLevel(clockDomain, coreId);
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

            @Override // oculus.internal.IVrApiService
            public void allowUidHighPriorityContext(int uid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(uid);
                    if (this.mRemote.transact(16, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().allowUidHighPriorityContext(uid);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVrApiService
            public void allowTidMaximumPriorityContext(int tid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(tid);
                    if (this.mRemote.transact(17, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().allowTidMaximumPriorityContext(tid);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVrApiService
            public int getThermalStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(18, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getThermalStatus();
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
        }

        public static boolean setDefaultImpl(IVrApiService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IVrApiService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
