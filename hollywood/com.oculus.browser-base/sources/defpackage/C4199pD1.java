package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.VastAdsRequest;
import com.oculus.os.Version;
import java.util.ArrayList;

/* renamed from: pD1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4199pD1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        long j = 0;
        long j2 = 0;
        String str = null;
        String str2 = null;
        MediaMetadata mediaMetadata = null;
        ArrayList arrayList = null;
        TextTrackStyle textTrackStyle = null;
        String str3 = null;
        ArrayList arrayList2 = null;
        ArrayList arrayList3 = null;
        String str4 = null;
        VastAdsRequest vastAdsRequest = null;
        int i = 0;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 3:
                    i = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 4:
                    str2 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case 5:
                    mediaMetadata = (MediaMetadata) AbstractC5588xO0.d(parcel, readInt, MediaMetadata.CREATOR);
                    break;
                case 6:
                    j = AbstractC5588xO0.r(parcel, readInt);
                    break;
                case Version.VERSION_7:
                    arrayList = AbstractC5588xO0.i(parcel, readInt, MediaTrack.CREATOR);
                    break;
                case Version.VERSION_8:
                    textTrackStyle = (TextTrackStyle) AbstractC5588xO0.d(parcel, readInt, TextTrackStyle.CREATOR);
                    break;
                case Version.VERSION_9:
                    str3 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_10:
                    arrayList2 = AbstractC5588xO0.i(parcel, readInt, AdBreakInfo.CREATOR);
                    break;
                case Version.VERSION_11:
                    arrayList3 = AbstractC5588xO0.i(parcel, readInt, AdBreakClipInfo.CREATOR);
                    break;
                case Version.VERSION_12:
                    str4 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_13:
                    vastAdsRequest = (VastAdsRequest) AbstractC5588xO0.d(parcel, readInt, VastAdsRequest.CREATOR);
                    break;
                case Version.VERSION_14:
                    j2 = AbstractC5588xO0.r(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new MediaInfo(str, i, str2, mediaMetadata, j, arrayList, textTrackStyle, str3, arrayList2, arrayList3, str4, vastAdsRequest, j2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new MediaInfo[i];
    }
}
