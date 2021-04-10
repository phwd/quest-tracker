package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Sq  reason: invalid class name */
public final class AnonymousClass0Sq implements Cloneable {
    public static final AnonymousClass0Sq A03;
    public static final AnonymousClass0Sq A04 = new AnonymousClass0Sq();
    public static final AnonymousClass0Sq A05;
    public boolean A00 = false;
    public boolean A01 = false;
    public boolean A02 = false;

    static {
        AnonymousClass0Sq A002 = A00(new AnonymousClass0Sq());
        A002.A00 = true;
        A03 = A002;
        AnonymousClass0Sq A003 = A00(new AnonymousClass0Sq());
        A003.A02 = true;
        A05 = A003;
    }

    public static AnonymousClass0Sq A00(AnonymousClass0Sq r1) {
        if (r1 != A04 && r1 != A03 && r1 != A05) {
            return r1;
        }
        try {
            return (AnonymousClass0Sq) r1.clone();
        } catch (CloneNotSupportedException unused) {
            throw new RuntimeException("");
        }
    }
}
