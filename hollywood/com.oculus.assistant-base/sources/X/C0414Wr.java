package X;

/* renamed from: X.Wr  reason: case insensitive filesystem */
public final class C0414Wr {
    public final int A00;
    public final boolean A01;

    public final String toString() {
        String str;
        String str2;
        if (this.A01) {
            str = "available";
        } else {
            str = "unavailable";
        }
        int i = this.A00;
        if (i == 1) {
            str2 = "In Shell";
        } else if (i == 2) {
            str2 = "In Pause";
        } else if (i != 3) {
            str2 = "Unknown";
        } else {
            str2 = "In Game";
        }
        return AnonymousClass08.A06("Panel is ", str, " ", str2);
    }

    public C0414Wr(int i, boolean z) {
        this.A00 = i;
        this.A01 = z;
    }
}
