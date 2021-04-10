package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.C05910ld;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class JdkDeserializers$AtomicBooleanDeserializer extends StdScalarDeserializer<AtomicBoolean> {
    public static final JdkDeserializers$AtomicBooleanDeserializer A00 = new JdkDeserializers$AtomicBooleanDeserializer();

    public JdkDeserializers$AtomicBooleanDeserializer() {
        super(AtomicBoolean.class);
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AtomicBoolean A09(AnonymousClass0aT r3, AbstractC02570aK r4) throws IOException, C05910ld {
        return new AtomicBoolean(A0O(r3, r4));
    }
}
