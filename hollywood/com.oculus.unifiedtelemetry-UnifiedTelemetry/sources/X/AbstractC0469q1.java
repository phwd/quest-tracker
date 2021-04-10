package X;

/* renamed from: X.q1  reason: case insensitive filesystem */
public abstract class AbstractC0469q1 {
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
