package defpackage;

import J.N;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import org.chromium.components.browser_ui.site_settings.WebsitePreferenceBridge;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* renamed from: ry1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4665ry1 extends AbstractC5855yy1 {

    /* renamed from: a  reason: collision with root package name */
    public final int f11239a;
    public final /* synthetic */ Dy1 b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C4665ry1(Dy1 dy1, int i) {
        super(dy1, null);
        this.b = dy1;
        this.f11239a = i != 21 ? i != 37 ? -1 : 20 : 53;
    }

    @Override // defpackage.AbstractC5855yy1
    public void a() {
        int i = this.f11239a;
        if (i != -1) {
            Dy1 dy1 = this.b;
            WebsitePreferenceBridge websitePreferenceBridge = dy1.b;
            BrowserContextHandle browserContextHandle = dy1.f7928a;
            Objects.requireNonNull(websitePreferenceBridge);
            ArrayList arrayList = new ArrayList();
            N.MA5QWuba(browserContextHandle, i, arrayList);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                C5316vp vpVar = (C5316vp) it.next();
                String str = vpVar.G;
                if (str != null) {
                    this.b.d(str, vpVar.H).L.add(vpVar);
                }
            }
        }
    }
}
