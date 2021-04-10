package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.Rn;
import java.io.IOException;

public final class JsonNodeDeserializer extends BaseNodeDeserializer {
    public static final JsonNodeDeserializer A00 = new JsonNodeDeserializer();

    public static final class ArrayDeserializer extends BaseNodeDeserializer {
        public static final ArrayDeserializer A00 = new ArrayDeserializer();
        public static final long serialVersionUID = 1;

        private final void A00() throws IOException, AnonymousClass9r {
            throw null;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
            throw null;
        }
    }

    public static final class ObjectDeserializer extends BaseNodeDeserializer {
        public static final ObjectDeserializer A00 = new ObjectDeserializer();
        public static final long serialVersionUID = 1;

        private final void A00(Rn rn) throws IOException, AnonymousClass9r {
            if (((B3) rn).A00 == AnonymousClass9p.START_OBJECT) {
                rn.A04();
            }
            throw null;
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
            A00(rn);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    private final void A00(Rn rn) throws IOException, AnonymousClass9r {
        throw null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        throw null;
    }
}
