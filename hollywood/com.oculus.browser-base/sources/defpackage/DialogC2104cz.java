package defpackage;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import org.chromium.base.ContextUtils;

/* renamed from: cz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogC2104cz extends K4 {
    public final Activity F;
    public final View G;
    public final float H;
    public final float I;

    /* renamed from: J  reason: collision with root package name */
    public final float f9735J;
    public final boolean K;
    public float L;
    public float M;
    public int N;
    public O4 O;
    public View P;
    public int Q;
    public int R;

    public DialogC2104cz(Activity activity, int i, float f, float f2, float f3, int i2, int i3, View view, View view2, boolean z) {
        super(activity, i);
        this.F = activity;
        this.H = f;
        this.I = f2;
        this.f9735J = f3;
        this.Q = i2;
        this.R = i3;
        this.G = view2;
        this.P = view;
        this.K = z;
    }

    public Animation b(boolean z, float f, float f2) {
        float f3 = z ? 0.0f : 1.0f;
        float f4 = z ? 1.0f : 0.0f;
        ScaleAnimation scaleAnimation = new ScaleAnimation(f3, f4, f3, f4, 0, f, 0, f2);
        scaleAnimation.setDuration((long) (((float) (z ? 250 : 150)) * Settings.Global.getFloat(ContextUtils.getApplicationContext().getContentResolver(), "animator_duration_scale", 1.0f)));
        scaleAnimation.setInterpolator(G30.e);
        return scaleAnimation;
    }

    @Override // defpackage.K4
    public void dismiss() {
        if (this.K) {
            this.O.K.dismiss();
            super.dismiss();
            return;
        }
        int[] iArr = new int[2];
        this.G.getLocationOnScreen(iArr);
        Animation b = b(false, this.L, this.M + ((float) (this.N - iArr[1])));
        b.setAnimationListener(new animation.Animation$AnimationListenerC1933bz(this));
        this.G.startAnimation(b);
    }

    public void onStart() {
        super.onStart();
        Window window = getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setLayout(-1, -1);
        if (!(this.Q == -1 || this.R == -1)) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.G.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.bottomMargin = this.R;
                layoutParams.topMargin = this.Q;
            } else {
                return;
            }
        }
        (this.K ? this.P : this.G).addOnLayoutChangeListener(new View$OnLayoutChangeListenerC1762az(this));
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0) {
            return false;
        }
        dismiss();
        return true;
    }
}
