package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class zzbf extends AbstractSafeParcelable {
    public static final Parcelable.Creator CREATOR = new FE1();
    public int F;
    public zzbd G;
    public AbstractC2679gJ1 H;
    public PendingIntent I;

    /* renamed from: J  reason: collision with root package name */
    public NI1 f9672J;
    public AbstractC3857nD1 K;

    public zzbf(int i, zzbd zzbd, IBinder iBinder, PendingIntent pendingIntent, IBinder iBinder2, IBinder iBinder3) {
        AbstractC2679gJ1 gj1;
        NI1 ni1;
        this.F = i;
        this.G = zzbd;
        AbstractC3857nD1 nd1 = null;
        if (iBinder == null) {
            gj1 = null;
        } else {
            int i2 = AbstractBinderC3533lJ1.f10338a;
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.location.ILocationListener");
            gj1 = queryLocalInterface instanceof AbstractC2679gJ1 ? (AbstractC2679gJ1) queryLocalInterface : new C4388qJ1(iBinder);
        }
        this.H = gj1;
        this.I = pendingIntent;
        if (iBinder2 == null) {
            ni1 = null;
        } else {
            int i3 = WD1.f9134a;
            IInterface queryLocalInterface2 = iBinder2.queryLocalInterface("com.google.android.gms.location.ILocationCallback");
            ni1 = queryLocalInterface2 instanceof NI1 ? (NI1) queryLocalInterface2 : new C1825bJ1(iBinder2);
        }
        this.f9672J = ni1;
        if (iBinder3 != null) {
            IInterface queryLocalInterface3 = iBinder3.queryLocalInterface("com.google.android.gms.location.internal.IFusedLocationProviderCallback");
            nd1 = queryLocalInterface3 instanceof AbstractC3857nD1 ? (AbstractC3857nD1) queryLocalInterface3 : new C5391wD1(iBinder3);
        }
        this.K = nd1;
    }

    public static zzbf x(AbstractC2679gJ1 gj1, AbstractC3857nD1 nd1) {
        return new zzbf(2, null, (AbstractBinderC3518lE1) gj1, null, null, nd1 != null ? nd1.asBinder() : null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        IBinder iBinder;
        int l = AbstractC5758yO0.l(parcel, 20293);
        int i2 = this.F;
        AbstractC5758yO0.o(parcel, 1, 4);
        parcel.writeInt(i2);
        AbstractC5758yO0.f(parcel, 2, this.G, i, false);
        AbstractC2679gJ1 gj1 = this.H;
        IBinder iBinder2 = null;
        AbstractC5758yO0.d(parcel, 3, gj1 == null ? null : gj1.asBinder(), false);
        AbstractC5758yO0.f(parcel, 4, this.I, i, false);
        NI1 ni1 = this.f9672J;
        if (ni1 == null) {
            iBinder = null;
        } else {
            iBinder = ((AbstractC2829hC1) ni1).f10053a;
        }
        AbstractC5758yO0.d(parcel, 5, iBinder, false);
        AbstractC3857nD1 nd1 = this.K;
        if (nd1 != null) {
            iBinder2 = nd1.asBinder();
        }
        AbstractC5758yO0.d(parcel, 6, iBinder2, false);
        AbstractC5758yO0.n(parcel, l);
    }
}
