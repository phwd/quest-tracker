package X;

import com.fasterxml.jackson.databind.JsonSerializer;

/* renamed from: X.0h8  reason: invalid class name and case insensitive filesystem */
public final class C01810h8 extends AbstractC04690qz {
    public static final C01810h8 A00 = new C01810h8();

    @Override // X.AbstractC04690qz
    public final JsonSerializer<Object> A00(Class<?> cls) {
        return null;
    }

    @Override // X.AbstractC04690qz
    public final AbstractC04690qz A01(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
        return new AnonymousClass0h6(cls, jsonSerializer);
    }
}
