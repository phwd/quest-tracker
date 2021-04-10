package defpackage;

import android.content.SharedPreferences;
import android.text.TextUtils;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: X51  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class X51 {

    /* renamed from: a  reason: collision with root package name */
    public static SharedPreferences f9193a;
    public final AbstractC0124Ca1 b;
    public final AbstractC5783ya1 c = new V51(this);
    public final AbstractC1099Sa1 d;
    public final AbstractC0612Ka1 e;

    public X51(AbstractC0124Ca1 ca1) {
        this.b = ca1;
        this.d = new U51(this, ca1);
        W51 w51 = new W51(this);
        this.e = w51;
        ((AbstractC0246Ea1) ca1).c(w51);
    }

    public static void a(Tab tab) {
        int i;
        if (tab.l() != null) {
            int id = tab.getId();
            C0948Pm0 y = tab.l().f().y();
            String str = null;
            if (TextUtils.isEmpty(AbstractC0444Hf1.a().c(tab.getUrl()))) {
                int i2 = y.b - 1;
                while (true) {
                    if (i2 < 0) {
                        break;
                    }
                    String c2 = AbstractC0444Hf1.a().c(y.a(i2).c);
                    if (!TextUtils.isEmpty(c2)) {
                        StringBuilder sb = new StringBuilder();
                        int i3 = 0;
                        while (i3 < c2.length()) {
                            if (c2.charAt(i3) != '%' || (i = i3 + 2) >= c2.length()) {
                                sb.append(c2.charAt(i3));
                            } else if (Character.digit(c2.charAt(i3 + 1), 16) == -1 || Character.digit(c2.charAt(i), 16) == -1) {
                                sb.append(c2.charAt(i3));
                            } else {
                                i3 = i;
                            }
                            i3++;
                        }
                        str = sb.toString();
                    } else {
                        i2--;
                    }
                }
            }
            d().edit().putString(b(id), str).apply();
        }
    }

    public static String b(int i) {
        return i + "_last_search_term";
    }

    public static String c(int i) {
        return i + "_rootID";
    }

    public static SharedPreferences d() {
        if (f9193a == null) {
            f9193a = ContextUtils.getApplicationContext().getSharedPreferences("tab_attribute_cache", 0);
        }
        return f9193a;
    }

    public static String e(int i) {
        return i + "_title";
    }

    public static String f(int i) {
        return i + "_url";
    }
}
