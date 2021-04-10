package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02570aK;
import X.C05910ld;
import java.io.IOException;
import java.util.regex.Pattern;

public class JdkDeserializers$PatternDeserializer extends FromStringDeserializer<Pattern> {
    public static final JdkDeserializers$PatternDeserializer A00 = new JdkDeserializers$PatternDeserializer();

    public JdkDeserializers$PatternDeserializer() {
        super(Pattern.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final Pattern A0Q(String str, AbstractC02570aK r3) throws IOException, C05910ld {
        return Pattern.compile(str);
    }
}
