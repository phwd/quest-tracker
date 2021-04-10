package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02190iF;
import X.AbstractC02210iH;
import X.AbstractC02220iI;
import X.AbstractC02280iQ;
import X.AbstractC04230pb;
import X.AbstractC04300pk;
import X.AbstractC04520qa;
import X.C02180iD;
import X.C03620oC;
import X.EnumC02200iG;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.util.Collection;

@JacksonStdImpl
public class CollectionDeserializer extends ContainerDeserializerBase<Collection<Object>> implements AbstractC04230pb {
    public static final long serialVersionUID = -2003828398549708958L;
    public final AbstractC02190iF _collectionType;
    public final JsonDeserializer<Object> _delegateDeserializer;
    public final JsonDeserializer<Object> _valueDeserializer;
    public final AbstractC04300pk _valueInstantiator;
    public final AbstractC04520qa _valueTypeDeserializer;

    public CollectionDeserializer(AbstractC02190iF r2, JsonDeserializer<Object> jsonDeserializer, AbstractC04520qa r4, AbstractC04300pk r5, JsonDeserializer<Object> jsonDeserializer2) {
        super(r2._class);
        this._collectionType = r2;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = r4;
        this._valueInstantiator = r5;
        this._delegateDeserializer = jsonDeserializer2;
    }

    public CollectionDeserializer A0Q(JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, AbstractC04520qa r9) {
        if (jsonDeserializer == this._delegateDeserializer && jsonDeserializer2 == this._valueDeserializer && r9 == this._valueTypeDeserializer) {
            return this;
        }
        return new CollectionDeserializer(this._collectionType, jsonDeserializer2, r9, this._valueInstantiator, jsonDeserializer);
    }

    /* renamed from: A0R */
    public Collection<Object> A0A(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException, C03620oC {
        Object A0A;
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            A0A = this._valueInstantiator.A09(r4, jsonDeserializer.A0A(r3, r4));
        } else {
            if (r3.A0i() == EnumC03640oE.VALUE_STRING) {
                String A0m = r3.A0m();
                if (A0m.length() == 0) {
                    A0A = this._valueInstantiator.A0A(r4, A0m);
                }
            }
            return A0C(r3, r4, (Collection) this._valueInstantiator.A05(r4));
        }
        return (Collection) A0A;
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/0iQ;LX/0iH;Ljava/util/Collection<Ljava/lang/Object;>;)Ljava/util/Collection<Ljava/lang/Object;>; */
    public final void A0T(AbstractC02280iQ r5, AbstractC02210iH r6, Collection collection) throws IOException, C03620oC {
        Object A0B;
        if (r6.A0P(EnumC02200iG.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
            AbstractC04520qa r2 = this._valueTypeDeserializer;
            if (r5.A0i() == EnumC03640oE.VALUE_NULL) {
                A0B = null;
            } else if (r2 == null) {
                A0B = jsonDeserializer.A0A(r5, r6);
            } else {
                A0B = jsonDeserializer.A0B(r5, r6, r2);
            }
            collection.add(A0B);
            return;
        }
        throw r6.A0B(this._collectionType._class);
    }

    @Override // X.AbstractC04230pb
    public final JsonDeserializer A2O(AbstractC02210iH r4, AbstractC02220iI r5) throws C02180iD {
        JsonDeserializer<Object> jsonDeserializer;
        AbstractC04300pk r1 = this._valueInstantiator;
        if (r1 == null || !r1.A0L()) {
            jsonDeserializer = null;
        } else {
            AbstractC02190iF A01 = r1.A01(r4._config);
            if (A01 != null) {
                jsonDeserializer = r4.A09(A01, r5);
            } else {
                StringBuilder sb = new StringBuilder("Invalid delegate-creator definition for ");
                sb.append(this._collectionType);
                sb.append(": value instantiator (");
                sb.append(this._valueInstantiator.getClass().getName());
                sb.append(") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
                throw new IllegalArgumentException(sb.toString());
            }
        }
        JsonDeserializer<?> A05 = StdDeserializer.A05(r4, r5, this._valueDeserializer);
        if (A05 == null) {
            A05 = r4.A09(this._collectionType.A04(), r5);
        } else if (A05 instanceof AbstractC04230pb) {
            A05 = ((AbstractC04230pb) A05).A2O(r4, r5);
        }
        AbstractC04520qa r0 = this._valueTypeDeserializer;
        if (r0 != null) {
            r0 = r0.A04(r5);
        }
        return A0Q(jsonDeserializer, A05, r0);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0B(AbstractC02280iQ r2, AbstractC02210iH r3, AbstractC04520qa r4) throws IOException, C03620oC {
        return r4.A08(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public final JsonDeserializer<Object> A0P() {
        return this._valueDeserializer;
    }

    /* renamed from: A0S */
    public Collection<Object> A0C(AbstractC02280iQ r5, AbstractC02210iH r6, Collection<Object> collection) throws IOException, C03620oC {
        Object A0B;
        if (r5.A0K()) {
            JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
            AbstractC04520qa r2 = this._valueTypeDeserializer;
            while (true) {
                EnumC03640oE A0j = r5.A0j();
                if (A0j == EnumC03640oE.END_ARRAY) {
                    break;
                }
                if (A0j == EnumC03640oE.VALUE_NULL) {
                    A0B = null;
                } else if (r2 == null) {
                    A0B = jsonDeserializer.A0A(r5, r6);
                } else {
                    A0B = jsonDeserializer.A0B(r5, r6, r2);
                }
                collection.add(A0B);
            }
        } else {
            A0T(r5, r6, collection);
        }
        return collection;
    }
}
