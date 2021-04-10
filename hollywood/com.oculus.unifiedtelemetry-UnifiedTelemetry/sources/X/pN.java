package X;

import java.util.Locale;
import java.util.TimeZone;

public class pN {
    public final pM A00;
    public final String A01;
    public final Locale A02;
    public final TimeZone A03;

    public pN() {
        this("", pM.ANY, "", "");
    }

    public pN(String str, pM pMVar, String str2, String str3) {
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
        this.A00 = pMVar;
        this.A02 = locale;
        this.A03 = timeZone;
    }
}
