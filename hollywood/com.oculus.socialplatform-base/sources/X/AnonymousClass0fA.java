package X;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableEntry;
import java.util.Iterator;
import java.util.Map;

/* renamed from: X.0fA  reason: invalid class name */
public class AnonymousClass0fA extends AnonymousClass0wW<K, Map.Entry<K, V>> {
    public final /* synthetic */ Function A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0fA(Iterator it, Function function) {
        super(it);
        this.A00 = function;
    }

    @Override // X.AnonymousClass0wW
    public final Object A00(Object obj) {
        return new ImmutableEntry(obj, this.A00.apply(obj));
    }
}
