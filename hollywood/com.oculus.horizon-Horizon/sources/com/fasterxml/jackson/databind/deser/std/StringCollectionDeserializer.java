package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04000gb;
import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AbstractC05430l6;
import X.AnonymousClass0jg;
import X.AnonymousClass0lG;
import X.AnonymousClass0m9;
import X.EnumC04010gf;
import X.EnumC04820ji;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.util.Collection;

@JacksonStdImpl
public final class StringCollectionDeserializer extends ContainerDeserializerBase<Collection<String>> implements AbstractC05430l6 {
    public static final long serialVersionUID = 1;
    public final AbstractC04000gb _collectionType;
    public final JsonDeserializer<Object> _delegateDeserializer;
    public final JsonDeserializer<String> _valueDeserializer;
    public final AnonymousClass0lG _valueInstantiator;

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: com.fasterxml.jackson.databind.JsonDeserializer<?> */
    /* JADX WARN: Multi-variable type inference failed */
    public StringCollectionDeserializer(AbstractC04000gb r2, AnonymousClass0lG r3, JsonDeserializer<?> jsonDeserializer, JsonDeserializer<?> jsonDeserializer2) {
        super(r2._class);
        this._collectionType = r2;
        this._valueDeserializer = jsonDeserializer2;
        this._valueInstantiator = r3;
        this._delegateDeserializer = jsonDeserializer;
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Collection<String> A09(AbstractC04100gp r3, AbstractC04020gg r4) throws IOException, AnonymousClass0jg {
        JsonDeserializer<Object> jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return (Collection) this._valueInstantiator.A09(r4, jsonDeserializer.A09(r3, r4));
        }
        Collection<String> collection = (Collection) this._valueInstantiator.A05(r4);
        A01(r3, r4, collection);
        return collection;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final /* bridge */ /* synthetic */ Object A0A(AbstractC04100gp r1, AbstractC04020gg r2, Object obj) throws IOException, AnonymousClass0jg {
        Collection collection = (Collection) obj;
        A01(r1, r2, collection);
        return collection;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0029, code lost:
        if (r3 != null) goto L_0x002b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0037  */
    @Override // X.AbstractC05430l6
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer<?> A21(X.AbstractC04020gg r6, X.AbstractC04030gh r7) throws X.C03990gZ {
        /*
            r5 = this;
            X.0lG r1 = r5._valueInstantiator
            r2 = 0
            if (r1 == 0) goto L_0x004c
            X.0GV r0 = r1.A04()
            if (r0 == 0) goto L_0x004c
            X.08X r0 = r6._config
            X.0gb r0 = r1.A01(r0)
            com.fasterxml.jackson.databind.JsonDeserializer r4 = r6.A05(r0, r7)
        L_0x0015:
            com.fasterxml.jackson.databind.JsonDeserializer<java.lang.String> r3 = r5._valueDeserializer
            if (r3 != 0) goto L_0x0041
            com.fasterxml.jackson.databind.JsonDeserializer r3 = com.fasterxml.jackson.databind.deser.std.StdDeserializer.A05(r6, r7, r3)
            if (r3 != 0) goto L_0x002b
            X.0gb r0 = r5._collectionType
            X.0gb r0 = r0.A03()
            com.fasterxml.jackson.databind.JsonDeserializer r3 = r6.A05(r0, r7)
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
            boolean r0 = r3 instanceof X.AbstractC05430l6
            if (r0 == 0) goto L_0x002b
            X.0l6 r3 = (X.AbstractC05430l6) r3
            com.fasterxml.jackson.databind.JsonDeserializer r3 = r3.A21(r6, r7)
            goto L_0x0029
        L_0x004c:
            r4 = r2
            goto L_0x0015
        L_0x004e:
            X.0gb r2 = r5._collectionType
            X.0lG r1 = r5._valueInstantiator
            com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer r0 = new com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer
            r0.<init>(r2, r1, r4, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer.A21(X.0gg, X.0gh):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    /* JADX WARN: Incorrect return type in method signature: (LX/0gp;LX/0gg;Ljava/util/Collection<Ljava/lang/String;>;)Ljava/util/Collection<Ljava/lang/String;>; */
    private final void A01(AbstractC04100gp r4, AbstractC04020gg r5, Collection collection) throws IOException, AnonymousClass0jg {
        String A06;
        String A09;
        String A092;
        if (r4.A0G()) {
            JsonDeserializer<String> jsonDeserializer = this._valueDeserializer;
            if (jsonDeserializer != null) {
                while (true) {
                    EnumC04820ji A0b = r4.A0b();
                    if (A0b != EnumC04820ji.END_ARRAY) {
                        if (A0b == EnumC04820ji.VALUE_NULL) {
                            A09 = null;
                        } else {
                            A09 = jsonDeserializer.A09(r4, r5);
                        }
                        collection.add(A09);
                    } else {
                        return;
                    }
                }
            } else {
                while (true) {
                    EnumC04820ji A0b2 = r4.A0b();
                    if (A0b2 != EnumC04820ji.END_ARRAY) {
                        if (A0b2 == EnumC04820ji.VALUE_NULL) {
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
        } else if (r5.A0I(EnumC04010gf.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            JsonDeserializer<String> jsonDeserializer2 = this._valueDeserializer;
            if (r4.A0a() == EnumC04820ji.VALUE_NULL) {
                A092 = null;
            } else if (jsonDeserializer2 == null) {
                A092 = StdDeserializer.A06(r4, r5);
            } else {
                A092 = jsonDeserializer2.A09(r4, r5);
            }
            collection.add(A092);
        } else {
            throw null;
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC04100gp r2, AbstractC04020gg r3, AnonymousClass0m9 r4) throws IOException, AnonymousClass0jg {
        return r4.A08(r2, r3);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.ContainerDeserializerBase
    public final JsonDeserializer<Object> A0P() {
        return this._valueDeserializer;
    }
}
