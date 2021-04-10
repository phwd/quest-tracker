package defpackage;

import org.chromium.chrome.browser.password_manager.PasswordGenerationPopupBridge;

/* renamed from: ey0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2443ey0 implements Runnable {
    public final PasswordGenerationPopupBridge F;

    public RunnableC2443ey0(PasswordGenerationPopupBridge passwordGenerationPopupBridge) {
        this.F = passwordGenerationPopupBridge;
    }

    public void run() {
        this.F.onDismiss();
    }
}
