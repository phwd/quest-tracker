package defpackage;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.TypedValue;
import com.oculus.os.Version;

/* renamed from: qv1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4486qv1 {
    public static float a(Context context, int i) {
        return TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    public static PorterDuff.Mode b(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case Version.VERSION_14:
                return PorterDuff.Mode.MULTIPLY;
            case Version.VERSION_15:
                return PorterDuff.Mode.SCREEN;
            case Version.VERSION_16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }
}
