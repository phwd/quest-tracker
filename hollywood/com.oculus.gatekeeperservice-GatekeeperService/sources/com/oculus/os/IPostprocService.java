package com.oculus.os;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IPostprocService extends IInterface {
    float getBacklightDutyCycle() throws RemoteException;

    void getPanelChromaticities(double[] dArr) throws RemoteException;

    void getPanelColorCorrection(float[] fArr) throws RemoteException;

    void setBacklightDutyCycle(float f) throws RemoteException;

    void setBlackLevel(float f) throws RemoteException;

    void setPanelColorCorrection(float[] fArr, int i) throws RemoteException;

    public static class Default implements IPostprocService {
        @Override // com.oculus.os.IPostprocService
        public void setBlackLevel(float newBlackLevel) throws RemoteException {
        }

        @Override // com.oculus.os.IPostprocService
        public void getPanelColorCorrection(float[] payload) throws RemoteException {
        }

        @Override // com.oculus.os.IPostprocService
        public void setPanelColorCorrection(float[] payload, int side) throws RemoteException {
        }

        @Override // com.oculus.os.IPostprocService
        public float getBacklightDutyCycle() throws RemoteException {
            return 0.0f;
        }

        @Override // com.oculus.os.IPostprocService
        public void setBacklightDutyCycle(float backlightDC) throws RemoteException {
        }

        @Override // com.oculus.os.IPostprocService
        public void getPanelChromaticities(double[] chromaticities) throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IPostprocService {
        private static final String DESCRIPTOR = "com.oculus.os.IPostprocService";
        static final int TRANSACTION_getBacklightDutyCycle = 4;
        static final int TRANSACTION_getPanelChromaticities = 6;
        static final int TRANSACTION_getPanelColorCorrection = 2;
        static final int TRANSACTION_setBacklightDutyCycle = 5;
        static final int TRANSACTION_setBlackLevel = 1;
        static final int TRANSACTION_setPanelColorCorrection = 3;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IPostprocService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IPostprocService)) {
                return new Proxy(obj);
            }
            return (IPostprocService) iin;
        }

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            float[] _arg0;
            double[] _arg02;
            if (code != 1598968902) {
                switch (code) {
                    case 1:
                        data.enforceInterface(DESCRIPTOR);
                        setBlackLevel(data.readFloat());
                        reply.writeNoException();
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        int _arg0_length = data.readInt();
                        if (_arg0_length < 0) {
                            _arg0 = null;
                        } else {
                            _arg0 = new float[_arg0_length];
                        }
                        getPanelColorCorrection(_arg0);
                        reply.writeNoException();
                        reply.writeFloatArray(_arg0);
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        setPanelColorCorrection(data.createFloatArray(), data.readInt());
                        reply.writeNoException();
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        float _result = getBacklightDutyCycle();
                        reply.writeNoException();
                        reply.writeFloat(_result);
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        setBacklightDutyCycle(data.readFloat());
                        reply.writeNoException();
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        int _arg0_length2 = data.readInt();
                        if (_arg0_length2 < 0) {
                            _arg02 = null;
                        } else {
                            _arg02 = new double[_arg0_length2];
                        }
                        getPanelChromaticities(_arg02);
                        reply.writeNoException();
                        reply.writeDoubleArray(_arg02);
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
        public static class Proxy implements IPostprocService {
            public static IPostprocService sDefaultImpl;
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

            @Override // com.oculus.os.IPostprocService
            public void setBlackLevel(float newBlackLevel) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(newBlackLevel);
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setBlackLevel(newBlackLevel);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.os.IPostprocService
            public void getPanelColorCorrection(float[] payload) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (payload == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(payload.length);
                    }
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.readFloatArray(payload);
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getPanelColorCorrection(payload);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.os.IPostprocService
            public void setPanelColorCorrection(float[] payload, int side) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloatArray(payload);
                    _data.writeInt(side);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setPanelColorCorrection(payload, side);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.os.IPostprocService
            public float getBacklightDutyCycle() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(4, _data, _reply, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBacklightDutyCycle();
                    }
                    _reply.readException();
                    float _result = _reply.readFloat();
                    _reply.recycle();
                    _data.recycle();
                    return _result;
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.os.IPostprocService
            public void setBacklightDutyCycle(float backlightDC) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeFloat(backlightDC);
                    if (this.mRemote.transact(5, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().setBacklightDutyCycle(backlightDC);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // com.oculus.os.IPostprocService
            public void getPanelChromaticities(double[] chromaticities) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (chromaticities == null) {
                        _data.writeInt(-1);
                    } else {
                        _data.writeInt(chromaticities.length);
                    }
                    if (this.mRemote.transact(6, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.readDoubleArray(chromaticities);
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getPanelChromaticities(chromaticities);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IPostprocService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IPostprocService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
