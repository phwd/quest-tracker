package X;

import com.google.common.collect.MapMakerInternalMap;
import java.util.NoSuchElementException;

/* renamed from: X.0db  reason: invalid class name and case insensitive filesystem */
public final class C03560db extends MapMakerInternalMap<K, V, E, S>.HashIterator {
    public final /* synthetic */ MapMakerInternalMap A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C03560db(MapMakerInternalMap mapMakerInternalMap) {
        super(mapMakerInternalMap);
        this.A00 = mapMakerInternalMap;
    }

    public final V next() {
        MapMakerInternalMap<K, V, E, S>.WriteThroughEntry writeThroughEntry = this.A05;
        if (writeThroughEntry != null) {
            this.A04 = writeThroughEntry;
            AbstractC06990qk.A00(this);
            return (V) this.A04.getValue();
        }
        throw new NoSuchElementException();
    }
}
