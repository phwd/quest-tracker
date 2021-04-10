package X;

import com.fasterxml.jackson.databind.JsonSerializer;

/* renamed from: X.0ZW  reason: invalid class name */
public final class AnonymousClass0ZW extends AbstractC06960oT {
    public final JsonSerializer<Object> A00;
    public final Class<?> A01;

    @Override // X.AbstractC06960oT
    public final JsonSerializer<Object> A00(Class<?> cls) {
        if (cls == this.A01) {
            return this.A00;
        }
        return null;
    }

    @Override // X.AbstractC06960oT
    public final AbstractC06960oT A01(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
        return new AnonymousClass0ZZ(this.A01, this.A00, cls, jsonSerializer);
    }

    public AnonymousClass0ZW(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
        this.A01 = cls;
        this.A00 = jsonSerializer;
    }
}
