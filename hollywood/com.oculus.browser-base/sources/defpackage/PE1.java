package defpackage;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: PE1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class PE1 {

    /* renamed from: a  reason: collision with root package name */
    public static final /* synthetic */ int f8678a = 0;

    static {
        PE1.class.getClassLoader();
    }

    public static Parcelable a(Parcel parcel, Parcelable.Creator creator) {
        if (parcel.readInt() == 0) {
            return null;
        }
        return (Parcelable) creator.createFromParcel(parcel);
    }
}
