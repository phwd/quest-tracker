package X;

import com.google.common.collect.CompactHashMap;

/* renamed from: X.0fk  reason: invalid class name */
public class AnonymousClass0fk extends CompactHashMap<K, V>.Itr {
    public final /* synthetic */ CompactHashMap A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0fk(CompactHashMap compactHashMap) {
        super(compactHashMap);
        this.A00 = compactHashMap;
    }

    public final V A00(int i) {
        return (V) this.A00.A00[i];
    }
}
