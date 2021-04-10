package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: L80  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class L80 extends N80 {
    public static final Class c = Collections.unmodifiableList(Collections.emptyList()).getClass();

    public L80(K80 k80) {
        super(null);
    }

    @Override // defpackage.N80
    public void a(Object obj, long j) {
        Object obj2;
        List list = (List) Op1.n(obj, j);
        if (list instanceof R70) {
            obj2 = ((R70) list).g();
        } else if (!c.isAssignableFrom(list.getClass())) {
            if (!(list instanceof LF0) || !(list instanceof E30)) {
                obj2 = Collections.unmodifiableList(list);
            } else {
                AbstractC2961i iVar = (AbstractC2961i) ((E30) list);
                if (iVar.F) {
                    iVar.F = false;
                    return;
                }
                return;
            }
        } else {
            return;
        }
        Op1.e.q(obj, j, obj2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v11, resolved type: java.util.ArrayList */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.N80
    public void b(Object obj, Object obj2, long j) {
        Q70 q70;
        List list = (List) Op1.n(obj2, j);
        int size = list.size();
        List list2 = (List) Op1.n(obj, j);
        if (list2.isEmpty()) {
            if (list2 instanceof R70) {
                list2 = new Q70(size);
            } else if (!(list2 instanceof LF0) || !(list2 instanceof E30)) {
                list2 = new ArrayList(size);
            } else {
                list2 = ((E30) list2).d(size);
            }
            Op1.e.q(obj, j, list2);
        } else {
            if (c.isAssignableFrom(list2.getClass())) {
                ArrayList arrayList = new ArrayList(list2.size() + size);
                arrayList.addAll(list2);
                Op1.e.q(obj, j, arrayList);
                q70 = arrayList;
            } else if (list2 instanceof Dp1) {
                Q70 q702 = new Q70(list2.size() + size);
                q702.addAll(q702.size(), (Dp1) list2);
                Op1.e.q(obj, j, q702);
                q70 = q702;
            } else if ((list2 instanceof LF0) && (list2 instanceof E30)) {
                E30 e30 = (E30) list2;
                if (!((AbstractC2961i) e30).F) {
                    list2 = e30.d(list2.size() + size);
                    Op1.e.q(obj, j, list2);
                }
            }
            list2 = q70;
        }
        int size2 = list2.size();
        int size3 = list.size();
        if (size2 > 0 && size3 > 0) {
            list2.addAll(list);
        }
        if (size2 > 0) {
            list = list2;
        }
        Op1.e.q(obj, j, list);
    }
}
