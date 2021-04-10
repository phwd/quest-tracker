package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.22i  reason: invalid class name and case insensitive filesystem */
public final class C142022i {
    public final AnonymousClass1QO<Byte> A00;
    public final AnonymousClass1QO<Exception> A01;
    public final AnonymousClass1QO<C141521z> A02;
    public final AnonymousClass1QO<C143622y> A03;
    public final AnonymousClass1QO<EnumC142522n> A04;
    public final boolean A05;

    public C142022i(C141521z r5, C143622y r6) {
        AnonymousClass1QO<C141521z> r2;
        AnonymousClass1QO<C143622y> A002;
        AnonymousClass1QP r3 = AnonymousClass1QP.A00;
        if (r5.equals(C141521z.A00)) {
            r2 = r3;
        } else {
            r2 = new AnonymousClass1QL<>(r5);
        }
        if (C143622y.A01.equals(r6)) {
            A002 = r3;
        } else {
            A002 = AnonymousClass1QO.A00(r6);
        }
        this.A05 = true;
        this.A04 = r3;
        this.A01 = r3;
        this.A00 = r3;
        this.A02 = r2;
        this.A03 = A002;
    }

    public C142022i(EnumC142522n r4) {
        AnonymousClass1QO<EnumC142522n> A002 = AnonymousClass1QO.A00(r4);
        AnonymousClass1QP r1 = AnonymousClass1QP.A00;
        this.A05 = false;
        this.A04 = A002;
        this.A01 = r1;
        this.A00 = r1;
        this.A02 = r1;
        this.A03 = r1;
    }

    public C142022i(EnumC142522n r5, byte b) {
        AnonymousClass1QO<EnumC142522n> A002 = AnonymousClass1QO.A00(r5);
        AnonymousClass1QP r2 = AnonymousClass1QP.A00;
        AnonymousClass1QO<Byte> A003 = AnonymousClass1QO.A00(Byte.valueOf(b));
        this.A05 = false;
        this.A04 = A002;
        this.A01 = r2;
        this.A00 = A003;
        this.A02 = r2;
        this.A03 = r2;
    }

    public C142022i(EnumC142522n r5, Exception exc) {
        AnonymousClass1QO<EnumC142522n> A002 = AnonymousClass1QO.A00(r5);
        AnonymousClass1QL r2 = new AnonymousClass1QL(exc);
        AnonymousClass1QP r1 = AnonymousClass1QP.A00;
        this.A05 = false;
        this.A04 = A002;
        this.A01 = r2;
        this.A00 = r1;
        this.A02 = r1;
        this.A03 = r1;
    }
}
