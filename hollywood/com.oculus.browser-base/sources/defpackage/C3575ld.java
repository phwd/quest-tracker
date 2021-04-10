package defpackage;

import J.N;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: ld  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3575ld extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3404kd f10357a;
    public final ChromeActivity b;

    public C3575ld(C3404kd kdVar, ChromeActivity chromeActivity) {
        this.f10357a = kdVar;
        this.b = chromeActivity;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        boolean z;
        C3404kd kdVar = this.f10357a;
        ChromeActivity chromeActivity = this.b;
        Tab tab = (Tab) obj;
        AbstractC4600rd rdVar = null;
        if (kdVar.c()) {
            N.MGqzwlIM("AutofillAssistantLiteScriptExperiment", kdVar.d("TRIGGER_SCRIPT_EXPERIMENT") ? "Experiment" : "Control");
            AbstractC4259pd.b(tab.l(), 6);
            if (kdVar.b()) {
                boolean z2 = false;
                if (!N.M09VlOh_("AutofillAssistantProactiveHelp")) {
                    z = false;
                } else {
                    z = NU0.f8549a.d("Chrome.AutofillAssistant.ProactiveHelp", true);
                }
                if (!(z && NU0.f8549a.d("autofill_assistant_switch", true))) {
                    if (NU0.f8549a.f("Chrome.AutofillAssistant.NumberOfLiteScriptsCanceled", 0) >= 2) {
                        z2 = true;
                    }
                    if (z2) {
                        AbstractC4259pd.b(tab.l(), 1);
                        return;
                    }
                    return;
                }
            }
            C4455ql0 ql0 = AbstractC4430qd.f11152a;
            if ((ql0.f() ? (AbstractC4600rd) ql0.b() : null) == null && kdVar.c() && !N.M09VlOh_("AutofillAssistantLoadDFMForTriggerScripts")) {
                return;
            }
        }
        C4455ql0 ql02 = AbstractC4430qd.f11152a;
        if ((ql02.f() ? (AbstractC4600rd) ql02.b() : null) == null) {
            C3746md mdVar = new C3746md(chromeActivity, kdVar, tab);
            boolean z3 = !kdVar.c();
            if (ql02.f()) {
                rdVar = (AbstractC4600rd) ql02.b();
            }
            if (rdVar != null) {
                AbstractC4259pd.a(3);
                mdVar.onResult(rdVar);
                return;
            }
            C5110ud.a(tab, mdVar, z3);
            return;
        }
        if (ql02.f()) {
            rdVar = (AbstractC4600rd) ql02.b();
        }
        AbstractC4088od.c(chromeActivity, kdVar, rdVar);
    }
}
