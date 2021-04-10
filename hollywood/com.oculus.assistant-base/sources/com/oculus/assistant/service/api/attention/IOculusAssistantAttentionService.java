package com.oculus.assistant.service.api.attention;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener;

public interface IOculusAssistantAttentionService extends IInterface {

    public abstract class Stub extends Binder implements IOculusAssistantAttentionService {
        public static final String DESCRIPTOR = "com.oculus.assistant.service.api.attention.IOculusAssistantAttentionService";
        public static final int TRANSACTION_registerAttentionListener = 1;
        public static final int TRANSACTION_removeAttentionListener = 2;

        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            IOculusAssistantAttentionListener proxy;
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                IBinder readStrongBinder = parcel.readStrongBinder();
                if (readStrongBinder == null) {
                    proxy = null;
                } else {
                    IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener");
                    if (queryLocalInterface == null || !(queryLocalInterface instanceof IOculusAssistantAttentionListener)) {
                        proxy = new IOculusAssistantAttentionListener.Stub.Proxy(readStrongBinder);
                    } else {
                        proxy = (IOculusAssistantAttentionListener) queryLocalInterface;
                    }
                }
                String registerAttentionListener = registerAttentionListener(proxy);
                parcel2.writeNoException();
                parcel2.writeString(registerAttentionListener);
                return true;
            } else if (i == 2) {
                parcel.enforceInterface(DESCRIPTOR);
                removeAttentionListener(parcel.readString());
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
        }

        public final class Proxy implements IOculusAssistantAttentionService {
            public IBinder A00;

            public Proxy(IBinder iBinder) {
                this.A00 = iBinder;
            }

            public final IBinder asBinder() {
                return this.A00;
            }

            @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionService
            public final String registerAttentionListener(IOculusAssistantAttentionListener iOculusAssistantAttentionListener) {
                IBinder iBinder;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (iOculusAssistantAttentionListener != null) {
                        iBinder = iOculusAssistantAttentionListener.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.A00.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionService
            public final void removeAttentionListener(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    this.A00.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IOculusAssistantAttentionService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IOculusAssistantAttentionService)) {
                return new Proxy(iBinder);
            }
            return (IOculusAssistantAttentionService) queryLocalInterface;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }
    }

    String registerAttentionListener(IOculusAssistantAttentionListener iOculusAssistantAttentionListener);

    void removeAttentionListener(String str);
}
