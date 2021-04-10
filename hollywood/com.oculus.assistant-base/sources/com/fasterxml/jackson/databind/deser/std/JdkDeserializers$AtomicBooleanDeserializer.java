package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import java.util.concurrent.atomic.AtomicBoolean;

public class JdkDeserializers$AtomicBooleanDeserializer extends StdScalarDeserializer {
    public static final JdkDeserializers$AtomicBooleanDeserializer A00 = new JdkDeserializers$AtomicBooleanDeserializer();

    public JdkDeserializers$AtomicBooleanDeserializer() {
        super(AtomicBoolean.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AtomicBoolean A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        return new AtomicBoolean(A0M(qiVar, qrVar));
    }
}
