package org.chromium.components.embedder_support.delegate;

import android.content.Context;
import org.chromium.base.ContextUtils;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ColorChooserAndroid {

    /* renamed from: a  reason: collision with root package name */
    public final AlertDialogC1026Qv f10841a;
    public final long b;

    public ColorChooserAndroid(long j, Context context, int i, ColorSuggestion[] colorSuggestionArr) {
        C0478Hv hv = new C0478Hv(this);
        this.b = j;
        this.f10841a = new AlertDialogC1026Qv(context, hv, i, colorSuggestionArr);
    }

    public static void addToColorSuggestionArray(ColorSuggestion[] colorSuggestionArr, int i, int i2, String str) {
        colorSuggestionArr[i] = new ColorSuggestion(i2, str);
    }

    public static ColorChooserAndroid createColorChooserAndroid(long j, WindowAndroid windowAndroid, int i, ColorSuggestion[] colorSuggestionArr) {
        if (windowAndroid == null) {
            return null;
        }
        Context context = (Context) windowAndroid.f11022J.get();
        if (ContextUtils.a(context) == null) {
            return null;
        }
        ColorChooserAndroid colorChooserAndroid = new ColorChooserAndroid(j, context, i, colorSuggestionArr);
        P21 f0 = P21.f0();
        try {
            colorChooserAndroid.f10841a.show();
            f0.close();
            return colorChooserAndroid;
        } catch (Throwable th) {
            AbstractC0754Mh1.f8495a.a(th, th);
        }
        throw th;
    }

    public static ColorSuggestion[] createColorSuggestionArray(int i) {
        return new ColorSuggestion[i];
    }

    public void closeColorChooser() {
        this.f10841a.dismiss();
    }
}
