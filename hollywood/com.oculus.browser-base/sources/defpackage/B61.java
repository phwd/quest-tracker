package defpackage;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* renamed from: B61  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class B61 extends AbstractC1036Ra {

    /* renamed from: a  reason: collision with root package name */
    public final boolean f7717a;

    public B61(boolean z) {
        this.f7717a = z;
    }

    @Override // defpackage.A61
    public Tab a(C0797Nb1 nb1, byte[] bArr, int i, boolean z, int i2) {
        if (z == this.f7717a) {
            C2128d61 d61 = new C2128d61();
            d61.d(3);
            d61.c(2);
            d61.h = true;
            d61.f9748a = i;
            d61.d = z;
            return d61.a();
        }
        throw new IllegalStateException("Incognito state mismatch. isIncognito: " + z + ". TabDelegate: " + this.f7717a);
    }

    @Override // defpackage.A61
    public Tab b(LoadUrlParams loadUrlParams, int i, Tab tab) {
        int i2;
        C0975Qa qa = new C0975Qa(loadUrlParams);
        if (tab == null) {
            i2 = -1;
        } else {
            i2 = tab.getId();
        }
        g(qa, i, i2);
        return null;
    }

    @Override // defpackage.A61
    public boolean c(Tab tab, WebContents webContents, int i, GURL gurl) {
        g(new C0975Qa(new LoadUrlParams(gurl.h(), 6), webContents), i, tab != null ? tab.getId() : -1);
        return true;
    }

    @Override // defpackage.A61
    public boolean d() {
        return true;
    }

    @Override // defpackage.A61
    public Tab f(String str, int i) {
        b(new LoadUrlParams(str, 0), i, null);
        return null;
    }

    public void g(C0975Qa qa, int i, int i2) {
        S20.y(h(qa, i2, i == 2), null);
    }

    public final Intent h(C0975Qa qa, int i, boolean z) {
        C4184p81 a2 = C4184p81.a();
        int andIncrement = a2.c.getAndIncrement();
        a2.b(andIncrement + 1);
        AbstractC1341Wa.f9155a.a(andIncrement, qa);
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(qa.f8769a.f10938a));
        ComponentName componentName = qa.d;
        if (componentName == null) {
            intent.setClass(ContextUtils.getApplicationContext(), Lr.class);
        } else {
            AbstractActivityC2601fu.K1(intent, componentName);
        }
        S20.w(qa.f8769a.e, intent);
        intent.putExtra("com.android.chrome.tab_id", andIncrement);
        intent.putExtra("com.google.android.apps.chrome.EXTRA_OPEN_NEW_INCOGNITO_TAB", this.f7717a);
        intent.putExtra("com.android.chrome.parent_tab_id", i);
        if (this.f7717a || z) {
            intent.putExtra("com.android.browser.application_id", ContextUtils.getApplicationContext().getPackageName());
        }
        if (z) {
            intent.putExtra("create_new_tab", true);
        }
        ChromeActivity chromeActivity = null;
        if (i != -1) {
            Iterator it = ((ArrayList) ApplicationStatus.d()).iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Activity activity = (Activity) it.next();
                if (activity instanceof ChromeActivity) {
                    ChromeActivity chromeActivity2 = (ChromeActivity) activity;
                    if (((AbstractC0246Ea1) chromeActivity2.P()).o(i) != null) {
                        chromeActivity = chromeActivity2;
                        break;
                    }
                }
            }
        }
        if (!(chromeActivity == null || chromeActivity.getIntent() == null)) {
            intent.putExtra("com.android.chrome.parent_intent", chromeActivity.getIntent());
        }
        Integer num = qa.c;
        if (num != null) {
            intent.putExtra("org.chromium.chrome.browser.ServiceTabLauncher.LAUNCH_REQUEST_ID", num.intValue());
        }
        intent.setFlags(268435456);
        return intent;
    }
}
