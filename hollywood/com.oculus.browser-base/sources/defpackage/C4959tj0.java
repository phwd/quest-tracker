package defpackage;

import org.chromium.components.messages.MessageBannerView;

/* renamed from: tj0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4959tj0 implements R80 {
    public final MessageBannerView F;
    public final UH0 G;

    public C4959tj0(MessageBannerView messageBannerView, UH0 uh0) {
        this.F = messageBannerView;
        this.G = uh0;
    }

    @Override // defpackage.R80
    public void I(UH0 uh0) {
        Runnable runnable = this.F.P;
        if (runnable != null) {
            runnable.run();
        }
    }
}
