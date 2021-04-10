package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;
import com.oculus.os.Version;

/* renamed from: xG1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5570xG1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        String str10 = null;
        String str11 = null;
        String str12 = null;
        String str13 = null;
        String str14 = null;
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
                case Version.VERSION_7:
                    str6 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_8:
                    str7 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_9:
                    str8 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_10:
                    str9 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_11:
                    str10 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_12:
                    str11 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_13:
                    str12 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_14:
                    str13 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_15:
                    str14 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new Barcode.DriverLicense(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, str12, str13, str14);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode.DriverLicense[i];
    }
}
