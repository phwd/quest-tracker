package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

/* renamed from: GA0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class GA0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final TA0 f8071a;

    public GA0(TA0 ta0) {
        this.f8071a = ta0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        View view = (View) obj;
        AB0 ab0 = (AB0) this.f8071a.I;
        Context e = ((C0289Es) ab0.m).e();
        if (e == null) {
            ((C0289Es) ab0.m).g(8, "Unable to find Chrome context.");
            return;
        }
        Intent l = AbstractC2531fV.l(e, XS0.class);
        if (!(e instanceof Activity)) {
            l.addFlags(268435456);
            l.addFlags(67108864);
        }
        U20.q(e, l);
    }
}
