package X;

/* renamed from: X.0jX  reason: invalid class name */
public final class AnonymousClass0jX {
    public static final C04780jW A00;
    public static final C04780jW A01;
    public static final C04780jW A02;
    public static final C04780jW A03 = new C04780jW(A00, "PEM");

    static {
        C04780jW r2 = new C04780jW("MIME", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=');
        A00 = r2;
        A01 = new C04780jW(r2, "MIME-NO-LINEFEEDS");
        StringBuffer stringBuffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
        stringBuffer.setCharAt(stringBuffer.indexOf("+"), '-');
        stringBuffer.setCharAt(stringBuffer.indexOf("/"), '_');
        A02 = new C04780jW("MODIFIED-FOR-URL", stringBuffer.toString(), false, 0);
    }
}
