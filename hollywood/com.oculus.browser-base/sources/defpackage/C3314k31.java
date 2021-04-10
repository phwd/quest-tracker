package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: k31  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3314k31 implements AbstractC6017zw {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C3998o31 f10256a;

    public C3314k31(C3998o31 o31) {
        this.f10256a = o31;
    }

    @Override // defpackage.AbstractC6017zw
    public void a(long j) {
        C3998o31 o31 = this.f10256a;
        X21 x21 = (X21) o31.f;
        Objects.requireNonNull(x21);
        if (o31 != null && !o31.l) {
            x21.g();
            C5677xw d = C5677xw.d(((D70) x21.b).h0, o31, C3998o31.b, o31.t, o31.x, 150);
            x21.n = d;
            d.addListener(new U21(x21, o31));
            x21.n.start();
            o31.l = true;
            Tab r = x21.d.r(o31.d);
            if (r != null) {
                x21.u(j, r.getId(), o31.d);
            }
        }
    }
}
