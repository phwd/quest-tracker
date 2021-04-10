package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.RatingCompat;

/* renamed from: TJ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TJ0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new RatingCompat(parcel.readInt(), parcel.readFloat());
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new RatingCompat[i];
    }
}
