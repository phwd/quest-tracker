package defpackage;

import android.content.Context;
import android.content.res.Resources;
import com.oculus.browser.R;
import java.util.Objects;

/* renamed from: y21  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5698y21 implements C00 {
    public final UH0 F;
    public boolean G;
    public boolean H;
    public boolean I;

    /* renamed from: J  reason: collision with root package name */
    public boolean f11657J;
    public boolean K;
    public boolean L;
    public boolean M;
    public boolean N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public final int R;
    public int S;
    public int T;
    public int U;
    public int V;
    public int W;
    public int X;
    public int Y;
    public int Z;
    public C5528x21 a0;
    public Resources b0;
    public AbstractC4422qa0 c0;
    public boolean d0 = true;
    public float e0;
    public String f0;
    public boolean g0;
    public Runnable h0;
    public final float i0;
    public final float j0;

    public C5698y21(UH0 uh0, Resources resources, Context context, Qq1 qq1, boolean z, Runnable runnable, D00 d00, AbstractC4422qa0 qa0) {
        this.F = uh0;
        this.c0 = qa0;
        this.a0 = new C5528x21(this);
        d();
        this.b0 = resources;
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.f24850_resource_name_obfuscated_RES_2131166104) - this.b0.getDimensionPixelSize(R.dimen.f24840_resource_name_obfuscated_RES_2131166103);
        this.R = dimensionPixelSize;
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.f20490_resource_name_obfuscated_RES_2131165668);
        float f = ((float) dimensionPixelSize2) / ((float) (dimensionPixelSize2 + dimensionPixelSize));
        this.i0 = f;
        this.j0 = f != 1.0f ? 1.0f - f : 1.0f;
        this.Q = z;
        this.h0 = runnable;
        if (d00 != null) {
            d00.f7854a.b(this);
            c(d00.b());
        }
    }

    public int a(int i) {
        if (i == 0 || i == R.drawable.f30800_resource_name_obfuscated_RES_2131231120) {
            return 0;
        }
        boolean z = this.G;
        if (z) {
            return R.color.f11380_resource_name_obfuscated_RES_2131099828;
        }
        return AbstractC1300Vg1.c(!z);
    }

    public void b(boolean z) {
        this.F.j(A21.d, z);
    }

    @Override // defpackage.C00
    public void c(boolean z) {
        boolean z2;
        boolean z3 = this.g0;
        this.g0 = z;
        this.F.j(A21.b, z && !this.Q);
        if (z3 != z && !this.Q && this.M && !(z2 = this.g0)) {
            Objects.requireNonNull(this.a0);
            if (AbstractC5762yQ0.g(z2)) {
                this.h0.run();
            }
        }
    }

    public final void d() {
        boolean z = this.G;
        int i = z ? R.color.f12160_resource_name_obfuscated_RES_2131099906 : R.color.f12170_resource_name_obfuscated_RES_2131099907;
        int i2 = 0;
        if (this.f11657J || this.K) {
            i2 = z ? R.color.f12950_resource_name_obfuscated_RES_2131099985 : R.color.f12960_resource_name_obfuscated_RES_2131099986;
        } else if (this.L) {
            i2 = z ? R.color.f12930_resource_name_obfuscated_RES_2131099983 : R.color.f12940_resource_name_obfuscated_RES_2131099984;
        }
        int c = AbstractC1300Vg1.c(!z);
        this.F.l(A21.c, i);
        this.Z = c;
        if (i2 != 0) {
            this.F.l(A21.j, i2);
        }
        e();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002d  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0174  */
    /* JADX WARNING: Removed duplicated region for block: B:88:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void e() {
        /*
        // Method dump skipped, instructions count: 388
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.C5698y21.e():void");
    }

    public final void f() {
        int i;
        boolean z = this.K;
        boolean z2 = false;
        if (z) {
            i = R.string.f54170_resource_name_obfuscated_RES_2131952734;
        } else if (this.f11657J) {
            i = R.string.f54180_resource_name_obfuscated_RES_2131952735;
        } else {
            i = this.L ? R.string.f54190_resource_name_obfuscated_RES_2131952736 : 0;
        }
        if (((this.f11657J && this.V != 5) || this.L || z) && this.I && !this.H && i != 0) {
            z2 = true;
        }
        if (z2) {
            this.F.l(A21.k, i);
        }
        this.F.j(A21.l, z2);
    }
}
