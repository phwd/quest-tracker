package defpackage;

import android.content.Intent;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.url.Origin;

/* renamed from: dq  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2247dq implements R20 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ChromeActivity f9810a;

    public C2247dq(ChromeActivity chromeActivity) {
        this.f9810a = chromeActivity;
    }

    @Override // defpackage.R20
    public void a(String str) {
        Intent intent = new Intent("android.intent.action.WEB_SEARCH");
        intent.putExtra("query", str);
        LocaleManager.getInstance().f(this.f9810a, new C2077cq(this, intent));
    }

    @Override // defpackage.R20
    public void b(String str, String str2) {
    }

    @Override // defpackage.R20
    public void c(String str, String str2, String str3, int i, String str4, int i2, boolean z, boolean z2, Origin origin, Intent intent) {
    }
}
