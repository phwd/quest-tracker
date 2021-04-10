package X;

import java.util.List;

/* renamed from: X.bd  reason: case insensitive filesystem */
public final class C0540bd {
    public final List A00;
    public final C0531bU A01;
    public final List A02;
    public final EnumC0557bu A03;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0540bd)) {
            return false;
        }
        C0540bd bdVar = (C0540bd) obj;
        C0531bU bUVar = this.A01;
        C0531bU bUVar2 = bdVar.A01;
        if (!C0561by.A09(bUVar, bUVar2) || !bUVar.equals(bUVar2) || !this.A00.equals(bdVar.A00) || !this.A02.equals(bdVar.A02)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int i;
        EnumC0557bu buVar = this.A03;
        if (buVar != null) {
            i = buVar.hashCode();
        } else {
            i = 0;
        }
        return ((((((527 + i) * 31) + this.A01.hashCode()) * 31) + this.A00.hashCode()) * 31) + this.A02.hashCode();
    }

    public C0540bd(EnumC0557bu buVar, C0531bU bUVar, List list, List list2) {
        this.A03 = buVar;
        this.A01 = bUVar;
        this.A00 = list;
        this.A02 = list2;
    }
}
