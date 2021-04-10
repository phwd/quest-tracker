package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Rh  reason: invalid class name */
public final class AnonymousClass0Rh implements Cloneable {
    public static final AnonymousClass0Rh A04;
    public static final AnonymousClass0Rh A05 = new AnonymousClass0Rh();
    public static final AnonymousClass0Rh A06;
    public AnonymousClass0Rj A00 = AnonymousClass0Rj.UNKNOWN;
    public boolean A01 = false;
    public boolean A02 = false;
    public boolean A03 = false;

    static {
        AnonymousClass0Rh A002 = A00(new AnonymousClass0Rh());
        A002.A01 = true;
        A04 = A002;
        AnonymousClass0Rh A003 = A00(new AnonymousClass0Rh());
        A003.A03 = true;
        A06 = A003;
    }

    public static AnonymousClass0Rh A00(AnonymousClass0Rh r1) {
        if (r1 != A05 && r1 != A04 && r1 != A06) {
            return r1;
        }
        try {
            return (AnonymousClass0Rh) r1.clone();
        } catch (CloneNotSupportedException unused) {
            throw new RuntimeException("");
        }
    }

    public final AnonymousClass0Rh A01() {
        AnonymousClass0Rh A002 = A00(this);
        A002.A02 = true;
        return A002;
    }
}
