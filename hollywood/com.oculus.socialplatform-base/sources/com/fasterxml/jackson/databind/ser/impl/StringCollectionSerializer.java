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
import java.util.Collection;

@JacksonStdImpl
public class StringCollectionSerializer extends StaticListSerializerBase<Collection<String>> implements AbstractC04600qk {
    public static final StringCollectionSerializer A01 = new StringCollectionSerializer(null);
    public final JsonSerializer<String> A00;

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
        return new StringCollectionSerializer(A03);
    }

    private void A00(Collection<String> collection, AbstractC02300iS r5, AbstractC02120i3 r6) throws IOException, C02310iT {
        JsonSerializer<String> jsonSerializer = this.A00;
        for (String str : collection) {
            if (str == null) {
                try {
                    r6.A0E(r5);
                } catch (Exception e) {
                    StdSerializer.A04(r6, e, collection, 0);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                jsonSerializer.serialize(str, r5, r6);
            }
        }
    }

    private final void A01(Collection<String> collection, AbstractC02300iS r5, AbstractC02120i3 r6) throws IOException, C02310iT {
        if (this.A00 != null) {
            A00(collection, r5, r6);
            return;
        }
        int i = 0;
        for (String str : collection) {
            if (str == null) {
                try {
                    r6.A0E(r5);
                } catch (Exception e) {
                    StdSerializer.A04(r6, e, collection, i);
                    throw new RuntimeException("Redex: Unreachable code after no-return invoke");
                }
            } else {
                r5.A0U(str);
            }
            i++;
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Object obj, AbstractC02300iS r4, AbstractC02120i3 r5) throws IOException, C02310iT {
        Collection<String> collection = (Collection) obj;
        if (collection.size() == 1) {
            if (r5._config.A06(AnonymousClass0i4.WRITE_SINGLE_ELEM_ARRAYS_UNWRAPPED)) {
                if (this.A00 == null) {
                    A01(collection, r4, r5);
                    return;
                } else {
                    A00(collection, r4, r5);
                    return;
                }
            }
        }
        r4.A0H();
        if (this.A00 == null) {
            A01(collection, r4, r5);
        } else {
            A00(collection, r4, r5);
        }
        r4.A0E();
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serializeWithType(Object obj, AbstractC02300iS r3, AbstractC02120i3 r4, AbstractC04550qd r5) throws IOException, C03620oC {
        Collection<String> collection = (Collection) obj;
        r5.A01(collection, r3);
        if (this.A00 == null) {
            A01(collection, r3, r4);
        } else {
            A00(collection, r3, r4);
        }
        r5.A04(collection, r3);
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
