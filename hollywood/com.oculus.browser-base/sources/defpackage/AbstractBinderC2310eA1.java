package defpackage;

import android.os.Bundle;

/* renamed from: eA1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractBinderC2310eA1 extends AbstractBinderC4361qA1 {

    /* renamed from: a  reason: collision with root package name */
    public final Rz1 f9837a;
    public final C2140dA1 b;
    public final /* synthetic */ Vz1 c;

    public AbstractBinderC2310eA1(Vz1 vz1, Rz1 rz1, C2140dA1 da1) {
        this.c = vz1;
        this.f9837a = rz1;
        this.b = da1;
    }

    @Override // defpackage.AbstractC4019oA1
    public void t0(Bundle bundle) {
        this.c.c.a();
        this.f9837a.a(4, "onRequestInfo", new Object[0]);
    }

    @Override // defpackage.AbstractC4019oA1
    public void v(Bundle bundle) {
        this.c.c.a();
        this.f9837a.a(4, "onCompleteUpdate", new Object[0]);
    }
}
