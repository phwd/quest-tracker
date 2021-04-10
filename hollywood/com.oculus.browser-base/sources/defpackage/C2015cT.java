package defpackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* renamed from: cT  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2015cT {

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList f9608a = new ArrayList();
    public final HashMap b = new HashMap();

    public void a(AbstractComponentCallbacksC3550lS lSVar) {
        if (!this.f9608a.contains(lSVar)) {
            synchronized (this.f9608a) {
                this.f9608a.add(lSVar);
            }
            lSVar.P = true;
            return;
        }
        throw new IllegalStateException("Fragment already added: " + lSVar);
    }

    public void b() {
        this.b.values().removeAll(Collections.singleton(null));
    }

    public boolean c(String str) {
        return this.b.containsKey(str);
    }

    public void d(int i) {
        Iterator it = this.f9608a.iterator();
        while (it.hasNext()) {
            C1844bT bTVar = (C1844bT) this.b.get(((AbstractComponentCallbacksC3550lS) it.next()).f10345J);
            if (bTVar != null) {
                bTVar.c = i;
            }
        }
        for (C1844bT bTVar2 : this.b.values()) {
            if (bTVar2 != null) {
                bTVar2.c = i;
            }
        }
    }

    public AbstractComponentCallbacksC3550lS e(String str) {
        C1844bT bTVar = (C1844bT) this.b.get(str);
        if (bTVar != null) {
            return bTVar.b;
        }
        return null;
    }

    public List f() {
        ArrayList arrayList = new ArrayList();
        for (C1844bT bTVar : this.b.values()) {
            if (bTVar != null) {
                arrayList.add(bTVar.b);
            } else {
                arrayList.add(null);
            }
        }
        return arrayList;
    }

    public List g() {
        ArrayList arrayList;
        if (this.f9608a.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.f9608a) {
            arrayList = new ArrayList(this.f9608a);
        }
        return arrayList;
    }

    public void h(AbstractComponentCallbacksC3550lS lSVar) {
        synchronized (this.f9608a) {
            this.f9608a.remove(lSVar);
        }
        lSVar.P = false;
    }
}
