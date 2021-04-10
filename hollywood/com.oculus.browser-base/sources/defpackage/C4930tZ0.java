package defpackage;

/* renamed from: tZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4930tZ0 extends C5270vZ0 {
    public final /* synthetic */ AbstractC5780yZ0 d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4930tZ0(AbstractC5780yZ0 yz0) {
        super(yz0);
        this.d = yz0;
        this.f11485a = yz0.g0();
        this.b = yz0.F;
    }

    @Override // defpackage.C5270vZ0
    public float c() {
        return super.k();
    }

    @Override // defpackage.C5270vZ0
    public float d() {
        if (this.d.m0()) {
            return 0.0f;
        }
        AbstractC5780yZ0 yz0 = this.d;
        return Math.max((float) yz0.i0, this.f11485a * 0.17f) * yz0.j0;
    }

    @Override // defpackage.C5270vZ0
    public float e() {
        return 0.0f;
    }

    @Override // defpackage.C5270vZ0
    public float f() {
        return 0.0f;
    }

    @Override // defpackage.C5270vZ0
    public float g() {
        if (this.d.m0()) {
            return this.d.G;
        }
        return (float) Math.round(this.f11485a - d());
    }

    @Override // defpackage.C5270vZ0
    public float h() {
        return j() + (a() * this.d.f0());
    }

    @Override // defpackage.C5270vZ0
    public int i(float f, float f2) {
        if (f2 < l()) {
            return -1;
        }
        return f2 > l() + super.k() ? 1 : 0;
    }

    @Override // defpackage.C5270vZ0
    public float k() {
        return this.b;
    }

    public float l() {
        float a2 = a() + ((float) this.d.i0());
        if (this.d.a0.size() > 2) {
            return j() + (d() / 2.0f) + (a2 * this.d.f0());
        }
        return j() + (a2 * this.d.f0()) + (this.d.i0() == 1 ? d() : 0.0f);
    }
}
