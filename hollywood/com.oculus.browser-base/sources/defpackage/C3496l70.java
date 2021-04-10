package defpackage;

import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;

/* renamed from: l70  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3496l70 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC0956Pq0 f10325a;
    public final /* synthetic */ AbstractC3838n70 b;

    public C3496l70(AbstractC3838n70 n70, AbstractC0956Pq0 pq0) {
        this.b = n70;
        this.f10325a = pq0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        TabContentManager tabContentManager = (TabContentManager) obj;
        AbstractC2300e70 e70 = this.b.t0;
        if (e70 != null) {
            e70.N(tabContentManager);
        }
        ((C1078Rq0) this.f10325a).I.c(this);
    }
}
