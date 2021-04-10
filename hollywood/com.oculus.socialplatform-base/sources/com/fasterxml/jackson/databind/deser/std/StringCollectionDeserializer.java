package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02190iF;
import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04230pb;
import X.AbstractC04300pk;
import X.AbstractC04520qa;
import X.C03620oC;
import X.EnumC02200iG;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.util.Collection;

@JacksonStdImpl
public final class StringCollectionDeserializer extends ContainerDeserializerBase<Collection<String>> implements AbstractC04230pb {
    public static final long serialVersionUID = 1;
    public final AbstractC02190iF _collectionType;
    public final JsonDeserializer<Object> _delegateDeserializer;
    public final JsonDeserializer<String> _valueDeserializer;
    public final AbstractC04300pk _valueInstantiator;

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: com.fasterxml.jackson.databind.JsonDeserializer<?> */
    /* JADX WARN: Multi-variable type inference failed */
    public StringCollectionDeserializer(AbstractC02190iF r2, AbstractC04300pk r3, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2) {
        super(r2._class);
        this._collectionType = r2;
        this._valueDeserializer = jsonDeserializer2;
        this._valueInstantiator = r3;
        this._delegateDeserializer = jsonDeserializer;
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Collection<String> A0A(AbstractC02280iQ r3, AbstractC02210iH r4) throws IOException, C03620oC {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return (Collection) this._valueInstantiator.A09(r4, jsonDeserializer.A0A(r3, r4));
        }
        Collection<String> collection = (Collection) this._valueInstantiator.A05(r4);
        A01(r3, r4, collection);
        return collection;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A0C(AbstractC02280iQ r1, AbstractC02210iH r2, Object obj) throws IOException, C03620oC {
        Collection collection = (Collection) obj;
        A01(r1, r2, collection);
        return collection;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0029, code lost:
        if (r3 != null) goto L_0x002b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0037  */
    @Override // X.AbstractC04230pb
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<?> A2O(X.AbstractC02210iH r6, X.AbstractC02220iI r7) throws X.C02180iD {
        /*
            r5 = this;
            X.0pk r1 = r5._valueInstantiator
            r2 = 0
            if (r1 == 0) goto L_0x004c
            X.0Ow r0 = r1.A04()
            if (r0 == 0) goto L_0x004c
            X.0HU r0 = r6._config
            X.0iF r0 = r1.A01(r0)
            com.fasterxml.jackson.databind.JsonDeserializer r4 = r6.A09(r0, r7)
        L_0x0015:
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.String> r3 = r5._valueDeserializer
            if (r3 != 0) goto L_0x0041
            com.fasterxml.jackson.databind.JsonDeserializer r3 = com.fasterxml.jackson.databind.deser.std.StdDeserializer.A05(r6, r7, r3)
            if (r3 != 0) goto L_0x002b
            X.0iF r0 = r5._collectionType
            X.0iF r0 = r0.A04()
            com.fasterxml.jackson.databind.JsonDeserializer r3 = r6.A09(r0, r7)
        L_0x0029:
            if (r3 == 0) goto L_0x0038
        L_0x002b:
            java.lang.Class r1 = r3.getClass()
            java.lang.Class<com.fasterxml.jackson.databind.annotation.JacksonStdImpl> r0 = com.fasterxml.jackson.databind.annotation.JacksonStdImpl.class
            java.lang.annotation.Annotation r0 = r1.getAnnotation(r0)
            if (r0 == 0) goto L_0x0038
            r3 = r2
        L_0x0038:
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.String> r0 = r5._valueDeserializer
            if (r0 != r3) goto L_0x004e
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.Object> r0 = r5._delegateDeserializer
            if (r0 != r4) goto L_0x004e
            return r5
        L_0x0041:
            boolean r0 = r3 instanceof X.AbstractC04230pb
            if (r0 == 0) goto L_0x002b
            X.0pb r3 = (X.AbstractC04230pb) r3
            com.fasterxml.jackson.databind.JsonDeserializer r3 = r3.A2O(r6, r7)
            goto L_0x0029
        L_0x004c:
            r4 = r2
            goto L_0x0015
        L_0x004e:
            X.0iF r2 = r5._collectionType
            X.0pk r1 = r5._valueInstantiator
            com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer r0 = new com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer
            r0.<init>(r2, r1, r4, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer.A2O(X.0iH, X.0iI):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/0iQ;LX/0iH;Ljava/util/Collection<Ljava/lang/String;>;)Ljava/util/Collection<Ljava/lang/String;>; */
    private final void A01(AbstractC02280iQ r4, AbstractC02210iH r5, Collection collection) throws IOException, C03620oC {
        String A06;
        String A0A;
        String A0A2;
        if (r4.A0K()) {
            JsonDeserializer<String> jsonDeserializer = this._valueDeserializer;
            if (jsonDeserializer != null) {
                while (true) {
                    EnumC03640oE A0j = r4.A0j();
                    if (A0j != EnumC03640oE.END_ARRAY) {
                        if (A0j == EnumC03640oE.VALUE_NULL) {
                            A0A = null;
                        } else {
                            A0A = jsonDeserializer.A0A(r4, r5);
                        }
                        collection.add(A0A);
                    } else {
                        return;
                    }
                }
            } else {
                while (true) {
                    EnumC03640oE A0j2 = r4.A0j();
                    if (A0j2 != EnumC03640oE.END_ARRAY) {
                        if (A0j2 == EnumC03640oE.VALUE_NULL) {
                            A06 = null;
                        } else {
                            A06 = StdDeserializer.A06(r4, r5);
                        }
                        collection.add(A06);
                    } else {
                        return;
                    }
                }
            }
        } else if (r5.A0P(EnumC02200iG.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            JsonDeserializer<String> jsonDeserializer2 = this._valueDeserializer;
            if (r4.A0i() == EnumC03640oE.VALUE_NULL) {
                A0A2 = null;
            } else if (jsonDeserializer2 == null) {
                A0A2 = StdDeserializer.A06(r4, r5);
            } else {
                A0A2 = jsonDeserializer2.A0A(r4, r5);
            }
            collection.add(A0A2);
        } else {
            throw r5.A0B(this._collectionType._class);
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0B(AbstractC02280iQ r2, AbstractC02210iH r3, AbstractC04520qa r4) throws IOException, C03620oC {
        return r4.A08(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public final JsonDeserializer<Object> A0P() {
        return this._valueDeserializer;
    }
}
