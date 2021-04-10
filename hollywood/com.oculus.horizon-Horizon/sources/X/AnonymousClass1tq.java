package X;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* renamed from: X.1tq  reason: invalid class name */
public final class AnonymousClass1tq {
    public ArrayList<Object> A00 = new ArrayList<>();
    public ArrayList<String> A01 = new ArrayList<>();

    public final Map<String, Map<String, String>> A00() {
        HashMap hashMap = new HashMap();
        C10571tu r6 = new C10571tu(this, hashMap);
        ArrayList<String> arrayList = this.A01;
        int size = arrayList.size() - 1;
        if (size >= 0 && arrayList.get(size) != null) {
            arrayList.remove(size);
        }
        int size2 = this.A01.size();
        int i = 0;
        for (int i2 = 0; i2 < size2; i2++) {
            String str = this.A01.get(i2);
            if (str != null) {
                r6.A9v(str);
            } else {
                ArrayList<Object> arrayList2 = this.A00;
                int i3 = i + 1;
                String str2 = (String) arrayList2.get(i);
                i = i3 + 1;
                Object obj = arrayList2.get(i3);
                if (obj instanceof String) {
                    r6.A9p(str2, (String) obj);
                } else if (obj instanceof Integer) {
                    r6.A9n(str2, ((Number) obj).intValue());
                } else if (obj instanceof Long) {
                    r6.A9o(str2, ((Number) obj).longValue());
                } else if (obj instanceof Double) {
                    r6.A9m(str2, ((Number) obj).doubleValue());
                } else if (obj instanceof Boolean) {
                    r6.A9q(str2, ((Boolean) obj).booleanValue());
                } else if (obj instanceof String[]) {
                    r6.A9t(str2, (String[]) obj);
                } else if (obj instanceof int[]) {
                    r6.A9r(str2, (int[]) obj);
                } else if (obj instanceof long[]) {
                    r6.A9s(str2, (long[]) obj);
                }
            }
        }
        return hashMap;
    }
}
