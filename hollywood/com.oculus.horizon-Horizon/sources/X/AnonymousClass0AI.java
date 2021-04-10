package X;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: X.0AI  reason: invalid class name */
public class AnonymousClass0AI {
    public final Map<AnonymousClass0AJ, AnonymousClass0AN> A00;
    public final Map<AnonymousClass0AN, List<AnonymousClass0AJ>> A01 = new HashMap();

    public static void A00(List<AnonymousClass0AJ> list, AnonymousClass0AR r7, AnonymousClass0AN r8, Object obj) {
        Method method;
        Object[] objArr;
        if (list != null) {
            int size = list.size();
            while (true) {
                size--;
                if (size >= 0) {
                    AnonymousClass0AJ r4 = list.get(size);
                    try {
                        int i = r4.A00;
                        if (i == 0) {
                            method = r4.A01;
                            objArr = new Object[0];
                        } else if (i == 1) {
                            method = r4.A01;
                            objArr = new Object[]{r7};
                        } else if (i == 2) {
                            method = r4.A01;
                            objArr = new Object[]{r7, r8};
                        }
                        method.invoke(obj, objArr);
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

    public AnonymousClass0AI(Map<AnonymousClass0AJ, AnonymousClass0AN> map) {
        this.A00 = map;
        for (Map.Entry<AnonymousClass0AJ, AnonymousClass0AN> entry : map.entrySet()) {
            AnonymousClass0AN value = entry.getValue();
            List<AnonymousClass0AJ> list = this.A01.get(value);
            if (list == null) {
                list = new ArrayList<>();
                this.A01.put(value, list);
            }
            list.add(entry.getKey());
        }
    }
}
