package com.facebook.common.callercontext;

import X.AnonymousClass006;
import X.AnonymousClass1lZ;
import android.os.Parcel;
import android.os.Parcelable;
import javax.annotation.Nullable;

public final class ContextChain implements Parcelable {
    public static final Parcelable.Creator<ContextChain> CREATOR = new AnonymousClass1lZ();
    @Nullable
    public String A00;
    public final int A01;
    @Nullable
    public final ContextChain A02;
    public final String A03;
    public final String A04;

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        String str = this.A00;
        if (str != null) {
            return str;
        }
        String A07 = AnonymousClass006.A07(this.A04, ":", this.A03);
        this.A00 = A07;
        ContextChain contextChain = this.A02;
        if (contextChain == null) {
            return A07;
        }
        String A002 = AnonymousClass006.A00(contextChain.toString(), '/', this.A00);
        this.A00 = A002;
        return A002;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.A04);
        parcel.writeString(this.A03);
        parcel.writeInt(this.A01);
        parcel.writeParcelable(this.A02, i);
    }

    public ContextChain(Parcel parcel) {
        this.A04 = parcel.readString();
        this.A03 = parcel.readString();
        this.A01 = parcel.readInt();
        this.A02 = (ContextChain) parcel.readParcelable(ContextChain.class.getClassLoader());
    }

    public final boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }

    public final int hashCode() {
        return super.hashCode();
    }
}
