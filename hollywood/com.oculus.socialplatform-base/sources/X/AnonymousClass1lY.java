package X;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import androidx.annotation.VisibleForTesting;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.util.Arrays;
import javax.annotation.Nullable;

/* renamed from: X.1lY  reason: invalid class name */
public final class AnonymousClass1lY extends AnonymousClass1lb implements AnonymousClass1m6 {
    @VisibleForTesting
    public Integer A00 = AnonymousClass007.A00;
    public int A01 = 0;
    public boolean A02 = false;
    public float A03 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public float A04 = AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z;
    public int A05 = 0;
    @Nullable
    public Matrix A06;
    @Nullable
    public RectF A07;
    public boolean A08 = false;
    public boolean A09 = false;
    @VisibleForTesting
    public final Paint A0A = new Paint(1);
    @VisibleForTesting
    public final float[] A0B = new float[8];
    public final Path A0C = new Path();
    public final Path A0D = new Path();
    public final RectF A0E = new RectF();
    public final RectF A0F = new RectF();
    public final float[] A0G = new float[8];

    private void A00() {
        float[] fArr;
        Path path = this.A0D;
        path.reset();
        Path path2 = this.A0C;
        path2.reset();
        RectF rectF = this.A0F;
        rectF.set(getBounds());
        float f = this.A04;
        rectF.inset(f, f);
        if (this.A00 == AnonymousClass007.A00) {
            path.addRect(rectF, Path.Direction.CW);
        }
        if (this.A08) {
            path.addCircle(rectF.centerX(), rectF.centerY(), Math.min(rectF.width(), rectF.height()) / 2.0f, Path.Direction.CW);
        } else {
            path.addRoundRect(rectF, this.A0G, Path.Direction.CW);
        }
        float f2 = -this.A04;
        rectF.inset(f2, f2);
        float f3 = this.A03 / 2.0f;
        rectF.inset(f3, f3);
        if (this.A08) {
            path2.addCircle(rectF.centerX(), rectF.centerY(), Math.min(rectF.width(), rectF.height()) / 2.0f, Path.Direction.CW);
        } else {
            int i = 0;
            while (true) {
                fArr = this.A0B;
                if (i >= fArr.length) {
                    break;
                }
                fArr[i] = (this.A0G[i] + this.A04) - (this.A03 / 2.0f);
                i++;
            }
            path2.addRoundRect(rectF, fArr, Path.Direction.CW);
        }
        float f4 = (-this.A03) / 2.0f;
        rectF.inset(f4, f4);
    }

    @Override // X.AnonymousClass1m6
    public final void A9g(int i, float f) {
        this.A05 = i;
        this.A03 = f;
        A00();
        invalidateSelf();
    }

    @Override // X.AnonymousClass1m6
    public final void A9k(boolean z) {
        this.A08 = z;
        A00();
        invalidateSelf();
    }

    @Override // X.AnonymousClass1m6
    public final void AA3(float f) {
        this.A04 = f;
        A00();
        invalidateSelf();
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
            Arrays.fill(this.A0G, (float) AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
        } else {
            boolean z = false;
            if (fArr.length == 8) {
                z = true;
            }
            C00740Ii.A04(z, "radii should have exactly 8 values");
            System.arraycopy(fArr, 0, this.A0G, 0, 8);
        }
        A00();
        invalidateSelf();
    }

    @Override // X.AnonymousClass1m6
    public final void AAA(boolean z) {
        this.A09 = z;
        A00();
        invalidateSelf();
    }

    @Override // X.AnonymousClass1lb
    public final void draw(Canvas canvas) {
        RectF rectF = this.A0E;
        rectF.set(getBounds());
        switch (this.A00.intValue()) {
            case 0:
                if (this.A09) {
                    RectF rectF2 = this.A07;
                    if (rectF2 == null) {
                        this.A07 = new RectF(rectF);
                        this.A06 = new Matrix();
                    } else {
                        rectF2.set(rectF);
                    }
                    RectF rectF3 = this.A07;
                    float f = this.A03;
                    rectF3.inset(f, f);
                    this.A06.setRectToRect(rectF, this.A07, Matrix.ScaleToFit.FILL);
                    int save = canvas.save();
                    canvas.clipRect(rectF);
                    canvas.concat(this.A06);
                    super.draw(canvas);
                    canvas.restoreToCount(save);
                } else {
                    super.draw(canvas);
                }
                Paint paint = this.A0A;
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(this.A01);
                paint.setStrokeWidth(AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z);
                paint.setFilterBitmap(this.A02);
                Path path = this.A0D;
                path.setFillType(Path.FillType.EVEN_ODD);
                canvas.drawPath(path, paint);
                if (this.A08) {
                    float width = ((rectF.width() - rectF.height()) + this.A03) / 2.0f;
                    float height = ((rectF.height() - rectF.width()) + this.A03) / 2.0f;
                    if (width > AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                        float f2 = rectF.left;
                        canvas.drawRect(f2, rectF.top, f2 + width, rectF.bottom, paint);
                        float f3 = rectF.right;
                        canvas.drawRect(f3 - width, rectF.top, f3, rectF.bottom, paint);
                    }
                    if (height > AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                        float f4 = rectF.left;
                        float f5 = rectF.top;
                        canvas.drawRect(f4, f5, rectF.right, f5 + height, paint);
                        float f6 = rectF.left;
                        float f7 = rectF.bottom;
                        canvas.drawRect(f6, f7 - height, rectF.right, f7, paint);
                        break;
                    }
                }
                break;
            case 1:
                int save2 = canvas.save();
                canvas.clipPath(this.A0D);
                super.draw(canvas);
                canvas.restoreToCount(save2);
                break;
        }
        if (this.A05 != 0) {
            Paint paint2 = this.A0A;
            paint2.setStyle(Paint.Style.STROKE);
            paint2.setColor(this.A05);
            paint2.setStrokeWidth(this.A03);
            this.A0D.setFillType(Path.FillType.EVEN_ODD);
            canvas.drawPath(this.A0C, paint2);
        }
    }

    public AnonymousClass1lY(Drawable drawable) {
        super(drawable);
    }

    @Override // X.AnonymousClass1lb
    public final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        A00();
    }
}
