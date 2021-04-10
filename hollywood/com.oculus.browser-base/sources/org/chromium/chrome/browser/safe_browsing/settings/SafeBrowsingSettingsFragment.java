package org.chromium.chrome.browser.safe_browsing.settings;

import J.N;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.preference.Preference;
import com.oculus.browser.R;
import com.oculus.os.Version;
import org.chromium.components.browser_ui.settings.TextMessagePreference;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SafeBrowsingSettingsFragment extends SafeBrowsingSettingsFragmentBase implements JJ0, XE0 {
    public static final /* synthetic */ int G0 = 0;
    public RadioButtonGroupSafeBrowsingPreference H0;
    public int I0;

    public static String m1(Context context) {
        String str;
        int MdyQjr8h = N.MdyQjr8h();
        if (MdyQjr8h == 2) {
            str = context.getString(R.string.f60480_resource_name_obfuscated_RES_2131953365);
        } else if (MdyQjr8h == 1) {
            str = context.getString(R.string.f60620_resource_name_obfuscated_RES_2131953379);
        } else if (MdyQjr8h == 0) {
            return context.getString(R.string.f59190_resource_name_obfuscated_RES_2131953236);
        } else {
            str = "";
        }
        return context.getString(R.string.f59200_resource_name_obfuscated_RES_2131953237, str);
    }

    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        String str = preference.Q;
        int intValue = ((Integer) obj).intValue();
        int MdyQjr8h = N.MdyQjr8h();
        if (intValue == MdyQjr8h) {
            return true;
        }
        if (intValue == 0) {
            o1(3);
        } else if (intValue == 1) {
            o1(2);
        } else if (intValue == 2) {
            o1(1);
        }
        if (intValue == 0) {
            this.H0.a0(MdyQjr8h);
            Context x = x();
            C4296pp0 pp0 = new C4296pp0(x, new C4056oO0(this));
            Resources resources = x.getResources();
            HH0 hh0 = new HH0(AbstractC3258jl0.r);
            hh0.e(AbstractC3258jl0.f10235a, new C4125op0(pp0));
            hh0.d(AbstractC3258jl0.c, resources, R.string.f60510_resource_name_obfuscated_RES_2131953368);
            hh0.d(AbstractC3258jl0.e, resources, R.string.f60500_resource_name_obfuscated_RES_2131953367);
            hh0.d(AbstractC3258jl0.g, resources, R.string.f60490_resource_name_obfuscated_RES_2131953366);
            hh0.b(AbstractC3258jl0.q, true);
            hh0.d(AbstractC3258jl0.j, resources, R.string.f48470_resource_name_obfuscated_RES_2131952164);
            pp0.c = hh0.a();
            C2746gl0 gl0 = new C2746gl0(new J9(pp0.f11088a), 0);
            pp0.b = gl0;
            gl0.i(pp0.c, 0, false);
        } else {
            N.MzV0f_Xz(intValue);
        }
        return true;
    }

    @Override // org.chromium.chrome.browser.safe_browsing.settings.SafeBrowsingSettingsFragmentBase
    public int k1() {
        return R.xml.f76350_resource_name_obfuscated_RES_2132213791;
    }

    @Override // org.chromium.chrome.browser.safe_browsing.settings.SafeBrowsingSettingsFragmentBase
    public void l1(Bundle bundle, String str) {
        this.I0 = U20.g(this.K, "SafeBrowsingSettingsFragment.AccessPoint", 0);
        C3885nO0 no0 = new C3885nO0();
        RadioButtonGroupSafeBrowsingPreference radioButtonGroupSafeBrowsingPreference = (RadioButtonGroupSafeBrowsingPreference) f1("safe_browsing_radio_button_group");
        this.H0 = radioButtonGroupSafeBrowsingPreference;
        int MdyQjr8h = N.MdyQjr8h();
        boolean M09VlOh_ = N.M09VlOh_("SafeBrowsingEnhancedProtection");
        int i = this.I0;
        radioButtonGroupSafeBrowsingPreference.w0 = MdyQjr8h;
        radioButtonGroupSafeBrowsingPreference.x0 = M09VlOh_;
        radioButtonGroupSafeBrowsingPreference.y0 = i;
        RadioButtonGroupSafeBrowsingPreference radioButtonGroupSafeBrowsingPreference2 = this.H0;
        radioButtonGroupSafeBrowsingPreference2.z0 = this;
        radioButtonGroupSafeBrowsingPreference2.A0 = no0;
        AbstractC1865bc0.b(no0, radioButtonGroupSafeBrowsingPreference2);
        this.H0.f9480J = this;
        TextMessagePreference textMessagePreference = (TextMessagePreference) f1("text_managed");
        textMessagePreference.u0 = no0;
        AbstractC1865bc0.b(no0, textMessagePreference);
        textMessagePreference.W(no0.b(this.H0));
        o1(0);
    }

    public void n1(int i) {
        if (i == 1) {
            o1(5);
        } else if (i == 2) {
            o1(4);
        }
        if (i == 2) {
            u();
            throw null;
        } else if (i == 1) {
            u();
            throw null;
        }
    }

    public final void o1(int i) {
        String str;
        int i2 = this.I0;
        String str2 = i2 == 1 ? "ParentSettings" : i2 == 2 ? "SafetyCheck" : i2 == 3 ? "SurfaceExplorerPromoSlinger" : i2 == 4 ? "SecurityInterstitial" : "Default";
        AbstractC3364kK0.g("SafeBrowsing.Settings.UserAction." + str2, i, 8);
        switch (i) {
            case 0:
                str = AbstractC2531fV.f("ShowedFrom", str2);
                break;
            case 1:
                str = "EnhancedProtectionClicked";
                break;
            case 2:
                str = "StandardProtectionClicked";
                break;
            case 3:
                str = "DisableSafeBrowsingClicked";
                break;
            case 4:
                str = "EnhancedProtectionExpandArrowClicked";
                break;
            case 5:
                str = "StandardProtectionExpandArrowClicked";
                break;
            case 6:
                str = "DisableSafeBrowsingDialogConfirmed";
                break;
            case Version.VERSION_7:
                str = "DisableSafeBrowsingDialogDenied";
                break;
            default:
                str = "";
                break;
        }
        AbstractC3535lK0.a("SafeBrowsing.Settings." + str);
    }
}
