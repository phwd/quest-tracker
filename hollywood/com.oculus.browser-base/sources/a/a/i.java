package a.a;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: chromium-webapk7.dex */
public abstract class i implements Iterator {

    /* renamed from: a  reason: collision with root package name */
    public int f9399a;
    public int b;
    public boolean c;

    public i(int i) {
        this.f9399a = i;
    }

    public abstract Object a(int i);

    public abstract void b(int i);

    public final boolean hasNext() {
        return this.b < this.f9399a;
    }

    @Override // java.util.Iterator
    public Object next() {
        if (hasNext()) {
            Object a2 = a(this.b);
            this.b++;
            this.c = true;
            return a2;
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        if (this.c) {
            int i = this.b - 1;
            this.b = i;
            b(i);
            this.f9399a--;
            this.c = false;
            return;
        }
        throw new IllegalStateException();
    }
}
