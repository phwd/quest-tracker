package X;

/* renamed from: X.aE  reason: case insensitive filesystem */
public final class C0469aE implements Comparable {
    public static final C0468aD A01 = new C0468aD();
    public final byte A00;

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
        byte b = ((C0469aE) obj).A00;
        int i = this.A00 & 255;
        int i2 = b & 255;
        if (i < i2) {
            return -1;
        }
        if (i == i2) {
            return 0;
        }
        return 1;
    }

    public final boolean equals(Object obj) {
        byte b = this.A00;
        if (!(obj instanceof C0469aE) || b != ((C0469aE) obj).A00) {
            return false;
        }
        return true;
    }

    public final String toString() {
        return String.valueOf(this.A00 & 255);
    }

    public /* synthetic */ C0469aE(byte b) {
        this.A00 = b;
    }

    public final int hashCode() {
        return this.A00;
    }
}
