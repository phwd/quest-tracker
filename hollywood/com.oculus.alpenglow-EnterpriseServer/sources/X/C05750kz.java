package X;

import java.util.Locale;
import java.util.TimeZone;

/* renamed from: X.0kz  reason: invalid class name and case insensitive filesystem */
public class C05750kz {
    public final EnumC05740ky A00;
    public final String A01;
    public final Locale A02;
    public final TimeZone A03;

    public C05750kz() {
        this("", EnumC05740ky.ANY, "", "");
    }

    public C05750kz(String str, EnumC05740ky r6, String str2, String str3) {
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
        this.A00 = r6;
        this.A02 = locale;
        this.A03 = timeZone;
    }
}
