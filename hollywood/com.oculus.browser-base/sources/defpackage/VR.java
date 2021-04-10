package defpackage;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: VR  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VR implements View.OnAttachStateChangeListener, View.OnLayoutChangeListener {
    public final RectF F = new RectF();
    public final RectF G = new RectF();
    public final Matrix H = new Matrix();
    public final View I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f9083J;
    public Drawable K;
    public ImageView.ScaleType L = ImageView.ScaleType.FIT_CENTER;

    public VR(View view) {
        this.I = view;
        view.addOnAttachStateChangeListener(this);
        view.addOnLayoutChangeListener(this);
    }

    public void a(Canvas canvas) {
        Drawable drawable = this.K;
        if (drawable != null) {
            if (drawable != null && this.f9083J) {
                this.H.reset();
                int intrinsicWidth = this.K.getIntrinsicWidth();
                int intrinsicHeight = this.K.getIntrinsicHeight();
                int width = this.I.getWidth();
                int height = this.I.getHeight();
                this.F.set(0.0f, 0.0f, (float) intrinsicWidth, (float) intrinsicHeight);
                this.G.set(0.0f, 0.0f, (float) width, (float) height);
                ImageView.ScaleType scaleType = this.L;
                if (scaleType == ImageView.ScaleType.FIT_START) {
                    this.H.setRectToRect(this.F, this.G, Matrix.ScaleToFit.START);
                    this.K.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
                } else if (scaleType == ImageView.ScaleType.FIT_CENTER) {
                    this.H.setRectToRect(this.F, this.G, Matrix.ScaleToFit.CENTER);
                    this.K.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
                } else if (scaleType == ImageView.ScaleType.FIT_END) {
                    this.H.setRectToRect(this.F, this.G, Matrix.ScaleToFit.END);
                    this.K.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
                } else if (scaleType == ImageView.ScaleType.CENTER) {
                    this.H.setTranslate((float) Math.round(((float) (width - intrinsicWidth)) * 0.5f), (float) Math.round(((float) (height - intrinsicHeight)) * 0.5f));
                    this.K.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
                } else {
                    this.K.setBounds(0, 0, width, height);
                }
                this.f9083J = false;
            }
            if (this.H.isIdentity()) {
                this.K.draw(canvas);
                return;
            }
            int saveCount = canvas.getSaveCount();
            canvas.save();
            canvas.concat(this.H);
            this.K.draw(canvas);
            canvas.restoreToCount(saveCount);
        }
    }

    public void b() {
        Drawable drawable = this.K;
        if (drawable != null && drawable.setState(this.I.getDrawableState())) {
            this.I.invalidate();
        }
    }

    public void c(View view, int i) {
        View view2 = this.I;
        if (view2 == view && this.K != null) {
            ViewParent parent = view2.getParent();
            boolean z = true;
            if ((parent != null && (!(parent instanceof ViewGroup) || ((ViewGroup) parent).isShown())) && this.I.getWindowVisibility() == 0) {
                Drawable drawable = this.K;
                if (i != 0) {
                    z = false;
                }
                drawable.setVisible(z, false);
            }
        }
    }

    public void d(Drawable drawable) {
        Drawable drawable2 = this.K;
        if (drawable2 != drawable) {
            if (drawable2 != null) {
                View view = this.I;
                AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
                if (view.isAttachedToWindow()) {
                    this.K.setVisible(false, false);
                }
                this.K.setCallback(null);
                this.I.unscheduleDrawable(this.K);
                this.K = null;
            }
            this.K = drawable;
            if (drawable != null) {
                boolean z = true;
                this.f9083J = true;
                View view2 = this.I;
                AtomicInteger atomicInteger2 = AbstractC1920bu1.f9571a;
                drawable.setLayoutDirection(view2.getLayoutDirection());
                if (this.K.isStateful()) {
                    this.K.setState(this.I.getDrawableState());
                }
                if (this.I.isAttachedToWindow()) {
                    Drawable drawable3 = this.K;
                    if (this.I.getWindowVisibility() != 0 || !this.I.isShown()) {
                        z = false;
                    }
                    drawable3.setVisible(z, false);
                }
                this.K.setCallback(this.I);
            }
            this.I.requestLayout();
            this.I.invalidate();
        }
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Drawable drawable = this.K;
        if (drawable != null) {
            int i9 = i4 - i2;
            if (i3 - i != drawable.getBounds().width() || i9 != this.K.getBounds().height()) {
                this.f9083J = true;
            }
        }
    }

    public void onViewAttachedToWindow(View view) {
        if (this.K != null && this.I.isShown() && this.I.getWindowVisibility() != 8) {
            this.K.setVisible(this.I.getVisibility() == 0, false);
        }
    }

    public void onViewDetachedFromWindow(View view) {
        if (this.K != null && this.I.isShown() && this.I.getWindowVisibility() != 8) {
            this.K.setVisible(false, false);
        }
    }
}
