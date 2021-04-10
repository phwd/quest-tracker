package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public enum Attachment implements Parcelable {
    PLATFORM("platform"),
    CROSS_PLATFORM("cross-platform");
    
    public static final Parcelable.Creator CREATOR = new C4878tC1();
    public final String I;

    /* access modifiers changed from: public */
    Attachment(String str) {
        this.I = str;
    }

    public static Attachment b(String str) {
        Attachment[] values = values();
        for (Attachment attachment : values) {
            if (str.equals(attachment.I)) {
                return attachment;
            }
        }
        throw new C2373eb(str);
    }

    public final int describeContents() {
        return 0;
    }

    public final String toString() {
        return this.I;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.I);
    }
}
