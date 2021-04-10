package defpackage;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.oculus.browser.R;

/* renamed from: Qf0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0991Qf0 {
    public static int a(Context context) {
        float fraction;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        boolean z = displayMetrics.widthPixels < displayMetrics.heightPixels;
        TypedValue typedValue = new TypedValue();
        context.getResources().getValue(z ? R.dimen.f21040_resource_name_obfuscated_RES_2131165723 : R.dimen.f21030_resource_name_obfuscated_RES_2131165722, typedValue, true);
        int i = typedValue.type;
        if (i == 5) {
            fraction = typedValue.getDimension(displayMetrics);
        } else if (i != 6) {
            return -2;
        } else {
            int i2 = displayMetrics.widthPixels;
            fraction = typedValue.getFraction((float) i2, (float) i2);
        }
        return (int) fraction;
    }

    public static int b(Context context) {
        if (!context.getResources().getBoolean(R.bool.f9540_resource_name_obfuscated_RES_2131034117)) {
            return -1;
        }
        return a(context);
    }
}
