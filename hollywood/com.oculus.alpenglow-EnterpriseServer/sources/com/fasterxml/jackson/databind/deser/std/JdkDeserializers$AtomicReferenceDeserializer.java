package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AbstractC02580aL;
import X.AbstractC06520n2;
import X.AnonymousClass0aG;
import X.AnonymousClass0aI;
import X.AnonymousClass0aT;
import X.C05910ld;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class JdkDeserializers$AtomicReferenceDeserializer extends StdScalarDeserializer<AtomicReference<?>> implements AbstractC06520n2 {
    public final AnonymousClass0aI _referencedType;
    public final JsonDeserializer<?> _valueDeserializer;

    public JdkDeserializers$AtomicReferenceDeserializer(AnonymousClass0aI r2, JsonDeserializer<?> jsonDeserializer) {
        super(AtomicReference.class);
        this._referencedType = r2;
        this._valueDeserializer = jsonDeserializer;
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AtomicReference<?> A09(AnonymousClass0aT r3, AbstractC02570aK r4) throws IOException, C05910ld {
        return new AtomicReference<>(this._valueDeserializer.A09(r3, r4));
    }

    @Override // X.AbstractC06520n2
    public final JsonDeserializer<?> A1w(AbstractC02570aK r4, AbstractC02580aL r5) throws AnonymousClass0aG {
        if (this._valueDeserializer != null) {
            return this;
        }
        AnonymousClass0aI r2 = this._referencedType;
        return new JdkDeserializers$AtomicReferenceDeserializer(r2, r4.A09(r2, r5));
    }
}
