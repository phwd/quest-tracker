package X;

import com.google.common.base.Objects;
import com.google.common.collect.CompactHashMap;

public final class u3 extends AbstractC0360Tk {
    public int A00;
    public final Object A01;
    public final /* synthetic */ CompactHashMap A02;

    public u3(CompactHashMap compactHashMap, int i) {
        this.A02 = compactHashMap;
        this.A01 = compactHashMap.A06[i];
        this.A00 = i;
    }

    public static void A00(u3 u3Var) {
        int i = u3Var.A00;
        if (i != -1) {
            CompactHashMap compactHashMap = u3Var.A02;
            if (i < compactHashMap.size() && Objects.equal(u3Var.A01, compactHashMap.A06[i])) {
                return;
            }
        }
        u3Var.A00 = CompactHashMap.A00(u3Var.A02, u3Var.A01);
    }
}
