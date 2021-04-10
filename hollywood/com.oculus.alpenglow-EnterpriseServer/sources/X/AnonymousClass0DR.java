package X;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: X.0DR  reason: invalid class name */
public class AnonymousClass0DR {
    public final Map<AnonymousClass0DS, AnonymousClass0DW> A00;
    public final Map<AnonymousClass0DW, List<AnonymousClass0DS>> A01 = new HashMap();

    public static void A00(List<AnonymousClass0DS> list, AbstractC01030Da r5, AnonymousClass0DW r6, Object obj) {
        if (list != null) {
            int size = list.size();
            while (true) {
                size--;
                if (size >= 0) {
                    AnonymousClass0DS r2 = list.get(size);
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

    public AnonymousClass0DR(Map<AnonymousClass0DS, AnonymousClass0DW> map) {
        this.A00 = map;
        for (Map.Entry<AnonymousClass0DS, AnonymousClass0DW> entry : map.entrySet()) {
            AnonymousClass0DW value = entry.getValue();
            List<AnonymousClass0DS> list = this.A01.get(value);
            if (list == null) {
                list = new ArrayList<>();
                this.A01.put(value, list);
            }
            list.add(entry.getKey());
        }
    }
}
