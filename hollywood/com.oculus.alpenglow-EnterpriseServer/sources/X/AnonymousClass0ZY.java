package X;

import com.fasterxml.jackson.databind.JsonSerializer;

/* renamed from: X.0ZY  reason: invalid class name */
public final class AnonymousClass0ZY extends AbstractC06960oT {
    public static final AnonymousClass0ZY A00 = new AnonymousClass0ZY();

    @Override // X.AbstractC06960oT
    public final JsonSerializer<Object> A00(Class<?> cls) {
        return null;
    }

    @Override // X.AbstractC06960oT
    public final AbstractC06960oT A01(Class<?> cls, JsonSerializer<Object> jsonSerializer) {
        return new AnonymousClass0ZW(cls, jsonSerializer);
    }
}
