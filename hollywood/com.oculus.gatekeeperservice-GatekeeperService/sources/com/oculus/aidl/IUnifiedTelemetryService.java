package com.oculus.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.os.RemoteException;

public interface IUnifiedTelemetryService extends IInterface {
    void endFunnel(String str) throws RemoteException;

    void endFunnelWithId(String str, long j) throws RemoteException;

    void logExposure(String str, String str2, PersistableBundle persistableBundle, PersistableBundle persistableBundle2, boolean z) throws RemoteException;

    void registerFunnel(String str, int i) throws RemoteException;

    void reportEvents(PersistableBundle persistableBundle, boolean z) throws RemoteException;

    void reportFunnelAction(String str, String str2, String str3, PersistableBundle persistableBundle) throws RemoteException;

    void reportFunnelActionWithId(String str, long j, String str2, String str3, PersistableBundle persistableBundle) throws RemoteException;

    boolean shouldDuplicateLogging(String str) throws RemoteException;

    boolean shouldUseUnifiedTelemetryService(String str) throws RemoteException;

    void startFunnel(String str) throws RemoteException;

    void startFunnelWithId(String str, long j) throws RemoteException;

    void startSession(String str, String str2, int i) throws RemoteException;

    void stopSession(String str, int i) throws RemoteException;

    public static class Default implements IUnifiedTelemetryService {
        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public boolean shouldDuplicateLogging(String packageName) throws RemoteException {
            return false;
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public boolean shouldUseUnifiedTelemetryService(String packageName) throws RemoteException {
            return false;
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void reportEvents(PersistableBundle events, boolean lowLatency) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void logExposure(String moduleName, String eventName, PersistableBundle baseParametersForInternalUse, PersistableBundle extras, boolean deliverImmediately) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void registerFunnel(String funnelName, int secondsToEndSinceLastUpdate) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void startFunnel(String funnelName) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void startFunnelWithId(String funnelName, long instanceId) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void reportFunnelAction(String funnelName, String actionName, String tag, PersistableBundle actionData) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void reportFunnelActionWithId(String funnelName, long instanceId, String actionName, String tag, PersistableBundle actionData) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void endFunnel(String funnelName) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void endFunnelWithId(String funnelName, long instanceId) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void startSession(String sessionName, String sessionId, int userId) throws RemoteException {
        }

        @Override // com.oculus.aidl.IUnifiedTelemetryService
        public void stopSession(String sessionName, int userId) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IUnifiedTelemetryService {
        private static final String DESCRIPTOR = "com.oculus.aidl.IUnifiedTelemetryService";
        static final int TRANSACTION_endFunnel = 10;
        static final int TRANSACTION_endFunnelWithId = 11;
        static final int TRANSACTION_logExposure = 4;
        static final int TRANSACTION_registerFunnel = 5;
        static final int TRANSACTION_reportEvents = 3;
        static final int TRANSACTION_reportFunnelAction = 8;
        static final int TRANSACTION_reportFunnelActionWithId = 9;
        static final int TRANSACTION_shouldDuplicateLogging = 1;
        static final int TRANSACTION_shouldUseUnifiedTelemetryService = 2;
        static final int TRANSACTION_startFunnel = 6;
        static final int TRANSACTION_startFunnelWithId = 7;
        static final int TRANSACTION_startSession = 12;
        static final int TRANSACTION_stopSession = 13;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IUnifiedTelemetryService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IUnifiedTelemetryService)) {
                return new Proxy(obj);
            }
            return (IUnifiedTelemetryService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            PersistableBundle _arg0;
            PersistableBundle _arg2;
            PersistableBundle _arg3;
            PersistableBundle _arg32;
            PersistableBundle _arg4;
            if (code != 1598968902) {
                boolean _arg1 = false;
                switch (code) {
                    case 1:
                        data.enforceInterface(DESCRIPTOR);
                        boolean shouldDuplicateLogging = shouldDuplicateLogging(data.readString());
                        reply.writeNoException();
                        reply.writeInt(shouldDuplicateLogging ? 1 : 0);
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        boolean shouldUseUnifiedTelemetryService = shouldUseUnifiedTelemetryService(data.readString());
                        reply.writeNoException();
                        reply.writeInt(shouldUseUnifiedTelemetryService ? 1 : 0);
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        if (data.readInt() != 0) {
                            _arg0 = (PersistableBundle) PersistableBundle.CREATOR.createFromParcel(data);
                        } else {
                            _arg0 = null;
                        }
                        if (data.readInt() != 0) {
                            _arg1 = true;
                        }
                        reportEvents(_arg0, _arg1);
                        reply.writeNoException();
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        String _arg02 = data.readString();
                        String _arg12 = data.readString();
                        if (data.readInt() != 0) {
                            _arg2 = (PersistableBundle) PersistableBundle.CREATOR.createFromParcel(data);
                        } else {
                            _arg2 = null;
                        }
                        if (data.readInt() != 0) {
                            _arg3 = (PersistableBundle) PersistableBundle.CREATOR.createFromParcel(data);
                        } else {
                            _arg3 = null;
                        }
                        logExposure(_arg02, _arg12, _arg2, _arg3, data.readInt() != 0);
                        reply.writeNoException();
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        registerFunnel(data.readString(), data.readInt());
                        reply.writeNoException();
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        startFunnel(data.readString());
                        reply.writeNoException();
                        return true;
                    case 7:
                        data.enforceInterface(DESCRIPTOR);
                        startFunnelWithId(data.readString(), data.readLong());
                        reply.writeNoException();
                        return true;
                    case 8:
                        data.enforceInterface(DESCRIPTOR);
                        String _arg03 = data.readString();
                        String _arg13 = data.readString();
                        String _arg22 = data.readString();
                        if (data.readInt() != 0) {
                            _arg32 = (PersistableBundle) PersistableBundle.CREATOR.createFromParcel(data);
                        } else {
                            _arg32 = null;
                        }
                        reportFunnelAction(_arg03, _arg13, _arg22, _arg32);
                        reply.writeNoException();
                        return true;
                    case 9:
                        data.enforceInterface(DESCRIPTOR);
                        String _arg04 = data.readString();
                        long _arg14 = data.readLong();
                        String _arg23 = data.readString();
                        String _arg33 = data.readString();
                        if (data.readInt() != 0) {
                            _arg4 = (PersistableBundle) PersistableBundle.CREATOR.createFromParcel(data);
                        } else {
                            _arg4 = null;
                        }
                        reportFunnelActionWithId(_arg04, _arg14, _arg23, _arg33, _arg4);
                        reply.writeNoException();
                        return true;
                    case 10:
                        data.enforceInterface(DESCRIPTOR);
                        endFunnel(data.readString());
                        reply.writeNoException();
                        return true;
                    case 11:
                        data.enforceInterface(DESCRIPTOR);
                        endFunnelWithId(data.readString(), data.readLong());
                        reply.writeNoException();
                        return true;
                    case 12:
                        data.enforceInterface(DESCRIPTOR);
                        startSession(data.readString(), data.readString(), data.readInt());
                        reply.writeNoException();
                        return true;
                    case 13:
                        data.enforceInterface(DESCRIPTOR);
                        stopSession(data.readString(), data.readInt());
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
        public static class Proxy implements IUnifiedTelemetryService {
            public static IUnifiedTelemetryService sDefaultImpl;
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

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public boolean shouldDuplicateLogging(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _result = false;
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldDuplicateLogging(packageName);
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

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public boolean shouldUseUnifiedTelemetryService(String packageName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(packageName);
                    boolean _result = false;
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldUseUnifiedTelemetryService(packageName);
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

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void reportEvents(PersistableBundle events, boolean lowLatency) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    if (events != null) {
                        _data.writeInt(1);
                        events.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!lowLatency) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().reportEvents(events, lowLatency);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void logExposure(String moduleName, String eventName, PersistableBundle baseParametersForInternalUse, PersistableBundle extras, boolean deliverImmediately) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(moduleName);
                    _data.writeString(eventName);
                    int i = 1;
                    if (baseParametersForInternalUse != null) {
                        _data.writeInt(1);
                        baseParametersForInternalUse.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (extras != null) {
                        _data.writeInt(1);
                        extras.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (!deliverImmediately) {
                        i = 0;
                    }
                    _data.writeInt(i);
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().logExposure(moduleName, eventName, baseParametersForInternalUse, extras, deliverImmediately);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void registerFunnel(String funnelName, int secondsToEndSinceLastUpdate) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(funnelName);
                    _data.writeInt(secondsToEndSinceLastUpdate);
                    if (this.mRemote.transact(5, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().registerFunnel(funnelName, secondsToEndSinceLastUpdate);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void startFunnel(String funnelName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(funnelName);
                    if (this.mRemote.transact(6, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().startFunnel(funnelName);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void startFunnelWithId(String funnelName, long instanceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(funnelName);
                    _data.writeLong(instanceId);
                    if (this.mRemote.transact(7, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().startFunnelWithId(funnelName, instanceId);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void reportFunnelAction(String funnelName, String actionName, String tag, PersistableBundle actionData) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(funnelName);
                    _data.writeString(actionName);
                    _data.writeString(tag);
                    if (actionData != null) {
                        _data.writeInt(1);
                        actionData.writeToParcel(_data, 0);
                    } else {
                        _data.writeInt(0);
                    }
                    if (this.mRemote.transact(8, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().reportFunnelAction(funnelName, actionName, tag, actionData);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void reportFunnelActionWithId(String funnelName, long instanceId, String actionName, String tag, PersistableBundle actionData) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(funnelName);
                        try {
                            _data.writeLong(instanceId);
                        } catch (Throwable th2) {
                            th = th2;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                        try {
                            _data.writeString(actionName);
                        } catch (Throwable th3) {
                            th = th3;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeString(tag);
                        if (actionData != null) {
                            _data.writeInt(1);
                            actionData.writeToParcel(_data, 0);
                        } else {
                            _data.writeInt(0);
                        }
                        if (this.mRemote.transact(9, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                            _reply.readException();
                            _reply.recycle();
                            _data.recycle();
                            return;
                        }
                        Stub.getDefaultImpl().reportFunnelActionWithId(funnelName, instanceId, actionName, tag, actionData);
                        _reply.recycle();
                        _data.recycle();
                    } catch (Throwable th5) {
                        th = th5;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void endFunnel(String funnelName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(funnelName);
                    if (this.mRemote.transact(10, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().endFunnel(funnelName);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void endFunnelWithId(String funnelName, long instanceId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(funnelName);
                    _data.writeLong(instanceId);
                    if (this.mRemote.transact(11, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().endFunnelWithId(funnelName, instanceId);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void startSession(String sessionName, String sessionId, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(sessionName);
                    _data.writeString(sessionId);
                    _data.writeInt(userId);
                    if (this.mRemote.transact(12, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().startSession(sessionName, sessionId, userId);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.aidl.IUnifiedTelemetryService
            public void stopSession(String sessionName, int userId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(sessionName);
                    _data.writeInt(userId);
                    if (this.mRemote.transact(13, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().stopSession(sessionName, userId);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IUnifiedTelemetryService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IUnifiedTelemetryService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
