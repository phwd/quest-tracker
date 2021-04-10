package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

/* renamed from: gd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2722gd0 {
    public static ColorStateList a(Context context, C0514Ii1 ii1, int i) {
        int resourceId;
        if (ii1.b.hasValue(i) && (resourceId = ii1.b.getResourceId(i, 0)) != 0) {
            ThreadLocal threadLocal = AbstractC5544x8.f11592a;
            ColorStateList colorStateList = context.getColorStateList(resourceId);
            if (colorStateList != null) {
                return colorStateList;
            }
        }
        return ii1.c(i);
    }

    public static ColorStateList b(Context context, TypedArray typedArray, int i) {
        int resourceId;
        if (typedArray.hasValue(i) && (resourceId = typedArray.getResourceId(i, 0)) != 0) {
            ThreadLocal threadLocal = AbstractC5544x8.f11592a;
            ColorStateList colorStateList = context.getColorStateList(resourceId);
            if (colorStateList != null) {
                return colorStateList;
            }
        }
        return typedArray.getColorStateList(i);
    }

    public static Drawable c(Context context, TypedArray typedArray, int i) {
        int resourceId;
        Drawable a2;
        if (!typedArray.hasValue(i) || (resourceId = typedArray.getResourceId(i, 0)) == 0 || (a2 = AbstractC5544x8.a(context, resourceId)) == null) {
            return typedArray.getDrawable(i);
        }
        return a2;
    }
}
