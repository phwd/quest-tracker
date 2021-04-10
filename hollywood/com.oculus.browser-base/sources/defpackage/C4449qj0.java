package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.res.Resources;
import android.view.MotionEvent;
import com.oculus.browser.R;
import org.chromium.base.Callback;

/* renamed from: qj0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4449qj0 extends AbstractC5024u41 {
    public static final TimeInterpolator F = G30.f;
    public static final TimeInterpolator G = G30.d;
    public static final TimeInterpolator H = G30.b;
    public final UH0 I;

    /* renamed from: J  reason: collision with root package name */
    public final Q31 f11158J;
    public final float K;
    public final float L;
    public final Q31 M;
    public final Runnable N;
    public final Callback O;
    public Animator P;
    public int Q = 0;
    public int R;
    public float S;
    public boolean T;

    public C4449qj0(UH0 uh0, Q31 q31, Resources resources, Runnable runnable, Callback callback) {
        this.I = uh0;
        this.f11158J = q31;
        this.K = (float) resources.getDimensionPixelSize(R.dimen.f20840_resource_name_obfuscated_RES_2131165703);
        this.L = (float) resources.getDimensionPixelSize(R.dimen.f20770_resource_name_obfuscated_RES_2131165696);
        this.M = new C3765mj0(resources);
        this.N = runnable;
        this.O = callback;
    }

    public final void a() {
        Animator animator = this.P;
        if (animator != null) {
            animator.cancel();
        }
        this.P = null;
    }

    public final boolean b(int i) {
        return i == 3 || i == 4;
    }

    public final void c(boolean z, float f, boolean z2, Runnable runnable) {
        float f2 = 0.0f;
        int i = (f > 0.0f ? 1 : (f == 0.0f ? 0 : -1));
        long j = i == 0 ? 600 : 300;
        boolean z3 = i == 0;
        if (z3) {
            f2 = 1.0f;
        }
        ObjectAnimator a2 = WH0.a(this.I, AbstractC4619rj0.o, f2);
        a2.setInterpolator(z3 ? G : H);
        a2.setDuration(j);
        ObjectAnimator a3 = WH0.a(this.I, z ? AbstractC4619rj0.n : AbstractC4619rj0.m, f);
        a3.setInterpolator(z3 ? F : H);
        a3.setDuration(j);
        if (!z2) {
            (z3 ? a3 : a2).setStartDelay(100);
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(a2, a3);
        animatorSet.addListener(new C4278pj0(this, z3, runnable));
        this.P = animatorSet;
        this.O.onResult(animatorSet);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0069  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006c  */
    @Override // defpackage.AbstractC5194v41
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d(int r1, android.view.MotionEvent r2, float r3, float r4, float r5, float r6) {
        /*
        // Method dump skipped, instructions count: 117
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4449qj0.d(int, android.view.MotionEvent, float, float, float, float):void");
    }

    @Override // defpackage.AbstractC5194v41
    public void f(MotionEvent motionEvent, float f, float f2, float f3, float f4) {
        float f5;
        float f6;
        if (b(this.R)) {
            this.I.k(AbstractC4619rj0.n, AbstractC4089od0.b(this.S + f2, (float) (-((Integer) this.f11158J.get()).intValue()), 0.0f));
        } else {
            this.I.k(AbstractC4619rj0.m, AbstractC4089od0.b(this.S + f, -((Float) this.M.get()).floatValue(), ((Float) this.M.get()).floatValue()));
        }
        UH0 uh0 = this.I;
        RH0 rh0 = AbstractC4619rj0.o;
        if (b(this.R)) {
            f6 = Math.abs(this.I.e(AbstractC4619rj0.n));
            f5 = (float) ((Integer) this.f11158J.get()).intValue();
        } else {
            f6 = Math.abs(this.I.e(AbstractC4619rj0.m));
            f5 = ((Float) this.M.get()).floatValue();
        }
        uh0.k(rh0, 1.0f - (f6 / f5));
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0087  */
    @Override // defpackage.AbstractC5194v41
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void h() {
        /*
        // Method dump skipped, instructions count: 144
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C4449qj0.h():void");
    }

    @Override // defpackage.AbstractC5194v41
    public boolean i(int i) {
        return i != 0 && this.Q == 2;
    }

    @Override // defpackage.AbstractC5194v41
    public void j(int i, MotionEvent motionEvent) {
        RH0 rh0;
        UH0 uh0;
        this.Q = 3;
        this.R = i;
        if (b(i)) {
            uh0 = this.I;
            rh0 = AbstractC4619rj0.n;
        } else {
            uh0 = this.I;
            rh0 = AbstractC4619rj0.m;
        }
        this.S = uh0.e(rh0);
        this.T = false;
    }
}
