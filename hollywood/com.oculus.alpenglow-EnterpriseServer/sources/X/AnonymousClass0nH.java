package X;

import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: X.0nH  reason: invalid class name */
public class AnonymousClass0nH {
    public final ArrayList<AnonymousClass0nI> A00 = new ArrayList<>();
    public final HashMap<String, Integer> A01 = new HashMap<>();

    public final void A00(AbstractC01680Ku r4, AnonymousClass0o3 r5) {
        ArrayList<AnonymousClass0nI> arrayList = this.A00;
        Integer valueOf = Integer.valueOf(arrayList.size());
        arrayList.add(new AnonymousClass0nI(r4, r5));
        HashMap<String, Integer> hashMap = this.A01;
        hashMap.put(r4._propName, valueOf);
        hashMap.put(r5.A0B(), valueOf);
    }
}
