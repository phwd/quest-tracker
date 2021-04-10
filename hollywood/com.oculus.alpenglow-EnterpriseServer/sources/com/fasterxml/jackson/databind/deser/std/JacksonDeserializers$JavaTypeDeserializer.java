package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aI;
import X.AnonymousClass0aT;
import X.C05910ld;
import X.C07060of;
import X.C07070og;
import X.EnumC05930lf;
import java.io.IOException;

public class JacksonDeserializers$JavaTypeDeserializer extends StdScalarDeserializer<AnonymousClass0aI> {
    public static final JacksonDeserializers$JavaTypeDeserializer A00 = new JacksonDeserializers$JavaTypeDeserializer();

    public JacksonDeserializers$JavaTypeDeserializer() {
        super(AnonymousClass0aI.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AnonymousClass0aI A09(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        Object A0M;
        EnumC05930lf A0G = r4.A0G();
        if (A0G == EnumC05930lf.VALUE_STRING) {
            String trim = r4.A0P().trim();
            if (trim.length() == 0) {
                A0M = A08();
            } else {
                C07070og r2 = r5.A07()._parser;
                C07060of r1 = new C07060of(trim.trim());
                AnonymousClass0aI A002 = C07070og.A00(r2, r1);
                if (!r1.hasMoreTokens()) {
                    return A002;
                }
                throw C07070og.A01(r1, "Unexpected tokens after complete type");
            }
        } else if (A0G == EnumC05930lf.VALUE_EMBEDDED_OBJECT) {
            A0M = r4.A0M();
        } else {
            throw r5.A0B(this._valueClass);
        }
        return (AnonymousClass0aI) A0M;
    }
}
