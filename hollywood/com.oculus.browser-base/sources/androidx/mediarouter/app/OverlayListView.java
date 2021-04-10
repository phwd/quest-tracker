package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class OverlayListView extends ListView {
    public final List F = new ArrayList();

    public OverlayListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onDraw(Canvas canvas) {
        boolean z;
        float f;
        super.onDraw(canvas);
        if (this.F.size() > 0) {
            Iterator it = this.F.iterator();
            while (it.hasNext()) {
                C0048At0 at0 = (C0048At0) it.next();
                BitmapDrawable bitmapDrawable = at0.f7702a;
                if (bitmapDrawable != null) {
                    bitmapDrawable.draw(canvas);
                }
                long drawingTime = getDrawingTime();
                if (at0.l) {
                    z = false;
                } else {
                    float f2 = 0.0f;
                    float max = Math.max(0.0f, Math.min(1.0f, ((float) (drawingTime - at0.j)) / ((float) at0.e)));
                    if (at0.k) {
                        f2 = max;
                    }
                    Interpolator interpolator = at0.d;
                    if (interpolator == null) {
                        f = f2;
                    } else {
                        f = interpolator.getInterpolation(f2);
                    }
                    int i = (int) (((float) at0.g) * f);
                    Rect rect = at0.c;
                    Rect rect2 = at0.f;
                    rect.top = rect2.top + i;
                    rect.bottom = rect2.bottom + i;
                    float f3 = at0.h;
                    float a2 = AbstractC2531fV.a(at0.i, f3, f, f3);
                    at0.b = a2;
                    BitmapDrawable bitmapDrawable2 = at0.f7702a;
                    if (bitmapDrawable2 != null) {
                        bitmapDrawable2.setAlpha((int) (a2 * 255.0f));
                        at0.f7702a.setBounds(at0.c);
                    }
                    if (at0.k && f2 >= 1.0f) {
                        at0.l = true;
                        C4266pf0 pf0 = at0.m;
                        if (pf0 != null) {
                            pf0.b.n0.remove(pf0.f11080a);
                            pf0.b.j0.notifyDataSetChanged();
                        }
                    }
                    z = !at0.l;
                }
                if (!z) {
                    it.remove();
                }
            }
        }
    }
}
