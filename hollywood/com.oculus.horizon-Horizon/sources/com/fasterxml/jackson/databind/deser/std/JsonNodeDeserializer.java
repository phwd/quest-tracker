package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC03980gY;
import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass033;
import X.AnonymousClass03C;
import X.AnonymousClass0jg;
import X.C03700fv;
import X.C05620la;
import X.EnumC04820ji;
import java.io.IOException;

public final class JsonNodeDeserializer extends BaseNodeDeserializer {
    public static final JsonNodeDeserializer A00 = new JsonNodeDeserializer();

    public static final class ArrayDeserializer extends BaseNodeDeserializer {
        public static final ArrayDeserializer A00 = new ArrayDeserializer();
        public static final long serialVersionUID = 1;

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final AnonymousClass03C A09(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
            if (r2.A0G()) {
                return A0Q(r2, r3, r3._config._nodeFactory);
            }
            throw null;
        }
    }

    public static final class ObjectDeserializer extends BaseNodeDeserializer {
        public static final ObjectDeserializer A00 = new ObjectDeserializer();
        public static final long serialVersionUID = 1;

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final AnonymousClass033 A09(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
            EnumC04820ji A0a = r3.A0a();
            if (A0a == EnumC04820ji.START_OBJECT) {
                r3.A0b();
            } else if (A0a != EnumC04820ji.FIELD_NAME) {
                throw null;
            }
            return A0R(r3, r4, r4._config._nodeFactory);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AbstractC03980gY A09(AbstractC04100gp r4, AbstractC04020gg r5) throws IOException, AnonymousClass0jg {
        int i = C05620la.A00[r4.A0a().ordinal()];
        if (i == 1) {
            return A0R(r4, r5, r5._config._nodeFactory);
        }
        C03700fv r0 = r5._config._nodeFactory;
        if (i != 2) {
            return A0P(r4, r5, r0);
        }
        return A0Q(r4, r5, r0);
    }
}
