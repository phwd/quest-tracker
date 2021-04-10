package X;

/* renamed from: X.0o3  reason: invalid class name */
public final class AnonymousClass0o3 {
    public static final AnonymousClass0o2 A00;
    public static final AnonymousClass0o2 A01;
    public static final AnonymousClass0o2 A02;
    public static final AnonymousClass0o2 A03 = new AnonymousClass0o2(A00, "PEM", 64);

    static {
        AnonymousClass0o2 r2 = new AnonymousClass0o2("MIME", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);
        A00 = r2;
        A01 = new AnonymousClass0o2(r2, "MIME-NO-LINEFEEDS", Integer.MAX_VALUE);
        StringBuffer stringBuffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
        stringBuffer.setCharAt(stringBuffer.indexOf("+"), '-');
        stringBuffer.setCharAt(stringBuffer.indexOf("/"), '_');
        A02 = new AnonymousClass0o2("MODIFIED-FOR-URL", stringBuffer.toString(), false, 0, Integer.MAX_VALUE);
    }
}
