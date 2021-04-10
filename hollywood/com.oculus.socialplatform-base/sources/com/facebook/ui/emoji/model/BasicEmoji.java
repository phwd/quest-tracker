package com.facebook.ui.emoji.model;

import X.C10561ol;
import android.os.Parcel;
import android.os.Parcelable;

public final class BasicEmoji extends Emoji {
    public static final Parcelable.Creator<Emoji> CREATOR = new C10561ol();
    public final String A00;

    public final int describeContents() {
        return 0;
    }

    public final boolean equals(Object obj) {
        return this == obj || ((obj instanceof BasicEmoji) && this.A00.equals(((BasicEmoji) obj).A00));
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.A00);
    }

    public BasicEmoji(String str) {
        this.A00 = str;
    }

    public BasicEmoji(Parcel parcel) {
        this.A00 = parcel.readString();
    }
}
