package X;

/* renamed from: X.09h  reason: invalid class name */
public class AnonymousClass09h {
    public static final byte[] A04 = new byte[1792];
    public char A00;
    public int A01;
    public final int A02;
    public final CharSequence A03;

    static {
        int i = 0;
        do {
            A04[i] = Character.getDirectionality(i);
            i++;
        } while (i < 1792);
    }

    public static final byte A00(AnonymousClass09h r3) {
        CharSequence charSequence = r3.A03;
        char charAt = charSequence.charAt(r3.A01 - 1);
        r3.A00 = charAt;
        if (Character.isLowSurrogate(charAt)) {
            int codePointBefore = Character.codePointBefore(charSequence, r3.A01);
            r3.A01 -= Character.charCount(codePointBefore);
            return Character.getDirectionality(codePointBefore);
        }
        r3.A01--;
        char c = r3.A00;
        if (c < 1792) {
            return A04[c];
        }
        return Character.getDirectionality(c);
    }

    public AnonymousClass09h(CharSequence charSequence) {
        this.A03 = charSequence;
        this.A02 = charSequence.length();
    }
}
