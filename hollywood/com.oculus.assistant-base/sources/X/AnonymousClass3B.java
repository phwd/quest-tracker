package X;

/* renamed from: X.3B  reason: invalid class name */
public final class AnonymousClass3B implements AnonymousClass2z {
    public final Class A00;

    public AnonymousClass3B(Class cls) {
        C0514bB.A02(cls, "jClass");
        this.A00 = cls;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass3B) || !C0514bB.A05(A2X(), ((AnonymousClass3B) obj).A2X())) {
            return false;
        }
        return true;
    }

    @Override // X.AnonymousClass2z
    public final Class A2X() {
        return this.A00;
    }

    public final int hashCode() {
        return A2X().hashCode();
    }

    public final String toString() {
        return AnonymousClass08.A04(A2X().toString(), " (Kotlin reflection is not available)");
    }
}
