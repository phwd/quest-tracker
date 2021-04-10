package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0122Rd;
import X.AnonymousClass4c;
import X.AnonymousClass9p;
import X.AnonymousClass9r;
import X.B3;
import X.Rn;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.util.LinkedHashMap;

@JacksonStdImpl
public final class UntypedObjectDeserializer extends StdDeserializer<Object> {
    public static final UntypedObjectDeserializer A00 = new UntypedObjectDeserializer();
    public static final long serialVersionUID = 1;

    private final Object A00(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        AnonymousClass9p r1 = ((B3) rn).A00;
        if (r1 == AnonymousClass9p.START_OBJECT) {
            r1 = rn.A04();
        }
        AnonymousClass9p r0 = AnonymousClass9p.FIELD_NAME;
        if (r1 != r0) {
            return new LinkedHashMap(4);
        }
        String A09 = rn.A09();
        rn.A04();
        Object A03 = A03(rn, rd);
        if (rn.A04() != r0) {
            LinkedHashMap linkedHashMap = new LinkedHashMap(4);
            linkedHashMap.put(A09, A03);
            return linkedHashMap;
        }
        String A092 = rn.A09();
        rn.A04();
        Object A032 = A03(rn, rd);
        if (rn.A04() != r0) {
            LinkedHashMap linkedHashMap2 = new LinkedHashMap(4);
            linkedHashMap2.put(A09, A03);
            linkedHashMap2.put(A092, A032);
            return linkedHashMap2;
        }
        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
        linkedHashMap3.put(A09, A03);
        linkedHashMap3.put(A092, A032);
        do {
            String A093 = rn.A09();
            rn.A04();
            linkedHashMap3.put(A093, A03(rn, rd));
        } while (rn.A04() != AnonymousClass9p.END_OBJECT);
        return linkedHashMap3;
    }

    public UntypedObjectDeserializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public final Object A03(Rn rn, AbstractC0122Rd rd) throws IOException, AnonymousClass9r {
        switch (AnonymousClass4c.A00[((B3) rn).A00.ordinal()]) {
            case 1:
                return A00(rn, rd);
            case 2:
            case 6:
            case 7:
            default:
                throw null;
            case 3:
                return A00(rn, rd);
            case 4:
                return rn.A07();
            case 5:
                return rn.A09();
            case 8:
                return Boolean.TRUE;
            case 9:
                return Boolean.FALSE;
            case 10:
                return null;
        }
    }
}
