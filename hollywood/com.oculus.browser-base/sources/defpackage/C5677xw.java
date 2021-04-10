package defpackage;

import android.animation.Animator;
import android.animation.TimeInterpolator;
import android.provider.Settings;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ContextUtils;

/* renamed from: xw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5677xw extends Animator {
    public static float F = 1.0f;
    public final WeakReference G;
    public final C1322Vq0 H = new C1322Vq0();
    public final ArrayList I = new ArrayList();

    /* renamed from: J  reason: collision with root package name */
    public final ArrayList f11648J = new ArrayList();
    public TimeInterpolator K;
    public long L;
    public float M;
    public Q31 N;
    public Q31 O;
    public long P;
    public long Q;
    public int R = 3;
    public boolean S;

    public C5677xw(C4316pw pwVar) {
        this.G = new WeakReference(pwVar);
        this.K = G30.f8058a;
        j(0.0f, 1.0f);
        float f = Settings.Global.getFloat(ContextUtils.getApplicationContext().getContentResolver(), "animator_duration_scale", F);
        F = f;
        if (f != 1.0f) {
            AbstractC1220Ua0.d("CompositorAnimator", "Settings.Global.ANIMATOR_DURATION_SCALE = %f", Float.valueOf(f));
        }
    }

    public static C5677xw c(C4316pw pwVar, float f, float f2, long j, AbstractC5507ww wwVar) {
        C5677xw xwVar = new C5677xw(pwVar);
        C5167uw uwVar = new C5167uw(f);
        C5337vw vwVar = new C5337vw(f2);
        xwVar.N = uwVar;
        xwVar.O = vwVar;
        if (wwVar != null) {
            xwVar.I.add(wwVar);
        }
        if (j < 0) {
            j = 0;
        }
        xwVar.P = j;
        return xwVar;
    }

    public static C5677xw d(C4316pw pwVar, Object obj, AbstractC3719mR mRVar, float f, float f2, long j) {
        return e(pwVar, obj, mRVar, f, f2, j, G30.f8058a);
    }

    public static C5677xw e(C4316pw pwVar, Object obj, AbstractC3719mR mRVar, float f, float f2, long j, TimeInterpolator timeInterpolator) {
        C5677xw xwVar = new C5677xw(pwVar);
        C5167uw uwVar = new C5167uw(f);
        C5337vw vwVar = new C5337vw(f2);
        xwVar.N = uwVar;
        xwVar.O = vwVar;
        if (j < 0) {
            j = 0;
        }
        xwVar.P = j;
        xwVar.I.add(new C4487qw(mRVar, obj));
        xwVar.K = timeInterpolator;
        return xwVar;
    }

    public static C5677xw f(C4316pw pwVar, UH0 uh0, RH0 rh0, float f, float f2, long j) {
        return g(pwVar, uh0, rh0, f, f2, j, G30.f8058a);
    }

    public static C5677xw g(C4316pw pwVar, UH0 uh0, RH0 rh0, float f, float f2, long j, TimeInterpolator timeInterpolator) {
        return h(pwVar, uh0, rh0, new C4827sw(f), new C4997tw(f2), j, timeInterpolator);
    }

    public static C5677xw h(C4316pw pwVar, UH0 uh0, RH0 rh0, Q31 q31, Q31 q312, long j, TimeInterpolator timeInterpolator) {
        C5677xw xwVar = new C5677xw(pwVar);
        xwVar.N = q31;
        xwVar.O = q312;
        if (j < 0) {
            j = 0;
        }
        xwVar.P = j;
        xwVar.I.add(new C4657rw(uh0, rh0));
        xwVar.K = timeInterpolator;
        return xwVar;
    }

    public float a() {
        return ((((Float) this.O.get()).floatValue() - ((Float) this.N.get()).floatValue()) * this.M) + ((Float) this.N.get()).floatValue();
    }

    public void addListener(Animator.AnimatorListener animatorListener) {
        this.H.b(animatorListener);
    }

    public final long b() {
        return (long) (((float) this.P) * F);
    }

    public void cancel() {
        if (this.R != 3) {
            this.R = 2;
            super.cancel();
            Iterator it = this.H.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((Animator.AnimatorListener) uq0.next()).onAnimationCancel(this);
                } else {
                    end();
                    return;
                }
            }
        }
    }

    public void end() {
        if (this.R != 3) {
            super.end();
            boolean z = this.R == 2;
            this.R = 3;
            if (!this.S && !z) {
                this.M = 1.0f;
                Iterator it = this.I.iterator();
                while (it.hasNext()) {
                    ((AbstractC5507ww) it.next()).a(this);
                }
            }
            Iterator it2 = this.H.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it2;
                if (uq0.hasNext()) {
                    ((Animator.AnimatorListener) uq0.next()).onAnimationEnd(this);
                } else {
                    return;
                }
            }
        }
    }

    public long getDuration() {
        return this.P;
    }

    public long getStartDelay() {
        return this.Q;
    }

    public C5677xw i(long j) {
        if (j < 0) {
            j = 0;
        }
        this.P = j;
        return this;
    }

    public boolean isRunning() {
        return this.R == 1;
    }

    public void j(float f, float f2) {
        C5167uw uwVar = new C5167uw(f);
        C5337vw vwVar = new C5337vw(f2);
        this.N = uwVar;
        this.O = vwVar;
    }

    public void removeAllListeners() {
        this.H.clear();
        this.I.clear();
    }

    public void removeListener(Animator.AnimatorListener animatorListener) {
        this.H.c(animatorListener);
    }

    public Animator setDuration(long j) {
        if (j < 0) {
            j = 0;
        }
        this.P = j;
        return this;
    }

    public void setInterpolator(TimeInterpolator timeInterpolator) {
        this.K = timeInterpolator;
    }

    public void setStartDelay(long j) {
        if (j < 0) {
            j = 0;
        }
        this.Q = j;
    }

    public void start() {
        if (this.R == 3) {
            super.start();
            this.R = 1;
            this.S = false;
            C4316pw pwVar = (C4316pw) this.G.get();
            if (pwVar != null) {
                if (pwVar.f11101a.size() <= 0) {
                    pwVar.e = System.currentTimeMillis();
                }
                this.H.b(new C4145ow(pwVar, this));
                pwVar.f11101a.add(this);
                if (!pwVar.d) {
                    pwVar.b.run();
                    pwVar.d = true;
                }
            }
            this.L = 0;
            Iterator it = this.H.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (uq0.hasNext()) {
                    ((Animator.AnimatorListener) uq0.next()).onAnimationStart(this);
                } else {
                    return;
                }
            }
        }
    }
}
