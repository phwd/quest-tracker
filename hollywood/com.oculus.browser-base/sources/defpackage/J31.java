package defpackage;

import android.text.TextUtils;
import java.util.Objects;
import org.chromium.chrome.browser.offlinepages.OfflinePageItem;

/* renamed from: J31  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class J31 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ K31 f8266a;
    public final /* synthetic */ AbstractC2422er0 b;
    public final /* synthetic */ C1364Wh1 c;

    public J31(C1364Wh1 wh1, K31 k31, AbstractC2422er0 er0) {
        this.c = wh1;
        this.f8266a = k31;
        this.b = er0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        Long l;
        OfflinePageItem offlinePageItem = (OfflinePageItem) obj;
        K31 k31 = this.f8266a;
        if (k31 != null) {
            k31.a(offlinePageItem != null && TextUtils.equals(offlinePageItem.c.f10716a, "suggested_articles"));
        }
        C1364Wh1 wh1 = this.c;
        AbstractC2422er0 er0 = this.b;
        Objects.requireNonNull(wh1);
        C0815Nh1 nh1 = (C0815Nh1) er0;
        boolean a2 = nh1.a();
        if (offlinePageItem == null) {
            l = null;
        } else {
            l = Long.valueOf(offlinePageItem.b);
        }
        nh1.f = l;
        if (a2 != nh1.a()) {
            wh1.b.c.c(nh1);
        }
    }
}
