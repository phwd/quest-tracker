package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: US  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class US {

    /* renamed from: a  reason: collision with root package name */
    public List f9028a = new CopyOnWriteArrayList();

    public List a(AbstractComponentCallbacksC3550lS lSVar, EnumC3328k80 k80) {
        ArrayList arrayList = new ArrayList();
        Iterator it = this.f9028a.iterator();
        if (!it.hasNext()) {
            return arrayList;
        }
        C5859z.a(it.next());
        throw null;
    }

    public void b(List list) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ((ZS) it.next()).a();
        }
    }
}
