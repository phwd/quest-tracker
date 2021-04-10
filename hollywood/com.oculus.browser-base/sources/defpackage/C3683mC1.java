package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.VastAdsRequest;
import com.oculus.os.Version;

/* renamed from: mC1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3683mC1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        long j = 0;
        long j2 = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        String str8 = null;
        String str9 = null;
        VastAdsRequest vastAdsRequest = null;
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
                    j = AbstractC5588xO0.r(parcel, readInt);
                    break;
                case 5:
                    str3 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 6:
                    str4 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_7:
                    str5 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_8:
                    str6 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_9:
                    str7 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_10:
                    str8 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_11:
                    j2 = AbstractC5588xO0.r(parcel, readInt);
                    break;
                case Version.VERSION_12:
                    str9 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_13:
                    vastAdsRequest = (VastAdsRequest) AbstractC5588xO0.d(parcel, readInt, VastAdsRequest.CREATOR);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new AdBreakClipInfo(str, str2, j, str3, str4, str5, str6, str7, str8, j2, str9, vastAdsRequest);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new AdBreakClipInfo[i];
    }
}
