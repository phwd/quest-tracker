package X;

import com.google.gson.internal.bind.MapTypeAdapterFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/* renamed from: X.0du  reason: invalid class name and case insensitive filesystem */
public final class C01400du<K, V> extends AnonymousClass13Y<Map<K, V>> {
    public final AnonymousClass13Y<K> A00;
    public final AnonymousClass13Y<V> A01;
    public final AnonymousClass143<? extends Map<K, V>> A02;
    public final /* synthetic */ MapTypeAdapterFactory A03;

    public C01400du(MapTypeAdapterFactory mapTypeAdapterFactory, AnonymousClass13N r3, Type type, AnonymousClass13Y<K> r5, Type type2, AnonymousClass13Y<V> r7, AnonymousClass143<? extends Map<K, V>> r8) {
        this.A03 = mapTypeAdapterFactory;
        this.A00 = new AnonymousClass0dg(r3, r5, type);
        this.A01 = new AnonymousClass0dg(r3, r7, type2);
        this.A02 = r8;
    }

    @Override // X.AnonymousClass13Y
    public final void A03(AnonymousClass14L r5, Object obj) throws IOException {
        Map map = (Map) obj;
        if (map == null) {
            r5.A09();
            return;
        }
        r5.A06();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            r5.A0C(String.valueOf(entry.getKey()));
            this.A01.A03(r5, entry.getValue());
        }
        r5.A08();
    }

    @Override // X.AnonymousClass13Y
    public final Object A02(AnonymousClass14I r5) throws IOException {
        Integer A0G = r5.A0G();
        if (A0G == AnonymousClass007.A09) {
            r5.A0P();
            return null;
        }
        Map map = (Map) this.A02.A2F();
        if (A0G == AnonymousClass007.A00) {
            r5.A0L();
            while (r5.A0R()) {
                r5.A0L();
                K A022 = this.A00.A02(r5);
                if (map.put(A022, this.A01.A02(r5)) == null) {
                    r5.A0N();
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("duplicate key: ");
                    sb.append((Object) A022);
                    throw new AnonymousClass0eR(sb.toString());
                }
            }
            r5.A0N();
            return map;
        }
        r5.A0M();
        while (r5.A0R()) {
            AbstractC057613n.A00.A00(r5);
            K A023 = this.A00.A02(r5);
            if (map.put(A023, this.A01.A02(r5)) != null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("duplicate key: ");
                sb2.append((Object) A023);
                throw new AnonymousClass0eR(sb2.toString());
            }
        }
        r5.A0O();
        return map;
    }
}
