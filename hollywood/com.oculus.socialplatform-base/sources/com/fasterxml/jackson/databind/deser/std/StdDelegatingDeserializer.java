package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02190iF;
import X.AbstractC02210iH;
import X.AbstractC02220iI;
import X.AbstractC02280iQ;
import X.AbstractC04230pb;
import X.AbstractC04280pi;
import X.AbstractC04520qa;
import X.AnonymousClass006;
import X.AnonymousClass0rJ;
import X.C02180iD;
import X.C03620oC;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class StdDelegatingDeserializer<T> extends StdDeserializer<T> implements AbstractC04230pb, AbstractC04280pi {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0rJ<Object, T> _converter = null;
    public final JsonDeserializer<Object> _delegateDeserializer;
    public final AbstractC02190iF _delegateType;

    /* JADX WARN: Incorrect args count in method signature: (LX/0rJ<Ljava/lang/Object;TT;>;LX/0iF;Lcom/fasterxml/jackson/databind/JsonDeserializer<*>;)V */
    public StdDelegatingDeserializer(AbstractC02190iF r2, JsonDeserializer jsonDeserializer) {
        super(r2);
        this._delegateType = r2;
        this._delegateDeserializer = jsonDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final T A0A(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException, C03620oC {
        if (this._delegateDeserializer.A0A(r3, r4) == null) {
            return null;
        }
        throw new NullPointerException("convert");
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0B(AbstractC02280iQ r3, AbstractC02210iH r4, AbstractC04520qa r5) throws IOException, C03620oC {
        if (this._delegateDeserializer.A0B(r3, r4, r5) == null) {
            return null;
        }
        throw new NullPointerException("convert");
    }

    @Override // X.AbstractC04230pb
    public final JsonDeserializer<?> A2O(AbstractC02210iH r5, AbstractC02220iI r6) throws C02180iD {
        JsonDeserializer<?> A2O;
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer == null) {
            throw new NullPointerException("getInputType");
        } else if (!(jsonDeserializer instanceof AbstractC04230pb) || (A2O = ((AbstractC04230pb) jsonDeserializer).A2O(r5, r6)) == this._delegateDeserializer) {
            return this;
        } else {
            AbstractC02190iF r2 = this._delegateType;
            Class<?> cls = getClass();
            if (cls == StdDelegatingDeserializer.class) {
                return new StdDelegatingDeserializer(r2, A2O);
            }
            throw new IllegalStateException(AnonymousClass006.A09("Sub-class ", cls.getName(), " must override 'withDelegate'"));
        }
    }

    @Override // X.AbstractC04280pi
    public final void A9N(AbstractC02210iH r3) throws C02180iD {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null && (jsonDeserializer instanceof AbstractC04280pi)) {
            ((AbstractC04280pi) jsonDeserializer).A9N(r3);
        }
    }
}
