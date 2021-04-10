package defpackage;

/* renamed from: M80  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class M80 extends N80 {
    public M80(K80 k80) {
        super(null);
    }

    public static E30 c(Object obj, long j) {
        return (E30) Op1.n(obj, j);
    }

    @Override // defpackage.N80
    public void a(Object obj, long j) {
        ((AbstractC2961i) c(obj, j)).F = false;
    }

    @Override // defpackage.N80
    public void b(Object obj, Object obj2, long j) {
        E30 c = c(obj, j);
        E30 c2 = c(obj2, j);
        int size = c.size();
        int size2 = c2.size();
        if (size > 0 && size2 > 0) {
            if (!((AbstractC2961i) c).F) {
                c = c.d(size2 + size);
            }
            c.addAll(c2);
        }
        if (size > 0) {
            c2 = c;
        }
        Op1.e.q(obj, j, c2);
    }
}
