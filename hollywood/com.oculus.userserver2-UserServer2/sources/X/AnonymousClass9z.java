package X;

/* renamed from: X.9z  reason: invalid class name */
public final class AnonymousClass9z {
    public static final A0 A00;
    public static final A0 A01;
    public static final A0 A02;
    public static final A0 A03 = new A0(A00, "PEM");

    static {
        A0 a0 = new A0("MIME", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=');
        A00 = a0;
        A01 = new A0(a0, "MIME-NO-LINEFEEDS");
        StringBuffer stringBuffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
        stringBuffer.setCharAt(stringBuffer.indexOf("+"), '-');
        stringBuffer.setCharAt(stringBuffer.indexOf("/"), '_');
        A02 = new A0("MODIFIED-FOR-URL", stringBuffer.toString(), false, 0);
    }
}
