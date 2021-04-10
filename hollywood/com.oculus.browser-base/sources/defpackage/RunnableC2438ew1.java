package defpackage;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.chrome.browser.app.ChromeActivity;

/* renamed from: ew1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2438ew1 implements Runnable {
    public final C2609fw1 F;
    public final AtomicBoolean G;
    public final ChromeActivity H;

    public RunnableC2438ew1(C2609fw1 fw1, AtomicBoolean atomicBoolean, ChromeActivity chromeActivity) {
        this.F = fw1;
        this.G = atomicBoolean;
        this.H = chromeActivity;
    }

    public void run() {
        C2609fw1 fw1 = this.F;
        AtomicBoolean atomicBoolean = this.G;
        ChromeActivity chromeActivity = this.H;
        Objects.requireNonNull(fw1);
        if (!atomicBoolean.getAndSet(true)) {
            fw1.c(chromeActivity);
        }
    }
}
