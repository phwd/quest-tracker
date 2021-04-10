package com.fasterxml.jackson.databind.deser;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.AbstractC1034r7;
import X.AnonymousClass0q;
import X.C0262Oa;
import X.C0267Oj;
import X.C0271Os;
import X.C0273Ou;
import X.NX;
import X.O4;
import X.Oc;
import X.Oo;
import X.QC;
import com.oculus.aidl.OVRServiceInterface;
import java.util.HashSet;
import java.util.Map;

public class BuilderBasedDeserializer extends BeanDeserializerBase {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0q _buildMethod;

    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0094, code lost:
        if (r1 == X.NX.START_OBJECT) goto L_0x0096;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0096, code lost:
        r1 = r6.A0o();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x009c, code lost:
        if (r1 != X.NX.FIELD_NAME) goto L_0x00e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x009e, code lost:
        r1 = r6.A0b();
        r6.A0o();
        r0 = r5._beanProperties.A00(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ab, code lost:
        if (r0 == null) goto L_0x00b2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r8 = r0.A05(r6, r7, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00b2, code lost:
        r0 = r5._ignorableProps;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b4, code lost:
        if (r0 == null) goto L_0x00c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00ba, code lost:
        if (r0.contains(r1) == false) goto L_0x00c0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x00bc, code lost:
        r6.A0T();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x00c0, code lost:
        r0 = r5._anySetter;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00c2, code lost:
        if (r0 == null) goto L_0x00c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x00c4, code lost:
        r0.A01(r6, r7, r8, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x00c8, code lost:
        r5.A0L(r6, r7, r8, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x00cc, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x00cd, code lost:
        r5.A0d(r0, r8, r1, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00d7, code lost:
        throw new java.lang.RuntimeException("Redex: Unreachable code after no-return invoke");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final java.lang.Object A00(com.fasterxml.jackson.databind.deser.BuilderBasedDeserializer r5, X.AbstractC1014qi r6, X.AbstractC1022qr r7, java.lang.Object r8) {
        /*
        // Method dump skipped, instructions count: 225
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BuilderBasedDeserializer.A00(com.fasterxml.jackson.databind.deser.BuilderBasedDeserializer, X.qi, X.qr, java.lang.Object):java.lang.Object");
    }

    public static final Object A01(BuilderBasedDeserializer builderBasedDeserializer, AbstractC1014qi qiVar, AbstractC1022qr qrVar, Object obj) {
        Class cls;
        if (builderBasedDeserializer._needViewProcesing) {
            cls = qrVar._view;
        } else {
            cls = null;
        }
        C0271Os os = new C0271Os(builderBasedDeserializer._externalTypeIdHandler);
        while (qiVar.A0U() != NX.END_OBJECT) {
            String A0b = qiVar.A0b();
            qiVar.A0o();
            AbstractC1034r7 A00 = builderBasedDeserializer._beanProperties.A00(A0b);
            if (A00 == null) {
                HashSet hashSet = builderBasedDeserializer._ignorableProps;
                if (hashSet == null || !hashSet.contains(A0b)) {
                    if (!os.A02(qiVar, qrVar, A0b, obj)) {
                        C0267Oj oj = builderBasedDeserializer._anySetter;
                        if (oj != null) {
                            try {
                                oj.A01(qiVar, qrVar, obj, A0b);
                            } catch (Exception e) {
                                builderBasedDeserializer.A0d(e, obj, A0b, qrVar);
                                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                            }
                        } else {
                            builderBasedDeserializer.A0L(qiVar, qrVar, obj, A0b);
                        }
                    }
                    qiVar.A0o();
                }
            } else if (cls == null || A00.A0A(cls)) {
                try {
                    obj = A00.A05(qiVar, qrVar, obj);
                    qiVar.A0o();
                } catch (Exception e2) {
                    builderBasedDeserializer.A0d(e2, obj, A0b, qrVar);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            qiVar.A0T();
            qiVar.A0o();
        }
        os.A01(qiVar, qrVar, obj);
        return obj;
    }

    public static final Object A03(BuilderBasedDeserializer builderBasedDeserializer, AbstractC1022qr qrVar, Object obj) {
        try {
            return builderBasedDeserializer._buildMethod.A00.invoke(obj, new Object[0]);
        } catch (Exception e) {
            builderBasedDeserializer.A0c(e, qrVar);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public static final Object A02(BuilderBasedDeserializer builderBasedDeserializer, AbstractC1014qi qiVar, AbstractC1022qr qrVar, Object obj, Class cls) {
        NX A0U = qiVar.A0U();
        while (A0U == NX.FIELD_NAME) {
            String A0b = qiVar.A0b();
            qiVar.A0o();
            AbstractC1034r7 A00 = builderBasedDeserializer._beanProperties.A00(A0b);
            if (A00 == null) {
                HashSet hashSet = builderBasedDeserializer._ignorableProps;
                if (hashSet == null || !hashSet.contains(A0b)) {
                    C0267Oj oj = builderBasedDeserializer._anySetter;
                    if (oj != null) {
                        oj.A01(qiVar, qrVar, obj, A0b);
                    } else {
                        builderBasedDeserializer.A0L(qiVar, qrVar, obj, A0b);
                    }
                    A0U = qiVar.A0o();
                }
            } else if (A00.A0A(cls)) {
                try {
                    obj = A00.A05(qiVar, qrVar, obj);
                    A0U = qiVar.A0o();
                } catch (Exception e) {
                    builderBasedDeserializer.A0d(e, obj, A0b, qrVar);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            }
            qiVar.A0T();
            A0U = qiVar.A0o();
        }
        return obj;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        Object obj;
        NX A0U = qiVar.A0U();
        if (A0U == NX.START_OBJECT) {
            qiVar.A0o();
            if (this._vanillaProcessing) {
                Object A01 = this._valueInstantiator.A01(qrVar);
                while (qiVar.A0U() != NX.END_OBJECT) {
                    String A0b = qiVar.A0b();
                    qiVar.A0o();
                    AbstractC1034r7 A00 = this._beanProperties.A00(A0b);
                    if (A00 != null) {
                        try {
                            A01 = A00.A05(qiVar, qrVar, A01);
                        } catch (Exception e) {
                            A0d(e, A01, A0b, qrVar);
                            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                        }
                    } else {
                        A0Z(qiVar, qrVar, A01, A0b);
                    }
                    qiVar.A0o();
                }
                return A03(this, qrVar, A01);
            }
        } else {
            switch (Oc.A00[A0U.ordinal()]) {
                case 1:
                    obj = A0V(qiVar, qrVar);
                    break;
                case 2:
                    obj = A0U(qiVar, qrVar);
                    break;
                case 3:
                    obj = A0T(qiVar, qrVar);
                    break;
                case 4:
                    return qiVar.A0Z();
                case 5:
                case 6:
                    obj = A0S(qiVar, qrVar);
                    break;
                case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                    obj = A0R(qiVar, qrVar);
                    break;
                case 8:
                case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                    break;
                default:
                    qrVar.A0J();
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
            return A03(this, qrVar, obj);
        }
        obj = A0Q(qiVar, qrVar);
        return A03(this, qrVar, obj);
    }

    public BuilderBasedDeserializer(C0262Oa oa, O4 o4, Oo oo, Map map, HashSet hashSet, boolean z, boolean z2) {
        super(oa, o4, oo, map, hashSet, z, z2);
        this._buildMethod = oa.A04;
        if (this._objectIdReader != null) {
            StringBuilder sb = new StringBuilder("Can not use Object Id with Builder-based deserialization (type ");
            sb.append(o4.A00);
            sb.append(")");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer builderBasedDeserializer, C0273Ou ou) {
        super(builderBasedDeserializer, ou);
        this._buildMethod = builderBasedDeserializer._buildMethod;
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer builderBasedDeserializer, QC qc) {
        super(builderBasedDeserializer, qc);
        this._buildMethod = builderBasedDeserializer._buildMethod;
    }

    public BuilderBasedDeserializer(BuilderBasedDeserializer builderBasedDeserializer, HashSet hashSet) {
        super(builderBasedDeserializer, hashSet);
        this._buildMethod = builderBasedDeserializer._buildMethod;
    }
}
