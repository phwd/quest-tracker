package defpackage;

import android.content.SharedPreferences;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: n81  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3842n81 {

    /* renamed from: a  reason: collision with root package name */
    public static AbstractC1099Sa1 f10474a;

    public static Tab a(AbstractC0124Ca1 ca1, Tab tab) {
        I71 i71 = (I71) ((AbstractC0246Ea1) ca1).c.d();
        return i71.getTabAt(i71.i(tab));
    }

    public static SharedPreferences b() {
        return ContextUtils.getApplicationContext().getSharedPreferences("tab_group_titles", 0);
    }

    public static String c(int i) {
        return b().getString(String.valueOf(i), null);
    }

    public static void d(String str, View view, AbstractC4448qj qjVar) {
        int i;
        int i2;
        int i3;
        if (!AbstractC4772sd1.g() && (!AbstractC4772sd1.a() || !str.equals("IPH_TabGroupsTapToSeeAnotherTab"))) {
            return;
        }
        if (!AbstractC4772sd1.d() || view != null) {
            char c = 65535;
            switch (str.hashCode()) {
                case -1060009038:
                    if (str.equals("IPH_TabGroupsTapToSeeAnotherTab")) {
                        c = 0;
                        break;
                    }
                    break;
                case 1292823118:
                    if (str.equals("IPH_TabGroupsQuicklyComparePages")) {
                        c = 1;
                        break;
                    }
                    break;
                case 1307968694:
                    if (str.equals("IPH_TabGroupsYourTabsTogether")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    i2 = R.string.f53510_resource_name_obfuscated_RES_2131952668;
                    i = R.string.f53500_resource_name_obfuscated_RES_2131952667;
                    break;
                case 1:
                    i3 = R.string.f53490_resource_name_obfuscated_RES_2131952666;
                    i2 = i3;
                    i = i2;
                    break;
                case 2:
                    i3 = R.string.f53520_resource_name_obfuscated_RES_2131952669;
                    i2 = i3;
                    i = i2;
                    break;
                default:
                    return;
            }
            Tm1 a2 = Um1.a(Profile.b());
            if (a2.isInitialized() && a2.shouldTriggerHelpUI(str)) {
                C1175Tf1 tf1 = new C1175Tf1(view.getContext(), view, i2, i, true, (C4390qK0) new ViewTreeObserver$OnGlobalLayoutListenerC2606fv1(view), C0283Ep.h().d());
                tf1.e(true);
                if (!AbstractC4772sd1.c()) {
                    tf1.I.P.b(new C3158j81(a2, str));
                    tf1.f();
                } else if (qjVar != null) {
                    C3500l81 l81 = new C3500l81(tf1);
                    tf1.I.P.b(new C3329k81(qjVar, a2, str, l81));
                    ((C5638xj) qjVar).j(l81);
                    tf1.f();
                }
            }
        }
    }
}
