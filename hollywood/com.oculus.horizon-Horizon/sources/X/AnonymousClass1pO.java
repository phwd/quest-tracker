package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;

/* renamed from: X.1pO  reason: invalid class name */
public final class AnonymousClass1pO implements AnonymousClass1pP<AnonymousClass1qQ> {
    public final AnonymousClass1p8<AnonymousClass1qQ>[] A00;

    @Override // X.AnonymousClass1pP
    public final void A7a(Consumer<AnonymousClass1qQ> consumer, ProducerContext producerContext) {
        consumer.A06(null, 1);
    }

    public AnonymousClass1pO(AnonymousClass1p8<AnonymousClass1qQ>... r8) {
        this.A00 = r8;
        int length = r8.length;
        if (0 >= length) {
            throw new IndexOutOfBoundsException(AnonymousClass0KU.A00("%s (%s) must be less than size (%s)", "index", 0, Integer.valueOf(length)));
        }
    }
}
