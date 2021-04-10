package org.chromium.chrome.browser.previews;

import J.N;
import android.app.Activity;
import com.oculus.browser.R;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder;
import org.chromium.content_public.browser.WebContents;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class PreviewsAndroidBridge {

    /* renamed from: a  reason: collision with root package name */
    public static PreviewsAndroidBridge f10749a;
    public final long b = N.MZa4o8Eq(this);

    public static PreviewsAndroidBridge a() {
        if (f10749a == null) {
            f10749a = new PreviewsAndroidBridge();
        }
        return f10749a;
    }

    public static boolean createHttpsImageCompressionInfoBar(Tab tab) {
        Activity b2 = AbstractC5112ud1.b(tab);
        if (b2 == null) {
            return false;
        }
        SimpleConfirmInfoBarBuilder.a(tab.l(), new C3220jY(tab), 100, tab.getContext(), R.drawable.f34660_resource_name_obfuscated_RES_2131231506, b2.getString(R.string.f54150_resource_name_obfuscated_RES_2131952732), null, null, b2.getString(R.string.f54160_resource_name_obfuscated_RES_2131952733), false);
        AbstractC3364kK0.g("SubresourceRedirect.ImageCompressionNotificationInfoBar", 0, 3);
        return true;
    }

    public String b(WebContents webContents) {
        return N.M6rlffHy(this.b, this, webContents);
    }
}
