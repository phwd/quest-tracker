package X;

/* renamed from: X.0BZ  reason: invalid class name */
public class AnonymousClass0BZ {
    public float A00;
    public float A01;
    public float A02;
    public int A03;
    public int A04;
    public int A05;
    public long A06 = 0;
    public long A07 = Long.MIN_VALUE;
    public long A08 = -1;

    public static float A00(AnonymousClass0BZ r9, long j) {
        long j2 = r9.A07;
        if (j < j2) {
            return 0.0f;
        }
        long j3 = r9.A08;
        if (j3 < 0 || j < j3) {
            float f = ((float) (j - j2)) / ((float) r9.A05);
            if (f > 1.0f) {
                f = 1.0f;
            } else if (f < 0.0f) {
                f = 0.0f;
            }
            return f * 0.5f;
        }
        float f2 = r9.A00;
        float f3 = 1.0f - f2;
        float f4 = ((float) (j - j3)) / ((float) r9.A03);
        if (f4 > 1.0f) {
            f4 = 1.0f;
        } else if (f4 < 0.0f) {
            f4 = 0.0f;
        }
        return f3 + (f2 * f4);
    }
}
