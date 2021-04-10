package defpackage;

import java.util.HashMap;
import java.util.Map;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: Y51  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Y51 extends Pr1 {
    public static final Object F = new Object();
    public final Map G = new HashMap();

    public static Y51 c(Tab tab) {
        Rr1 M = tab.M();
        Y51 y51 = (Y51) M.c(Y51.class);
        return y51 != null ? y51 : (Y51) M.e(Y51.class, new Y51());
    }

    public Object e(String str) {
        Object obj = this.G.get(str);
        if (obj != F) {
            return obj;
        }
        return null;
    }
}
