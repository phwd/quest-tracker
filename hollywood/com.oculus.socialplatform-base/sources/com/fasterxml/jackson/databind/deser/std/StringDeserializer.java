package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04520qa;
import X.AnonymousClass0o3;
import X.C03620oC;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class StringDeserializer extends StdScalarDeserializer<String> {
    public static final StringDeserializer A00 = new StringDeserializer();
    public static final long serialVersionUID = 1;

    public StringDeserializer() {
        super(String.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final String A0A(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        String A0P = r4.A0P();
        if (A0P != null) {
            return A0P;
        }
        EnumC03640oE A0i = r4.A0i();
        if (A0i == EnumC03640oE.VALUE_EMBEDDED_OBJECT) {
            Object A0Z = r4.A0Z();
            if (A0Z == null) {
                return null;
            }
            if (A0Z instanceof byte[]) {
                return AnonymousClass0o3.A01.A02((byte[]) A0Z, false);
            }
            return A0Z.toString();
        }
        throw r5.A0C(this._valueClass, A0i);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer
    public final Object A0B(AbstractC02280iQ r2, AbstractC02210iH r3, AbstractC04520qa r4) throws IOException, C03620oC {
        return A0A(r2, r3);
    }
}
