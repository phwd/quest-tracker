package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0xj  reason: invalid class name and case insensitive filesystem */
public final class C08730xj {
    public final AbstractC09150yk<Byte> A00;
    public final AbstractC09150yk<Exception> A01;
    public final AbstractC09150yk<C07760vx> A02;
    public final AbstractC09150yk<C07920wE> A03;
    public final AbstractC09150yk<EnumC08860xw> A04;
    public final boolean A05;

    public C08730xj(C07760vx r5, C07920wE r6) {
        AbstractC09150yk<C07760vx> r2;
        AbstractC09150yk<C07920wE> A002;
        C09340zG r3 = C09340zG.A00;
        if (r5.equals(C07760vx.A00)) {
            r2 = r3;
        } else {
            r2 = new AnonymousClass0yR<>(r5);
        }
        if (C07920wE.A01.equals(r6)) {
            A002 = r3;
        } else {
            A002 = AbstractC09150yk.A00(r6);
        }
        this.A05 = true;
        this.A04 = r3;
        this.A01 = r3;
        this.A00 = r3;
        this.A02 = r2;
        this.A03 = A002;
    }

    public C08730xj(EnumC08860xw r4) {
        AbstractC09150yk<EnumC08860xw> A002 = AbstractC09150yk.A00(r4);
        C09340zG r1 = C09340zG.A00;
        this.A05 = false;
        this.A04 = A002;
        this.A01 = r1;
        this.A00 = r1;
        this.A02 = r1;
        this.A03 = r1;
    }

    public C08730xj(EnumC08860xw r5, byte b) {
        AbstractC09150yk<EnumC08860xw> A002 = AbstractC09150yk.A00(r5);
        C09340zG r2 = C09340zG.A00;
        AbstractC09150yk<Byte> A003 = AbstractC09150yk.A00(Byte.valueOf(b));
        this.A05 = false;
        this.A04 = A002;
        this.A01 = r2;
        this.A00 = A003;
        this.A02 = r2;
        this.A03 = r2;
    }

    public C08730xj(EnumC08860xw r5, Exception exc) {
        AbstractC09150yk<EnumC08860xw> A002 = AbstractC09150yk.A00(r5);
        AnonymousClass0yR r2 = new AnonymousClass0yR(exc);
        C09340zG r1 = C09340zG.A00;
        this.A05 = false;
        this.A04 = A002;
        this.A01 = r2;
        this.A00 = r1;
        this.A02 = r1;
        this.A03 = r1;
    }
}
