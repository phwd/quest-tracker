package defpackage;

import java.util.Iterator;
import java.util.Map;

/* renamed from: Cc0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0129Cc0 {
    public int a(int i, Object obj, Object obj2) {
        C0068Bc0 bc0 = (C0068Bc0) obj;
        C5859z.a(obj2);
        if (bc0.isEmpty()) {
            return 0;
        }
        Iterator it = bc0.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw null;
    }

    public Object b(Object obj, Object obj2) {
        C0068Bc0 bc0 = (C0068Bc0) obj;
        C0068Bc0 bc02 = (C0068Bc0) obj2;
        if (!bc02.isEmpty()) {
            if (!bc0.G) {
                bc0 = bc0.isEmpty() ? new C0068Bc0() : new C0068Bc0(bc0);
            }
            bc0.b();
            if (!bc02.isEmpty()) {
                bc0.putAll(bc02);
            }
        }
        return bc0;
    }
}
