package defpackage;

import android.content.Context;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;

/* renamed from: Uc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1226Uc0 {
    public static int a(Context context, int i, int i2) {
        TypedValue a2 = AbstractC0251Ec0.a(context, i);
        return a2 != null ? a2.data : i2;
    }

    public static int b(View view, int i) {
        return AbstractC0251Ec0.c(view.getContext(), i, view.getClass().getCanonicalName());
    }

    public static int c(int i, int i2, float f) {
        return AbstractC1331Vv.e(AbstractC1331Vv.h(i2, Math.round(((float) Color.alpha(i2)) * f)), i);
    }
}
