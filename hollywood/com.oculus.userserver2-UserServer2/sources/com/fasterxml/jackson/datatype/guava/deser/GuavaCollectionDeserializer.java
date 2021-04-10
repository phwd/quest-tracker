package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC0122Rd;
import X.AnonymousClass1S;
import X.AnonymousClass1V;
import X.AnonymousClass6l;
import X.AnonymousClass7F;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.C00179c;
import X.N4;
import X.Qe;
import X.Rn;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.TreeMultiset;
import java.io.IOException;

public abstract class GuavaCollectionDeserializer<T> extends StdDeserializer<T> implements AnonymousClass1V {
    public final AnonymousClass1S _containerType;
    public final AnonymousClass7F _typeDeserializerForValue;
    public final JsonDeserializer<?> _valueDeserializer;

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final T A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        Object A03;
        Qe qe;
        Object A032;
        if (((B3) rn).A00 != AnonymousClass9p.START_ARRAY) {
            throw null;
        } else if (!(this instanceof GuavaMultisetDeserializer)) {
            GuavaImmutableCollectionDeserializer guavaImmutableCollectionDeserializer = (GuavaImmutableCollectionDeserializer) this;
            JsonDeserializer<?> jsonDeserializer = guavaImmutableCollectionDeserializer._valueDeserializer;
            if (guavaImmutableCollectionDeserializer instanceof ImmutableSortedSetDeserializer) {
                qe = new AnonymousClass6l(NaturalOrdering.A00);
            } else if (guavaImmutableCollectionDeserializer instanceof ImmutableSetDeserializer) {
                qe = new C00179c();
            } else if (!(guavaImmutableCollectionDeserializer instanceof ImmutableMultisetDeserializer)) {
                qe = ImmutableList.A02();
            } else {
                qe = new N4(4);
            }
            while (true) {
                AnonymousClass9p A04 = rn.A04();
                if (A04 == AnonymousClass9p.END_ARRAY) {
                    return (T) qe.build();
                }
                if (A04 == AnonymousClass9p.VALUE_NULL) {
                    A032 = null;
                } else {
                    A032 = jsonDeserializer.A03(rn, rd);
                }
                qe.add(A032);
            }
        } else {
            GuavaMultisetDeserializer guavaMultisetDeserializer = (GuavaMultisetDeserializer) this;
            JsonDeserializer<?> jsonDeserializer2 = guavaMultisetDeserializer._valueDeserializer;
            T t = !(guavaMultisetDeserializer instanceof TreeMultisetDeserializer) ? !(guavaMultisetDeserializer instanceof LinkedHashMultisetDeserializer) ? (T) new HashMultiset() : (T) new LinkedHashMultiset() : (T) new TreeMultiset(NaturalOrdering.A00);
            while (true) {
                AnonymousClass9p A042 = rn.A04();
                if (A042 == AnonymousClass9p.END_ARRAY) {
                    return t;
                }
                if (A042 == AnonymousClass9p.VALUE_NULL) {
                    A03 = null;
                } else {
                    A03 = jsonDeserializer2.A03(rn, rd);
                }
                t.add(A03);
            }
        }
    }
}
