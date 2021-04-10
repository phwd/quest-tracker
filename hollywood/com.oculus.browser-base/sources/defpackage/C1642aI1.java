package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.common.images.WebImage;
import com.oculus.os.Version;
import java.util.ArrayList;

/* renamed from: aI1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C1642aI1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        ArrayList arrayList = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        byte[] bArr = null;
        String str9 = null;
        int i4 = -1;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 3:
                    str2 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 4:
                    str3 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 5:
                    str4 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 6:
                    str5 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    i = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    arrayList = AbstractC5588xO0.i(parcel, readInt, WebImage.CREATOR);
                    break;
                case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                    i2 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                    i4 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                    str6 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
                    str7 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                    i3 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_14 /*{ENCODED_INT: 14}*/:
                    str8 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_15 /*{ENCODED_INT: 15}*/:
                    bArr = AbstractC5588xO0.b(parcel, readInt);
                    break;
                case Version.VERSION_16 /*{ENCODED_INT: 16}*/:
                    str9 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new CastDevice(str, str2, str3, str4, str5, i, arrayList, i2, i4, str6, str7, i3, str8, bArr, str9);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new CastDevice[i];
    }
}
