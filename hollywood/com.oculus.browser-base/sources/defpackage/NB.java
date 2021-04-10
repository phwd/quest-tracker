package defpackage;

import android.app.PendingIntent;
import android.content.ComponentName;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.url.GURL;

/* renamed from: NB  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NB extends C2800h3 {
    public final ChromeActivity d = null;
    public final int e;
    public final String f;
    public final int g;
    public final C3432km0 h;
    public final boolean i;
    public final PendingIntent j;

    public NB(Tab tab, ChromeActivity chromeActivity, int i2, String str, int i3, C3432km0 km0, boolean z, PendingIntent pendingIntent) {
        super(tab, null);
        this.e = i2;
        this.f = str;
        this.g = i3;
        this.h = km0;
        this.i = z;
        this.j = pendingIntent;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid, defpackage.C2800h3
    public int a() {
        return this.g;
    }

    @Override // defpackage.C2800h3
    public void b() {
        int i2 = this.e;
        if (!(i2 == 3 || i2 == 4)) {
            PendingIntent pendingIntent = this.j;
            if (pendingIntent != null) {
                try {
                    pendingIntent.send();
                } catch (PendingIntent.CanceledException unused) {
                    AbstractC1220Ua0.a("CustomTabWebContentsDelegate", "CanceledException when sending pending intent.", new Object[0]);
                }
            }
        } else {
            C5859z.a(this.d);
            throw null;
        }
    }

    @Override // defpackage.AbstractC0133Cd1
    public boolean canShowAppBanners() {
        return this.e == 1;
    }

    @Override // defpackage.AbstractC0133Cd1
    public String getManifestScope() {
        return this.f;
    }

    @Override // defpackage.AbstractC0133Cd1
    public boolean isInstalledWebappDelegateGeolocation() {
        return false;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void openNewTab(GURL gurl, String str, ResourceRequestBody resourceRequestBody, int i2, boolean z) {
        if (i2 != 8) {
            super.openNewTab(gurl, str, resourceRequestBody, i2, z);
        } else if (!z) {
            LoadUrlParams loadUrlParams = new LoadUrlParams(gurl.h(), 0);
            loadUrlParams.f = str;
            loadUrlParams.h = resourceRequestBody;
            loadUrlParams.i = z;
            new B61(true).g(new C0975Qa(loadUrlParams, new ComponentName(ContextUtils.getApplicationContext(), this.h.e(null, ContextUtils.getApplicationContext()))), 4, -1);
        } else {
            throw new IllegalStateException("Invalid attempt to open an incognito tab from the renderer");
        }
    }

    @Override // defpackage.AbstractC0133Cd1
    public boolean shouldEnableEmbeddedMediaExperience() {
        return this.i;
    }

    @Override // defpackage.AbstractC0133Cd1, defpackage.C2800h3
    public boolean shouldResumeRequestsForCreatedWindow() {
        return true;
    }
}
