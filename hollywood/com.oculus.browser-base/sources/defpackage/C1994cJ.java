package defpackage;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* renamed from: cJ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1994cJ extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    public final Paint f9597a = new Paint();
    public final Rect b = new Rect();
    public final Integer c = null;

    public C1994cJ(Integer num) {
    }

    public void draw(Canvas canvas) {
        Integer num = this.c;
        if (num != null) {
            canvas.drawColor(num.intValue());
        }
        canvas.drawRect(this.b, this.f9597a);
    }

    public int getOpacity() {
        return -2;
    }

    public void onBoundsChange(Rect rect) {
        this.b.set(0, 0, rect.width(), this.b.height());
    }

    public void setAlpha(int i) {
    }

    public void setColorFilter(ColorFilter colorFilter) {
    }
}
