package X;

import java.util.Locale;

/* renamed from: X.dC  reason: case insensitive filesystem */
public final class C0343dC {
    public static final ci A03 = ci.A04(":");
    public static final ci A04 = ci.A04(":status");
    public static final ci A05 = ci.A04(":authority");
    public static final ci A06 = ci.A04(":method");
    public static final ci A07 = ci.A04(":path");
    public static final ci A08 = ci.A04(":scheme");
    public final int A00;
    public final ci A01;
    public final ci A02;

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0343dC)) {
            return false;
        }
        C0343dC dCVar = (C0343dC) obj;
        if (!this.A01.equals(dCVar.A01) || !this.A02.equals(dCVar.A02)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return ((527 + this.A01.hashCode()) * 31) + this.A02.hashCode();
    }

    public final String toString() {
        return String.format(Locale.US, "%s: %s", this.A01.A0A(), this.A02.A0A());
    }

    public C0343dC(String str, String str2) {
        this(ci.A04(str), ci.A04(str2));
    }

    public C0343dC(ci ciVar, String str) {
        this(ciVar, ci.A04(str));
    }

    public C0343dC(ci ciVar, ci ciVar2) {
        this.A01 = ciVar;
        this.A02 = ciVar2;
        this.A00 = ciVar.A07() + 32 + ciVar2.A07();
    }
}
