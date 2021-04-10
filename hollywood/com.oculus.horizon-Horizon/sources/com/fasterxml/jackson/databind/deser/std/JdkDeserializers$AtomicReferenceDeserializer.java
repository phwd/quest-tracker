package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04000gb;
import X.AbstractC04020gg;
import X.AbstractC04030gh;
import X.AbstractC04100gp;
import X.AbstractC05430l6;
import X.AnonymousClass0jg;
import X.C03990gZ;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class JdkDeserializers$AtomicReferenceDeserializer extends StdScalarDeserializer<AtomicReference<?>> implements AbstractC05430l6 {
    public final AbstractC04000gb _referencedType;
    public final JsonDeserializer<?> _valueDeserializer;

    public JdkDeserializers$AtomicReferenceDeserializer(AbstractC04000gb r2, JsonDeserializer<?> jsonDeserializer) {
        super(AtomicReference.class);
        this._referencedType = r2;
        this._valueDeserializer = jsonDeserializer;
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AtomicReference<?> A09(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        return new AtomicReference<>(this._valueDeserializer.A09(r3, r4));
    }

    @Override // X.AbstractC05430l6
    public final JsonDeserializer<?> A21(AbstractC04020gg r4, AbstractC04030gh r5) throws C03990gZ {
        if (this._valueDeserializer != null) {
            return this;
        }
        AbstractC04000gb r2 = this._referencedType;
        return new JdkDeserializers$AtomicReferenceDeserializer(r2, r4.A05(r2, r5));
    }
}
