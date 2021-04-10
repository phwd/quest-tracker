package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.os.LocaleList;
import java.util.Locale;

/* renamed from: BV  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class BV {

    /* renamed from: a  reason: collision with root package name */
    public static final BV f7741a = new BV();
    public final Locale b = Locale.getDefault();
    public String c;
    public boolean d;

    public Configuration a(Context context) {
        Configuration configuration = new Configuration();
        configuration.fontScale = 0.0f;
        String str = this.c;
        configuration.setLocales(LocaleList.forLanguageTags(String.format("%1$s,%2$s", str, context.getResources().getConfiguration().getLocales().toLanguageTags().replaceFirst(String.format("(^|,)%1$s$|%1$s,", str), ""))));
        return configuration;
    }
}
