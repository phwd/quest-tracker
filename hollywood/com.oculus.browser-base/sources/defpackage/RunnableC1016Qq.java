package defpackage;

import java.util.Arrays;
import java.util.Objects;
import org.chromium.base.BundleUtils;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.ContextUtils;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.flags.CachedFeatureFlags;

/* renamed from: Qq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1016Qq implements Runnable {
    public final C1321Vq F;

    public RunnableC1016Qq(C1321Vq vq) {
        this.F = vq;
    }

    public void run() {
        C1321Vq vq = this.F;
        if (!vq.g) {
            vq.g = true;
            XP xp = new XP();
            synchronized (ContentUriUtils.b) {
                ContentUriUtils.f10584a = xp;
            }
            ComponentCallbacks2C3933ni0 ni0 = ComponentCallbacks2C3933ni0.F;
            Object obj = ThreadUtils.f10596a;
            ComponentCallbacks2C3933ni0.F = new ComponentCallbacks2C3933ni0("Browser");
            ContextUtils.getApplicationContext().registerComponentCallbacks(ComponentCallbacks2C3933ni0.F);
            CachedFeatureFlags.a(Arrays.asList("ServiceManagerForDownload", "ServiceManagerForBackgroundPrefetch"));
            Boolean bool = BundleUtils.f10583a;
            Objects.requireNonNull(AppHooks.get());
        }
    }
}
