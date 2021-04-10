package X;

/* renamed from: X.aO  reason: case insensitive filesystem */
public final class C0479aO implements Comparable {
    public static final C0478aN A01 = new C0478aN();
    public final short A00;

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        short s = ((C0479aO) obj).A00;
        int i = this.A00 & 65535;
        int i2 = s & 65535;
        if (i < i2) {
            return -1;
        }
        if (i == i2) {
            return 0;
        }
        return 1;
    }

    public final boolean equals(Object obj) {
        short s = this.A00;
        if (!(obj instanceof C0479aO) || s != ((C0479aO) obj).A00) {
            return false;
        }
        return true;
    }

    public final String toString() {
        return String.valueOf(this.A00 & 65535);
    }

    public /* synthetic */ C0479aO(short s) {
        this.A00 = s;
    }

    public final int hashCode() {
        return this.A00;
    }
}
