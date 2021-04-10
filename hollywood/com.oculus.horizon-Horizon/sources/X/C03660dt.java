package X;

import com.google.common.base.Function;
import java.util.Iterator;

/* renamed from: X.0dt  reason: invalid class name and case insensitive filesystem */
public class C03660dt extends AbstractC07300rr<F, T> {
    public final /* synthetic */ Function A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C03660dt(Iterator it, Function function) {
        super(it);
        this.A00 = function;
    }

    @Override // X.AbstractC07300rr
    public final T A00(F f) {
        return (T) this.A00.apply(f);
    }
}
