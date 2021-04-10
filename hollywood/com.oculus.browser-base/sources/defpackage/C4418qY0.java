package defpackage;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/* renamed from: qY0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4418qY0 {

    /* renamed from: a  reason: collision with root package name */
    public Deque f11148a = new LinkedList();
    public Deque b = new LinkedList();

    public static boolean d(Deque deque, AbstractC4758sY0 sy0) {
        Iterator it = deque.iterator();
        boolean z = false;
        while (it.hasNext()) {
            C4076oY0 oy0 = (C4076oY0) it.next();
            if (oy0.f10557a == sy0) {
                it.remove();
                sy0.d(oy0.e);
                z = true;
            }
        }
        return z;
    }

    public static boolean e(Deque deque, AbstractC4758sY0 sy0, Object obj) {
        boolean z;
        Iterator it = deque.iterator();
        boolean z2 = false;
        while (it.hasNext()) {
            C4076oY0 oy0 = (C4076oY0) it.next();
            if (oy0.f10557a == sy0) {
                Object obj2 = oy0.e;
                if (obj2 == null && obj == null) {
                    z = true;
                } else {
                    z = (obj2 == null || obj == null) ? false : obj2.equals(obj);
                }
                if (z) {
                    it.remove();
                    sy0.d(oy0.e);
                    z2 = true;
                }
            }
        }
        return z2;
    }

    public C4076oY0 a() {
        C4076oY0 oy0 = (C4076oY0) this.f11148a.peekFirst();
        return oy0 == null ? (C4076oY0) this.b.peekFirst() : oy0;
    }

    public boolean b() {
        return this.f11148a.isEmpty() && this.b.isEmpty();
    }

    public final C4076oY0 c(boolean z) {
        C4076oY0 oy0 = (C4076oY0) this.f11148a.pollFirst();
        if (oy0 == null) {
            oy0 = (C4076oY0) this.b.pollFirst();
        }
        if (oy0 != null) {
            AbstractC4758sY0 sy0 = oy0.f10557a;
            if (z) {
                sy0.c(oy0.e);
            } else {
                sy0.d(oy0.e);
            }
        }
        return oy0;
    }
}
