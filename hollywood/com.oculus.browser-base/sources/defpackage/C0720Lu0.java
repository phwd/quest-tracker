package defpackage;

import J.N;
import java.util.Objects;
import org.chromium.components.content_settings.CookieControlsBridge;
import org.chromium.components.page_info.PageInfoController;

/* renamed from: Lu0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C0720Lu0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final PageInfoController f8445a;

    public C0720Lu0(PageInfoController pageInfoController) {
        this.f8445a = pageInfoController;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        PageInfoController pageInfoController = this.f8445a;
        Boolean bool = (Boolean) obj;
        Objects.requireNonNull(pageInfoController);
        pageInfoController.k(bool.booleanValue() ? 12 : 11);
        CookieControlsBridge cookieControlsBridge = pageInfoController.a0;
        boolean booleanValue = bool.booleanValue();
        long j = cookieControlsBridge.f10833a;
        if (j != 0) {
            N.MTF7msU_(j, booleanValue);
        }
    }
}
