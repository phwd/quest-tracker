package com.oculus.inputinjectionservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IInputInjectionService extends IInterface {
    boolean[] getButtons() throws RemoteException;

    float[] getHeadsetPose() throws RemoteException;

    float[] getJoysticks() throws RemoteException;

    float[] getLeftPose() throws RemoteException;

    float[] getRightPose() throws RemoteException;

    boolean[] getTurnOff() throws RemoteException;

    void setButtons(boolean[] zArr) throws RemoteException;

    void setHeadsetPose(float[] fArr) throws RemoteException;

    void setJoysticks(float[] fArr) throws RemoteException;

    void setLeftPose(float[] fArr) throws RemoteException;

    void setRightPose(float[] fArr) throws RemoteException;

    void setTurnOff(boolean[] zArr) throws RemoteException;

    public static class Default implements IInputInjectionService {
        @Override // com.oculus.inputinjectionservice.IInputInjectionService
        public float[] getLeftPose() throws RemoteException {
            return null;
        }

        @Override // com.oculus.inputinjectionservice.IInputInjectionService
        public void setLeftPose(float[] data) throws RemoteException {
        }

        @Override // com.oculus.inputinjectionservice.IInputInjectionService
        public float[] getRightPose() throws RemoteException {
            return null;
        }

        @Override // com.oculus.inputinjectionservice.IInputInjectionService
        public void setRightPose(float[] data) throws RemoteException {
        }

        @Override // com.oculus.inputinjectionservice.IInputInjectionService
        public float[] getHeadsetPose() throws RemoteException {
            return null;
        }

        @Override // com.oculus.inputinjectionservice.IInputInjectionService
        public void setHeadsetPose(float[] data) throws RemoteException {
        }

        @Override // com.oculus.inputinjectionservice.IInputInjectionService
        public boolean[] getTurnOff() throws RemoteException {
            return null;
        }

        @Override // com.oculus.inputinjectionservice.IInputInjectionService
        public void setTurnOff(boolean[] values) throws RemoteException {
        }

        @Override // com.oculus.inputinjectionservice.IInputInjectionService
        public boolean[] getButtons() throws RemoteException {
            return null;
        }

        @Override // com.oculus.inputinjectionservice.IInputInjectionService
        public void setButtons(boolean[] data) throws RemoteException {
        }

        @Override // com.oculus.inputinjectionservice.IInputInjectionService
        public float[] getJoysticks() throws RemoteException {
            return null;
        }

        @Override // com.oculus.inputinjectionservice.IInputInjectionService
        public void setJoysticks(float[] data) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IInputInjectionService {
        private static final String DESCRIPTOR = "com.oculus.inputinjectionservice.IInputInjectionService";
        static final int TRANSACTION_getButtons = 9;
        static final int TRANSACTION_getHeadsetPose = 5;
        static final int TRANSACTION_getJoysticks = 11;
        static final int TRANSACTION_getLeftPose = 1;
        static final int TRANSACTION_getRightPose = 3;
        static final int TRANSACTION_getTurnOff = 7;
        static final int TRANSACTION_setButtons = 10;
        static final int TRANSACTION_setHeadsetPose = 6;
        static final int TRANSACTION_setJoysticks = 12;
        static final int TRANSACTION_setLeftPose = 2;
        static final int TRANSACTION_setRightPose = 4;
        static final int TRANSACTION_setTurnOff = 8;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IInputInjectionService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IInputInjectionService)) {
                return new Proxy(obj);
            }
            return (IInputInjectionService) iin;
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
                        float[] _result = getLeftPose();
                        reply.writeNoException();
                        reply.writeFloatArray(_result);
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        setLeftPose(data.createFloatArray());
                        reply.writeNoException();
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        float[] _result2 = getRightPose();
                        reply.writeNoException();
                        reply.writeFloatArray(_result2);
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        setRightPose(data.createFloatArray());
                        reply.writeNoException();
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        float[] _result3 = getHeadsetPose();
                        reply.writeNoException();
                        reply.writeFloatArray(_result3);
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        setHeadsetPose(data.createFloatArray());
                        reply.writeNoException();
                        return true;
                    case 7:
                        data.enforceInterface(DESCRIPTOR);
                        boolean[] _result4 = getTurnOff();
                        reply.writeNoException();
                        reply.writeBooleanArray(_result4);
                        return true;
                    case 8:
                        data.enforceInterface(DESCRIPTOR);
                        setTurnOff(data.createBooleanArray());
                        reply.writeNoException();
                        return true;
                    case 9:
                        data.enforceInterface(DESCRIPTOR);
                        boolean[] _result5 = getButtons();
                        reply.writeNoException();
                        reply.writeBooleanArray(_result5);
                        return true;
                    case 10:
                        data.enforceInterface(DESCRIPTOR);
                        setButtons(data.createBooleanArray());
                        reply.writeNoException();
                        return true;
                    case 11:
                        data.enforceInterface(DESCRIPTOR);
                        float[] _result6 = getJoysticks();
                        reply.writeNoException();
                        reply.writeFloatArray(_result6);
                        return true;
                    case 12:
                        data.enforceInterface(DESCRIPTOR);
                        setJoysticks(data.createFloatArray());
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
        public static class Proxy implements IInputInjectionService {
            public static IInputInjectionService sDefaultImpl;
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

            @Override // com.oculus.inputinjectionservice.IInputInjectionService
            public float[] getLeftPose() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getLeftPose();
                    }
                    _reply.readException();
                    float[] _result = _reply.createFloatArray();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.inputinjectionservice.IInputInjectionService
            public void setLeftPose(float[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloatArray(data);
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setLeftPose(data);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.inputinjectionservice.IInputInjectionService
            public float[] getRightPose() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRightPose();
                    }
                    _reply.readException();
                    float[] _result = _reply.createFloatArray();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.inputinjectionservice.IInputInjectionService
            public void setRightPose(float[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloatArray(data);
                    if (this.mRemote.transact(4, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setRightPose(data);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.inputinjectionservice.IInputInjectionService
            public float[] getHeadsetPose() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getHeadsetPose();
                    }
                    _reply.readException();
                    float[] _result = _reply.createFloatArray();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.inputinjectionservice.IInputInjectionService
            public void setHeadsetPose(float[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloatArray(data);
                    if (this.mRemote.transact(6, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setHeadsetPose(data);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.inputinjectionservice.IInputInjectionService
            public boolean[] getTurnOff() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getTurnOff();
                    }
                    _reply.readException();
                    boolean[] _result = _reply.createBooleanArray();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.inputinjectionservice.IInputInjectionService
            public void setTurnOff(boolean[] values) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeBooleanArray(values);
                    if (this.mRemote.transact(8, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setTurnOff(values);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.inputinjectionservice.IInputInjectionService
            public boolean[] getButtons() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(9, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getButtons();
                    }
                    _reply.readException();
                    boolean[] _result = _reply.createBooleanArray();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.inputinjectionservice.IInputInjectionService
            public void setButtons(boolean[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeBooleanArray(data);
                    if (this.mRemote.transact(10, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setButtons(data);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.inputinjectionservice.IInputInjectionService
            public float[] getJoysticks() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(11, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getJoysticks();
                    }
                    _reply.readException();
                    float[] _result = _reply.createFloatArray();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.inputinjectionservice.IInputInjectionService
            public void setJoysticks(float[] data) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloatArray(data);
                    if (this.mRemote.transact(12, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setJoysticks(data);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IInputInjectionService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IInputInjectionService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
