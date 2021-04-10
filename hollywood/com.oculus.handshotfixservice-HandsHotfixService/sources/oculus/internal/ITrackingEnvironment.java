package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;

public interface ITrackingEnvironment extends IInterface {
    void deregisterAnchor(ParcelAnchorHandle parcelAnchorHandle) throws RemoteException;

    long exportMapDataForAnchor(ParcelAnchorUuid parcelAnchorUuid, ParcelFileDescriptor parcelFileDescriptor) throws RemoteException;

    ParcelFileDescriptor getAnchorSharedMemory() throws RemoteException;

    ParcelAnchorUuid getAnchorUuid(ParcelAnchorHandle parcelAnchorHandle) throws RemoteException;

    String getCurrentMapUUID() throws RemoteException;

    String[] getDebugInfo() throws RemoteException;

    ParcelFileDescriptor getSharedMemory() throws RemoteException;

    long importMapDataForAnchor(ParcelAnchorUuid parcelAnchorUuid, ParcelFileDescriptor parcelFileDescriptor, long j) throws RemoteException;

    boolean isInOrientationOnlyMode() throws RemoteException;

    boolean isLocalAnchor(ParcelAnchorUuid parcelAnchorUuid) throws RemoteException;

    boolean keepMap(String str) throws RemoteException;

    String[] listMaps() throws RemoteException;

    boolean loadMap(String str, double d) throws RemoteException;

    boolean locateAnchor(ParcelAnchorUuid parcelAnchorUuid, double d) throws RemoteException;

    ParcelAnchorUuid persistAnchor(ParcelAnchorHandle parcelAnchorHandle) throws RemoteException;

    ParcelAnchorPlacementData placeAnchor() throws RemoteException;

    long readMap(ParcelFileDescriptor parcelFileDescriptor, String str) throws RemoteException;

    ParcelAnchorHandle registerAnchor(ParcelAnchorUuid parcelAnchorUuid) throws RemoteException;

    boolean removeAllMaps() throws RemoteException;

    void setControllerMotionModelSettings(boolean z) throws RemoteException;

    void setOrientationOnlyMode(boolean z) throws RemoteException;

    void stopMapExpansion(boolean z) throws RemoteException;

    boolean writeMap(ParcelFileDescriptor parcelFileDescriptor, String str, long j) throws RemoteException;

    public static class Default implements ITrackingEnvironment {
        @Override // oculus.internal.ITrackingEnvironment
        public ParcelFileDescriptor getSharedMemory() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public ParcelFileDescriptor getAnchorSharedMemory() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public boolean writeMap(ParcelFileDescriptor pipeReadEnd, String mapId, long fileSize) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public long readMap(ParcelFileDescriptor pipeWriteEnd, String mapId) throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public String getCurrentMapUUID() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public boolean keepMap(String mapId) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public boolean removeAllMaps() throws RemoteException {
            return false;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public boolean loadMap(String mapId, double timeOut) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public String[] listMaps() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public void stopMapExpansion(boolean mode) throws RemoteException {
        }

        @Override // oculus.internal.ITrackingEnvironment
        public ParcelAnchorPlacementData placeAnchor() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public ParcelAnchorHandle registerAnchor(ParcelAnchorUuid uuid) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public void deregisterAnchor(ParcelAnchorHandle handle) throws RemoteException {
        }

        @Override // oculus.internal.ITrackingEnvironment
        public ParcelAnchorUuid persistAnchor(ParcelAnchorHandle handle) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public boolean locateAnchor(ParcelAnchorUuid uuid, double timeoutSec) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public boolean isLocalAnchor(ParcelAnchorUuid uuid) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public ParcelAnchorUuid getAnchorUuid(ParcelAnchorHandle handle) throws RemoteException {
            return null;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public long exportMapDataForAnchor(ParcelAnchorUuid uuid, ParcelFileDescriptor pipeWriteEnd) throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public long importMapDataForAnchor(ParcelAnchorUuid uuid, ParcelFileDescriptor pfd, long mapSize) throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public String[] getDebugInfo() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public void setOrientationOnlyMode(boolean mode) throws RemoteException {
        }

        @Override // oculus.internal.ITrackingEnvironment
        public boolean isInOrientationOnlyMode() throws RemoteException {
            return false;
        }

        @Override // oculus.internal.ITrackingEnvironment
        public void setControllerMotionModelSettings(boolean enableHeuristics) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements ITrackingEnvironment {
        private static final String DESCRIPTOR = "oculus.internal.ITrackingEnvironment";
        static final int TRANSACTION_deregisterAnchor = 13;
        static final int TRANSACTION_exportMapDataForAnchor = 18;
        static final int TRANSACTION_getAnchorSharedMemory = 2;
        static final int TRANSACTION_getAnchorUuid = 17;
        static final int TRANSACTION_getCurrentMapUUID = 5;
        static final int TRANSACTION_getDebugInfo = 20;
        static final int TRANSACTION_getSharedMemory = 1;
        static final int TRANSACTION_importMapDataForAnchor = 19;
        static final int TRANSACTION_isInOrientationOnlyMode = 22;
        static final int TRANSACTION_isLocalAnchor = 16;
        static final int TRANSACTION_keepMap = 6;
        static final int TRANSACTION_listMaps = 9;
        static final int TRANSACTION_loadMap = 8;
        static final int TRANSACTION_locateAnchor = 15;
        static final int TRANSACTION_persistAnchor = 14;
        static final int TRANSACTION_placeAnchor = 11;
        static final int TRANSACTION_readMap = 4;
        static final int TRANSACTION_registerAnchor = 12;
        static final int TRANSACTION_removeAllMaps = 7;
        static final int TRANSACTION_setControllerMotionModelSettings = 23;
        static final int TRANSACTION_setOrientationOnlyMode = 21;
        static final int TRANSACTION_stopMapExpansion = 10;
        static final int TRANSACTION_writeMap = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ITrackingEnvironment asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof ITrackingEnvironment)) {
                return new Proxy(obj);
            }
            return (ITrackingEnvironment) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            ParcelFileDescriptor _arg0;
            ParcelFileDescriptor _arg02;
            ParcelAnchorUuid _arg03;
            ParcelAnchorHandle _arg04;
            ParcelAnchorHandle _arg05;
            ParcelAnchorUuid _arg06;
            ParcelAnchorUuid _arg07;
            ParcelAnchorHandle _arg08;
            ParcelAnchorUuid _arg09;
            ParcelFileDescriptor _arg1;
            ParcelAnchorUuid _arg010;
            ParcelFileDescriptor _arg12;
            if (code != 1598968902) {
                boolean _arg011 = false;
                switch (code) {
                    case 1:
                        data.enforceInterface(DESCRIPTOR);
                        ParcelFileDescriptor _result = getSharedMemory();
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
                        ParcelFileDescriptor _result2 = getAnchorSharedMemory();
                        reply.writeNoException();
                        if (_result2 != null) {
                            reply.writeInt(1);
                            _result2.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg0 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(data);
                        } else {
                            _arg0 = null;
                        }
                        boolean writeMap = writeMap(_arg0, data.readString(), data.readLong());
                        reply.writeNoException();
                        reply.writeInt(writeMap ? 1 : 0);
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg02 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(data);
                        } else {
                            _arg02 = null;
                        }
                        long _result3 = readMap(_arg02, data.readString());
                        reply.writeNoException();
                        reply.writeLong(_result3);
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        String _result4 = getCurrentMapUUID();
                        reply.writeNoException();
                        reply.writeString(_result4);
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        boolean keepMap = keepMap(data.readString());
                        reply.writeNoException();
                        reply.writeInt(keepMap ? 1 : 0);
                        return true;
                    case 7:
                        data.enforceInterface(DESCRIPTOR);
                        boolean removeAllMaps = removeAllMaps();
                        reply.writeNoException();
                        reply.writeInt(removeAllMaps ? 1 : 0);
                        return true;
                    case 8:
                        data.enforceInterface(DESCRIPTOR);
                        boolean loadMap = loadMap(data.readString(), data.readDouble());
                        reply.writeNoException();
                        reply.writeInt(loadMap ? 1 : 0);
                        return true;
                    case 9:
                        data.enforceInterface(DESCRIPTOR);
                        String[] _result5 = listMaps();
                        reply.writeNoException();
                        reply.writeStringArray(_result5);
                        return true;
                    case 10:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg011 = true;
                        }
                        stopMapExpansion(_arg011);
                        reply.writeNoException();
                        return true;
                    case 11:
                        data.enforceInterface(DESCRIPTOR);
                        ParcelAnchorPlacementData _result6 = placeAnchor();
                        reply.writeNoException();
                        if (_result6 != null) {
                            reply.writeInt(1);
                            _result6.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 12:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg03 = ParcelAnchorUuid.CREATOR.createFromParcel(data);
                        } else {
                            _arg03 = null;
                        }
                        ParcelAnchorHandle _result7 = registerAnchor(_arg03);
                        reply.writeNoException();
                        if (_result7 != null) {
                            reply.writeInt(1);
                            _result7.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 13:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg04 = ParcelAnchorHandle.CREATOR.createFromParcel(data);
                        } else {
                            _arg04 = null;
                        }
                        deregisterAnchor(_arg04);
                        reply.writeNoException();
                        return true;
                    case 14:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg05 = ParcelAnchorHandle.CREATOR.createFromParcel(data);
                        } else {
                            _arg05 = null;
                        }
                        ParcelAnchorUuid _result8 = persistAnchor(_arg05);
                        reply.writeNoException();
                        if (_result8 != null) {
                            reply.writeInt(1);
                            _result8.writeToParcel(reply, 1);
                        } else {
                            reply.writeInt(0);
                        }
                        return true;
                    case 15:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg06 = ParcelAnchorUuid.CREATOR.createFromParcel(data);
                        } else {
                            _arg06 = null;
                        }
                        boolean locateAnchor = locateAnchor(_arg06, data.readDouble());
                        reply.writeNoException();
                        reply.writeInt(locateAnchor ? 1 : 0);
                        return true;
                    case 16:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg07 = ParcelAnchorUuid.CREATOR.createFromParcel(data);
                        } else {
                            _arg07 = null;
                        }
                        boolean isLocalAnchor = isLocalAnchor(_arg07);
                        reply.writeNoException();
                        reply.writeInt(isLocalAnchor ? 1 : 0);
                        return true;
                    case 17:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg08 = ParcelAnchorHandle.CREATOR.createFromParcel(data);
                        } else {
                            _arg08 = null;
                        }
                        ParcelAnchorUuid _result9 = getAnchorUuid(_arg08);
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
                        if (data.readInt() != 0) {
                            _arg09 = ParcelAnchorUuid.CREATOR.createFromParcel(data);
                        } else {
                            _arg09 = null;
                        }
                        if (data.readInt() != 0) {
                            _arg1 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(data);
                        } else {
                            _arg1 = null;
                        }
                        long _result10 = exportMapDataForAnchor(_arg09, _arg1);
                        reply.writeNoException();
                        reply.writeLong(_result10);
                        return true;
                    case 19:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg010 = ParcelAnchorUuid.CREATOR.createFromParcel(data);
                        } else {
                            _arg010 = null;
                        }
                        if (data.readInt() != 0) {
                            _arg12 = (ParcelFileDescriptor) ParcelFileDescriptor.CREATOR.createFromParcel(data);
                        } else {
                            _arg12 = null;
                        }
                        long _result11 = importMapDataForAnchor(_arg010, _arg12, data.readLong());
                        reply.writeNoException();
                        reply.writeLong(_result11);
                        return true;
                    case 20:
                        data.enforceInterface(DESCRIPTOR);
                        String[] _result12 = getDebugInfo();
                        reply.writeNoException();
                        reply.writeStringArray(_result12);
                        return true;
                    case 21:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg011 = true;
                        }
                        setOrientationOnlyMode(_arg011);
                        reply.writeNoException();
                        return true;
                    case 22:
                        data.enforceInterface(DESCRIPTOR);
                        boolean isInOrientationOnlyMode = isInOrientationOnlyMode();
                        reply.writeNoException();
                        reply.writeInt(isInOrientationOnlyMode ? 1 : 0);
                        return true;
                    case 23:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg011 = true;
                        }
                        setControllerMotionModelSettings(_arg011);
                        reply.writeNoException();
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
        public static class Proxy implements ITrackingEnvironment {
            public static ITrackingEnvironment sDefaultImpl;
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

            @Override // oculus.internal.ITrackingEnvironment
            public ParcelFileDescriptor getSharedMemory() throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSharedMemory();
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

            @Override // oculus.internal.ITrackingEnvironment
            public ParcelFileDescriptor getAnchorSharedMemory() throws RemoteException {
                ParcelFileDescriptor _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAnchorSharedMemory();
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

            @Override // oculus.internal.ITrackingEnvironment
            public boolean writeMap(ParcelFileDescriptor pipeReadEnd, String mapId, long fileSize) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (pipeReadEnd != null) {
                        _data.writeInt(1);
                        pipeReadEnd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(mapId);
                    _data.writeLong(fileSize);
                    if (!this.mRemote.transact(3, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().writeMap(pipeReadEnd, mapId, fileSize);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ITrackingEnvironment
            public long readMap(ParcelFileDescriptor pipeWriteEnd, String mapId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (pipeWriteEnd != null) {
                        _data.writeInt(1);
                        pipeWriteEnd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeString(mapId);
                    if (!this.mRemote.transact(4, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().readMap(pipeWriteEnd, mapId);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ITrackingEnvironment
            public String getCurrentMapUUID() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentMapUUID();
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

            @Override // oculus.internal.ITrackingEnvironment
            public boolean keepMap(String mapId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(mapId);
                    boolean _result = false;
                    if (!this.mRemote.transact(6, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().keepMap(mapId);
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

            @Override // oculus.internal.ITrackingEnvironment
            public boolean removeAllMaps() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    if (!this.mRemote.transact(7, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeAllMaps();
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

            @Override // oculus.internal.ITrackingEnvironment
            public boolean loadMap(String mapId, double timeOut) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(mapId);
                    _data.writeDouble(timeOut);
                    boolean _result = false;
                    if (!this.mRemote.transact(8, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().loadMap(mapId, timeOut);
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

            @Override // oculus.internal.ITrackingEnvironment
            public String[] listMaps() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listMaps();
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ITrackingEnvironment
            public void stopMapExpansion(boolean mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode ? 1 : 0);
                    if (this.mRemote.transact(10, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().stopMapExpansion(mode);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ITrackingEnvironment
            public ParcelAnchorPlacementData placeAnchor() throws RemoteException {
                ParcelAnchorPlacementData _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().placeAnchor();
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParcelAnchorPlacementData.CREATOR.createFromParcel(_reply);
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

            @Override // oculus.internal.ITrackingEnvironment
            public ParcelAnchorHandle registerAnchor(ParcelAnchorUuid uuid) throws RemoteException {
                ParcelAnchorHandle _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uuid != null) {
                        _data.writeInt(1);
                        uuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(12, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerAnchor(uuid);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParcelAnchorHandle.CREATOR.createFromParcel(_reply);
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

            @Override // oculus.internal.ITrackingEnvironment
            public void deregisterAnchor(ParcelAnchorHandle handle) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (handle != null) {
                        _data.writeInt(1);
                        handle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (this.mRemote.transact(13, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().deregisterAnchor(handle);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ITrackingEnvironment
            public ParcelAnchorUuid persistAnchor(ParcelAnchorHandle handle) throws RemoteException {
                ParcelAnchorUuid _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (handle != null) {
                        _data.writeInt(1);
                        handle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(14, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().persistAnchor(handle);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParcelAnchorUuid.CREATOR.createFromParcel(_reply);
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

            @Override // oculus.internal.ITrackingEnvironment
            public boolean locateAnchor(ParcelAnchorUuid uuid, double timeoutSec) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (uuid != null) {
                        _data.writeInt(1);
                        uuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeDouble(timeoutSec);
                    if (!this.mRemote.transact(15, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().locateAnchor(uuid, timeoutSec);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ITrackingEnvironment
            public boolean isLocalAnchor(ParcelAnchorUuid uuid) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = true;
                    if (uuid != null) {
                        _data.writeInt(1);
                        uuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(16, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLocalAnchor(uuid);
                    }
                    _reply.readException();
                    if (_reply.readInt() == 0) {
                        _result = false;
                    }
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ITrackingEnvironment
            public ParcelAnchorUuid getAnchorUuid(ParcelAnchorHandle handle) throws RemoteException {
                ParcelAnchorUuid _result;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (handle != null) {
                        _data.writeInt(1);
                        handle.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(17, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAnchorUuid(handle);
                    }
                    _reply.readException();
                    if (_reply.readInt() != 0) {
                        _result = ParcelAnchorUuid.CREATOR.createFromParcel(_reply);
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

            @Override // oculus.internal.ITrackingEnvironment
            public long exportMapDataForAnchor(ParcelAnchorUuid uuid, ParcelFileDescriptor pipeWriteEnd) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uuid != null) {
                        _data.writeInt(1);
                        uuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (pipeWriteEnd != null) {
                        _data.writeInt(1);
                        pipeWriteEnd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!this.mRemote.transact(18, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().exportMapDataForAnchor(uuid, pipeWriteEnd);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ITrackingEnvironment
            public long importMapDataForAnchor(ParcelAnchorUuid uuid, ParcelFileDescriptor pfd, long mapSize) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (uuid != null) {
                        _data.writeInt(1);
                        uuid.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (pfd != null) {
                        _data.writeInt(1);
                        pfd.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    _data.writeLong(mapSize);
                    if (!this.mRemote.transact(19, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().importMapDataForAnchor(uuid, pfd, mapSize);
                    }
                    _reply.readException();
                    long _result = _reply.readLong();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ITrackingEnvironment
            public String[] getDebugInfo() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(20, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDebugInfo();
                    }
                    _reply.readException();
                    String[] _result = _reply.createStringArray();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ITrackingEnvironment
            public void setOrientationOnlyMode(boolean mode) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(mode ? 1 : 0);
                    if (this.mRemote.transact(21, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setOrientationOnlyMode(mode);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.ITrackingEnvironment
            public boolean isInOrientationOnlyMode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    boolean _result = false;
                    if (!this.mRemote.transact(22, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isInOrientationOnlyMode();
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

            @Override // oculus.internal.ITrackingEnvironment
            public void setControllerMotionModelSettings(boolean enableHeuristics) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enableHeuristics ? 1 : 0);
                    if (this.mRemote.transact(23, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setControllerMotionModelSettings(enableHeuristics);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ITrackingEnvironment impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static ITrackingEnvironment getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
