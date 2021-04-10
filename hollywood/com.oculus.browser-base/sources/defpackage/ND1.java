package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaQueueItem;
import com.oculus.os.Version;

/* renamed from: ND1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ND1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 0;
        boolean z = false;
        double d = 0.0d;
        double d2 = 0.0d;
        double d3 = 0.0d;
        MediaInfo mediaInfo = null;
        long[] jArr = null;
        String str = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    mediaInfo = (MediaInfo) AbstractC5588xO0.d(parcel, readInt, MediaInfo.CREATOR);
                    break;
                case 3:
                    i = AbstractC5588xO0.p(parcel, readInt);
                    break;
                case 4:
                    z = AbstractC5588xO0.k(parcel, readInt);
                    break;
                case 5:
                    d = AbstractC5588xO0.l(parcel, readInt);
                    break;
                case 6:
                    d2 = AbstractC5588xO0.l(parcel, readInt);
                    break;
                case Version.VERSION_7 /*{ENCODED_INT: 7}*/:
                    d3 = AbstractC5588xO0.l(parcel, readInt);
                    break;
                case Version.VERSION_8 /*{ENCODED_INT: 8}*/:
                    jArr = AbstractC5588xO0.c(parcel, readInt);
                    break;
                case Version.VERSION_9 /*{ENCODED_INT: 9}*/:
                    str = AbstractC5588xO0.e(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new MediaQueueItem(mediaInfo, i, z, d, d2, d3, jArr, str);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new MediaQueueItem[i];
    }
}
