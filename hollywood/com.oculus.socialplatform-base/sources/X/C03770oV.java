package X;

import java.lang.ref.SoftReference;

/* renamed from: X.0oV  reason: invalid class name and case insensitive filesystem */
public final class C03770oV {
    public static final ThreadLocal<SoftReference<C03770oV>> A02 = new ThreadLocal<>();
    public static final char[] A03 = C03730oR.A02();
    public static final byte[] A04 = C03730oR.A01();
    public C03970ou A00;
    public final char[] A01;

    public C03770oV() {
        char[] cArr = new char[6];
        this.A01 = cArr;
        cArr[0] = '\\';
        cArr[2] = '0';
        cArr[3] = '0';
    }
}
