package defpackage;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import com.oculus.browser.R;

/* renamed from: hT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2870hT0 {
    public static void a(AbstractC2324eF0 ef0, int i) {
        StrictMode.ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            ef0.e1(i);
        } finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }

    public static Drawable b(Context context, int i) {
        return c(context, i, R.color.f11220_resource_name_obfuscated_RES_2131099812);
    }

    public static Drawable c(Context context, int i, int i2) {
        Drawable a2 = AbstractC5544x8.a(context, i);
        a2.setColorFilter(context.getResources().getColor(i2), PorterDuff.Mode.SRC_IN);
        return a2;
    }
}
