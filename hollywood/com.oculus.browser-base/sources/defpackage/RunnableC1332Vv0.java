package defpackage;

import org.chromium.chrome.browser.paint_preview.services.PaintPreviewTabService;

/* renamed from: Vv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1332Vv0 implements Runnable {
    public final PaintPreviewTabService F;

    public RunnableC1332Vv0(PaintPreviewTabService paintPreviewTabService) {
        this.F = paintPreviewTabService;
    }

    public void run() {
        PaintPreviewTabService paintPreviewTabService = this.F;
        paintPreviewTabService.f10728a.run();
        paintPreviewTabService.f10728a = null;
    }
}
