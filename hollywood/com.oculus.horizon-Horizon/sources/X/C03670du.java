package X;

import com.google.common.base.Function;
import java.util.Iterator;

/* renamed from: X.0du  reason: invalid class name and case insensitive filesystem */
public class C03670du extends AbstractC06670pU<T> {
    public final /* synthetic */ Function A00;
    public final /* synthetic */ Iterable A01;

    public C03670du(Iterable iterable, Function function) {
        this.A01 = iterable;
        this.A00 = function;
    }

    @Override // java.lang.Iterable
    public final Iterator<T> iterator() {
        Iterator it = this.A01.iterator();
        Function function = this.A00;
        if (function != null) {
            return new C03660dt(it, function);
        }
        throw null;
    }
}
