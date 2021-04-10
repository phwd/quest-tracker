package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.AbstractC0232Ww;
import X.EnumC0225Wm;
import X.EnumC0470q2;
import X.J9;
import X.JJ;
import X.V4;
import X.VX;
import X.q0;
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
    public final Object A09(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        switch (VX.A00[ww.A0Z().ordinal()]) {
            case 1:
                return A00(ww, wn);
            case 2:
                if (wn.A0L(EnumC0225Wm.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                    if (ww.A0a() == EnumC0470q2.END_ARRAY) {
                        return A01;
                    }
                    J9 A0H = wn.A0H();
                    Object[] A012 = A0H.A01();
                    int i = 0;
                    while (true) {
                        Object A09 = A09(ww, wn);
                        if (i >= A012.length) {
                            A012 = A0H.A02(A012);
                            i = 0;
                        }
                        int i2 = i + 1;
                        A012[i] = A09;
                        if (ww.A0a() == EnumC0470q2.END_ARRAY) {
                            int i3 = A0H.A00 + i2;
                            Object[] objArr = new Object[i3];
                            J9.A00(A0H, objArr, i3, A012, i2);
                            return objArr;
                        }
                        i = i2;
                    }
                } else if (ww.A0a() == EnumC0470q2.END_ARRAY) {
                    return new ArrayList(4);
                } else {
                    J9 A0H2 = wn.A0H();
                    Object[] A013 = A0H2.A01();
                    int i4 = 0;
                    int i5 = 0;
                    while (true) {
                        Object A092 = A09(ww, wn);
                        i4++;
                        if (i5 >= A013.length) {
                            A013 = A0H2.A02(A013);
                            i5 = 0;
                        }
                        int i6 = i5 + 1;
                        A013[i5] = A092;
                        if (ww.A0a() == EnumC0470q2.END_ARRAY) {
                            ArrayList arrayList = new ArrayList(i4 + (i4 >> 3) + 1);
                            JJ jj = A0H2.A01;
                            while (true) {
                                int i7 = 0;
                                if (jj != null) {
                                    Object[] objArr2 = jj.A01;
                                    int length = objArr2.length;
                                    while (i7 < length) {
                                        arrayList.add(objArr2[i7]);
                                        i7++;
                                    }
                                    jj = jj.A00;
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
                return A00(ww, wn);
            case 4:
                return ww.A0R();
            case 5:
                return ww.A0d();
            case 6:
                if (wn.A0L(EnumC0225Wm.USE_BIG_INTEGER_FOR_INTS)) {
                    return ww.A0T();
                }
                return ww.A0Q();
            case 7:
                if (wn.A0L(EnumC0225Wm.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return ww.A0S();
                }
                return Double.valueOf(ww.A0J());
            case 8:
                return Boolean.TRUE;
            case 9:
                return Boolean.FALSE;
            case 10:
                return null;
            default:
                throw wn.A08(Object.class);
        }
    }

    private final Object A00(AbstractC0232Ww ww, AbstractC0226Wn wn) throws IOException, q0 {
        EnumC0470q2 A0Z = ww.A0Z();
        if (A0Z == EnumC0470q2.START_OBJECT) {
            A0Z = ww.A0a();
        }
        EnumC0470q2 q2Var = EnumC0470q2.FIELD_NAME;
        if (A0Z != q2Var) {
            return new LinkedHashMap(4);
        }
        String A0d = ww.A0d();
        ww.A0a();
        Object A09 = A09(ww, wn);
        if (ww.A0a() != q2Var) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(4);
            linkedHashMap.put(A0d, A09);
            return linkedHashMap;
        }
        String A0d2 = ww.A0d();
        ww.A0a();
        Object A092 = A09(ww, wn);
        if (ww.A0a() != q2Var) {
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(4);
            linkedHashMap2.put(A0d, A09);
            linkedHashMap2.put(A0d2, A092);
            return linkedHashMap2;
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(A0d, A09);
        linkedHashMap3.put(A0d2, A092);
        do {
            String A0d3 = ww.A0d();
            ww.A0a();
            linkedHashMap3.put(A0d3, A09(ww, wn));
        } while (ww.A0a() != EnumC0470q2.END_OBJECT);
        return linkedHashMap3;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC0232Ww ww, AbstractC0226Wn wn, V4 v4) throws IOException, q0 {
        switch (VX.A00[ww.A0Z().ordinal()]) {
            case 1:
            case 2:
            case 3:
                return v4.A07(ww, wn);
            case 4:
                return ww.A0R();
            case 5:
                return ww.A0d();
            case 6:
                if (wn.A0L(EnumC0225Wm.USE_BIG_INTEGER_FOR_INTS)) {
                    return ww.A0T();
                }
                return ww.A0Q();
            case 7:
                if (wn.A0L(EnumC0225Wm.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return ww.A0S();
                }
                return Double.valueOf(ww.A0J());
            case 8:
                return Boolean.TRUE;
            case 9:
                return Boolean.FALSE;
            case 10:
                return null;
            default:
                throw wn.A08(Object.class);
        }
    }
}
