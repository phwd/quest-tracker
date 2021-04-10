package com.facebook.share.model;

import X.AnonymousClass1jQ;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public final class SharePhoto extends ShareMedia {
    public static final Parcelable.Creator<SharePhoto> CREATOR = new AnonymousClass1jQ();
    public final Uri A00;
    public final Bitmap A01;
    public final String A02;
    public final boolean A03;

    public SharePhoto(Parcel parcel) {
        super(parcel);
        this.A01 = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.A00 = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.A03 = parcel.readByte() != 0;
        this.A02 = parcel.readString();
    }

    @Override // com.facebook.share.model.ShareMedia
    public final void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.A01, 0);
        parcel.writeParcelable(this.A00, 0);
        parcel.writeByte(this.A03 ? (byte) 1 : 0);
        parcel.writeString(this.A02);
    }
}
