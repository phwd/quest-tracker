package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.io.FileDescriptor;
import oculus.internal.IMrSystemListener;

public interface IMrSystemService extends IInterface {
    int bind(IBinder iBinder, IMrSystemListener iMrSystemListener, byte[] bArr, long[] jArr, FileDescriptor[] fileDescriptorArr, long[] jArr2, FileDescriptor[] fileDescriptorArr2, long[] jArr3) throws RemoteException;

    int unbind(int i) throws RemoteException;

    public static class Default implements IMrSystemService {
        @Override // oculus.internal.IMrSystemService
        public int bind(IBinder binder, IMrSystemListener listener, byte[] graphicBufferData, long[] graphicBufferSizes, FileDescriptor[] graphicBufferFds, long[] graphicBufferFdCount, FileDescriptor[] ionFds, long[] ionSizes) throws RemoteException {
            return 0;
        }

        @Override // oculus.internal.IMrSystemService
        public int unbind(int bindId) throws RemoteException {
            return 0;
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IMrSystemService {
        private static final String DESCRIPTOR = "oculus.internal.IMrSystemService";
        static final int TRANSACTION_bind = 1;
        static final int TRANSACTION_unbind = 2;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IMrSystemService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IMrSystemService)) {
                return new Proxy(obj);
            }
            return (IMrSystemService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            byte[] _arg2;
            long[] _arg3;
            FileDescriptor[] _arg4;
            long[] _arg5;
            FileDescriptor[] _arg6;
            long[] _arg7;
            if (code == 1) {
                data.enforceInterface(DESCRIPTOR);
                IBinder _arg0 = data.readStrongBinder();
                IMrSystemListener _arg1 = IMrSystemListener.Stub.asInterface(data.readStrongBinder());
                int _arg2_length = data.readInt();
                if (_arg2_length < 0) {
                    _arg2 = null;
                } else {
                    _arg2 = new byte[_arg2_length];
                }
                int _arg3_length = data.readInt();
                if (_arg3_length < 0) {
                    _arg3 = null;
                } else {
                    _arg3 = new long[_arg3_length];
                }
                int _arg4_length = data.readInt();
                if (_arg4_length < 0) {
                    _arg4 = null;
                } else {
                    _arg4 = new FileDescriptor[_arg4_length];
                }
                int _arg5_length = data.readInt();
                if (_arg5_length < 0) {
                    _arg5 = null;
                } else {
                    _arg5 = new long[_arg5_length];
                }
                int _arg6_length = data.readInt();
                if (_arg6_length < 0) {
                    _arg6 = null;
                } else {
                    _arg6 = new FileDescriptor[_arg6_length];
                }
                int _arg7_length = data.readInt();
                if (_arg7_length < 0) {
                    _arg7 = null;
                } else {
                    _arg7 = new long[_arg7_length];
                }
                int _result = bind(_arg0, _arg1, _arg2, _arg3, _arg4, _arg5, _arg6, _arg7);
                reply.writeNoException();
                reply.writeInt(_result);
                reply.writeByteArray(_arg2);
                reply.writeLongArray(_arg3);
                reply.writeRawFileDescriptorArray(_arg4);
                reply.writeLongArray(_arg5);
                reply.writeRawFileDescriptorArray(_arg6);
                reply.writeLongArray(_arg7);
                return true;
            } else if (code == 2) {
                data.enforceInterface(DESCRIPTOR);
                int _result2 = unbind(data.readInt());
                reply.writeNoException();
                reply.writeInt(_result2);
                return true;
            } else if (code != 1598968902) {
                return super.onTransact(code, data, reply, flags);
            } else {
                reply.writeString(DESCRIPTOR);
                return true;
            }
        }

        /* access modifiers changed from: private */
        public static class Proxy implements IMrSystemService {
            public static IMrSystemService sDefaultImpl;
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

            @Override // oculus.internal.IMrSystemService
            public int bind(IBinder binder, IMrSystemListener listener, byte[] graphicBufferData, long[] graphicBufferSizes, FileDescriptor[] graphicBufferFds, long[] graphicBufferFdCount, FileDescriptor[] ionFds, long[] ionSizes) throws RemoteException {
                Parcel _data;
                Parcel _reply;
                Throwable th;
                IBinder iBinder;
                int _result;
                Parcel _data2 = Parcel.obtain();
                Parcel _reply2 = Parcel.obtain();
                try {
                    _data2.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data2.writeStrongBinder(binder);
                    if (listener != null) {
                        try {
                            iBinder = listener.asBinder();
                        } catch (Throwable th2) {
                            th = th2;
                            _reply = _reply2;
                            _data = _data2;
                        }
                    } else {
                        iBinder = null;
                    }
                    _data2.writeStrongBinder(iBinder);
                    if (graphicBufferData == null) {
                        _data2.writeInt(-1);
                    } else {
                        _data2.writeInt(graphicBufferData.length);
                    }
                    if (graphicBufferSizes == null) {
                        _data2.writeInt(-1);
                    } else {
                        _data2.writeInt(graphicBufferSizes.length);
                    }
                    if (graphicBufferFds == null) {
                        _data2.writeInt(-1);
                    } else {
                        _data2.writeInt(graphicBufferFds.length);
                    }
                    if (graphicBufferFdCount == null) {
                        _data2.writeInt(-1);
                    } else {
                        _data2.writeInt(graphicBufferFdCount.length);
                    }
                    if (ionFds == null) {
                        _data2.writeInt(-1);
                    } else {
                        _data2.writeInt(ionFds.length);
                    }
                    if (ionSizes == null) {
                        _data2.writeInt(-1);
                    } else {
                        _data2.writeInt(ionSizes.length);
                    }
                    if (!this.mRemote.transact(1, _data2, _reply2, 0)) {
                        try {
                            if (Stub.getDefaultImpl() != null) {
                                _data = _data2;
                                try {
                                    int bind = Stub.getDefaultImpl().bind(binder, listener, graphicBufferData, graphicBufferSizes, graphicBufferFds, graphicBufferFdCount, ionFds, ionSizes);
                                    _reply2.recycle();
                                    _data.recycle();
                                    return bind;
                                } catch (Throwable th3) {
                                    th = th3;
                                    _reply = _reply2;
                                    _reply.recycle();
                                    _data.recycle();
                                    throw th;
                                }
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            _data = _data2;
                            _reply = _reply2;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    }
                    _data = _data2;
                    try {
                        _reply2.readException();
                        _result = _reply2.readInt();
                        _reply = _reply2;
                    } catch (Throwable th5) {
                        th = th5;
                        _reply = _reply2;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _reply.readByteArray(graphicBufferData);
                        _reply.readLongArray(graphicBufferSizes);
                        _reply.createRawFileDescriptorArray();
                    } catch (Throwable th6) {
                        th = th6;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                    try {
                        _reply.readLongArray(graphicBufferFdCount);
                        _reply.createRawFileDescriptorArray();
                        try {
                            _reply.readLongArray(ionSizes);
                            _reply.recycle();
                            _data.recycle();
                            return _result;
                        } catch (Throwable th7) {
                            th = th7;
                            _reply.recycle();
                            _data.recycle();
                            throw th;
                        }
                    } catch (Throwable th8) {
                        th = th8;
                        _reply.recycle();
                        _data.recycle();
                        throw th;
                    }
                } catch (Throwable th9) {
                    th = th9;
                    _reply = _reply2;
                    _data = _data2;
                    _reply.recycle();
                    _data.recycle();
                    throw th;
                }
            }

            @Override // oculus.internal.IMrSystemService
            public int unbind(int bindId) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(bindId);
                    if (!this.mRemote.transact(2, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().unbind(bindId);
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

        public static boolean setDefaultImpl(IMrSystemService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IMrSystemService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
