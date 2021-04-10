package defpackage;

import java.util.Objects;

/* renamed from: bV  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1848bV extends AbstractC2619g {
    public final AbstractC2360eV F;
    public AbstractC2360eV G;
    public boolean H = false;

    public C1848bV(AbstractC2360eV eVVar) {
        this.F = eVVar;
        this.G = (AbstractC2360eV) eVVar.d(EnumC2190dV.NEW_MUTABLE_INSTANCE);
    }

    public final AbstractC2360eV a() {
        AbstractC2360eV b = b();
        if (b.i()) {
            return b;
        }
        throw new C5488wp1();
    }

    public AbstractC2360eV b() {
        if (this.H) {
            return this.G;
        }
        AbstractC2360eV eVVar = this.G;
        Objects.requireNonNull(eVVar);
        C2163dI0.f9768a.b(eVVar).c(eVVar);
        this.H = true;
        return this.G;
    }

    public final void c() {
        if (this.H) {
            AbstractC2360eV eVVar = (AbstractC2360eV) this.G.e(EnumC2190dV.NEW_MUTABLE_INSTANCE, null, null);
            C2163dI0.f9768a.b(eVVar).b(eVVar, this.G);
            this.G = eVVar;
            this.H = false;
        }
    }

    @Override // java.lang.Object
    public Object clone() {
        C1848bV bVVar = (C1848bV) this.F.e(EnumC2190dV.NEW_BUILDER, null, null);
        bVVar.d(b());
        return bVVar;
    }

    public C1848bV d(AbstractC2360eV eVVar) {
        c();
        AbstractC2360eV eVVar2 = this.G;
        C2163dI0.f9768a.b(eVVar2).b(eVVar2, eVVar);
        return this;
    }
}
