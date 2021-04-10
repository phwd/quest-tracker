package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1rX  reason: invalid class name */
public abstract class AnonymousClass1rX<I, O> extends AnonymousClass1qD<I> {
    public final Consumer<O> A00;

    @Override // X.AnonymousClass1qD
    public void A04(float f) {
        this.A00.A05(f);
    }

    public AnonymousClass1rX(Consumer<O> consumer) {
        this.A00 = consumer;
    }
}
