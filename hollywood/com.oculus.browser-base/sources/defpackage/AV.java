package defpackage;

import java.util.List;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.offlinepages.ClientId;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;
import org.chromium.chrome.browser.offlinepages.OfflinePageItem;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: AV  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class AV extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public Tab f7674a;
    public Callback b;
    public OfflinePageBridge c;

    public AV(Tab tab, Callback callback, OfflinePageBridge offlinePageBridge) {
        this.f7674a = tab;
        this.b = callback;
        this.c = offlinePageBridge;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        for (OfflinePageItem offlinePageItem : (List) obj) {
            if (offlinePageItem.f10719a.equals(this.f7674a.s())) {
                AbstractC2254ds0.k(offlinePageItem, this.f7674a.i(), this.b);
                return;
            }
        }
        this.c.b(this.f7674a.l(), new ClientId("live_page_sharing", Integer.toString(this.f7674a.getId())), new DO0(this.f7674a.i(), this.b, this.c));
    }
}
