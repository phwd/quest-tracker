package org.chromium.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.style.BulletSpan;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ChromeBulletSpan extends BulletSpan {
    public int F;

    public ChromeBulletSpan(Context context) {
        super(context.getResources().getDimensionPixelSize(R.dimen.f17310_resource_name_obfuscated_RES_2131165350));
        this.F = context.getResources().getDimensionPixelSize(R.dimen.f17320_resource_name_obfuscated_RES_2131165351);
    }

    public void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
        super.drawLeadingMargin(canvas, paint, i + this.F, i2, i3, i4, i5, charSequence, i6, i7, z, layout);
    }
}
