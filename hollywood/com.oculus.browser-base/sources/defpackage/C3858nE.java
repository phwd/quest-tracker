package defpackage;

import com.oculus.browser.R;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.url.GURL;

/* renamed from: nE  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3858nE implements AbstractC3688mE0 {

    /* renamed from: a  reason: collision with root package name */
    public Tab f10477a;
    public C1872be1 b;
    public C3687mE c;

    public C3858nE(Tab tab) {
        this.f10477a = tab;
        this.b = C1872be1.e(tab);
        C3687mE mEVar = new C3687mE(this, null);
        this.c = mEVar;
        this.f10477a.A(mEVar);
    }

    @Override // defpackage.AbstractC3688mE0
    public void a(int i) {
        C1184Ti1.a(this.f10477a.getContext(), R.string.f57330_resource_name_obfuscated_RES_2131953050, 1).b.show();
        h();
    }

    @Override // defpackage.AbstractC3688mE0
    public void b(GURL gurl) {
        if (this.f10477a != null && gurl.b && !gurl.j()) {
            this.f10477a.c(new LoadUrlParams(gurl.h(), 0));
            h();
        }
    }

    @Override // defpackage.AbstractC3688mE0
    public void c() {
    }

    @Override // defpackage.AbstractC3688mE0
    public void d() {
    }

    @Override // defpackage.AbstractC3688mE0
    public void e() {
        h();
    }

    @Override // defpackage.AbstractC3688mE0
    public void f() {
        C1184Ti1.a(this.f10477a.getContext(), R.string.f57340_resource_name_obfuscated_RES_2131953051, 1).b.show();
    }

    @Override // defpackage.AbstractC3688mE0
    public void g() {
    }

    public final void h() {
        if (this.f10477a != null) {
            this.b.k(false);
            this.f10477a.I(this.c);
            this.f10477a = null;
            this.b = null;
        }
    }
}
