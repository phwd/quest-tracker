package X;

import com.google.common.collect.MapMakerInternalMap;
import java.util.NoSuchElementException;

/* renamed from: X.0dn  reason: invalid class name and case insensitive filesystem */
public final class C03620dn extends MapMakerInternalMap<K, V, E, S>.HashIterator {
    public final /* synthetic */ MapMakerInternalMap A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C03620dn(MapMakerInternalMap mapMakerInternalMap) {
        super(mapMakerInternalMap);
        this.A00 = mapMakerInternalMap;
    }

    public final Object next() {
        MapMakerInternalMap<K, V, E, S>.WriteThroughEntry writeThroughEntry = this.A05;
        if (writeThroughEntry != null) {
            this.A04 = writeThroughEntry;
            AbstractC06990qk.A00(this);
            return this.A04;
        }
        throw new NoSuchElementException();
    }
}
