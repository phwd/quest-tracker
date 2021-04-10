package defpackage;

import android.content.Context;
import android.graphics.Color;

/* renamed from: Uv  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1270Uv {
    public static int a(int i, int i2, float f) {
        return b(i, i2, f, false);
    }

    public static int b(int i, int i2, float f, boolean z) {
        int e = (int) AbstractC4089od0.e((float) Color.red(i), (float) Color.red(i2), f);
        int e2 = (int) AbstractC4089od0.e((float) Color.green(i), (float) Color.green(i2), f);
        int e3 = (int) AbstractC4089od0.e((float) Color.blue(i), (float) Color.blue(i2), f);
        if (z) {
            return Color.argb((int) AbstractC4089od0.e((float) Color.alpha(i), (float) Color.alpha(i2), f), e, e2, e3);
        }
        return Color.rgb(e, e2, e3);
    }

    public static int c(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        fArr[2] = fArr[2] * 0.6f;
        return Color.HSVToColor(fArr);
    }

    public static float d(int i) {
        int red = Color.red(i);
        int green = Color.green(i);
        int blue = Color.blue(i);
        return ((float) ((Math.min(red, Math.min(green, blue)) + Math.max(red, Math.max(green, blue))) / 2)) / 255.0f;
    }

    public static boolean e(Context context) {
        return (context.getResources().getConfiguration().uiMode & 48) == 32;
    }

    public static boolean f(int i) {
        return d(i) <= 0.94f;
    }

    public static boolean g(int i) {
        float f;
        float f2;
        float f3;
        float red = ((float) Color.red(i)) / 255.0f;
        float green = ((float) Color.green(i)) / 255.0f;
        float blue = ((float) Color.blue(i)) / 255.0f;
        if (red < 0.03928f) {
            f = red / 12.92f;
        } else {
            f = (float) Math.pow((double) ((red + 0.055f) / 1.055f), 2.4000000953674316d);
        }
        if (green < 0.03928f) {
            f2 = green / 12.92f;
        } else {
            f2 = (float) Math.pow((double) ((green + 0.055f) / 1.055f), 2.4000000953674316d);
        }
        if (blue < 0.03928f) {
            f3 = blue / 12.92f;
        } else {
            f3 = (float) Math.pow((double) ((blue + 0.055f) / 1.055f), 2.4000000953674316d);
        }
        return Math.abs(1.05f / (((f3 * 0.0722f) + ((f2 * 0.7152f) + (f * 0.2126f))) + 0.05f)) >= 3.0f;
    }

    public static boolean h(int i) {
        return d(i) > 0.82f;
    }
}
