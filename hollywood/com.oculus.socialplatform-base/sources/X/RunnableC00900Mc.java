package X;

import androidx.annotation.MainThread;
import java.util.ArrayList;

/* renamed from: X.0Mc  reason: invalid class name and case insensitive filesystem */
public class RunnableC00900Mc implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.drawee.components.DeferredReleaserConcurrentImpl$1";
    public final /* synthetic */ C03710oL A00;

    public RunnableC00900Mc(C03710oL r1) {
        this.A00 = r1;
    }

    @MainThread
    public final void run() {
        C03710oL r3 = this.A00;
        synchronized (r3.A02) {
            ArrayList<AbstractC00880Ma> arrayList = r3.A01;
            r3.A01 = r3.A00;
            r3.A00 = arrayList;
        }
        int size = r3.A01.size();
        for (int i = 0; i < size; i++) {
            r3.A01.get(i).A8x();
        }
        r3.A01.clear();
    }
}
