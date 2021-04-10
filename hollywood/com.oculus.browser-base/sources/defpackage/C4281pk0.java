package defpackage;

import java.util.Objects;

/* renamed from: pk0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4281pk0 implements UO0 {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC1125Sj0 f11084a;
    public final Ap1 b;
    public final VM c;

    public C4281pk0(Ap1 ap1, VM vm, AbstractC1125Sj0 sj0) {
        this.b = ap1;
        Objects.requireNonNull(vm);
        this.c = vm;
        this.f11084a = sj0;
    }

    @Override // defpackage.UO0
    public void a(Object obj, C0112Bv bv) {
        this.c.a(obj);
        throw null;
    }

    @Override // defpackage.UO0
    public void b(Object obj, Object obj2) {
        WO0.z(this.b, obj, obj2);
    }

    @Override // defpackage.UO0
    public void c(Object obj) {
        Objects.requireNonNull(this.b);
        ((AbstractC2360eV) obj).c.f = false;
        this.c.a(obj);
        throw null;
    }

    @Override // defpackage.UO0
    public final boolean d(Object obj) {
        this.c.a(obj);
        throw null;
    }

    @Override // defpackage.UO0
    public void e(Object obj, byte[] bArr, int i, int i2, C3566la laVar) {
        AbstractC2360eV eVVar = (AbstractC2360eV) obj;
        if (eVVar.c == C5998zp1.f11772a) {
            eVVar.c = C5998zp1.b();
        }
        C5859z.a(obj);
        throw null;
    }

    @Override // defpackage.UO0
    public boolean f(Object obj, Object obj2) {
        Objects.requireNonNull(this.b);
        C5998zp1 zp1 = ((AbstractC2360eV) obj).c;
        Objects.requireNonNull(this.b);
        return zp1.equals(((AbstractC2360eV) obj2).c);
    }

    @Override // defpackage.UO0
    public int g(Object obj) {
        Objects.requireNonNull(this.b);
        C5998zp1 zp1 = ((AbstractC2360eV) obj).c;
        int i = zp1.e;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < zp1.b; i2++) {
                i += C6014zv.b(3, (AbstractC1248Uk) zp1.d[i2]) + C6014zv.w(2, zp1.c[i2] >>> 3) + (C6014zv.v(1) * 2);
            }
            zp1.e = i;
        }
        return i + 0;
    }

    @Override // defpackage.UO0
    public Object h() {
        return ((C1848bV) ((AbstractC2360eV) this.f11084a).e(EnumC2190dV.NEW_BUILDER, null, null)).b();
    }

    @Override // defpackage.UO0
    public int i(Object obj) {
        Objects.requireNonNull(this.b);
        return ((AbstractC2360eV) obj).c.hashCode();
    }
}
