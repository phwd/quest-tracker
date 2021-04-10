package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AnonymousClass0U;
import X.AnonymousClass0Z;
import X.NX;
import X.OA;
import X.P3;
import X.PW;

public class JsonNodeDeserializer extends BaseNodeDeserializer {
    public static final JsonNodeDeserializer A00 = new JsonNodeDeserializer();

    public final class ArrayDeserializer extends BaseNodeDeserializer {
        public static final ArrayDeserializer A00 = new ArrayDeserializer();
        public static final long serialVersionUID = 1;

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final AnonymousClass0Z A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            if (qiVar.A0i()) {
                return A0O(qiVar, qrVar, qrVar._config._nodeFactory);
            }
            qrVar.A0J();
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public final class ObjectDeserializer extends BaseNodeDeserializer {
        public static final ObjectDeserializer A00 = new ObjectDeserializer();
        public static final long serialVersionUID = 1;

        /* access modifiers changed from: private */
        /* renamed from: A00 */
        public final AnonymousClass0U A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
            NX A0U = qiVar.A0U();
            if (A0U == NX.START_OBJECT) {
                qiVar.A0o();
            } else if (A0U != NX.FIELD_NAME) {
                qrVar.A0J();
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
            return A0P(qiVar, qrVar, qrVar._config._nodeFactory);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final OA A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        int i = P3.A00[qiVar.A0U().ordinal()];
        if (i == 1) {
            return A0P(qiVar, qrVar, qrVar._config._nodeFactory);
        }
        PW pw = qrVar._config._nodeFactory;
        if (i != 2) {
            return A0N(qiVar, qrVar, pw);
        }
        return A0O(qiVar, qrVar, pw);
    }
}
