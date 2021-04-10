package X;

import com.google.common.base.Objects;
import com.google.common.collect.CompactHashMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

public final class NF extends QL<K, V> {
    public int A00;
    @NullableDecl
    public final K A01;
    public final /* synthetic */ CompactHashMap A02;

    public NF(CompactHashMap compactHashMap, int i) {
        this.A02 = compactHashMap;
        this.A01 = (K) compactHashMap.A06[i];
        this.A00 = i;
    }

    public static void A00(NF nf) {
        int i = nf.A00;
        if (i != -1) {
            CompactHashMap compactHashMap = nf.A02;
            if (i < compactHashMap.size() && Objects.equal(nf.A01, compactHashMap.A06[i])) {
                return;
            }
        }
        nf.A00 = CompactHashMap.A00(nf.A02, nf.A01);
    }
}
