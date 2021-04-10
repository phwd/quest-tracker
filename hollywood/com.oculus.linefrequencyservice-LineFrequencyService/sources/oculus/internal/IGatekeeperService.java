package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IGatekeeperService extends IInterface {
    public static final int COLLECT_BAD_DISCHARGE_STATS = 0;
    public static final int ENABLE_APP_SAFETY_BINARY_CHECK = 15;
    public static final int ENABLE_ASSISTANT_WAKEWORD = 27;
    public static final int ENABLE_BATTERY_DRAIN_SERVICE = 35;
    public static final int ENABLE_BUGREPORTER = 1;
    public static final int ENABLE_BUGREPORTER_V2 = 10;
    public static final int ENABLE_BUGREPORTSERVICE = 22;
    public static final int ENABLE_CHROMECASTING = 4;
    public static final int ENABLE_CHROMECAST_101_ERROR_KEEP_CONNECTED = 30;
    public static final int ENABLE_CHROMECAST_FORCE_H264_FOR_SMART_TVS = 34;
    public static final int ENABLE_CHROMECAST_H264_OVERRIDE = 32;
    public static final int ENABLE_CHROMECAST_INCREASE_PING_FAILURE_TOLERANCE = 28;
    public static final int ENABLE_CHROMECAST_INIT_NSD_ON_DISCOVERY = 33;
    public static final int ENABLE_CHROMECAST_LONGER_PING_INTERVAL = 19;
    public static final int ENABLE_CHROMECAST_NATIVE_RECEIVER = 8;
    public static final int ENABLE_CHROMECAST_REMOVE_PING = 29;
    public static final int ENABLE_CONFIGURABLE_MTP_DIALOG = 23;
    public static final int ENABLE_CONTROLLER_LED_CONTROL = 31;
    public static final int ENABLE_CRASH_REPORT_LOG_COLLECTION = 37;
    public static final int ENABLE_HANDS_OPT_IN_MIGRATION = 38;
    public static final int ENABLE_LICENSE_ENFORCEMENT = 17;
    public static final int ENABLE_LICENSE_MANAGER = 13;
    public static final int ENABLE_LINK_NO_MODAL = 21;
    public static final int ENABLE_LOCATION_PROVIDER = 2;
    public static final int ENABLE_MIRACAST = 9;
    public static final int ENABLE_MONTEREY_N_10BPP_TRACKING = 25;
    public static final int ENABLE_MR_DATA_PERMISSION = 11;
    public static final int ENABLE_MULTI_USER = 3;
    public static final int ENABLE_NATIVE_VOICE_SERVICE = 36;
    public static final int ENABLE_OCULUS_MEDIA_SHARED_MIC = 40;
    public static final int ENABLE_PERMISSION_DIALOG_TELEMETRY = 16;
    public static final int ENABLE_PHONE_NOTIFICATIONS = 18;
    public static final int ENABLE_RETAIL_DEMO = 20;
    public static final int ENABLE_SAFETY_SIGNAL_COLLECTION = 26;
    public static final int ENABLE_SWAP_SYSTEM_BTN_HANDEDNESS = 12;
    public static final int ENABLE_TOUR_GUIDE = 14;
    public static final int ENABLE_UNKNOWN_SOURCES = 6;
    public static final int ENABLE_VISION_BUGREPORTS = 5;
    public static final int ENABLE_VR_KEYBOARD = 7;
    public static final int ENABLE_WIFI_TELEMETRY = 39;
    public static final int GATEKEEPER_COUNT = 41;
    public static final int USE_PACKAGE_PARTS_UPLOADER = 24;

    void clearGatekeeper(String str, int i) throws RemoteException;

    void clearOverriddenGatekeepers() throws RemoteException;

    void clearOverrideGatekeeper(String str) throws RemoteException;

    boolean getGatekeeperByName(String str, boolean z) throws RemoteException;

    boolean getGatekeeperDef(int i, boolean z) throws RemoteException;

    boolean isGatekeeperOverridden(String str) throws RemoteException;

    String[] listDynamicGatekeepers() throws RemoteException;

    String[] listGatekeepers() throws RemoteException;

    void overrideGatekeeper(String str, String str2) throws RemoteException;

    void registerDynamicGatekeeper(String str) throws RemoteException;

    void setGatekeeper(String str, int i, boolean z) throws RemoteException;

    public static class Default implements IGatekeeperService {
        @Override // oculus.internal.IGatekeeperService
        public boolean getGatekeeperDef(int gatekeeper, boolean defValue) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.IGatekeeperService
        public boolean getGatekeeperByName(String gk, boolean defVal) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.IGatekeeperService
        public void registerDynamicGatekeeper(String gk) throws RemoteException {
        }

        @Override // oculus.internal.IGatekeeperService
        public void setGatekeeper(String gk, int userId, boolean val) throws RemoteException {
        }

        @Override // oculus.internal.IGatekeeperService
        public void clearGatekeeper(String gk, int userId) throws RemoteException {
        }

        @Override // oculus.internal.IGatekeeperService
        public String[] listGatekeepers() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.IGatekeeperService
        public String[] listDynamicGatekeepers() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.IGatekeeperService
        public void overrideGatekeeper(String gk, String val) throws RemoteException {
        }

        @Override // oculus.internal.IGatekeeperService
        public void clearOverrideGatekeeper(String gk) throws RemoteException {
        }

        @Override // oculus.internal.IGatekeeperService
        public void clearOverriddenGatekeepers() throws RemoteException {
        }

        @Override // oculus.internal.IGatekeeperService
        public boolean isGatekeeperOverridden(String gk) throws RemoteException {
            return false;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IGatekeeperService {
        private static final String DESCRIPTOR = "oculus.internal.IGatekeeperService";
        static final int TRANSACTION_clearGatekeeper = 5;
        static final int TRANSACTION_clearOverriddenGatekeepers = 10;
        static final int TRANSACTION_clearOverrideGatekeeper = 9;
        static final int TRANSACTION_getGatekeeperByName = 2;
        static final int TRANSACTION_getGatekeeperDef = 1;
        static final int TRANSACTION_isGatekeeperOverridden = 11;
        static final int TRANSACTION_listDynamicGatekeepers = 7;
        static final int TRANSACTION_listGatekeepers = 6;
        static final int TRANSACTION_overrideGatekeeper = 8;
        static final int TRANSACTION_registerDynamicGatekeeper = 3;
        static final int TRANSACTION_setGatekeeper = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IGatekeeperService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IGatekeeperService)) {
                return new Proxy(obj);
            }
            return (IGatekeeperService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            if (code != 1598968902) {
                boolean _arg2 = false;
                switch (code) {
                    case 1:
                        data.enforceInterface(DESCRIPTOR);
                        int _arg0 = data.readInt();
                        if (data.readInt() != 0) {
                            _arg2 = true;
                        }
                        boolean gatekeeperDef = getGatekeeperDef(_arg0, _arg2);
                        reply.writeNoException();
                        reply.writeInt(gatekeeperDef ? 1 : 0);
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        String _arg02 = data.readString();
                        if (data.readInt() != 0) {
                            _arg2 = true;
                        }
                        boolean gatekeeperByName = getGatekeeperByName(_arg02, _arg2);
                        reply.writeNoException();
                        reply.writeInt(gatekeeperByName ? 1 : 0);
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        registerDynamicGatekeeper(data.readString());
                        reply.writeNoException();
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        String _arg03 = data.readString();
                        int _arg1 = data.readInt();
                        if (data.readInt() != 0) {
                            _arg2 = true;
                        }
                        setGatekeeper(_arg03, _arg1, _arg2);
                        reply.writeNoException();
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        clearGatekeeper(data.readString(), data.readInt());
                        reply.writeNoException();
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        String[] _result = listGatekeepers();
                        reply.writeNoException();
                        reply.writeStringArray(_result);
                        return true;
                    case 7:
                        data.enforceInterface(DESCRIPTOR);
                        String[] _result2 = listDynamicGatekeepers();
                        reply.writeNoException();
                        reply.writeStringArray(_result2);
                        return true;
                    case 8:
                        data.enforceInterface(DESCRIPTOR);
                        overrideGatekeeper(data.readString(), data.readString());
                        reply.writeNoException();
                        return true;
                    case 9:
                        data.enforceInterface(DESCRIPTOR);
                        clearOverrideGatekeeper(data.readString());
                        reply.writeNoException();
                        return true;
                    case 10:
                        data.enforceInterface(DESCRIPTOR);
                        clearOverriddenGatekeepers();
                        reply.writeNoException();
                        return true;
                    case 11:
                        data.enforceInterface(DESCRIPTOR);
                        boolean isGatekeeperOverridden = isGatekeeperOverridden(data.readString());
                        reply.writeNoException();
                        reply.writeInt(isGatekeeperOverridden ? 1 : 0);
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
        public static class Proxy implements IGatekeeperService {
            public static IGatekeeperService sDefaultImpl;
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

            @Override // oculus.internal.IGatekeeperService
            public boolean getGatekeeperDef(int gatekeeper, boolean defValue) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(gatekeeper);
                    boolean _result = true;
                    _data.writeInt(defValue ? 1 : 0);
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGatekeeperDef(gatekeeper, defValue);
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

            @Override // oculus.internal.IGatekeeperService
            public boolean getGatekeeperByName(String gk, boolean defVal) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(gk);
                    boolean _result = true;
                    _data.writeInt(defVal ? 1 : 0);
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getGatekeeperByName(gk, defVal);
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

            @Override // oculus.internal.IGatekeeperService
            public void registerDynamicGatekeeper(String gk) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(gk);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().registerDynamicGatekeeper(gk);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IGatekeeperService
            public void setGatekeeper(String gk, int userId, boolean val) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(gk);
                    _data.writeInt(userId);
                    _data.writeInt(val ? 1 : 0);
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setGatekeeper(gk, userId, val);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IGatekeeperService
            public void clearGatekeeper(String gk, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(gk);
                    _data.writeInt(userId);
                    if (this.mRemote.transact(5, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().clearGatekeeper(gk, userId);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IGatekeeperService
            public String[] listGatekeepers() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listGatekeepers();
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

            @Override // oculus.internal.IGatekeeperService
            public String[] listDynamicGatekeepers() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().listDynamicGatekeepers();
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

            @Override // oculus.internal.IGatekeeperService
            public void overrideGatekeeper(String gk, String val) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(gk);
                    _data.writeString(val);
                    if (this.mRemote.transact(8, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().overrideGatekeeper(gk, val);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IGatekeeperService
            public void clearOverrideGatekeeper(String gk) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(gk);
                    if (this.mRemote.transact(9, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().clearOverrideGatekeeper(gk);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IGatekeeperService
            public void clearOverriddenGatekeepers() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(10, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().clearOverriddenGatekeepers();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IGatekeeperService
            public boolean isGatekeeperOverridden(String gk) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(gk);
                    boolean _result = false;
                    if (!this.mRemote.transact(11, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isGatekeeperOverridden(gk);
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

        public static boolean setDefaultImpl(IGatekeeperService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IGatekeeperService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
