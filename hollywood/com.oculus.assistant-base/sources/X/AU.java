package X;

import android.os.Process;

public final class AU implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.executors.NameAndPriorityThreadFactory$PrioritySetter";
    public final int A00;
    public final Runnable A01;

    public final void run() {
        Process.setThreadPriority(this.A00);
        this.A01.run();
    }

    public AU(Runnable runnable, int i) {
        this.A01 = runnable;
        this.A00 = i;
    }
}
