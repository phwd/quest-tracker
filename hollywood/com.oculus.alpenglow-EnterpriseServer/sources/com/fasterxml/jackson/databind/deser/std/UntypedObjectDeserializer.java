package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.AnonymousClass0aT;
import X.AnonymousClass0nZ;
import X.AnonymousClass0o3;
import X.C05910ld;
import X.C07220ox;
import X.C07230oy;
import X.EnumC02560aJ;
import X.EnumC05930lf;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

@JacksonStdImpl
public final class UntypedObjectDeserializer extends StdDeserializer<Object> {
    public static final UntypedObjectDeserializer A00 = new UntypedObjectDeserializer();
    public static final Object[] A01 = new Object[0];
    public static final long serialVersionUID = 1;

    public UntypedObjectDeserializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A09(AnonymousClass0aT r9, AbstractC02570aK r10) throws IOException, C05910ld {
        switch (AnonymousClass0nZ.A00[r9.A0G().ordinal()]) {
            case 1:
                return A00(r9, r10);
            case 2:
                if (r10.A0O(EnumC02560aJ.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                    if (r9.A0a() == EnumC05930lf.END_ARRAY) {
                        return A01;
                    }
                    C07230oy A0K = r10.A0K();
                    Object[] A012 = A0K.A01();
                    int i = 0;
                    while (true) {
                        Object A09 = A09(r9, r10);
                        if (i >= A012.length) {
                            A012 = A0K.A02(A012);
                            i = 0;
                        }
                        int i2 = i + 1;
                        A012[i] = A09;
                        if (r9.A0a() == EnumC05930lf.END_ARRAY) {
                            int i3 = A0K.A00 + i2;
                            Object[] objArr = new Object[i3];
                            C07230oy.A00(A0K, objArr, i3, A012, i2);
                            return objArr;
                        }
                        i = i2;
                    }
                } else if (r9.A0a() == EnumC05930lf.END_ARRAY) {
                    return new ArrayList(4);
                } else {
                    C07230oy A0K2 = r10.A0K();
                    Object[] A013 = A0K2.A01();
                    int i4 = 0;
                    int i5 = 0;
                    while (true) {
                        Object A092 = A09(r9, r10);
                        i4++;
                        if (i5 >= A013.length) {
                            A013 = A0K2.A02(A013);
                            i5 = 0;
                        }
                        int i6 = i5 + 1;
                        A013[i5] = A092;
                        if (r9.A0a() == EnumC05930lf.END_ARRAY) {
                            ArrayList arrayList = new ArrayList(i4 + (i4 >> 3) + 1);
                            C07220ox r0 = A0K2.A01;
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
                return r9.A0M();
            case 5:
                return r9.A0P();
            case 6:
                if (r10.A0O(EnumC02560aJ.USE_BIG_INTEGER_FOR_INTS)) {
                    return r9.A0S();
                }
                return r9.A0K();
            case 7:
                if (r10.A0O(EnumC02560aJ.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return r9.A0R();
                }
                return Double.valueOf(r9.A03());
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

    private final Object A00(AnonymousClass0aT r8, AbstractC02570aK r9) throws IOException, C05910ld {
        EnumC05930lf A0G = r8.A0G();
        if (A0G == EnumC05930lf.START_OBJECT) {
            A0G = r8.A0a();
        }
        EnumC05930lf r0 = EnumC05930lf.FIELD_NAME;
        if (A0G != r0) {
            return new LinkedHashMap(4);
        }
        String A0P = r8.A0P();
        r8.A0a();
        Object A09 = A09(r8, r9);
        if (r8.A0a() != r0) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(4);
            linkedHashMap.put(A0P, A09);
            return linkedHashMap;
        }
        String A0P2 = r8.A0P();
        r8.A0a();
        Object A092 = A09(r8, r9);
        if (r8.A0a() != r0) {
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(4);
            linkedHashMap2.put(A0P, A09);
            linkedHashMap2.put(A0P2, A092);
            return linkedHashMap2;
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(A0P, A09);
        linkedHashMap3.put(A0P2, A092);
        do {
            String A0P3 = r8.A0P();
            r8.A0a();
            linkedHashMap3.put(A0P3, A09(r8, r9));
        } while (r8.A0a() != EnumC05930lf.END_OBJECT);
        return linkedHashMap3;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AnonymousClass0aT r3, AbstractC02570aK r4, AnonymousClass0o3 r5) throws IOException, C05910ld {
        switch (AnonymousClass0nZ.A00[r3.A0G().ordinal()]) {
            case 1:
            case 2:
            case 3:
                return r5.A07(r3, r4);
            case 4:
                return r3.A0M();
            case 5:
                return r3.A0P();
            case 6:
                if (r4.A0O(EnumC02560aJ.USE_BIG_INTEGER_FOR_INTS)) {
                    return r3.A0S();
                }
                return r3.A0K();
            case 7:
                if (r4.A0O(EnumC02560aJ.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return r3.A0R();
                }
                return Double.valueOf(r3.A03());
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
