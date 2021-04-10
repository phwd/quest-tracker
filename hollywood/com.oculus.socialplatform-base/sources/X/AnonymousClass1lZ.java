package X;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.Arrays;

/* renamed from: X.1lZ  reason: invalid class name */
public abstract class AnonymousClass1lZ extends Drawable implements AnonymousClass1m6, AnonymousClass1mn {
    @Nullable
    @VisibleForTesting
    public Matrix A00;
    @Nullable
    @VisibleForTesting
    public Matrix A01;
    @Nullable
    @VisibleForTesting
    public RectF A02;
    @Nullable
    @VisibleForTesting
    public float[] A03;
    public boolean A04 = true;
    public boolean A05 = false;
    public float A06 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    @Nullable
    public AbstractC00940Mi A07;
    public boolean A08 = true;
    public boolean A09 = false;
    public float A0A = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public int A0B = 0;
    public boolean A0C = false;
    public boolean A0D = false;
    @VisibleForTesting
    public final Matrix A0E = new Matrix();
    @VisibleForTesting
    public final Matrix A0F = new Matrix();
    @VisibleForTesting
    public final Matrix A0G = new Matrix();
    @VisibleForTesting
    public final Matrix A0H = new Matrix();
    @VisibleForTesting
    public final Matrix A0I = new Matrix();
    @VisibleForTesting
    public final RectF A0J = new RectF();
    @VisibleForTesting
    public final RectF A0K = new RectF();
    @VisibleForTesting
    public final RectF A0L = new RectF();
    @VisibleForTesting
    public final RectF A0M = new RectF();
    @VisibleForTesting
    public final float[] A0N = new float[8];
    @VisibleForTesting
    public final Matrix A0O = new Matrix();
    public final Drawable A0P;
    public final float[] A0Q = new float[8];
    public final Path A0R = new Path();
    public final Path A0S = new Path();

    @Override // X.AnonymousClass1m6
    public final void AA7(float[] fArr) {
        int i = 0;
        if (fArr == null) {
            Arrays.fill(this.A0Q, (float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
            this.A0D = false;
        } else {
            boolean z = false;
            if (fArr.length == 8) {
                z = true;
            }
            C00740Ii.A04(z, "radii should have exactly 8 values");
            System.arraycopy(fArr, 0, this.A0Q, 0, 8);
            this.A0D = false;
            boolean z2 = false;
            do {
                boolean z3 = false;
                if (fArr[i] > AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                    z3 = true;
                }
                z2 |= z3;
                this.A0D = z2;
                i++;
            } while (i < 8);
        }
        this.A08 = true;
        invalidateSelf();
    }

    public final void A00() {
        float[] fArr;
        float f;
        if (this.A08) {
            Path path = this.A0R;
            path.reset();
            RectF rectF = this.A0M;
            float f2 = this.A0A / 2.0f;
            rectF.inset(f2, f2);
            if (this.A0C) {
                path.addCircle(rectF.centerX(), rectF.centerY(), Math.min(rectF.width(), rectF.height()) / 2.0f, Path.Direction.CW);
            } else {
                int i = 0;
                while (true) {
                    fArr = this.A0N;
                    if (i >= fArr.length) {
                        break;
                    }
                    fArr[i] = (this.A0Q[i] + this.A06) - (this.A0A / 2.0f);
                    i++;
                }
                path.addRoundRect(rectF, fArr, Path.Direction.CW);
            }
            float f3 = (-this.A0A) / 2.0f;
            rectF.inset(f3, f3);
            Path path2 = this.A0S;
            path2.reset();
            float f4 = this.A06;
            if (this.A09) {
                f = this.A0A;
            } else {
                f = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
            }
            float f5 = f4 + f;
            rectF.inset(f5, f5);
            if (this.A0C) {
                path2.addCircle(rectF.centerX(), rectF.centerY(), Math.min(rectF.width(), rectF.height()) / 2.0f, Path.Direction.CW);
            } else if (this.A09) {
                float[] fArr2 = this.A03;
                if (fArr2 == null) {
                    fArr2 = new float[8];
                    this.A03 = fArr2;
                }
                for (int i2 = 0; i2 < this.A0N.length; i2++) {
                    fArr2[i2] = this.A0Q[i2] - this.A0A;
                }
                path2.addRoundRect(rectF, fArr2, Path.Direction.CW);
            } else {
                path2.addRoundRect(rectF, this.A0Q, Path.Direction.CW);
            }
            float f6 = -f5;
            rectF.inset(f6, f6);
            path2.setFillType(Path.FillType.WINDING);
            this.A08 = false;
        }
    }

    public final void A01() {
        Matrix matrix;
        RectF rectF;
        Matrix matrix2;
        AbstractC00940Mi r0 = this.A07;
        if (r0 != null) {
            Matrix matrix3 = this.A0G;
            r0.A55(matrix3);
            AbstractC00940Mi r02 = this.A07;
            RectF rectF2 = this.A0M;
            r02.A4r(rectF2);
            rectF = rectF2;
            matrix = matrix3;
        } else {
            Matrix matrix4 = this.A0G;
            matrix4.reset();
            RectF rectF3 = this.A0M;
            rectF3.set(getBounds());
            rectF = rectF3;
            matrix = matrix4;
        }
        RectF rectF4 = this.A0J;
        rectF4.set(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z, (float) getIntrinsicWidth(), (float) getIntrinsicHeight());
        RectF rectF5 = this.A0K;
        rectF5.set(this.A0P.getBounds());
        Matrix matrix5 = this.A0E;
        Matrix.ScaleToFit scaleToFit = Matrix.ScaleToFit.FILL;
        matrix5.setRectToRect(rectF4, rectF5, scaleToFit);
        if (this.A09) {
            RectF rectF6 = this.A02;
            if (rectF6 == null) {
                this.A02 = new RectF(rectF);
            } else {
                rectF6.set(rectF);
            }
            RectF rectF7 = this.A02;
            float f = this.A0A;
            rectF7.inset(f, f);
            Matrix matrix6 = this.A00;
            Matrix matrix7 = matrix6;
            if (matrix6 == null) {
                Matrix matrix8 = new Matrix();
                this.A00 = matrix8;
                matrix7 = matrix8;
            }
            matrix7.setRectToRect(rectF, this.A02, scaleToFit);
        } else {
            Matrix matrix9 = this.A00;
            if (matrix9 != null) {
                matrix9.reset();
            }
        }
        Matrix matrix10 = this.A0I;
        if (!matrix.equals(matrix10) || !matrix5.equals(this.A0H) || ((matrix2 = this.A00) != null && !matrix2.equals(this.A01))) {
            this.A04 = true;
            matrix.invert(this.A0F);
            Matrix matrix11 = this.A0O;
            matrix11.set(matrix);
            if (this.A09) {
                matrix11.postConcat(this.A00);
            }
            matrix11.preConcat(matrix5);
            matrix10.set(matrix);
            this.A0H.set(matrix5);
            if (this.A09) {
                Matrix matrix12 = this.A01;
                Matrix matrix13 = this.A00;
                if (matrix12 == null) {
                    this.A01 = new Matrix(matrix13);
                } else {
                    matrix12.set(matrix13);
                }
            } else {
                Matrix matrix14 = this.A01;
                if (matrix14 != null) {
                    matrix14.reset();
                }
            }
        }
        RectF rectF8 = this.A0L;
        if (!rectF.equals(rectF8)) {
            this.A08 = true;
            rectF8.set(rectF);
        }
    }

    @VisibleForTesting
    public boolean A02() {
        if (this.A0C || this.A0D || this.A0A > AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass1m6
    public final void A9g(int i, float f) {
        if (this.A0B != i || this.A0A != f) {
            this.A0B = i;
            this.A0A = f;
            this.A08 = true;
            invalidateSelf();
        }
    }

    @Override // X.AnonymousClass1m6
    public final void A9k(boolean z) {
        this.A0C = z;
        this.A08 = true;
        invalidateSelf();
    }

    @Override // X.AnonymousClass1m6
    public final void AA3(float f) {
        if (this.A06 != f) {
            this.A06 = f;
            this.A08 = true;
            invalidateSelf();
        }
    }

    @Override // X.AnonymousClass1m6
    public final void AA4(boolean z) {
        if (this.A05 != z) {
            this.A05 = z;
            invalidateSelf();
        }
    }

    @Override // X.AnonymousClass1m6
    public final void AAA(boolean z) {
        if (this.A09 != z) {
            this.A09 = z;
            this.A08 = true;
            invalidateSelf();
        }
    }

    public final void clearColorFilter() {
        this.A0P.clearColorFilter();
    }

    @RequiresApi(api = 19)
    public final int getAlpha() {
        return this.A0P.getAlpha();
    }

    @Nullable
    @RequiresApi(api = 21)
    public final ColorFilter getColorFilter() {
        return this.A0P.getColorFilter();
    }

    public final int getIntrinsicHeight() {
        return this.A0P.getIntrinsicHeight();
    }

    public final int getIntrinsicWidth() {
        return this.A0P.getIntrinsicWidth();
    }

    public final int getOpacity() {
        return this.A0P.getOpacity();
    }

    public final void onBoundsChange(Rect rect) {
        this.A0P.setBounds(rect);
    }

    public void setAlpha(int i) {
        this.A0P.setAlpha(i);
    }

    public AnonymousClass1lZ(Drawable drawable) {
        this.A0P = drawable;
    }

    public void draw(@NonNull Canvas canvas) {
        C01060Pq.A00();
        this.A0P.draw(canvas);
        C01060Pq.A00();
    }

    @Override // X.AnonymousClass1mn
    public final void AAF(@Nullable AbstractC00940Mi r1) {
        this.A07 = r1;
    }

    public final void setColorFilter(int i, @NonNull PorterDuff.Mode mode) {
        this.A0P.setColorFilter(i, mode);
    }

    public void setColorFilter(@Nullable ColorFilter colorFilter) {
        this.A0P.setColorFilter(colorFilter);
    }
}
