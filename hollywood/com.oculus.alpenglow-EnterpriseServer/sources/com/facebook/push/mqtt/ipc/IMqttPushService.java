package com.facebook.push.mqtt.ipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.facebook.push.mqtt.ipc.MqttPubAckCallback;
import com.facebook.push.mqtt.ipc.MqttPublishListener;
import com.facebook.rti.mqtt.protocol.messages.SubscribeTopic;
import java.util.List;

public interface IMqttPushService extends IInterface {

    public static abstract class Stub extends Binder implements IMqttPushService {
        public final IBinder asBinder() {
            return this;
        }

        public static class Proxy implements IMqttPushService {
            public static IMqttPushService A01;
            public IBinder A00;

            public Proxy(IBinder iBinder) {
                this.A00 = iBinder;
            }

            @Override // com.facebook.push.mqtt.ipc.IMqttPushService
            public final boolean A1n(long j) throws RemoteException {
                IMqttPushService iMqttPushService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.mqtt.ipc.IMqttPushService");
                    obtain.writeLong(j);
                    boolean z = false;
                    if (!this.A00.transact(3, obtain, obtain2, 0) && (iMqttPushService = A01) != null) {
                        return iMqttPushService.A1n(j);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.facebook.push.mqtt.ipc.IMqttPushService
            public final String A34() throws RemoteException {
                String str;
                IMqttPushService iMqttPushService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.mqtt.ipc.IMqttPushService");
                    if (this.A00.transact(12, obtain, obtain2, 0) || (iMqttPushService = A01) == null) {
                        obtain2.readException();
                        str = obtain2.readString();
                    } else {
                        str = iMqttPushService.A34();
                    }
                    return str;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.facebook.push.mqtt.ipc.IMqttPushService
            public final String A3D() throws RemoteException {
                String str;
                IMqttPushService iMqttPushService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.mqtt.ipc.IMqttPushService");
                    if (this.A00.transact(10, obtain, obtain2, 0) || (iMqttPushService = A01) == null) {
                        obtain2.readException();
                        str = obtain2.readString();
                    } else {
                        str = iMqttPushService.A3D();
                    }
                    return str;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.facebook.push.mqtt.ipc.IMqttPushService
            public final String A46() throws RemoteException {
                String str;
                IMqttPushService iMqttPushService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.mqtt.ipc.IMqttPushService");
                    if (this.A00.transact(11, obtain, obtain2, 0) || (iMqttPushService = A01) == null) {
                        obtain2.readException();
                        str = obtain2.readString();
                    } else {
                        str = iMqttPushService.A46();
                    }
                    return str;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.facebook.push.mqtt.ipc.IMqttPushService
            public final boolean A5N() throws RemoteException {
                IMqttPushService iMqttPushService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.mqtt.ipc.IMqttPushService");
                    boolean z = false;
                    if (!this.A00.transact(1, obtain, obtain2, 0) && (iMqttPushService = A01) != null) {
                        return iMqttPushService.A5N();
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.facebook.push.mqtt.ipc.IMqttPushService
            public final int A6y(String str, byte[] bArr, int i, MqttPublishListener mqttPublishListener) throws RemoteException {
                IBinder iBinder;
                int i2;
                IMqttPushService iMqttPushService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.mqtt.ipc.IMqttPushService");
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i);
                    if (mqttPublishListener != null) {
                        iBinder = mqttPublishListener.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.A00.transact(5, obtain, obtain2, 0) || (iMqttPushService = A01) == null) {
                        obtain2.readException();
                        i2 = obtain2.readInt();
                    } else {
                        i2 = iMqttPushService.A6y(str, bArr, i, mqttPublishListener);
                    }
                    return i2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.facebook.push.mqtt.ipc.IMqttPushService
            public final boolean A6z(String str, byte[] bArr, long j, MqttPublishListener mqttPublishListener, long j2) throws RemoteException {
                IBinder iBinder;
                IMqttPushService iMqttPushService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.mqtt.ipc.IMqttPushService");
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    obtain.writeLong(j);
                    if (mqttPublishListener != null) {
                        iBinder = mqttPublishListener.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeLong(j2);
                    boolean z = false;
                    if (!this.A00.transact(6, obtain, obtain2, 0) && (iMqttPushService = A01) != null) {
                        return iMqttPushService.A6z(str, bArr, j, mqttPublishListener, j2);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.facebook.push.mqtt.ipc.IMqttPushService
            public final int A71(String str, byte[] bArr, long j, MqttPubAckCallback mqttPubAckCallback) throws RemoteException {
                IBinder iBinder;
                int i;
                IMqttPushService iMqttPushService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.mqtt.ipc.IMqttPushService");
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    obtain.writeLong(j);
                    if (mqttPubAckCallback != null) {
                        iBinder = mqttPubAckCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.A00.transact(9, obtain, obtain2, 0) || (iMqttPushService = A01) == null) {
                        obtain2.readException();
                        i = obtain2.readInt();
                    } else {
                        i = iMqttPushService.A71(str, bArr, j, mqttPubAckCallback);
                    }
                    return i;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.facebook.push.mqtt.ipc.IMqttPushService
            public final int A72(String str, byte[] bArr, int i, MqttPubAckCallback mqttPubAckCallback) throws RemoteException {
                IBinder iBinder;
                int i2;
                IMqttPushService iMqttPushService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.mqtt.ipc.IMqttPushService");
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i);
                    if (mqttPubAckCallback != null) {
                        iBinder = mqttPubAckCallback.asBinder();
                    } else {
                        iBinder = null;
                    }
                    obtain.writeStrongBinder(iBinder);
                    if (this.A00.transact(8, obtain, obtain2, 0) || (iMqttPushService = A01) == null) {
                        obtain2.readException();
                        i2 = obtain2.readInt();
                    } else {
                        i2 = iMqttPushService.A72(str, bArr, i, mqttPubAckCallback);
                    }
                    return i2;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.facebook.push.mqtt.ipc.IMqttPushService
            public final boolean A8W(List<SubscribeTopic> list, int i) throws RemoteException {
                IMqttPushService iMqttPushService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.mqtt.ipc.IMqttPushService");
                    obtain.writeTypedList(list);
                    obtain.writeInt(i);
                    boolean z = false;
                    if (!this.A00.transact(4, obtain, obtain2, 0) && (iMqttPushService = A01) != null) {
                        return iMqttPushService.A8W(list, i);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.facebook.push.mqtt.ipc.IMqttPushService
            public final boolean isConnected() throws RemoteException {
                IMqttPushService iMqttPushService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.mqtt.ipc.IMqttPushService");
                    boolean z = false;
                    if (!this.A00.transact(2, obtain, obtain2, 0) && (iMqttPushService = A01) != null) {
                        return iMqttPushService.isConnected();
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.A00;
            }

            @Override // com.facebook.push.mqtt.ipc.IMqttPushService
            public final boolean A70(String str, byte[] bArr, long j, MqttPublishListener mqttPublishListener, long j2, String str2) throws RemoteException {
                IMqttPushService iMqttPushService;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.mqtt.ipc.IMqttPushService");
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    obtain.writeLong(j);
                    obtain.writeStrongBinder(mqttPublishListener != null ? mqttPublishListener.asBinder() : null);
                    obtain.writeLong(j2);
                    obtain.writeString(str2);
                    boolean z = false;
                    if (!this.A00.transact(7, obtain, obtain2, 0) && (iMqttPushService = A01) != null) {
                        return iMqttPushService.A70(str, bArr, j, mqttPublishListener, j2, str2);
                    }
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static IMqttPushService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.facebook.push.mqtt.ipc.IMqttPushService");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IMqttPushService)) {
                return new Proxy(iBinder);
            }
            return (IMqttPushService) queryLocalInterface;
        }

        public static boolean setDefaultImpl(IMqttPushService iMqttPushService) {
            if (Proxy.A01 != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (iMqttPushService == null) {
                return false;
            } else {
                Proxy.A01 = iMqttPushService;
                return true;
            }
        }

        public Stub() {
            attachInterface(this, "com.facebook.push.mqtt.ipc.IMqttPushService");
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            String str;
            int i3;
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface("com.facebook.push.mqtt.ipc.IMqttPushService");
                        i3 = A5N();
                        parcel2.writeNoException();
                        parcel2.writeInt(i3 == 1 ? 1 : 0);
                        return true;
                    case 2:
                        parcel.enforceInterface("com.facebook.push.mqtt.ipc.IMqttPushService");
                        i3 = isConnected();
                        parcel2.writeNoException();
                        parcel2.writeInt(i3 == 1 ? 1 : 0);
                        return true;
                    case 3:
                        parcel.enforceInterface("com.facebook.push.mqtt.ipc.IMqttPushService");
                        i3 = A1n(parcel.readLong());
                        parcel2.writeNoException();
                        parcel2.writeInt(i3 == 1 ? 1 : 0);
                        return true;
                    case 4:
                        parcel.enforceInterface("com.facebook.push.mqtt.ipc.IMqttPushService");
                        i3 = A8W(parcel.createTypedArrayList(SubscribeTopic.CREATOR), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(i3 == 1 ? 1 : 0);
                        return true;
                    case 5:
                        parcel.enforceInterface("com.facebook.push.mqtt.ipc.IMqttPushService");
                        i3 = A6y(parcel.readString(), parcel.createByteArray(), parcel.readInt(), MqttPublishListener.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeInt(i3 == 1 ? 1 : 0);
                        return true;
                    case 6:
                        parcel.enforceInterface("com.facebook.push.mqtt.ipc.IMqttPushService");
                        i3 = A6z(parcel.readString(), parcel.createByteArray(), parcel.readLong(), MqttPublishListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong());
                        parcel2.writeNoException();
                        parcel2.writeInt(i3 == 1 ? 1 : 0);
                        return true;
                    case 7:
                        parcel.enforceInterface("com.facebook.push.mqtt.ipc.IMqttPushService");
                        i3 = A70(parcel.readString(), parcel.createByteArray(), parcel.readLong(), MqttPublishListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readLong(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(i3 == 1 ? 1 : 0);
                        return true;
                    case 8:
                        parcel.enforceInterface("com.facebook.push.mqtt.ipc.IMqttPushService");
                        i3 = A72(parcel.readString(), parcel.createByteArray(), parcel.readInt(), MqttPubAckCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeInt(i3 == 1 ? 1 : 0);
                        return true;
                    case 9:
                        parcel.enforceInterface("com.facebook.push.mqtt.ipc.IMqttPushService");
                        i3 = A71(parcel.readString(), parcel.createByteArray(), parcel.readLong(), MqttPubAckCallback.Stub.asInterface(parcel.readStrongBinder()));
                        parcel2.writeNoException();
                        parcel2.writeInt(i3 == 1 ? 1 : 0);
                        return true;
                    case 10:
                        parcel.enforceInterface("com.facebook.push.mqtt.ipc.IMqttPushService");
                        str = A3D();
                        parcel2.writeNoException();
                        parcel2.writeString(str);
                        return true;
                    case 11:
                        parcel.enforceInterface("com.facebook.push.mqtt.ipc.IMqttPushService");
                        str = A46();
                        parcel2.writeNoException();
                        parcel2.writeString(str);
                        return true;
                    case 12:
                        parcel.enforceInterface("com.facebook.push.mqtt.ipc.IMqttPushService");
                        str = A34();
                        parcel2.writeNoException();
                        parcel2.writeString(str);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            } else {
                parcel2.writeString("com.facebook.push.mqtt.ipc.IMqttPushService");
                return true;
            }
        }

        public static IMqttPushService getDefaultImpl() {
            return Proxy.A01;
        }
    }

    boolean A1n(long j) throws RemoteException;

    String A34() throws RemoteException;

    String A3D() throws RemoteException;

    String A46() throws RemoteException;

    boolean A5N() throws RemoteException;

    int A6y(String str, byte[] bArr, int i, MqttPublishListener mqttPublishListener) throws RemoteException;

    boolean A6z(String str, byte[] bArr, long j, MqttPublishListener mqttPublishListener, long j2) throws RemoteException;

    boolean A70(String str, byte[] bArr, long j, MqttPublishListener mqttPublishListener, long j2, String str2) throws RemoteException;

    int A71(String str, byte[] bArr, long j, MqttPubAckCallback mqttPubAckCallback) throws RemoteException;

    int A72(String str, byte[] bArr, int i, MqttPubAckCallback mqttPubAckCallback) throws RemoteException;

    boolean A8W(List<SubscribeTopic> list, int i) throws RemoteException;

    boolean isConnected() throws RemoteException;
}
