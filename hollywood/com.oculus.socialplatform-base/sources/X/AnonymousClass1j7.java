package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;

/* renamed from: X.1j7  reason: invalid class name */
public final class AnonymousClass1j7 implements AnonymousClass1j8<AnonymousClass0PZ> {
    public final AnonymousClass1j5<AnonymousClass0PZ>[] A00;

    @Override // X.AnonymousClass1j8
    public final void A8d(Consumer<AnonymousClass0PZ> consumer, ProducerContext producerContext) {
        consumer.A07(null, 1);
    }

    public AnonymousClass1j7(AnonymousClass1j5<AnonymousClass0PZ>... r5) {
        this.A00 = r5;
        int length = r5.length;
        if (0 >= length) {
            throw new IndexOutOfBoundsException(C00740Ii.A00("%s (%s) must be less than size (%s)", "index", 0, Integer.valueOf(length)));
        }
    }
}
