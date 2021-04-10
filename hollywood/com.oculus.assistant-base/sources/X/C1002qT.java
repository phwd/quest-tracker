package X;

/* renamed from: X.qT  reason: case insensitive filesystem */
public final class C1002qT extends AbstractC0552bp {
    public static final C0546bj A02 = C0546bj.A00("application/octet-stream");
    public final C0546bj A00;
    public final byte[] A01;

    public C1002qT(byte[] bArr, String str) {
        C0546bj bjVar;
        this.A01 = bArr;
        if (str != null) {
            bjVar = C0546bj.A00(str);
        } else {
            bjVar = A02;
        }
        this.A00 = bjVar;
    }
}
