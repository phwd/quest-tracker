package defpackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: Vq0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1322Vq0 implements Iterable {
    public final List F = new ArrayList();
    public int G;
    public int H;
    public boolean I;

    public static Object a(C1322Vq0 vq0, int i) {
        return vq0.F.get(i);
    }

    public boolean b(Object obj) {
        if (obj == null || this.F.contains(obj)) {
            return false;
        }
        this.F.add(obj);
        this.H++;
        return true;
    }

    public boolean c(Object obj) {
        int indexOf;
        if (obj == null || (indexOf = this.F.indexOf(obj)) == -1) {
            return false;
        }
        if (this.G == 0) {
            this.F.remove(indexOf);
        } else {
            this.I = true;
            this.F.set(indexOf, null);
        }
        this.H--;
        return true;
    }

    public void clear() {
        this.H = 0;
        if (this.G == 0) {
            this.F.clear();
            return;
        }
        int size = this.F.size();
        this.I |= size != 0;
        for (int i = 0; i < size; i++) {
            this.F.set(i, null);
        }
    }

    public C1261Uq0 e() {
        return new C1261Uq0(this, null);
    }

    public boolean isEmpty() {
        return this.H == 0;
    }

    @Override // java.lang.Iterable
    public Iterator iterator() {
        return new C1261Uq0(this, null);
    }
}
