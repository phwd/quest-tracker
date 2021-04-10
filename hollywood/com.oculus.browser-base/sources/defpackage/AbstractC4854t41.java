package defpackage;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content_public.browser.WebContents;

/* renamed from: t41  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4854t41 extends FrameLayout {
    public final AbstractC5771yV F = new C4343q41(this);
    public final View.OnLayoutChangeListener G = new View$OnLayoutChangeListenerC4513r41(this);
    public final Interpolator H = new DecelerateInterpolator(1.0f);
    public int I = 0;

    /* renamed from: J  reason: collision with root package name */
    public Animator f11321J;
    public int K;
    public int L;
    public int M;
    public boolean N;
    public WebContents O;

    public AbstractC4854t41(Context context, AttributeSet attributeSet) {
        super(context, null);
        setWillNotDraw(false);
    }

    public static boolean a(AbstractC4854t41 t41) {
        if (t41.N) {
            return false;
        }
        Animator animator = t41.f11321J;
        if (animator != null) {
            animator.cancel();
        }
        return true;
    }

    public boolean b() {
        if (getParent() == null) {
            return false;
        }
        ((ViewGroup) getParent()).removeView(this);
        removeOnLayoutChangeListener(this.G);
        return true;
    }

    public abstract void c(boolean z);

    public void d(WebContents webContents) {
        WebContents webContents2 = this.O;
        if (webContents2 != null) {
            GestureListenerManagerImpl.s0(webContents2).u0(this.F);
        }
        this.O = webContents;
        if (webContents != null && this.M > 0) {
            GestureListenerManagerImpl.s0(webContents).r0(this.F);
        }
    }

    public boolean gatherTransparentRegion(Region region) {
        float translationY = getTranslationY();
        setTranslationY(0.0f);
        boolean gatherTransparentRegion = super.gatherTransparentRegion(region);
        setTranslationY(translationY);
        return gatherTransparentRegion;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!B10.P) {
            setTranslationY(0.0f);
        }
    }

    public void onDetachedFromWindow() {
        Animator animator;
        super.onDetachedFromWindow();
        if (!this.N && (animator = this.f11321J) != null) {
            animator.cancel();
        }
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int height = getParent() == null ? 0 : ((View) getParent()).getHeight();
        if (this.K != height) {
            this.K = height;
            this.I = 0;
            Animator animator = this.f11321J;
            if (animator != null) {
                animator.end();
            }
        }
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) getLayoutParams();
        int measuredHeight = getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
        this.M = measuredHeight;
        WebContents webContents = this.O;
        if (webContents != null) {
            if (measuredHeight > 0) {
                GestureListenerManagerImpl.s0(webContents).r0(this.F);
            } else {
                GestureListenerManagerImpl.s0(webContents).u0(this.F);
            }
        }
        super.onLayout(z, i, i2, i3, i4);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!B10.P) {
            setTranslationY(0.0f);
        }
    }
}
