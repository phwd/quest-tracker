package X;

import X.AbstractC07000ql;
import com.google.common.collect.MapMakerInternalMap;
import com.google.common.collect.MapMakerInternalMap.Segment;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0qm  reason: invalid class name and case insensitive filesystem */
public interface AbstractC07010qm<K, V, E extends AbstractC07000ql<K, V, E>, S extends MapMakerInternalMap.Segment<K, V, E, S>> {
    E A1t(S s, E e, @NullableDecl E e2);

    MapMakerInternalMap.Strength A5B();

    E A5Z(S s, K k, int i, @NullableDecl E e);

    S A5c(MapMakerInternalMap<K, V, E, S> mapMakerInternalMap, int i, int i2);

    void A8s(S s, E e, V v);

    MapMakerInternalMap.Strength A9k();
}
