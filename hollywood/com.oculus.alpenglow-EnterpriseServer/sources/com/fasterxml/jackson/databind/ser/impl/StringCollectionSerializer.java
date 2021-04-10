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
import java.util.Collection;

@JacksonStdImpl
public final class StringCollectionSerializer extends StaticListSerializerBase<Collection<String>> implements AbstractC06840oE {
    public static final StringCollectionSerializer A01 = new StringCollectionSerializer(null);
    public final JsonSerializer<String> A00;

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
        return new StringCollectionSerializer(A03);
    }

    private void A00(Collection<String> collection, AbstractC02640aV r5, AnonymousClass0a8 r6) throws IOException, C02650aW {
        JsonSerializer<String> jsonSerializer = this.A00;
        for (String str : collection) {
            if (str == null) {
                try {
                    r6.A0D(r5);
                } catch (Exception e) {
                    StdSerializer.A04(r6, e, collection, 0);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                jsonSerializer.A0D(str, r5, r6);
            }
        }
    }

    private final void A01(Collection<String> collection, AbstractC02640aV r5, AnonymousClass0a8 r6) throws IOException, C02650aW {
        if (this.A00 != null) {
            A00(collection, r5, r6);
            return;
        }
        int i = 0;
        for (String str : collection) {
            if (str == null) {
                try {
                    r6.A0D(r5);
                } catch (Exception e) {
                    StdSerializer.A04(r6, e, collection, i);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                r5.A0S(str);
            }
            i++;
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0A(Object obj, AbstractC02640aV r3, AnonymousClass0a8 r4, AnonymousClass0o6 r5) throws IOException, C05910ld {
        Collection<String> collection = (Collection) obj;
        r5.A01(collection, r3);
        if (this.A00 == null) {
            A01(collection, r3, r4);
        } else {
            A00(collection, r3, r4);
        }
        r5.A04(collection, r3);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r4, AnonymousClass0a8 r5) throws IOException, C02650aW {
        Collection<String> collection = (Collection) obj;
        if (collection.size() == 1) {
            if (r5._config.A06(AnonymousClass0a9.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) {
                if (this.A00 == null) {
                    A01(collection, r4, r5);
                    return;
                } else {
                    A00(collection, r4, r5);
                    return;
                }
            }
        }
        r4.A0E();
        if (this.A00 == null) {
            A01(collection, r4, r5);
        } else {
            A00(collection, r4, r5);
        }
        r4.A0B();
    }

    public StringCollectionSerializer() {
        this(null);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: com.fasterxml.jackson.databind.JsonSerializer<?> */
    /* JADX WARN: Multi-variable type inference failed */
    public StringCollectionSerializer(JsonSerializer<?> jsonSerializer) {
        super(Collection.class);
        this.A00 = jsonSerializer;
    }
}
