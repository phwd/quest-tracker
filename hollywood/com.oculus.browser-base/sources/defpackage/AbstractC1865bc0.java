package defpackage;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.preference.Preference;
import com.oculus.browser.R;
import java.util.Locale;
import org.chromium.components.browser_ui.settings.ChromeImageViewPreference;

/* renamed from: bc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1865bc0 {
    public static Drawable a(AbstractC1528Zb0 zb0, Preference preference) {
        int i;
        int i2;
        if (zb0.d(preference)) {
            i = R.drawable.f29660_resource_name_obfuscated_RES_2131231006;
        } else {
            i = zb0.a(preference) ? R.drawable.f29470_resource_name_obfuscated_RES_2131230987 : 0;
        }
        if (i != 0) {
            return AbstractC2870hT0.b(preference.F, i);
        }
        if (preference.P == null && (i2 = preference.O) != 0) {
            preference.P = AbstractC5544x8.a(preference.F, i2);
        }
        return preference.P;
    }

    public static void b(AbstractC1528Zb0 zb0, Preference preference) {
        if (zb0 != null) {
            if (!(preference instanceof ChromeImageViewPreference)) {
                preference.N(a(zb0, preference));
            }
            if (zb0.b(preference)) {
                if (preference.j0) {
                    preference.j0 = false;
                    preference.s();
                }
                preference.K(false);
                preference.S = null;
                preference.R = null;
                preference.K = null;
            }
        }
    }

    public static void c(AbstractC1528Zb0 zb0, Preference preference, View view) {
        if (zb0 != null) {
            if (zb0.b(preference)) {
                AbstractC4656rv1.g(view, false);
            }
            TextView textView = (TextView) view.findViewById(16908304);
            String str = null;
            String text = textView.getVisibility() == 0 ? textView.getText() : null;
            if (zb0.d(preference)) {
                str = preference.F.getString(R.string.f54350_resource_name_obfuscated_RES_2131952752);
            } else if (zb0.a(preference)) {
                str = preference.F.getString(zb0.c() ? R.string.f54370_resource_name_obfuscated_RES_2131952754 : R.string.f54360_resource_name_obfuscated_RES_2131952753);
            }
            if (!TextUtils.isEmpty(str)) {
                if (TextUtils.isEmpty(text)) {
                    text = str;
                } else {
                    text = String.format(Locale.getDefault(), "%s\n%s", text, str);
                }
            }
            if (!TextUtils.isEmpty(text)) {
                textView.setText(text);
                textView.setVisibility(0);
            }
        }
    }

    public static boolean d(AbstractC1528Zb0 zb0, Preference preference) {
        if (zb0 == null || !zb0.b(preference)) {
            return false;
        }
        if (zb0.d(preference)) {
            e(preference.F);
            return true;
        } else if (!zb0.a(preference)) {
            return true;
        } else {
            f(preference.F, zb0);
            return true;
        }
    }

    public static void e(Context context) {
        C1184Ti1.b(context, context.getString(R.string.f54350_resource_name_obfuscated_RES_2131952752), 1).b.show();
    }

    public static void f(Context context, AbstractC1528Zb0 zb0) {
        C1184Ti1.b(context, context.getString(zb0.c() ? R.string.f54370_resource_name_obfuscated_RES_2131952754 : R.string.f54360_resource_name_obfuscated_RES_2131952753), 1).b.show();
    }
}
