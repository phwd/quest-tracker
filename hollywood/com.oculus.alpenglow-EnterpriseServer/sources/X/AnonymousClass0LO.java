package X;

import java.io.IOException;

/* renamed from: X.0LO  reason: invalid class name */
public abstract class AnonymousClass0LO extends AnonymousClass0aT {
    public EnumC05930lf A00;

    public AnonymousClass0LO(int i) {
        super(0);
    }

    @Override // X.AnonymousClass0aT
    public abstract int A07() throws IOException, C02630aU;

    @Override // X.AnonymousClass0aT
    public abstract int A08() throws IOException, C02630aU;

    @Override // X.AnonymousClass0aT
    public abstract String A0O() throws IOException, C02630aU;

    @Override // X.AnonymousClass0aT
    public abstract String A0P() throws IOException, C02630aU;

    @Override // X.AnonymousClass0aT
    public abstract boolean A0X();

    @Override // X.AnonymousClass0aT
    public abstract byte[] A0Y(C05830lU v) throws IOException, C02630aU;

    @Override // X.AnonymousClass0aT
    public abstract char[] A0Z() throws IOException, C02630aU;

    @Override // X.AnonymousClass0aT
    public abstract EnumC05930lf A0a() throws IOException, C02630aU;

    public abstract void A0b() throws C02630aU;

    @Override // X.AnonymousClass0aT, java.io.Closeable, java.lang.AutoCloseable
    public abstract void close() throws IOException;

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        if (r3 < r4) goto L_0x0045;
     */
    @Override // X.AnonymousClass0aT
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int A09(int r7) throws java.io.IOException, X.C02630aU {
        /*
        // Method dump skipped, instructions count: 140
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0LO.A09(int):int");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        if (r3 < r4) goto L_0x0045;
     */
    @Override // X.AnonymousClass0aT
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long A0C(long r7) throws java.io.IOException, X.C02630aU {
        /*
        // Method dump skipped, instructions count: 142
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0LO.A0C(long):long");
    }

    @Override // X.AnonymousClass0aT
    public final AnonymousClass0aT A0F() throws IOException, C02630aU {
        EnumC05930lf r1 = this.A00;
        if (r1 != EnumC05930lf.START_OBJECT && r1 != EnumC05930lf.START_ARRAY) {
            return this;
        }
        int i = 1;
        while (true) {
            EnumC05930lf A0a = A0a();
            if (A0a == null) {
                A0b();
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
            int i2 = C06010ln.A00[A0a.ordinal()];
            if (i2 == 1 || i2 == 2) {
                i++;
            } else if ((i2 == 3 || i2 == 4) && i - 1 == 0) {
                return this;
            }
        }
    }

    @Override // X.AnonymousClass0aT
    public final EnumC05930lf A0G() {
        return this.A00;
    }

    @Override // X.AnonymousClass0aT
    public final String A0Q(String str) throws IOException, C02630aU {
        EnumC05930lf r1 = this.A00;
        if (r1 == EnumC05930lf.VALUE_STRING || (r1 != null && r1 != EnumC05930lf.VALUE_NULL && r1.isScalarValue())) {
            return A0P();
        }
        return str;
    }

    @Override // X.AnonymousClass0aT
    public final void A0U() {
        if (this.A00 != null) {
            this.A00 = null;
        }
    }

    @Override // X.AnonymousClass0aT
    public final boolean A0W() {
        if (this.A00 != null) {
            return true;
        }
        return false;
    }

    @Override // X.AnonymousClass0aT
    public final EnumC05930lf A0H() throws IOException, C02630aU {
        EnumC05930lf A0a = A0a();
        if (A0a == EnumC05930lf.FIELD_NAME) {
            return A0a();
        }
        return A0a;
    }
}
