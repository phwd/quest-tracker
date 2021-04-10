package defpackage;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.common.images.WebImage;
import java.util.ArrayList;

/* renamed from: CD1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class CD1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        ArrayList arrayList = null;
        int i = 0;
        Bundle bundle = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 2) {
                arrayList = AbstractC5588xO0.i(parcel, readInt, WebImage.CREATOR);
            } else if (i2 == 3) {
                bundle = AbstractC5588xO0.a(parcel, readInt);
            } else if (i2 != 4) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                i = AbstractC5588xO0.p(parcel, readInt);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new MediaMetadata(arrayList, bundle, i);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new MediaMetadata[i];
    }
}
