package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import com.oculus.browser.R;

/* renamed from: hr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2934hr {
    public static int a(Resources resources, boolean z) {
        if (z) {
            return resources.getColor(R.color.f15350_resource_name_obfuscated_RES_2131100225);
        }
        return resources.getColor(R.color.f15340_resource_name_obfuscated_RES_2131100224);
    }

    public static int b(Resources resources, boolean z) {
        if (z) {
            return resources.getColor(R.color.f10880_resource_name_obfuscated_RES_2131099778);
        }
        return resources.getColor(R.color.f10840_resource_name_obfuscated_RES_2131099774);
    }

    public static ColorStateList c(Context context, boolean z) {
        int d = d(z);
        ThreadLocal threadLocal = AbstractC5544x8.f11592a;
        return context.getColorStateList(d);
    }

    public static int d(boolean z) {
        return z ? R.color.f11330_resource_name_obfuscated_RES_2131099823 : R.color.f11390_resource_name_obfuscated_RES_2131099829;
    }
}
