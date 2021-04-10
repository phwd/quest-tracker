package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0264Od;
import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AbstractC1024qt;
import X.O5;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.util.concurrent.atomic.AtomicReference;

public class JdkDeserializers$AtomicReferenceDeserializer extends StdScalarDeserializer implements AbstractC0264Od {
    public final AbstractC1024qt _referencedType;
    public final JsonDeserializer _valueDeserializer;

    public JdkDeserializers$AtomicReferenceDeserializer(AbstractC1024qt qtVar, JsonDeserializer jsonDeserializer) {
        super(AtomicReference.class);
        this._referencedType = qtVar;
        this._valueDeserializer = jsonDeserializer;
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final AtomicReference A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        return new AtomicReference(this._valueDeserializer.A0C(qiVar, qrVar));
    }

    @Override // X.AbstractC0264Od
    public final JsonDeserializer A1X(AbstractC1022qr qrVar, O5 o5) {
        if (this._valueDeserializer != null) {
            return this;
        }
        AbstractC1024qt qtVar = this._referencedType;
        return new JdkDeserializers$AtomicReferenceDeserializer(qtVar, qrVar.A08(qtVar, o5));
    }
}
