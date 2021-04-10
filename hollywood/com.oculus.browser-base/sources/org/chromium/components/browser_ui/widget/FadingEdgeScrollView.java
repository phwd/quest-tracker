package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ScrollView;
import com.oculus.browser.R;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FadingEdgeScrollView extends ScrollView {
    public final Paint F = new Paint();
    public final int G = getResources().getColor(R.color.f15370_resource_name_obfuscated_RES_2131100227);
    public final int H = getResources().getDimensionPixelSize(R.dimen.f18710_resource_name_obfuscated_RES_2131165490);
    public int I = 1;

    /* renamed from: J  reason: collision with root package name */
    public int f10818J = 1;

    public FadingEdgeScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public final void a(Canvas canvas, int i, float f, int i2) {
        if (i2 != 0) {
            float f2 = 1.0f;
            if (i2 == 1) {
                f2 = Math.max(0.0f, Math.min(1.0f, f));
            }
            if (f2 > 0.0f) {
                this.F.setColor(Color.argb((int) (((float) Color.alpha(this.G)) * f2), (int) (((float) Color.red(this.G)) * f2), (int) (((float) Color.green(this.G)) * f2), (int) (((float) Color.blue(this.G)) * f2)));
                int scrollX = getScrollX();
                int right = getRight() + scrollX;
                if (i == 1) {
                    int bottom = (getBottom() + getScrollY()) - getTop();
                    canvas.drawRect((float) scrollX, (float) (bottom - this.H), (float) right, (float) bottom, this.F);
                } else if (i == 0) {
                    int scrollY = getScrollY();
                    canvas.drawRect((float) scrollX, (float) scrollY, (float) right, (float) (scrollY + this.H), this.F);
                }
            }
        }
    }

    public void b(int i, int i2) {
        this.I = i;
        this.f10818J = i2;
        invalidate();
    }

    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        setVerticalFadingEdgeEnabled(true);
        float topFadingEdgeStrength = getTopFadingEdgeStrength();
        float bottomFadingEdgeStrength = getBottomFadingEdgeStrength();
        setVerticalFadingEdgeEnabled(false);
        a(canvas, 0, topFadingEdgeStrength, this.I);
        a(canvas, 1, bottomFadingEdgeStrength, this.f10818J);
    }
}
