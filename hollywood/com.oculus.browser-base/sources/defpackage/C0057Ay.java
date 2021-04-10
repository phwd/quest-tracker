package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: Ay  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0057Ay {

    /* renamed from: a  reason: collision with root package name */
    public final List f7708a;
    public final List b;
    public final List c;
    public final List d;
    public final List e;

    public C0057Ay(List list, List list2, List list3, List list4, List list5) {
        this.f7708a = list;
        this.b = list2;
        this.c = list3;
        if (list4 != null) {
            this.d = new ArrayList();
            Iterator it = list4.iterator();
            while (it.hasNext()) {
                this.d.add(((C1033Qy0) it.next()).b());
            }
        } else {
            this.d = null;
        }
        if (list5 != null) {
            this.e = new ArrayList();
            Iterator it2 = list5.iterator();
            while (it2.hasNext()) {
                this.e.add(((C4833sy) it2.next()).b());
            }
            return;
        }
        this.e = null;
    }
}
