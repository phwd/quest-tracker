package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.components.browser_ui.site_settings.WebsitePreferenceBridge;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* renamed from: By1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class By1 extends AbstractC5855yy1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Dy1 f7776a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public By1(Dy1 dy1, AbstractC4495qy1 qy1) {
        super(dy1, null);
        this.f7776a = dy1;
    }

    @Override // defpackage.AbstractC5855yy1
    public void b(C6025zy1 zy1) {
        Dy1 dy1 = this.f7776a;
        WebsitePreferenceBridge websitePreferenceBridge = dy1.b;
        BrowserContextHandle browserContextHandle = dy1.f7928a;
        Ay1 ay1 = new Ay1(this, zy1);
        Objects.requireNonNull(websitePreferenceBridge);
        N.MlZM1XeP(browserContextHandle, ay1);
    }
}
