package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import org.chromium.third_party.android.datausagechart.NetworkStatsHistory;

/* renamed from: Nn0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0828Nn0 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Object createFromParcel(Parcel parcel) {
        return new NetworkStatsHistory(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public Object[] newArray(int i) {
        return new NetworkStatsHistory[i];
    }
}
