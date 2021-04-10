package oculus.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface IVoiceAssistantService extends IInterface {
    void getMicData(MicrophoneData microphoneData) throws RemoteException;

    void initialize(boolean z) throws RemoteException;

    void resetWakeWordRecognition() throws RemoteException;

    void startAudioCapture() throws RemoteException;

    void stopAudioCapture() throws RemoteException;

    void stopWakeWordRecognition() throws RemoteException;

    public static class Default implements IVoiceAssistantService {
        @Override // oculus.internal.IVoiceAssistantService
        public void getMicData(MicrophoneData microphoneData) throws RemoteException {
        }

        @Override // oculus.internal.IVoiceAssistantService
        public void initialize(boolean useWakeWord) throws RemoteException {
        }

        @Override // oculus.internal.IVoiceAssistantService
        public void resetWakeWordRecognition() throws RemoteException {
        }

        @Override // oculus.internal.IVoiceAssistantService
        public void stopWakeWordRecognition() throws RemoteException {
        }

        @Override // oculus.internal.IVoiceAssistantService
        public void stopAudioCapture() throws RemoteException {
        }

        @Override // oculus.internal.IVoiceAssistantService
        public void startAudioCapture() throws RemoteException {
        }

        public IBinder asBinder() {
            return null;
        }
    }

    public static abstract class Stub extends Binder implements IVoiceAssistantService {
        private static final String DESCRIPTOR = "oculus.internal.IVoiceAssistantService";
        static final int TRANSACTION_getMicData = 1;
        static final int TRANSACTION_initialize = 2;
        static final int TRANSACTION_resetWakeWordRecognition = 3;
        static final int TRANSACTION_startAudioCapture = 6;
        static final int TRANSACTION_stopAudioCapture = 5;
        static final int TRANSACTION_stopWakeWordRecognition = 4;

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IVoiceAssistantService asInterface(IBinder obj) {
            if (obj == null) {
                return null;
            }
            IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
            if (iin == null || !(iin instanceof IVoiceAssistantService)) {
                return new Proxy(obj);
            }
            return (IVoiceAssistantService) iin;
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
                        MicrophoneData _arg0 = new MicrophoneData();
                        getMicData(_arg0);
                        reply.writeNoException();
                        reply.writeInt(1);
                        _arg0.writeToParcel(reply, 1);
                        return true;
                    case 2:
                        data.enforceInterface(DESCRIPTOR);
                        initialize(data.readInt() != 0);
                        reply.writeNoException();
                        return true;
                    case 3:
                        data.enforceInterface(DESCRIPTOR);
                        resetWakeWordRecognition();
                        reply.writeNoException();
                        return true;
                    case 4:
                        data.enforceInterface(DESCRIPTOR);
                        stopWakeWordRecognition();
                        reply.writeNoException();
                        return true;
                    case 5:
                        data.enforceInterface(DESCRIPTOR);
                        stopAudioCapture();
                        reply.writeNoException();
                        return true;
                    case 6:
                        data.enforceInterface(DESCRIPTOR);
                        startAudioCapture();
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
        public static class Proxy implements IVoiceAssistantService {
            public static IVoiceAssistantService sDefaultImpl;
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

            @Override // oculus.internal.IVoiceAssistantService
            public void getMicData(MicrophoneData microphoneData) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(1, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        if (_reply.readInt() != 0) {
                            microphoneData.readFromParcel(_reply);
                        }
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().getMicData(microphoneData);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVoiceAssistantService
            public void initialize(boolean useWakeWord) throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    _data.writeInt(useWakeWord ? 1 : 0);
                    if (this.mRemote.transact(2, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().initialize(useWakeWord);
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVoiceAssistantService
            public void resetWakeWordRecognition() throws RemoteException {
                Parcel _data = Parcel.obtain();
                Parcel _reply = Parcel.obtain();
                try {
                    _data.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (this.mRemote.transact(3, _data, _reply, 0) || Stub.getDefaultImpl() == null) {
                        _reply.readException();
                        _reply.recycle();
                        _data.recycle();
                        return;
                    }
                    Stub.getDefaultImpl().resetWakeWordRecognition();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVoiceAssistantService
            public void stopWakeWordRecognition() throws RemoteException {
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
                    Stub.getDefaultImpl().stopWakeWordRecognition();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVoiceAssistantService
            public void stopAudioCapture() throws RemoteException {
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
                    Stub.getDefaultImpl().stopAudioCapture();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }

            @Override // oculus.internal.IVoiceAssistantService
            public void startAudioCapture() throws RemoteException {
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
                    Stub.getDefaultImpl().startAudioCapture();
                } finally {
                    _reply.recycle();
                    _data.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IVoiceAssistantService impl) {
            if (Proxy.sDefaultImpl != null || impl == null) {
                return false;
            }
            Proxy.sDefaultImpl = impl;
            return true;
        }

        public static IVoiceAssistantService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
