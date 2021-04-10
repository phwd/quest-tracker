package defpackage;

/* renamed from: ge0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2725ge0 {

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f10009a = "0123456789ABCDEF".toCharArray();
    public final byte[] b;
    public byte[] c;
    public byte[] d;

    public C2725ge0(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.b = bArr;
        this.c = bArr2;
        this.d = null;
    }

    public static C2725ge0 a(byte[] bArr) {
        return new C2725ge0(bArr, bArr, null);
    }

    public static String c(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bArr.length; i++) {
            char[] cArr = f10009a;
            sb.append(cArr[bArr[i] >>> 4]);
            sb.append(cArr[bArr[i] & 15]);
        }
        return sb.toString();
    }

    public String b() {
        return c(this.b);
    }

    public C2725ge0(byte[] bArr, byte[] bArr2, byte[] bArr3, C2554fe0 fe0) {
        this.b = bArr;
        this.c = null;
        this.d = bArr3;
    }
}
