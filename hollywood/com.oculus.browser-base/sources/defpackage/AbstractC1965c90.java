package defpackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* renamed from: c90  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1965c90 extends AbstractC2990i90 implements FW0 {
    public final List G = new ArrayList();

    public void add(int i, Object obj) {
        this.G.add(i, obj);
        o(i, 1);
    }

    public void clear() {
        if (size() > 0) {
            int size = size();
            this.G.subList(0, size + 0).clear();
            p(0, size);
        }
    }

    public Object get(int i) {
        return this.G.get(i);
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return this.G.iterator();
    }

    public void q(Object obj) {
        this.G.add(obj);
        o(this.G.size() - 1, 1);
    }

    public void r(int i, int i2) {
        Object remove = this.G.remove(i);
        if (i2 == this.G.size()) {
            this.G.add(remove);
        } else {
            this.G.add(i2, remove);
        }
        m(i, i2);
    }

    public Object s(int i) {
        Object remove = this.G.remove(i);
        p(i, 1);
        return remove;
    }

    public int size() {
        return this.G.size();
    }

    public void t(Collection collection) {
        int size = this.G.size();
        int size2 = collection.size();
        this.G.clear();
        this.G.addAll(collection);
        int min = Math.min(size, size2);
        if (min > 0) {
            n(0, min, null);
        }
        if (size2 > size) {
            o(min, size2 - size);
        } else if (size2 < size) {
            p(min, size - size2);
        }
    }

    public void u(Object[] objArr) {
        t(Arrays.asList(objArr));
    }
}
