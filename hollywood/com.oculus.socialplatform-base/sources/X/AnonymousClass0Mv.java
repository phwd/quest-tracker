package X;

import com.google.common.collect.Lists$TransformingSequentialList;
import java.util.ListIterator;

/* renamed from: X.0Mv  reason: invalid class name */
public class AnonymousClass0Mv extends AbstractC01550es<F, T> {
    public final /* synthetic */ Lists$TransformingSequentialList A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0Mv(Lists$TransformingSequentialList lists$TransformingSequentialList, ListIterator listIterator) {
        super(listIterator);
        this.A00 = lists$TransformingSequentialList;
    }

    @Override // X.AnonymousClass0wW
    public final T A00(F f) {
        return (T) this.A00.function.apply(f);
    }
}
