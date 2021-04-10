package X;

import com.google.common.collect.MapMakerInternalMap;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* renamed from: X.0dg  reason: invalid class name and case insensitive filesystem */
public final class C03580dg<K, V> implements AbstractC07010qm<K, V, AnonymousClass0C7<K, V>, MapMakerInternalMap.StrongKeyStrongValueSegment<K, V>> {
    public static final C03580dg<?, ?> A00 = new C03580dg<>();

    @Override // X.AbstractC07010qm
    public final AbstractC07000ql A1t(MapMakerInternalMap.Segment segment, AbstractC07000ql r5, @NullableDecl AbstractC07000ql r6) {
        AnonymousClass0C7 r52 = (AnonymousClass0C7) r5;
        AnonymousClass0C7 r1 = new AnonymousClass0C7(r52.A02, r52.A01, (AnonymousClass0C7) r6);
        r1.A00 = r52.A00;
        return r1;
    }

    @Override // X.AbstractC07010qm
    public final AbstractC07000ql A5Z(MapMakerInternalMap.Segment segment, Object obj, int i, @NullableDecl AbstractC07000ql r5) {
        return new AnonymousClass0C7(obj, i, (AnonymousClass0C7) r5);
    }

    @Override // X.AbstractC07010qm
    public final MapMakerInternalMap.Segment A5c(MapMakerInternalMap mapMakerInternalMap, int i, int i2) {
        return new MapMakerInternalMap.StrongKeyStrongValueSegment(mapMakerInternalMap, i, i2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AbstractC07010qm
    public final void A8s(MapMakerInternalMap.Segment segment, AbstractC07000ql r2, Object obj) {
        ((AnonymousClass0C7) r2).A00 = obj;
    }

    @Override // X.AbstractC07010qm
    public final MapMakerInternalMap.Strength A5B() {
        return MapMakerInternalMap.Strength.STRONG;
    }

    @Override // X.AbstractC07010qm
    public final MapMakerInternalMap.Strength A9k() {
        return MapMakerInternalMap.Strength.STRONG;
    }
}
