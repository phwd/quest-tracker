package defpackage;

import java.util.Objects;
import java.util.regex.Pattern;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: sA  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4700sA {

    /* renamed from: a  reason: collision with root package name */
    public final ChromeActivity f11254a;
    public final AbstractC4870tA b;
    public final float c;
    public final Pattern d;
    public C2138dA e;
    public String f;
    public int g;
    public boolean h;
    public C5210vA i;
    public boolean j;
    public float k;
    public float l;
    public int m;
    public int n;
    public long o;
    public long p;
    public boolean q;
    public int r = -1;
    public boolean s;
    public boolean t;
    public boolean u;

    static {
        Pattern.compile("((http|https|file|ftp|ssh)://)([\\w_-]+(?:(?:\\.[\\w_-]+)+))([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?");
    }

    public C4700sA(ChromeActivity chromeActivity, AbstractC4870tA tAVar) {
        this.f11254a = chromeActivity;
        this.b = tAVar;
        this.c = 1.0f / chromeActivity.getResources().getDisplayMetrics().density;
        this.d = Pattern.compile("(\\w|\\p{L}|\\p{N})+");
        AbstractC1220Ua0.d("ContextualSearch", "Tap suppression enabled: %s", Boolean.valueOf(AbstractC5686xz.c(18)));
    }

    public void a() {
        if (!this.s) {
            this.s = true;
            AbstractC3551lS0 c2 = c();
            if (c2 != null) {
                ((SelectionPopupControllerImpl) c2).m();
            }
            g();
            this.s = false;
        }
    }

    public WebContents b() {
        Tab K0 = this.f11254a.K0();
        if (K0 == null) {
            return null;
        }
        return K0.l();
    }

    public AbstractC3551lS0 c() {
        WebContents b2 = b();
        if (b2 != null) {
            return SelectionPopupControllerImpl.r(b2);
        }
        return null;
    }

    public final void d(String str, int i2) {
        C1796bA bAVar;
        boolean h2 = h(str);
        AbstractC4870tA tAVar = this.b;
        float f2 = this.l;
        ContextualSearchManager contextualSearchManager = (ContextualSearchManager) tAVar;
        if (!contextualSearchManager.n() && !str.isEmpty()) {
            Pattern pattern = AA.f7657a;
            AbstractC3364kK0.g("Search.ContextualSearchSelectionValid", !h2, 2);
            if (h2 == 0 || (bAVar = contextualSearchManager.T) == null) {
                contextualSearchManager.i(5);
                return;
            }
            bAVar.N0 = f2;
            if (!bAVar.O()) {
                C1967cA cAVar = contextualSearchManager.T.F0;
                Objects.requireNonNull(cAVar);
                cAVar.q = str.length();
            }
            if (contextualSearchManager.m()) {
                contextualSearchManager.T.v0(str);
            }
            if (i2 == 2) {
                contextualSearchManager.S.a(2);
            } else if (i2 == 3) {
                contextualSearchManager.S.a(15);
            }
        }
    }

    public boolean e() {
        return this.g == 1;
    }

    public final void f() {
        g();
        this.i = null;
        this.o = 0;
        this.p = 0;
        this.r = -1;
        this.j = false;
        this.m = 0;
        this.n = 0;
    }

    public final void g() {
        this.g = 0;
        this.f = null;
        this.h = false;
        this.t = false;
        this.u = false;
    }

    public final boolean h(String str) {
        AbstractC3551lS0 c2 = c();
        boolean z = str.length() <= 1000 && this.d.matcher(str).find() && (c2 == null || !((SelectionPopupControllerImpl) c2).U);
        if (this.g == 1) {
            int d2 = AbstractC5686xz.d(2);
            if (str.length() < d2) {
                Pattern pattern = AA.f7657a;
                AbstractC3100ip1.f10165a.a("Search.ContextualSearchSelectionLengthSuppression", true);
                return false;
            } else if (d2 > 0) {
                Pattern pattern2 = AA.f7657a;
                AbstractC3100ip1.f10165a.a("Search.ContextualSearchSelectionLengthSuppression", false);
            }
        }
        return z;
    }
}
