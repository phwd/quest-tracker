package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Size;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import java.util.List;
import java.util.Objects;

/* renamed from: aE0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1629aE0 extends FrameLayout {
    public ED0 F;
    public QD0 G;
    public AbstractC1809bE0 H;
    public List I;

    /* renamed from: J  reason: collision with root package name */
    public List f9421J;
    public Matrix K;

    public C1629aE0(Context context, boolean z, AbstractC1809bE0 be0, RD0 rd0, Runnable runnable) {
        super(context);
        setWillNotDraw(false);
        this.H = be0;
        this.F = new ED0(new ZD0(this), runnable);
        this.G = new QD0(context, z, rd0);
    }

    public final void a() {
        if (this.I == null || this.f9421J == null) {
            removeAllViews();
            return;
        }
        for (int i = 0; i < this.I.size(); i++) {
            View view = (View) this.I.get(i);
            if (view.getVisibility() != 0) {
                removeView(view);
            } else {
                if (view.getParent() == null) {
                    addView((View) this.I.get(i));
                } else if (view.getParent() != this) {
                    throw new IllegalStateException("Sub-frame view already has a parent.");
                }
                Rect rect = (Rect) this.f9421J.get(i);
                view.layout(rect.left, rect.top, rect.right, rect.bottom);
            }
        }
    }

    public void onDraw(Canvas canvas) {
        int i;
        canvas.save();
        canvas.concat(this.K);
        ED0 ed0 = this.F;
        if (ed0.b != null && !ed0.c.isEmpty() && ed0.f7945a.getWidth() > 0 && ed0.f7945a.getHeight() > 0) {
            int width = ed0.c.left / ed0.f7945a.getWidth();
            int ceil = (int) Math.ceil(((double) ed0.c.right) / ((double) ed0.f7945a.getWidth()));
            int min = Math.min((int) Math.ceil(((double) ed0.c.bottom) / ((double) ed0.f7945a.getHeight())), ed0.b.length);
            int i2 = 0;
            int min2 = Math.min(ceil, min >= 1 ? ed0.b[min - 1].length : 0);
            ed0.j.clear();
            ed0.i.clear();
            for (int height = ed0.c.top / ed0.f7945a.getHeight(); height < min; height++) {
                int i3 = width;
                while (i3 < min2) {
                    C2952hx hxVar = ed0.b[height][i3];
                    if (hxVar != null) {
                        ed0.i.add(hxVar);
                        if (!hxVar.d()) {
                            ed0.h.post(ed0.f);
                        } else {
                            Bitmap bitmap = hxVar.f10112a;
                            if (bitmap == null) {
                                hxVar.e();
                                ed0.j.add(hxVar);
                                hxVar.c.b(new RunnableC2268dx(hxVar, new CD0(ed0)));
                            } else {
                                ed0.k.add(hxVar);
                                int max = Math.max(ed0.c.left - (ed0.f7945a.getWidth() * i3), i2);
                                int max2 = Math.max(ed0.c.top - (ed0.f7945a.getHeight() * height), i2);
                                ed0.d.set(max, max2, Math.min(ed0.f7945a.getWidth(), (ed0.c.right + max) - (ed0.f7945a.getWidth() * i3)), Math.min(ed0.f7945a.getHeight(), (ed0.c.bottom + max2) - (ed0.f7945a.getHeight() * height)));
                                i = 0;
                                int max3 = Math.max((ed0.f7945a.getWidth() * i3) - ed0.c.left, 0);
                                int max4 = Math.max((ed0.f7945a.getHeight() * height) - ed0.c.top, 0);
                                ed0.e.set(max3, max4, ed0.d.width() + max3, ed0.d.height() + max4);
                                canvas.drawBitmap(bitmap, ed0.d, ed0.e, (Paint) null);
                                hxVar.e();
                                Runnable runnable = ed0.g;
                                if (runnable != null) {
                                    runnable.run();
                                    ed0.g = null;
                                }
                                i3++;
                                i2 = i;
                            }
                        }
                    }
                    i = i2;
                    i3++;
                    i2 = i;
                }
            }
            for (C2952hx hxVar2 : ed0.k) {
                if (!ed0.i.contains(hxVar2)) {
                    hxVar2.c.b(new RunnableC2098cx(hxVar2));
                }
            }
            ed0.k.clear();
            ed0.k.addAll(ed0.i);
        }
        canvas.restore();
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        AbstractC1809bE0 be0 = this.H;
        int width = getWidth();
        int height = getHeight();
        SD0 sd0 = (SD0) be0;
        if (!sd0.l.isIdentity()) {
            C1980cE0 ce0 = sd0.i;
            Objects.requireNonNull(ce0);
            ce0.f9592a = new Size(width, height);
            return;
        }
        boolean z2 = false;
        if (!sd0.j) {
            sd0.k = ((float) width) / ((float) sd0.b.getWidth());
            for (int i5 = 0; i5 < sd0.c.size(); i5++) {
                ((SD0) sd0.e.get(i5)).c(sd0.k);
            }
        }
        float c = sd0.i.c();
        if (c == 0.0f) {
            c = sd0.k;
        }
        if (width > 0 && height > 0) {
            sd0.i.h((float) Math.max(0, Math.min(Math.round(sd0.i.d()), Math.round(((float) sd0.b.getWidth()) * c) - width)), (float) Math.max(0, Math.min(Math.round(sd0.i.e()), Math.round(((float) sd0.b.getHeight()) * c) - height)));
            C1980cE0 ce02 = sd0.i;
            Objects.requireNonNull(ce02);
            ce02.f9592a = new Size(width, height);
            float c2 = sd0.i.c();
            sd0.i.g(c);
            if (c2 != c) {
                z2 = true;
            }
            sd0.f(z2);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.G.a(motionEvent) || super.onTouchEvent(motionEvent);
    }
}
