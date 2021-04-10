package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02170iC;
import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AnonymousClass04L;
import X.AnonymousClass04X;
import X.C01850hC;
import X.C03620oC;
import X.C04460q4;
import X.EnumC03640oE;
import java.io.IOException;

public class JsonNodeDeserializer extends BaseNodeDeserializer {
    public static final JsonNodeDeserializer A00 = new JsonNodeDeserializer();

    public static final class ArrayDeserializer extends BaseNodeDeserializer {
        public static final ArrayDeserializer A00 = new ArrayDeserializer();
        public static final long serialVersionUID = 1;

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final AnonymousClass04X A0A(AbstractC02280iQ r2, AbstractC02210iH r3) throws IOException, C03620oC {
            if (r2.A0K()) {
                return A0Q(r2, r3, r3._config._nodeFactory);
            }
            throw r3.A0B(AnonymousClass04X.class);
        }
    }

    public static final class ObjectDeserializer extends BaseNodeDeserializer {
        public static final ObjectDeserializer A00 = new ObjectDeserializer();
        public static final long serialVersionUID = 1;

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final AnonymousClass04L A0A(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException, C03620oC {
            EnumC03640oE A0i = r3.A0i();
            if (A0i == EnumC03640oE.START_OBJECT) {
                r3.A0j();
            } else if (A0i != EnumC03640oE.FIELD_NAME) {
                throw r4.A0B(AnonymousClass04L.class);
            }
            return A0R(r3, r4, r4._config._nodeFactory);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AbstractC02170iC A0A(AbstractC02280iQ r4, AbstractC02210iH r5) throws IOException, C03620oC {
        int i = C04460q4.A00[r4.A0i().ordinal()];
        if (i == 1) {
            return A0R(r4, r5, r5._config._nodeFactory);
        }
        C01850hC r0 = r5._config._nodeFactory;
        if (i != 2) {
            return A0P(r4, r5, r0);
        }
        return A0Q(r4, r5, r0);
    }
}
