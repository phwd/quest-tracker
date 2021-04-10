package defpackage;

import org.chromium.content_public.browser.RenderFrameHost;

/* renamed from: Ir  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0531Ir implements AbstractC5190v30 {
    public C0531Ir(AbstractC0470Hr hr) {
    }

    @Override // defpackage.AbstractC5190v30
    public void a(A30 a30, Object obj) {
        RenderFrameHost renderFrameHost = (RenderFrameHost) obj;
        int i = AbstractC1617aA0.x;
        a30.F.put("payments.mojom.PaymentRequest", new C5870z30(AbstractC2654gB0.f9984a, new C5665xs(renderFrameHost)));
        int i2 = AbstractC1944c20.r;
        a30.F.put("blink.mojom.InstalledAppProvider", new C5870z30(A20.f7650a, new C2285e20(renderFrameHost)));
        int i3 = AbstractC5274vb.e;
        a30.F.put("blink.mojom.Authenticator", new C5870z30(AbstractC1222Ub.f9034a, new C5444wb(renderFrameHost)));
        int i4 = AbstractC1631aF.m;
        a30.F.put("payments.mojom.DigitalGoodsFactory", new C5870z30(AbstractC3519lF.f10334a, new C1811bF(renderFrameHost)));
    }
}
