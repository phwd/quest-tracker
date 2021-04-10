package defpackage;

import J.N;
import java.util.HashSet;
import java.util.Set;
import org.chromium.chrome.browser.webapps.WebappRegistry;

/* renamed from: xG0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5569xG0 implements Runnable {
    public void run() {
        PU0 pu0 = NU0.f8549a;
        Set<String> j = pu0.j("webapk_uninstalled_packages");
        if (!j.isEmpty()) {
            long currentTimeMillis = System.currentTimeMillis();
            WebappRegistry.d();
            for (String str : j) {
                AbstractC3100ip1.f10165a.a("WebApk.Uninstall.Browser", true);
                Xx1 c = AbstractC2957hy1.f10115a.c(AbstractC2103cy1.a(str));
                if (c != null) {
                    long j2 = c.c.getLong("webapk_uninstall_timestamp", 0);
                    if (j2 == 0) {
                        j2 = currentTimeMillis;
                    }
                    N.MSohKQE6(c.c.getString("webapk_manifest_url", null), 0, c.c.getInt("webapk_version_code", 0), c.c.getInt("launch_count", 0), j2 - c.c.getLong("webapk_install_timestamp", 0));
                }
            }
            pu0.q("webapk_uninstalled_packages", new HashSet());
        }
    }
}
