package X;

/* renamed from: X.0c7  reason: invalid class name and case insensitive filesystem */
public final class C03100c7 extends AbstractC08820ye {
    public final C09000zB<String, AbstractC08820ye> A00 = new C09000zB<>();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C03100c7) || !((C03100c7) obj).A00.equals(this.A00)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }
}
