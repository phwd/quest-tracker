package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public interface IGpuProfilingServiceClient extends IInterface {
    void FreeTraceMemory() throws RemoteException;

    ParcelFileDescriptor GetDrawcallSharedMemoryFd() throws RemoteException;

    ParcelFileDescriptor GetRealtimeDataSharedMemoryFd() throws RemoteException;

    ParcelFileDescriptor GetSurfaceDataSharedMemoryFd() throws RemoteException;

    ParcelFileDescriptor GetSurfaceStageSharedMemoryFd() throws RemoteException;

    ParcelFileDescriptor GetTraceLockMemoryFd() throws RemoteException;

    int GetTraceLockMemorySize() throws RemoteException;

    boolean IsDetailedProfiling() throws RemoteException;

    int PollDrawcallTrace() throws RemoteException;

    int PollRenderStageTrace() throws RemoteException;

    boolean StartDetailedProfiling(String str) throws RemoteException;

    boolean StartDrawcallTrace(int[] iArr) throws RemoteException;

    boolean StartRealtimeMonitor(int[] iArr, int i) throws RemoteException;

    boolean StartRenderStageTrace() throws RemoteException;

    boolean StopDetailedProfiling() throws RemoteException;

    int StopDrawcallTrace() throws RemoteException;

    void StopRealtimeMonitor(int[] iArr) throws RemoteException;

    int StopRenderStageTrace() throws RemoteException;

    public static class Default implements IGpuProfilingServiceClient {
        @Override // oculus.internal.IGpuProfilingServiceClient
        public ParcelFileDescriptor GetRealtimeDataSharedMemoryFd() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.IGpuProfilingServiceClient
        public boolean StartRealtimeMonitor(int[] metricIds, int interval) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.IGpuProfilingServiceClient
        public void StopRealtimeMonitor(int[] metricIds) throws RemoteException {
        }

        @Override // oculus.internal.IGpuProfilingServiceClient
        public boolean StartDetailedProfiling(String processName) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.IGpuProfilingServiceClient
        public boolean IsDetailedProfiling() throws RemoteException {
            return false;
        }

        @Override // oculus.internal.IGpuProfilingServiceClient
        public boolean StopDetailedProfiling() throws RemoteException {
            return false;
        }

        @Override // oculus.internal.IGpuProfilingServiceClient
        public boolean StartRenderStageTrace() throws RemoteException {
            return false;
        }

        @Override // oculus.internal.IGpuProfilingServiceClient
        public int PollRenderStageTrace() throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.IGpuProfilingServiceClient
        public int StopRenderStageTrace() throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.IGpuProfilingServiceClient
        public ParcelFileDescriptor GetSurfaceDataSharedMemoryFd() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.IGpuProfilingServiceClient
        public ParcelFileDescriptor GetSurfaceStageSharedMemoryFd() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.IGpuProfilingServiceClient
        public boolean StartDrawcallTrace(int[] metricIds) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.IGpuProfilingServiceClient
        public int PollDrawcallTrace() throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.IGpuProfilingServiceClient
        public int StopDrawcallTrace() throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.IGpuProfilingServiceClient
        public ParcelFileDescriptor GetDrawcallSharedMemoryFd() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.IGpuProfilingServiceClient
        public void FreeTraceMemory() throws RemoteException {
        }

        @Override // oculus.internal.IGpuProfilingServiceClient
        public ParcelFileDescriptor GetTraceLockMemoryFd() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.IGpuProfilingServiceClient
        public int GetTraceLockMemorySize() throws RemoteException {
            return 0;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IGpuProfilingServiceClient {
        private static final String DESCRIPTOR = "oculus.internal.IGpuProfilingServiceClient";
        static final int TRANSACTION_FreeTraceMemory = 16;
        static final int TRANSACTION_GetDrawcallSharedMemoryFd = 15;
        static final int TRANSACTION_GetRealtimeDataSharedMemoryFd = 1;
        static final int TRANSACTION_GetSurfaceDataSharedMemoryFd = 10;
        static final int TRANSACTION_GetSurfaceStageSharedMemoryFd = 11;
        static final int TRANSACTION_GetTraceLockMemoryFd = 17;
        static final int TRANSACTION_GetTraceLockMemorySize = 18;
        static final int TRANSACTION_IsDetailedProfiling = 5;
        static final int TRANSACTION_PollDrawcallTrace = 13;
        static final int TRANSACTION_PollRenderStageTrace = 8;
        static final int TRANSACTION_StartDetailedProfiling = 4;
        static final int TRANSACTION_StartDrawcallTrace = 12;
        static final int TRANSACTION_StartRealtimeMonitor = 2;
        static final int TRANSACTION_StartRenderStageTrace = 7;
        static final int TRANSACTION_StopDetailedProfiling = 6;
        static final int TRANSACTION_StopDrawcallTrace = 14;
        static final int TRANSACTION_StopRealtimeMonitor = 3;
        static final int TRANSACTION_StopRenderStageTrace = 9;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGpuProfilingServiceClient asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IGpuProfilingServiceClient)) {
                return new Proxy(obj);
            }
            return (IGpuProfilingServiceClient) iin;
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
                        ParcelFileDescriptor _result = GetRealtimeDataSharedMemoryFd();
                        reply.writeNoException();
                        if (_result != null) {
                            reply.writeInt(1);
                            _result.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        boolean StartRealtimeMonitor = StartRealtimeMonitor(data.createIntArray(), data.readInt());
                        reply.writeNoException();
                        reply.writeInt(StartRealtimeMonitor ? 1 : 0);
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        StopRealtimeMonitor(data.createIntArray());
                        reply.writeNoException();
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        boolean StartDetailedProfiling = StartDetailedProfiling(data.readString());
                        reply.writeNoException();
                        reply.writeInt(StartDetailedProfiling ? 1 : 0);
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        boolean IsDetailedProfiling = IsDetailedProfiling();
                        reply.writeNoException();
                        reply.writeInt(IsDetailedProfiling ? 1 : 0);
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        boolean StopDetailedProfiling = StopDetailedProfiling();
                        reply.writeNoException();
                        reply.writeInt(StopDetailedProfiling ? 1 : 0);
                        return true;
                    case 7:
                        data.enforceInterface(DESCRIPTOR);
                        boolean StartRenderStageTrace = StartRenderStageTrace();
                        reply.writeNoException();
                        reply.writeInt(StartRenderStageTrace ? 1 : 0);
                        return true;
                    case 8:
                        data.enforceInterface(DESCRIPTOR);
                        int _result2 = PollRenderStageTrace();
                        reply.writeNoException();
                        reply.writeInt(_result2);
                        return true;
                    case 9:
                        data.enforceInterface(DESCRIPTOR);
                        int _result3 = StopRenderStageTrace();
                        reply.writeNoException();
                        reply.writeInt(_result3);
                        return true;
                    case 10:
                        data.enforceInterface(DESCRIPTOR);
                        ParcelFileDescriptor _result4 = GetSurfaceDataSharedMemoryFd();
                        reply.writeNoException();
                        if (_result4 != null) {
                            reply.writeInt(1);
                            _result4.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 11:
                        data.enforceInterface(DESCRIPTOR);
                        ParcelFileDescriptor _result5 = GetSurfaceStageSharedMemoryFd();
                        reply.writeNoException();
                        if (_result5 != null) {
                            reply.writeInt(1);
                            _result5.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 12:
                        data.enforceInterface(DESCRIPTOR);
                        boolean StartDrawcallTrace = StartDrawcallTrace(data.createIntArray());
                        reply.writeNoException();
                        reply.writeInt(StartDrawcallTrace ? 1 : 0);
                        return true;
                    case 13:
                        data.enforceInterface(DESCRIPTOR);
                        int _result6 = PollDrawcallTrace();
                        reply.writeNoException();
                        reply.writeInt(_result6);
                        return true;
                    case 14:
                        data.enforceInterface(DESCRIPTOR);
                        int _result7 = StopDrawcallTrace();
                        reply.writeNoException();
                        reply.writeInt(_result7);
                        return true;
                    case 15:
                        data.enforceInterface(DESCRIPTOR);
                        ParcelFileDescriptor _result8 = GetDrawcallSharedMemoryFd();
                        reply.writeNoException();
                        if (_result8 != null) {
                            reply.writeInt(1);
                            _result8.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 16:
                        data.enforceInterface(DESCRIPTOR);
                        FreeTraceMemory();
                        reply.writeNoException();
                        return true;
                    case 17:
                        data.enforceInterface(DESCRIPTOR);
                        ParcelFileDescriptor _result9 = GetTraceLockMemoryFd();
                        reply.writeNoException();
                        if (_result9 != null) {
                            reply.writeInt(1);
                            _result9.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 18:
                        data.enforceInterface(DESCRIPTOR);
                        int _result10 = GetTraceLockMemorySize();
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
        public static class Proxy implements IGpuProfilingServiceClient {
            public static IGpuProfilingServiceClient sDefaultImpl;
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

            @Override // oculus.internal.IGpuProfilingServiceClient
            public ParcelFileDescriptor GetRealtimeDataSharedMemoryFd() throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().GetRealtimeDataSharedMemoryFd();
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

            @Override // oculus.internal.IGpuProfilingServiceClient
            public boolean StartRealtimeMonitor(int[] metricIds, int interval) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(metricIds);
                    _data.writeInt(interval);
                    boolean _result = false;
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().StartRealtimeMonitor(metricIds, interval);
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

            @Override // oculus.internal.IGpuProfilingServiceClient
            public void StopRealtimeMonitor(int[] metricIds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(metricIds);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().StopRealtimeMonitor(metricIds);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IGpuProfilingServiceClient
            public boolean StartDetailedProfiling(String processName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(processName);
                    boolean _result = false;
                    if (!this.mRemote.transact(4, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().StartDetailedProfiling(processName);
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

            @Override // oculus.internal.IGpuProfilingServiceClient
            public boolean IsDetailedProfiling() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    if (!this.mRemote.transact(5, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().IsDetailedProfiling();
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

            @Override // oculus.internal.IGpuProfilingServiceClient
            public boolean StopDetailedProfiling() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    if (!this.mRemote.transact(6, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().StopDetailedProfiling();
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

            @Override // oculus.internal.IGpuProfilingServiceClient
            public boolean StartRenderStageTrace() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    if (!this.mRemote.transact(7, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().StartRenderStageTrace();
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

            @Override // oculus.internal.IGpuProfilingServiceClient
            public int PollRenderStageTrace() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().PollRenderStageTrace();
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

            @Override // oculus.internal.IGpuProfilingServiceClient
            public int StopRenderStageTrace() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().StopRenderStageTrace();
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

            @Override // oculus.internal.IGpuProfilingServiceClient
            public ParcelFileDescriptor GetSurfaceDataSharedMemoryFd() throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(10, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().GetSurfaceDataSharedMemoryFd();
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

            @Override // oculus.internal.IGpuProfilingServiceClient
            public ParcelFileDescriptor GetSurfaceStageSharedMemoryFd() throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().GetSurfaceStageSharedMemoryFd();
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

            @Override // oculus.internal.IGpuProfilingServiceClient
            public boolean StartDrawcallTrace(int[] metricIds) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeIntArray(metricIds);
                    boolean _result = false;
                    if (!this.mRemote.transact(12, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().StartDrawcallTrace(metricIds);
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

            @Override // oculus.internal.IGpuProfilingServiceClient
            public int PollDrawcallTrace() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(13, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().PollDrawcallTrace();
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

            @Override // oculus.internal.IGpuProfilingServiceClient
            public int StopDrawcallTrace() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(14, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().StopDrawcallTrace();
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

            @Override // oculus.internal.IGpuProfilingServiceClient
            public ParcelFileDescriptor GetDrawcallSharedMemoryFd() throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(15, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().GetDrawcallSharedMemoryFd();
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

            @Override // oculus.internal.IGpuProfilingServiceClient
            public void FreeTraceMemory() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(16, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().FreeTraceMemory();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IGpuProfilingServiceClient
            public ParcelFileDescriptor GetTraceLockMemoryFd() throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(17, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().GetTraceLockMemoryFd();
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

            @Override // oculus.internal.IGpuProfilingServiceClient
            public int GetTraceLockMemorySize() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(18, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().GetTraceLockMemorySize();
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

        public static boolean setDefaultImpl(IGpuProfilingServiceClient impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IGpuProfilingServiceClient getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
