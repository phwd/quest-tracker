package defpackage;

import J.N;
import android.content.res.Resources;
import android.text.TextUtils;
import com.oculus.browser.R;
import org.chromium.base.MemoryPressureListener;
import org.chromium.chrome.browser.preferences.PrefChangeRegistrar;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.components.prefs.PrefService;
import org.chromium.components.signin.identitymanager.PrimaryAccountChangeEvent;
import org.chromium.content_public.browser.LoadUrlParams;

/* renamed from: hP  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2861hP extends AbstractC5779yZ implements AbstractC0383Gf1, R80, AbstractC3365kL {
    public final XO F;
    public final PrefChangeRegistrar G;
    public final SigninManager H = C5949zZ.a().d(Profile.b());
    public final AbstractC0095Bm0 I;

    /* renamed from: J  reason: collision with root package name */
    public L21 f10068J;
    public C3891nR0 K;
    public AbstractC2737gi0 L;
    public C2690gP M;
    public boolean N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public long S;

    public C2861hP(XO xo, AbstractC0095Bm0 bm0) {
        this.F = xo;
        this.I = bm0;
        PrefChangeRegistrar prefChangeRegistrar = new PrefChangeRegistrar();
        this.G = prefChangeRegistrar;
        this.O = xo.v != null;
        prefChangeRegistrar.f10748a.put("ntp_snippets.enable", new ZO(this));
        N.Mrf8X6ah(prefChangeRegistrar.b, prefChangeRegistrar, "ntp_snippets.enable");
        this.P = true;
        Y();
    }

    @Override // defpackage.R80
    public void I(UH0 uh0) {
        int f = uh0.f(Y80.f);
        if (f == R.id.ntp_feed_header_menu_item_activity) {
            this.I.a(1, new LoadUrlParams("https://myactivity.google.com/myactivity?product=50", 0));
            AbstractC4228pP.a(0);
        } else if (f == R.id.ntp_feed_header_menu_item_interest) {
            this.I.a(1, new LoadUrlParams("https://www.google.com/preferences/interests", 0));
            AbstractC4228pP.a(1);
        } else if (f == R.id.ntp_feed_header_menu_item_learn) {
            ((GM) this.I).a(1, new LoadUrlParams("https://support.google.com/chrome/?p=new_tab", 6));
            AbstractC4228pP.a(2);
        } else if (f == R.id.ntp_feed_header_menu_item_toggle_switch) {
            this.K.w();
            AbstractC4228pP.a(3);
            G31.a();
        }
    }

    @Override // defpackage.AbstractC0383Gf1
    public void N() {
        Z();
    }

    @Override // defpackage.AbstractC5779yZ
    public void T(PrimaryAccountChangeEvent primaryAccountChangeEvent) {
        Z();
    }

    public final C4935tb0 U() {
        C4935tb0 tb0 = new C4935tb0();
        if (this.H.f().c()) {
            tb0.q(C1237Ug.a(R.string.f56410_resource_name_obfuscated_RES_2131952958, R.id.ntp_feed_header_menu_item_activity, 0));
            tb0.q(C1237Ug.a(R.string.f56400_resource_name_obfuscated_RES_2131952957, R.id.ntp_feed_header_menu_item_interest, 0));
        }
        tb0.q(C1237Ug.a(R.string.f54060_resource_name_obfuscated_RES_2131952723, R.id.ntp_feed_header_menu_item_learn, 0));
        if (this.K.M) {
            tb0.q(C1237Ug.a(R.string.f56450_resource_name_obfuscated_RES_2131952962, R.id.ntp_feed_header_menu_item_toggle_switch, 0));
        } else {
            tb0.q(C1237Ug.a(R.string.f56460_resource_name_obfuscated_RES_2131952963, R.id.ntp_feed_header_menu_item_toggle_switch, 0));
        }
        return tb0;
    }

    public final void V() {
        K21 k21 = this.F.t;
        if (k21 != null) {
            ((FO) k21).f8014a.k.c(this.f10068J);
            this.f10068J = null;
            MemoryPressureListener.f10590a.c(this.L);
            this.L = null;
            C2690gP gPVar = this.M;
            if (gPVar != null) {
                C5772yV0 yv0 = gPVar.L;
                if (!yv0.H) {
                    yv0.H = true;
                    yv0.F.M(yv0);
                    yv0.F.z(yv0);
                    yv0.I.N.Y(yv0);
                    yv0.G.m(yv0);
                }
                this.M = null;
            }
            PrefChangeRegistrar prefChangeRegistrar = this.G;
            if (((UE0) prefChangeRegistrar.f10748a.get("ntp_snippets.list_visible")) != null) {
                prefChangeRegistrar.f10748a.remove("ntp_snippets.list_visible");
                N.M0E$fVRB(prefChangeRegistrar.b, prefChangeRegistrar, "ntp_snippets.list_visible");
            }
            AbstractC0444Hf1.a().b.c(this);
            this.H.f().b.c(this);
        }
    }

    public final PrefService W() {
        return Wr1.a(Profile.b());
    }

    public final String X(boolean z) {
        Resources resources = this.F.v.getResources();
        boolean e = AbstractC0444Hf1.a().e();
        return resources.getString(this.P ? e ? z ? R.string.f56350_resource_name_obfuscated_RES_2131952952 : R.string.f56330_resource_name_obfuscated_RES_2131952950 : z ? R.string.f56360_resource_name_obfuscated_RES_2131952953 : R.string.f56340_resource_name_obfuscated_RES_2131952951 : e ? R.string.f56310_resource_name_obfuscated_RES_2131952948 : R.string.f56320_resource_name_obfuscated_RES_2131952949);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0274, code lost:
        if ((r6 != null && !J.N.MzIXnlkD(defpackage.Wr1.a(r6).f10883a, "safebrowsing.enhanced") && !r0.d(defpackage.AbstractC0533Is.h.b("EnhancedProtectionPromoCard"), false) && r0.f(defpackage.AbstractC0533Is.i.b("EnhancedProtectionPromoCard"), 0) <= J.N.M37SqSAy("EnhancedProtectionPromoCard", "MaxEnhancedProtectionPromoImpressions", 22) && !J.N.MAU7_6Tq()) != false) goto L_0x0276;
     */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x027e  */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0323  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x01f9  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0239  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void Y() {
        /*
        // Method dump skipped, instructions count: 988
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C2861hP.Y():void");
    }

    public final void Z() {
        boolean MzIXnlkD = N.MzIXnlkD(W().f10883a, "ntp_snippets.list_visible");
        C3891nR0 nr0 = this.K;
        if (nr0.M != MzIXnlkD) {
            nr0.w();
        }
        C3891nR0 nr02 = this.K;
        String X = X(nr02.M);
        if (!TextUtils.equals(nr02.I, X)) {
            nr02.I = X;
            nr02.n(0, 1, null);
        }
        if (this.P) {
            this.K.f10491J = U();
        }
        C2690gP gPVar = this.M;
        if (gPVar != null) {
            gPVar.K = MzIXnlkD;
            gPVar.x();
        }
        if (MzIXnlkD) {
            this.F.u.a();
        }
        this.F.v.b();
    }
}
