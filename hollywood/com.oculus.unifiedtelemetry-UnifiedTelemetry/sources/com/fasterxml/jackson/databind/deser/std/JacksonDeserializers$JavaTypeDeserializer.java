package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0224Wl;
import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.EnumC0470q2;
import X.NC;
import X.NQ;
import X.q0;
import java.io.IOException;

public class JacksonDeserializers$JavaTypeDeserializer extends StdScalarDeserializer<AbstractC0224Wl> {
    public static final JacksonDeserializers$JavaTypeDeserializer A00 = new JacksonDeserializers$JavaTypeDeserializer();

    public JacksonDeserializers$JavaTypeDeserializer() {
        super(AbstractC0224Wl.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AbstractC0224Wl A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        Object A0R;
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.VALUE_STRING) {
            String trim = ww.A0d().trim();
            if (trim.length() == 0) {
                A0R = A08();
            } else {
                NC nc = wn._config._base._typeFactory._parser;
                NQ nq = new NQ(trim.trim());
                AbstractC0224Wl A002 = NC.A00(nc, nq);
                if (!nq.hasMoreTokens()) {
                    return A002;
                }
                throw NC.A01(nq, "Unexpected tokens after complete type");
            }
        } else if (A0Z == EnumC0470q2.VALUE_EMBEDDED_OBJECT) {
            A0R = ww.A0R();
        } else {
            throw wn.A08(this._valueClass);
        }
        return (AbstractC0224Wl) A0R;
    }
}
