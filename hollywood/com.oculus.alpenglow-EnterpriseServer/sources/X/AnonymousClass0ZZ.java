package X;

import com.fasterxml.jackson.databind.JsonSerializer;

/* renamed from: X.0ZZ  reason: invalid class name */
public final class AnonymousClass0ZZ extends AbstractC06960oT {
    public final JsonSerializer<Object> A00;
    public final JsonSerializer<Object> A01;
    public final Class<?> A02;
    public final Class<?> A03;

    @Override // X.AbstractC06960oT
    public final JsonSerializer<Object> A00(Class<?> cls) {
        if (cls == this.A02) {
            return this.A00;
        }
        if (cls == this.A03) {
            return this.A01;
        }
        return null;
    }

    @Override // X.AbstractC06960oT
    public final AbstractC06960oT A01(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
        return new AnonymousClass0ZX(new C06950oS[]{new C06950oS(this.A02, this.A00), new C06950oS(this.A03, this.A01)});
    }

    public AnonymousClass0ZZ(Class<?> cls, JsonSerializer<Object> jsonSerializer, Class<?> cls2, JsonSerializer<Object> jsonSerializer2) {
        this.A02 = cls;
        this.A00 = jsonSerializer;
        this.A03 = cls2;
        this.A01 = jsonSerializer2;
    }
}
