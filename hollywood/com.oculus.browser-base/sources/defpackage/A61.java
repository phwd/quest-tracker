package defpackage;

import org.chromium.base.TraceEvent;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* renamed from: A61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class A61 {
    public abstract Tab a(C0797Nb1 nb1, byte[] bArr, int i, boolean z, int i2);

    public abstract Tab b(LoadUrlParams loadUrlParams, int i, Tab tab);

    public abstract boolean c(Tab tab, WebContents webContents, int i, GURL gurl);

    public abstract boolean d();

    public final void e() {
        try {
            TraceEvent.Y("TabCreator.launchNTP", null);
            f("chrome-native://newtab/", 2);
        } finally {
            TraceEvent.f0("TabCreator.launchNTP");
        }
    }

    public abstract Tab f(String str, int i);
}
