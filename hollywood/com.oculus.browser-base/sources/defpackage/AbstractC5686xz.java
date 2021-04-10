package defpackage;

import J.N;
import android.text.TextUtils;
import com.oculus.os.Version;
import org.chromium.base.SysUtils;

/* renamed from: xz  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5686xz {

    /* renamed from: a  reason: collision with root package name */
    public static Boolean f11651a;
    public static Boolean[] b = new Boolean[22];
    public static Integer[] c = new Integer[6];
    public static final String[] d = {"disable_translation", "disable_online_detection", "disable_search_term_resolution", "mandatory_promo_enabled", "enable_english_target_translation", "enable_bar_overlap_collection", "enable_bar_overlap_suppression", "enable_word_edge_suppression", "enable_short_word_suppression", "enable_not_long_word_suppression", "enable_not_an_entity_suppression", "enable_engagement_suppression", "enable_short_text_run_suppression", "enable_small_text_suppression", "disable_amp_as_separate_tab", "disable_send_home_country", "disable_page_content_notification", "disable_ukm_ranker_logging", "ContextualSearchMlTapSuppression", "ContextualSearchSecondTap", "ContextualSearchTapDisableOverride", "disable_send_url"};
    public static final String[] e = {"mandatory_promo_limit", "screen_top_suppression_dps", "minimum_selection_length", "wait_after_tap_delay_ms", "tap_duration_threshold_ms", "recent_scroll_duration_ms"};

    public static boolean a(String str) {
        if (AbstractC1575Zv.e().g(str)) {
            return true;
        }
        return TextUtils.equals("true", N.MOVY9QtZ("ContextualSearch", str));
    }

    public static String b(String str) {
        return N.MMltG$kc("RelatedSearches", str);
    }

    public static boolean c(int i) {
        Boolean[] boolArr = b;
        if (boolArr[i] == null) {
            switch (i) {
                case Version.VERSION_18:
                case Version.VERSION_19:
                case Version.VERSION_20:
                    boolArr[i] = Boolean.valueOf(N.M09VlOh_(d[i]));
                    break;
                default:
                    boolArr[i] = Boolean.valueOf(a(d[i]));
                    break;
            }
        }
        return boolArr[i].booleanValue();
    }

    public static int d(int i) {
        Integer[] numArr = c;
        if (numArr[i] == null) {
            String str = e[i];
            int i2 = i == 0 ? 10 : 0;
            String f = AbstractC1575Zv.e().f(str);
            if (TextUtils.isEmpty(f)) {
                f = N.MOVY9QtZ("ContextualSearch", str);
            }
            if (!TextUtils.isEmpty(f)) {
                try {
                    i2 = Integer.parseInt(f);
                } catch (NumberFormatException unused) {
                }
            }
            numArr[i] = Integer.valueOf(i2);
        }
        return c[i].intValue();
    }

    public static boolean e() {
        if (f11651a == null) {
            boolean z = false;
            if (!SysUtils.isLowEndDevice() && !AbstractC1575Zv.e().g("disable-contextual-search") && (AbstractC1575Zv.e().g("enable-contextual-search") || !a("disabled"))) {
                z = true;
            }
            f11651a = Boolean.valueOf(z);
        }
        return f11651a.booleanValue();
    }
}
