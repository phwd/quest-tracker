package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.AbstractC02280iQ;
import X.AbstractC04520qa;
import X.AnonymousClass0q8;
import X.AnonymousClass0rU;
import X.C03620oC;
import X.C04890rT;
import X.EnumC02200iG;
import X.EnumC03640oE;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@JacksonStdImpl
public class UntypedObjectDeserializer extends StdDeserializer<Object> {
    public static final UntypedObjectDeserializer A00 = new UntypedObjectDeserializer();
    public static final Object[] A01 = new Object[0];
    public static final long serialVersionUID = 1;

    public UntypedObjectDeserializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0A(AbstractC02280iQ r9, AbstractC02210iH r10) throws IOException, C03620oC {
        switch (AnonymousClass0q8.A00[r9.A0i().ordinal()]) {
            case 1:
                return A00(r9, r10);
            case 2:
                if (r10.A0P(EnumC02200iG.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                    if (r9.A0j() == EnumC03640oE.END_ARRAY) {
                        return A01;
                    }
                    AnonymousClass0rU A0L = r10.A0L();
                    Object[] A012 = A0L.A01();
                    int i = 0;
                    while (true) {
                        Object A0A = A0A(r9, r10);
                        if (i >= A012.length) {
                            A012 = A0L.A02(A012);
                            i = 0;
                        }
                        int i2 = i + 1;
                        A012[i] = A0A;
                        if (r9.A0j() == EnumC03640oE.END_ARRAY) {
                            int i3 = A0L.A00 + i2;
                            Object[] objArr = new Object[i3];
                            AnonymousClass0rU.A00(A0L, objArr, i3, A012, i2);
                            return objArr;
                        }
                        i = i2;
                    }
                } else if (r9.A0j() == EnumC03640oE.END_ARRAY) {
                    return new ArrayList(4);
                } else {
                    AnonymousClass0rU A0L2 = r10.A0L();
                    Object[] A013 = A0L2.A01();
                    int i4 = 0;
                    int i5 = 0;
                    while (true) {
                        Object A0A2 = A0A(r9, r10);
                        i4++;
                        if (i5 >= A013.length) {
                            A013 = A0L2.A02(A013);
                            i5 = 0;
                        }
                        int i6 = i5 + 1;
                        A013[i5] = A0A2;
                        if (r9.A0j() == EnumC03640oE.END_ARRAY) {
                            ArrayList arrayList = new ArrayList(i4 + (i4 >> 3) + 1);
                            C04890rT r0 = A0L2.A01;
                            while (true) {
                                int i7 = 0;
                                if (r0 != null) {
                                    Object[] objArr2 = r0.A01;
                                    int length = objArr2.length;
                                    while (i7 < length) {
                                        arrayList.add(objArr2[i7]);
                                        i7++;
                                    }
                                    r0 = r0.A00;
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
                return A00(r9, r10);
            case 4:
                return r9.A0Z();
            case 5:
                return r9.A0m();
            case 6:
                if (r10.A0P(EnumC02200iG.USE_BIG_INTEGER_FOR_INTS)) {
                    return r9.A0b();
                }
                return r9.A0Y();
            case 7:
                if (r10.A0P(EnumC02200iG.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return r9.A0a();
                }
                return Double.valueOf(r9.A0R());
            case 8:
                return Boolean.TRUE;
            case 9:
                return Boolean.FALSE;
            case 10:
                return null;
            default:
                throw r10.A0B(Object.class);
        }
    }

    private final Object A00(AbstractC02280iQ r8, AbstractC02210iH r9) throws IOException, C03620oC {
        EnumC03640oE A0i = r8.A0i();
        if (A0i == EnumC03640oE.START_OBJECT) {
            A0i = r8.A0j();
        }
        EnumC03640oE r0 = EnumC03640oE.FIELD_NAME;
        if (A0i != r0) {
            return new LinkedHashMap(4);
        }
        String A0m = r8.A0m();
        r8.A0j();
        Object A0A = A0A(r8, r9);
        if (r8.A0j() != r0) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(4);
            linkedHashMap.put(A0m, A0A);
            return linkedHashMap;
        }
        String A0m2 = r8.A0m();
        r8.A0j();
        Object A0A2 = A0A(r8, r9);
        if (r8.A0j() != r0) {
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(4);
            linkedHashMap2.put(A0m, A0A);
            linkedHashMap2.put(A0m2, A0A2);
            return linkedHashMap2;
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(A0m, A0A);
        linkedHashMap3.put(A0m2, A0A2);
        do {
            String A0m3 = r8.A0m();
            r8.A0j();
            linkedHashMap3.put(A0m3, A0A(r8, r9));
        } while (r8.A0j() != EnumC03640oE.END_OBJECT);
        return linkedHashMap3;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0B(AbstractC02280iQ r3, AbstractC02210iH r4, AbstractC04520qa r5) throws IOException, C03620oC {
        switch (AnonymousClass0q8.A00[r3.A0i().ordinal()]) {
            case 1:
            case 2:
            case 3:
                return r5.A07(r3, r4);
            case 4:
                return r3.A0Z();
            case 5:
                return r3.A0m();
            case 6:
                if (r4.A0P(EnumC02200iG.USE_BIG_INTEGER_FOR_INTS)) {
                    return r3.A0b();
                }
                return r3.A0Y();
            case 7:
                if (r4.A0P(EnumC02200iG.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return r3.A0a();
                }
                return Double.valueOf(r3.A0R());
            case 8:
                return Boolean.TRUE;
            case 9:
                return Boolean.FALSE;
            case 10:
                return null;
            default:
                throw r4.A0B(Object.class);
        }
    }
}
