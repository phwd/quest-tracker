package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass86;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.Rn;
import java.io.IOException;
import java.lang.reflect.Method;

public final class EnumDeserializer extends StdScalarDeserializer<Enum<?>> {
    public static final long serialVersionUID = -5893263645879532318L;
    public final AnonymousClass86<?> _resolver;

    public static class FactoryBasedDeserializer extends StdScalarDeserializer<Object> {
        public static final long serialVersionUID = -7775129435872564122L;
        public final Class<?> _enumClass;
        public final Method _factory;
        public final Class<?> _inputType;

        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0066, code lost:
            if (r4 < r5) goto L_0x0068;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:71:0x00f7, code lost:
            if (r5 < r6) goto L_0x00f9;
         */
        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object A03(X.Rn r9, X.AbstractC0122Rd r10) throws java.io.IOException, X.AnonymousClass9r {
            /*
            // Method dump skipped, instructions count: 378
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.EnumDeserializer.FactoryBasedDeserializer.A03(X.Rn, X.Rd):java.lang.Object");
        }
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/Rn;LX/Rd;)Ljava/lang/Enum<*>; */
    private final void A00(Rn rn) throws IOException, AnonymousClass9r {
        AnonymousClass9p r1 = ((B3) rn).A00;
        if (r1 == AnonymousClass9p.VALUE_STRING || r1 == AnonymousClass9p.FIELD_NAME) {
            rn.A09();
        }
        throw null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        A00(rn);
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
