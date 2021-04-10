package defpackage;

import org.chromium.components.messages.MessageContainer;

/* renamed from: SW0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class SW0 implements Runnable {
    public final WW0 F;
    public final Runnable G;

    public SW0(WW0 ww0, Runnable runnable) {
        this.F = ww0;
        this.G = runnable;
    }

    public void run() {
        WW0 ww0 = this.F;
        Runnable runnable = this.G;
        MessageContainer messageContainer = ww0.c;
        if (messageContainer.indexOfChild(ww0.b) >= 0) {
            AbstractC4656rv1.f(messageContainer, true);
            messageContainer.removeAllViews();
            if (runnable != null) {
                runnable.run();
                return;
            }
            return;
        }
        throw new IllegalStateException("The given view is not being shown.");
    }
}
