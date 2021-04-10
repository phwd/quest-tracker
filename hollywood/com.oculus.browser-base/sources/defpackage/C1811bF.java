package defpackage;

import org.chromium.content_public.browser.RenderFrameHost;

/* renamed from: bF  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1811bF implements AbstractC3655m30 {

    /* renamed from: a  reason: collision with root package name */
    public final RenderFrameHost f9523a;

    public C1811bF(RenderFrameHost renderFrameHost) {
        this.f9523a = renderFrameHost;
    }

    @Override // defpackage.AbstractC3655m30
    public AbstractC3313k30 a() {
        return new C2153dF(this.f9523a);
    }
}
