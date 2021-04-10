package defpackage;

import org.chromium.chrome.browser.compositor.CompositorViewHolder;

/* renamed from: Ak1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Ak1 implements Runnable {
    public final CompositorViewHolder F;

    public Ak1(CompositorViewHolder compositorViewHolder) {
        this.F = compositorViewHolder;
    }

    public void run() {
        this.F.requestFocus();
    }
}
