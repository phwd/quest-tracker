package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers$LocaleDeserializer;
import java.io.IOException;
import java.util.Locale;

@JacksonStdImpl
/* renamed from: X.0KR  reason: invalid class name */
public final class AnonymousClass0KR extends AbstractC02490Zv {
    public static final long serialVersionUID = 1;
    public JdkDeserializers$LocaleDeserializer _localeDeserializer = new JdkDeserializers$LocaleDeserializer();

    public AnonymousClass0KR() {
        super(Locale.class);
    }

    @Override // X.AbstractC02490Zv
    public final Object A01(String str, AbstractC02570aK r4) throws Exception {
        try {
            return JdkDeserializers$LocaleDeserializer.A00(str);
        } catch (IOException unused) {
            throw r4.A0E(this._keyClass, str, "unable to parse key as locale");
        }
    }
}
