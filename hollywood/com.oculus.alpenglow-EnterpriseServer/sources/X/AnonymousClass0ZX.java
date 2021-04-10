package X;

import com.fasterxml.jackson.databind.JsonSerializer;

/* renamed from: X.0ZX  reason: invalid class name */
public final class AnonymousClass0ZX extends AbstractC06960oT {
    public final C06950oS[] A00;

    @Override // X.AbstractC06960oT
    public final JsonSerializer<Object> A00(Class<?> cls) {
        C06950oS[] r4 = this.A00;
        for (C06950oS r1 : r4) {
            if (r1.A01 == cls) {
                return r1.A00;
            }
        }
        return null;
    }

    @Override // X.AbstractC06960oT
    public final AbstractC06960oT A01(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
        C06950oS[] r3 = this.A00;
        int length = r3.length;
        if (length == 8) {
            return this;
        }
        C06950oS[] r1 = new C06950oS[(length + 1)];
        System.arraycopy(r3, 0, r1, 0, length);
        r1[length] = new C06950oS(cls, jsonSerializer);
        return new AnonymousClass0ZX(r1);
    }

    public AnonymousClass0ZX(C06950oS[] r1) {
        this.A00 = r1;
    }
}
