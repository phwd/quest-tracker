package X;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableBiMap;
import java.util.Map;

public final class u7 extends ImmutableMap.Builder {
    public u7() {
        super(4);
    }

    @Override // com.google.common.collect.ImmutableMap.Builder
    public final ImmutableMap build() {
        int i = this.A00;
        if (i == 0) {
            return RegularImmutableBiMap.A05;
        }
        return new RegularImmutableBiMap(this.A01, i);
    }

    @Override // com.google.common.collect.ImmutableMap.Builder
    public final ImmutableMap.Builder A00(Iterable iterable) {
        super.A00(iterable);
        return this;
    }

    @Override // com.google.common.collect.ImmutableMap.Builder
    public final ImmutableMap.Builder put(Object obj, Object obj2) {
        super.put(obj, obj2);
        return this;
    }

    @Override // com.google.common.collect.ImmutableMap.Builder
    public final ImmutableMap.Builder put(Map.Entry entry) {
        super.put(entry);
        return this;
    }
}
