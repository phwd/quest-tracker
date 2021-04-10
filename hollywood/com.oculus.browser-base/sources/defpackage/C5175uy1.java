package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.components.browser_ui.site_settings.WebsitePreferenceBridge;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* renamed from: uy1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5175uy1 extends AbstractC5855yy1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Dy1 f11450a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C5175uy1(Dy1 dy1, AbstractC4495qy1 qy1) {
        super(dy1, null);
        this.f11450a = dy1;
    }

    @Override // defpackage.AbstractC5855yy1
    public void b(C6025zy1 zy1) {
        Dy1 dy1 = this.f11450a;
        WebsitePreferenceBridge websitePreferenceBridge = dy1.b;
        BrowserContextHandle browserContextHandle = dy1.f7928a;
        C5005ty1 ty1 = new C5005ty1(this, zy1);
        boolean z = dy1.d;
        Objects.requireNonNull(websitePreferenceBridge);
        N.MvKW9dxN(browserContextHandle, ty1, z);
    }
}
