package defpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: cc1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2037cc1 extends C5616xb1 {
    public final /* synthetic */ C1529Zb1 c;
    public final /* synthetic */ Callback d;
    public final /* synthetic */ C2891hc1 e;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C2037cc1(C2891hc1 hc1, AbstractC0249Eb1 eb1, int i, C1529Zb1 zb1, Callback callback) {
        super(eb1, i);
        this.e = hc1;
        this.c = zb1;
        this.d = callback;
    }

    @Override // defpackage.C5616xb1
    public void a(List list, AbstractC0124Ca1 ca1) {
        AbstractC0246Ea1 ea1 = (AbstractC0246Ea1) ca1;
        int count = ea1.i().getCount();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            arrayList.add(Integer.valueOf(((Tab) list.get(i)).getId()));
        }
        C2891hc1 hc1 = this.e;
        C1529Zb1 zb1 = this.c;
        Callback callback = this.d;
        Objects.requireNonNull(hc1);
        callback.onResult(new C1686ac1(zb1, 2, arrayList, count));
        super.a(list, ea1);
    }
}
