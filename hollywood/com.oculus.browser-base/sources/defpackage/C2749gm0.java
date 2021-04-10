package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;

/* renamed from: gm0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2749gm0 {

    /* renamed from: a  reason: collision with root package name */
    public final C4384qI0 f10018a;
    public final Callback b;
    public final boolean c;
    public final boolean d;
    public final List e = new ArrayList(4);
    public final AtomicInteger f = new AtomicInteger();
    public Canvas g;
    public Bitmap h;
    public String i;
    public final /* synthetic */ C2920hm0 j;

    public C2749gm0(C2920hm0 hm0, C4384qI0 qi0, Callback callback, boolean z, boolean z2) {
        this.j = hm0;
        this.b = callback;
        this.f10018a = qi0;
        this.c = z;
        this.d = z2;
    }

    public final void a(Drawable drawable, int i2) {
        RectF rectF = (RectF) this.j.o.get(i2);
        C2920hm0 hm0 = this.j;
        this.g.drawCircle((rectF.left + rectF.right) / 2.0f, (rectF.bottom + rectF.top) / 2.0f, (rectF.width() / 2.0f) - hm0.e, hm0.k);
        drawable.setBounds((Rect) this.j.m.get(i2));
        drawable.draw(this.g);
        if (this.f.decrementAndGet() == 0) {
            PostTask.b(Zo1.c, this.b.a(this.h), 0);
        }
    }

    public final void b(Bitmap bitmap, int i2) {
        C2920hm0 hm0 = this.j;
        float f2 = hm0.d;
        this.g.drawRoundRect((RectF) this.j.n.get(i2), f2, f2, hm0.h);
        if (bitmap != null) {
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) ((RectF) this.j.n.get(i2)).width(), (int) ((RectF) this.j.n.get(i2)).height(), true);
            this.j.h.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            this.g.drawBitmap(createScaledBitmap, new Rect(0, 0, createScaledBitmap.getWidth(), createScaledBitmap.getHeight()), (RectF) this.j.n.get(i2), this.j.h);
            createScaledBitmap.recycle();
            this.j.h.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
            C2920hm0 hm02 = this.j;
            float f3 = hm02.d;
            this.g.drawRoundRect((RectF) this.j.n.get(i2), f3, f3, hm02.i);
        }
    }
}
