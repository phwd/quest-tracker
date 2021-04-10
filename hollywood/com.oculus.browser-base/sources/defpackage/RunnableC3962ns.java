package defpackage;

import java.util.Objects;
import org.chromium.chrome.browser.previews.PreviewsAndroidBridge;

/* renamed from: ns  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC3962ns implements Runnable {
    public final C4985ts F;
    public final AbstractC3467ky G;
    public final PreviewsAndroidBridge H;

    public RunnableC3962ns(C4985ts tsVar, AbstractC3467ky kyVar, PreviewsAndroidBridge previewsAndroidBridge) {
        this.F = tsVar;
        this.G = kyVar;
        this.H = previewsAndroidBridge;
    }

    public void run() {
        C4985ts tsVar = this.F;
        AbstractC3467ky kyVar = this.G;
        PreviewsAndroidBridge previewsAndroidBridge = this.H;
        Objects.requireNonNull(tsVar);
        kyVar.a(new RunnableC4815ss(tsVar, previewsAndroidBridge));
    }
}
