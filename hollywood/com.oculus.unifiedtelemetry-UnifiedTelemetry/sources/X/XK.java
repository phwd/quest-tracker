package X;

import java.util.ArrayList;
import java.util.HashMap;

public class XK {
    public final ArrayList<XH> A00 = new ArrayList<>();
    public final HashMap<String, Integer> A01 = new HashMap<>();

    public final void A00(AbstractC0073Cr cr, V4 v4) {
        ArrayList<XH> arrayList = this.A00;
        Integer valueOf = Integer.valueOf(arrayList.size());
        arrayList.add(new XH(cr, v4));
        HashMap<String, Integer> hashMap = this.A01;
        hashMap.put(cr._propName, valueOf);
        hashMap.put(v4.A0B(), valueOf);
    }
}
