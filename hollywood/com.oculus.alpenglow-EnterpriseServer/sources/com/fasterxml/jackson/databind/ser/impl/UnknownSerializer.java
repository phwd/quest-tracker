package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC02640aV;
import X.AnonymousClass006;
import X.AnonymousClass0a8;
import X.AnonymousClass0a9;
import X.AnonymousClass0aG;
import X.AnonymousClass0o6;
import X.C02650aW;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public final class UnknownSerializer extends StdSerializer<Object> {
    public UnknownSerializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void A0A(Object obj, AbstractC02640aV r5, AnonymousClass0a8 r6, AnonymousClass0o6 r7) throws IOException, C02650aW {
        if (r6._config.A06(AnonymousClass0a9.FAIL_ON_EMPTY_BEANS)) {
            throw new AnonymousClass0aG(AnonymousClass006.A07("No serializer found for class ", obj.getClass().getName(), " and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) )"));
        }
        r7.A02(obj, r5);
        r7.A05(obj, r5);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r5, AnonymousClass0a8 r6) throws IOException, AnonymousClass0aG {
        if (r6._config.A06(AnonymousClass0a9.FAIL_ON_EMPTY_BEANS)) {
            throw new AnonymousClass0aG(AnonymousClass006.A07("No serializer found for class ", obj.getClass().getName(), " and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) )"));
        }
        r5.A0F();
        r5.A0C();
    }
}
