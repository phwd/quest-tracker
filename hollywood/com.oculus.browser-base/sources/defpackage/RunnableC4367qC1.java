package defpackage;

import android.os.Process;

/* renamed from: qC1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RunnableC4367qC1 implements Runnable {
    public final Runnable F;

    public RunnableC4367qC1(Runnable runnable, int i) {
        this.F = runnable;
    }

    public final void run() {
        Process.setThreadPriority(0);
        this.F.run();
    }
}
