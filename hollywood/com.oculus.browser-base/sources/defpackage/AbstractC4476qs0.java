package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import com.oculus.browser.R;

/* renamed from: qs0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4476qs0 {
    public static boolean a(int i) {
        return i == 1 || i == 2;
    }

    public static Drawable b(Context context, int i, int i2) {
        if (i == 2) {
            context = AbstractC3612lp0.b(context, R.style.f72810_resource_name_obfuscated_RES_2132017854, true);
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(i2, typedValue, true);
        int i3 = typedValue.resourceId;
        Object obj = K2.f8337a;
        return context.getDrawable(i3);
    }
}
