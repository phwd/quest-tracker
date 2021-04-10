package defpackage;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.view.View;
import java.util.HashSet;
import java.util.Iterator;
import org.chromium.base.Callback;

/* renamed from: q21  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnLayoutChangeListenerC4337q21 extends AbstractC2059ck implements View.OnLayoutChangeListener {
    public UH0 F;
    public AbstractC2400ek G;
    public HashSet H = new HashSet();
    public Q31 I;

    /* renamed from: J  reason: collision with root package name */
    public Runnable f11110J;
    public Runnable K;
    public Runnable L;
    public Q31 M;
    public Callback N;
    public Runnable O;
    public ValueAnimator P;
    public ValueAnimator Q;
    public AnimatorSet R;
    public AnimatorSet S;
    public int T;
    public int U;
    public boolean V;

    public View$OnLayoutChangeListenerC4337q21(AbstractC2400ek ekVar, Q31 q31, Q31 q312) {
        this.G = ekVar;
        this.I = q31;
        this.M = q312;
    }

    public final void a(int i) {
        Iterator it = this.H.iterator();
        while (it.hasNext()) {
            ((Y11) it.next()).f(i);
        }
    }

    public final void b(boolean z) {
        int i;
        this.V = z;
        if (z) {
            i = 0;
        } else {
            i = this.U;
        }
        this.T = i;
        if (!z) {
            ((C1551Zj) this.G).Y.b(this);
        }
        int i2 = this.T;
        Iterator it = this.H.iterator();
        while (it.hasNext()) {
            ((Y11) it.next()).g(i2);
        }
    }

    @Override // defpackage.AbstractC2230dk
    public void j(int i, int i2, int i3, int i4, boolean z) {
        int i5;
        boolean z2 = true;
        boolean z3 = i2 > 0;
        this.F.j(AbstractC4507r21.d, z3 && ((Boolean) this.M.get()).booleanValue());
        this.F.l(AbstractC4507r21.i, i2);
        boolean z4 = z3 && i2 == this.T;
        UH0 uh0 = this.F;
        SH0 sh0 = AbstractC4507r21.c;
        if (!this.V || (!((Boolean) this.M.get()).booleanValue() && z3)) {
            i5 = (z4 || !((Boolean) this.M.get()).booleanValue()) ? 0 : 4;
        } else {
            i5 = 8;
        }
        uh0.l(sh0, i5);
        Runnable runnable = this.f11110J;
        if (runnable != null && z4) {
            runnable.run();
            this.f11110J = null;
        }
        if (z3 || !this.V) {
            z2 = false;
        }
        if (z2) {
            ((C1551Zj) this.G).Y.c(this);
            this.V = false;
            this.U = 0;
            this.L.run();
        }
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        if (!this.V && this.U == 0 && view.getHeight() > 0) {
            this.N.onResult(null);
            this.U = view.getHeight();
            b(false);
        }
    }
}
