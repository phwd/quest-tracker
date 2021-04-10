package X;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import androidx.annotation.VisibleForTesting;
import com.oculus.vrshell.panels.AndroidPanelLayer;
import java.lang.ref.WeakReference;
import javax.annotation.Nullable;

/* renamed from: X.1la  reason: invalid class name and case insensitive filesystem */
public final class C10201la extends AnonymousClass1lZ {
    @Nullable
    public WeakReference<Bitmap> A00;
    @Nullable
    public final Bitmap A01;
    public final Paint A02 = new Paint();
    public final Paint A03 = new Paint(1);

    public C10201la(Resources resources, @Nullable Bitmap bitmap, @Nullable Paint paint) {
        super(new BitmapDrawable(resources, bitmap));
        this.A01 = bitmap;
        if (paint != null) {
            this.A02.set(paint);
        }
        this.A02.setFlags(1);
        this.A03.setStyle(Paint.Style.STROKE);
    }

    @Override // X.AnonymousClass1lZ
    @VisibleForTesting
    public final boolean A02() {
        if (!super.A02() || this.A01 == null) {
            return false;
        }
        return true;
    }

    @Override // X.AnonymousClass1lZ
    public final void draw(Canvas canvas) {
        C01060Pq.A00();
        if (!A02()) {
            super.draw(canvas);
        } else {
            A01();
            A00();
            WeakReference<Bitmap> weakReference = this.A00;
            if (weakReference == null || weakReference.get() != this.A01) {
                Bitmap bitmap = this.A01;
                this.A00 = new WeakReference<>(bitmap);
                Paint paint = this.A02;
                Shader.TileMode tileMode = Shader.TileMode.CLAMP;
                paint.setShader(new BitmapShader(bitmap, tileMode, tileMode));
                this.A04 = true;
            }
            if (this.A04) {
                this.A02.getShader().setLocalMatrix(this.A0O);
                this.A04 = false;
            }
            Paint paint2 = this.A02;
            paint2.setFilterBitmap(this.A05);
            int save = canvas.save();
            canvas.concat(this.A0F);
            canvas.drawPath(this.A0S, paint2);
            float f = this.A0A;
            if (f > AndroidPanelLayer.Spec.DEFAULT_CYLINDER_POSITION_Z) {
                Paint paint3 = this.A03;
                paint3.setStrokeWidth(f);
                paint3.setColor(C10281lk.A00(this.A0B, paint2.getAlpha()));
                canvas.drawPath(this.A0R, paint3);
            }
            canvas.restoreToCount(save);
        }
        C01060Pq.A00();
    }

    @Override // X.AnonymousClass1lZ
    public final void setAlpha(int i) {
        super.setAlpha(i);
        Paint paint = this.A02;
        if (i != paint.getAlpha()) {
            paint.setAlpha(i);
            super.setAlpha(i);
            invalidateSelf();
        }
    }

    @Override // X.AnonymousClass1lZ
    public final void setColorFilter(@Nullable ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
        this.A02.setColorFilter(colorFilter);
    }
}
