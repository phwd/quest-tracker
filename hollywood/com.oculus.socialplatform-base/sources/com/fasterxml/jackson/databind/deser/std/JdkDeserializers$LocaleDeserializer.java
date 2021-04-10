package com.fasterxml.jackson.databind.deser.std;

import X.AbstractC02210iH;
import X.C03620oC;
import java.io.IOException;
import java.util.Locale;

public class JdkDeserializers$LocaleDeserializer extends FromStringDeserializer<Locale> {
    public static final JdkDeserializers$LocaleDeserializer A00 = new JdkDeserializers$LocaleDeserializer();

    public JdkDeserializers$LocaleDeserializer() {
        super(Locale.class);
    }

    public static final Locale A00(String str) throws IOException {
        int indexOf = str.indexOf(95);
        if (indexOf < 0) {
            return new Locale(str);
        }
        String substring = str.substring(0, indexOf);
        String substring2 = str.substring(indexOf + 1);
        int indexOf2 = substring2.indexOf(95);
        if (indexOf2 < 0) {
            return new Locale(substring, substring2);
        }
        return new Locale(substring, substring2.substring(0, indexOf2), substring2.substring(indexOf2 + 1));
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // com.fasterxml.jackson.databind.deser.std.FromStringDeserializer
    public final /* bridge */ /* synthetic */ Locale A0Q(String str, AbstractC02210iH r3) throws IOException, C03620oC {
        return A00(str);
    }
}
