package X;

import com.google.common.collect.MapMakerInternalMap;
import java.lang.ref.ReferenceQueue;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0de  reason: invalid class name */
public final class AnonymousClass0de<K, V> implements AbstractC07010qm<K, V, AnonymousClass0C6<K, V>, MapMakerInternalMap.StrongKeyWeakValueSegment<K, V>> {
    public static final AnonymousClass0de<?, ?> A00 = new AnonymousClass0de<>();

    @Override // X.AbstractC07010qm
    public final AbstractC07000ql A1t(MapMakerInternalMap.Segment segment, AbstractC07000ql r6, @NullableDecl AbstractC07000ql r7) {
        MapMakerInternalMap.StrongKeyWeakValueSegment strongKeyWeakValueSegment = (MapMakerInternalMap.StrongKeyWeakValueSegment) segment;
        AnonymousClass0C6 r62 = (AnonymousClass0C6) r6;
        AnonymousClass0C6 r72 = (AnonymousClass0C6) r7;
        if (r62.getValue() == null) {
            return null;
        }
        ReferenceQueue<V> referenceQueue = strongKeyWeakValueSegment.queueForValues;
        AnonymousClass0C6<K, V> r1 = new AnonymousClass0C6<>(r62.A02, r62.A01, r72);
        r1.A00 = r62.A00.A1u(referenceQueue, r1);
        return r1;
    }

    @Override // X.AbstractC07010qm
    public final AbstractC07000ql A5Z(MapMakerInternalMap.Segment segment, Object obj, int i, @NullableDecl AbstractC07000ql r5) {
        return new AnonymousClass0C6(obj, i, (AnonymousClass0C6) r5);
    }

    @Override // X.AbstractC07010qm
    public final MapMakerInternalMap.Segment A5c(MapMakerInternalMap mapMakerInternalMap, int i, int i2) {
        return new MapMakerInternalMap.StrongKeyWeakValueSegment(mapMakerInternalMap, i, i2);
    }

    @Override // X.AbstractC07010qm
    public final void A8s(MapMakerInternalMap.Segment segment, AbstractC07000ql r5, Object obj) {
        AnonymousClass0C6 r52 = (AnonymousClass0C6) r5;
        ReferenceQueue<V> referenceQueue = ((MapMakerInternalMap.StrongKeyWeakValueSegment) segment).queueForValues;
        AbstractC07040qr<K, V, AnonymousClass0C6<K, V>> r1 = r52.A00;
        r52.A00 = new AnonymousClass0dV(referenceQueue, obj, r52);
        r1.clear();
    }

    @Override // X.AbstractC07010qm
    public final MapMakerInternalMap.Strength A5B() {
        return MapMakerInternalMap.Strength.STRONG;
    }

    @Override // X.AbstractC07010qm
    public final MapMakerInternalMap.Strength A9k() {
        return MapMakerInternalMap.Strength.WEAK;
    }
}
