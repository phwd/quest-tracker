package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jX;
import X.AnonymousClass0jg;
import X.AnonymousClass0m9;
import X.EnumC04820ji;
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
    public final String A09(AbstractC04100gp r4, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        String A0J = r4.A0J();
        if (A0J != null) {
            return A0J;
        }
        EnumC04820ji A0a = r4.A0a();
        if (A0a == EnumC04820ji.VALUE_EMBEDDED_OBJECT) {
            Object A0S = r4.A0S();
            if (A0S == null) {
                return null;
            }
            if (A0S instanceof byte[]) {
                return AnonymousClass0jX.A01.A02((byte[]) A0S, false);
            }
            return A0S.toString();
        }
        throw r5.A07(this._valueClass, A0a);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer
    public final Object A0C(AbstractC04100gp r2, AbstractC04020gg r3, AnonymousClass0m9 r4) throws IOException, AnonymousClass0jg {
        return A09(r2, r3);
    }
}
