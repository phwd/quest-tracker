package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04000gb;
import X.AbstractC04020gg;
import X.AbstractC04030gh;
import X.AbstractC04100gp;
import X.AbstractC05430l6;
import X.AnonymousClass0jg;
import X.AnonymousClass0lG;
import X.AnonymousClass0m9;
import X.C03990gZ;
import X.EnumC04010gf;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.util.Collection;

@JacksonStdImpl
public class CollectionDeserializer extends ContainerDeserializerBase<Collection<Object>> implements AbstractC05430l6 {
    public static final long serialVersionUID = -2003828398549708958L;
    public final AbstractC04000gb _collectionType;
    public final JsonDeserializer<Object> _delegateDeserializer;
    public final JsonDeserializer<Object> _valueDeserializer;
    public final AnonymousClass0lG _valueInstantiator;
    public final AnonymousClass0m9 _valueTypeDeserializer;

    public CollectionDeserializer(AbstractC04000gb r2, JsonDeserializer<Object> jsonDeserializer, AnonymousClass0m9 r4, AnonymousClass0lG r5, JsonDeserializer<Object> jsonDeserializer2) {
        super(r2._class);
        this._collectionType = r2;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = r4;
        this._valueInstantiator = r5;
        this._delegateDeserializer = jsonDeserializer2;
    }

    public CollectionDeserializer A0Q(JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, AnonymousClass0m9 r9) {
        if (jsonDeserializer == this._delegateDeserializer && jsonDeserializer2 == this._valueDeserializer && r9 == this._valueTypeDeserializer) {
            return this;
        }
        return new CollectionDeserializer(this._collectionType, jsonDeserializer2, r9, this._valueInstantiator, jsonDeserializer);
    }

    /* renamed from: A0R */
    public Collection<Object> A09(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        Object A0A;
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            A0A = this._valueInstantiator.A09(r4, jsonDeserializer.A09(r3, r4));
        } else {
            if (r3.A0a() == EnumC04820ji.VALUE_STRING) {
                String A0e = r3.A0e();
                if (A0e.length() == 0) {
                    A0A = this._valueInstantiator.A0A(r4, A0e);
                }
            }
            return A0A(r3, r4, (Collection) this._valueInstantiator.A05(r4));
        }
        return (Collection) A0A;
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/0gp;LX/0gg;Ljava/util/Collection<Ljava/lang/Object;>;)Ljava/util/Collection<Ljava/lang/Object;>; */
    public final void A0T(AbstractC04100gp r5, AbstractC04020gg r6, Collection collection) throws IOException, AnonymousClass0jg {
        Object A0C;
        if (r6.A0I(EnumC04010gf.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
            AnonymousClass0m9 r2 = this._valueTypeDeserializer;
            if (r5.A0a() == EnumC04820ji.VALUE_NULL) {
                A0C = null;
            } else if (r2 == null) {
                A0C = jsonDeserializer.A09(r5, r6);
            } else {
                A0C = jsonDeserializer.A0C(r5, r6, r2);
            }
            collection.add(A0C);
            return;
        }
        throw null;
    }

    @Override // X.AbstractC05430l6
    public final JsonDeserializer A21(AbstractC04020gg r4, AbstractC04030gh r5) throws C03990gZ {
        JsonDeserializer<Object> jsonDeserializer;
        AnonymousClass0lG r1 = this._valueInstantiator;
        if (r1 == null || !r1.A0L()) {
            jsonDeserializer = null;
        } else {
            AbstractC04000gb A01 = r1.A01(r4._config);
            if (A01 != null) {
                jsonDeserializer = r4.A05(A01, r5);
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
            A05 = r4.A05(this._collectionType.A03(), r5);
        } else if (A05 instanceof AbstractC05430l6) {
            A05 = ((AbstractC05430l6) A05).A21(r4, r5);
        }
        AnonymousClass0m9 r0 = this._valueTypeDeserializer;
        if (r0 != null) {
            r0 = r0.A04(r5);
        }
        return A0Q(jsonDeserializer, A05, r0);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC04100gp r2, AbstractC04020gg r3, AnonymousClass0m9 r4) throws IOException, AnonymousClass0jg {
        return r4.A08(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public final JsonDeserializer<Object> A0P() {
        return this._valueDeserializer;
    }

    /* renamed from: A0S */
    public Collection<Object> A0A(AbstractC04100gp r5, AbstractC04020gg r6, Collection<Object> collection) throws IOException, AnonymousClass0jg {
        Object A0C;
        if (r5.A0G()) {
            JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
            AnonymousClass0m9 r2 = this._valueTypeDeserializer;
            while (true) {
                EnumC04820ji A0b = r5.A0b();
                if (A0b == EnumC04820ji.END_ARRAY) {
                    break;
                }
                if (A0b == EnumC04820ji.VALUE_NULL) {
                    A0C = null;
                } else if (r2 == null) {
                    A0C = jsonDeserializer.A09(r5, r6);
                } else {
                    A0C = jsonDeserializer.A0C(r5, r6, r2);
                }
                collection.add(A0C);
            }
        } else {
            A0T(r5, r6, collection);
        }
        return collection;
    }
}
