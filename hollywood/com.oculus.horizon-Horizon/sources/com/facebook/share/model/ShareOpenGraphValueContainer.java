package com.facebook.share.model;

import X.AnonymousClass1jT;
import android.os.Bundle;
import android.os.Parcel;
import com.facebook.share.model.ShareOpenGraphValueContainer;

public abstract class ShareOpenGraphValueContainer<P extends ShareOpenGraphValueContainer, E extends AnonymousClass1jT> implements ShareModel {
    public final Bundle A00;

    public final int describeContents() {
        return 0;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.A00);
    }

    public ShareOpenGraphValueContainer(Parcel parcel) {
        this.A00 = parcel.readBundle(AnonymousClass1jT.class.getClassLoader());
    }
}
