package X;

/* renamed from: X.0lV  reason: invalid class name and case insensitive filesystem */
public final class C05840lV {
    public static final C05830lU A00;
    public static final C05830lU A01;
    public static final C05830lU A02;
    public static final C05830lU A03 = new C05830lU(A00, "PEM", 64);

    static {
        C05830lU r2 = new C05830lU("MIME", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);
        A00 = r2;
        A01 = new C05830lU(r2, "MIME-NO-LINEFEEDS", Integer.MAX_VALUE);
        StringBuffer stringBuffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
        stringBuffer.setCharAt(stringBuffer.indexOf("+"), '-');
        stringBuffer.setCharAt(stringBuffer.indexOf("/"), '_');
        A02 = new C05830lU("MODIFIED-FOR-URL", stringBuffer.toString(), false, 0, Integer.MAX_VALUE);
    }
}
