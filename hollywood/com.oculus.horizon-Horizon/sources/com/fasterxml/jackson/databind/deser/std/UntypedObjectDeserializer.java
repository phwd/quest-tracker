package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AbstractC04100gp;
import X.AnonymousClass0jg;
import X.AnonymousClass0m9;
import X.C05660le;
import X.C06430n4;
import X.C06440n5;
import X.EnumC04010gf;
import X.EnumC04820ji;
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
    public final Object A09(AbstractC04100gp r9, AbstractC04020gg r10) throws IOException, AnonymousClass0jg {
        switch (C05660le.A00[r9.A0a().ordinal()]) {
            case 1:
                return A00(r9, r10);
            case 2:
                if (r10.A0I(EnumC04010gf.USE_JAVA_ARRAY_FOR_JSON_ARRAY)) {
                    if (r9.A0b() == EnumC04820ji.END_ARRAY) {
                        return A01;
                    }
                    C06440n5 A0D = r10.A0D();
                    Object[] A012 = A0D.A01();
                    int i = 0;
                    while (true) {
                        Object A09 = A09(r9, r10);
                        if (i >= A012.length) {
                            A012 = A0D.A02(A012);
                            i = 0;
                        }
                        int i2 = i + 1;
                        A012[i] = A09;
                        if (r9.A0b() == EnumC04820ji.END_ARRAY) {
                            int i3 = A0D.A00 + i2;
                            Object[] objArr = new Object[i3];
                            C06440n5.A00(A0D, objArr, i3, A012, i2);
                            return objArr;
                        }
                        i = i2;
                    }
                } else if (r9.A0b() == EnumC04820ji.END_ARRAY) {
                    return new ArrayList(4);
                } else {
                    C06440n5 A0D2 = r10.A0D();
                    Object[] A013 = A0D2.A01();
                    int i4 = 0;
                    int i5 = 0;
                    while (true) {
                        Object A092 = A09(r9, r10);
                        i4++;
                        if (i5 >= A013.length) {
                            A013 = A0D2.A02(A013);
                            i5 = 0;
                        }
                        int i6 = i5 + 1;
                        A013[i5] = A092;
                        if (r9.A0b() == EnumC04820ji.END_ARRAY) {
                            ArrayList arrayList = new ArrayList(i4 + (i4 >> 3) + 1);
                            C06430n4 r0 = A0D2.A01;
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
                return r9.A0S();
            case 5:
                return r9.A0e();
            case 6:
                if (r10.A0I(EnumC04010gf.USE_BIG_INTEGER_FOR_INTS)) {
                    return r9.A0U();
                }
                return r9.A0R();
            case 7:
                if (r10.A0I(EnumC04010gf.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return r9.A0T();
                }
                return Double.valueOf(r9.A0K());
            case 8:
                return Boolean.TRUE;
            case 9:
                return Boolean.FALSE;
            case 10:
                return null;
            default:
                throw null;
        }
    }

    private final Object A00(AbstractC04100gp r8, AbstractC04020gg r9) throws IOException, AnonymousClass0jg {
        EnumC04820ji A0a = r8.A0a();
        if (A0a == EnumC04820ji.START_OBJECT) {
            A0a = r8.A0b();
        }
        EnumC04820ji r0 = EnumC04820ji.FIELD_NAME;
        if (A0a != r0) {
            return new LinkedHashMap(4);
        }
        String A0e = r8.A0e();
        r8.A0b();
        Object A09 = A09(r8, r9);
        if (r8.A0b() != r0) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(4);
            linkedHashMap.put(A0e, A09);
            return linkedHashMap;
        }
        String A0e2 = r8.A0e();
        r8.A0b();
        Object A092 = A09(r8, r9);
        if (r8.A0b() != r0) {
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(4);
            linkedHashMap2.put(A0e, A09);
            linkedHashMap2.put(A0e2, A092);
            return linkedHashMap2;
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(A0e, A09);
        linkedHashMap3.put(A0e2, A092);
        do {
            String A0e3 = r8.A0e();
            r8.A0b();
            linkedHashMap3.put(A0e3, A09(r8, r9));
        } while (r8.A0b() != EnumC04820ji.END_OBJECT);
        return linkedHashMap3;
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A0C(AbstractC04100gp r3, AbstractC04020gg r4, AnonymousClass0m9 r5) throws IOException, AnonymousClass0jg {
        switch (C05660le.A00[r3.A0a().ordinal()]) {
            case 1:
            case 2:
            case 3:
                return r5.A07(r3, r4);
            case 4:
                return r3.A0S();
            case 5:
                return r3.A0e();
            case 6:
                if (r4.A0I(EnumC04010gf.USE_BIG_INTEGER_FOR_INTS)) {
                    return r3.A0U();
                }
                return r3.A0R();
            case 7:
                if (r4.A0I(EnumC04010gf.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return r3.A0T();
                }
                return Double.valueOf(r3.A0K());
            case 8:
                return Boolean.TRUE;
            case 9:
                return Boolean.FALSE;
            case 10:
                return null;
            default:
                throw null;
        }
    }
}
