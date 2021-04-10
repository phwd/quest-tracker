package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.lang.reflect.Method;

/* renamed from: X.0b  reason: invalid class name and case insensitive filesystem */
public final class C00000b {
    public final JsonDeserializer<Object> A00;
    public final Method A01;

    public final Object A00(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        if (((B3) rn).A00 == AnonymousClass9p.VALUE_NULL) {
            return null;
        }
        return this.A00.A03(rn, rd);
    }

    public final String toString() {
        return AnonymousClass06.A04("[any property on class ", this.A01.getDeclaringClass().getName(), "]");
    }
}
