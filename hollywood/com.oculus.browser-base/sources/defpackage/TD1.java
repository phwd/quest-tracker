package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.AdBreakStatus;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.VideoInfo;
import com.oculus.os.Version;
import java.util.ArrayList;

/* renamed from: TD1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class TD1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        double d = 0.0d;
        double d2 = 0.0d;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        MediaInfo mediaInfo = null;
        long[] jArr = null;
        String str = null;
        ArrayList arrayList = null;
        AdBreakStatus adBreakStatus = null;
        VideoInfo videoInfo = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        boolean z = false;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        boolean z2 = false;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    mediaInfo = (MediaInfo) AbstractC5588xO0.d(parcel, readInt, MediaInfo.CREATOR);
                    break;
                case 3:
                    j = AbstractC5588xO0.r(parcel, readInt);
                    break;
                case 4:
                    i = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 5:
                    d = AbstractC5588xO0.l(parcel, readInt);
                    break;
                case 6:
                    i2 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    i3 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    j2 = AbstractC5588xO0.r(parcel, readInt);
                    break;
                case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                    j3 = AbstractC5588xO0.r(parcel, readInt);
                    break;
                case Version.VERSION_10 /*{ENCODED_INT: 10}*/:
                    d2 = AbstractC5588xO0.l(parcel, readInt);
                    break;
                case Version.VERSION_11 /*{ENCODED_INT: 11}*/:
                    z = AbstractC5588xO0.k(parcel, readInt);
                    break;
                case Version.VERSION_12 /*{ENCODED_INT: 12}*/:
                    jArr = AbstractC5588xO0.c(parcel, readInt);
                    break;
                case Version.VERSION_13 /*{ENCODED_INT: 13}*/:
                    i4 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_14 /*{ENCODED_INT: 14}*/:
                    i5 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_15 /*{ENCODED_INT: 15}*/:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_16 /*{ENCODED_INT: 16}*/:
                    i6 = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case Version.VERSION_17 /*{ENCODED_INT: 17}*/:
                    arrayList = AbstractC5588xO0.i(parcel, readInt, MediaQueueItem.CREATOR);
                    break;
                case Version.VERSION_18 /*{ENCODED_INT: 18}*/:
                    z2 = AbstractC5588xO0.k(parcel, readInt);
                    break;
                case Version.VERSION_19 /*{ENCODED_INT: 19}*/:
                    adBreakStatus = (AdBreakStatus) AbstractC5588xO0.d(parcel, readInt, AdBreakStatus.CREATOR);
                    break;
                case Version.VERSION_20 /*{ENCODED_INT: 20}*/:
                    videoInfo = (VideoInfo) AbstractC5588xO0.d(parcel, readInt, VideoInfo.CREATOR);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new MediaStatus(mediaInfo, j, i, d, i2, i3, j2, j3, d2, z, jArr, i4, i5, str, i6, arrayList, z2, adBreakStatus, videoInfo);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new MediaStatus[i];
    }
}
