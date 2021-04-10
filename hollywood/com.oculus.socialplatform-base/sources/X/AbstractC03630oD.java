package X;

/* renamed from: X.0oD  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC03630oD {
    public int A00;
    public int A01;

    public final String A00() {
        int i = this.A01;
        if (i == 0) {
            return "ROOT";
        }
        if (i == 1) {
            return "ARRAY";
        }
        if (i != 2) {
            return "?";
        }
        return "OBJECT";
    }
}
