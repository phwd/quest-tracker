package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC0226Wn;
import X.q0;
import java.io.IOException;
import java.util.regex.Pattern;

public class JdkDeserializers$PatternDeserializer extends FromStringDeserializer<Pattern> {
    public static final JdkDeserializers$PatternDeserializer A00 = new JdkDeserializers$PatternDeserializer();

    public JdkDeserializers$PatternDeserializer() {
        super(Pattern.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final Pattern A0Q(String str, AbstractC0226Wn wn) throws IOException, q0 {
        return Pattern.compile(str);
    }
}
