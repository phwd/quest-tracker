package defpackage;

import java.util.HashMap;
import java.util.Map;

/* renamed from: pe0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4263pe0 {

    /* renamed from: a  reason: collision with root package name */
    public final Map f11078a = new HashMap();
    public final Map b = new HashMap();
    public final Map c = new HashMap();

    public final C4263pe0 a(String str, String str2, int i) {
        this.f11078a.put(str, str2);
        this.b.put(str2, str);
        this.c.put(str, Integer.valueOf(i));
        return this;
    }

    public final String b(String str) {
        return (String) this.f11078a.get(str);
    }

    public final int c(String str) {
        Integer num = (Integer) this.c.get(str);
        if (num != null) {
            return num.intValue();
        }
        return 0;
    }
}
