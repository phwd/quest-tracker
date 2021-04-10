package defpackage;

import J.N;
import android.content.Context;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* renamed from: va1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5273va1 extends AbstractC1566Zq0 {
    public final /* synthetic */ C5613xa1 m;
    public final /* synthetic */ C3390kX0 n;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C5273va1(Context context, AbstractC3226ja1 ja1, WindowAndroid windowAndroid, boolean z, C1280Va va, C5613xa1 xa1, C3390kX0 kx0) {
        super(context, ja1, windowAndroid, z, va);
        this.m = xa1;
        this.n = kx0;
    }

    @Override // defpackage.C3623lt, defpackage.A61
    public boolean c(Tab tab, WebContents webContents, int i, GURL gurl) {
        String h = gurl.h();
        if (k()) {
            N.M4p7Ma6S(this.m.c().f9704J, h);
            return false;
        }
        Tab tabAt = this.n.i().getTabAt(0);
        if (tabAt == null || tabAt != tab || "about:blank#blocked".equals(h)) {
            return super.c(tab, webContents, i, gurl);
        }
        tabAt.c(new LoadUrlParams(h, 0));
        webContents.destroy();
        return true;
    }

    @Override // defpackage.C3623lt, defpackage.A61
    public Tab f(String str, int i) {
        LoadUrlParams loadUrlParams = new LoadUrlParams(str, 0);
        loadUrlParams.k = 0;
        if (k()) {
            loadUrlParams.g = 2;
        }
        return i(loadUrlParams, i, null, null);
    }
}
