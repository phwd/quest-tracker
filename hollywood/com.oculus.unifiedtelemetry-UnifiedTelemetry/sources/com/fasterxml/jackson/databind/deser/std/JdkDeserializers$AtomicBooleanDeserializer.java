package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.q0;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class JdkDeserializers$AtomicBooleanDeserializer extends StdScalarDeserializer<AtomicBoolean> {
    public static final JdkDeserializers$AtomicBooleanDeserializer A00 = new JdkDeserializers$AtomicBooleanDeserializer();

    public JdkDeserializers$AtomicBooleanDeserializer() {
        super(AtomicBoolean.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AtomicBoolean A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        return new AtomicBoolean(A0O(ww, wn));
    }
}
