package defpackage;

import android.os.SystemClock;
import org.chromium.chrome.browser.metrics.UmaUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.url.GURL;

/* renamed from: c3  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1946c3 extends AbstractC1099Sa1 {
    public boolean I = true;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ C2287e3 f9580J;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C1946c3(C2287e3 e3Var, AbstractC0124Ca1 ca1) {
        super(ca1);
        this.f9580J = e3Var;
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void K(Tab tab, GURL gurl) {
        if (!this.I) {
            this.f9580J.f = false;
        } else {
            this.I = false;
        }
    }

    @Override // defpackage.WK, defpackage.AbstractC1404Xa1
    public void u(Tab tab, NavigationHandle navigationHandle) {
        boolean z = navigationHandle.f && navigationHandle.f10940a && !navigationHandle.g && !navigationHandle.c && !navigationHandle.h && AbstractC5154ur1.e(navigationHandle.e);
        C2287e3 e3Var = this.f9580J;
        if (e3Var.f) {
            if (z && UmaUtils.b() && !UmaUtils.a()) {
                e3Var.b = SystemClock.uptimeMillis() - e3Var.f9827a;
                StringBuilder i = AbstractC2531fV.i("Startup.Android.Cold.TimeToFirstNavigationCommit");
                i.append(e3Var.c);
                AbstractC3364kK0.j(i.toString(), e3Var.b);
            }
            e3Var.f = false;
        }
    }
}
