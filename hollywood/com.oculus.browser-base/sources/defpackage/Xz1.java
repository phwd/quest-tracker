package defpackage;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: Xz1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Xz1 extends AbstractRunnableC4702sA1 {
    public final /* synthetic */ C2140dA1 G;
    public final /* synthetic */ String H;
    public final /* synthetic */ Vz1 I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Xz1(Vz1 vz1, C2140dA1 da1, C2140dA1 da12, String str) {
        super(da1);
        this.I = vz1;
        this.G = da12;
        this.H = str;
    }

    @Override // defpackage.AbstractRunnableC4702sA1
    public final void a() {
        try {
            Vz1 vz1 = this.I;
            String str = vz1.d;
            Bundle bundle = new Bundle();
            bundle.putInt("playcore.version.code", 10604);
            BinderC1969cA1 ca1 = new BinderC1969cA1(this.I, this.G);
            C4190pA1 pa1 = (C4190pA1) ((AbstractC3848nA1) vz1.c.l);
            Parcel c = pa1.c();
            c.writeString(str);
            AbstractC2481fA1.c(c, bundle);
            AbstractC2481fA1.b(c, ca1);
            pa1.d(3, c);
        } catch (RemoteException e) {
            Vz1.f9121a.b(e, "completeUpdate(%s)", this.H);
            this.G.a(new RuntimeException(e));
        }
    }
}
