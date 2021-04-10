package defpackage;

import J.N;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* renamed from: md  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3746md extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final ChromeActivity f10435a;
    public final C3404kd b;
    public final Tab c;

    public C3746md(ChromeActivity chromeActivity, C3404kd kdVar, Tab tab) {
        this.f10435a = chromeActivity;
        this.b = kdVar;
        this.c = tab;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        ChromeActivity chromeActivity = this.f10435a;
        C3404kd kdVar = this.b;
        Tab tab = this.c;
        AbstractC4600rd rdVar = (AbstractC4600rd) obj;
        if (rdVar == null || chromeActivity.v()) {
            AbstractC3364kK0.g("Android.AutofillAssistant.DropOutReason", 20, 28);
            if (kdVar.c()) {
                WebContents l = tab.l();
                if (l != null && !l.g()) {
                    N.M5aNQ$DO(l, "AutofillAssistant.LiteScriptFinished", "LiteScriptFinished", 0);
                    return;
                }
                return;
            }
            return;
        }
        AbstractC4088od.c(chromeActivity, kdVar, rdVar);
    }
}
