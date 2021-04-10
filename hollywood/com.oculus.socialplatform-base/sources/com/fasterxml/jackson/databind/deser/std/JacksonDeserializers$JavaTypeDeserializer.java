package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02190iF;
import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.C03620oC;
import X.C04740rB;
import X.C04750rC;
import X.EnumC03640oE;
import java.io.IOException;

public class JacksonDeserializers$JavaTypeDeserializer extends StdScalarDeserializer<AbstractC02190iF> {
    public static final JacksonDeserializers$JavaTypeDeserializer A00 = new JacksonDeserializers$JavaTypeDeserializer();

    public JacksonDeserializers$JavaTypeDeserializer() {
        super(AbstractC02190iF.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AbstractC02190iF A0A(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        Object A0Z;
        EnumC03640oE A0i = r4.A0i();
        if (A0i == EnumC03640oE.VALUE_STRING) {
            String trim = r4.A0m().trim();
            if (trim.length() == 0) {
                A0Z = A08();
            } else {
                C04750rC r2 = r5.A07()._parser;
                C04740rB r1 = new C04740rB(trim.trim());
                AbstractC02190iF A002 = C04750rC.A00(r2, r1);
                if (!r1.hasMoreTokens()) {
                    return A002;
                }
                throw C04750rC.A01(r1, "Unexpected tokens after complete type");
            }
        } else if (A0i == EnumC03640oE.VALUE_EMBEDDED_OBJECT) {
            A0Z = r4.A0Z();
        } else {
            throw r5.A0B(this._valueClass);
        }
        return (AbstractC02190iF) A0Z;
    }
}
