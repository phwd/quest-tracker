package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0264Od;
import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AbstractC1024qt;
import X.EnumC1023qs;
import X.NX;
import X.Ok;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.util.Collection;

@JacksonStdImpl
public final class StringCollectionDeserializer extends ContainerDeserializerBase implements AbstractC0264Od {
    public static final long serialVersionUID = 1;
    public final AbstractC1024qt _collectionType;
    public final JsonDeserializer _delegateDeserializer;
    public final JsonDeserializer _valueDeserializer;
    public final Ok _valueInstantiator;

    public StringCollectionDeserializer(AbstractC1024qt qtVar, Ok ok, JsonDeserializer jsonDeserializer, JsonDeserializer jsonDeserializer2) {
        super(qtVar._class);
        this._collectionType = qtVar;
        this._valueDeserializer = jsonDeserializer2;
        this._valueInstantiator = ok;
        this._delegateDeserializer = jsonDeserializer;
    }

    /* access modifiers changed from: private */
    /* renamed from: A00 */
    public final Collection A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        JsonDeserializer jsonDeserializer = this._delegateDeserializer;
        if (jsonDeserializer != null) {
            return (Collection) this._valueInstantiator.A02(qrVar, jsonDeserializer.A0C(qiVar, qrVar));
        }
        Collection collection = (Collection) this._valueInstantiator.A01(qrVar);
        A01(this, qiVar, qrVar, collection);
        return collection;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        if (r4 == null) goto L_0x0039;
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x001a  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0042  */
    @Override // X.AbstractC0264Od
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.databind.JsonDeserializer A1X(X.AbstractC1022qr r6, X.O5 r7) {
        /*
            r5 = this;
            X.Ok r2 = r5._valueInstantiator
            r3 = 0
            if (r2 == 0) goto L_0x0050
            boolean r1 = r2 instanceof X.C1042rH
            if (r1 == 0) goto L_0x0050
            X.rH r2 = (X.C1042rH) r2
            X.SV r0 = r2._delegateCreator
            if (r0 == 0) goto L_0x0050
            if (r1 != 0) goto L_0x004d
            r0 = r3
        L_0x0012:
            com.fasterxml.jackson.databind.JsonDeserializer r0 = r6.A08(r0, r7)
        L_0x0016:
            com.fasterxml.jackson.databind.JsonDeserializer r4 = r5._valueDeserializer
            if (r4 != 0) goto L_0x0042
            com.fasterxml.jackson.databind.JsonDeserializer r4 = com.fasterxml.jackson.databind.deser.std.StdDeserializer.A05(r6, r7, r4)
            if (r4 != 0) goto L_0x002c
            X.qt r1 = r5._collectionType
            X.qt r1 = r1.A04()
            com.fasterxml.jackson.databind.JsonDeserializer r4 = r6.A08(r1, r7)
        L_0x002a:
            if (r4 == 0) goto L_0x0039
        L_0x002c:
            java.lang.Class r2 = r4.getClass()
            java.lang.Class<com.fasterxml.jackson.databind.annotation.JacksonStdImpl> r1 = com.fasterxml.jackson.databind.annotation.JacksonStdImpl.class
            java.lang.annotation.Annotation r1 = r2.getAnnotation(r1)
            if (r1 == 0) goto L_0x0039
            r4 = r3
        L_0x0039:
            com.fasterxml.jackson.databind.JsonDeserializer r1 = r5._valueDeserializer
            if (r1 != r4) goto L_0x0052
            com.fasterxml.jackson.databind.JsonDeserializer r1 = r5._delegateDeserializer
            if (r1 != r0) goto L_0x0052
            return r5
        L_0x0042:
            boolean r1 = r4 instanceof X.AbstractC0264Od
            if (r1 == 0) goto L_0x002c
            X.Od r4 = (X.AbstractC0264Od) r4
            com.fasterxml.jackson.databind.JsonDeserializer r4 = r4.A1X(r6, r7)
            goto L_0x002a
        L_0x004d:
            X.qt r0 = r2._delegateType
            goto L_0x0012
        L_0x0050:
            r0 = r3
            goto L_0x0016
        L_0x0052:
            X.qt r3 = r5._collectionType
            X.Ok r2 = r5._valueInstantiator
            com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer r1 = new com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer
            r1.<init>(r3, r2, r0, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer.A1X(X.qr, X.O5):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    public static final void A01(StringCollectionDeserializer stringCollectionDeserializer, AbstractC1014qi qiVar, AbstractC1022qr qrVar, Collection collection) {
        String A06;
        Object A0C;
        Object A0C2;
        if (qiVar.A0i()) {
            JsonDeserializer jsonDeserializer = stringCollectionDeserializer._valueDeserializer;
            if (jsonDeserializer != null) {
                while (true) {
                    NX A0o = qiVar.A0o();
                    if (A0o != NX.END_ARRAY) {
                        if (A0o == NX.VALUE_NULL) {
                            A0C = null;
                        } else {
                            A0C = jsonDeserializer.A0C(qiVar, qrVar);
                        }
                        collection.add(A0C);
                    } else {
                        return;
                    }
                }
            } else {
                while (true) {
                    NX A0o2 = qiVar.A0o();
                    if (A0o2 != NX.END_ARRAY) {
                        if (A0o2 == NX.VALUE_NULL) {
                            A06 = null;
                        } else {
                            A06 = StdDeserializer.A06(qiVar, qrVar);
                        }
                        collection.add(A06);
                    } else {
                        return;
                    }
                }
            }
        } else if (qrVar.A0O(EnumC1023qs.ACCEPT_SINGLE_VALUE_AS_ARRAY)) {
            JsonDeserializer jsonDeserializer2 = stringCollectionDeserializer._valueDeserializer;
            if (qiVar.A0U() == NX.VALUE_NULL) {
                A0C2 = null;
            } else if (jsonDeserializer2 == null) {
                A0C2 = StdDeserializer.A06(qiVar, qrVar);
            } else {
                A0C2 = jsonDeserializer2.A0C(qiVar, qrVar);
            }
            collection.add(A0C2);
        } else {
            qrVar.A0J();
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }
}
