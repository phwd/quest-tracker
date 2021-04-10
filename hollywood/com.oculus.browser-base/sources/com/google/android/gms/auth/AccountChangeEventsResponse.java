package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.List;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AccountChangeEventsResponse extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C2155dF1();
    public final int F;
    public final List G;

    public AccountChangeEventsResponse(int i, List list) {
        this.F = i;
        Objects.requireNonNull(list, "null reference");
        this.G = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.k(parcel, 2, this.G, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
