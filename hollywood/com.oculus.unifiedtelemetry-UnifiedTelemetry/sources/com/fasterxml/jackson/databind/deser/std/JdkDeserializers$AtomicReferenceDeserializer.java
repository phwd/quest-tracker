package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0224Wl;
import X.AbstractC0226Wn;
import X.AbstractC0227Wo;
import X.AbstractC0232Ww;
import X.C0223Wj;
import X.Zy;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

public class JdkDeserializers$AtomicReferenceDeserializer extends StdScalarDeserializer<AtomicReference<?>> implements Zy {
    public final AbstractC0224Wl _referencedType;
    public final JsonDeserializer<?> _valueDeserializer;

    public JdkDeserializers$AtomicReferenceDeserializer(AbstractC0224Wl wl, JsonDeserializer<?> jsonDeserializer) {
        super(AtomicReference.class);
        this._referencedType = wl;
        this._valueDeserializer = jsonDeserializer;
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AtomicReference<?> A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        return new AtomicReference<>(this._valueDeserializer.A09(ww, wn));
    }

    @Override // X.Zy
    public final JsonDeserializer<?> A1g(AbstractC0226Wn wn, AbstractC0227Wo wo) throws C0223Wj {
        if (this._valueDeserializer != null) {
            return this;
        }
        AbstractC0224Wl wl = this._referencedType;
        return new JdkDeserializers$AtomicReferenceDeserializer(wl, wn.A06(wl, wo));
    }
}
