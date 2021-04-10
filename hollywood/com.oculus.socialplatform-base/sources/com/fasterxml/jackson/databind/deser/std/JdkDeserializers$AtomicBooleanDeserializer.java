package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.C03620oC;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class JdkDeserializers$AtomicBooleanDeserializer extends StdScalarDeserializer<AtomicBoolean> {
    public static final JdkDeserializers$AtomicBooleanDeserializer A00 = new JdkDeserializers$AtomicBooleanDeserializer();

    public JdkDeserializers$AtomicBooleanDeserializer() {
        super(AtomicBoolean.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AtomicBoolean A0A(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException, C03620oC {
        return new AtomicBoolean(A0O(r3, r4));
    }
}
