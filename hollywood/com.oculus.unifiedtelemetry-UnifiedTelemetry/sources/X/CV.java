package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers$LocaleDeserializer;
import java.io.IOException;
import java.util.Locale;

@JacksonStdImpl
public final class CV extends WN {
    public static final long serialVersionUID = 1;
    public JdkDeserializers$LocaleDeserializer _localeDeserializer = new JdkDeserializers$LocaleDeserializer();

    public CV() {
        super(Locale.class);
    }

    @Override // X.WN
    public final Object A01(String str, AbstractC0226Wn wn) throws Exception {
        try {
            return JdkDeserializers$LocaleDeserializer.A00(str);
        } catch (IOException unused) {
            throw wn.A0B(this._keyClass, str, "unable to parse key as locale");
        }
    }
}
