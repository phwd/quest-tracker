package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AccountChangeEvent extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C5048uC1();
    public final int F;
    public final long G;
    public final String H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final int f9634J;
    public final String K;

    public AccountChangeEvent(int i, long j, String str, int i2, int i3, String str2) {
        this.F = i;
        this.G = j;
        Objects.requireNonNull(str, "null reference");
        this.H = str;
        this.I = i2;
        this.f9634J = i3;
        this.K = str2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AccountChangeEvent) {
            AccountChangeEvent accountChangeEvent = (AccountChangeEvent) obj;
            return this.F == accountChangeEvent.F && this.G == accountChangeEvent.G && AbstractC0895Oq0.a(this.H, accountChangeEvent.H) && this.I == accountChangeEvent.I && this.f9634J == accountChangeEvent.f9634J && AbstractC0895Oq0.a(this.K, accountChangeEvent.K);
        }
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.F), Long.valueOf(this.G), this.H, Integer.valueOf(this.I), Integer.valueOf(this.f9634J), this.K});
    }

    public String toString() {
        int i = this.I;
        String str = i != 1 ? i != 2 ? i != 3 ? i != 4 ? "UNKNOWN" : "RENAMED_TO" : "RENAMED_FROM" : "REMOVED" : "ADDED";
        String str2 = this.H;
        String str3 = this.K;
        int i2 = this.f9634J;
        StringBuilder sb = new StringBuilder(String.valueOf(str3).length() + str.length() + String.valueOf(str2).length() + 91);
        sb.append("AccountChangeEvent {accountName = ");
        sb.append(str2);
        sb.append(", changeType = ");
        sb.append(str);
        sb.append(", changeData = ");
        sb.append(str3);
        sb.append(", eventIndex = ");
        sb.append(i2);
        sb.append("}");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        long j = this.G;
        AbstractC5758yO0.o(parcel, 2, 8);
        parcel.writeLong(j);
        AbstractC5758yO0.g(parcel, 3, this.H, false);
        int i3 = this.I;
        AbstractC5758yO0.o(parcel, 4, 4);
        parcel.writeInt(i3);
        int i4 = this.f9634J;
        AbstractC5758yO0.o(parcel, 5, 4);
        parcel.writeInt(i4);
        AbstractC5758yO0.g(parcel, 6, this.K, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
