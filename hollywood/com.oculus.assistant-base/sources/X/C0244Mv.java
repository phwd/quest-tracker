package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.util.Locale;
import java.util.TimeZone;

/* renamed from: X.Mv  reason: case insensitive filesystem */
public final class C0244Mv {
    public final EnumC0243Mu A00;
    public final String A01;
    public final Locale A02;
    public final TimeZone A03;

    public C0244Mv() {
        this(OacrConstants.AUTO_SPEECH_DOMAIN, EnumC0243Mu.ANY, OacrConstants.AUTO_SPEECH_DOMAIN, OacrConstants.AUTO_SPEECH_DOMAIN);
    }

    public C0244Mv(String str, EnumC0243Mu mu, String str2, String str3) {
        Locale locale;
        TimeZone timeZone = null;
        if (str2 == null || str2.length() == 0 || "##default".equals(str2)) {
            locale = null;
        } else {
            locale = new Locale(str2);
        }
        if (!(str3 == null || str3.length() == 0 || "##default".equals(str3))) {
            timeZone = TimeZone.getTimeZone(str3);
        }
        this.A01 = str;
        this.A00 = mu;
        this.A02 = locale;
        this.A03 = timeZone;
    }
}
