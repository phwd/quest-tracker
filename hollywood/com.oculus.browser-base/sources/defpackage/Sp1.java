package defpackage;

import J.N;
import android.content.res.Resources;
import android.text.TextUtils;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;

/* renamed from: Sp1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Sp1 {
    public static String a(String str) {
        String f = AbstractC1575Zv.e().f(str);
        return TextUtils.isEmpty(f) ? N.MOVY9QtZ("UpdateMenuItem", str) : f;
    }

    public static String b() {
        Resources resources = ContextUtils.getApplicationContext().getResources();
        String MMltG$kc = N.MMltG$kc("InlineUpdateFlow", "update_notification_experimental_context");
        char c = 65535;
        int hashCode = MMltG$kc.hashCode();
        if (hashCode != -2011176706) {
            if (hashCode != -675034215) {
                if (hashCode == 1544803905 && MMltG$kc.equals("default")) {
                    c = 2;
                }
            } else if (MMltG$kc.equals("experimental_update_chrome")) {
                c = 0;
            }
        } else if (MMltG$kc.equals("experimental_translate_the_web")) {
            c = 1;
        }
        if (c == 0) {
            return resources.getString(R.string.f64070_resource_name_obfuscated_RES_2131953724);
        }
        if (c != 1) {
            return resources.getString(R.string.f64050_resource_name_obfuscated_RES_2131953722);
        }
        return resources.getString(R.string.f64060_resource_name_obfuscated_RES_2131953723);
    }

    public static String c() {
        Resources resources = ContextUtils.getApplicationContext().getResources();
        String MMltG$kc = N.MMltG$kc("InlineUpdateFlow", "update_notification_experimental_context");
        char c = 65535;
        int hashCode = MMltG$kc.hashCode();
        if (hashCode != -2011176706) {
            if (hashCode != -675034215) {
                if (hashCode == 1544803905 && MMltG$kc.equals("default")) {
                    c = 2;
                }
            } else if (MMltG$kc.equals("experimental_update_chrome")) {
                c = 0;
            }
        } else if (MMltG$kc.equals("experimental_translate_the_web")) {
            c = 1;
        }
        if (c == 0) {
            return resources.getString(R.string.f64100_resource_name_obfuscated_RES_2131953727);
        }
        if (c != 1) {
            return resources.getString(R.string.f64080_resource_name_obfuscated_RES_2131953725);
        }
        return resources.getString(R.string.f64090_resource_name_obfuscated_RES_2131953726);
    }
}
