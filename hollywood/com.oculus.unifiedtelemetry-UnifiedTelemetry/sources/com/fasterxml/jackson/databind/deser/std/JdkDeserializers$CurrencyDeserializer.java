package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.q0;
import java.io.IOException;
import java.util.Currency;

public class JdkDeserializers$CurrencyDeserializer extends FromStringDeserializer<Currency> {
    public static final JdkDeserializers$CurrencyDeserializer A00 = new JdkDeserializers$CurrencyDeserializer();

    public JdkDeserializers$CurrencyDeserializer() {
        super(Currency.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final Currency A0Q(String str, AbstractC0226Wn wn) throws IOException, q0 {
        return Currency.getInstance(str);
    }
}
