package X;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bj {
    public final Map<C0036Bk, EnumC0039Bo> A00;
    public final Map<EnumC0039Bo, List<C0036Bk>> A01 = new HashMap();

    public static void A00(List<C0036Bk> list, Bs bs, EnumC0039Bo bo, Object obj) {
        if (list != null) {
            int size = list.size();
            while (true) {
                size--;
                if (size >= 0) {
                    C0036Bk bk = list.get(size);
                    try {
                        int i = bk.A00;
                        if (i == 0) {
                            bk.A01.invoke(obj, new Object[0]);
                        } else if (i == 1) {
                            bk.A01.invoke(obj, bs);
                        } else if (i == 2) {
                            bk.A01.invoke(obj, bs, bo);
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

    public Bj(Map<C0036Bk, EnumC0039Bo> map) {
        this.A00 = map;
        for (Map.Entry<C0036Bk, EnumC0039Bo> entry : map.entrySet()) {
            EnumC0039Bo value = entry.getValue();
            List<C0036Bk> list = this.A01.get(value);
            if (list == null) {
                list = new ArrayList<>();
                this.A01.put(value, list);
            }
            list.add(entry.getKey());
        }
    }
}
