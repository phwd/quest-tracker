package X;

import com.google.common.collect.MapMakerInternalMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0da  reason: invalid class name and case insensitive filesystem */
public final class C03550da<K, V> implements AbstractC07010qm<K, V, AnonymousClass0C5<K, V>, MapMakerInternalMap.WeakKeyStrongValueSegment<K, V>> {
    public static final C03550da<?, ?> A00 = new C03550da<>();

    @Override // X.AbstractC07010qm
    public final AbstractC07000ql A1t(MapMakerInternalMap.Segment segment, AbstractC07000ql r6, @NullableDecl AbstractC07000ql r7) {
        MapMakerInternalMap.WeakKeyStrongValueSegment weakKeyStrongValueSegment = (MapMakerInternalMap.WeakKeyStrongValueSegment) segment;
        AnonymousClass0C5 r62 = (AnonymousClass0C5) r6;
        AnonymousClass0C5 r72 = (AnonymousClass0C5) r7;
        if (r62.getKey() == null) {
            return null;
        }
        AnonymousClass0C5 r1 = new AnonymousClass0C5(weakKeyStrongValueSegment.queueForKeys, r62.getKey(), r62.A01, r72);
        r1.A00 = r62.A00;
        return r1;
    }

    @Override // X.AbstractC07010qm
    public final AbstractC07000ql A5Z(MapMakerInternalMap.Segment segment, Object obj, int i, @NullableDecl AbstractC07000ql r6) {
        return new AnonymousClass0C5(((MapMakerInternalMap.WeakKeyStrongValueSegment) segment).queueForKeys, obj, i, (AnonymousClass0C5) r6);
    }

    @Override // X.AbstractC07010qm
    public final MapMakerInternalMap.Segment A5c(MapMakerInternalMap mapMakerInternalMap, int i, int i2) {
        return new MapMakerInternalMap.WeakKeyStrongValueSegment(mapMakerInternalMap, i, i2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AbstractC07010qm
    public final void A8s(MapMakerInternalMap.Segment segment, AbstractC07000ql r2, Object obj) {
        ((AnonymousClass0C5) r2).A00 = obj;
    }

    @Override // X.AbstractC07010qm
    public final MapMakerInternalMap.Strength A5B() {
        return MapMakerInternalMap.Strength.WEAK;
    }

    @Override // X.AbstractC07010qm
    public final MapMakerInternalMap.Strength A9k() {
        return MapMakerInternalMap.Strength.STRONG;
    }
}
