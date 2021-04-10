package X;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractBiMap;
import java.util.Map;

/* renamed from: X.0Ni  reason: invalid class name */
public class AnonymousClass0Ni extends AbstractC01620fd<K, V> {
    public final Map.Entry<K, V> A00;
    public final /* synthetic */ AbstractBiMap A01;

    public AnonymousClass0Ni(AbstractBiMap abstractBiMap, Map.Entry<K, V> entry) {
        this.A01 = abstractBiMap;
        this.A00 = entry;
    }

    @Override // java.util.Map.Entry, X.AbstractC01620fd
    public final V setValue(V v) {
        AbstractBiMap abstractBiMap = this.A01;
        Preconditions.checkState(abstractBiMap.entrySet().contains(this), "entry no longer in map");
        if (Objects.equal(v, getValue())) {
            return v;
        }
        Preconditions.checkArgument(!abstractBiMap.containsValue(v), "value already present: %s", v);
        V value = this.A00.setValue(v);
        Preconditions.checkState(Objects.equal(v, abstractBiMap.get(getKey())), "entry no longer in map");
        Object key = getKey();
        abstractBiMap.A00.A01.remove(value);
        abstractBiMap.A00.A01.put(v, key);
        return value;
    }

    @Override // X.AbstractC01620fd
    /* renamed from: A01 */
    public final Map.Entry<K, V> A00() {
        return this.A00;
    }
}
