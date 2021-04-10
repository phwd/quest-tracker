package androidx.mediarouter.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import com.oculus.browser.R;
import java.util.Objects;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class MediaRouteButton extends View {
    public static C2045cf0 F;
    public static final SparseArray G = new SparseArray(2);
    public static final int[] H = {16842912};
    public static final int[] I = {16842911};

    /* renamed from: J  reason: collision with root package name */
    public final C3246jh0 f9477J;
    public final C2216df0 K;
    public C0629Kg0 L;
    public C0930Pf0 M;
    public boolean N;
    public int O;
    public AsyncTaskC2386ef0 P;
    public Drawable Q;
    public int R;
    public int S;
    public ColorStateList T;
    public int U;
    public int V;
    public boolean W;
    public boolean a0;

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MediaRouteButton(android.content.Context r10, android.util.AttributeSet r11) {
        /*
        // Method dump skipped, instructions count: 228
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.MediaRouteButton.<init>(android.content.Context, android.util.AttributeSet):void");
    }

    public final void a() {
        if (this.R > 0) {
            AsyncTaskC2386ef0 ef0 = this.P;
            if (ef0 != null) {
                ef0.cancel(false);
            }
            AsyncTaskC2386ef0 ef02 = new AsyncTaskC2386ef0(this, this.R, getContext());
            this.P = ef02;
            this.R = 0;
            ef02.executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
        }
    }

    public void b() {
        boolean z;
        C2392eh0 h = this.f9477J.h();
        boolean z2 = false;
        int i = !h.e() && h.i(this.L) ? h.h : 0;
        if (this.S != i) {
            this.S = i;
            z = true;
        } else {
            z = false;
        }
        if (z) {
            h();
            refreshDrawableState();
        }
        if (i == 1) {
            a();
        }
        if (this.N) {
            if (this.W || this.f9477J.i(this.L, 1)) {
                z2 = true;
            }
            setEnabled(z2);
        }
        Drawable drawable = this.Q;
        if (drawable != null && (drawable.getCurrent() instanceof AnimationDrawable)) {
            AnimationDrawable animationDrawable = (AnimationDrawable) this.Q.getCurrent();
            if (this.N) {
                if ((z || i == 1) && !animationDrawable.isRunning()) {
                    animationDrawable.start();
                }
            } else if (i == 2) {
                if (animationDrawable.isRunning()) {
                    animationDrawable.stop();
                }
                animationDrawable.selectDrawable(animationDrawable.getNumberOfFrames() - 1);
            }
        }
    }

    public void c() {
        int i = this.O;
        if (i == 0 && !this.W && !F.b) {
            i = 4;
        }
        super.setVisibility(i);
        Drawable drawable = this.Q;
        if (drawable != null) {
            drawable.setVisible(getVisibility() == 0, false);
        }
    }

    public void d(Drawable drawable) {
        Drawable drawable2;
        AsyncTaskC2386ef0 ef0 = this.P;
        if (ef0 != null) {
            ef0.cancel(false);
        }
        Drawable drawable3 = this.Q;
        if (drawable3 != null) {
            drawable3.setCallback(null);
            unscheduleDrawable(this.Q);
        }
        if (drawable != null) {
            if (this.T != null) {
                drawable = drawable.mutate();
                drawable.setTintList(this.T);
            }
            drawable.setCallback(this);
            drawable.setState(getDrawableState());
            drawable.setVisible(getVisibility() == 0, false);
        }
        this.Q = drawable;
        refreshDrawableState();
        if (this.N && (drawable2 = this.Q) != null && (drawable2.getCurrent() instanceof AnimationDrawable)) {
            AnimationDrawable animationDrawable = (AnimationDrawable) this.Q.getCurrent();
            int i = this.S;
            if (i == 1) {
                if (!animationDrawable.isRunning()) {
                    animationDrawable.start();
                }
            } else if (i == 2) {
                if (animationDrawable.isRunning()) {
                    animationDrawable.stop();
                }
                animationDrawable.selectDrawable(animationDrawable.getNumberOfFrames() - 1);
            }
        }
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        if (this.Q != null) {
            this.Q.setState(getDrawableState());
            invalidate();
        }
    }

    public void e(C0629Kg0 kg0) {
        if (kg0 == null) {
            throw new IllegalArgumentException("selector must not be null");
        } else if (!this.L.equals(kg0)) {
            if (this.N) {
                if (!this.L.c()) {
                    this.f9477J.j(this.K);
                }
                if (!kg0.c()) {
                    this.f9477J.a(kg0, this.K, 0);
                }
            }
            this.L = kg0;
            b();
        }
    }

    public boolean f() {
        if (!this.N) {
            return false;
        }
        Objects.requireNonNull(this.f9477J);
        C3246jh0.b();
        Objects.requireNonNull(C3246jh0.f10229a);
        return g(1);
    }

    public final boolean g(int i) {
        KS ks;
        Activity activity;
        Context context = getContext();
        while (true) {
            ks = null;
            if (!(context instanceof ContextWrapper)) {
                activity = null;
                break;
            } else if (context instanceof Activity) {
                activity = (Activity) context;
                break;
            } else {
                context = ((ContextWrapper) context).getBaseContext();
            }
        }
        if (activity instanceof AbstractActivityC3892nS) {
            ks = ((AbstractActivityC3892nS) activity).Y();
        }
        if (ks != null) {
            C2392eh0 h = this.f9477J.h();
            if (h.e() || !h.i(this.L)) {
                if (ks.J("android.support.v7.mediarouter:MediaRouteChooserDialogFragment") != null) {
                    Log.w("MediaRouteButton", "showDialog(): Route chooser dialog already showing!");
                    return false;
                }
                Objects.requireNonNull(this.M);
                C3411kf0 kf0 = new C3411kf0();
                kf0.n1(this.L);
                if (i == 2) {
                    if (kf0.N0 == null) {
                        kf0.M0 = true;
                    } else {
                        throw new IllegalStateException("This must be called before creating dialog");
                    }
                }
                C0317Fe fe = new C0317Fe(ks);
                fe.i(0, kf0, "android.support.v7.mediarouter:MediaRouteChooserDialogFragment", 1);
                fe.f();
            } else if (ks.J("android.support.v7.mediarouter:MediaRouteControllerDialogFragment") != null) {
                Log.w("MediaRouteButton", "showDialog(): Route controller dialog already showing!");
                return false;
            } else {
                Objects.requireNonNull(this.M);
                C0565Jf0 jf0 = new C0565Jf0();
                C0629Kg0 kg0 = this.L;
                if (kg0 != null) {
                    if (jf0.O0 == null) {
                        Bundle bundle = jf0.K;
                        if (bundle != null) {
                            jf0.O0 = C0629Kg0.b(bundle.getBundle("selector"));
                        }
                        if (jf0.O0 == null) {
                            jf0.O0 = C0629Kg0.f8380a;
                        }
                    }
                    if (!jf0.O0.equals(kg0)) {
                        jf0.O0 = kg0;
                        Bundle bundle2 = jf0.K;
                        if (bundle2 == null) {
                            bundle2 = new Bundle();
                        }
                        bundle2.putBundle("selector", kg0.b);
                        jf0.U0(bundle2);
                        Dialog dialog = jf0.N0;
                        if (dialog != null && jf0.M0) {
                            ((DialogC5460wg0) dialog).h(kg0);
                        }
                    }
                    if (i == 2) {
                        if (jf0.N0 == null) {
                            jf0.M0 = true;
                        } else {
                            throw new IllegalStateException("This must be called before creating dialog");
                        }
                    }
                    C0317Fe fe2 = new C0317Fe(ks);
                    fe2.i(0, jf0, "android.support.v7.mediarouter:MediaRouteControllerDialogFragment", 1);
                    fe2.f();
                } else {
                    throw new IllegalArgumentException("selector must not be null");
                }
            }
            return true;
        }
        throw new IllegalStateException("The activity must be a subclass of FragmentActivity");
    }

    public final void h() {
        int i = this.S;
        String string = getContext().getString(i != 1 ? i != 2 ? R.string.f55180_resource_name_obfuscated_RES_2131952835 : R.string.f55160_resource_name_obfuscated_RES_2131952833 : R.string.f55170_resource_name_obfuscated_RES_2131952834);
        setContentDescription(string);
        if (!this.a0 || TextUtils.isEmpty(string)) {
            string = null;
        }
        Il1.a(this, string);
    }

    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        Drawable drawable = this.Q;
        if (drawable != null) {
            drawable.jumpToCurrentState();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.N = true;
            if (!this.L.c()) {
                this.f9477J.a(this.L, this.K, 0);
            }
            b();
            C2045cf0 cf0 = F;
            if (cf0.c.size() == 0) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                cf0.f9622a.registerReceiver(cf0, intentFilter);
            }
            cf0.c.add(this);
        }
    }

    public int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        int i2 = this.S;
        if (i2 == 1) {
            View.mergeDrawableStates(onCreateDrawableState, I);
        } else if (i2 == 2) {
            View.mergeDrawableStates(onCreateDrawableState, H);
        }
        return onCreateDrawableState;
    }

    public void onDetachedFromWindow() {
        if (!isInEditMode()) {
            this.N = false;
            if (!this.L.c()) {
                this.f9477J.j(this.K);
            }
            C2045cf0 cf0 = F;
            cf0.c.remove(this);
            if (cf0.c.size() == 0) {
                cf0.f9622a.unregisterReceiver(cf0);
            }
        }
        super.onDetachedFromWindow();
    }

    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.Q != null) {
            int paddingLeft = getPaddingLeft();
            int width = getWidth() - getPaddingRight();
            int paddingTop = getPaddingTop();
            int height = getHeight() - getPaddingBottom();
            int intrinsicWidth = this.Q.getIntrinsicWidth();
            int intrinsicHeight = this.Q.getIntrinsicHeight();
            int i = (((width - paddingLeft) - intrinsicWidth) / 2) + paddingLeft;
            int i2 = (((height - paddingTop) - intrinsicHeight) / 2) + paddingTop;
            this.Q.setBounds(i, i2, intrinsicWidth + i, intrinsicHeight + i2);
            this.Q.draw(canvas);
        }
    }

    public void onMeasure(int i, int i2) {
        int i3;
        int size = View.MeasureSpec.getSize(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode = View.MeasureSpec.getMode(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int i4 = this.U;
        Drawable drawable = this.Q;
        int i5 = 0;
        if (drawable != null) {
            i3 = getPaddingRight() + getPaddingLeft() + drawable.getIntrinsicWidth();
        } else {
            i3 = 0;
        }
        int max = Math.max(i4, i3);
        int i6 = this.V;
        Drawable drawable2 = this.Q;
        if (drawable2 != null) {
            i5 = getPaddingBottom() + getPaddingTop() + drawable2.getIntrinsicHeight();
        }
        int max2 = Math.max(i6, i5);
        if (mode == Integer.MIN_VALUE) {
            size = Math.min(size, max);
        } else if (mode != 1073741824) {
            size = max;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(size2, max2);
        } else if (mode2 != 1073741824) {
            size2 = max2;
        }
        setMeasuredDimension(size, size2);
    }

    public boolean performClick() {
        boolean performClick = super.performClick();
        if (!performClick) {
            playSoundEffect(0);
        }
        a();
        if (f() || performClick) {
            return true;
        }
        return false;
    }

    public void setVisibility(int i) {
        this.O = i;
        c();
    }

    public boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.Q;
    }
}
