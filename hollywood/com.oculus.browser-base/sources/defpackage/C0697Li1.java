package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.text.TextPaint;
import com.oculus.browser.R;

/* renamed from: Li1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0697Li1 {

    /* renamed from: a  reason: collision with root package name */
    public final int f8433a;
    public final TextPaint b;
    public int c;
    public final int d;
    public final float e;
    public final float f;

    public C0697Li1(Context context, boolean z) {
        Resources resources = context.getResources();
        int color = resources.getColor(z ? R.color.f10750_resource_name_obfuscated_RES_2131099765 : R.color.f10740_resource_name_obfuscated_RES_2131099764);
        float dimension = resources.getDimension(R.dimen.f17480_resource_name_obfuscated_RES_2131165367);
        boolean z2 = resources.getBoolean(R.bool.f9520_resource_name_obfuscated_RES_2131034115);
        TextPaint textPaint = new TextPaint(1);
        this.b = textPaint;
        textPaint.setColor(color);
        textPaint.setTextSize(dimension);
        textPaint.setFakeBoldText(z2);
        textPaint.density = resources.getDisplayMetrics().density;
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float ceil = (float) Math.ceil((double) (fontMetrics.bottom - fontMetrics.top));
        this.e = ceil;
        this.f = -fontMetrics.top;
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.f17470_resource_name_obfuscated_RES_2131165366);
        this.c = dimensionPixelSize;
        this.d = (int) Math.max((float) dimensionPixelSize, ceil);
        int max = (int) (((float) Math.max(resources.getDisplayMetrics().widthPixels, resources.getDisplayMetrics().heightPixels)) * 1.0f);
        this.f8433a = max;
        this.c = Math.min(max, this.c);
    }
}
