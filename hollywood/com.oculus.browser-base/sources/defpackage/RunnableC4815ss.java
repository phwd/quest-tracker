package defpackage;

import J.N;
import org.chromium.chrome.browser.previews.PreviewsAndroidBridge;

/* renamed from: ss  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC4815ss implements Runnable {
    public final C4985ts F;
    public final PreviewsAndroidBridge G;

    public RunnableC4815ss(C4985ts tsVar, PreviewsAndroidBridge previewsAndroidBridge) {
        this.F = tsVar;
        this.G = previewsAndroidBridge;
    }

    public void run() {
        C4985ts tsVar = this.F;
        PreviewsAndroidBridge previewsAndroidBridge = this.G;
        AbstractC5736yF0.a(previewsAndroidBridge.b(tsVar.i), 0);
        N.MUNdHFuu(previewsAndroidBridge.b, previewsAndroidBridge, tsVar.i);
    }
}
