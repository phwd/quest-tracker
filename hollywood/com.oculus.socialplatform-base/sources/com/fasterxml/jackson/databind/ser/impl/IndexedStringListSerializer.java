package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC01990hm;
import X.AbstractC02120i3;
import X.AbstractC02220iI;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.AbstractC04600qk;
import X.AnonymousClass0i4;
import X.C02180iD;
import X.C02310iT;
import X.C03620oC;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.List;

@JacksonStdImpl
public final class IndexedStringListSerializer extends StaticListSerializerBase<List<String>> implements AbstractC04600qk {
    public static final IndexedStringListSerializer A01 = new IndexedStringListSerializer(null);
    public final JsonSerializer<String> A00;

    private final void A00(List<String> list, AbstractC02300iS r5, AbstractC02120i3 r6, int i) throws IOException, C02310iT {
        try {
            JsonSerializer<String> jsonSerializer = this.A00;
            for (int i2 = 0; i2 < i; i2++) {
                String str = list.get(i2);
                if (str == null) {
                    r6.A0E(r5);
                } else {
                    jsonSerializer.serialize(str, r5, r6);
                }
            }
        } catch (Exception e) {
            StdSerializer.A04(r6, e, list, 0);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public static final void A01(List<String> list, AbstractC02300iS r3, AbstractC02120i3 r4, int i) throws IOException, C02310iT {
        for (int i2 = 0; i2 < i; i2++) {
            try {
                String str = list.get(i2);
                if (str == null) {
                    r4.A0E(r3);
                } else {
                    r3.A0U(str);
                }
            } catch (Exception e) {
                StdSerializer.A04(r4, e, list, i2);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    @Override // X.AbstractC04600qk
    public final JsonSerializer<?> A2P(AbstractC02120i3 r4, AbstractC02220iI r5) throws C02180iD {
        JsonSerializer<Object> jsonSerializer;
        AbstractC01990hm A4N;
        Object A0U;
        if (r5 == null || (A4N = r5.A4N()) == null || (A0U = r4._config.A01().A0U(A4N)) == null || (jsonSerializer = r4.A0A(A4N, A0U)) == null) {
            jsonSerializer = this.A00;
        }
        JsonSerializer<?> A03 = StdSerializer.A03(r4, r5, jsonSerializer);
        if (A03 == null) {
            A03 = r4.A0B(String.class, r5);
        } else if (A03 instanceof AbstractC04600qk) {
            A03 = ((AbstractC04600qk) A03).A2P(r4, r5);
        }
        if (StdSerializer.A06(A03)) {
            A03 = null;
        }
        if (A03 == this.A00) {
            return this;
        }
        return new IndexedStringListSerializer(A03);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Object obj, AbstractC02300iS r5, AbstractC02120i3 r6) throws IOException, C02310iT {
        List<String> list = (List) obj;
        int size = list.size();
        if (size == 1) {
            if (r6._config.A06(AnonymousClass0i4.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) {
                if (this.A00 == null) {
                    A01(list, r5, r6, 1);
                    return;
                } else {
                    A00(list, r5, r6, 1);
                    return;
                }
            }
        }
        r5.A0H();
        if (this.A00 == null) {
            A01(list, r5, r6, size);
        } else {
            A00(list, r5, r6, size);
        }
        r5.A0E();
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serializeWithType(Object obj, AbstractC02300iS r4, AbstractC02120i3 r5, AbstractC04550qd r6) throws IOException, C03620oC {
        List<String> list = (List) obj;
        int size = list.size();
        r6.A01(list, r4);
        if (this.A00 == null) {
            A01(list, r4, r5, size);
        } else {
            A00(list, r4, r5, size);
        }
        r6.A04(list, r4);
    }

    public IndexedStringListSerializer() {
        this(null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.fasterxml.jackson.databind.JsonSerializer<?> */
    /* JADX WARN: Multi-variable type inference failed */
    public IndexedStringListSerializer(JsonSerializer<?> jsonSerializer) {
        super(List.class);
        this.A00 = jsonSerializer;
    }
}
