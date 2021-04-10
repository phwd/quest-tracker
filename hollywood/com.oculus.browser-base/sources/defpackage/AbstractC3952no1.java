package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.Collection;
import org.chromium.components.browser_ui.site_settings.AllSiteSettings;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;

/* renamed from: no1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3952no1 {
    public static Integer a(Context context, String str) {
        try {
            return Integer.valueOf(context.getPackageManager().getApplicationInfo(str, 0).uid);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static void b(Context context, Collection collection, Collection collection2) {
        if (collection.size() == 1) {
            c(context, (String) collection.iterator().next());
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("category", QX0.p(0));
        bundle.putString("title", context.getString(R.string.f63850_resource_name_obfuscated_RES_2131953702));
        bundle.putStringArrayList("selected_domains", new ArrayList<>(collection2));
        bundle.putInt("org.chromium.chrome.preferences.navigation_source", 1);
        String name = AllSiteSettings.class.getName();
        Intent l = AbstractC2531fV.l(context, XS0.class);
        if (!(context instanceof Activity)) {
            l.addFlags(268435456);
            l.addFlags(67108864);
        }
        l.putExtra("show_fragment", name);
        l.putExtra("show_fragment_args", bundle);
        U20.q(context, l);
    }

    public static void c(Context context, String str) {
        Bundle k1 = SingleWebsiteSettings.k1(str);
        k1.putInt("org.chromium.chrome.preferences.navigation_source", 1);
        String name = SingleWebsiteSettings.class.getName();
        Intent l = AbstractC2531fV.l(context, XS0.class);
        if (!(context instanceof Activity)) {
            l.addFlags(268435456);
            l.addFlags(67108864);
        }
        l.putExtra("show_fragment", name);
        l.putExtra("show_fragment_args", k1);
        context.startActivity(l);
    }
}
