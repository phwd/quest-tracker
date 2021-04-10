package com.fasterxml.jackson.databind.ser.impl;

import X.AbstractC02120i3;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.AnonymousClass006;
import X.AnonymousClass0i4;
import X.C02180iD;
import X.C02310iT;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class UnknownSerializer extends StdSerializer<Object> {
    public UnknownSerializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Object obj, AbstractC02300iS r5, AbstractC02120i3 r6) throws IOException, C02180iD {
        if (r6._config.A06(AnonymousClass0i4.FAIL_ON_EMPTY_BEANS)) {
            throw new C02180iD(AnonymousClass006.A09("No serializer found for class ", obj.getClass().getName(), " and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) )"));
        }
        r5.A0I();
        r5.A0F();
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serializeWithType(Object obj, AbstractC02300iS r5, AbstractC02120i3 r6, AbstractC04550qd r7) throws IOException, C02310iT {
        if (r6._config.A06(AnonymousClass0i4.FAIL_ON_EMPTY_BEANS)) {
            throw new C02180iD(AnonymousClass006.A09("No serializer found for class ", obj.getClass().getName(), " and no properties discovered to create BeanSerializer (to avoid exception, disable SerializationFeature.FAIL_ON_EMPTY_BEANS) )"));
        }
        r7.A02(obj, r5);
        r7.A05(obj, r5);
    }
}
