package X;

import java.security.cert.Certificate;
import java.util.List;

/* renamed from: X.0wZ  reason: invalid class name and case insensitive filesystem */
public final class C08430wZ {
    public final List<Certificate> A00;
    public final C08530wj A01;
    public final List<Certificate> A02;
    public final EnumC08190w9 A03;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C08430wZ)) {
            return false;
        }
        C08430wZ r5 = (C08430wZ) obj;
        C08530wj r2 = this.A01;
        C08530wj r1 = r5.A01;
        if (!C08160w5.A09(r2, r1) || !r2.equals(r1) || !this.A00.equals(r5.A00) || !this.A02.equals(r5.A02)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int i;
        EnumC08190w9 r0 = this.A03;
        if (r0 != null) {
            i = r0.hashCode();
        } else {
            i = 0;
        }
        return ((((((527 + i) * 31) + this.A01.hashCode()) * 31) + this.A00.hashCode()) * 31) + this.A02.hashCode();
    }

    public C08430wZ(EnumC08190w9 r1, C08530wj r2, List<Certificate> list, List<Certificate> list2) {
        this.A03 = r1;
        this.A01 = r2;
        this.A00 = list;
        this.A02 = list2;
    }
}
