package X;

import com.facebook.imagepipeline.producers.Consumer;
import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1jm  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC09791jm<I, O> extends AbstractC10011kf<I> {
    public final Consumer<O> A00;

    @Override // X.AbstractC10011kf
    public void A05(float f) {
        this.A00.A06(f);
    }

    public AbstractC09791jm(Consumer<O> consumer) {
        this.A00 = consumer;
    }
}
