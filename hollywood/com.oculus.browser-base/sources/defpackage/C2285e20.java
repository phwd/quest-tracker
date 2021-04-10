package defpackage;

import org.chromium.content_public.browser.RenderFrameHost;

/* renamed from: e20  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2285e20 implements AbstractC3655m30 {

    /* renamed from: a  reason: collision with root package name */
    public final C2115d20 f9826a;

    public C2285e20(RenderFrameHost renderFrameHost) {
        this.f9826a = new C2115d20(renderFrameHost);
    }

    @Override // defpackage.AbstractC3655m30
    public AbstractC3313k30 a() {
        return new C4677s20(this.f9826a, new C3627lu0(), N20.a());
    }
}
