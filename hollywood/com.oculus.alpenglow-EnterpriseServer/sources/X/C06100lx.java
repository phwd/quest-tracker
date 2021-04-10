package X;

import java.lang.ref.SoftReference;

/* renamed from: X.0lx  reason: invalid class name and case insensitive filesystem */
public final class C06100lx {
    public static final ThreadLocal<SoftReference<C06100lx>> A02 = new ThreadLocal<>();
    public static final char[] A03 = C06060lt.A02();
    public static final byte[] A04 = C06060lt.A01();
    public C06220mM A00;
    public final char[] A01;

    public C06100lx() {
        char[] cArr = new char[6];
        this.A01 = cArr;
        cArr[0] = '\\';
        cArr[2] = '0';
        cArr[3] = '0';
    }
}
