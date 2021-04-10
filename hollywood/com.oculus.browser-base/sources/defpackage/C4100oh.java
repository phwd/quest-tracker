package defpackage;

/* renamed from: oh  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4100oh {

    /* renamed from: a  reason: collision with root package name */
    public static final byte[] f10568a = new byte[1792];
    public final CharSequence b;
    public final int c;
    public int d;
    public char e;

    static {
        for (int i = 0; i < 1792; i++) {
            f10568a[i] = Character.getDirectionality(i);
        }
    }

    public C4100oh(CharSequence charSequence, boolean z) {
        this.b = charSequence;
        this.c = charSequence.length();
    }

    public byte a() {
        char charAt = this.b.charAt(this.d - 1);
        this.e = charAt;
        if (Character.isLowSurrogate(charAt)) {
            int codePointBefore = Character.codePointBefore(this.b, this.d);
            this.d -= Character.charCount(codePointBefore);
            return Character.getDirectionality(codePointBefore);
        }
        this.d--;
        char c2 = this.e;
        return c2 < 1792 ? f10568a[c2] : Character.getDirectionality(c2);
    }
}
