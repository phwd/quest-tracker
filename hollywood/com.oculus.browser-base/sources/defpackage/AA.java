package defpackage;

import android.util.Pair;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.chromium.chrome.browser.contextualsearch.ContextualSearchManager;

/* renamed from: AA  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AA {

    /* renamed from: a  reason: collision with root package name */
    public static final Pattern f7657a = Pattern.compile("\\s");
    public static final Map b;
    public static final Map c;
    public static final Map d;
    public static final Map e;
    public static final Map f;
    public static final Map g;
    public static final Map h;
    public static final Map i;
    public static final Map j;
    public static final Map k;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(new C5890zA(2, 2), 1);
        hashMap.put(new C5890zA(2, 8), 2);
        hashMap.put(new C5890zA(2, 3), 3);
        hashMap.put(new C5890zA(3, 2), 4);
        hashMap.put(new C5890zA(3, 7), 5);
        hashMap.put(new C5890zA(3, 14), 6);
        hashMap.put(new C5890zA(4, 2), 7);
        hashMap.put(new C5890zA(4, 14), 8);
        hashMap.put(new C5890zA(4, 11), 9);
        hashMap.put(new C5890zA(4, 10), 10);
        b = Collections.unmodifiableMap(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(new C5890zA(0, 3), 1);
        hashMap2.put(new C5890zA(0, 4), 2);
        hashMap2.put(new C5890zA(1, 3), 1);
        hashMap2.put(new C5890zA(1, 4), 2);
        hashMap2.put(new C5890zA(2, 3), 3);
        hashMap2.put(new C5890zA(2, 4), 4);
        hashMap2.put(new C5890zA(3, 9), 5);
        hashMap2.put(new C5890zA(3, 13), 6);
        hashMap2.put(new C5890zA(3, 14), 7);
        hashMap2.put(new C5890zA(4, 13), 8);
        hashMap2.put(new C5890zA(4, 14), 9);
        c = Collections.unmodifiableMap(hashMap2);
        HashMap hashMap3 = new HashMap();
        hashMap3.put(new C5890zA(2, 9), 1);
        hashMap3.put(new C5890zA(2, 13), 2);
        hashMap3.put(new C5890zA(2, 14), 3);
        hashMap3.put(new C5890zA(4, 13), 4);
        hashMap3.put(new C5890zA(4, 14), 5);
        d = Collections.unmodifiableMap(hashMap3);
        HashMap hashMap4 = new HashMap();
        hashMap4.put(new C5890zA(2, 13), 1);
        hashMap4.put(new C5890zA(2, 14), 2);
        hashMap4.put(new C5890zA(3, 13), 3);
        hashMap4.put(new C5890zA(3, 14), 4);
        hashMap4.put(new C5890zA(3, 10), 5);
        e = Collections.unmodifiableMap(hashMap4);
        HashMap hashMap5 = new HashMap();
        hashMap5.put(new C5890zA(2, 3), 1);
        hashMap5.put(new C5890zA(2, 4), 2);
        f = Collections.unmodifiableMap(hashMap5);
        HashMap hashMap6 = new HashMap();
        hashMap6.put(new C5890zA(1, 2), 1);
        hashMap6.put(new C5890zA(1, 8), 2);
        hashMap6.put(new C5890zA(1, 7), 3);
        hashMap6.put(new C5890zA(2, 3), 4);
        hashMap6.put(new C5890zA(2, 4), 5);
        hashMap6.put(new C5890zA(3, 9), 6);
        hashMap6.put(new C5890zA(3, 13), 7);
        hashMap6.put(new C5890zA(3, 14), 8);
        hashMap6.put(new C5890zA(4, 13), 9);
        hashMap6.put(new C5890zA(4, 14), 10);
        g = Collections.unmodifiableMap(hashMap6);
        HashMap hashMap7 = new HashMap();
        hashMap7.put(new C5890zA(1, 2), 1);
        hashMap7.put(new C5890zA(1, 7), 2);
        hashMap7.put(new C5890zA(1, 14), 3);
        hashMap7.put(new C5890zA(2, 9), 4);
        hashMap7.put(new C5890zA(2, 13), 5);
        hashMap7.put(new C5890zA(2, 14), 6);
        hashMap7.put(new C5890zA(4, 13), 7);
        hashMap7.put(new C5890zA(4, 14), 8);
        hashMap7.put(new C5890zA(4, 10), 9);
        h = Collections.unmodifiableMap(hashMap7);
        HashMap hashMap8 = new HashMap();
        hashMap8.put(new C5890zA(1, 2), 1);
        hashMap8.put(new C5890zA(1, 14), 2);
        hashMap8.put(new C5890zA(1, 11), 3);
        hashMap8.put(new C5890zA(1, 10), 4);
        hashMap8.put(new C5890zA(2, 13), 5);
        hashMap8.put(new C5890zA(2, 14), 6);
        hashMap8.put(new C5890zA(3, 13), 7);
        hashMap8.put(new C5890zA(3, 14), 8);
        i = Collections.unmodifiableMap(hashMap8);
        HashMap hashMap9 = new HashMap();
        Boolean bool = Boolean.TRUE;
        hashMap9.put(new Pair(bool, bool), 0);
        Boolean bool2 = Boolean.FALSE;
        hashMap9.put(new Pair(bool2, bool), 1);
        hashMap9.put(new Pair(bool, bool2), 2);
        hashMap9.put(new Pair(bool2, bool2), 3);
        j = Collections.unmodifiableMap(hashMap9);
        HashMap hashMap10 = new HashMap();
        hashMap10.put(new Pair(1, bool), 0);
        hashMap10.put(new Pair(2, bool), 1);
        hashMap10.put(new Pair(0, bool), 2);
        hashMap10.put(new Pair(1, bool2), 3);
        hashMap10.put(new Pair(2, bool2), 4);
        hashMap10.put(new Pair(0, bool2), 5);
        k = Collections.unmodifiableMap(hashMap10);
    }

    public static String a(int i2) {
        return i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "None" : "Website" : "Phone" : "Event" : "Email" : "Address";
    }

    public static int b() {
        if (ContextualSearchManager.k()) {
            return 0;
        }
        return ContextualSearchManager.j() ? 2 : 1;
    }

    public static int c(int i2, int i3, Map map, int i4) {
        Integer num = (Integer) map.get(new C5890zA(i2, i3));
        return num != null ? num.intValue() : i4;
    }

    public static void d(boolean z, boolean z2, String str) {
        AbstractC3364kK0.g(str, ((Integer) j.get(new Pair(Boolean.valueOf(z), Boolean.valueOf(z2)))).intValue(), 4);
    }

    public static void e(int i2, int i3) {
        int i4 = 1;
        if (i3 == 0) {
            i4 = 0;
        } else if (i3 != 1) {
            i4 = 2;
        }
        StringBuilder i5 = AbstractC2531fV.i("Search.ContextualSearchQuickActions.IntentResolution.");
        i5.append(a(i2));
        AbstractC3364kK0.g(i5.toString(), i4, 3);
    }
}
