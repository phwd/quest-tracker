package defpackage;

import android.app.Activity;
import android.view.View;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Rs  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1081Rs extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C1569Zs f8860a;

    public C1081Rs(C1569Zs zs) {
        this.f8860a = zs;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C1569Zs zs = this.f8860a;
        View view = (View) obj;
        Activity activity = zs.f9379a;
        String str = zs.m;
        String str2 = zs.e.b;
        AbstractC4448qj qjVar = zs.c;
        EE ee = new EE(activity, str, str2, ((Tab) zs.b.get()).l().f().r().h, qjVar, zs.g, zs.h);
        C5638xj xjVar = (C5638xj) qjVar;
        xjVar.u(ee, true);
        xjVar.m();
    }
}
