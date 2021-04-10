package defpackage;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* renamed from: Bu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0110Bu {

    /* renamed from: a  reason: collision with root package name */
    public final Map f7768a = new HashMap();
    public final Map b;

    public C0110Bu(Map map) {
        this.b = map;
        for (Map.Entry entry : map.entrySet()) {
            EnumC3157j80 j80 = (EnumC3157j80) entry.getValue();
            List list = (List) this.f7768a.get(j80);
            if (list == null) {
                list = new ArrayList();
                this.f7768a.put(j80, list);
            }
            list.add(entry.getKey());
        }
    }

    public static void a(List list, AbstractC4524r80 r80, EnumC3157j80 j80, Object obj) {
        if (list != null) {
            for (int size = list.size() - 1; size >= 0; size--) {
                C0171Cu cu = (C0171Cu) list.get(size);
                Objects.requireNonNull(cu);
                try {
                    int i = cu.f7847a;
                    if (i == 0) {
                        cu.b.invoke(obj, new Object[0]);
                    } else if (i == 1) {
                        cu.b.invoke(obj, r80);
                    } else if (i == 2) {
                        cu.b.invoke(obj, r80, j80);
                    }
                } catch (InvocationTargetException e) {
                    throw new RuntimeException("Failed to call observer method", e.getCause());
                } catch (IllegalAccessException e2) {
                    throw new RuntimeException(e2);
                }
            }
        }
    }
}
