package X;

import com.google.common.collect.CompactHashMap;

/* renamed from: X.0eJ  reason: invalid class name */
public class AnonymousClass0eJ extends CompactHashMap<K, V>.Itr {
    public final /* synthetic */ CompactHashMap A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0eJ(CompactHashMap compactHashMap) {
        super(compactHashMap);
        this.A00 = compactHashMap;
    }

    public final K A00(int i) {
        return (K) this.A00.A06[i];
    }
}
