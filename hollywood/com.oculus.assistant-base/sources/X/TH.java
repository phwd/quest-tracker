package X;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers$LocaleDeserializer;
import java.util.Locale;

@JacksonStdImpl
public final class TH extends AbstractC1040rF {
    public static final long serialVersionUID = 1;
    public JdkDeserializers$LocaleDeserializer _localeDeserializer = new JdkDeserializers$LocaleDeserializer();

    public TH() {
        super(Locale.class);
    }
}
