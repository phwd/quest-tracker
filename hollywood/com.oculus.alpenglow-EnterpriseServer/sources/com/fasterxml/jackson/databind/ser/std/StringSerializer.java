package com.fasterxml.jackson.databind.ser.std;

import X.AbstractC02640aV;
import X.AnonymousClass0a8;
import X.C02650aW;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;

@JacksonStdImpl
public final class StringSerializer extends NonTypedScalarSerializerBase<String> {
    public StringSerializer() {
        super(String.class);
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer
    public final boolean A0B(Object obj) {
        String str = (String) obj;
        if (str == null || str.length() == 0) {
            return true;
        }
        return false;
    }

    @Override // com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.ser.std.StdSerializer
    public final void A0D(Object obj, AbstractC02640aV r2, AnonymousClass0a8 r3) throws IOException, C02650aW {
        r2.A0S((String) obj);
    }
}
