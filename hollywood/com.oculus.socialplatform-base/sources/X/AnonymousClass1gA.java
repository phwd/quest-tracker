package X;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import androidx.annotation.NonNull;
import java.util.List;

/* renamed from: X.1gA  reason: invalid class name */
public final class AnonymousClass1gA extends Drawable implements Animatable, AnonymousClass1hK, AbstractC10491my {
    public int A00;
    public Paint A01;
    public Rect A02;
    public boolean A03;
    public boolean A04;
    public int A05 = -1;
    public boolean A06;
    public boolean A07;
    public boolean A08 = true;
    public final C09131gk A09;

    public final int getOpacity() {
        return -2;
    }

    public final void start() {
        this.A07 = true;
        this.A00 = 0;
        if (this.A08) {
            A00();
        }
    }

    public final void stop() {
        this.A07 = false;
        this.A04 = false;
        AnonymousClass1g5 r1 = this.A09.A00;
        List<AnonymousClass1hK> list = r1.A0G;
        list.remove(this);
        if (list.isEmpty()) {
            r1.A09 = false;
        }
    }

    private void A00() {
        AnonymousClass1S2.A01(!this.A03, "You cannot start a recycled Drawable. Ensure thatyou clear any references to the Drawable when clearing the corresponding request.");
        AnonymousClass1g5 r2 = this.A09.A00;
        if (r2.A0E.A07.A02 != 1) {
            if (!this.A04) {
                this.A04 = true;
                if (!r2.A08) {
                    List<AnonymousClass1hK> list = r2.A0G;
                    if (!list.contains(this)) {
                        boolean isEmpty = list.isEmpty();
                        list.add(this);
                        if (isEmpty && !r2.A09) {
                            r2.A09 = true;
                            r2.A08 = false;
                            AnonymousClass1g5.A00(r2);
                        }
                    } else {
                        throw new IllegalStateException("Cannot subscribe twice in a row");
                    }
                } else {
                    throw new IllegalStateException("Cannot subscribe to a cleared frame loader");
                }
            } else {
                return;
            }
        }
        invalidateSelf();
    }

    public final void draw(@NonNull Canvas canvas) {
        Bitmap bitmap;
        if (!this.A03) {
            if (this.A06) {
                int intrinsicWidth = getIntrinsicWidth();
                int intrinsicHeight = getIntrinsicHeight();
                Rect bounds = getBounds();
                Rect rect = this.A02;
                if (rect == null) {
                    rect = new Rect();
                    this.A02 = rect;
                }
                Gravity.apply(119, intrinsicWidth, intrinsicHeight, bounds, rect);
                this.A06 = false;
            }
            AnonymousClass1g5 r1 = this.A09.A00;
            AnonymousClass1gV r0 = r1.A05;
            if (r0 != null) {
                bitmap = r0.A00;
            } else {
                bitmap = r1.A03;
            }
            Rect rect2 = this.A02;
            if (rect2 == null) {
                rect2 = new Rect();
                this.A02 = rect2;
            }
            Paint paint = this.A01;
            if (paint == null) {
                paint = new Paint(2);
                this.A01 = paint;
            }
            canvas.drawBitmap(bitmap, (Rect) null, rect2, paint);
        }
    }

    public final Drawable.ConstantState getConstantState() {
        return this.A09;
    }

    public final int getIntrinsicHeight() {
        return this.A09.A00.A01;
    }

    public final int getIntrinsicWidth() {
        return this.A09.A00.A02;
    }

    public final boolean isRunning() {
        return this.A04;
    }

    public final void setAlpha(int i) {
        Paint paint = this.A01;
        if (paint == null) {
            paint = new Paint(2);
            this.A01 = paint;
        }
        paint.setAlpha(i);
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        Paint paint = this.A01;
        if (paint == null) {
            paint = new Paint(2);
            this.A01 = paint;
        }
        paint.setColorFilter(colorFilter);
    }

    public final boolean setVisible(boolean z, boolean z2) {
        AnonymousClass1S2.A01(!this.A03, "Cannot change the visibility of a recycled resource. Ensure that you unset the Drawable from your View before changing the View's visibility.");
        this.A08 = z;
        if (!z) {
            this.A04 = false;
            AnonymousClass1g5 r1 = this.A09.A00;
            List<AnonymousClass1hK> list = r1.A0G;
            list.remove(this);
            if (list.isEmpty()) {
                r1.A09 = false;
            }
        } else if (this.A07) {
            A00();
        }
        return super.setVisible(z, z2);
    }

    public AnonymousClass1gA(C09131gk r2) {
        AnonymousClass1S2.A00(r2);
        this.A09 = r2;
    }

    @Override // X.AnonymousClass1hK
    public final void A78() {
        int i;
        Drawable.Callback callback = getCallback();
        while (callback instanceof Drawable) {
            callback = ((Drawable) callback).getCallback();
        }
        if (callback == null) {
            stop();
            invalidateSelf();
            return;
        }
        invalidateSelf();
        AnonymousClass1g5 r2 = this.A09.A00;
        AnonymousClass1gV r0 = r2.A05;
        if (r0 != null) {
            i = r0.A01;
        } else {
            i = -1;
        }
        if (i == r2.A0E.A07.A02 - 1) {
            this.A00++;
        }
        int i2 = this.A05;
        if (i2 != -1 && this.A00 >= i2) {
            stop();
        }
    }

    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.A06 = true;
    }
}
