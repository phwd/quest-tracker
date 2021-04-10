package org.chromium.chrome.browser.banners;

import J.N;
import android.text.TextUtils;
import com.oculus.browser.R;
import org.chromium.base.ThreadUtils;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AppBannerManager {

    /* renamed from: a  reason: collision with root package name */
    public static final C5711y7 f10618a = new C5711y7(R.string.f54460_resource_name_obfuscated_RES_2131952763, R.string.f46920_resource_name_obfuscated_RES_2131952009);
    public static final C5711y7 b = new C5711y7(R.string.f54450_resource_name_obfuscated_RES_2131952762, R.string.f46530_resource_name_obfuscated_RES_2131951970);
    public static Boolean c;

    public AppBannerManager(long j) {
    }

    public static C5711y7 a(WebContents webContents) {
        AppBannerManager appBannerManager;
        if (webContents != null) {
            Object obj = ThreadUtils.f10596a;
            appBannerManager = (AppBannerManager) N.MEiNcXHa(webContents);
        } else {
            appBannerManager = null;
        }
        if (appBannerManager == null || !(!TextUtils.equals("", N.MjJ5MWI_(webContents)))) {
            return b;
        }
        return f10618a;
    }

    public static AppBannerManager create(long j) {
        return new AppBannerManager(j);
    }

    public static boolean isSupported() {
        if (c == null) {
            c = Boolean.FALSE;
        }
        return c.booleanValue();
    }

    public final void destroy() {
    }

    public final void fetchAppDetails(String str, String str2, String str3, int i) {
    }
}
