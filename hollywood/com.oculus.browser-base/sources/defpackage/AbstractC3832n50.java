package defpackage;

import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: n50  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3832n50 {
    public static C1175Tf1 a(String str, C4390qK0 qk0, View view, String str2) {
        C1175Tf1 tf1;
        Tm1 b = b();
        if (b == null || !b.shouldTriggerHelpUI(str)) {
            return null;
        }
        if (str2 == null || str2.isEmpty()) {
            str.hashCode();
            char c = 65535;
            int i = 0;
            switch (str.hashCode()) {
                case -1315293096:
                    if (str.equals("IPH_KeyboardAccessoryAddressFilling")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1195119663:
                    if (str.equals("IPH_KeyboardAccessoryBarSwiping")) {
                        c = 1;
                        break;
                    }
                    break;
                case 532938391:
                    if (str.equals("IPH_KeyboardAccessoryPasswordFilling")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1572211206:
                    if (str.equals("IPH_KeyboardAccessoryPaymentFilling")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                case 2:
                case 3:
                    i = R.string.f53400_resource_name_obfuscated_RES_2131952657;
                    break;
                case 1:
                    i = R.string.f53410_resource_name_obfuscated_RES_2131952658;
                    break;
            }
            tf1 = new C1175Tf1(view.getContext(), view, i, i, true, qk0, C0283Ep.h().d());
        } else {
            tf1 = new C1175Tf1(view.getContext(), view, str2, str2, true, qk0, C0283Ep.h().d());
        }
        tf1.e(true);
        tf1.I.P.b(new C3661m50(b, str));
        return tf1;
    }

    public static Tm1 b() {
        Tm1 a2 = Um1.a(Profile.b());
        if (!a2.isInitialized()) {
            return null;
        }
        return a2;
    }

    public static void c(String str, View view, View view2, String str2) {
        C1175Tf1 a2 = a(str, new ViewTreeObserver$OnGlobalLayoutListenerC2606fv1(view), view2, str2);
        if (a2 != null) {
            view.setSelected(true);
            a2.I.P.b(new C3490l50(view));
            a2.f();
        }
    }
}
