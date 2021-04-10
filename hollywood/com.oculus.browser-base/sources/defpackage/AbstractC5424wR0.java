package defpackage;

import android.content.Context;
import android.text.Layout;
import android.text.TextPaint;
import com.oculus.browser.R;

/* renamed from: wR0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5424wR0 {
    public static String a(Context context, C5084uR0 ur0, Layout layout, TextPaint textPaint) {
        int e = ur0.e();
        C1997cK c = ur0.c(0);
        String string = context.getString(R.string.f47030_resource_name_obfuscated_RES_2131952020);
        String b = c.b(string, -1);
        int i = e - 1;
        if (i == 0) {
            return b;
        }
        int i2 = ur0.b;
        int i3 = i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? 0 : R.plurals.f42870_resource_name_obfuscated_RES_2131820579 : R.plurals.f42860_resource_name_obfuscated_RES_2131820578 : R.plurals.f42890_resource_name_obfuscated_RES_2131820581 : R.plurals.f42880_resource_name_obfuscated_RES_2131820580;
        String quantityString = context.getResources().getQuantityString(i3, i, b, Integer.valueOf(i));
        if (!(textPaint == null || layout == null)) {
            int ellipsizedWidth = layout.getEllipsizedWidth();
            while (Layout.getDesiredWidth(quantityString, textPaint) > ((float) ellipsizedWidth)) {
                b = c.b(string, b.length());
                quantityString = context.getResources().getQuantityString(i3, i, b, Integer.valueOf(i));
            }
        }
        return quantityString;
    }
}
