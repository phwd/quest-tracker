package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC01900ha;
import X.AbstractC02120i3;
import X.AbstractC02190iF;
import X.AbstractC02300iS;
import X.AbstractC04550qd;
import X.C02180iD;
import X.C02310iT;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public class ToStringSerializer extends StdSerializer<Object> {
    public static final ToStringSerializer A00 = new ToStringSerializer();

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final boolean isEmpty(Object obj) {
        String obj2;
        return obj == null || (obj2 = obj.toString()) == null || obj2.length() == 0;
    }

    public ToStringSerializer() {
        super(Object.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void acceptJsonFormatVisitor(AbstractC01900ha r3, AbstractC02190iF r4) throws C02180iD {
        if (r3 != null) {
            throw new NullPointerException("expectStringFormat");
        }
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void serialize(Object obj, AbstractC02300iS r3, AbstractC02120i3 r4) throws IOException, C02310iT {
        r3.A0U(obj.toString());
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final void serializeWithType(Object obj, AbstractC02300iS r2, AbstractC02120i3 r3, AbstractC04550qd r4) throws IOException, C02310iT {
        r4.A03(obj, r2);
        serialize(obj, r2, r3);
        r4.A06(obj, r2);
    }
}
