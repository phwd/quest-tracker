package X;

import com.google.common.collect.CompactHashMap;

/* renamed from: X.0eI  reason: invalid class name */
public class AnonymousClass0eI extends CompactHashMap<K, V>.Itr {
    public final /* synthetic */ CompactHashMap A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0eI(CompactHashMap compactHashMap) {
        super(compactHashMap);
        this.A00 = compactHashMap;
    }

    public final Object A00(int i) {
        return new AnonymousClass0eG(this.A00, i);
    }
}
