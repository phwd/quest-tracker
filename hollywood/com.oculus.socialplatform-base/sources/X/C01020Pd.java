package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers$LocaleDeserializer;
import java.io.IOException;
import java.util.Locale;

@JacksonStdImpl
/* renamed from: X.0Pd  reason: invalid class name and case insensitive filesystem */
public final class C01020Pd extends AbstractC02030hq {
    public static final long serialVersionUID = 1;
    public JdkDeserializers$LocaleDeserializer _localeDeserializer = new JdkDeserializers$LocaleDeserializer();

    public C01020Pd() {
        super(Locale.class);
    }

    @Override // X.AbstractC02030hq
    public final Object A01(String str, AbstractC02210iH r4) throws Exception {
        try {
            return JdkDeserializers$LocaleDeserializer.A00(str);
        } catch (IOException unused) {
            throw r4.A0E(this._keyClass, str, "unable to parse key as locale");
        }
    }
}
