package defpackage;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.EdgeEffect;

/* renamed from: A6  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class A6 extends View {
    public final EdgeEffect F;

    public A6(Context context) {
        super(context);
        EdgeEffect edgeEffect = new EdgeEffect(context);
        this.F = edgeEffect;
        edgeEffect.setColor(17170444);
    }

    public void onDraw(Canvas canvas) {
        boolean z;
        super.onDraw(canvas);
        if (!this.F.isFinished()) {
            int save = canvas.save();
            canvas.translate((float) getWidth(), 0.0f);
            canvas.rotate(90.0f, 0.0f, 0.0f);
            z = this.F.draw(canvas);
            canvas.restoreToCount(save);
        } else {
            z = false;
        }
        if (z) {
            postInvalidateOnAnimation();
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.F.setSize(getHeight(), getWidth());
    }
}
