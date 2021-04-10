package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AbstractC02580aL;
import X.AbstractC06520n2;
import X.AnonymousClass0aG;
import X.AnonymousClass0aI;
import X.AnonymousClass0aT;
import X.AnonymousClass0nB;
import X.AnonymousClass0o3;
import X.C05910ld;
import X.EnumC02560aJ;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.util.Collection;

@JacksonStdImpl
public class CollectionDeserializer extends ContainerDeserializerBase<Collection<Object>> implements AbstractC06520n2 {
    public static final long serialVersionUID = -2003828398549708958L;
    public final AnonymousClass0aI _collectionType;
    public final JsonDeserializer<Object> _delegateDeserializer;
    public final JsonDeserializer<Object> _valueDeserializer;
    public final AnonymousClass0nB _valueInstantiator;
    public final AnonymousClass0o3 _valueTypeDeserializer;

    public CollectionDeserializer(AnonymousClass0aI r2, JsonDeserializer<Object> jsonDeserializer, AnonymousClass0o3 r4, AnonymousClass0nB r5, JsonDeserializer<Object> jsonDeserializer2) {
        super(r2._class);
        this._collectionType = r2;
        this._valueDeserializer = jsonDeserializer;
        this._valueTypeDeserializer = r4;
        this._valueInstantiator = r5;
        this._delegateDeserializer = jsonDeserializer2;
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public final JsonDeserializer<Object> A0P() {
        return this._valueDeserializer;
    }

    public CollectionDeserializer A0Q(JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2, AnonymousClass0o3 r9) {
        if (jsonDeserializer == this._delegateDeserializer && jsonDeserializer2 == this._valueDeserializer && r9 == this._valueTypeDeserializer) {
            return this;
        }
        return new CollectionDeserializer(this._collectionType, jsonDeserializer2, r9, this._valueInstantiator, jsonDeserializer);
    }

    /* renamed from: A0R */
    public Collection<Object> A09(AnonymousClass0aT r3, AbstractC02570aK r4) throws IOException, C05910ld {
        Object A0A;
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            A0A = this._valueInstantiator.A09(r4, jsonDeserializer.A09(r3, r4));
        } else {
            if (r3.A0G() == EnumC05930lf.VALUE_STRING) {
                String A0P = r3.A0P();
                if (A0P.length() == 0) {
                    A0A = this._valueInstantiator.A0A(r4, A0P);
                }
            }
            return A0A(r3, r4, (Collection) this._valueInstantiator.A05(r4));
        }
        return (Collection) A0A;
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/0aT;LX/0aK;Ljava/util/Collection<Ljava/lang/Object;>;)Ljava/util/Collection<Ljava/lang/Object;>; */
    public final void A0T(AnonymousClass0aT r5, AbstractC02570aK r6, Collection collection) throws IOException, C05910ld {
        Object A0C;
        if (r6.A0O(EnumC02560aJ.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
            AnonymousClass0o3 r2 = this._valueTypeDeserializer;
            if (r5.A0G() == EnumC05930lf.VALUE_NULL) {
                A0C = null;
            } else if (r2 == null) {
                A0C = jsonDeserializer.A09(r5, r6);
            } else {
                A0C = jsonDeserializer.A0C(r5, r6, r2);
            }
            collection.add(A0C);
            return;
        }
        throw r6.A0B(this._collectionType._class);
    }

    @Override // X.AbstractC06520n2
    public final JsonDeserializer A1w(AbstractC02570aK r4, AbstractC02580aL r5) throws AnonymousClass0aG {
        JsonDeserializer<Object> jsonDeserializer;
        AnonymousClass0nB r1 = this._valueInstantiator;
        if (r1 == null || !r1.A0L()) {
            jsonDeserializer = null;
        } else {
            AnonymousClass0aI A01 = r1.A01(r4._config);
            if (A01 != null) {
                jsonDeserializer = r4.A09(A01, r5);
            } else {
                throw new IllegalArgumentException("Invalid delegate-creator definition for " + this._collectionType + ": value instantiator (" + this._valueInstantiator.getClass().getName() + ") returned true for 'canCreateUsingDelegate()', but null for 'getDelegateType()'");
            }
        }
        JsonDeserializer<?> A05 = StdDeserializer.A05(r4, r5, this._valueDeserializer);
        if (A05 == null) {
            A05 = r4.A09(this._collectionType.A04(), r5);
        } else if (A05 instanceof AbstractC06520n2) {
            A05 = ((AbstractC06520n2) A05).A1w(r4, r5);
        }
        AnonymousClass0o3 r0 = this._valueTypeDeserializer;
        if (r0 != null) {
            r0 = r0.A04(r5);
        }
        return A0Q(jsonDeserializer, A05, r0);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AnonymousClass0aT r2, AbstractC02570aK r3, AnonymousClass0o3 r4) throws IOException, C05910ld {
        return r4.A08(r2, r3);
    }

    /* renamed from: A0S */
    public Collection<Object> A0A(AnonymousClass0aT r5, AbstractC02570aK r6, Collection<Object> collection) throws IOException, C05910ld {
        Object A0C;
        if (r5.A0V()) {
            JsonDeserializer<Object> jsonDeserializer = this._valueDeserializer;
            AnonymousClass0o3 r2 = this._valueTypeDeserializer;
            while (true) {
                EnumC05930lf A0a = r5.A0a();
                if (A0a == EnumC05930lf.END_ARRAY) {
                    break;
                }
                if (A0a == EnumC05930lf.VALUE_NULL) {
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
