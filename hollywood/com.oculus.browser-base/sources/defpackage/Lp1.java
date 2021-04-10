package defpackage;

import sun.misc.Unsafe;

/* renamed from: Lp1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class Lp1 extends Np1 {
    public Lp1(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // defpackage.Np1
    public boolean c(Object obj, long j) {
        return Op1.i ? Op1.h(obj, j) != 0 : Op1.i(obj, j) != 0;
    }

    @Override // defpackage.Np1
    public byte d(Object obj, long j) {
        if (Op1.i) {
            return Op1.h(obj, j);
        }
        return Op1.i(obj, j);
    }

    @Override // defpackage.Np1
    public double e(Object obj, long j) {
        return Double.longBitsToDouble(h(obj, j));
    }

    @Override // defpackage.Np1
    public float f(Object obj, long j) {
        return Float.intBitsToFloat(g(obj, j));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: boolean */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.Np1
    public void k(Object obj, long j, boolean z) {
        if (Op1.i) {
            Op1.q(obj, j, z ? (byte) 1 : 0);
        } else {
            Op1.r(obj, j, z ? (byte) 1 : 0);
        }
    }

    @Override // defpackage.Np1
    public void l(Object obj, long j, byte b) {
        if (Op1.i) {
            Op1.q(obj, j, b);
        } else {
            Op1.r(obj, j, b);
        }
    }

    @Override // defpackage.Np1
    public void m(Object obj, long j, double d) {
        p(obj, j, Double.doubleToLongBits(d));
    }

    @Override // defpackage.Np1
    public void n(Object obj, long j, float f) {
        o(obj, j, Float.floatToIntBits(f));
    }
}
