package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AbstractC02580aL;
import X.AbstractC06520n2;
import X.AbstractC06550n9;
import X.AbstractC07140on;
import X.AnonymousClass006;
import X.AnonymousClass0aG;
import X.AnonymousClass0aI;
import X.AnonymousClass0aT;
import X.AnonymousClass0o3;
import X.C05910ld;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public final class StdDelegatingDeserializer<T> extends StdDeserializer<T> implements AbstractC06520n2, AbstractC06550n9 {
    public static final long serialVersionUID = 1;
    public final AbstractC07140on<Object, T> _converter = null;
    public final JsonDeserializer<Object> _delegateDeserializer;
    public final AnonymousClass0aI _delegateType;

    /* JADX WARN: Incorrect args count in method signature: (LX/0on<Ljava/lang/Object;TT;>;LX/0aI;Lcom/fasterxml/jackson/databind/JsonDeserializer<*>;)V */
    public StdDelegatingDeserializer(AnonymousClass0aI r2, JsonDeserializer jsonDeserializer) {
        super(r2);
        this._delegateType = r2;
        this._delegateDeserializer = jsonDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final T A09(AnonymousClass0aT r2, AbstractC02570aK r3) throws IOException, C05910ld {
        if (this._delegateDeserializer.A09(r2, r3) == null) {
            return null;
        }
        throw null;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AnonymousClass0aT r2, AbstractC02570aK r3, AnonymousClass0o3 r4) throws IOException, C05910ld {
        if (this._delegateDeserializer.A0C(r2, r3, r4) == null) {
            return null;
        }
        throw null;
    }

    @Override // X.AbstractC06520n2
    public final JsonDeserializer<?> A1w(AbstractC02570aK r5, AbstractC02580aL r6) throws AnonymousClass0aG {
        JsonDeserializer<?> A1w;
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer == null) {
            throw null;
        } else if (!(jsonDeserializer instanceof AbstractC06520n2) || (A1w = ((AbstractC06520n2) jsonDeserializer).A1w(r5, r6)) == this._delegateDeserializer) {
            return this;
        } else {
            AnonymousClass0aI r2 = this._delegateType;
            Class<?> cls = getClass();
            if (cls == StdDelegatingDeserializer.class) {
                return new StdDelegatingDeserializer(r2, A1w);
            }
            throw new IllegalStateException(AnonymousClass006.A07("Sub-class ", cls.getName(), " must override 'withDelegate'"));
        }
    }

    @Override // X.AbstractC06550n9
    public final void A7T(AbstractC02570aK r3) throws AnonymousClass0aG {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null && (jsonDeserializer instanceof AbstractC06550n9)) {
            ((AbstractC06550n9) jsonDeserializer).A7T(r3);
        }
    }
}
