package defpackage;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: Zz1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Zz1 extends AbstractRunnableC4702sA1 {
    public final /* synthetic */ String G;
    public final /* synthetic */ C2140dA1 H;
    public final /* synthetic */ Vz1 I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Zz1(Vz1 vz1, C2140dA1 da1, String str, C2140dA1 da12) {
        super(da1);
        this.I = vz1;
        this.G = str;
        this.H = da12;
    }

    @Override // defpackage.AbstractRunnableC4702sA1
    public final void a() {
        try {
            Vz1 vz1 = this.I;
            String str = vz1.d;
            Bundle a2 = Vz1.a(vz1, this.G);
            BinderC3335kA1 ka1 = new BinderC3335kA1(this.I, this.H, this.G);
            C4190pA1 pa1 = (C4190pA1) ((AbstractC3848nA1) vz1.c.l);
            Parcel c = pa1.c();
            c.writeString(str);
            AbstractC2481fA1.c(c, a2);
            AbstractC2481fA1.b(c, ka1);
            pa1.d(2, c);
        } catch (RemoteException e) {
            Vz1.f9121a.b(e, "requestUpdateInfo(%s)", this.G);
            this.H.a(new RuntimeException(e));
        }
    }
}
