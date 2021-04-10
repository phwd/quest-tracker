package defpackage;

import J.N;
import org.chromium.chrome.browser.webauth.AuthenticatorImpl;
import org.chromium.content_public.browser.RenderFrameHost;

/* renamed from: wb  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5444wb implements AbstractC3655m30 {

    /* renamed from: a  reason: collision with root package name */
    public final RenderFrameHost f11551a;

    public C5444wb(RenderFrameHost renderFrameHost) {
        this.f11551a = renderFrameHost;
    }

    @Override // defpackage.AbstractC3655m30
    public AbstractC3313k30 a() {
        if (N.M09VlOh_("WebAuthentication") && this.f11551a != null) {
            return new AuthenticatorImpl(this.f11551a);
        }
        return null;
    }
}
