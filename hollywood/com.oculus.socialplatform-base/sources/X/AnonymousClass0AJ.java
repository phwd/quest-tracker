package X;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: X.0AJ  reason: invalid class name */
public class AnonymousClass0AJ {
    public final Map<AnonymousClass0AK, AnonymousClass0AO> A00;
    public final Map<AnonymousClass0AO, List<AnonymousClass0AK>> A01 = new HashMap();

    public static void A00(List<AnonymousClass0AK> list, AnonymousClass0AS r5, AnonymousClass0AO r6, Object obj) {
        if (list != null) {
            int size = list.size();
            while (true) {
                size--;
                if (size >= 0) {
                    AnonymousClass0AK r2 = list.get(size);
                    try {
                        int i = r2.A00;
                        if (i == 0) {
                            r2.A01.invoke(obj, new Object[0]);
                        } else if (i == 1) {
                            r2.A01.invoke(obj, r5);
                        } else if (i == 2) {
                            r2.A01.invoke(obj, r5, r6);
                        }
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException("Failed to call observer method", e.getCause());
                    } catch (IllegalAccessException e2) {
                        throw new RuntimeException(e2);
                    }
                } else {
                    return;
                }
            }
        }
    }

    public AnonymousClass0AJ(Map<AnonymousClass0AK, AnonymousClass0AO> map) {
        this.A00 = map;
        for (Map.Entry<AnonymousClass0AK, AnonymousClass0AO> entry : map.entrySet()) {
            AnonymousClass0AO value = entry.getValue();
            List<AnonymousClass0AK> list = this.A01.get(value);
            if (list == null) {
                list = new ArrayList<>();
                this.A01.put(value, list);
            }
            list.add(entry.getKey());
        }
    }
}
