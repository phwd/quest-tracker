package X;

import android.content.ContentResolver;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import java.util.concurrent.Executor;

/* renamed from: X.1j0  reason: invalid class name */
public final class AnonymousClass1j0 implements AnonymousClass1j5<AnonymousClass0PZ> {
    public final ContentResolver A00;
    public final AnonymousClass0JW A01;
    public final Executor A02;

    @Override // X.AnonymousClass1j8
    public final void A8d(Consumer<AnonymousClass0PZ> consumer, ProducerContext producerContext) {
        AnonymousClass1l6 r4 = producerContext.A05;
        AnonymousClass1kA r6 = producerContext.A07;
        producerContext.A06("local", "exif");
        C09541ip r1 = new C09541ip(this, consumer, r4, producerContext, r6);
        producerContext.A04(new AnonymousClass1j4(this, r1));
        this.A02.execute(r1);
    }

    public AnonymousClass1j0(Executor executor, AnonymousClass0JW r2, ContentResolver contentResolver) {
        this.A02 = executor;
        this.A01 = r2;
        this.A00 = contentResolver;
    }
}
