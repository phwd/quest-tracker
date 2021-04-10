package X;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public final class Yt implements Runnable {
    public static final String __redex_internal_original_name = "com.oculus.assistant.service.audiosources.MicDataLogger$DataQueueThread";
    public final ConcurrentLinkedQueue A00 = new ConcurrentLinkedQueue();
    public final AtomicBoolean A01 = new AtomicBoolean(false);
    public final /* synthetic */ C0450Yv A02;

    public final void run() {
        int i = 0;
        while (!Thread.interrupted() && this.A01.get()) {
            ConcurrentLinkedQueue concurrentLinkedQueue = this.A00;
            if (concurrentLinkedQueue.size() <= 0) {
                i++;
                if (i == 100) {
                    i = 0;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException unused) {
                }
            } else if (((AbstractC0449Yu) concurrentLinkedQueue.remove()).A4u()) {
                break;
            }
        }
        if (!this.A01.get() && !Thread.interrupted()) {
            while (true) {
                ConcurrentLinkedQueue concurrentLinkedQueue2 = this.A00;
                if (concurrentLinkedQueue2.size() > 0) {
                    ((AbstractC0449Yu) concurrentLinkedQueue2.remove()).A4u();
                } else {
                    return;
                }
            }
        }
    }

    public Yt(C0450Yv yv) {
        this.A02 = yv;
    }
}
