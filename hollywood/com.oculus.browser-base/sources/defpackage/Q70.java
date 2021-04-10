package defpackage;

import java.nio.charset.Charset;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* renamed from: Q70  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Q70 extends AbstractC2961i implements R70, RandomAccess {
    public static final Q70 G;
    public final List H;

    static {
        Q70 q70 = new Q70(10);
        G = q70;
        q70.F = false;
    }

    public Q70(int i) {
        this.H = new ArrayList(i);
    }

    public static String b(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof AbstractC1248Uk) {
            return ((AbstractC1248Uk) obj).m();
        }
        Charset charset = F30.f7992a;
        return new String((byte[]) obj, F30.f7992a);
    }

    @Override // java.util.List, java.util.AbstractList
    public void add(int i, Object obj) {
        a();
        this.H.add(i, (String) obj);
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.AbstractCollection, java.util.List, java.util.Collection, defpackage.AbstractC2961i
    public boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }

    @Override // defpackage.AbstractC2961i
    public void clear() {
        a();
        this.H.clear();
        ((AbstractList) this).modCount++;
    }

    @Override // defpackage.E30
    public E30 d(int i) {
        if (i >= size()) {
            ArrayList arrayList = new ArrayList(i);
            arrayList.addAll(this.H);
            return new Q70(arrayList);
        }
        throw new IllegalArgumentException();
    }

    @Override // defpackage.R70
    public R70 g() {
        return this.F ? new Dp1(this) : this;
    }

    @Override // java.util.List, java.util.AbstractList
    public Object get(int i) {
        String str;
        Object obj = this.H.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof AbstractC1248Uk) {
            AbstractC1248Uk uk = (AbstractC1248Uk) obj;
            str = uk.m();
            C1309Vk vk = (C1309Vk) uk;
            int o = vk.o();
            if (AbstractC3280js1.e(vk.I, o, vk.size() + o)) {
                this.H.set(i, str);
            }
        } else {
            byte[] bArr = (byte[]) obj;
            Charset charset = F30.f7992a;
            str = new String(bArr, F30.f7992a);
            boolean z = false;
            if (AbstractC3280js1.f10244a.c(0, bArr, 0, bArr.length) == 0) {
                z = true;
            }
            if (z) {
                this.H.set(i, str);
            }
        }
        return str;
    }

    @Override // defpackage.R70
    public Object i(int i) {
        return this.H.get(i);
    }

    @Override // defpackage.R70
    public List j() {
        return Collections.unmodifiableList(this.H);
    }

    @Override // defpackage.R70
    public void k(AbstractC1248Uk uk) {
        a();
        this.H.add(uk);
        ((AbstractList) this).modCount++;
    }

    @Override // java.util.List, java.util.AbstractList
    public Object remove(int i) {
        a();
        Object remove = this.H.remove(i);
        ((AbstractList) this).modCount++;
        return b(remove);
    }

    @Override // java.util.List, java.util.AbstractList
    public Object set(int i, Object obj) {
        a();
        return b(this.H.set(i, (String) obj));
    }

    public int size() {
        return this.H.size();
    }

    @Override // java.util.List, defpackage.AbstractC2961i, java.util.AbstractList
    public boolean addAll(int i, Collection collection) {
        a();
        if (collection instanceof R70) {
            collection = ((R70) collection).j();
        }
        boolean addAll = this.H.addAll(i, collection);
        ((AbstractList) this).modCount++;
        return addAll;
    }

    public Q70(ArrayList arrayList) {
        this.H = arrayList;
    }
}
