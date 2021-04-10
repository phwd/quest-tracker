package X;

import android.os.Process;

public final class EY implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.http.executors.liger.nodi.HTTPThreadFactory$ThreadPriorityRunnable";
    public int A00 = 10;
    public final Runnable A01;

    public EY(Runnable runnable) {
        this.A01 = runnable;
    }

    public final void run() {
        Process.setThreadPriority(this.A00);
        this.A01.run();
    }
}
