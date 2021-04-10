package X;

import com.google.gson.internal.bind.MapTypeAdapterFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/* renamed from: X.0Wr  reason: invalid class name and case insensitive filesystem */
public final class C01690Wr<K, V> extends AnonymousClass0yl<Map<K, V>> {
    public final AnonymousClass0yl<K> A00;
    public final AnonymousClass0yl<V> A01;
    public final AbstractC09010zC<? extends Map<K, V>> A02;
    public final /* synthetic */ MapTypeAdapterFactory A03;

    public C01690Wr(MapTypeAdapterFactory mapTypeAdapterFactory, C08780ya r3, Type type, AnonymousClass0yl<K> r5, Type type2, AnonymousClass0yl<V> r7, AbstractC09010zC<? extends Map<K, V>> r8) {
        this.A03 = mapTypeAdapterFactory;
        this.A00 = new AnonymousClass0VA(r3, r5, type);
        this.A01 = new AnonymousClass0VA(r3, r7, type2);
        this.A02 = r8;
    }

    @Override // X.AnonymousClass0yl
    public final void A03(C09130zU r5, Object obj) throws IOException {
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

    @Override // X.AnonymousClass0yl
    public final Object A02(C09120zR r5) throws IOException {
        K k;
        Integer A0G = r5.A0G();
        if (A0G == AnonymousClass007.A0I) {
            r5.A0P();
            return null;
        }
        Map map = (Map) this.A02.A1q();
        if (A0G == AnonymousClass007.A00) {
            r5.A0L();
            while (r5.A0R()) {
                r5.A0L();
                k = this.A00.A02(r5);
                if (map.put(k, this.A01.A02(r5)) == null) {
                    r5.A0N();
                }
            }
            r5.A0N();
            return map;
        }
        r5.A0M();
        while (r5.A0R()) {
            AbstractC08940z0.A00.A00(r5);
            k = this.A00.A02(r5);
            if (map.put(k, this.A01.A02(r5)) != null) {
            }
        }
        r5.A0O();
        return map;
        StringBuilder sb = new StringBuilder();
        sb.append("duplicate key: ");
        sb.append((Object) k);
        throw new C03080c5(sb.toString());
    }
}
