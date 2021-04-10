package X;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public final class TD<K, V> extends AbstractC0131Ob<Map<K, V>> {
    public final AbstractC0131Ob<K> A00;
    public final AbstractC0131Ob<V> A01;
    public final VE<? extends Map<K, V>> A02;
    public final /* synthetic */ TB A03;

    public TD(TB tb, HY hy, Type type, AbstractC0131Ob<K> ob, Type type2, AbstractC0131Ob<V> ob2, VE<? extends Map<K, V>> ve) {
        this.A03 = tb;
        this.A00 = new C0162Sx(hy, ob, type);
        this.A01 = new C0162Sx(hy, ob2, type2);
        this.A02 = ve;
    }

    @Override // X.AbstractC0131Ob
    public final void A03(mm mmVar, Object obj) throws IOException {
        Map map = (Map) obj;
        if (map == null) {
            mmVar.A0B();
            return;
        }
        mmVar.A08();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            mmVar.A0F(String.valueOf(entry.getKey()));
            this.A01.A03(mmVar, entry.getValue());
        }
        mmVar.A0A();
    }

    @Override // X.AbstractC0131Ob
    public final Object A02(lk lkVar) throws IOException {
        K k;
        Integer A0G = lkVar.A0G();
        if (A0G == AnonymousClass07.A08) {
            lkVar.A0P();
            return null;
        }
        Map map = (Map) this.A02.A1b();
        if (A0G == AnonymousClass07.A00) {
            lkVar.A0L();
            while (lkVar.A0R()) {
                lkVar.A0L();
                k = this.A00.A02(lkVar);
                if (map.put(k, this.A01.A02(lkVar)) == null) {
                    lkVar.A0N();
                }
            }
            lkVar.A0N();
            return map;
        }
        lkVar.A0M();
        while (lkVar.A0R()) {
            AbstractC0184Uj.A00.A00(lkVar);
            k = this.A00.A02(lkVar);
            if (map.put(k, this.A01.A02(lkVar)) != null) {
            }
        }
        lkVar.A0O();
        return map;
        StringBuilder sb = new StringBuilder();
        sb.append("duplicate key: ");
        sb.append((Object) k);
        throw new U0(sb.toString());
    }
}
