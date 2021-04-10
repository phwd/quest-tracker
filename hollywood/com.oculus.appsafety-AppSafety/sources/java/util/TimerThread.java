package java.util;

/* access modifiers changed from: package-private */
/* compiled from: Timer */
public class TimerThread extends Thread {
    boolean newTasksMayBeScheduled = true;
    private TaskQueue queue;

    TimerThread(TaskQueue queue2) {
        this.queue = queue2;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        try {
            mainLoop();
            synchronized (this.queue) {
                this.newTasksMayBeScheduled = false;
                this.queue.clear();
            }
        } catch (Throwable th) {
            synchronized (this.queue) {
                this.newTasksMayBeScheduled = false;
                this.queue.clear();
                throw th;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0074, code lost:
        if (r7 == false) goto L_0x0000;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0076, code lost:
        r1.run();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void mainLoop() {
        /*
        // Method dump skipped, instructions count: 131
        */
        throw new UnsupportedOperationException("Method not decompiled: java.util.TimerThread.mainLoop():void");
    }
}
