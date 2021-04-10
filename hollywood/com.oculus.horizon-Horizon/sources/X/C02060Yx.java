package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Yx  reason: invalid class name and case insensitive filesystem */
public final class C02060Yx {
    public final AnonymousClass0W8<Byte> A00;
    public final AnonymousClass0W8<Exception> A01;
    public final AnonymousClass0W8<AnonymousClass0Y2> A02;
    public final AnonymousClass0W8<AnonymousClass0Y3> A03;
    public final AnonymousClass0W8<AnonymousClass0Yy> A04;
    public final boolean A05;

    public C02060Yx(AnonymousClass0Y2 r5, AnonymousClass0Y3 r6) {
        AnonymousClass0W8<AnonymousClass0Y2> r2;
        AnonymousClass0W8<AnonymousClass0Y3> A002;
        C06530na r3 = C06530na.A00;
        if (r5.equals(AnonymousClass0Y2.A00)) {
            r2 = r3;
        } else {
            r2 = new AnonymousClass0nZ<>(r5);
        }
        if (AnonymousClass0Y3.A01.equals(r6)) {
            A002 = r3;
        } else {
            A002 = AnonymousClass0W8.A00(r6);
        }
        this.A05 = true;
        this.A04 = r3;
        this.A01 = r3;
        this.A00 = r3;
        this.A02 = r2;
        this.A03 = A002;
    }

    public C02060Yx(AnonymousClass0Yy r4) {
        AnonymousClass0W8<AnonymousClass0Yy> A002 = AnonymousClass0W8.A00(r4);
        C06530na r1 = C06530na.A00;
        this.A05 = false;
        this.A04 = A002;
        this.A01 = r1;
        this.A00 = r1;
        this.A02 = r1;
        this.A03 = r1;
    }

    public C02060Yx(AnonymousClass0Yy r5, byte b) {
        AnonymousClass0W8<AnonymousClass0Yy> A002 = AnonymousClass0W8.A00(r5);
        C06530na r2 = C06530na.A00;
        AnonymousClass0W8<Byte> A003 = AnonymousClass0W8.A00(Byte.valueOf(b));
        this.A05 = false;
        this.A04 = A002;
        this.A01 = r2;
        this.A00 = A003;
        this.A02 = r2;
        this.A03 = r2;
    }

    public C02060Yx(AnonymousClass0Yy r5, Exception exc) {
        AnonymousClass0W8<AnonymousClass0Yy> A002 = AnonymousClass0W8.A00(r5);
        AnonymousClass0nZ r2 = new AnonymousClass0nZ(exc);
        C06530na r1 = C06530na.A00;
        this.A05 = false;
        this.A04 = A002;
        this.A01 = r2;
        this.A00 = r1;
        this.A02 = r1;
        this.A03 = r1;
    }
}
