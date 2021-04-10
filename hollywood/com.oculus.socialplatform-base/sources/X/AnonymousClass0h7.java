package X;

import com.fasterxml.jackson.databind.JsonSerializer;

/* renamed from: X.0h7  reason: invalid class name */
public final class AnonymousClass0h7 extends AbstractC04690qz {
    public final C04680qy[] A00;

    @Override // X.AbstractC04690qz
    public final JsonSerializer<Object> A00(Class<?> cls) {
        C04680qy[] r4 = this.A00;
        for (C04680qy r1 : r4) {
            if (r1.A01 == cls) {
                return r1.A00;
            }
        }
        return null;
    }

    @Override // X.AbstractC04690qz
    public final AbstractC04690qz A01(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
        C04680qy[] r3 = this.A00;
        int length = r3.length;
        if (length == 8) {
            return this;
        }
        C04680qy[] r1 = new C04680qy[(length + 1)];
        System.arraycopy(r3, 0, r1, 0, length);
        r1[length] = new C04680qy(cls, jsonSerializer);
        return new AnonymousClass0h7(r1);
    }

    public AnonymousClass0h7(C04680qy[] r1) {
        this.A00 = r1;
    }
}
