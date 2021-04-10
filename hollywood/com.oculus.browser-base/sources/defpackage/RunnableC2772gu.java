package defpackage;

import android.os.Process;

/* renamed from: gu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2772gu implements Runnable {
    public final Runnable F;

    public RunnableC2772gu(Runnable runnable) {
        this.F = runnable;
    }

    public void run() {
        Runnable runnable = this.F;
        Process.setThreadPriority(10);
        runnable.run();
    }
}
