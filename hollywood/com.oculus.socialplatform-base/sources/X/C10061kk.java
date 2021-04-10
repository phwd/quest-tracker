package X;

import android.util.Pair;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;

/* renamed from: X.1kk  reason: invalid class name and case insensitive filesystem */
public class C10061kk extends AbstractC09791jm<T, T> {
    public final /* synthetic */ C10051kj A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C10061kk(C10051kj r1, Consumer<T> consumer) {
        super(consumer);
        this.A00 = r1;
    }

    public static void A00(C10061kk r3) {
        Pair<Consumer<T>, ProducerContext> poll;
        C10051kj r1 = r3.A00;
        synchronized (r1) {
            poll = r1.A02.poll();
            if (poll == null) {
                r1.A00--;
            }
        }
        if (poll != null) {
            r1.A03.execute(new AnonymousClass1lH(r3, poll));
        }
    }
}
