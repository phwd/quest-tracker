package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1k1  reason: invalid class name */
public class AnonymousClass1k1 {
    public int A00;
    public long A01 = 0;
    @Nullable
    public AnonymousClass1jz A02;
    public final Consumer<AnonymousClass1qQ> A03;
    public final AnonymousClass1qU A04;

    public AnonymousClass1k1(Consumer<AnonymousClass1qQ> consumer, ProducerContext producerContext) {
        this.A03 = consumer;
        this.A04 = producerContext;
    }
}
