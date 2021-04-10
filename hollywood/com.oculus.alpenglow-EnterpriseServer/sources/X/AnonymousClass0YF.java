package X;

import com.google.common.collect.CompactHashMap;

/* renamed from: X.0YF  reason: invalid class name */
public class AnonymousClass0YF extends CompactHashMap<K, V>.Itr {
    public final /* synthetic */ CompactHashMap A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0YF(CompactHashMap compactHashMap) {
        super(compactHashMap);
        this.A00 = compactHashMap;
    }

    public final V A00(int i) {
        return (V) this.A00.A07[i];
    }
}
