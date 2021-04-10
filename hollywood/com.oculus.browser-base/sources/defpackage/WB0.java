package defpackage;

import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: WB0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class WB0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final Tab f9133a;
    public final Class b;
    public final Callback c;

    public WB0(Tab tab, Class cls, Callback callback) {
        this.f9133a = tab;
        this.b = cls;
        this.c = callback;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Tab tab = this.f9133a;
        Class cls = this.b;
        Callback callback = this.c;
        AbstractC2145dC0 dc0 = (AbstractC2145dC0) obj;
        AbstractC2145dC0.p(dc0);
        if (dc0 != null) {
            AbstractC2145dC0 dc02 = (AbstractC2145dC0) tab.M().e(cls, dc0);
        }
        PostTask.c(Zo1.f9374a, new RunnableC1974cC0(callback, dc0));
    }
}
