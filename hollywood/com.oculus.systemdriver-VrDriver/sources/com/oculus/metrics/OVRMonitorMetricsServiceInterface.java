package com.oculus.metrics;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface OVRMonitorMetricsServiceInterface extends IInterface {
    void updateMetrics(String str, long j, String str2) throws RemoteException;

    boolean updateMetrics2(String str, long j, String str2) throws RemoteException;

    void updateUtilization(String str, long j, float f, float f2) throws RemoteException;

    boolean updateUtilization2(String str, long j, float f, float f2) throws RemoteException;

    public static abstract class Stub extends Binder implements OVRMonitorMetricsServiceInterface {
        private static final String DESCRIPTOR = "com.oculus.metrics.OVRMonitorMetricsServiceInterface";
        static final int TRANSACTION_updateMetrics = 1;
        static final int TRANSACTION_updateMetrics2 = 3;
        static final int TRANSACTION_updateUtilization = 2;
        static final int TRANSACTION_updateUtilization2 = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static OVRMonitorMetricsServiceInterface asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof OVRMonitorMetricsServiceInterface)) {
                return new Proxy(obj);
            }
            return (OVRMonitorMetricsServiceInterface) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                updateMetrics(data.readString(), data.readLong(), data.readString());
                reply.writeNoException();
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                updateUtilization(data.readString(), data.readLong(), data.readFloat(), data.readFloat());
                reply.writeNoException();
                return true;
            } else if (code == 3) {
                data.enforceInterface(DESCRIPTOR);
                boolean updateMetrics2 = updateMetrics2(data.readString(), data.readLong(), data.readString());
                reply.writeNoException();
                reply.writeInt(updateMetrics2 ? 1 : 0);
                return true;
            } else if (code == TRANSACTION_updateUtilization2) {
                data.enforceInterface(DESCRIPTOR);
                boolean updateUtilization2 = updateUtilization2(data.readString(), data.readLong(), data.readFloat(), data.readFloat());
                reply.writeNoException();
                reply.writeInt(updateUtilization2 ? 1 : 0);
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        private static class Proxy implements OVRMonitorMetricsServiceInterface {
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

            @Override // com.oculus.metrics.OVRMonitorMetricsServiceInterface
            public void updateMetrics(String appName, long time, String metricsJson) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(appName);
                    _data.writeLong(time);
                    _data.writeString(metricsJson);
                    this.mRemote.transact(1, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.metrics.OVRMonitorMetricsServiceInterface
            public void updateUtilization(String appName, long time, float cpu, float gpu) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(appName);
                    _data.writeLong(time);
                    _data.writeFloat(cpu);
                    _data.writeFloat(gpu);
                    this.mRemote.transact(2, _data, _reply, 0);
                    _reply.readException();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.metrics.OVRMonitorMetricsServiceInterface
            public boolean updateMetrics2(String appName, long time, String metricsJson) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(appName);
                    _data.writeLong(time);
                    _data.writeString(metricsJson);
                    boolean _result = false;
                    this.mRemote.transact(3, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.metrics.OVRMonitorMetricsServiceInterface
            public boolean updateUtilization2(String appName, long time, float cpu, float gpu) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(appName);
                    _data.writeLong(time);
                    _data.writeFloat(cpu);
                    _data.writeFloat(gpu);
                    boolean _result = false;
                    this.mRemote.transact(Stub.TRANSACTION_updateUtilization2, _data, _reply, 0);
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = true;
                    }
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }
    }
}
