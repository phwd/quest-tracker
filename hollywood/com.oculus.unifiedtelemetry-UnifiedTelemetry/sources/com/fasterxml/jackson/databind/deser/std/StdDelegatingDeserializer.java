package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0224Wl;
import X.AbstractC0226Wn;
import X.AbstractC0227Wo;
import X.AbstractC0232Ww;
import X.AbstractC0264Yo;
import X.AnonymousClass06;
import X.C0223Wj;
import X.MH;
import X.V4;
import X.Zy;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public final class StdDelegatingDeserializer<T> extends StdDeserializer<T> implements Zy, AbstractC0264Yo {
    public static final long serialVersionUID = 1;
    public final MH<Object, T> _converter = null;
    public final JsonDeserializer<Object> _delegateDeserializer;
    public final AbstractC0224Wl _delegateType;

    /* JADX WARN: Incorrect args count in method signature: (LX/MH<Ljava/lang/Object;TT;>;LX/Wl;Lcom/fasterxml/jackson/databind/JsonDeserializer<*>;)V */
    public StdDelegatingDeserializer(AbstractC0224Wl wl, JsonDeserializer jsonDeserializer) {
        super(wl);
        this._delegateType = wl;
        this._delegateDeserializer = jsonDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final T A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        if (this._delegateDeserializer.A09(ww, wn) == null) {
            return null;
        }
        throw null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        if (this._delegateDeserializer.A0C(ww, wn, v4) == null) {
            return null;
        }
        throw null;
    }

    @Override // X.Zy
    public final JsonDeserializer<?> A1g(AbstractC0226Wn wn, AbstractC0227Wo wo) throws C0223Wj {
        JsonDeserializer<?> A1g;
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer == null) {
            throw null;
        } else if (!(jsonDeserializer instanceof Zy) || (A1g = ((Zy) jsonDeserializer).A1g(wn, wo)) == this._delegateDeserializer) {
            return this;
        } else {
            AbstractC0224Wl wl = this._delegateType;
            Class<?> cls = getClass();
            if (cls == StdDelegatingDeserializer.class) {
                return new StdDelegatingDeserializer(wl, A1g);
            }
            throw new IllegalStateException(AnonymousClass06.A05("Sub-class ", cls.getName(), " must override 'withDelegate'"));
        }
    }

    @Override // X.AbstractC0264Yo
    public final void A4n(AbstractC0226Wn wn) throws C0223Wj {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null && (jsonDeserializer instanceof AbstractC0264Yo)) {
            ((AbstractC0264Yo) jsonDeserializer).A4n(wn);
        }
    }
}
