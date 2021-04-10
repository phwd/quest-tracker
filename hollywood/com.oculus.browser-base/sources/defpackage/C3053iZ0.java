package defpackage;

import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import java.util.ArrayList;

/* renamed from: iZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3053iZ0 {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList f10144a = new ArrayList();
    public final AnimatorSet b = new AnimatorSet();
    public final ArrayList c = new ArrayList();
    public final C4316pw d;

    public C3053iZ0(C3223jZ0 jz0, C4316pw pwVar) {
        this.d = pwVar;
    }

    public void a(UH0 uh0, RH0 rh0, float f, float f2, long j) {
        c(uh0, rh0, f, f2, j, 0);
    }

    public void b(Object obj, AbstractC3719mR mRVar, float f, float f2, long j, TimeInterpolator timeInterpolator) {
        d(obj, mRVar, f, f2, j, 0, timeInterpolator);
    }

    public void c(UH0 uh0, RH0 rh0, float f, float f2, long j, long j2) {
        C5677xw f3 = C5677xw.f(this.d, uh0, rh0, f, f2, j);
        if (j2 < 0) {
            j2 = 0;
        }
        f3.Q = j2;
        this.f10144a.add(f3);
    }

    public void d(Object obj, AbstractC3719mR mRVar, float f, float f2, long j, long j2, TimeInterpolator timeInterpolator) {
        C5677xw xwVar;
        if (timeInterpolator == null) {
            xwVar = C5677xw.d(this.d, obj, mRVar, f, f2, j);
        } else {
            xwVar = C5677xw.e(this.d, obj, mRVar, f, f2, j, timeInterpolator);
        }
        long j3 = 0;
        if (j2 >= 0) {
            j3 = j2;
        }
        xwVar.Q = j3;
        this.f10144a.add(xwVar);
        if (mRVar == IZ0.d) {
            this.c.add(xwVar);
        }
    }
}
