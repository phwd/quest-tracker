package X;

import com.facebook.assistant.oacr.OacrConstants;

/* renamed from: X.Fs  reason: case insensitive filesystem */
public final class C0167Fs implements Cloneable {
    public static final C0167Fs A04;
    public static final C0167Fs A05 = new C0167Fs();
    public static final C0167Fs A06;
    public EnumC0169Fu A00 = EnumC0169Fu.UNKNOWN;
    public boolean A01 = false;
    public boolean A02 = false;
    public boolean A03 = false;

    static {
        C0167Fs A002 = A00(new C0167Fs());
        A002.A01 = true;
        A04 = A002;
        C0167Fs A003 = A00(new C0167Fs());
        A003.A03 = true;
        A06 = A003;
    }

    public static C0167Fs A00(C0167Fs fs) {
        if (fs != A05 && fs != A04 && fs != A06) {
            return fs;
        }
        try {
            return (C0167Fs) fs.clone();
        } catch (CloneNotSupportedException unused) {
            throw new RuntimeException(OacrConstants.AUTO_SPEECH_DOMAIN);
        }
    }
}
