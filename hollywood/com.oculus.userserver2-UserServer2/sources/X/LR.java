package X;

import java.lang.reflect.Type;
import java.util.Map;

public final class LR<K, V> extends hh<Map<K, V>> {
    public final hh<K> A00;
    public final hh<V> A01;
    public final hL<? extends Map<K, V>> A02;
    public final /* synthetic */ LQ A03;

    public LR(LQ lq, C0246hr hrVar, Type type, hh<K> hhVar, Type type2, hh<V> hhVar2, hL<? extends Map<K, V>> hLVar) {
        this.A03 = lq;
        this.A00 = new LD(hrVar, hhVar, type);
        this.A01 = new LD(hrVar, hhVar2, type2);
        this.A02 = hLVar;
    }
}
