package defpackage;

import android.content.Context;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.animation.Animation;
import android.widget.ImageView;

/* renamed from: wu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5501wu extends ImageView {
    public Animation.AnimationListener F;
    public int G;
    public int H;

    public C5501wu(Context context, int i, float f) {
        super(context);
        float f2 = getContext().getResources().getDisplayMetrics().density;
        int i2 = (int) (3.5f * f2);
        this.G = i2;
        this.H = (int) ((float) i2);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
        setElevation(f2 * 4.0f);
        shapeDrawable.getPaint().setColor(i);
        setBackground(shapeDrawable);
    }

    public void onAnimationEnd() {
        super.onAnimationEnd();
        Animation.AnimationListener animationListener = this.F;
        if (animationListener != null) {
            animationListener.onAnimationEnd(getAnimation());
        }
    }

    public void onAnimationStart() {
        super.onAnimationStart();
        Animation.AnimationListener animationListener = this.F;
        if (animationListener != null) {
            animationListener.onAnimationStart(getAnimation());
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.H > this.G) {
            setMeasuredDimension((this.H * 2) + getMeasuredWidth(), (this.H * 2) + getMeasuredHeight());
        }
    }

    public void setBackgroundColor(int i) {
        if (getBackground() instanceof ShapeDrawable) {
            ((ShapeDrawable) getBackground()).getPaint().setColor(i);
        }
    }
}
