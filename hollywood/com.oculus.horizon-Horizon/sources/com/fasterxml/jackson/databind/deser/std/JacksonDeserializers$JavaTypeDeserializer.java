package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04000gb;
import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import X.C06260mn;
import X.C06270mo;
import X.EnumC04820ji;
import java.io.IOException;

public class JacksonDeserializers$JavaTypeDeserializer extends StdScalarDeserializer<AbstractC04000gb> {
    public static final JacksonDeserializers$JavaTypeDeserializer A00 = new JacksonDeserializers$JavaTypeDeserializer();

    public JacksonDeserializers$JavaTypeDeserializer() {
        super(AbstractC04000gb.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AbstractC04000gb A09(AbstractC04100gp r4, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        Object A0S;
        EnumC04820ji A0a = r4.A0a();
        if (A0a == EnumC04820ji.VALUE_STRING) {
            String trim = r4.A0e().trim();
            if (trim.length() == 0) {
                A0S = A08();
            } else {
                C06270mo r2 = r5._config._base._typeFactory._parser;
                C06260mn r1 = new C06260mn(trim.trim());
                AbstractC04000gb A002 = C06270mo.A00(r2, r1);
                if (!r1.hasMoreTokens()) {
                    return A002;
                }
                throw C06270mo.A01(r1, "Unexpected tokens after complete type");
            }
        } else if (A0a == EnumC04820ji.VALUE_EMBEDDED_OBJECT) {
            A0S = r4.A0S();
        } else {
            throw null;
        }
        return (AbstractC04000gb) A0S;
    }
}
