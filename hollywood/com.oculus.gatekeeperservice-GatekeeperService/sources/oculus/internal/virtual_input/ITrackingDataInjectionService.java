package oculus.internal.virtual_input;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ITrackingDataInjectionService extends IInterface {
    public static final int IMU_FROM_CENTER_EYE_TRANSLATION = 3;
    public static final int RECENTERED_FROM_WORLD_TRANSLATION = 2;
    public static final int WORLD_FROM_IMU_ROTATION = 1;
    public static final int WORLD_FROM_IMU_TRANSLATION = 0;

    boolean updateHeadsetPoseField(int i, float[] fArr) throws RemoteException;

    boolean updateRemotePoseField(String str, int i, float[] fArr) throws RemoteException;

    public static class Default implements ITrackingDataInjectionService {
        @Override // oculus.internal.virtual_input.ITrackingDataInjectionService
        public boolean updateRemotePoseField(String deviceId, int poseField, float[] poseFieldValue) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.virtual_input.ITrackingDataInjectionService
        public boolean updateHeadsetPoseField(int poseField, float[] poseFieldValue) throws RemoteException {
            return false;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ITrackingDataInjectionService {
        private static final String DESCRIPTOR = "oculus.internal.virtual_input.ITrackingDataInjectionService";
        static final int TRANSACTION_updateHeadsetPoseField = 2;
        static final int TRANSACTION_updateRemotePoseField = 1;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITrackingDataInjectionService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITrackingDataInjectionService)) {
                return new Proxy(obj);
            }
            return (ITrackingDataInjectionService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                boolean updateRemotePoseField = updateRemotePoseField(data.readString(), data.readInt(), data.createFloatArray());
                reply.writeNoException();
                reply.writeInt(updateRemotePoseField ? 1 : 0);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                boolean updateHeadsetPoseField = updateHeadsetPoseField(data.readInt(), data.createFloatArray());
                reply.writeNoException();
                reply.writeInt(updateHeadsetPoseField ? 1 : 0);
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements ITrackingDataInjectionService {
            public static ITrackingDataInjectionService sDefaultImpl;
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

            @Override // oculus.internal.virtual_input.ITrackingDataInjectionService
            public boolean updateRemotePoseField(String deviceId, int poseField, float[] poseFieldValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(deviceId);
                    _data.writeInt(poseField);
                    _data.writeFloatArray(poseFieldValue);
                    boolean _result = false;
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateRemotePoseField(deviceId, poseField, poseFieldValue);
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

            @Override // oculus.internal.virtual_input.ITrackingDataInjectionService
            public boolean updateHeadsetPoseField(int poseField, float[] poseFieldValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(poseField);
                    _data.writeFloatArray(poseFieldValue);
                    boolean _result = false;
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateHeadsetPoseField(poseField, poseFieldValue);
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

        public static boolean setDefaultImpl(ITrackingDataInjectionService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ITrackingDataInjectionService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
