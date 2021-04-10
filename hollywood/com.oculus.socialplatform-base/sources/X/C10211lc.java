package X;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.annotation.VisibleForTesting;
import com.oculus.localmedia.MediaProviderUtils;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.Arrays;
import javax.annotation.Nullable;

/* renamed from: X.1lc  reason: invalid class name and case insensitive filesystem */
public final class C10211lc extends Drawable implements AnonymousClass1m6 {
    @Nullable
    @VisibleForTesting
    public float[] A00;
    public int A01 = 0;
    public boolean A02 = false;
    public float A03 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public float A04 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public int A05 = MediaProviderUtils.JPEG_HEADER;
    public int A06 = 0;
    public boolean A07 = false;
    public boolean A08 = false;
    @VisibleForTesting
    public final Paint A09 = new Paint(1);
    @VisibleForTesting
    public final Path A0A = new Path();
    @VisibleForTesting
    public final Path A0B = new Path();
    @VisibleForTesting
    public final float[] A0C = new float[8];
    public final RectF A0D = new RectF();
    public final float[] A0E = new float[8];

    public final void setColorFilter(ColorFilter colorFilter) {
    }

    private void A00() {
        float[] fArr;
        float f;
        Path path = this.A0B;
        path.reset();
        Path path2 = this.A0A;
        path2.reset();
        RectF rectF = this.A0D;
        rectF.set(getBounds());
        float f2 = this.A03 / 2.0f;
        rectF.inset(f2, f2);
        if (this.A07) {
            path2.addCircle(rectF.centerX(), rectF.centerY(), Math.min(rectF.width(), rectF.height()) / 2.0f, Path.Direction.CW);
        } else {
            int i = 0;
            while (true) {
                fArr = this.A0C;
                if (i >= fArr.length) {
                    break;
                }
                fArr[i] = (this.A0E[i] + this.A04) - (this.A03 / 2.0f);
                i++;
            }
            path2.addRoundRect(rectF, fArr, Path.Direction.CW);
        }
        float f3 = (-this.A03) / 2.0f;
        rectF.inset(f3, f3);
        float f4 = this.A04;
        if (this.A08) {
            f = this.A03;
        } else {
            f = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
        }
        float f5 = f4 + f;
        rectF.inset(f5, f5);
        if (this.A07) {
            path.addCircle(rectF.centerX(), rectF.centerY(), Math.min(rectF.width(), rectF.height()) / 2.0f, Path.Direction.CW);
        } else if (this.A08) {
            float[] fArr2 = this.A00;
            if (fArr2 == null) {
                fArr2 = new float[8];
                this.A00 = fArr2;
            }
            for (int i2 = 0; i2 < fArr2.length; i2++) {
                fArr2[i2] = this.A0E[i2] - this.A03;
            }
            path.addRoundRect(rectF, fArr2, Path.Direction.CW);
        } else {
            path.addRoundRect(rectF, this.A0E, Path.Direction.CW);
        }
        float f6 = -f5;
        rectF.inset(f6, f6);
    }

    @Override // X.AnonymousClass1m6
    public final void A9g(int i, float f) {
        if (this.A06 != i) {
            this.A06 = i;
            invalidateSelf();
        }
        if (this.A03 != f) {
            this.A03 = f;
            A00();
            invalidateSelf();
        }
    }

    @Override // X.AnonymousClass1m6
    public final void A9k(boolean z) {
        this.A07 = z;
        A00();
        invalidateSelf();
    }

    @Override // X.AnonymousClass1m6
    public final void AA3(float f) {
        if (this.A04 != f) {
            this.A04 = f;
            A00();
            invalidateSelf();
        }
    }

    @Override // X.AnonymousClass1m6
    public final void AA4(boolean z) {
        if (this.A02 != z) {
            this.A02 = z;
            invalidateSelf();
        }
    }

    @Override // X.AnonymousClass1m6
    public final void AA7(float[] fArr) {
        if (fArr == null) {
            Arrays.fill(this.A0E, (float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        } else {
            boolean z = false;
            if (fArr.length == 8) {
                z = true;
            }
            C00740Ii.A04(z, "radii should have exactly 8 values");
            System.arraycopy(fArr, 0, this.A0E, 0, 8);
        }
        A00();
        invalidateSelf();
    }

    @Override // X.AnonymousClass1m6
    public final void AAA(boolean z) {
        if (this.A08 != z) {
            this.A08 = z;
            A00();
            invalidateSelf();
        }
    }

    public final void draw(Canvas canvas) {
        Paint paint = this.A09;
        paint.setColor(C10281lk.A00(this.A01, this.A05));
        paint.setStyle(Paint.Style.FILL);
        paint.setFilterBitmap(this.A02);
        canvas.drawPath(this.A0B, paint);
        if (this.A03 != AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
            paint.setColor(C10281lk.A00(this.A06, this.A05));
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(this.A03);
            canvas.drawPath(this.A0A, paint);
        }
    }

    public final int getAlpha() {
        return this.A05;
    }

    public final int getOpacity() {
        int A002 = C10281lk.A00(this.A01, this.A05) >>> 24;
        if (A002 == 255) {
            return -1;
        }
        if (A002 == 0) {
            return -2;
        }
        return -3;
    }

    public final void setAlpha(int i) {
        if (i != this.A05) {
            this.A05 = i;
            invalidateSelf();
        }
    }

    public C10211lc(int i) {
        if (this.A01 != i) {
            this.A01 = i;
            invalidateSelf();
        }
    }

    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        A00();
    }
}
