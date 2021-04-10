package X;

import com.google.common.collect.MapMakerInternalMap;
import java.lang.ref.ReferenceQueue;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0dY  reason: invalid class name */
public final class AnonymousClass0dY<K, V> implements AbstractC07010qm<K, V, AnonymousClass0C4<K, V>, MapMakerInternalMap.WeakKeyWeakValueSegment<K, V>> {
    public static final AnonymousClass0dY<?, ?> A00 = new AnonymousClass0dY<>();

    @Override // X.AbstractC07010qm
    public final AbstractC07000ql A1t(MapMakerInternalMap.Segment segment, AbstractC07000ql r7, @NullableDecl AbstractC07000ql r8) {
        MapMakerInternalMap.WeakKeyWeakValueSegment weakKeyWeakValueSegment = (MapMakerInternalMap.WeakKeyWeakValueSegment) segment;
        AnonymousClass0C4 r72 = (AnonymousClass0C4) r7;
        AnonymousClass0C4 r82 = (AnonymousClass0C4) r8;
        if (r72.getKey() == null || r72.getValue() == null) {
            return null;
        }
        ReferenceQueue<K> referenceQueue = weakKeyWeakValueSegment.queueForKeys;
        ReferenceQueue<V> referenceQueue2 = weakKeyWeakValueSegment.queueForValues;
        AnonymousClass0C4<K, V> r1 = new AnonymousClass0C4<>(referenceQueue, r72.getKey(), r72.A01, r82);
        r1.A00 = r72.A00.A1u(referenceQueue2, r1);
        return r1;
    }

    @Override // X.AbstractC07010qm
    public final AbstractC07000ql A5Z(MapMakerInternalMap.Segment segment, Object obj, int i, @NullableDecl AbstractC07000ql r6) {
        return new AnonymousClass0C4(((MapMakerInternalMap.WeakKeyWeakValueSegment) segment).queueForKeys, obj, i, (AnonymousClass0C4) r6);
    }

    @Override // X.AbstractC07010qm
    public final MapMakerInternalMap.Segment A5c(MapMakerInternalMap mapMakerInternalMap, int i, int i2) {
        return new MapMakerInternalMap.WeakKeyWeakValueSegment(mapMakerInternalMap, i, i2);
    }

    @Override // X.AbstractC07010qm
    public final void A8s(MapMakerInternalMap.Segment segment, AbstractC07000ql r5, Object obj) {
        AnonymousClass0C4 r52 = (AnonymousClass0C4) r5;
        ReferenceQueue<V> referenceQueue = ((MapMakerInternalMap.WeakKeyWeakValueSegment) segment).queueForValues;
        AbstractC07040qr<K, V, AnonymousClass0C4<K, V>> r1 = r52.A00;
        r52.A00 = new AnonymousClass0dV(referenceQueue, obj, r52);
        r1.clear();
    }

    @Override // X.AbstractC07010qm
    public final MapMakerInternalMap.Strength A5B() {
        return MapMakerInternalMap.Strength.WEAK;
    }

    @Override // X.AbstractC07010qm
    public final MapMakerInternalMap.Strength A9k() {
        return MapMakerInternalMap.Strength.WEAK;
    }
}
