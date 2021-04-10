package org.chromium.chrome.browser.ntp;

import J.N;
import java.util.List;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.url.GURL;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RecentlyClosedBridge {

    /* renamed from: a  reason: collision with root package name */
    public long f10713a;

    public RecentlyClosedBridge(Profile profile) {
        this.f10713a = N.Mlookj5S(this, profile);
    }

    public static void pushTab(List list, int i, String str, GURL gurl) {
        list.add(new C3193jK0(i, str, gurl));
    }

    public final void onUpdated() {
    }
}
