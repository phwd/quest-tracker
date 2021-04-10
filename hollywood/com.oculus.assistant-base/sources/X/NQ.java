package X;

public final class NQ {
    public static final NP A00;
    public static final NP A01;
    public static final NP A02;
    public static final NP A03 = new NP(A00, "PEM", 64);

    static {
        NP np = new NP("MIME", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", true, '=', 76);
        A00 = np;
        A01 = new NP(np, "MIME-NO-LINEFEEDS", Integer.MAX_VALUE);
        StringBuffer stringBuffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
        stringBuffer.setCharAt(stringBuffer.indexOf("+"), '-');
        stringBuffer.setCharAt(stringBuffer.indexOf("/"), '_');
        A02 = new NP("MODIFIED-FOR-URL", stringBuffer.toString(), false, 0, Integer.MAX_VALUE);
    }
}
