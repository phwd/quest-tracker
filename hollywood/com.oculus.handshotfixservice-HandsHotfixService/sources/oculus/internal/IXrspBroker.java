package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import oculus.internal.IXrspStateObserver;
import oculus.internal.IXrspStreamingClient;

public interface IXrspBroker extends IInterface {
    public static final int REASON_CABLE_PLUGGED_IN = 1;
    public static final int REASON_CABLE_UNPLUGGED = 2;
    public static final int REASON_CLIENT_REQUESTED_START = 7;
    public static final int REASON_CLIENT_REQUESTED_STOP = 8;
    public static final int REASON_CLIENT_TERMINATED = 9;
    public static final int REASON_HANDSHAKE_FAILED = 5;
    public static final int REASON_HANDSHAKE_INITIATED = 3;
    public static final int REASON_HANDSHAKE_SUCCESSFUL = 4;
    public static final int REASON_HOST_DISCONNECTED = 6;
    public static final int REASON_OBSERVER_REGISTERED = 11;
    public static final int REASON_SERVICE_TERMINATED = 10;
    public static final int REASON_UNKNOWN = 0;
    public static final int STATE_CONNECTED = 4;
    public static final int STATE_CONNECTING = 3;
    public static final int STATE_DISCONNECTED = 2;
    public static final int STATE_STREAMING = 5;
    public static final int STATE_UNKNOWN = 0;
    public static final int STATE_UNPLUGGED = 1;

    void forgetCounterpart() throws RemoteException;

    String getConnectedHostName() throws RemoteException;

    String getCounterpart() throws RemoteException;

    int getState() throws RemoteException;

    boolean registerStateObserver(IXrspStateObserver iXrspStateObserver) throws RemoteException;

    void setALEnabled(boolean z) throws RemoteException;

    void setCounterpart(String str) throws RemoteException;

    boolean startStreamingSession(IXrspStreamingClient iXrspStreamingClient) throws RemoteException;

    void stopStreamingSession(IXrspStreamingClient iXrspStreamingClient) throws RemoteException;

    void unregisterStateObserver(IXrspStateObserver iXrspStateObserver) throws RemoteException;

    public static class Default implements IXrspBroker {
        @Override // oculus.internal.IXrspBroker
        public boolean startStreamingSession(IXrspStreamingClient client) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.IXrspBroker
        public void stopStreamingSession(IXrspStreamingClient client) throws RemoteException {
        }

        @Override // oculus.internal.IXrspBroker
        public boolean registerStateObserver(IXrspStateObserver observer) throws RemoteException {
            return false;
        }

        @Override // oculus.internal.IXrspBroker
        public void unregisterStateObserver(IXrspStateObserver observer) throws RemoteException {
        }

        @Override // oculus.internal.IXrspBroker
        public int getState() throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.IXrspBroker
        public String getConnectedHostName() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.IXrspBroker
        public void setCounterpart(String mdnsName) throws RemoteException {
        }

        @Override // oculus.internal.IXrspBroker
        public String getCounterpart() throws RemoteException {
            return null;
        }

        @Override // oculus.internal.IXrspBroker
        public void forgetCounterpart() throws RemoteException {
        }

        @Override // oculus.internal.IXrspBroker
        public void setALEnabled(boolean enabled) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IXrspBroker {
        private static final String DESCRIPTOR = "oculus.internal.IXrspBroker";
        static final int TRANSACTION_forgetCounterpart = 9;
        static final int TRANSACTION_getConnectedHostName = 6;
        static final int TRANSACTION_getCounterpart = 8;
        static final int TRANSACTION_getState = 5;
        static final int TRANSACTION_registerStateObserver = 3;
        static final int TRANSACTION_setALEnabled = 10;
        static final int TRANSACTION_setCounterpart = 7;
        static final int TRANSACTION_startStreamingSession = 1;
        static final int TRANSACTION_stopStreamingSession = 2;
        static final int TRANSACTION_unregisterStateObserver = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IXrspBroker asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IXrspBroker)) {
                return new Proxy(obj);
            }
            return (IXrspBroker) iin;
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
                        boolean startStreamingSession = startStreamingSession(IXrspStreamingClient.Stub.asInterface(data.readStrongBinder()));
                        reply.writeNoException();
                        reply.writeInt(startStreamingSession ? 1 : 0);
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        stopStreamingSession(IXrspStreamingClient.Stub.asInterface(data.readStrongBinder()));
                        reply.writeNoException();
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        boolean registerStateObserver = registerStateObserver(IXrspStateObserver.Stub.asInterface(data.readStrongBinder()));
                        reply.writeNoException();
                        reply.writeInt(registerStateObserver ? 1 : 0);
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        unregisterStateObserver(IXrspStateObserver.Stub.asInterface(data.readStrongBinder()));
                        reply.writeNoException();
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        int _result = getState();
                        reply.writeNoException();
                        reply.writeInt(_result);
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        String _result2 = getConnectedHostName();
                        reply.writeNoException();
                        reply.writeString(_result2);
                        return true;
                    case 7:
                        data.enforceInterface(DESCRIPTOR);
                        setCounterpart(data.readString());
                        return true;
                    case 8:
                        data.enforceInterface(DESCRIPTOR);
                        String _result3 = getCounterpart();
                        reply.writeNoException();
                        reply.writeString(_result3);
                        return true;
                    case 9:
                        data.enforceInterface(DESCRIPTOR);
                        forgetCounterpart();
                        return true;
                    case 10:
                        data.enforceInterface(DESCRIPTOR);
                        setALEnabled(data.readInt() != 0);
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
        public static class Proxy implements IXrspBroker {
            public static IXrspBroker sDefaultImpl;
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

            @Override // oculus.internal.IXrspBroker
            public boolean startStreamingSession(IXrspStreamingClient client) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    boolean _result = false;
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startStreamingSession(client);
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

            @Override // oculus.internal.IXrspBroker
            public void stopStreamingSession(IXrspStreamingClient client) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(client != null ? client.asBinder() : null);
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().stopStreamingSession(client);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IXrspBroker
            public boolean registerStateObserver(IXrspStateObserver observer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(observer != null ? observer.asBinder() : null);
                    boolean _result = false;
                    if (!this.mRemote.transact(3, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerStateObserver(observer);
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

            @Override // oculus.internal.IXrspBroker
            public void unregisterStateObserver(IXrspStateObserver observer) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeStrongBinder(observer != null ? observer.asBinder() : null);
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().unregisterStateObserver(observer);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IXrspBroker
            public int getState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getState();
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

            @Override // oculus.internal.IXrspBroker
            public String getConnectedHostName() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(6, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getConnectedHostName();
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

            @Override // oculus.internal.IXrspBroker
            public void setCounterpart(String mdnsName) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeString(mdnsName);
                    if (this.mRemote.transact(7, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().setCounterpart(mdnsName);
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IXrspBroker
            public String getCounterpart() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCounterpart();
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

            @Override // oculus.internal.IXrspBroker
            public void forgetCounterpart() throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(9, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().forgetCounterpart();
                    }
                } finally {
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IXrspBroker
            public void setALEnabled(boolean enabled) throws RemoteException {
                Parcel _data = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(enabled ? 1 : 0);
                    if (this.mRemote.transact(10, _data, null, 1) || Stub.getDefaultImpl() == null) {
                        _data.recycle();
                    } else {
                        Stub.getDefaultImpl().setALEnabled(enabled);
                    }
                } finally {
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IXrspBroker impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IXrspBroker getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
