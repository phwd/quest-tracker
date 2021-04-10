package X;

import java.security.cert.Certificate;
import java.util.List;

/* renamed from: X.du  reason: case insensitive filesystem */
public final class C0371du {
    public final List<Certificate> A00;
    public final e3 A01;
    public final List<Certificate> A02;
    public final EnumC0356dd A03;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0371du)) {
            return false;
        }
        C0371du duVar = (C0371du) obj;
        e3 e3Var = this.A01;
        e3 e3Var2 = duVar.A01;
        if (!dZ.A09(e3Var, e3Var2) || !e3Var.equals(e3Var2) || !this.A00.equals(duVar.A00) || !this.A02.equals(duVar.A02)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int i;
        EnumC0356dd ddVar = this.A03;
        if (ddVar != null) {
            i = ddVar.hashCode();
        } else {
            i = 0;
        }
        return ((((((527 + i) * 31) + this.A01.hashCode()) * 31) + this.A00.hashCode()) * 31) + this.A02.hashCode();
    }

    public C0371du(EnumC0356dd ddVar, e3 e3Var, List<Certificate> list, List<Certificate> list2) {
        this.A03 = ddVar;
        this.A01 = e3Var;
        this.A00 = list;
        this.A02 = list2;
    }
}
