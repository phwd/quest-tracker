package defpackage;

import J.N;
import android.text.TextUtils;
import java.net.URL;
import java.util.Objects;
import java.util.regex.Pattern;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: dA  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2138dA {

    /* renamed from: a  reason: collision with root package name */
    public static final Pattern f9753a = Pattern.compile("\\s");
    public final PU0 b = NU0.f8549a;
    public final C4700sA c;
    public AbstractC1522Yz d;

    static {
        AbstractC0417Gv.d("GB", "US");
    }

    public C2138dA(C4700sA sAVar, AbstractC1522Yz yz) {
        this.c = sAVar;
        this.d = yz;
        if (sAVar != null) {
            sAVar.e = this;
        }
    }

    public boolean a() {
        return N.M09VlOh_("ContextualSearchLongpressResolve") || N.M09VlOh_("ContextualSearchTranslations");
    }

    public boolean b() {
        return !ContextualSearchManager.k() || c();
    }

    public final boolean c() {
        if (!g(((ContextualSearchManager) this.d).c())) {
            return false;
        }
        return N.M09VlOh_("ContextualSearchLegacyHttpPolicy");
    }

    public MF d() {
        PU0 pu0 = this.b;
        if (MF.f8467a == null) {
            MF.f8467a = new MF(pu0);
        }
        return MF.f8467a;
    }

    public int e() {
        if (!ContextualSearchManager.k()) {
            return -1;
        }
        MF d2 = d();
        if (d2.b()) {
            return Math.max(0, 50 - d2.a());
        }
        return -1;
    }

    public final boolean f() {
        return N.Mfmn09fr(Profile.b());
    }

    public boolean g(URL url) {
        return url != null && "http".equals(url.getProtocol());
    }

    public boolean h() {
        return N.M09VlOh_("ContextualSearchLiteralSearchTap");
    }

    public boolean i() {
        if (!ContextualSearchManager.k() || !AbstractC5686xz.c(3) || this.b.f("contextual_search_promo_open_count", 0) < AbstractC5686xz.d(0)) {
            return false;
        }
        return true;
    }

    public final boolean j(String str) {
        String b2 = AbstractC5686xz.b("language_allowlist");
        if (!(TextUtils.isEmpty(b2) || b2.contains(str))) {
            return false;
        }
        if (!(!(N.M6bsIDpc("RelatedSearches", "needs_url", false) || TextUtils.isEmpty(AbstractC5686xz.b("stamp"))) || f())) {
            return false;
        }
        return !(N.M6bsIDpc("RelatedSearches", "needs_content", false) || TextUtils.isEmpty(AbstractC5686xz.b("stamp"))) || !ContextualSearchManager.k();
    }

    public boolean k() {
        if ((this.c.g != 1 || h()) && this.c.g != 3) {
            return false;
        }
        return true;
    }

    public boolean l() {
        if (a() && !h()) {
            return false;
        }
        return !ContextualSearchManager.k() || AbstractC5686xz.c(20) || e() != 0;
    }

    public boolean m() {
        if (i()) {
            return false;
        }
        Objects.requireNonNull(WF0.a());
        if (N.MBIqJabw() && k() && n()) {
            return true;
        }
        return false;
    }

    public boolean n() {
        if (i() || AbstractC5686xz.c(2)) {
            return false;
        }
        if (!ContextualSearchManager.k() || c()) {
            return true;
        }
        return false;
    }
}
