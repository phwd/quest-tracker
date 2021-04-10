package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RatingBar;
import com.oculus.browser.R;

/* renamed from: w8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5374w8 extends RatingBar {
    public final C5034u8 F;

    public C5374w8(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.f7230_resource_name_obfuscated_RES_2130969169);
        AbstractC1361Wg1.a(this, getContext());
        C5034u8 u8Var = new C5034u8(this);
        this.F = u8Var;
        u8Var.a(attributeSet, R.attr.f7230_resource_name_obfuscated_RES_2130969169);
    }

    public synchronized void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        Bitmap bitmap = this.F.c;
        if (bitmap != null) {
            setMeasuredDimension(View.resolveSizeAndState(bitmap.getWidth() * getNumStars(), i, 0), getMeasuredHeight());
        }
    }
}
