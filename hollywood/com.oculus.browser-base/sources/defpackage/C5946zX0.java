package defpackage;

import java.util.Objects;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;
import org.chromium.components.embedder_support.browser_context.BrowserContextHandle;

/* renamed from: zX0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C5946zX0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final SingleWebsiteSettings f11748a;

    public C5946zX0(SingleWebsiteSettings singleWebsiteSettings) {
        this.f11748a = singleWebsiteSettings;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        SingleWebsiteSettings singleWebsiteSettings = this.f11748a;
        Objects.requireNonNull(singleWebsiteSettings);
        if (((Boolean) obj).booleanValue()) {
            C3469ky1 ky1 = singleWebsiteSettings.M0;
            BrowserContextHandle browserContextHandle = singleWebsiteSettings.G0.b;
            Runnable runnable = singleWebsiteSettings.T0;
            runnable.getClass();
            ky1.a(browserContextHandle, new FX0(runnable));
        }
    }
}
