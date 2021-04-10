package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.vision.barcode.Barcode;
import com.oculus.os.Version;

/* renamed from: mH1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3698mH1 implements Parcelable.Creator {
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
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new Barcode.PersonName(str, str2, str3, str4, str5, str6, str7);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new Barcode.PersonName[i];
    }
}
