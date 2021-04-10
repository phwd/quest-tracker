package X;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class hO<T> implements Iterator<T> {
    public int A00;
    public hN<K, V> A01 = null;
    public hN<K, V> A02;
    public final /* synthetic */ hM A03;

    public hO(hM hMVar) {
        this.A03 = hMVar;
        this.A02 = hMVar.header.A01;
        this.A00 = hMVar.modCount;
    }

    public final hN<K, V> A00() {
        hN<K, V> hNVar = this.A02;
        hM hMVar = this.A03;
        if (hNVar == hMVar.header) {
            throw new NoSuchElementException();
        } else if (hMVar.modCount == this.A00) {
            this.A02 = hNVar.A01;
            this.A01 = hNVar;
            return hNVar;
        } else {
            throw new ConcurrentModificationException();
        }
    }

    public final boolean hasNext() {
        if (this.A02 != this.A03.header) {
            return true;
        }
        return false;
    }

    public final void remove() {
        hN<K, V> hNVar = this.A01;
        if (hNVar != null) {
            hM hMVar = this.A03;
            hMVar.A06(hNVar, true);
            this.A01 = null;
            this.A00 = hMVar.modCount;
            return;
        }
        throw new IllegalStateException();
    }
}
