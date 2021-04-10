package com.facebook.papaya.client;

import X.H2;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.facebook.papaya.IPapayaCallback;
import com.facebook.papaya.IPapayaCallback$Stub$Proxy;
import com.facebook.papaya.IPapayaLogSink;
import com.facebook.papaya.IPapayaLogSink$Stub$Proxy;

public final class PapayaMetadataInternal implements Parcelable {
    public static final Parcelable.Creator CREATOR = new H2();
    public IPapayaCallback A00;
    public IPapayaLogSink A01;
    public PapayaMetadata A02;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.A02, i);
        parcel.writeStrongBinder(this.A01.asBinder());
        parcel.writeStrongBinder(this.A00.asBinder());
    }

    public PapayaMetadataInternal(Parcel parcel) {
        IPapayaLogSink iPapayaLogSink;
        this.A02 = (PapayaMetadata) parcel.readParcelable(PapayaMetadata.class.getClassLoader());
        IBinder readStrongBinder = parcel.readStrongBinder();
        IPapayaCallback iPapayaCallback = null;
        if (readStrongBinder != null) {
            IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.facebook.papaya.IPapayaLogSink");
            if (queryLocalInterface == null || !(queryLocalInterface instanceof IPapayaLogSink)) {
                iPapayaLogSink = new IPapayaLogSink$Stub$Proxy(readStrongBinder);
            } else {
                iPapayaLogSink = (IPapayaLogSink) queryLocalInterface;
            }
        } else {
            iPapayaLogSink = null;
        }
        this.A01 = iPapayaLogSink;
        IBinder readStrongBinder2 = parcel.readStrongBinder();
        if (!(readStrongBinder == null || readStrongBinder2 == null)) {
            IInterface queryLocalInterface2 = readStrongBinder2.queryLocalInterface("com.facebook.papaya.IPapayaCallback");
            iPapayaCallback = (queryLocalInterface2 == null || !(queryLocalInterface2 instanceof IPapayaCallback)) ? new IPapayaCallback$Stub$Proxy(readStrongBinder2) : (IPapayaCallback) queryLocalInterface2;
        }
        this.A00 = iPapayaCallback;
    }

    public PapayaMetadataInternal(PapayaMetadata papayaMetadata, IPapayaLogSink iPapayaLogSink, IPapayaCallback iPapayaCallback) {
        this.A02 = papayaMetadata;
        this.A01 = iPapayaLogSink;
        this.A00 = iPapayaCallback;
    }
}
