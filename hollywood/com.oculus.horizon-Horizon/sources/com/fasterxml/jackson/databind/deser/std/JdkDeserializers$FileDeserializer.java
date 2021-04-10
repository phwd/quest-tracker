package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC04020gg;
import X.AnonymousClass0jg;
import java.io.File;
import java.io.IOException;

public class JdkDeserializers$FileDeserializer extends FromStringDeserializer<File> {
    public static final JdkDeserializers$FileDeserializer A00 = new JdkDeserializers$FileDeserializer();

    public JdkDeserializers$FileDeserializer() {
        super(File.class);
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final File A0Q(String str, AbstractC04020gg r3) throws IOException, AnonymousClass0jg {
        return new File(str);
    }
}
