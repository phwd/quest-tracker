package X;

import android.content.ContentResolver;
import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.imagepipeline.producers.ProducerContext;
import com.facebook.internal.AnalyticsEvents;
import java.util.concurrent.Executor;

/* renamed from: X.1p2  reason: invalid class name */
public final class AnonymousClass1p2 implements AnonymousClass1pP<AnonymousClass1qa<AnonymousClass1q1>> {
    public final ContentResolver A00;
    public final Executor A01;

    @Override // X.AnonymousClass1pP
    public final void A7a(Consumer<AnonymousClass1qa<AnonymousClass1q1>> consumer, ProducerContext producerContext) {
        AnonymousClass1qE r4 = producerContext.A05;
        C09811pd r8 = producerContext.A07;
        producerContext.A06("local", AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO);
        AnonymousClass1p1 r1 = new AnonymousClass1p1(this, consumer, r4, producerContext, r4, producerContext, r8);
        producerContext.A04(new AnonymousClass1p3(this, r1));
        this.A01.execute(r1);
    }

    public AnonymousClass1p2(Executor executor, ContentResolver contentResolver) {
        this.A01 = executor;
        this.A00 = contentResolver;
    }
}
