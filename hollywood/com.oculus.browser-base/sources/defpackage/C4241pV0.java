package defpackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import com.oculus.browser.R;
import org.chromium.chrome.browser.gesturenav.NavigationBubble;

/* renamed from: pV0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4241pV0 extends ViewGroup {
    public static long F;
    public static boolean G;
    public final DecelerateInterpolator H;
    public final float I;

    /* renamed from: J  reason: collision with root package name */
    public final int f11069J;
    public float K;
    public IX L;
    public JX M;
    public boolean N;
    public int O;
    public float P;
    public boolean Q;
    public NavigationBubble R;
    public int S;
    public int T;
    public int U;
    public AnimationSet V;
    public int W;
    public boolean a0;
    public int b0;
    public boolean c0;
    public final Animation.AnimationListener d0 = new animation.Animation$AnimationListenerC3728mV0(this);
    public final Animation e0;

    public C4241pV0(Context context) {
        super(context);
        C3899nV0 nv0 = new C3899nV0(this);
        this.e0 = nv0;
        getResources().getInteger(17694721);
        setWillNotDraw(false);
        this.H = new DecelerateInterpolator(2.0f);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.f22750_resource_name_obfuscated_RES_2131165894);
        this.f11069J = dimensionPixelSize;
        NavigationBubble navigationBubble = (NavigationBubble) LayoutInflater.from(getContext()).inflate(R.layout.f39790_resource_name_obfuscated_RES_2131624288, (ViewGroup) null);
        this.R = navigationBubble;
        navigationBubble.L.setText(getResources().getString(R.string.f56870_resource_name_obfuscated_RES_2131953004, getContext().getString(R.string.f46950_resource_name_obfuscated_RES_2131952012)));
        this.S = dimensionPixelSize;
        addView(this.R);
        this.I = getResources().getDisplayMetrics().density * 32.0f;
        nv0.setAnimationListener(new animation.Animation$AnimationListenerC4070oV0(this));
    }

    public float a() {
        return this.a0 ? -Math.min(0.0f, this.P) : Math.max(0.0f, this.P);
    }

    public final void b() {
        this.R.c(0);
        this.S = this.f11069J;
    }

    public void c() {
        this.Q = false;
        d(false);
        b();
        e(this.U - this.O);
        this.O = this.R.getLeft();
        JX jx = this.M;
        if (jx != null) {
            MX mx = jx.f8294a;
            C4241pV0 pv0 = jx.b;
            if (mx.M == null) {
                KX kx = new KX(mx);
                mx.M = kx;
                pv0.post(kx);
            }
        }
    }

    public final void d(boolean z) {
        if (this.N != z) {
            this.N = z;
            if (z) {
                f(this.d0);
            }
        }
    }

    public final void e(int i) {
        this.R.offsetLeftAndRight(i);
        this.O = this.R.getLeft();
    }

    public final void f(Animation.AnimationListener animationListener) {
        IX ix;
        if (this.N && (ix = this.L) != null) {
            boolean z = this.a0;
            MX mx = ix.f8230a;
            C4241pV0 pv0 = ix.b;
            mx.H.onResult(Boolean.valueOf(z));
            mx.a();
            if (mx.L == null) {
                mx.L = new LX(mx);
            }
            pv0.post(mx.L);
        }
        if (this.V == null || this.W != this.S) {
            this.W = this.S;
            ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, (float) (this.S / 2), (float) (this.R.getHeight() / 2));
            scaleAnimation.setInterpolator(G30.d);
            scaleAnimation.setDuration(600);
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setInterpolator(this.H);
            alphaAnimation.setDuration(600);
            AnimationSet animationSet = new AnimationSet(false);
            this.V = animationSet;
            animationSet.addAnimation(alphaAnimation);
            this.V.addAnimation(scaleAnimation);
        }
        NavigationBubble navigationBubble = this.R;
        navigationBubble.N = animationListener;
        navigationBubble.clearAnimation();
        this.R.startAnimation(this.V);
    }

    public boolean g() {
        return a() > this.I * 3.0f;
    }

    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (getChildCount() != 0) {
            int measuredHeight = getMeasuredHeight();
            int measuredWidth = this.R.getMeasuredWidth();
            int measuredHeight2 = this.R.getMeasuredHeight();
            NavigationBubble navigationBubble = this.R;
            int i5 = this.O;
            int i6 = measuredHeight / 2;
            int i7 = measuredHeight2 / 2;
            navigationBubble.layout(i5, i6 - i7, measuredWidth + i5, i6 + i7);
        }
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.R.measure(View.MeasureSpec.makeMeasureSpec(this.S, 1073741824), View.MeasureSpec.makeMeasureSpec(this.f11069J, 1073741824));
    }
}
