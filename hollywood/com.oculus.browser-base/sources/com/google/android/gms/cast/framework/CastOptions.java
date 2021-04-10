package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CastOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new C4543rE1();
    public String F;
    public final List G;
    public boolean H;
    public final LaunchOptions I;

    /* renamed from: J  reason: collision with root package name */
    public final boolean f9648J;
    public final CastMediaOptions K;
    public final boolean L;
    public final double M;
    public final boolean N;

    public CastOptions(String str, List list, boolean z, LaunchOptions launchOptions, boolean z2, CastMediaOptions castMediaOptions, boolean z3, double d, boolean z4) {
        int i;
        this.F = TextUtils.isEmpty(str) ? "" : str;
        if (list == null) {
            i = 0;
        } else {
            i = list.size();
        }
        ArrayList arrayList = new ArrayList(i);
        this.G = arrayList;
        if (i > 0) {
            arrayList.addAll(list);
        }
        this.H = z;
        this.I = launchOptions == null ? new LaunchOptions() : launchOptions;
        this.f9648J = z2;
        this.K = castMediaOptions;
        this.L = z3;
        this.M = d;
        this.N = z4;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int l = AbstractC5758yO0.l(parcel, 20293);
        AbstractC5758yO0.g(parcel, 2, this.F, false);
        AbstractC5758yO0.i(parcel, 3, x(), false);
        boolean z = this.H;
        AbstractC5758yO0.o(parcel, 4, 4);
        parcel.writeInt(z ? 1 : 0);
        AbstractC5758yO0.f(parcel, 5, this.I, i, false);
        boolean z2 = this.f9648J;
        AbstractC5758yO0.o(parcel, 6, 4);
        parcel.writeInt(z2 ? 1 : 0);
        AbstractC5758yO0.f(parcel, 7, null, i, false);
        boolean z3 = this.L;
        AbstractC5758yO0.o(parcel, 8, 4);
        parcel.writeInt(z3 ? 1 : 0);
        double d = this.M;
        AbstractC5758yO0.o(parcel, 9, 8);
        parcel.writeDouble(d);
        boolean z4 = this.N;
        AbstractC5758yO0.o(parcel, 10, 4);
        parcel.writeInt(z4 ? 1 : 0);
        AbstractC5758yO0.n(parcel, l);
    }

    public List x() {
        return Collections.unmodifiableList(this.G);
    }
}
