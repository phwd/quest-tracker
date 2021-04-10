package X;

import java.util.ArrayList;

public final class Ic {
    public ArrayList A00 = new ArrayList();
    public ArrayList A01 = new ArrayList();

    public static void A00(Ic ic, String str, Object obj) {
        if (!ic.A01.isEmpty()) {
            ArrayList arrayList = ic.A00;
            arrayList.add(str);
            arrayList.add(obj);
            ic.A01.add(null);
            return;
        }
        throw new IllegalStateException("Adding entries can be only done after category is started. Call startCategory first");
    }

    public final void A01(Ib ib) {
        ArrayList arrayList = this.A01;
        int size = arrayList.size() - 1;
        if (size >= 0 && arrayList.get(size) != null) {
            arrayList.remove(size);
        }
        int size2 = this.A01.size();
        int i = 0;
        for (int i2 = 0; i2 < size2; i2++) {
            String str = (String) this.A01.get(i2);
            if (str != null) {
                ib.A5W(str);
            } else {
                ArrayList arrayList2 = this.A00;
                int i3 = i + 1;
                String str2 = (String) arrayList2.get(i);
                i = i3 + 1;
                Object obj = arrayList2.get(i3);
                if (obj instanceof String) {
                    ib.A5R(str2, (String) obj);
                } else if (obj instanceof Integer) {
                    ib.A5P(str2, ((Number) obj).intValue());
                } else if (obj instanceof Long) {
                    ib.A5Q(str2, ((Number) obj).longValue());
                } else if (obj instanceof Double) {
                    ib.A5O(str2, ((Number) obj).doubleValue());
                } else if (obj instanceof Boolean) {
                    ib.A5S(str2, ((Boolean) obj).booleanValue());
                } else if (obj instanceof String[]) {
                    ib.A5V(str2, (String[]) obj);
                } else if (obj instanceof int[]) {
                    ib.A5T(str2, (int[]) obj);
                } else if (obj instanceof long[]) {
                    ib.A5U(str2, (long[]) obj);
                }
            }
        }
    }
}
