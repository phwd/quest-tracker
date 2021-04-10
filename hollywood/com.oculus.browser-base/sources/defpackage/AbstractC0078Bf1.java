package defpackage;

import java.util.List;
import java.util.Map;

/* renamed from: Bf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0078Bf1 {
    public static int a(Map map, int i) {
        int i2 = 1;
        if (map.containsKey(Integer.valueOf(i))) {
            List list = (List) map.get(Integer.valueOf(i));
            for (int i3 = 0; i3 < list.size(); i3++) {
                i2 += a(map, ((Integer) list.get(i3)).intValue());
            }
        }
        return i2;
    }
}
