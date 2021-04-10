package X;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.annotation.VisibleForTesting;

/* renamed from: X.1jU  reason: invalid class name and case insensitive filesystem */
public final class C09681jU extends AnonymousClass1lb {
    public int A00;
    public int A01;
    @VisibleForTesting
    public final Matrix A02 = new Matrix();
    public final Matrix A03 = new Matrix();
    public final RectF A04 = new RectF();

    @Override // X.AnonymousClass1lb
    public final void draw(Canvas canvas) {
        int i;
        if (this.A01 > 0 || !((i = this.A00) == 0 || i == 1)) {
            int save = canvas.save();
            canvas.concat(this.A02);
            super.draw(canvas);
            canvas.restoreToCount(save);
            return;
        }
        super.draw(canvas);
    }

    @Override // X.AnonymousClass1lb
    public final int getIntrinsicHeight() {
        int i = this.A00;
        if (i == 5 || i == 7 || this.A01 % 180 != 0) {
            return super.getIntrinsicWidth();
        }
        return super.getIntrinsicHeight();
    }

    @Override // X.AnonymousClass1lb
    public final int getIntrinsicWidth() {
        int i = this.A00;
        if (i == 5 || i == 7 || this.A01 % 180 != 0) {
            return super.getIntrinsicHeight();
        }
        return super.getIntrinsicWidth();
    }

    public C09681jU(Drawable drawable, int i, int i2) {
        super(drawable);
        this.A01 = i - (i % 90);
        this.A00 = (i2 < 0 || i2 > 8) ? 0 : i2;
    }

    @Override // X.AnonymousClass1lb
    public final void onBoundsChange(Rect rect) {
        Matrix matrix;
        int i;
        Drawable current = getCurrent();
        int i2 = this.A01;
        if (i2 > 0 || !((i = this.A00) == 0 || i == 1)) {
            int i3 = this.A00;
            if (i3 == 2) {
                matrix = this.A02;
                matrix.setScale(-1.0f, 1.0f);
            } else if (i3 == 7) {
                matrix = this.A02;
                matrix.setRotate(270.0f, (float) rect.centerX(), (float) rect.centerY());
                matrix.postScale(-1.0f, 1.0f);
            } else if (i3 == 4) {
                matrix = this.A02;
                matrix.setScale(1.0f, -1.0f);
            } else if (i3 != 5) {
                matrix = this.A02;
                matrix.setRotate((float) i2, (float) rect.centerX(), (float) rect.centerY());
            } else {
                matrix = this.A02;
                matrix.setRotate(270.0f, (float) rect.centerX(), (float) rect.centerY());
                matrix.postScale(1.0f, -1.0f);
            }
            Matrix matrix2 = this.A03;
            matrix2.reset();
            matrix.invert(matrix2);
            RectF rectF = this.A04;
            rectF.set(rect);
            matrix2.mapRect(rectF);
            current.setBounds((int) rectF.left, (int) rectF.top, (int) rectF.right, (int) rectF.bottom);
            return;
        }
        current.setBounds(rect);
    }
}
