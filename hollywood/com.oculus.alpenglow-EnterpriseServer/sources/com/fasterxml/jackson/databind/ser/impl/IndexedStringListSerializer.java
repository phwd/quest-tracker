package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC02450Zr;
import X.AbstractC02580aL;
import X.AbstractC02640aV;
import X.AbstractC06840oE;
import X.AnonymousClass0a8;
import X.AnonymousClass0a9;
import X.AnonymousClass0aG;
import X.AnonymousClass0o6;
import X.C02650aW;
import X.C05910ld;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.util.List;

@JacksonStdImpl
public final class IndexedStringListSerializer extends StaticListSerializerBase<List<String>> implements AbstractC06840oE {
    public static final IndexedStringListSerializer A01 = new IndexedStringListSerializer(null);
    public final JsonSerializer<String> A00;

    private final void A00(List<String> list, AbstractC02640aV r5, AnonymousClass0a8 r6, int i) throws IOException, C02650aW {
        try {
            JsonSerializer<String> jsonSerializer = this.A00;
            for (int i2 = 0; i2 < i; i2++) {
                String str = list.get(i2);
                if (str == null) {
                    r6.A0D(r5);
                } else {
                    jsonSerializer.A0D(str, r5, r6);
                }
            }
        } catch (Exception e) {
            StdSerializer.A04(r6, e, list, 0);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }

    public static final void A01(List<String> list, AbstractC02640aV r3, AnonymousClass0a8 r4, int i) throws IOException, C02650aW {
        for (int i2 = 0; i2 < i; i2++) {
            try {
                String str = list.get(i2);
                if (str == null) {
                    r4.A0D(r3);
                } else {
                    r3.A0S(str);
                }
            } catch (Exception e) {
                StdSerializer.A04(r4, e, list, i2);
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            }
        }
    }

    @Override // X.AbstractC06840oE
    public final JsonSerializer<?> A1x(AnonymousClass0a8 r4, AbstractC02580aL r5) throws AnonymousClass0aG {
        JsonSerializer<Object> jsonSerializer;
        AbstractC02450Zr A41;
        Object A0U;
        if (r5 == null || (A41 = r5.A41()) == null || (A0U = r4._config.A01().A0U(A41)) == null || (jsonSerializer = r4.A09(A41, A0U)) == null) {
            jsonSerializer = this.A00;
        }
        JsonSerializer<?> A03 = StdSerializer.A03(r4, r5, jsonSerializer);
        if (A03 == null) {
            A03 = r4.A0B(String.class, r5);
        } else if (A03 instanceof AbstractC06840oE) {
            A03 = ((AbstractC06840oE) A03).A1x(r4, r5);
        }
        if (StdSerializer.A06(A03)) {
            A03 = null;
        }
        if (A03 == this.A00) {
            return this;
        }
        return new IndexedStringListSerializer(A03);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0A(Object obj, AbstractC02640aV r4, AnonymousClass0a8 r5, AnonymousClass0o6 r6) throws IOException, C05910ld {
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

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r5, AnonymousClass0a8 r6) throws IOException, C02650aW {
        List<String> list = (List) obj;
        int size = list.size();
        if (size == 1) {
            if (r6._config.A06(AnonymousClass0a9.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) {
                if (this.A00 == null) {
                    A01(list, r5, r6, 1);
                    return;
                } else {
                    A00(list, r5, r6, 1);
                    return;
                }
            }
        }
        r5.A0E();
        if (this.A00 == null) {
            A01(list, r5, r6, size);
        } else {
            A00(list, r5, r6, size);
        }
        r5.A0B();
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
