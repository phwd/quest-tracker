package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IOsUpdateMonitor extends IInterface {
    void applyPayload(String str, long j, long j2, String[] strArr) throws RemoteException;

    void cancel() throws RemoteException;

    int getUpdateEngineErrorCode() throws RemoteException;

    int getUpdateEngineState() throws RemoteException;

    void resetStatus() throws RemoteException;

    void resume() throws RemoteException;

    void suspend() throws RemoteException;

    public static class Default implements IOsUpdateMonitor {
        @Override // oculus.internal.IOsUpdateMonitor
        public int getUpdateEngineState() throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.IOsUpdateMonitor
        public int getUpdateEngineErrorCode() throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.IOsUpdateMonitor
        public void applyPayload(String url, long offset, long size, String[] headerKeyValuePairs) throws RemoteException {
        }

        @Override // oculus.internal.IOsUpdateMonitor
        public void cancel() throws RemoteException {
        }

        @Override // oculus.internal.IOsUpdateMonitor
        public void suspend() throws RemoteException {
        }

        @Override // oculus.internal.IOsUpdateMonitor
        public void resume() throws RemoteException {
        }

        @Override // oculus.internal.IOsUpdateMonitor
        public void resetStatus() throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IOsUpdateMonitor {
        private static final String DESCRIPTOR = "oculus.internal.IOsUpdateMonitor";
        static final int TRANSACTION_applyPayload = 3;
        static final int TRANSACTION_cancel = 4;
        static final int TRANSACTION_getUpdateEngineErrorCode = 2;
        static final int TRANSACTION_getUpdateEngineState = 1;
        static final int TRANSACTION_resetStatus = 7;
        static final int TRANSACTION_resume = 6;
        static final int TRANSACTION_suspend = 5;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IOsUpdateMonitor asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IOsUpdateMonitor)) {
                return new Proxy(obj);
            }
            return (IOsUpdateMonitor) iin;
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
                        int _result = getUpdateEngineState();
                        reply.writeNoException();
                        reply.writeInt(_result);
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        int _result2 = getUpdateEngineErrorCode();
                        reply.writeNoException();
                        reply.writeInt(_result2);
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        applyPayload(data.readString(), data.readLong(), data.readLong(), data.createStringArray());
                        reply.writeNoException();
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        cancel();
                        reply.writeNoException();
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        suspend();
                        reply.writeNoException();
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        resume();
                        reply.writeNoException();
                        return true;
                    case 7:
                        data.enforceInterface(DESCRIPTOR);
                        resetStatus();
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
        public static class Proxy implements IOsUpdateMonitor {
            public static IOsUpdateMonitor sDefaultImpl;
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

            @Override // oculus.internal.IOsUpdateMonitor
            public int getUpdateEngineState() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUpdateEngineState();
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

            @Override // oculus.internal.IOsUpdateMonitor
            public int getUpdateEngineErrorCode() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUpdateEngineErrorCode();
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

            @Override // oculus.internal.IOsUpdateMonitor
            public void applyPayload(String url, long offset, long size, String[] headerKeyValuePairs) throws RemoteException {
                Throwable th;
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    try {
                        _data.writeString(url);
                    } catch (Throwable th2) {
                        th = th2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeLong(offset);
                    } catch (Throwable th3) {
                        th = th3;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _data.writeLong(size);
                        try {
                            _data.writeStringArray(headerKeyValuePairs);
                            if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                                _reply.readException();
                                _reply.recycle();
                                _data.recycle();
                                return;
                            }
                            Stub.getDefaultImpl().applyPayload(url, offset, size, headerKeyValuePairs);
                            _reply.recycle();
                            _data.recycle();
                        } catch (Throwable th4) {
                            th = th4;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
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

            @Override // oculus.internal.IOsUpdateMonitor
            public void cancel() throws RemoteException {
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
                    Stub.getDefaultImpl().cancel();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IOsUpdateMonitor
            public void suspend() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(5, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().suspend();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IOsUpdateMonitor
            public void resume() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(6, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().resume();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IOsUpdateMonitor
            public void resetStatus() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(7, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().resetStatus();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IOsUpdateMonitor impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IOsUpdateMonitor getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
