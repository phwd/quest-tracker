package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.chrome.browser.offlinepages.OfflinePageBridge;

/* renamed from: Wh1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1364Wh1 extends AbstractC0471Hr0 {

    /* renamed from: a  reason: collision with root package name */
    public final OfflinePageBridge f9168a;
    public final /* synthetic */ C1425Xh1 b;

    public C1364Wh1(C1425Xh1 xh1, OfflinePageBridge offlinePageBridge) {
        this.b = xh1;
        this.f9168a = offlinePageBridge;
        offlinePageBridge.c.b(this);
    }

    public Iterable a() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.b.g.size(); i++) {
            arrayList.addAll((Collection) this.b.g.valueAt(i));
        }
        return arrayList;
    }

    public void b(boolean z) {
        K31 k31;
        if (z) {
            int i = 0;
            Iterator it = ((ArrayList) a()).iterator();
            while (it.hasNext()) {
                AbstractC2422er0 er0 = (AbstractC2422er0) it.next();
                i++;
            }
            k31 = new K31(i);
        } else {
            k31 = null;
        }
        Iterator it2 = ((ArrayList) a()).iterator();
        while (it2.hasNext()) {
            AbstractC2422er0 er02 = (AbstractC2422er0) it2.next();
            Objects.requireNonNull(er02);
            c(er02, k31);
        }
    }

    public void c(AbstractC2422er0 er0, K31 k31) {
        OfflinePageBridge offlinePageBridge = this.f9168a;
        if (offlinePageBridge.b) {
            N.MR_37z77(offlinePageBridge.f10718a, offlinePageBridge, ((C0815Nh1) er0).f8567a.b, 0, new J31(this, k31, er0));
        } else if (k31 != null) {
            k31.a(false);
        }
    }
}
