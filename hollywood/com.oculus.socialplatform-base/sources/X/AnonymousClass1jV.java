package X;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import androidx.annotation.VisibleForTesting;
import javax.annotation.Nullable;

/* renamed from: X.1jV  reason: invalid class name */
public final class AnonymousClass1jV extends AnonymousClass1lb {
    @VisibleForTesting
    public int A00 = 0;
    @VisibleForTesting
    public int A01 = 0;
    @Nullable
    @VisibleForTesting
    public Matrix A02;
    @VisibleForTesting
    public AnonymousClass2eu A03;
    public Matrix A04 = new Matrix();

    @Override // X.AnonymousClass1lb
    public final void draw(Canvas canvas) {
        if (!(this.A01 == getCurrent().getIntrinsicWidth() && this.A00 == getCurrent().getIntrinsicHeight())) {
            A00(this);
        }
        if (this.A02 != null) {
            int save = canvas.save();
            canvas.clipRect(getBounds());
            canvas.concat(this.A02);
            super.draw(canvas);
            canvas.restoreToCount(save);
            return;
        }
        super.draw(canvas);
    }

    public AnonymousClass1jV(Drawable drawable, AnonymousClass2eu r3) {
        super(drawable);
        this.A03 = r3;
    }

    @VisibleForTesting
    public static final void A00(AnonymousClass1jV r9) {
        Drawable current = r9.getCurrent();
        Rect bounds = r9.getBounds();
        int width = bounds.width();
        int height = bounds.height();
        int intrinsicWidth = current.getIntrinsicWidth();
        r9.A01 = intrinsicWidth;
        int intrinsicHeight = current.getIntrinsicHeight();
        r9.A00 = intrinsicHeight;
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0 || ((intrinsicWidth == width && intrinsicHeight == height) || r9.A03 == AnonymousClass2eu.A08)) {
            current.setBounds(bounds);
            r9.A02 = null;
            return;
        }
        current.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
        r9.A03.A54(r9.A04, bounds, intrinsicWidth, intrinsicHeight, 0.5f, 0.5f);
        r9.A02 = r9.A04;
    }

    @Override // X.AnonymousClass1lb
    public final Drawable A01(@Nullable Drawable drawable) {
        Drawable A012 = super.A01(drawable);
        A00(this);
        return A012;
    }

    @Override // X.AnonymousClass1lb
    public final void onBoundsChange(Rect rect) {
        A00(this);
    }
}
