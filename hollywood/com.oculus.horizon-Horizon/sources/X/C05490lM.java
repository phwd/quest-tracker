package X;

import java.util.ArrayList;
import java.util.HashMap;

/* renamed from: X.0lM  reason: invalid class name and case insensitive filesystem */
public class C05490lM {
    public final ArrayList<C05500lN> A00 = new ArrayList<>();
    public final HashMap<String, Integer> A01 = new HashMap<>();

    public final void A00(AnonymousClass0HD r4, AnonymousClass0m9 r5) {
        ArrayList<C05500lN> arrayList = this.A00;
        Integer valueOf = Integer.valueOf(arrayList.size());
        arrayList.add(new C05500lN(r4, r5));
        HashMap<String, Integer> hashMap = this.A01;
        hashMap.put(r4._propName, valueOf);
        hashMap.put(r5.A0B(), valueOf);
    }
}
