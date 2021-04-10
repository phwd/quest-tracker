package defpackage;

import java.util.ArrayList;
import java.util.HashSet;

/* renamed from: KF  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class KF {

    /* renamed from: a  reason: collision with root package name */
    public final C5903zE0 f8353a = new C5903zE0(10);
    public final BW0 b = new BW0();
    public final ArrayList c = new ArrayList();
    public final HashSet d = new HashSet();

    public void a(Object obj) {
        if (!(this.b.e(obj) >= 0)) {
            this.b.put(obj, null);
        }
    }

    public final void b(Object obj, ArrayList arrayList, HashSet hashSet) {
        if (!arrayList.contains(obj)) {
            if (!hashSet.contains(obj)) {
                hashSet.add(obj);
                ArrayList arrayList2 = (ArrayList) this.b.getOrDefault(obj, null);
                if (arrayList2 != null) {
                    int size = arrayList2.size();
                    for (int i = 0; i < size; i++) {
                        b(arrayList2.get(i), arrayList, hashSet);
                    }
                }
                hashSet.remove(obj);
                arrayList.add(obj);
                return;
            }
            throw new RuntimeException("This graph contains cyclic dependencies");
        }
    }
}
