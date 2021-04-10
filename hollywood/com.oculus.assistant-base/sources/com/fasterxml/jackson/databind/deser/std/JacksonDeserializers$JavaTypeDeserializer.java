package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AbstractC1024qt;
import X.C0302Py;
import X.C0303Pz;
import X.NX;

public class JacksonDeserializers$JavaTypeDeserializer extends StdScalarDeserializer {
    public static final JacksonDeserializers$JavaTypeDeserializer A00 = new JacksonDeserializers$JavaTypeDeserializer();

    public JacksonDeserializers$JavaTypeDeserializer() {
        super(AbstractC1024qt.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AbstractC1024qt A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        Object A0Z;
        NX A0U = qiVar.A0U();
        if (A0U == NX.VALUE_STRING) {
            String trim = qiVar.A0p().trim();
            if (trim.length() == 0) {
                A0Z = A08();
            } else {
                C0303Pz pz = qrVar.A05()._parser;
                C0302Py py = new C0302Py(trim.trim());
                AbstractC1024qt A002 = C0303Pz.A00(pz, py);
                if (!py.hasMoreTokens()) {
                    return A002;
                }
                throw C0303Pz.A01(py, "Unexpected tokens after complete type");
            }
        } else if (A0U == NX.VALUE_EMBEDDED_OBJECT) {
            A0Z = qiVar.A0Z();
        } else {
            qrVar.A0J();
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
        return (AbstractC1024qt) A0Z;
    }
}
