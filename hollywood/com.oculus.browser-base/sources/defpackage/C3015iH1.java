package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.gcm.PendingCallback;

/* renamed from: iH1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C3015iH1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new PendingCallback(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new PendingCallback[i];
    }
}
