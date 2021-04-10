package org.chromium.components.browser_ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FadingShadowView extends View {
    public VN F;
    public int G;
    public float H = 1.0f;

    public FadingShadowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a(int i, int i2) {
        this.F = new VN(i);
        this.G = i2;
        postInvalidateOnAnimation();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        VN vn = this.F;
        if (vn != null) {
            int i = this.G;
            float f = this.H;
            Objects.requireNonNull(vn);
            float max = Math.max(0.0f, Math.min(1.0f, f)) * ((float) getHeight());
            if (max >= 1.0f) {
                int scrollX = getScrollX();
                int right = getRight() + scrollX;
                if (i == 1) {
                    int bottom = (getBottom() + getScrollY()) - getTop();
                    vn.b.setScale(1.0f, max);
                    vn.b.postRotate(180.0f);
                    float f2 = (float) scrollX;
                    float f3 = (float) bottom;
                    vn.b.postTranslate(f2, f3);
                    vn.c.setLocalMatrix(vn.b);
                    vn.f9079a.setShader(vn.c);
                    canvas.drawRect(f2, f3 - max, (float) right, f3, vn.f9079a);
                } else if (i == 0) {
                    int scrollY = getScrollY();
                    vn.b.setScale(1.0f, max);
                    float f4 = (float) scrollX;
                    float f5 = (float) scrollY;
                    vn.b.postTranslate(f4, f5);
                    vn.c.setLocalMatrix(vn.b);
                    vn.f9079a.setShader(vn.c);
                    canvas.drawRect(f4, f5, (float) right, f5 + max, vn.f9079a);
                }
            }
        }
    }
}
