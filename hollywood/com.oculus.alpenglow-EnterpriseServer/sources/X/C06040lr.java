package X;

import java.security.cert.Certificate;
import java.util.List;

/* renamed from: X.0lr  reason: invalid class name and case insensitive filesystem */
public final class C06040lr {
    public final List<Certificate> A00;
    public final C06320mZ A01;
    public final List<Certificate> A02;
    public final EnumC05630kA A03;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C06040lr)) {
            return false;
        }
        C06040lr r5 = (C06040lr) obj;
        C06320mZ r2 = this.A01;
        C06320mZ r1 = r5.A01;
        if (!C05570jz.A09(r2, r1) || !r2.equals(r1) || !this.A00.equals(r5.A00) || !this.A02.equals(r5.A02)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        int i;
        EnumC05630kA r0 = this.A03;
        if (r0 != null) {
            i = r0.hashCode();
        } else {
            i = 0;
        }
        return ((((((527 + i) * 31) + this.A01.hashCode()) * 31) + this.A00.hashCode()) * 31) + this.A02.hashCode();
    }

    public C06040lr(EnumC05630kA r1, C06320mZ r2, List<Certificate> list, List<Certificate> list2) {
        this.A03 = r1;
        this.A01 = r2;
        this.A00 = list;
        this.A02 = list2;
    }
}
