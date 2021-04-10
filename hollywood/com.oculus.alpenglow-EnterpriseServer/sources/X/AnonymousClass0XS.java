package X;

/* renamed from: X.0XS  reason: invalid class name */
public final class AnonymousClass0XS extends AnonymousClass0AU {
    public final C01100Dk<String, AnonymousClass0AU> A00 = new C01100Dk<>();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AnonymousClass0XS) || !((AnonymousClass0XS) obj).A00.equals(this.A00)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }
}
