package X;

import com.fasterxml.jackson.databind.JsonSerializer;

/* renamed from: X.0qw  reason: invalid class name and case insensitive filesystem */
public final class C04660qw {
    public final AbstractC03600nz<?> A00;
    public final C02270iP A01;
    public final AbstractC02190iF A02;
    public final JsonSerializer<Object> A03;
    public final boolean A04;

    public static C04660qw A00(AbstractC02190iF r6, String str, AbstractC03600nz<?> r8, boolean z) {
        C02270iP r2;
        if (str == null) {
            r2 = null;
        } else {
            r2 = new C02270iP(str);
        }
        return new C04660qw(r6, r2, r8, null, z);
    }

    public C04660qw(AbstractC02190iF r1, C02270iP r2, AbstractC03600nz<?> r3, JsonSerializer<?> jsonSerializer, boolean z) {
        this.A02 = r1;
        this.A01 = r2;
        this.A00 = r3;
        this.A03 = jsonSerializer;
        this.A04 = z;
    }
}
