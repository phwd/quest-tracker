package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.C0466pr;
import X.EnumC0470q2;
import X.V4;
import X.q0;
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
    public final String A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        String A0I = ww.A0I();
        if (A0I != null) {
            return A0I;
        }
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_EMBEDDED_OBJECT) {
            Object A0R = ww.A0R();
            if (A0R == null) {
                return null;
            }
            if (A0R instanceof byte[]) {
                return C0466pr.A01.A02((byte[]) A0R, false);
            }
            return A0R.toString();
        }
        throw wn.A09(this._valueClass, A0Z);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer
    public final Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        return A09(ww, wn);
    }
}
