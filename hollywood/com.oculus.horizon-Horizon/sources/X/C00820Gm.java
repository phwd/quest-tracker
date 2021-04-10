package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers$LocaleDeserializer;
import java.io.IOException;
import java.util.Locale;

@JacksonStdImpl
/* renamed from: X.0Gm  reason: invalid class name and case insensitive filesystem */
public final class C00820Gm extends AbstractC03840gD {
    public static final long serialVersionUID = 1;
    public JdkDeserializers$LocaleDeserializer _localeDeserializer = new JdkDeserializers$LocaleDeserializer();

    public C00820Gm() {
        super(Locale.class);
    }

    @Override // X.AbstractC03840gD
    public final Object A01(String str, AbstractC04020gg r4) throws Exception {
        try {
            return JdkDeserializers$LocaleDeserializer.A00(str);
        } catch (IOException unused) {
            r4.A0G(this._keyClass, str);
            throw new RuntimeException("Redex: Unreachable code after no-return invoke");
        }
    }
}
