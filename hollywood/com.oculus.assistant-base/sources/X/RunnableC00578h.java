package X;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/* renamed from: X.8h  reason: invalid class name and case insensitive filesystem */
public final class RunnableC00578h implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.assistant.clientplatform.input.LastAudioLogger$DataQueueThread";
    public final ConcurrentLinkedQueue A00 = new ConcurrentLinkedQueue();
    public final AtomicBoolean A01 = new AtomicBoolean(false);
    public final /* synthetic */ C00598j A02;

    public final void run() {
        int i = 0;
        while (!Thread.interrupted() && this.A01.get()) {
            ConcurrentLinkedQueue concurrentLinkedQueue = this.A00;
            if (concurrentLinkedQueue.isEmpty()) {
                i++;
                if (i == 100) {
                    i = 0;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException unused) {
                }
            } else if (((AbstractC00588i) concurrentLinkedQueue.remove()).A4u()) {
                break;
            }
        }
        if (!this.A01.get() && !Thread.interrupted()) {
            while (true) {
                ConcurrentLinkedQueue concurrentLinkedQueue2 = this.A00;
                if (!concurrentLinkedQueue2.isEmpty()) {
                    ((AbstractC00588i) concurrentLinkedQueue2.remove()).A4u();
                } else {
                    return;
                }
            }
        }
    }

    public RunnableC00578h(C00598j r3) {
        this.A02 = r3;
    }
}
