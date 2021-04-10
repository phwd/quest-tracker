package X;

import java.util.Arrays;

/* renamed from: X.Qm  reason: case insensitive filesystem */
public final class C0315Qm {
    public final C0307Qd A00;
    public final int A01;
    public final AbstractC0305Qb A02;
    public final String A03;

    public final boolean equals(Object obj) {
        if (obj != null) {
            if (obj != this) {
                if (obj instanceof C0315Qm) {
                    C0315Qm qm = (C0315Qm) obj;
                    if (!RY.A00(this.A00, qm.A00) || !RY.A00(this.A02, qm.A02) || !RY.A00(this.A03, qm.A03)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }

    public C0315Qm(C0307Qd qd, AbstractC0305Qb qb, String str) {
        this.A00 = qd;
        this.A02 = qb;
        this.A03 = str;
        this.A01 = Arrays.hashCode(new Object[]{qd, qb, str});
    }

    public final int hashCode() {
        return this.A01;
    }
}
