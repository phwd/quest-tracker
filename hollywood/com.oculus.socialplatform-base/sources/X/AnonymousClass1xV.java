package X;

/* renamed from: X.1xV  reason: invalid class name */
public final class AnonymousClass1xV<T> {
    public int A00;
    public int A01;
    public int A02;
    public T[] A03;
    public final float A04 = 0.75f;

    public AnonymousClass1xV() {
        int numberOfLeadingZeros = 1 << (32 - Integer.numberOfLeadingZeros(15));
        this.A00 = numberOfLeadingZeros - 1;
        this.A01 = (int) (0.75f * ((float) numberOfLeadingZeros));
        this.A03 = (T[]) new Object[numberOfLeadingZeros];
    }
}
