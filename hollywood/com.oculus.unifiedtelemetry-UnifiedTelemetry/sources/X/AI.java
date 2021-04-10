package X;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AI {
    public final Map<AJ, AN> A00;
    public final Map<AN, List<AJ>> A01 = new HashMap();

    public static void A00(List<AJ> list, AR ar, AN an, Object obj) {
        if (list != null) {
            int size = list.size();
            while (true) {
                size--;
                if (size >= 0) {
                    AJ aj = list.get(size);
                    try {
                        int i = aj.A00;
                        if (i == 0) {
                            aj.A01.invoke(obj, new Object[0]);
                        } else if (i == 1) {
                            aj.A01.invoke(obj, ar);
                        } else if (i == 2) {
                            aj.A01.invoke(obj, ar, an);
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

    public AI(Map<AJ, AN> map) {
        this.A00 = map;
        for (Map.Entry<AJ, AN> entry : map.entrySet()) {
            AN value = entry.getValue();
            List<AJ> list = this.A01.get(value);
            if (list == null) {
                list = new ArrayList<>();
                this.A01.put(value, list);
            }
            list.add(entry.getKey());
        }
    }
}
