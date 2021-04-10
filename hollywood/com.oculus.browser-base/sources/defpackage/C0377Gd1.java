package defpackage;

import android.os.Handler;
import java.util.Iterator;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* renamed from: Gd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0377Gd1 extends AbstractC0499Id1 {
    public final TabImpl G;
    public final C1322Vq0 H = new C1322Vq0();
    public AbstractC6022zx1 I;

    /* renamed from: J  reason: collision with root package name */
    public GURL f8098J;

    public C0377Gd1(Tab tab) {
        super(tab);
        new Handler();
        this.G = (TabImpl) tab;
    }

    public static C0377Gd1 j(Tab tab) {
        C0377Gd1 gd1 = (C0377Gd1) tab.M().c(C0377Gd1.class);
        if (gd1 != null) {
            return gd1;
        }
        C0377Gd1 gd12 = new C0377Gd1(tab);
        tab.M().e(C0377Gd1.class, gd12);
        return gd12;
    }

    @Override // defpackage.AbstractC0499Id1
    public void c(WebContents webContents) {
        AbstractC6022zx1 zx1 = this.I;
        if (zx1 != null) {
            zx1.destroy();
            this.I = null;
        }
    }

    @Override // defpackage.AbstractC0499Id1
    public void e() {
        this.H.clear();
    }

    @Override // defpackage.AbstractC0499Id1
    public void h(WebContents webContents) {
        this.I = new C0316Fd1(this, webContents);
        ((WebContentsAccessibilityImpl) AbstractC3637lx1.a(webContents)).e0 = true;
        Iterator it = this.H.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((Callback) uq0.next()).onResult(webContents);
            } else {
                return;
            }
        }
    }
}
