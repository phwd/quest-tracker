package X;

import android.util.Pair;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.infer.annotation.Nullsafe;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1q2  reason: invalid class name */
public final class AnonymousClass1q2<T> implements AnonymousClass1pP<T> {
    @GuardedBy("this")
    public int A00;
    public final AnonymousClass1pP<T> A01;
    @GuardedBy("this")
    public final ConcurrentLinkedQueue<Pair<Consumer<T>, ProducerContext>> A02;
    public final Executor A03;
    public final int A04 = 5;

    /* JADX WARN: Incorrect args count in method signature: (ILjava/util/concurrent/Executor;LX/1pP<TT;>;)V */
    public AnonymousClass1q2(Executor executor, AnonymousClass1pP r3) {
        if (executor != null) {
            this.A03 = executor;
            this.A01 = r3;
            this.A02 = new ConcurrentLinkedQueue<>();
            this.A00 = 0;
            return;
        }
        throw null;
    }

    @Override // X.AnonymousClass1pP
    public final void A7a(Consumer<T> consumer, ProducerContext producerContext) {
        boolean z;
        AnonymousClass1qE r4 = producerContext.A05;
        r4.A6b(producerContext, "ThrottlingProducer");
        synchronized (this) {
            int i = this.A00;
            z = true;
            if (i >= this.A04) {
                this.A02.add(Pair.create(consumer, producerContext));
            } else {
                this.A00 = i + 1;
                z = false;
            }
        }
        if (!z) {
            r4.A6Z(producerContext, "ThrottlingProducer", null);
            this.A01.A7a(new AnonymousClass1qB(this, consumer), producerContext);
        }
    }
}
