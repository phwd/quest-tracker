package X;

import com.fasterxml.jackson.databind.JsonSerializer;

/* renamed from: X.0oQ  reason: invalid class name and case insensitive filesystem */
public final class C06930oQ {
    public final AnonymousClass0lR<?> A00;
    public final C02620aS A01;
    public final AnonymousClass0aI A02;
    public final JsonSerializer<Object> A03;
    public final boolean A04;

    public static C06930oQ A00(AnonymousClass0aI r6, String str, AnonymousClass0lR<?> r8, boolean z) {
        C02620aS r2;
        if (str == null) {
            r2 = null;
        } else {
            r2 = new C02620aS(str);
        }
        return new C06930oQ(r6, r2, r8, null, z);
    }

    public C06930oQ(AnonymousClass0aI r1, C02620aS r2, AnonymousClass0lR<?> r3, JsonSerializer<?> jsonSerializer, boolean z) {
        this.A02 = r1;
        this.A01 = r2;
        this.A00 = r3;
        this.A03 = jsonSerializer;
        this.A04 = z;
    }
}
