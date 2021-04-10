package X;

import com.google.common.annotations.Beta;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableBiMap;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Map;

/* renamed from: X.0fU  reason: invalid class name */
public final class AnonymousClass0fU<K, V> extends ImmutableMap.Builder<K, V> {
    @Override // com.google.common.collect.ImmutableMap.Builder
    public final ImmutableMap build() {
        int i = this.A00;
        if (i == 0) {
            return RegularImmutableBiMap.A05;
        }
        return new RegularImmutableBiMap(this.A01, i);
    }

    @Override // com.google.common.collect.ImmutableMap.Builder
    @CanIgnoreReturnValue
    @Beta
    public final ImmutableMap.Builder A00(Iterable iterable) {
        super.A00(iterable);
        return this;
    }

    @Override // com.google.common.collect.ImmutableMap.Builder
    @CanIgnoreReturnValue
    public final ImmutableMap.Builder A01(Map map) {
        super.A01(map);
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object */
    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.common.collect.ImmutableMap.Builder
    @CanIgnoreReturnValue
    public final ImmutableMap.Builder put(Object obj, Object obj2) {
        super.put(obj, obj2);
        return this;
    }

    @Override // com.google.common.collect.ImmutableMap.Builder
    @CanIgnoreReturnValue
    public final ImmutableMap.Builder put(Map.Entry entry) {
        super.put(entry);
        return this;
    }
}
