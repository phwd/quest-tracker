package defpackage;

import J.N;
import android.content.Context;
import java.util.Objects;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.paint_preview.services.PaintPreviewTabService;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: l11  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3479l11 extends VK {
    public final /* synthetic */ AbstractC0124Ca1 F;
    public final /* synthetic */ WindowAndroid G;

    public C3479l11(AbstractC0124Ca1 ca1, WindowAndroid windowAndroid) {
        this.F = ca1;
        this.G = windowAndroid;
    }

    @Override // defpackage.VK, defpackage.AbstractC0612Ka1
    public void b() {
        boolean z;
        Tab j = ((AbstractC0246Ea1) this.F).j();
        boolean z2 = true;
        if (j == null || j.p() || j.isNativePage()) {
            z = true;
        } else {
            String g = j.getUrl().g();
            z = !(g.equals("http") || g.equals("https"));
        }
        if (z) {
            AbstractC3992o11.f10528a = false;
        }
        Context context = (Context) this.G.f11022J.get();
        if (context != null && C3432km0.F.a(context)) {
            z2 = false;
        }
        PaintPreviewTabService paintPreviewTabService = (PaintPreviewTabService) N.M$ZOxizP();
        AbstractC0124Ca1 ca1 = this.F;
        Objects.requireNonNull(paintPreviewTabService);
        new C1515Yv0(paintPreviewTabService, paintPreviewTabService, ca1, false, null);
        if (z2 && paintPreviewTabService.f10728a == null) {
            paintPreviewTabService.f10728a = new RunnableC1271Uv0(paintPreviewTabService, ca1);
            PostTask.b(Zo1.f9374a, new RunnableC1332Vv0(paintPreviewTabService), 120000);
        }
        ((AbstractC0246Ea1) this.F).f.c(this);
    }
}
