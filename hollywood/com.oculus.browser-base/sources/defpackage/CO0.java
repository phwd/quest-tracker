package defpackage;

import org.chromium.chrome.browser.offlinepages.OfflinePageItem;

/* renamed from: CO0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CO0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DO0 f7806a;

    public CO0(DO0 do0) {
        this.f7806a = do0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        DO0 do0 = this.f7806a;
        AbstractC2254ds0.k((OfflinePageItem) obj, do0.f7888a, do0.b);
    }
}
