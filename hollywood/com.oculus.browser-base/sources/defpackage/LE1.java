package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.TextTrackStyle;
import com.oculus.os.Version;

/* renamed from: LE1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class LE1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        String str = null;
        String str2 = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        float f = 0.0f;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    f = AbstractC5588xO0.n(parcel, readInt);
                    break;
                case 3:
                    i = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 4:
                    i2 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 5:
                    i3 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 6:
                    i4 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    i5 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    i6 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                    i7 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                    i8 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
                    i9 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                    str2 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new TextTrackStyle(f, i, i2, i3, i4, i5, i6, i7, str, i8, i9, str2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new TextTrackStyle[i];
    }
}
