package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class JdkDeserializers$AtomicBooleanDeserializer extends StdScalarDeserializer<AtomicBoolean> {
    public static final JdkDeserializers$AtomicBooleanDeserializer A00 = new JdkDeserializers$AtomicBooleanDeserializer();

    public JdkDeserializers$AtomicBooleanDeserializer() {
        super(AtomicBoolean.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AtomicBoolean A09(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        return new AtomicBoolean(A0O(r3, r4));
    }
}
