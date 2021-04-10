package com.fasterxml.jackson.datatype.guava.deser;

import X.AbstractC0264Od;
import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.C00323q;
import X.C1163uG;
import X.DS;
import X.NX;
import X.O5;
import X.PR;
import X.SX;
import X.Tx;
import X.UM;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.LinkedHashMultiset;
import com.google.common.collect.NaturalOrdering;
import com.google.common.collect.TreeMultiset;

public abstract class GuavaCollectionDeserializer extends StdDeserializer implements AbstractC0264Od {
    public final C00323q _containerType;
    public final PR _typeDeserializerForValue;
    public final JsonDeserializer _valueDeserializer;

    @Override // X.AbstractC0264Od
    public final JsonDeserializer A1X(AbstractC1022qr qrVar, O5 o5) {
        JsonDeserializer jsonDeserializer = this._valueDeserializer;
        PR pr = this._typeDeserializerForValue;
        if (jsonDeserializer == null) {
            jsonDeserializer = qrVar.A08(this._containerType.A04(), o5);
        }
        if (pr != null) {
            pr = pr.A03(o5);
        }
        if (jsonDeserializer == this._valueDeserializer && pr == this._typeDeserializerForValue) {
            return this;
        }
        if (this instanceof ImmutableSortedSetDeserializer) {
            return new ImmutableSortedSetDeserializer(this._containerType, pr, jsonDeserializer);
        }
        if (this instanceof ImmutableSetDeserializer) {
            return new ImmutableSetDeserializer(this._containerType, pr, jsonDeserializer);
        }
        if (this instanceof ImmutableMultisetDeserializer) {
            return new ImmutableMultisetDeserializer(this._containerType, pr, jsonDeserializer);
        }
        if (this instanceof ImmutableListDeserializer) {
            return new ImmutableListDeserializer(this._containerType, pr, jsonDeserializer);
        }
        if (this instanceof TreeMultisetDeserializer) {
            return new TreeMultisetDeserializer(this._containerType, pr, jsonDeserializer);
        }
        if (!(this instanceof LinkedHashMultisetDeserializer)) {
            return new HashMultisetDeserializer(this._containerType, pr, jsonDeserializer);
        }
        return new LinkedHashMultisetDeserializer(this._containerType, pr, jsonDeserializer);
    }

    public GuavaCollectionDeserializer(C00323q r1, PR pr, JsonDeserializer jsonDeserializer) {
        super(r1);
        this._containerType = r1;
        this._typeDeserializerForValue = pr;
        this._valueDeserializer = jsonDeserializer;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        UM um;
        Object A09;
        Tx tx;
        Object A092;
        if (qiVar.A0U() != NX.START_ARRAY) {
            qrVar.A0J();
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        } else if (!(this instanceof GuavaMultisetDeserializer)) {
            GuavaImmutableCollectionDeserializer guavaImmutableCollectionDeserializer = (GuavaImmutableCollectionDeserializer) this;
            JsonDeserializer jsonDeserializer = guavaImmutableCollectionDeserializer._valueDeserializer;
            PR pr = guavaImmutableCollectionDeserializer._typeDeserializerForValue;
            if (guavaImmutableCollectionDeserializer instanceof ImmutableSortedSetDeserializer) {
                tx = new SX(NaturalOrdering.A00);
            } else if (guavaImmutableCollectionDeserializer instanceof ImmutableSetDeserializer) {
                tx = new DS();
            } else if (!(guavaImmutableCollectionDeserializer instanceof ImmutableMultisetDeserializer)) {
                tx = ImmutableList.A02();
            } else {
                tx = new C1163uG(4);
            }
            while (true) {
                NX A0o = qiVar.A0o();
                if (A0o == NX.END_ARRAY) {
                    return tx.build();
                }
                if (A0o == NX.VALUE_NULL) {
                    A092 = null;
                } else if (pr == null) {
                    A092 = jsonDeserializer.A0C(qiVar, qrVar);
                } else {
                    A092 = jsonDeserializer.A09(qiVar, qrVar, pr);
                }
                tx.add(A092);
            }
        } else {
            GuavaMultisetDeserializer guavaMultisetDeserializer = (GuavaMultisetDeserializer) this;
            JsonDeserializer jsonDeserializer2 = guavaMultisetDeserializer._valueDeserializer;
            PR pr2 = guavaMultisetDeserializer._typeDeserializerForValue;
            if (guavaMultisetDeserializer instanceof TreeMultisetDeserializer) {
                um = new TreeMultiset(NaturalOrdering.A00);
            } else if (!(guavaMultisetDeserializer instanceof LinkedHashMultisetDeserializer)) {
                um = new HashMultiset();
            } else {
                um = new LinkedHashMultiset();
            }
            while (true) {
                NX A0o2 = qiVar.A0o();
                if (A0o2 == NX.END_ARRAY) {
                    return um;
                }
                if (A0o2 == NX.VALUE_NULL) {
                    A09 = null;
                } else if (pr2 == null) {
                    A09 = jsonDeserializer2.A0C(qiVar, qrVar);
                } else {
                    A09 = jsonDeserializer2.A09(qiVar, qrVar, pr2);
                }
                um.add(A09);
            }
        }
    }
}
