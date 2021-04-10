package X;

import android.content.ContentResolver;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.concurrent.Executor;

/* renamed from: X.1j1  reason: invalid class name */
public final class AnonymousClass1j1 implements AnonymousClass1j8<AbstractC00820Ju<AnonymousClass0VM>> {
    public final ContentResolver A00;
    public final Executor A01;

    @Override // X.AnonymousClass1j8
    public final void A8d(Consumer<AbstractC00820Ju<AnonymousClass0VM>> consumer, ProducerContext producerContext) {
        AnonymousClass1l6 r4 = producerContext.A05;
        AnonymousClass1kA r8 = producerContext.A07;
        producerContext.A06("local", "video");
        C09591iv r1 = new C09591iv(this, consumer, r4, producerContext, r4, producerContext, r8);
        producerContext.A04(new AnonymousClass1j3(this, r1));
        this.A01.execute(r1);
    }

    public AnonymousClass1j1(Executor executor, ContentResolver contentResolver) {
        this.A01 = executor;
        this.A00 = contentResolver;
    }
}
