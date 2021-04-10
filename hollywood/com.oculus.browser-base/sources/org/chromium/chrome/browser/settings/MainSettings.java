package org.chromium.chrome.browser.settings;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.view.View;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.oculus.browser.R;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.net.spdyproxy.DataReductionProxySettings;
import org.chromium.chrome.browser.password_manager.PasswordManagerLauncher;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.SigninFragment;
import org.chromium.chrome.browser.signin.SigninFragmentBase;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.chrome.browser.sync.settings.ManageSyncSettings;
import org.chromium.chrome.browser.sync.settings.SignInPreference;
import org.chromium.chrome.browser.sync.settings.SyncPromoPreference;
import org.chromium.components.browser_ui.settings.ChromeBasePreference;
import org.chromium.components.search_engines.TemplateUrl;
import org.chromium.components.signin.base.CoreAccountInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MainSettings extends AbstractC2324eF0 implements AbstractC0322Ff1, AbstractC3526lH0, AbstractC2534fW0 {
    public final AbstractC1528Zb0 G0;
    public final Map H0 = new HashMap();
    public SyncPromoPreference I0;
    public SignInPreference J0;
    public ChromeBasePreference K0;
    public C0909Ox0 L0;

    public MainSettings() {
        V0(true);
        this.G0 = new C0370Gb0(this);
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void B0() {
        this.i0 = true;
        p1();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void D0() {
        super.D0();
        SigninManager d = C5949zZ.a().d(Profile.b());
        if (d.B()) {
            d.m(this);
        }
        ProfileSyncService b = ProfileSyncService.b();
        if (b != null) {
            b.a(this);
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void E0() {
        super.E0();
        SigninManager d = C5949zZ.a().d(Profile.b());
        if (d.B()) {
            d.z(this);
        }
        ProfileSyncService b = ProfileSyncService.b();
        if (b != null) {
            b.q(this);
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void F0(View view, Bundle bundle) {
        super.F0(view, bundle);
        this.A0.s0(null);
    }

    @Override // defpackage.AbstractC2534fW0
    public void b() {
        new Handler().post(new RunnableC0309Fb0(this));
    }

    @Override // defpackage.AbstractC0322Ff1
    public void f() {
        AbstractC0444Hf1.a().k(this);
        q1();
    }

    @Override // defpackage.AbstractC2324eF0
    public void g1(Bundle bundle, String str) {
        CharSequence charSequence;
        AbstractC2870hT0.a(this, R.xml.f76270_resource_name_obfuscated_RES_2132213783);
        if (N.M09VlOh_("SafeBrowsingSecuritySectionUIAndroid")) {
            f1("privacy").U(R.string.f59170_resource_name_obfuscated_RES_2131953234);
        }
        int d0 = this.z0.g.d0();
        for (int i = 0; i < d0; i++) {
            Preference c0 = this.z0.g.c0(i);
            this.H0.put(c0.Q, c0);
        }
        this.I0 = (SyncPromoPreference) this.H0.get("sync_promo");
        this.J0 = (SignInPreference) this.H0.get("sign_in");
        this.K0 = (ChromeBasePreference) f1("manage_sync");
        this.I0.w0 = new RunnableC0065Bb0(this);
        f1("passwords").K = new C0248Eb0(this);
        ChromeBasePreference chromeBasePreference = (ChromeBasePreference) this.H0.get("search_engine");
        AbstractC1528Zb0 zb0 = this.G0;
        chromeBasePreference.u0 = zb0;
        AbstractC1865bc0.b(zb0, chromeBasePreference);
        ChromeBasePreference chromeBasePreference2 = (ChromeBasePreference) this.H0.get("data_reduction");
        AbstractC1528Zb0 zb02 = this.G0;
        chromeBasePreference2.u0 = zb02;
        AbstractC1865bc0.b(zb02, chromeBasePreference2);
        if (Build.VERSION.SDK_INT >= 26) {
            f1("notifications").K = new C0126Cb0(this);
        } else if (!AbstractC5056uF0.a()) {
            this.z0.g.f0(f1("notifications"));
        }
        if (!AbstractC0444Hf1.a().g()) {
            AbstractC0444Hf1.a().i(this);
            AbstractC0444Hf1.a().h();
        }
        if (!N.M09VlOh_("SafetyCheckAndroid")) {
            this.z0.g.f0(f1("safety_check"));
        } else {
            Preference f1 = f1("safety_check");
            Context x = x();
            if (NU0.f8549a.f("Chrome.SafetyCheck.RunCounter", 0) < 3) {
                charSequence = FY0.a(x.getString(R.string.f59220_resource_name_obfuscated_RES_2131953239), new EY0("<new>", "</new>", new SuperscriptSpan(), new RelativeSizeSpan(0.75f), new ForegroundColorSpan(x.getResources().getColor(R.color.f11460_resource_name_obfuscated_RES_2131099836))));
            } else {
                charSequence = FY0.b(x.getString(R.string.f59220_resource_name_obfuscated_RES_2131953239), new EY0("<new>", "</new>", new Object[0])).toString().trim();
            }
            f1.V(charSequence);
        }
        if (N.M09VlOh_("MobileIdentityConsistency")) {
            this.z0.g.f0(f1("account_section"));
            this.z0.g.f0(f1("sync_and_services"));
            f1("account_and_google_services_section").W(true);
            this.K0.W(true);
            f1("google_services").W(true);
        }
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS, defpackage.AbstractC2324eF0
    public void h0(Bundle bundle) {
        super.h0(bundle);
        this.L0 = AbstractC0848Nx0.b(new C2528fT0());
    }

    public final Preference k1(String str) {
        if (this.z0.g.b0(str) == null) {
            this.z0.g.a0((Preference) this.H0.get(str));
        }
        return (Preference) this.H0.get(str);
    }

    public final /* synthetic */ boolean l1() {
        Intent intent = new Intent();
        intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
        intent.putExtra("android.provider.extra.APP_PACKAGE", ContextUtils.getApplicationContext().getPackageName());
        c1(intent);
        return true;
    }

    @Override // defpackage.AbstractC3526lH0
    public void m() {
        r1();
    }

    @Override // defpackage.AbstractComponentCallbacksC3550lS
    public void m0() {
        String str;
        this.i0 = true;
        C5264vW0 vw0 = this.I0.x0;
        if (vw0 != null && vw0.r && !vw0.s && (str = vw0.l) != null) {
            AbstractC3364kK0.c(str, vw0.a());
        }
        if (u().isFinishing() && this.L0 != null) {
            AbstractC0848Nx0.a();
        }
    }

    public final boolean m1(boolean z, String str) {
        Context x = x();
        if (ProfileSyncService.b().l()) {
            C1184Ti1.b(x, x.getString(R.string.f62800_resource_name_obfuscated_RES_2131953597), 1).b.show();
        } else if (z) {
            String name = ManageSyncSettings.class.getName();
            Intent l = AbstractC2531fV.l(x, XS0.class);
            if (!(x instanceof Activity)) {
                l.addFlags(268435456);
                l.addFlags(67108864);
            }
            l.putExtra("show_fragment", name);
            U20.q(x, l);
        } else {
            BV0 a2 = BV0.a();
            Objects.requireNonNull(a2);
            int i = SigninFragment.U0;
            Bundle f1 = SigninFragmentBase.f1(str);
            f1.putInt("SigninFragment.AccessPoint", 3);
            f1.putInt("SigninFragment.PersonalizedPromoAction", 1);
            a2.c(x, f1);
        }
        return true;
    }

    public final /* synthetic */ boolean n1() {
        PasswordManagerLauncher.b(u(), 0);
        return true;
    }

    @Override // defpackage.AbstractC2534fW0
    public void o() {
        p1();
    }

    public final void o1(String str) {
        Preference b0 = this.z0.g.b0(str);
        if (b0 != null) {
            PreferenceScreen preferenceScreen = this.z0.g;
            preferenceScreen.g0(b0);
            preferenceScreen.u();
        }
    }

    public final void p1() {
        String str;
        if (C5949zZ.a().d(Profile.b()).B()) {
            k1("sign_in");
        } else {
            o1("sign_in");
        }
        r1();
        q1();
        k1("homepage").S(QX.g() ? R.string.f63330_resource_name_obfuscated_RES_2131953650 : R.string.f63320_resource_name_obfuscated_RES_2131953649);
        k1("ui_theme");
        if (NU0.f8549a.d("developer", false)) {
            k1("developer");
        } else {
            o1("developer");
        }
        ChromeBasePreference chromeBasePreference = (ChromeBasePreference) f1("data_reduction");
        Resources I = I();
        if (DataReductionProxySettings.d().e()) {
            DataReductionProxySettings d = DataReductionProxySettings.d();
            DataReductionProxySettings.ContentLengths contentLengths = (DataReductionProxySettings.ContentLengths) N.MG86mkwd(d.c, d);
            long j = contentLengths.b;
            if (j / 1024 < 100) {
                str = "";
            } else {
                long j2 = contentLengths.f10700a;
                str = I.getString(R.string.f50520_resource_name_obfuscated_RES_2131952369, NumberFormat.getPercentInstance(Locale.getDefault()).format((j2 <= 0 || j2 <= j) ? 0.0d : ((double) (j2 - j)) / ((double) j2)));
            }
        } else {
            str = (String) I.getText(R.string.f63320_resource_name_obfuscated_RES_2131953649);
        }
        chromeBasePreference.T(str);
    }

    public final void q1() {
        if (!AbstractC0444Hf1.a().g()) {
            ((ChromeBasePreference) f1("search_engine")).K(false);
            return;
        }
        String str = null;
        TemplateUrl a2 = AbstractC0444Hf1.a().a();
        if (a2 != null) {
            str = a2.d();
        }
        Preference f1 = f1("search_engine");
        f1.K(true);
        f1.T(str);
    }

    public final void r1() {
        if (N.M09VlOh_("MobileIdentityConsistency")) {
            boolean z = false;
            String b = CoreAccountInfo.b(C5949zZ.a().c(Profile.b()).b(0));
            boolean z2 = N.M09VlOh_("MobileIdentityConsistency") && b != null;
            this.K0.W(z2);
            if (z2) {
                if (AbstractC2531fV.m(C5949zZ.a(), 1) != null) {
                    z = true;
                }
                this.K0.N(AbstractC4175p51.e(u()));
                this.K0.T(AbstractC4175p51.f(u()));
                this.K0.K = new C0187Db0(this, z, b);
                return;
            }
            return;
        }
        ChromeBasePreference chromeBasePreference = (ChromeBasePreference) f1("sync_and_services");
        chromeBasePreference.N(AbstractC4175p51.e(u()));
        chromeBasePreference.T(AbstractC4175p51.f(u()));
    }
}
