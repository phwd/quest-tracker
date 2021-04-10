package defpackage;

import java.nio.ByteBuffer;

/* renamed from: q31  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4340q31 {

    /* renamed from: a  reason: collision with root package name */
    public final int f11112a;

    public AbstractC4340q31(int i, int i2) {
        this.f11112a = i;
    }

    public abstract void a(C1648aL aLVar);

    public ByteBuffer b() {
        C1648aL aLVar = new C1648aL(null, this.f11112a);
        a(aLVar);
        C2740gj0 y = aLVar.y();
        if (y.b.isEmpty()) {
            return y.f10015a;
        }
        throw new UnsupportedOperationException("Handles are discarded.");
    }

    public IS0 c(SA sa, C0942Pj0 pj0) {
        C1648aL aLVar = new C1648aL(sa, this.f11112a + pj0.c.f7794a);
        aLVar.e(pj0.c);
        aLVar.c(0, 8);
        aLVar.c(pj0.d, 12);
        aLVar.c(pj0.e, 16);
        if (C0942Pj0.b(pj0.e)) {
            aLVar.d(pj0.f, 24);
        }
        a(aLVar);
        return new IS0(aLVar.y(), pj0);
    }
}
