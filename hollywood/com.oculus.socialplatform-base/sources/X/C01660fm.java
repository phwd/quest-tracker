package X;

import com.google.common.collect.CompactHashMap;

/* renamed from: X.0fm  reason: invalid class name and case insensitive filesystem */
public class C01660fm extends CompactHashMap<K, V>.Itr {
    public final /* synthetic */ CompactHashMap A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C01660fm(CompactHashMap compactHashMap) {
        super(compactHashMap);
        this.A00 = compactHashMap;
    }

    public final K A00(int i) {
        return (K) this.A00.A07[i];
    }
}
