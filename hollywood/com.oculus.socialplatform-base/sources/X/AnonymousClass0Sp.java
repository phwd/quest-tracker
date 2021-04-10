package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Sp  reason: invalid class name */
public final class AnonymousClass0Sp implements Cloneable {
    public static final AnonymousClass0Sp A02 = A00(new AnonymousClass0Sp());
    public static final AnonymousClass0Sp A03 = new AnonymousClass0Sp();
    public static final AnonymousClass0Sp A04 = A00(new AnonymousClass0Sp());
    public AnonymousClass0Sq A00 = AnonymousClass0Sq.UNKNOWN;
    public boolean A01 = false;

    public static AnonymousClass0Sp A00(AnonymousClass0Sp r1) {
        if (r1 != A03 && r1 != A02 && r1 != A04) {
            return r1;
        }
        try {
            return (AnonymousClass0Sp) r1.clone();
        } catch (CloneNotSupportedException unused) {
            throw new RuntimeException("");
        }
    }
}
