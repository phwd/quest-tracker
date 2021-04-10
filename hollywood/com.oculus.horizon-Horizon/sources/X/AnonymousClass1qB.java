package X;

import android.util.Pair;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;

/* renamed from: X.1qB  reason: invalid class name */
public class AnonymousClass1qB extends AnonymousClass1rX<T, T> {
    public final /* synthetic */ AnonymousClass1q2 A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1qB(AnonymousClass1q2 r1, Consumer<T> consumer) {
        super(consumer);
        this.A00 = r1;
    }

    public static void A00(AnonymousClass1qB r3) {
        Pair<Consumer<T>, ProducerContext> poll;
        AnonymousClass1q2 r1 = r3.A00;
        synchronized (r1) {
            poll = r1.A02.poll();
            if (poll == null) {
                r1.A00--;
            }
        }
        if (poll != null) {
            r1.A03.execute(new AnonymousClass1sQ(r3, poll));
        }
    }
}
