package defpackage;

import J.N;
import android.content.res.Resources;
import android.graphics.Color;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Pj1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Pj1 {
    public static boolean a() {
        return C5052uE.a() || (AbstractC4226pO.a() && N.M09VlOh_("HorizontalTabSwitcherAndroid")) || AbstractC4772sd1.b() || AbstractC2793h01.b();
    }

    public static int b(Resources resources, Tab tab, int i) {
        int c = c(resources, i, tab != null && tab.a());
        if ((tab != null ? tab.Q() : null) != null) {
            return 0;
        }
        return c;
    }

    public static int c(Resources resources, int i, boolean z) {
        if (z) {
            int color = resources.getColor(R.color.f15390_resource_name_obfuscated_RES_2131100229);
            return AbstractC1270Uv.a(i, color & -16777216, ((float) Color.alpha(color)) / 255.0f);
        }
        boolean z2 = false;
        if (i == AbstractC2934hr.a(resources, false)) {
            z2 = true;
        }
        if (z2) {
            return resources.getColor(R.color.f15380_resource_name_obfuscated_RES_2131100228);
        }
        if (AbstractC1270Uv.h(i)) {
            return -1;
        }
        return AbstractC1270Uv.a(i, -1, 0.2f);
    }

    public static boolean d(Resources resources, boolean z, int i) {
        return i == AbstractC2934hr.a(resources, z);
    }
}
