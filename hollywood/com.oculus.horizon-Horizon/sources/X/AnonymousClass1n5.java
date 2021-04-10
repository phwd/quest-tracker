package X;

import androidx.annotation.MainThread;
import com.facebook.drawee.components.DeferredReleaser;
import java.util.ArrayList;
import java.util.Queue;

/* renamed from: X.1n5  reason: invalid class name */
public class AnonymousClass1n5 implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.drawee.components.DeferredReleaserConcurrentImpl$1";
    public final /* synthetic */ AnonymousClass1n4 A00;

    public AnonymousClass1n5(AnonymousClass1n4 r1) {
        this.A00 = r1;
    }

    @MainThread
    public final void run() {
        AnonymousClass1n4 r6 = this.A00;
        synchronized (r6.A02) {
            ArrayList<DeferredReleaser.Releasable> arrayList = r6.A01;
            r6.A01 = r6.A00;
            r6.A00 = arrayList;
        }
        int size = r6.A01.size();
        for (int i = 0; i < size; i++) {
            AnonymousClass1sA r0 = r6.A01.get(i).A00;
            AnonymousClass1qF r3 = AnonymousClass1qF.ON_RELEASE_CONTROLLER;
            Queue<AnonymousClass1qF> queue = r0.A00;
            if (queue.size() + 1 > 20) {
                queue.poll();
            }
            queue.add(r3);
        }
        r6.A01.clear();
    }
}
