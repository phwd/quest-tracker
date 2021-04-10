package defpackage;

import J.N;
import android.content.Context;
import android.content.res.ColorStateList;
import java.util.concurrent.Executor;
import org.chromium.base.SysUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.ProfileManager;
import org.chromium.components.search_engines.TemplateUrlService;

/* renamed from: Ca  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0122Ca implements AbstractC0383Gf1, AbstractC1818bH0 {
    public static Boolean F;
    public final AbstractC0061Ba G;
    public final Context H;
    public final YM I;

    /* renamed from: J  reason: collision with root package name */
    public final TemplateUrlService f7820J;
    public final C5259vU K;
    public final PU0 L;
    public boolean M;
    public boolean N = N.M09VlOh_("OmniboxAssistantVoiceSearch");
    public boolean O = N.M6bsIDpc("OmniboxAssistantVoiceSearch", "colorful_mic", false);
    public boolean P;
    public String Q = N.MMltG$kc("OmniboxAssistantVoiceSearch", "min_agsa_version");

    public C0122Ca(Context context, YM ym, TemplateUrlService templateUrlService, C5259vU vUVar, AbstractC0061Ba ba, PU0 pu0) {
        this.H = context;
        this.I = ym;
        this.f7820J = templateUrlService;
        this.K = vUVar;
        this.L = pu0;
        this.G = ba;
        ProfileManager.f10754a.b(this);
        vUVar.e.b(this);
        templateUrlService.b.b(this);
        this.M = templateUrlService.e();
        c();
        this.P = false;
        if (!this.N || !this.M || SysUtils.isLowEndDevice()) {
            F = Boolean.FALSE;
            return;
        }
        String a2 = vUVar.a();
        pu0.f8694a.a("Chrome.Assistant.Supported");
        if (!AbstractC3983nz.f10523a.contains("Chrome.Assistant.Supported") || !pu0.i("Chrome.Assistant.LastVersion", "n/a").equals(a2)) {
            C2150dE.b().a(new RunnableC5781ya(this, a2));
            return;
        }
        F = Boolean.valueOf(pu0.d("Chrome.Assistant.Supported", false));
        d();
    }

    @Override // defpackage.AbstractC0383Gf1
    public void N() {
        boolean e = this.f7820J.e();
        if (this.M != e) {
            this.M = e;
            c();
            this.P = false;
            AbstractC0061Ba ba = this.G;
            if (ba != null) {
                ba.g();
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a() {
        /*
        // Method dump skipped, instructions count: 142
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C0122Ca.a():boolean");
    }

    public ColorStateList b(int i, Context context) {
        if (this.P) {
            return null;
        }
        int d = AbstractC2934hr.d(AbstractC1270Uv.g(i));
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        return context.getColorStateList(d);
    }

    public final boolean c() {
        if (this.H.getPackageManager() == null || !this.O || !this.N) {
            return false;
        }
        a();
        return false;
    }

    public final void d() {
        c();
        C0000Aa aa = new C0000Aa(this, false);
        Executor executor = AbstractC2032cb.f9616a;
        aa.f();
        ((ExecutorC1463Ya) executor).execute(aa.e);
    }

    @Override // defpackage.AbstractC1818bH0
    public void f(Profile profile) {
        d();
    }

    @Override // defpackage.AbstractC1818bH0
    public void i(Profile profile) {
    }
}
