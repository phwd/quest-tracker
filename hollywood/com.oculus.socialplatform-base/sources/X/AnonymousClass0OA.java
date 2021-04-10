package X;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.datatype.guava.ser.GuavaOptionalSerializer;
import com.fasterxml.jackson.datatype.guava.ser.MultimapSerializer;
import com.google.common.base.Optional;

/* renamed from: X.0OA  reason: invalid class name */
public final class AnonymousClass0OA extends C01830hA {
    @Override // X.AbstractC04640qs, X.C01830hA
    public final JsonSerializer<?> A38(AnonymousClass0HM r3, AnonymousClass0C8 r4, AbstractC04010oz r5, JsonSerializer<Object> jsonSerializer, AbstractC04550qd r7, JsonSerializer<Object> jsonSerializer2) {
        if (AbstractC05440vj.class.isAssignableFrom(r4._class)) {
            return new MultimapSerializer(r4, jsonSerializer, r7, jsonSerializer2);
        }
        return null;
    }

    @Override // X.AbstractC04640qs, X.C01830hA
    public final JsonSerializer<?> A3B(AnonymousClass0HM r3, AbstractC02190iF r4, AbstractC04010oz r5) {
        if (Optional.class.isAssignableFrom(r4._class)) {
            return new GuavaOptionalSerializer(r4);
        }
        return super.A3B(r3, r4, r5);
    }
}
