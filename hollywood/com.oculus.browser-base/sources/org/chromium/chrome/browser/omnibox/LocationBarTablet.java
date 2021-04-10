package org.chromium.chrome.browser.omnibox;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import com.oculus.browser.R;
import org.chromium.chrome.browser.download.DownloadUtils;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.LocalizationUtils;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class LocationBarTablet extends AbstractView$OnClickListenerC5272va0 {
    public static final /* synthetic */ int g0 = 0;
    public final Property h0 = new C0123Ca0(this, Float.class, "");
    public final Property i0 = new C0184Da0(this, Float.class, "");
    public View j0;
    public View k0;
    public View l0;
    public Animator m0;
    public View[] n0;
    public final Rect o0 = new Rect();
    public boolean p0 = true;
    public final int q0 = (getResources().getDimensionPixelOffset(R.dimen.f26310_resource_name_obfuscated_RES_2131166250) * 3);
    public final int r0 = getResources().getDimensionPixelOffset(R.dimen.f20430_resource_name_obfuscated_RES_2131165662);
    public boolean s0;
    public float t0;
    public float u0;
    public float v0;
    public int w0;

    public LocationBarTablet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.layout.f39150_resource_name_obfuscated_RES_2131624224);
        C3974nw nwVar = new C3974nw(this);
        this.e0 = nwVar;
        setTouchDelegate(nwVar);
    }

    public static void y(LocationBarTablet locationBarTablet) {
        locationBarTablet.G.setTranslationX(0.0f);
        locationBarTablet.F.setTranslationX(0.0f);
        locationBarTablet.k0.setTranslationX(0.0f);
        locationBarTablet.l0.setTranslationX(0.0f);
        locationBarTablet.j0.setTranslationX(0.0f);
        locationBarTablet.I.setTranslationX(0.0f);
        locationBarTablet.G.setAlpha(1.0f);
        locationBarTablet.F.setAlpha(1.0f);
        locationBarTablet.k0.setAlpha(1.0f);
        locationBarTablet.l0.setAlpha(1.0f);
    }

    public ObjectAnimator A(View view) {
        if (view.getVisibility() != 0) {
            view.setAlpha(0.0f);
        }
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, 1.0f);
        ofFloat.setInterpolator(animation.InterpolatorC5286vf.g);
        ofFloat.setStartDelay(75);
        ofFloat.setDuration(150L);
        return ofFloat;
    }

    public final void B(float f) {
        this.t0 = f;
        float f2 = ((float) (this.q0 + this.w0)) * f;
        if (LocalizationUtils.isLayoutRtl()) {
            setRight((int) (this.v0 + f2));
        } else {
            setLeft((int) (this.u0 - f2));
        }
        int i = (int) (((float) this.r0) * f);
        int i2 = (int) f2;
        if (getLayoutDirection() != 1) {
            if (this.l0.getVisibility() == 0) {
                this.l0.setTranslationX((float) i2);
            } else {
                this.G.setTranslationX((float) i2);
            }
            if (this.F.getVisibility() == 0) {
                this.F.setTranslationX((float) (i2 + i));
            } else {
                this.k0.setTranslationX((float) i2);
            }
        } else {
            float f3 = (float) i2;
            this.j0.setTranslationX(f3);
            this.I.setTranslationX(f3);
            if (this.F.getVisibility() == 0) {
                this.F.setTranslationX((float) (-i));
            }
        }
    }

    public final boolean C() {
        if (!this.S) {
            return true;
        }
        if (this.I.hasFocus() || this.R) {
            return false;
        }
        return true;
    }

    public final boolean D() {
        AbstractC4422qa0 qa0;
        Tab d;
        if (!this.S || (qa0 = this.L) == null || (d = qa0.d()) == null || !C() || d.a()) {
            return false;
        }
        return true;
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void e(boolean z) {
        super.e(z);
        Animator animator = this.m0;
        if (animator != null && animator.isRunning()) {
            this.m0.cancel();
            this.m0 = null;
        }
        if (this.L.e().g()) {
            b(z, z);
            return;
        }
        Rect rect = new Rect();
        getRootView().getLocalVisibleRect(rect);
        float height = ((float) rect.height()) / ((float) Math.max(rect.height(), rect.width()));
        Property property = this.h0;
        float[] fArr = new float[1];
        fArr[0] = z ? 1.0f : 0.0f;
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this, property, fArr);
        this.m0 = ofFloat;
        ofFloat.setDuration((long) (height * 200.0f));
        this.m0.addListener(new C0245Ea0(this, z));
        q(true);
        this.m0.start();
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void f(C0859Oc oc, Oq1 oq1, R11 r11, AbstractC4422qa0 qa0, Uy1 uy1, WindowAndroid windowAndroid, Sv1 sv1, AbstractC1509Ys0 ys0) {
        super.f(oc, oq1, r11, qa0, uy1, windowAndroid, sv1, ys0);
        C5698y21 y21 = this.O.G;
        y21.M = true;
        y21.e();
        if (AbstractC5762yQ0.g(this.L.a())) {
            this.O.q(true);
        }
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void onFinishInflate() {
        super.onFinishInflate();
        this.j0 = findViewById(R.id.location_bar_status_icon);
        this.k0 = findViewById(R.id.bookmark_button);
        this.l0 = findViewById(R.id.save_offline_button);
        this.n0 = new View[]{this.I, this.F};
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.u0 = (float) i;
        this.v0 = (float) i3;
        if (this.s0) {
            B(this.t0);
        }
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void onMeasure(int i, int i2) {
        int measuredWidth = getMeasuredWidth();
        super.onMeasure(i, i2);
        if (getMeasuredWidth() != measuredWidth) {
            l(getMeasuredWidth());
            super.onMeasure(i, i2);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        View[] viewArr = this.n0;
        if (viewArr == null) {
            return true;
        }
        View view = null;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        for (View view2 : viewArr) {
            if (view2.isShown()) {
                this.o0.set(0, 0, view2.getWidth(), view2.getHeight());
                offsetDescendantRectToMyCoords(view2, this.o0);
                float x = motionEvent.getX();
                float y = motionEvent.getY();
                Rect rect = this.o0;
                float f4 = (float) rect.left;
                float f5 = (float) rect.right;
                float f6 = x < f4 ? f4 - x : x > f5 ? f5 - x : 0.0f;
                float f7 = (float) rect.top;
                float f8 = (float) rect.bottom;
                float f9 = y < f7 ? f7 - y : y > f8 ? f8 - y : 0.0f;
                float abs = Math.abs(f9) + Math.abs(f6);
                if (view == null || abs < f3) {
                    f = x + f6;
                    f2 = y + f9;
                    view = view2;
                    f3 = abs;
                }
            }
        }
        if (view == null) {
            return false;
        }
        motionEvent.setLocation(f, f2);
        return view.onTouchEvent(motionEvent);
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void p(float f) {
        this.b0 = f;
        this.L.e().d(f);
    }

    @Override // defpackage.AbstractView$OnClickListenerC5272va0
    public void t() {
        boolean z;
        super.t();
        boolean z2 = true;
        int i = 0;
        this.k0.setVisibility(this.p0 && C() ? 0 : 8);
        boolean z3 = this.p0 && D();
        this.l0.setVisibility(z3 ? 0 : 8);
        if (z3) {
            View view = this.l0;
            AbstractC4422qa0 qa0 = this.L;
            if (qa0 == null) {
                z = false;
            } else {
                z = DownloadUtils.c(qa0.d());
            }
            view.setEnabled(z);
        }
        if (!this.p0) {
            v();
            return;
        }
        ImageButton imageButton = this.G;
        if (!this.a0 || !this.S || (!this.I.hasFocus() && !this.R)) {
            z2 = false;
        }
        if (!z2) {
            i = 8;
        }
        imageButton.setVisibility(i);
    }

    public ObjectAnimator z(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, View.ALPHA, 0.0f);
        ofFloat.setInterpolator(animation.InterpolatorC5286vf.f);
        ofFloat.setDuration(150L);
        return ofFloat;
    }
}
