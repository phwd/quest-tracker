package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.ArrayList;
import java.util.List;
import oculus.internal.IGpuProfilingServiceClient;

public interface IGpuProfilingService extends IInterface {
    void GetAvailableDrawcallMetrics(int[] iArr, List<String> list, List<String> list2) throws RemoteException;

    void GetAvailableRealtimeMetrics(int[] iArr, List<String> list, List<String> list2) throws RemoteException;

    void GetSharedMemoryIndex(int[] iArr, int[] iArr2) throws RemoteException;

    IGpuProfilingServiceClient createClient() throws RemoteException;

    public static class Default implements IGpuProfilingService {
        @Override // oculus.internal.IGpuProfilingService
        public IGpuProfilingServiceClient createClient() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.IGpuProfilingService
        public void GetAvailableRealtimeMetrics(int[] metricIds, List<String> list, List<String> list2) throws RemoteException {
        }

        @Override // oculus.internal.IGpuProfilingService
        public void GetAvailableDrawcallMetrics(int[] metricIds, List<String> list, List<String> list2) throws RemoteException {
        }

        @Override // oculus.internal.IGpuProfilingService
        public void GetSharedMemoryIndex(int[] metricIds, int[] index) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IGpuProfilingService {
        private static final String DESCRIPTOR = "oculus.internal.IGpuProfilingService";
        static final int TRANSACTION_GetAvailableDrawcallMetrics = 3;
        static final int TRANSACTION_GetAvailableRealtimeMetrics = 2;
        static final int TRANSACTION_GetSharedMemoryIndex = 4;
        static final int TRANSACTION_createClient = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGpuProfilingService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IGpuProfilingService)) {
                return new Proxy(obj);
            }
            return (IGpuProfilingService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            int[] _arg0;
            int[] _arg02;
            int[] _arg03;
            int[] _arg1;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                IGpuProfilingServiceClient _result = createClient();
                reply.writeNoException();
                reply.writeStrongBinder(_result != null ? _result.asBinder() : null);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _arg0_length = data.readInt();
                if (_arg0_length < 0) {
                    _arg0 = null;
                } else {
                    _arg0 = new int[_arg0_length];
                }
                List<String> _arg12 = new ArrayList<>();
                List<String> _arg2 = new ArrayList<>();
                GetAvailableRealtimeMetrics(_arg0, _arg12, _arg2);
                reply.writeNoException();
                reply.writeIntArray(_arg0);
                reply.writeStringList(_arg12);
                reply.writeStringList(_arg2);
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                int _arg0_length2 = data.readInt();
                if (_arg0_length2 < 0) {
                    _arg02 = null;
                } else {
                    _arg02 = new int[_arg0_length2];
                }
                List<String> _arg13 = new ArrayList<>();
                List<String> _arg22 = new ArrayList<>();
                GetAvailableDrawcallMetrics(_arg02, _arg13, _arg22);
                reply.writeNoException();
                reply.writeIntArray(_arg02);
                reply.writeStringList(_arg13);
                reply.writeStringList(_arg22);
                return true;
            } else if (code == 4) {
                data.enforceInterface(DESCRIPTOR);
                int _arg0_length3 = data.readInt();
                if (_arg0_length3 < 0) {
                    _arg03 = null;
                } else {
                    _arg03 = new int[_arg0_length3];
                }
                int _arg1_length = data.readInt();
                if (_arg1_length < 0) {
                    _arg1 = null;
                } else {
                    _arg1 = new int[_arg1_length];
                }
                GetSharedMemoryIndex(_arg03, _arg1);
                reply.writeNoException();
                reply.writeIntArray(_arg03);
                reply.writeIntArray(_arg1);
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IGpuProfilingService {
            public static IGpuProfilingService sDefaultImpl;
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

            @Override // oculus.internal.IGpuProfilingService
            public IGpuProfilingServiceClient createClient() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().createClient();
                    }
                    _reply.readException();
                    IGpuProfilingServiceClient _result = IGpuProfilingServiceClient.Stub.asInterface(_reply.readStrongBinder());
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IGpuProfilingService
            public void GetAvailableRealtimeMetrics(int[] metricIds, List<String> metricName, List<String> metricDescription) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (metricIds == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(metricIds.length);
                    }
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.readIntArray(metricIds);
                        _reply.readStringList(metricName);
                        _reply.readStringList(metricDescription);
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().GetAvailableRealtimeMetrics(metricIds, metricName, metricDescription);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IGpuProfilingService
            public void GetAvailableDrawcallMetrics(int[] metricIds, List<String> metricName, List<String> metricDescription) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (metricIds == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(metricIds.length);
                    }
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.readIntArray(metricIds);
                        _reply.readStringList(metricName);
                        _reply.readStringList(metricDescription);
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().GetAvailableDrawcallMetrics(metricIds, metricName, metricDescription);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IGpuProfilingService
            public void GetSharedMemoryIndex(int[] metricIds, int[] index) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (metricIds == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(metricIds.length);
                    }
                    if (index == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(index.length);
                    }
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.readIntArray(metricIds);
                        _reply.readIntArray(index);
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().GetSharedMemoryIndex(metricIds, index);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IGpuProfilingService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IGpuProfilingService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
