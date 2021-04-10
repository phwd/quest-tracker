package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0224Wl;
import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.AbstractC0262Ym;
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
public final class StringCollectionDeserializer extends ContainerDeserializerBase<Collection<String>> implements Zy {
    public static final long serialVersionUID = 1;
    public final AbstractC0224Wl _collectionType;
    public final JsonDeserializer<Object> _delegateDeserializer;
    public final JsonDeserializer<String> _valueDeserializer;
    public final AbstractC0262Ym _valueInstantiator;

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: com.fasterxml.jackson.databind.JsonDeserializer<?> */
    /* JADX WARN: Multi-variable type inference failed */
    public StringCollectionDeserializer(AbstractC0224Wl wl, AbstractC0262Ym ym, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2) {
        super(wl._class);
        this._collectionType = wl;
        this._valueDeserializer = jsonDeserializer2;
        this._valueInstantiator = ym;
        this._delegateDeserializer = jsonDeserializer;
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Collection<String> A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return (Collection) this._valueInstantiator.A09(wn, jsonDeserializer.A09(ww, wn));
        }
        Collection<String> collection = (Collection) this._valueInstantiator.A05(wn);
        A01(ww, wn, collection);
        return collection;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A0A(AbstractC0232Ww ww, AbstractC0226Wn wn, Object obj) throws IOException, q0 {
        Collection collection = (Collection) obj;
        A01(ww, wn, collection);
        return collection;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0029, code lost:
        if (r3 != null) goto L_0x002b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0037  */
    @Override // X.Zy
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<?> A1g(X.AbstractC0226Wn r6, X.AbstractC0227Wo r7) throws X.C0223Wj {
        /*
            r5 = this;
            X.Ym r1 = r5._valueInstantiator
            r2 = 0
            if (r1 == 0) goto L_0x004c
            X.CB r0 = r1.A04()
            if (r0 == 0) goto L_0x004c
            X.8M r0 = r6._config
            X.Wl r0 = r1.A01(r0)
            com.fasterxml.jackson.databind.JsonDeserializer r4 = r6.A06(r0, r7)
        L_0x0015:
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.String> r3 = r5._valueDeserializer
            if (r3 != 0) goto L_0x0041
            com.fasterxml.jackson.databind.JsonDeserializer r3 = com.fasterxml.jackson.databind.deser.std.StdDeserializer.A05(r6, r7, r3)
            if (r3 != 0) goto L_0x002b
            X.Wl r0 = r5._collectionType
            X.Wl r0 = r0.A03()
            com.fasterxml.jackson.databind.JsonDeserializer r3 = r6.A06(r0, r7)
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
            boolean r0 = r3 instanceof X.Zy
            if (r0 == 0) goto L_0x002b
            X.Zy r3 = (X.Zy) r3
            com.fasterxml.jackson.databind.JsonDeserializer r3 = r3.A1g(r6, r7)
            goto L_0x0029
        L_0x004c:
            r4 = r2
            goto L_0x0015
        L_0x004e:
            X.Wl r2 = r5._collectionType
            X.Ym r1 = r5._valueInstantiator
            com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer r0 = new com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer
            r0.<init>(r2, r1, r4, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer.A1g(X.Wn, X.Wo):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/Ww;LX/Wn;Ljava/util/Collection<Ljava/lang/String;>;)Ljava/util/Collection<Ljava/lang/String;>; */
    private final void A01(AbstractC0232Ww ww, AbstractC0226Wn wn, Collection collection) throws IOException, q0 {
        String A06;
        String A09;
        String A092;
        if (ww.A0F()) {
            JsonDeserializer<String> jsonDeserializer = this._valueDeserializer;
            if (jsonDeserializer != null) {
                while (true) {
                    EnumC0470q2 A0a = ww.A0a();
                    if (A0a != EnumC0470q2.END_ARRAY) {
                        if (A0a == EnumC0470q2.VALUE_NULL) {
                            A09 = null;
                        } else {
                            A09 = jsonDeserializer.A09(ww, wn);
                        }
                        collection.add(A09);
                    } else {
                        return;
                    }
                }
            } else {
                while (true) {
                    EnumC0470q2 A0a2 = ww.A0a();
                    if (A0a2 != EnumC0470q2.END_ARRAY) {
                        if (A0a2 == EnumC0470q2.VALUE_NULL) {
                            A06 = null;
                        } else {
                            A06 = StdDeserializer.A06(ww, wn);
                        }
                        collection.add(A06);
                    } else {
                        return;
                    }
                }
            }
        } else if (wn.A0L(EnumC0225Wm.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            JsonDeserializer<String> jsonDeserializer2 = this._valueDeserializer;
            if (ww.A0Z() == EnumC0470q2.VALUE_NULL) {
                A092 = null;
            } else if (jsonDeserializer2 == null) {
                A092 = StdDeserializer.A06(ww, wn);
            } else {
                A092 = jsonDeserializer2.A09(ww, wn);
            }
            collection.add(A092);
        } else {
            throw wn.A08(this._collectionType._class);
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        return v4.A08(ww, wn);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public final JsonDeserializer<Object> A0P() {
        return this._valueDeserializer;
    }
}
