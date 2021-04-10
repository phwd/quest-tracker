package X;

import android.util.Pair;
import com.facebook.imagepipeline.producers.ProducerContext;

/* renamed from: X.1lH  reason: invalid class name */
public class AnonymousClass1lH implements Runnable {
    public static final String __redex_internal_original_name = "com.facebook.imagepipeline.producers.ThrottlingProducer$ThrottlerConsumer$1";
    public final /* synthetic */ Pair A00;
    public final /* synthetic */ C10061kk A01;

    public AnonymousClass1lH(C10061kk r1, Pair pair) {
        this.A01 = r1;
        this.A00 = pair;
    }

    public final void run() {
        C10051kj r5 = this.A01.A00;
        Pair pair = this.A00;
        ProducerContext producerContext = (C10161kv) pair.second;
        producerContext.A05.A7c(producerContext, "ThrottlingProducer", null);
        r5.A01.A8d(new C10061kk(r5, (AbstractC10011kf) pair.first), producerContext);
    }
}
