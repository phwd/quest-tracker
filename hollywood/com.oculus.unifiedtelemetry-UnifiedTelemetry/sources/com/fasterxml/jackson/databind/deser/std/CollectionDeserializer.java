package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0224Wl;
import X.AbstractC0226Wn;
import X.AbstractC0227Wo;
import X.AbstractC0232Ww;
import X.AbstractC0262Ym;
import X.C0223Wj;
import X.EnumC0225Wm;
import X.EnumC0470q2;
import X.V4;
import X.Zy;
import X.q0;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.util.Collection;

@JacksonStdImpl
public class CollectionDeserializer extends ContainerDeserializerBase<Collection<Object>> implements Zy {
    public static final long serialVersionUID = -2003828398549708958L;
    public final AbstractC0224Wl _collectionType;
    public final JsonDeserializer<Object> _delegateDeserializer;
    public final JsonDeserializer<Object> _valueDeserializer;
    public final AbstractC0262Ym _valueInstantiator;
    public final V4 _valueTypeDeserializer;

    public CollectionDeserializer(AbstractC0224Wl wl, JsonDeserializer<Object> jsonDeserializer, V4 v4, AbstractC0262Ym ym, JsonDeserializer<Object> jsonDeserializer2) {
        super(wl._class);
        this._collectionType = wl;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = v4;
        this._valueInstantiator = ym;
        this._delegateDeserializer = jsonDeserializer2;
    }

    public CollectionDeserializer A0Q(JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, V4 v4) {
        if (jsonDeserializer == this._delegateDeserializer && jsonDeserializer2 == this._valueDeserializer && v4 == this._valueTypeDeserializer) {
            return this;
        }
        return new CollectionDeserializer(this._collectionType, jsonDeserializer2, v4, this._valueInstantiator, jsonDeserializer);
    }

    /* renamed from: A0R */
    public Collection<Object> A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        Object A0A;
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            A0A = this._valueInstantiator.A09(wn, jsonDeserializer.A09(ww, wn));
        } else {
            if (ww.A0Z() == EnumC0470q2.VALUE_STRING) {
                String A0d = ww.A0d();
                if (A0d.length() == 0) {
                    A0A = this._valueInstantiator.A0A(wn, A0d);
                }
            }
            return A0A(ww, wn, (Collection) this._valueInstantiator.A05(wn));
        }
        return (Collection) A0A;
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/Ww;LX/Wn;Ljava/util/Collection<Ljava/lang/Object;>;)Ljava/util/Collection<Ljava/lang/Object;>; */
    public final void A0T(AbstractC0232Ww ww, AbstractC0226Wn wn, Collection collection) throws IOException, q0 {
        Object A0C;
        if (wn.A0L(EnumC0225Wm.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
            V4 v4 = this._valueTypeDeserializer;
            if (ww.A0Z() == EnumC0470q2.VALUE_NULL) {
                A0C = null;
            } else if (v4 == null) {
                A0C = jsonDeserializer.A09(ww, wn);
            } else {
                A0C = jsonDeserializer.A0C(ww, wn, v4);
            }
            collection.add(A0C);
            return;
        }
        throw wn.A08(this._collectionType._class);
    }

    @Override // X.Zy
    public final JsonDeserializer A1g(AbstractC0226Wn wn, AbstractC0227Wo wo) throws C0223Wj {
        JsonDeserializer<Object> jsonDeserializer;
        AbstractC0262Ym ym = this._valueInstantiator;
        if (ym == null || !ym.A0L()) {
            jsonDeserializer = null;
        } else {
            AbstractC0224Wl A01 = ym.A01(wn._config);
            if (A01 != null) {
                jsonDeserializer = wn.A06(A01, wo);
            } else {
                StringBuilder sb = new StringBuilder("Invalid delegate-creator definition for ");
                sb.append(this._collectionType);
                sb.append(": value instantiator (");
                sb.append(this._valueInstantiator.getClass().getName());
                sb.append(") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
                throw new IllegalArgumentException(sb.toString());
            }
        }
        JsonDeserializer<?> A05 = StdDeserializer.A05(wn, wo, this._valueDeserializer);
        if (A05 == null) {
            A05 = wn.A06(this._collectionType.A03(), wo);
        } else if (A05 instanceof Zy) {
            A05 = ((Zy) A05).A1g(wn, wo);
        }
        V4 v4 = this._valueTypeDeserializer;
        if (v4 != null) {
            v4 = v4.A04(wo);
        }
        return A0Q(jsonDeserializer, A05, v4);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        return v4.A08(ww, wn);
    }

    /* renamed from: A0S */
    public Collection<Object> A0A(AbstractC0232Ww ww, AbstractC0226Wn wn, Collection<Object> collection) throws IOException, q0 {
        Object A0C;
        if (ww.A0F()) {
            JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
            V4 v4 = this._valueTypeDeserializer;
            while (true) {
                EnumC0470q2 A0a = ww.A0a();
                if (A0a == EnumC0470q2.END_ARRAY) {
                    break;
                }
                if (A0a == EnumC0470q2.VALUE_NULL) {
                    A0C = null;
                } else if (v4 == null) {
                    A0C = jsonDeserializer.A09(ww, wn);
                } else {
                    A0C = jsonDeserializer.A0C(ww, wn, v4);
                }
                collection.add(A0C);
            }
        } else {
            A0T(ww, wn, collection);
        }
        return collection;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public final JsonDeserializer<Object> A0P() {
        return this._valueDeserializer;
    }
}
