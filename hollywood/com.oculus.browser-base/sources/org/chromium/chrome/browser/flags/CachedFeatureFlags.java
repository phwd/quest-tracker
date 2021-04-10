package org.chromium.chrome.browser.flags;

import J.N;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CachedFeatureFlags {

    /* renamed from: a  reason: collision with root package name */
    public static Map f10676a = new C2233dl();
    public static final Map b = new C2403el();
    public static Map c = new HashMap();
    public static Map d = new HashMap();
    public static Map e = new HashMap();
    public static Map f = new HashMap();
    public static String g;

    public static void a(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (f10676a.containsKey(str)) {
                String str2 = (String) b.get(str);
                if (str2 == null) {
                    str2 = AbstractC0533Is.d.b(str);
                }
                NU0.f8549a.m(str2, N.M09VlOh_(str));
            } else {
                throw new IllegalArgumentException(AbstractC2531fV.g("Feature ", str, " has no default in CachedFeatureFlags."));
            }
        }
    }

    public static boolean b(String str, boolean z) {
        Boolean bool = (Boolean) c.get(str);
        if (bool == null) {
            bool = Boolean.valueOf(NU0.f8549a.d(str, z));
            c.put(str, bool);
        }
        return bool.booleanValue();
    }

    public static String getReachedCodeProfilerTrialGroup() {
        if (g == null) {
            g = NU0.f8549a.i("reached_code_profiler_group", "");
        }
        return g;
    }

    public static boolean isEnabled(String str) {
        Boolean bool;
        if (f10676a.containsKey(str)) {
            String str2 = (String) b.get(str);
            if (str2 == null) {
                str2 = AbstractC0533Is.d.b(str);
            }
            Boolean bool2 = (Boolean) c.get(str2);
            if (bool2 != null) {
                return bool2.booleanValue();
            }
            PU0 pu0 = NU0.f8549a;
            if (pu0.b(str2)) {
                bool = Boolean.valueOf(pu0.d(str2, false));
            } else {
                bool = (Boolean) f10676a.get(str);
            }
            c.put(str2, bool);
            return bool.booleanValue();
        }
        throw new IllegalArgumentException(AbstractC2531fV.g("Feature ", str, " has no default in CachedFeatureFlags."));
    }
}
