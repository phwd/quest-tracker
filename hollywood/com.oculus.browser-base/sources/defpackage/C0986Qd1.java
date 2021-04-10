package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.contextmenu.ContextMenuPopulatorFactory;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Qd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0986Qd1 implements C61 {

    /* renamed from: a  reason: collision with root package name */
    public final ChromeActivity f8774a;
    public final AbstractC2742gk b;
    public final Q31 c;
    public final Q31 d;
    public final Runnable e;
    public final AbstractC4448qj f;
    public C0034Am0 g;

    public C0986Qd1(ChromeActivity chromeActivity, AbstractC2742gk gkVar, Q31 q31, Q31 q312, Runnable runnable, AbstractC4448qj qjVar) {
        this.f8774a = chromeActivity;
        this.b = gkVar;
        this.c = q31;
        this.d = q312;
        this.e = runnable;
        this.f = qjVar;
    }

    @Override // defpackage.C61
    public AbstractC0133Cd1 a(Tab tab) {
        return new C2800h3(tab, this.f8774a);
    }

    @Override // defpackage.C61
    public AbstractC5818ym0 b(String str, AbstractC5818ym0 ym0, Tab tab) {
        if (this.g == null) {
            this.g = new C0034Am0(this.f8774a, this.f);
        }
        Objects.requireNonNull(this.g);
        return null;
    }

    @Override // defpackage.C61
    public C3198jN c(Tab tab) {
        return new C3198jN(new C2003cN(tab));
    }

    @Override // defpackage.C61
    public AbstractC2742gk d(Tab tab) {
        if (this.f8774a == null) {
            return null;
        }
        return new C3461kw(new C0980Qb1(tab), this.b);
    }

    @Override // defpackage.C61
    public ContextMenuPopulatorFactory e(Tab tab) {
        ChromeActivity chromeActivity = this.f8774a;
        if (chromeActivity == null) {
            return null;
        }
        AbstractC0124Ca1 P = chromeActivity.P();
        Q31 q31 = this.d;
        Runnable runnable = this.e;
        ChromeActivity chromeActivity2 = this.f8774a;
        chromeActivity2.getClass();
        return new C5662xr(new C4349q61(tab, P, q31, runnable, new C0925Pd1(chromeActivity2)), this.c, 0, YM.f9268a);
    }
}
