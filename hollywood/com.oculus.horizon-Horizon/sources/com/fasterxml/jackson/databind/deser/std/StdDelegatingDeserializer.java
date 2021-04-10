package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04000gb;
import X.AbstractC04020gg;
import X.AbstractC04030gh;
import X.AbstractC04100gp;
import X.AbstractC05430l6;
import X.AbstractC05470lE;
import X.AbstractC06340mv;
import X.AnonymousClass006;
import X.AnonymousClass0jg;
import X.AnonymousClass0m9;
import X.C03990gZ;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public final class StdDelegatingDeserializer<T> extends StdDeserializer<T> implements AbstractC05430l6, AbstractC05470lE {
    public static final long serialVersionUID = 1;
    public final AbstractC06340mv<Object, T> _converter = null;
    public final JsonDeserializer<Object> _delegateDeserializer;
    public final AbstractC04000gb _delegateType;

    /* JADX WARN: Incorrect args count in method signature: (LX/0mv<Ljava/lang/Object;TT;>;LX/0gb;Lcom/fasterxml/jackson/databind/JsonDeserializer<*>;)V */
    public StdDelegatingDeserializer(AbstractC04000gb r2, JsonDeserializer jsonDeserializer) {
        super(r2);
        this._delegateType = r2;
        this._delegateDeserializer = jsonDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final T A09(AbstractC04100gp r2, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        if (this._delegateDeserializer.A09(r2, r3) == null) {
            return null;
        }
        throw null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC04100gp r2, AbstractC04020gg r3, AnonymousClass0m9 r4) throws IOException, AnonymousClass0jg {
        if (this._delegateDeserializer.A0C(r2, r3, r4) == null) {
            return null;
        }
        throw null;
    }

    @Override // X.AbstractC05430l6
    public final JsonDeserializer<?> A21(AbstractC04020gg r5, AbstractC04030gh r6) throws C03990gZ {
        JsonDeserializer<?> A21;
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer == null) {
            throw null;
        } else if (!(jsonDeserializer instanceof AbstractC05430l6) || (A21 = ((AbstractC05430l6) jsonDeserializer).A21(r5, r6)) == this._delegateDeserializer) {
            return this;
        } else {
            AbstractC04000gb r2 = this._delegateType;
            Class<?> cls = getClass();
            if (cls == StdDelegatingDeserializer.class) {
                return new StdDelegatingDeserializer(r2, A21);
            }
            throw new IllegalStateException(AnonymousClass006.A07("Sub-class ", cls.getName(), " must override 'withDelegate'"));
        }
    }

    @Override // X.AbstractC05470lE
    public final void A8N(AbstractC04020gg r3) throws C03990gZ {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null && (jsonDeserializer instanceof AbstractC05470lE)) {
            ((AbstractC05470lE) jsonDeserializer).A8N(r3);
        }
    }
}
