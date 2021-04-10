package X;

import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: X.0pq  reason: invalid class name and case insensitive filesystem */
public class C04350pq {
    public final ArrayList<C04360pr> A00 = new ArrayList<>();
    public final HashMap<String, Integer> A01 = new HashMap<>();

    public final void A00(AbstractC01170Rz r4, AbstractC04520qa r5) {
        ArrayList<C04360pr> arrayList = this.A00;
        Integer valueOf = Integer.valueOf(arrayList.size());
        arrayList.add(new C04360pr(r4, r5));
        HashMap<String, Integer> hashMap = this.A01;
        hashMap.put(r4._propName, valueOf);
        hashMap.put(r5.A0B(), valueOf);
    }
}
