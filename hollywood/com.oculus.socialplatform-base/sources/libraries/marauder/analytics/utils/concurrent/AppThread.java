package libraries.marauder.analytics.utils.concurrent;

import android.os.Process;

public class AppThread extends Thread {
    public final int mThreadPriority;

    public void run() {
        Process.setThreadPriority(this.mThreadPriority);
        super.run();
    }

    public AppThread(Runnable runnable, String str, int i) {
        super(runnable, str);
        this.mThreadPriority = i;
    }
}
