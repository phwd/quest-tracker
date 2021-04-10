package defpackage;

import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;

/* renamed from: bd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1868bd0 extends Animation {
    public final /* synthetic */ C2380ed0 F;
    public final /* synthetic */ C2551fd0 G;

    public C1868bd0(C2551fd0 fd0, C2380ed0 ed0) {
        this.G = fd0;
        this.F = ed0;
    }

    public void applyTransformation(float f, Transformation transformation) {
        C2551fd0 fd0 = this.G;
        if (fd0.R) {
            C2380ed0 ed0 = this.F;
            fd0.b(f, ed0);
            float floor = (float) (Math.floor((double) (ed0.n / 0.8f)) + 1.0d);
            float f2 = ed0.l;
            ed0.e = (((ed0.m - ((float) Math.toRadians(((double) ed0.h) / (ed0.r * 6.283185307179586d)))) - f2) * f) + f2;
            ed0.a();
            ed0.f = ed0.m;
            ed0.a();
            float f3 = ed0.n;
            ed0.g = AbstractC2531fV.a(floor, f3, f, f3);
            ed0.a();
            return;
        }
        C2380ed0 ed02 = this.F;
        float radians = (float) Math.toRadians(((double) ed02.h) / (ed02.r * 6.283185307179586d));
        C2380ed0 ed03 = this.F;
        float f4 = ed03.m;
        float f5 = ed03.l;
        float f6 = ed03.n;
        this.G.b(f, ed03);
        if (f <= 0.5f) {
            float interpolation = (C2551fd0.G.getInterpolation(f / 0.5f) * (0.8f - radians)) + f5;
            C2380ed0 ed04 = this.F;
            ed04.e = interpolation;
            ed04.a();
        }
        if (f > 0.5f) {
            Interpolator interpolator = C2551fd0.G;
            C2380ed0 ed05 = this.F;
            ed05.f = (interpolator.getInterpolation((f - 0.5f) / 0.5f) * (0.8f - radians)) + f4;
            ed05.a();
        }
        C2380ed0 ed06 = this.F;
        ed06.g = (0.25f * f) + f6;
        ed06.a();
        C2551fd0 fd02 = this.G;
        fd02.K = ((fd02.O / 5.0f) * 1080.0f) + (f * 216.0f);
        fd02.invalidateSelf();
    }
}
