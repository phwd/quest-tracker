package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC1014qi;
import X.AbstractC1022qr;
import X.EnumC1023qs;
import X.NX;
import X.P7;
import X.QD;
import X.QE;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.oculus.aidl.OVRServiceInterface;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@JacksonStdImpl
public class UntypedObjectDeserializer extends StdDeserializer {
    public static final UntypedObjectDeserializer A00 = new UntypedObjectDeserializer();
    public static final Object[] A01 = new Object[0];
    public static final long serialVersionUID = 1;

    public UntypedObjectDeserializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        switch (P7.A00[qiVar.A0U().ordinal()]) {
            case 1:
                return A00(qiVar, qrVar);
            case 2:
                if (qrVar.A0O(EnumC1023qs.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                    if (qiVar.A0o() == NX.END_ARRAY) {
                        return A01;
                    }
                    QE A0H = qrVar.A0H();
                    Object[] A012 = A0H.A01();
                    int i = 0;
                    while (true) {
                        Object A0C = A0C(qiVar, qrVar);
                        if (i >= A012.length) {
                            A012 = A0H.A02(A012);
                            i = 0;
                        }
                        int i2 = i + 1;
                        A012[i] = A0C;
                        if (qiVar.A0o() == NX.END_ARRAY) {
                            int i3 = A0H.A00 + i2;
                            Object[] objArr = new Object[i3];
                            QE.A00(A0H, objArr, i3, A012, i2);
                            return objArr;
                        }
                        i = i2;
                    }
                } else if (qiVar.A0o() == NX.END_ARRAY) {
                    return new ArrayList(4);
                } else {
                    QE A0H2 = qrVar.A0H();
                    Object[] A013 = A0H2.A01();
                    int i4 = 0;
                    int i5 = 0;
                    while (true) {
                        Object A0C2 = A0C(qiVar, qrVar);
                        i4++;
                        if (i5 >= A013.length) {
                            A013 = A0H2.A02(A013);
                            i5 = 0;
                        }
                        int i6 = i5 + 1;
                        A013[i5] = A0C2;
                        if (qiVar.A0o() == NX.END_ARRAY) {
                            ArrayList arrayList = new ArrayList(i4 + (i4 >> 3) + 1);
                            QD qd = A0H2.A01;
                            while (true) {
                                int i7 = 0;
                                if (qd != null) {
                                    Object[] objArr2 = qd.A01;
                                    int length = objArr2.length;
                                    while (i7 < length) {
                                        arrayList.add(objArr2[i7]);
                                        i7++;
                                    }
                                    qd = qd.A00;
                                } else {
                                    while (i7 < i6) {
                                        arrayList.add(A013[i7]);
                                        i7++;
                                    }
                                    return arrayList;
                                }
                            }
                        } else {
                            i5 = i6;
                        }
                    }
                }
            case 3:
                return A00(qiVar, qrVar);
            case 4:
                return qiVar.A0Z();
            case 5:
                return qiVar.A0p();
            case 6:
                if (qrVar.A0O(EnumC1023qs.USE_BIG_INTEGER_FOR_INTS)) {
                    return qiVar.A0d();
                }
                return qiVar.A0Y();
            case OVRServiceInterface.Stub.TRANSACTION_getEntitlementCheckBundle /*{ENCODED_INT: 7}*/:
                if (qrVar.A0O(EnumC1023qs.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return qiVar.A0c();
                }
                return Double.valueOf(qiVar.A0F());
            case 8:
                return Boolean.TRUE;
            case OVRServiceInterface.Stub.TRANSACTION_getLatestAvailableAppInformation /*{ENCODED_INT: 9}*/:
                return Boolean.FALSE;
            case 10:
                return null;
            default:
                qrVar.A0J();
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    private final Object A00(AbstractC1014qi qiVar, AbstractC1022qr qrVar) {
        NX A0U = qiVar.A0U();
        if (A0U == NX.START_OBJECT) {
            A0U = qiVar.A0o();
        }
        NX nx = NX.FIELD_NAME;
        if (A0U != nx) {
            return new LinkedHashMap(4);
        }
        String A0p = qiVar.A0p();
        qiVar.A0o();
        Object A0C = A0C(qiVar, qrVar);
        if (qiVar.A0o() != nx) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(4);
            linkedHashMap.put(A0p, A0C);
            return linkedHashMap;
        }
        String A0p2 = qiVar.A0p();
        qiVar.A0o();
        Object A0C2 = A0C(qiVar, qrVar);
        if (qiVar.A0o() != nx) {
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(4);
            linkedHashMap2.put(A0p, A0C);
            linkedHashMap2.put(A0p2, A0C2);
            return linkedHashMap2;
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(A0p, A0C);
        linkedHashMap3.put(A0p2, A0C2);
        do {
            String A0p3 = qiVar.A0p();
            qiVar.A0o();
            linkedHashMap3.put(A0p3, A0C(qiVar, qrVar));
        } while (qiVar.A0o() != NX.END_OBJECT);
        return linkedHashMap3;
    }
}
