package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.components.browser_ui.site_settings.WebsitePreferenceBridge;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* renamed from: sy1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4835sy1 extends AbstractC5855yy1 {

    /* renamed from: a  reason: collision with root package name */
    public final int f11310a;
    public final /* synthetic */ Dy1 b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4835sy1(Dy1 dy1, int i) {
        super(dy1, null);
        this.b = dy1;
        this.f11310a = i;
    }

    @Override // defpackage.AbstractC5855yy1
    public void a() {
        Dy1 dy1 = this.b;
        int i = this.f11310a;
        WebsitePreferenceBridge websitePreferenceBridge = dy1.b;
        BrowserContextHandle browserContextHandle = dy1.f7928a;
        Objects.requireNonNull(websitePreferenceBridge);
        ArrayList<C1032Qy> arrayList = new ArrayList();
        N.MI$w5f76(browserContextHandle, i, arrayList);
        if (N.MnAiqOhu(browserContextHandle, i)) {
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                C1032Qy qy = (C1032Qy) it.next();
                if (qy.f8799J.equals("policy")) {
                    arrayList2.add(qy);
                }
            }
            arrayList = arrayList2;
        }
        for (C1032Qy qy2 : arrayList) {
            String str = qy2.G;
            String str2 = qy2.H;
            if (str != null && (!str.equals(str2) || !str.equals("*"))) {
                dy1.d(str, str2).H.put(Integer.valueOf(i), qy2);
            }
        }
    }
}
