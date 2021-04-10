package X;

import com.google.common.collect.Lists$TransformingRandomAccessList;
import java.util.ListIterator;

/* renamed from: X.0Mw  reason: invalid class name */
public class AnonymousClass0Mw extends AbstractC01550es<F, T> {
    public final /* synthetic */ Lists$TransformingRandomAccessList A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0Mw(Lists$TransformingRandomAccessList lists$TransformingRandomAccessList, ListIterator listIterator) {
        super(listIterator);
        this.A00 = lists$TransformingRandomAccessList;
    }

    @Override // X.AnonymousClass0wW
    public final T A00(F f) {
        return (T) this.A00.function.apply(f);
    }
}
