package X;

import java.util.Locale;

/* renamed from: X.cJ  reason: case insensitive filesystem */
public final class C0578cJ {
    public static final C0603cm A03 = C0603cm.A02(":");
    public static final C0603cm A04 = C0603cm.A02(":status");
    public static final C0603cm A05 = C0603cm.A02(":authority");
    public static final C0603cm A06 = C0603cm.A02(":method");
    public static final C0603cm A07 = C0603cm.A02(":path");
    public static final C0603cm A08 = C0603cm.A02(":scheme");
    public final int A00;
    public final C0603cm A01;
    public final C0603cm A02;

    public final String toString() {
        return String.format(Locale.US, "%s: %s", this.A01.A08(), this.A02.A08());
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0578cJ)) {
            return false;
        }
        C0578cJ cJVar = (C0578cJ) obj;
        if (!this.A01.equals(cJVar.A01) || !this.A02.equals(cJVar.A02)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return ((527 + this.A01.hashCode()) * 31) + this.A02.hashCode();
    }

    public C0578cJ(String str, String str2) {
        this(C0603cm.A02(str), C0603cm.A02(str2));
    }

    public C0578cJ(C0603cm cmVar, String str) {
        this(cmVar, C0603cm.A02(str));
    }

    public C0578cJ(C0603cm cmVar, C0603cm cmVar2) {
        this.A01 = cmVar;
        this.A02 = cmVar2;
        this.A00 = cmVar.A05() + 32 + cmVar2.A05();
    }
}
