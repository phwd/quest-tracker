package com.facebook.push.mqtt.ipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface MqttPubAckCallback extends IInterface {

    public static abstract class Stub extends Binder implements MqttPubAckCallback {
        public final IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.facebook.push.mqtt.ipc.MqttPubAckCallback");
                A6f(parcel.readInt());
            } else if (i == 2) {
                parcel.enforceInterface("com.facebook.push.mqtt.ipc.MqttPubAckCallback");
                A67(parcel.readInt());
            } else if (i == 3) {
                parcel.enforceInterface("com.facebook.push.mqtt.ipc.MqttPubAckCallback");
                A6O(parcel.readInt());
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.facebook.push.mqtt.ipc.MqttPubAckCallback");
                return true;
            }
            parcel2.writeNoException();
            return true;
        }

        public static class Proxy implements MqttPubAckCallback {
            public static MqttPubAckCallback A01;
            public IBinder A00;

            public Proxy(IBinder iBinder) {
                this.A00 = iBinder;
            }

            @Override // com.facebook.push.mqtt.ipc.MqttPubAckCallback
            public final void A67(int i) throws RemoteException {
                MqttPubAckCallback mqttPubAckCallback;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.mqtt.ipc.MqttPubAckCallback");
                    obtain.writeInt(i);
                    if (this.A00.transact(2, obtain, obtain2, 0) || (mqttPubAckCallback = A01) == null) {
                        obtain2.readException();
                    } else {
                        mqttPubAckCallback.A67(i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.facebook.push.mqtt.ipc.MqttPubAckCallback
            public final void A6O(int i) throws RemoteException {
                MqttPubAckCallback mqttPubAckCallback;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.mqtt.ipc.MqttPubAckCallback");
                    obtain.writeInt(i);
                    if (this.A00.transact(3, obtain, obtain2, 0) || (mqttPubAckCallback = A01) == null) {
                        obtain2.readException();
                    } else {
                        mqttPubAckCallback.A6O(i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.facebook.push.mqtt.ipc.MqttPubAckCallback
            public final void A6f(int i) throws RemoteException {
                MqttPubAckCallback mqttPubAckCallback;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.mqtt.ipc.MqttPubAckCallback");
                    obtain.writeInt(i);
                    if (this.A00.transact(1, obtain, obtain2, 0) || (mqttPubAckCallback = A01) == null) {
                        obtain2.readException();
                    } else {
                        mqttPubAckCallback.A6f(i);
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.A00;
            }
        }

        public static MqttPubAckCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.facebook.push.mqtt.ipc.MqttPubAckCallback");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof MqttPubAckCallback)) {
                return new Proxy(iBinder);
            }
            return (MqttPubAckCallback) queryLocalInterface;
        }

        public static boolean setDefaultImpl(MqttPubAckCallback mqttPubAckCallback) {
            if (Proxy.A01 != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (mqttPubAckCallback == null) {
                return false;
            } else {
                Proxy.A01 = mqttPubAckCallback;
                return true;
            }
        }

        public Stub() {
            attachInterface(this, "com.facebook.push.mqtt.ipc.MqttPubAckCallback");
        }

        public static MqttPubAckCallback getDefaultImpl() {
            return Proxy.A01;
        }
    }

    void A67(int i) throws RemoteException;

    void A6O(int i) throws RemoteException;

    void A6f(int i) throws RemoteException;
}
