package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.Arrays;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Status extends AbstractSafeParcelable implements AM0, ReflectedParcelable {
    public static final Parcelable.Creator CREATOR = new C4884tE1();
    public static final Status F = new Status(0);
    public static final Status G = new Status(8);
    public static final Status H = new Status(15);
    public static final Status I = new Status(16);

    /* renamed from: J  reason: collision with root package name */
    public final int f9655J;
    public final int K;
    public final String L;
    public final PendingIntent M;

    static {
        new Status(14);
        new Status(17);
        new Status(18);
    }

    public Status(int i, int i2, String str, PendingIntent pendingIntent) {
        this.f9655J = i;
        this.K = i2;
        this.L = str;
        this.M = pendingIntent;
    }

    @Override // defpackage.AM0
    public final Status b() {
        return this;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        if (this.f9655J != status.f9655J || this.K != status.K || !AbstractC0895Oq0.a(this.L, status.L) || !AbstractC0895Oq0.a(this.M, status.M)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.f9655J), Integer.valueOf(this.K), this.L, this.M});
    }

    public final String toString() {
        C0834Nq0 nq0 = new C0834Nq0(this, null);
        String str = this.L;
        if (str == null) {
            str = AbstractC1924bw.a(this.K);
        }
        nq0.a("statusCode", str);
        nq0.a("resolution", this.M);
        return nq0.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.K;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.g(parcel, 2, this.L, false);
        AbstractC5758yO0.f(parcel, 3, this.M, i, false);
        int i3 = this.f9655J;
        AbstractC5758yO0.o(parcel, 1000, 4);
        parcel.writeInt(i3);
        AbstractC5758yO0.n(parcel, l);
    }

    public final boolean x() {
        return this.K <= 0;
    }

    public Status(int i) {
        this(1, i, null, null);
    }

    public Status(int i, String str) {
        this(1, i, str, null);
    }
}
