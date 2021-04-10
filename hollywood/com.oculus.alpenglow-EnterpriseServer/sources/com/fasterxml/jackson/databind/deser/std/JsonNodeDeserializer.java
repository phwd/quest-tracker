package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass07p;
import X.AnonymousClass0Zc;
import X.AnonymousClass0aF;
import X.AnonymousClass0aT;
import X.AnonymousClass0nV;
import X.C007107f;
import X.C05910ld;
import X.EnumC05930lf;
import java.io.IOException;

public final class JsonNodeDeserializer extends BaseNodeDeserializer {
    public static final JsonNodeDeserializer A00 = new JsonNodeDeserializer();

    public static final class ArrayDeserializer extends BaseNodeDeserializer {
        public static final ArrayDeserializer A00 = new ArrayDeserializer();
        public static final long serialVersionUID = 1;

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final AnonymousClass07p A09(AnonymousClass0aT r2, AbstractC02570aK r3) throws IOException, C05910ld {
            if (r2.A0V()) {
                return A0Q(r2, r3, r3._config._nodeFactory);
            }
            throw r3.A0B(AnonymousClass07p.class);
        }
    }

    public static final class ObjectDeserializer extends BaseNodeDeserializer {
        public static final ObjectDeserializer A00 = new ObjectDeserializer();
        public static final long serialVersionUID = 1;

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final C007107f A09(AnonymousClass0aT r3, AbstractC02570aK r4) throws IOException, C05910ld {
            EnumC05930lf A0G = r3.A0G();
            if (A0G == EnumC05930lf.START_OBJECT) {
                r3.A0a();
            } else if (A0G != EnumC05930lf.FIELD_NAME) {
                throw r4.A0B(C007107f.class);
            }
            return A0R(r3, r4, r4._config._nodeFactory);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AnonymousClass0aF A09(AnonymousClass0aT r4, AbstractC02570aK r5) throws IOException, C05910ld {
        int i = AnonymousClass0nV.A00[r4.A0G().ordinal()];
        if (i == 1) {
            return A0R(r4, r5, r5._config._nodeFactory);
        }
        AnonymousClass0Zc r0 = r5._config._nodeFactory;
        if (i != 2) {
            return A0P(r4, r5, r0);
        }
        return A0Q(r4, r5, r0);
    }
}
