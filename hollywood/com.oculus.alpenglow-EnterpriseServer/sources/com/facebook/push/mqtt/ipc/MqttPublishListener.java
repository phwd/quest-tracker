package com.facebook.push.mqtt.ipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface MqttPublishListener extends IInterface {

    public static abstract class Stub extends Binder implements MqttPublishListener {
        public final IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public final boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface("com.facebook.push.mqtt.ipc.MqttPublishListener");
                A6g(parcel.readLong());
                return true;
            } else if (i == 2) {
                parcel.enforceInterface("com.facebook.push.mqtt.ipc.MqttPublishListener");
                A66();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString("com.facebook.push.mqtt.ipc.MqttPublishListener");
                return true;
            }
        }

        public static class Proxy implements MqttPublishListener {
            public static MqttPublishListener A01;
            public IBinder A00;

            public Proxy(IBinder iBinder) {
                this.A00 = iBinder;
            }

            @Override // com.facebook.push.mqtt.ipc.MqttPublishListener
            public final void A66() throws RemoteException {
                MqttPublishListener mqttPublishListener;
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.mqtt.ipc.MqttPublishListener");
                    if (!this.A00.transact(2, obtain, null, 1) && (mqttPublishListener = A01) != null) {
                        mqttPublishListener.A66();
                    }
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.facebook.push.mqtt.ipc.MqttPublishListener
            public final void A6g(long j) throws RemoteException {
                MqttPublishListener mqttPublishListener;
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.facebook.push.mqtt.ipc.MqttPublishListener");
                    obtain.writeLong(j);
                    if (!this.A00.transact(1, obtain, null, 1) && (mqttPublishListener = A01) != null) {
                        mqttPublishListener.A6g(j);
                    }
                } finally {
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.A00;
            }
        }

        public static MqttPublishListener asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.facebook.push.mqtt.ipc.MqttPublishListener");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof MqttPublishListener)) {
                return new Proxy(iBinder);
            }
            return (MqttPublishListener) queryLocalInterface;
        }

        public static boolean setDefaultImpl(MqttPublishListener mqttPublishListener) {
            if (Proxy.A01 != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            } else if (mqttPublishListener == null) {
                return false;
            } else {
                Proxy.A01 = mqttPublishListener;
                return true;
            }
        }

        public Stub() {
            attachInterface(this, "com.facebook.push.mqtt.ipc.MqttPublishListener");
        }

        public static MqttPublishListener getDefaultImpl() {
            return Proxy.A01;
        }
    }

    void A66() throws RemoteException;

    void A6g(long j) throws RemoteException;
}
