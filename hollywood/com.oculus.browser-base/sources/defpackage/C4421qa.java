package defpackage;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* renamed from: qa  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C4421qa implements Iterator, Map.Entry {
    public int F;
    public int G = -1;
    public boolean H;
    public final /* synthetic */ C4931ta I;

    public C4421qa(C4931ta taVar) {
        this.I = taVar;
        this.F = taVar.L - 1;
    }

    public boolean equals(Object obj) {
        if (!this.H) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        } else if (!(obj instanceof Map.Entry)) {
            return false;
        } else {
            Map.Entry entry = (Map.Entry) obj;
            if (!AbstractC0179Cy.c(entry.getKey(), this.I.h(this.G)) || !AbstractC0179Cy.c(entry.getValue(), this.I.k(this.G))) {
                return false;
            }
            return true;
        }
    }

    @Override // java.util.Map.Entry
    public Object getKey() {
        if (this.H) {
            return this.I.h(this.G);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    @Override // java.util.Map.Entry
    public Object getValue() {
        if (this.H) {
            return this.I.k(this.G);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public boolean hasNext() {
        return this.G < this.F;
    }

    public int hashCode() {
        int i;
        if (this.H) {
            Object h = this.I.h(this.G);
            Object k = this.I.k(this.G);
            int i2 = 0;
            if (h == null) {
                i = 0;
            } else {
                i = h.hashCode();
            }
            if (k != null) {
                i2 = k.hashCode();
            }
            return i ^ i2;
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    @Override // java.util.Iterator
    public Object next() {
        if (hasNext()) {
            this.G++;
            this.H = true;
            return this;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        if (this.H) {
            this.I.i(this.G);
            this.G--;
            this.F--;
            this.H = false;
            return;
        }
        throw new IllegalStateException();
    }

    @Override // java.util.Map.Entry
    public Object setValue(Object obj) {
        if (this.H) {
            return this.I.j(this.G, obj);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public String toString() {
        return getKey() + "=" + getValue();
    }
}
