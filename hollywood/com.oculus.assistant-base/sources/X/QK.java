package X;

public final class QK {
    public static final NX[] A03 = new NX[16];
    public long A00;
    public QK A01;
    public final Object[] A02 = new Object[16];

    static {
        NX[] values = NX.values();
        System.arraycopy(values, 1, A03, 1, Math.min(15, values.length - 1));
    }
}
