package X;

import com.fasterxml.jackson.databind.JsonSerializer;

/* renamed from: X.0h6  reason: invalid class name */
public final class AnonymousClass0h6 extends AbstractC04690qz {
    public final JsonSerializer<Object> A00;
    public final Class<?> A01;

    @Override // X.AbstractC04690qz
    public final JsonSerializer<Object> A00(Class<?> cls) {
        if (cls == this.A01) {
            return this.A00;
        }
        return null;
    }

    @Override // X.AbstractC04690qz
    public final AbstractC04690qz A01(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
        return new C01820h9(this.A01, this.A00, cls, jsonSerializer);
    }

    public AnonymousClass0h6(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
        this.A01 = cls;
        this.A00 = jsonSerializer;
    }
}
