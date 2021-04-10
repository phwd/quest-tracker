package com.oculus.assistant.service.api.remoteaudiosource;

import android.os.IBinder;
import android.os.Parcel;

public final class IRemoteAudioSource$Stub$Proxy implements IRemoteAudioSource {
    public IBinder A00;

    public IRemoteAudioSource$Stub$Proxy(IBinder iBinder) {
        this.A00 = iBinder;
    }

    @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
    public final String A2v() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource");
            this.A00.transact(5, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
    public final boolean A3b() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource");
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

    @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
    public final void A4S() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource");
            this.A00.transact(3, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    public final IBinder asBinder() {
        return this.A00;
    }

    @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
    public final void close() {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource");
            this.A00.transact(4, obtain, obtain2, 0);
            obtain2.readException();
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }

    @Override // com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource
    public final int read(byte[] bArr, int i, int i2) {
        Parcel obtain = Parcel.obtain();
        Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("com.oculus.assistant.service.api.remoteaudiosource.IRemoteAudioSource");
            obtain.writeByteArray(bArr);
            obtain.writeInt(i);
            obtain.writeInt(i2);
            this.A00.transact(1, obtain, obtain2, 0);
            obtain2.readException();
            int readInt = obtain2.readInt();
            obtain2.readByteArray(bArr);
            return readInt;
        } finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
