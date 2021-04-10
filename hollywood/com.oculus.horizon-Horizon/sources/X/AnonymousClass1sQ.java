package X;

import android.util.Pair;
import com.facebook.imagepipeline.producers.ProducerContext;

/* renamed from: X.1sQ  reason: invalid class name */
public class AnonymousClass1sQ implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.imagepipeline.producers.ThrottlingProducer$ThrottlerConsumer$1";
    public final /* synthetic */ Pair A00;
    public final /* synthetic */ AnonymousClass1qB A01;

    public AnonymousClass1sQ(AnonymousClass1qB r1, Pair pair) {
        this.A01 = r1;
        this.A00 = pair;
    }

    public final void run() {
        AnonymousClass1q2 r5 = this.A01.A00;
        Pair pair = this.A00;
        ProducerContext producerContext = (AnonymousClass1qU) pair.second;
        producerContext.A05.A6Z(producerContext, "ThrottlingProducer", null);
        r5.A01.A7a(new AnonymousClass1qB(r5, (AnonymousClass1qD) pair.first), producerContext);
    }
}
