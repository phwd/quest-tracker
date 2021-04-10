package defpackage;

import org.chromium.chrome.browser.offlinepages.prefetch.PrefetchedPagesNotifier;

/* renamed from: vF0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC5226vF0 implements Runnable {
    public final int F;

    public RunnableC5226vF0(int i) {
        this.F = i;
    }

    public void run() {
        PrefetchedPagesNotifier.b().c(this.F);
    }
}
