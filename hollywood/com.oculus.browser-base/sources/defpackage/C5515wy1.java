package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.components.browser_ui.site_settings.WebsitePreferenceBridge;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* renamed from: wy1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5515wy1 extends AbstractC5855yy1 {

    /* renamed from: a  reason: collision with root package name */
    public final int f11579a;
    public final /* synthetic */ Dy1 b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C5515wy1(Dy1 dy1, int i) {
        super(dy1, null);
        this.b = dy1;
        this.f11579a = i;
    }

    @Override // defpackage.AbstractC5855yy1
    public void a() {
        boolean z;
        String str;
        Dy1 dy1 = this.b;
        WebsitePreferenceBridge websitePreferenceBridge = dy1.b;
        BrowserContextHandle browserContextHandle = dy1.f7928a;
        int i = this.f11579a;
        Objects.requireNonNull(websitePreferenceBridge);
        ArrayList arrayList = new ArrayList();
        if (i == 5 || i == 9 || i == 10) {
            z = !N.MB23OvTV(browserContextHandle, i);
        } else {
            z = false;
        }
        N.MexN59P3(browserContextHandle, i, arrayList, z);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            OB0 ob0 = (OB0) it.next();
            String str2 = ob0.H;
            if (str2 != null) {
                if (this.f11579a == 33) {
                    str = null;
                } else {
                    str = ob0.G;
                }
                this.b.d(str2, str).I.put(Integer.valueOf(ob0.I), ob0);
            }
        }
    }
}
