package defpackage;

import org.chromium.ui.base.LocalizationUtils;

/* renamed from: vZ0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5270vZ0 {

    /* renamed from: a  reason: collision with root package name */
    public float f11485a;
    public float b;
    public final /* synthetic */ AbstractC5780yZ0 c;

    public C5270vZ0(AbstractC5780yZ0 yz0) {
        this.c = yz0;
        this.f11485a = yz0.F;
        this.b = yz0.g0();
    }

    public float a() {
        AbstractC5780yZ0 yz0 = this.c;
        return AbstractC4089od0.b(yz0.g0, 0.0f, (float) yz0.h0());
    }

    public float b() {
        float a2 = a() + ((float) this.c.i0());
        if (this.c.a0.size() > 2) {
            return (d() / 2.0f) + (a2 * this.c.f0());
        }
        boolean z = true;
        if (this.c.i0() != 1) {
            z = false;
        }
        return (a2 * this.c.f0()) + (LocalizationUtils.isLayoutRtl() ^ z ? d() : 0.0f);
    }

    public float c() {
        return this.b;
    }

    public float d() {
        AbstractC5780yZ0 yz0 = this.c;
        return Math.max((float) yz0.i0, this.f11485a * 0.17f) * yz0.j0;
    }

    public float e() {
        float a2 = a() * this.c.f0();
        if (this.c.a0.size() > 2) {
            a2 += d() / 2.0f;
        }
        return LocalizationUtils.isLayoutRtl() ? d() - a2 : a2;
    }

    public float f() {
        float f;
        if (LocalizationUtils.isLayoutRtl()) {
            f = d() + (-this.f11485a);
        } else {
            f = this.f11485a - d();
        }
        return (float) Math.round(f);
    }

    public float g() {
        return 0.0f;
    }

    public float h() {
        return j();
    }

    public int i(float f, float f2) {
        int i;
        if (f < b()) {
            i = -1;
        } else {
            i = f > k() + b() ? 1 : 0;
        }
        return LocalizationUtils.isLayoutRtl() ? i * -1 : i;
    }

    public float j() {
        return this.c.k0() * this.c.k0;
    }

    public float k() {
        return this.f11485a - d();
    }
}
