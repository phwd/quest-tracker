package com.oculus.assistant.service.api.attention;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.oculus.aidl.OVRServiceInterface;

public interface IOculusAssistantAttentionListener extends IInterface {

    public abstract class Stub extends Binder implements IOculusAssistantAttentionListener {
        public final IBinder asBinder() {
            return this;
        }

        public final class Proxy implements IOculusAssistantAttentionListener {
            public IBinder A00;

            public Proxy(IBinder iBinder) {
                this.A00 = iBinder;
            }

            @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
            public final boolean A3r(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener");
                    obtain.writeString(str);
                    boolean z = false;
                    this.A00.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
            public final boolean A46(AssistantErrorType assistantErrorType) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener");
                    boolean z = true;
                    if (assistantErrorType != null) {
                        obtain.writeInt(1);
                        assistantErrorType.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.A00.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
            public final boolean A4C(boolean z) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener");
                    boolean z2 = true;
                    int i = 0;
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.A00.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    return z2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
            public final boolean A4D(String str) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener");
                    obtain.writeString(str);
                    boolean z = false;
                    this.A00.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
            public final boolean A4E(float f) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener");
                    obtain.writeFloat(f);
                    boolean z = false;
                    this.A00.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
            public final boolean A4G(AssistantInteractionState assistantInteractionState) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener");
                    boolean z = true;
                    if (assistantInteractionState != null) {
                        obtain.writeInt(1);
                        assistantInteractionState.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.A00.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z = false;
                    }
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener
            public final boolean A4O(String str, boolean z) {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener");
                    obtain.writeString(str);
                    boolean z2 = true;
                    int i = 0;
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.A00.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() == 0) {
                        z2 = false;
                    }
                    return z2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.A00;
            }
        }

        public Stub() {
            attachInterface(this, "com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener");
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) {
            boolean z;
            if (i != 1598968902) {
                boolean z2 = false;
                AssistantInteractionState assistantInteractionState = null;
                AssistantErrorType assistantErrorType = null;
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener");
                        if (parcel.readInt() != 0) {
                            assistantInteractionState = (AssistantInteractionState) AssistantInteractionState.CREATOR.createFromParcel(parcel);
                        }
                        z = A4G(assistantInteractionState);
                        break;
                    case 2:
                        parcel.enforceInterface("com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener");
                        z = A4E(parcel.readFloat());
                        break;
                    case 3:
                        parcel.enforceInterface("com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener");
                        String readString = parcel.readString();
                        if (parcel.readInt() != 0) {
                            z2 = true;
                        }
                        z = A4O(readString, z2);
                        break;
                    case 4:
                        parcel.enforceInterface("com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener");
                        z = A3r(parcel.readString());
                        break;
                    case 5:
                        parcel.enforceInterface("com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener");
                        if (parcel.readInt() != 0) {
                            z2 = true;
                        }
                        z = A4C(z2);
                        break;
                    case 6:
                        parcel.enforceInterface("com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener");
                        if (parcel.readInt() != 0) {
                            assistantErrorType = (AssistantErrorType) AssistantErrorType.CREATOR.createFromParcel(parcel);
                        }
                        z = A46(assistantErrorType);
                        break;
                    case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle:
                        parcel.enforceInterface("com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener");
                        z = A4D(parcel.readString());
                        break;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
                parcel2.writeNoException();
                int i3 = z ? 1 : 0;
                int i4 = z ? 1 : 0;
                int i5 = z ? 1 : 0;
                int i6 = z ? 1 : 0;
                int i7 = z ? 1 : 0;
                int i8 = z ? 1 : 0;
                int i9 = z ? 1 : 0;
                int i10 = z ? 1 : 0;
                parcel2.writeInt(i3);
                return true;
            }
            parcel2.writeString("com.oculus.assistant.service.api.attention.IOculusAssistantAttentionListener");
            return true;
        }
    }

    boolean A3r(String str);

    boolean A46(AssistantErrorType assistantErrorType);

    boolean A4C(boolean z);

    boolean A4D(String str);

    boolean A4E(float f);

    boolean A4G(AssistantInteractionState assistantInteractionState);

    boolean A4O(String str, boolean z);
}
