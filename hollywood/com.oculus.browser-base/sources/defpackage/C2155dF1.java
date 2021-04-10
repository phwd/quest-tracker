package defpackage;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.auth.AccountChangeEvent;
import com.google.android.gms.auth.AccountChangeEventsResponse;
import java.util.ArrayList;

/* renamed from: dF1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C2155dF1 implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public final Object createFromParcel(Parcel parcel) {
        int u = AbstractC5588xO0.u(parcel);
        int i = 0;
        ArrayList arrayList = null;
        while (parcel.dataPosition() < u) {
            int readInt = parcel.readInt();
            int i2 = 65535 & readInt;
            if (i2 == 1) {
                i = AbstractC5588xO0.p(parcel, readInt);
            } else if (i2 != 2) {
                AbstractC5588xO0.t(parcel, readInt);
            } else {
                arrayList = AbstractC5588xO0.i(parcel, readInt, AccountChangeEvent.CREATOR);
            }
        }
        AbstractC5588xO0.j(parcel, u);
        return new AccountChangeEventsResponse(i, arrayList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Object[] newArray(int i) {
        return new AccountChangeEventsResponse[i];
    }
}
