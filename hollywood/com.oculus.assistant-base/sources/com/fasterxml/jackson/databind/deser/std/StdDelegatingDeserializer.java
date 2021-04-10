package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0264Od;
import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AbstractC1024qt;
import X.AnonymousClass08;
import X.O5;
import X.Oi;
import X.Q6;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class StdDelegatingDeserializer extends StdDeserializer implements AbstractC0264Od, Oi {
    public static final long serialVersionUID = 1;
    public final Q6 _converter = null;
    public final JsonDeserializer _delegateDeserializer;
    public final AbstractC1024qt _delegateType;

    public StdDelegatingDeserializer(AbstractC1024qt qtVar, JsonDeserializer jsonDeserializer) {
        super(qtVar);
        this._delegateType = qtVar;
        this._delegateDeserializer = jsonDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        if (this._delegateDeserializer.A0C(qiVar, qrVar) == null) {
            return null;
        }
        throw new NullPointerException("convert");
    }

    @Override // X.AbstractC0264Od
    public final JsonDeserializer A1X(AbstractC1022qr qrVar, O5 o5) {
        JsonDeserializer A1X;
        JsonDeserializer jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer == null) {
            throw new NullPointerException("getInputType");
        } else if (!(jsonDeserializer instanceof AbstractC0264Od) || (A1X = ((AbstractC0264Od) jsonDeserializer).A1X(qrVar, o5)) == this._delegateDeserializer) {
            return this;
        } else {
            AbstractC1024qt qtVar = this._delegateType;
            Class<?> cls = getClass();
            if (cls == StdDelegatingDeserializer.class) {
                return new StdDelegatingDeserializer(qtVar, A1X);
            }
            throw new IllegalStateException(AnonymousClass08.A05("Sub-class ", cls.getName(), " must override 'withDelegate'"));
        }
    }

    @Override // X.Oi
    public final void A4s(AbstractC1022qr qrVar) {
        JsonDeserializer jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null && (jsonDeserializer instanceof Oi)) {
            ((Oi) jsonDeserializer).A4s(qrVar);
        }
    }
}
