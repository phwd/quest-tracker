package X;

import com.fasterxml.jackson.databind.JsonSerializer;

/* renamed from: X.0h9  reason: invalid class name and case insensitive filesystem */
public final class C01820h9 extends AbstractC04690qz {
    public final JsonSerializer<Object> A00;
    public final JsonSerializer<Object> A01;
    public final Class<?> A02;
    public final Class<?> A03;

    @Override // X.AbstractC04690qz
    public final JsonSerializer<Object> A00(Class<?> cls) {
        if (cls == this.A02) {
            return this.A00;
        }
        if (cls == this.A03) {
            return this.A01;
        }
        return null;
    }

    @Override // X.AbstractC04690qz
    public final AbstractC04690qz A01(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
        return new AnonymousClass0h7(new C04680qy[]{new C04680qy(this.A02, this.A00), new C04680qy(this.A03, this.A01)});
    }

    public C01820h9(Class<?> cls, JsonSerializer<Object> jsonSerializer, Class<?> cls2, JsonSerializer<Object> jsonSerializer2) {
        this.A02 = cls;
        this.A00 = jsonSerializer;
        this.A03 = cls2;
        this.A01 = jsonSerializer2;
    }
}
