package X;

import com.google.common.base.Function;
import java.util.Iterator;

/* renamed from: X.0dv  reason: invalid class name and case insensitive filesystem */
public class C03680dv implements Function<Iterable<? extends T>, Iterator<? extends T>> {
    @Override // com.google.common.base.Function
    public final Object apply(Object obj) {
        return ((Iterable) obj).iterator();
    }
}
