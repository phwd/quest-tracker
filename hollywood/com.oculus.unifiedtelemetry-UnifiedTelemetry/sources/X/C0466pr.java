package X;

/* renamed from: X.pr  reason: case insensitive filesystem */
public final class C0466pr {
    public static final C0465pq A00;
    public static final C0465pq A01;
    public static final C0465pq A02;
    public static final C0465pq A03 = new C0465pq(A00, "PEM");

    static {
        C0465pq pqVar = new C0465pq("MIME", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=');
        A00 = pqVar;
        A01 = new C0465pq(pqVar, "MIME-NO-LINEFEEDS");
        StringBuffer stringBuffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
        stringBuffer.setCharAt(stringBuffer.indexOf("+"), '-');
        stringBuffer.setCharAt(stringBuffer.indexOf("/"), '_');
        A02 = new C0465pq("MODIFIED-FOR-URL", stringBuffer.toString(), false, 0);
    }
}
