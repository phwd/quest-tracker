package a.a;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* compiled from: chromium-webapk7.dex */
public final class d implements Iterator, Map.Entry {

    /* renamed from: a  reason: collision with root package name */
    public int f9395a;
    public int b = -1;
    public boolean c;
    public final /* synthetic */ g d;

    public d(g gVar) {
        this.d = gVar;
        this.f9395a = gVar.g - 1;
    }

    public boolean equals(Object obj) {
        if (!this.c) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        } else if (!(obj instanceof Map.Entry)) {
            return false;
        } else {
            Map.Entry entry = (Map.Entry) obj;
            if (!h.a(entry.getKey(), this.d.i(this.b)) || !h.a(entry.getValue(), this.d.m(this.b))) {
                return false;
            }
            return true;
        }
    }

    @Override // java.util.Map.Entry
    public Object getKey() {
        if (this.c) {
            return this.d.i(this.b);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    @Override // java.util.Map.Entry
    public Object getValue() {
        if (this.c) {
            return this.d.m(this.b);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public boolean hasNext() {
        return this.b < this.f9395a;
    }

    public int hashCode() {
        int i;
        if (this.c) {
            Object i2 = this.d.i(this.b);
            Object m = this.d.m(this.b);
            int i3 = 0;
            if (i2 == null) {
                i = 0;
            } else {
                i = i2.hashCode();
            }
            if (m != null) {
                i3 = m.hashCode();
            }
            return i ^ i3;
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    @Override // java.util.Iterator
    public Object next() {
        if (hasNext()) {
            this.b++;
            this.c = true;
            return this;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        if (this.c) {
            this.d.j(this.b);
            this.b--;
            this.f9395a--;
            this.c = false;
            return;
        }
        throw new IllegalStateException();
    }

    @Override // java.util.Map.Entry
    public Object setValue(Object obj) {
        if (this.c) {
            return this.d.k(this.b, obj);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public String toString() {
        return getKey() + "=" + getValue();
    }
}
