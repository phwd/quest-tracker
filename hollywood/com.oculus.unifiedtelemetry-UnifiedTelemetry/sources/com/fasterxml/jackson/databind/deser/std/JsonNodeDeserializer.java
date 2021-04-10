package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0222Wi;
import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.AnonymousClass2z;
import X.AnonymousClass38;
import X.C0195Vb;
import X.EnumC0470q2;
import X.W6;
import X.q0;
import java.io.IOException;

public final class JsonNodeDeserializer extends BaseNodeDeserializer {
    public static final JsonNodeDeserializer A00 = new JsonNodeDeserializer();

    public static final class ArrayDeserializer extends BaseNodeDeserializer {
        public static final ArrayDeserializer A00 = new ArrayDeserializer();
        public static final long serialVersionUID = 1;

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final AnonymousClass38 A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
            if (ww.A0F()) {
                return A0Q(ww, wn, wn._config._nodeFactory);
            }
            throw wn.A08(AnonymousClass38.class);
        }
    }

    public static final class ObjectDeserializer extends BaseNodeDeserializer {
        public static final ObjectDeserializer A00 = new ObjectDeserializer();
        public static final long serialVersionUID = 1;

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final AnonymousClass2z A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
            EnumC0470q2 A0Z = ww.A0Z();
            if (A0Z == EnumC0470q2.START_OBJECT) {
                ww.A0a();
            } else if (A0Z != EnumC0470q2.FIELD_NAME) {
                throw wn.A08(AnonymousClass2z.class);
            }
            return A0R(ww, wn, wn._config._nodeFactory);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AbstractC0222Wi A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        int i = C0195Vb.A00[ww.A0Z().ordinal()];
        if (i == 1) {
            return A0R(ww, wn, wn._config._nodeFactory);
        }
        W6 w6 = wn._config._nodeFactory;
        if (i != 2) {
            return A0P(ww, wn, w6);
        }
        return A0Q(ww, wn, w6);
    }
}
