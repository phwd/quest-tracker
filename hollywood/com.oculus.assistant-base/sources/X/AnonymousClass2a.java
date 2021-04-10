package X;

/* renamed from: X.2a  reason: invalid class name */
public class AnonymousClass2a extends C0489aZ {
    public static final int A00(int i) {
        if (i < 0) {
            return i;
        }
        if (i < 3) {
            return i + 1;
        }
        if (i < 1073741824) {
            return (int) ((((float) i) / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }
}
