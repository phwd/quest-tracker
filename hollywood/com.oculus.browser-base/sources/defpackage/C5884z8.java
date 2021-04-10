package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z8  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5884z8 extends C5034u8 {
    public final SeekBar d;
    public Drawable e;
    public ColorStateList f = null;
    public PorterDuff.Mode g = null;
    public boolean h = false;
    public boolean i = false;

    public C5884z8(SeekBar seekBar) {
        super(seekBar);
        this.d = seekBar;
    }

    @Override // defpackage.C5034u8
    public void a(AttributeSet attributeSet, int i2) {
        super.a(attributeSet, i2);
        Context context = this.d.getContext();
        int[] iArr = FJ0.l;
        C0514Ii1 q = C0514Ii1.q(context, attributeSet, iArr, i2, 0);
        SeekBar seekBar = this.d;
        AbstractC1920bu1.m(seekBar, seekBar.getContext(), iArr, attributeSet, q.b, i2, 0);
        Drawable h2 = q.h(0);
        if (h2 != null) {
            this.d.setThumb(h2);
        }
        Drawable g2 = q.g(1);
        Drawable drawable = this.e;
        if (drawable != null) {
            drawable.setCallback(null);
        }
        this.e = g2;
        if (g2 != null) {
            g2.setCallback(this.d);
            SeekBar seekBar2 = this.d;
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            g2.setLayoutDirection(seekBar2.getLayoutDirection());
            if (g2.isStateful()) {
                g2.setState(this.d.getDrawableState());
            }
            c();
        }
        this.d.invalidate();
        if (q.o(3)) {
            this.g = XI.c(q.j(3, -1), this.g);
            this.i = true;
        }
        if (q.o(2)) {
            this.f = q.c(2);
            this.h = true;
        }
        q.b.recycle();
        c();
    }

    public final void c() {
        Drawable drawable = this.e;
        if (drawable == null) {
            return;
        }
        if (this.h || this.i) {
            Drawable mutate = drawable.mutate();
            this.e = mutate;
            if (this.h) {
                mutate.setTintList(this.f);
            }
            if (this.i) {
                this.e.setTintMode(this.g);
            }
            if (this.e.isStateful()) {
                this.e.setState(this.d.getDrawableState());
            }
        }
    }

    public void d(Canvas canvas) {
        if (this.e != null) {
            int max = this.d.getMax();
            int i2 = 1;
            if (max > 1) {
                int intrinsicWidth = this.e.getIntrinsicWidth();
                int intrinsicHeight = this.e.getIntrinsicHeight();
                int i3 = intrinsicWidth >= 0 ? intrinsicWidth / 2 : 1;
                if (intrinsicHeight >= 0) {
                    i2 = intrinsicHeight / 2;
                }
                this.e.setBounds(-i3, -i2, i3, i2);
                float width = ((float) ((this.d.getWidth() - this.d.getPaddingLeft()) - this.d.getPaddingRight())) / ((float) max);
                int save = canvas.save();
                canvas.translate((float) this.d.getPaddingLeft(), (float) (this.d.getHeight() / 2));
                for (int i4 = 0; i4 <= max; i4++) {
                    this.e.draw(canvas);
                    canvas.translate(width, 0.0f);
                }
                canvas.restoreToCount(save);
            }
        }
    }
}
