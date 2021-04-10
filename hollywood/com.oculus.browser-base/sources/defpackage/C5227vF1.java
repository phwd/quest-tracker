package defpackage;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.images.WebImage;
import com.oculus.os.Version;
import java.util.ArrayList;

/* renamed from: vF1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5227vF1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        String str = null;
        String str2 = null;
        ArrayList arrayList = null;
        String str3 = null;
        Uri uri = null;
        String str4 = null;
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
                    AbstractC5588xO0.i(parcel, readInt, WebImage.CREATOR);
                    break;
                case 5:
                    arrayList = AbstractC5588xO0.g(parcel, readInt);
                    break;
                case 6:
                    str3 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                case Version.VERSION_7:
                    uri = (Uri) AbstractC5588xO0.d(parcel, readInt, Uri.CREATOR);
                    break;
                case Version.VERSION_8:
                    str4 = AbstractC5588xO0.e(parcel, readInt);
                    break;
                default:
                    AbstractC5588xO0.t(parcel, readInt);
                    break;
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new ApplicationMetadata(str, str2, arrayList, str3, uri, str4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new ApplicationMetadata[i];
    }
}
