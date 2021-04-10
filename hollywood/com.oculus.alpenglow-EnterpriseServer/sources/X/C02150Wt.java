package X;

import com.google.gson.internal.bind.MapTypeAdapterFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

/* renamed from: X.0Wt  reason: invalid class name and case insensitive filesystem */
public final class C02150Wt<K, V> extends AnonymousClass0Bd<Map<K, V>> {
    public final AnonymousClass0Bd<K> A00;
    public final AnonymousClass0Bd<V> A01;
    public final AbstractC01170Du<? extends Map<K, V>> A02;
    public final /* synthetic */ MapTypeAdapterFactory A03;

    public C02150Wt(MapTypeAdapterFactory mapTypeAdapterFactory, AnonymousClass08D r3, Type type, AnonymousClass0Bd<K> r5, Type type2, AnonymousClass0Bd<V> r7, AbstractC01170Du<? extends Map<K, V>> r8) {
        this.A03 = mapTypeAdapterFactory;
        this.A00 = new AnonymousClass0Wf(r3, r5, type);
        this.A01 = new AnonymousClass0Wf(r3, r7, type2);
        this.A02 = r8;
    }

    @Override // X.AnonymousClass0Bd
    public final void A03(AnonymousClass0GL r5, Object obj) throws IOException {
        Map map = (Map) obj;
        if (map == null) {
            r5.A0A();
            return;
        }
        r5.A07();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            r5.A0D(String.valueOf(entry.getKey()));
            this.A01.A03(r5, entry.getValue());
        }
        r5.A09();
    }

    @Override // X.AnonymousClass0Bd
    public final Object A02(AnonymousClass0Fo r5) throws IOException {
        K k;
        Integer A0D = r5.A0D();
        if (A0D == AnonymousClass007.A0I) {
            r5.A0L();
            return null;
        }
        Map map = (Map) this.A02.A1o();
        if (A0D == AnonymousClass007.A00) {
            r5.A0H();
            while (r5.A0N()) {
                r5.A0H();
                k = this.A00.A02(r5);
                if (map.put(k, this.A01.A02(r5)) == null) {
                    r5.A0J();
                }
            }
            r5.A0J();
            return map;
        }
        r5.A0I();
        while (r5.A0N()) {
            AnonymousClass0D7.A00.A00(r5);
            k = this.A00.A02(r5);
            if (map.put(k, this.A01.A02(r5)) != null) {
            }
        }
        r5.A0K();
        return map;
        throw new AnonymousClass0XQ("duplicate key: " + ((Object) k));
    }
}
