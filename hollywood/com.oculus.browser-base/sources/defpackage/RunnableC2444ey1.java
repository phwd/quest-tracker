package defpackage;

import android.util.Pair;
import java.util.List;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.webapps.WebappRegistry;

/* renamed from: ey1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2444ey1 implements Runnable {
    public final WebappRegistry F;
    public final List G;

    public RunnableC2444ey1(WebappRegistry webappRegistry, List list) {
        this.F = webappRegistry;
        this.G = list;
    }

    public void run() {
        WebappRegistry webappRegistry = this.F;
        List<Pair> list = this.G;
        Objects.requireNonNull(webappRegistry);
        Object obj = ThreadUtils.f10596a;
        for (Pair pair : list) {
            if (!webappRegistry.b.containsKey(pair.first)) {
                webappRegistry.b.put((String) pair.first, (Xx1) pair.second);
            }
        }
    }
}
