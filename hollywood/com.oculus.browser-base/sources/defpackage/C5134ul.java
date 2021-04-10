package defpackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: ul  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5134ul implements AbstractC2930hp1 {

    /* renamed from: a  reason: collision with root package name */
    public final ReentrantReadWriteLock f11431a = new ReentrantReadWriteLock(false);
    public Map b = new HashMap();
    public AtomicInteger c = new AtomicInteger();
    public List d = new ArrayList();
    public int e;
    public AbstractC2930hp1 f;

    @Override // defpackage.AbstractC2930hp1
    public void a(String str, boolean z) {
        f(1, str, z ? 1 : 0, 0, 0, 0);
    }

    @Override // defpackage.AbstractC2930hp1
    public void b(String str, long j) {
        this.f11431a.readLock().lock();
        try {
            AbstractC2930hp1 hp1 = this.f;
            if (hp1 != null) {
                hp1.b(str, j);
                return;
            }
            this.f11431a.readLock().unlock();
            this.f11431a.writeLock().lock();
            try {
                if (this.f == null) {
                    if (this.d.size() < 256) {
                        this.d.add(new C4964tl(str, j));
                    } else {
                        this.e++;
                    }
                    return;
                }
                this.f11431a.readLock().lock();
                this.f11431a.writeLock().unlock();
                try {
                    this.f.b(str, j);
                } finally {
                    this.f11431a.readLock().unlock();
                }
            } finally {
                this.f11431a.writeLock().unlock();
            }
        } finally {
            this.f11431a.readLock().unlock();
        }
    }

    @Override // defpackage.AbstractC2930hp1
    public void c(String str, int i, int i2, int i3, int i4) {
        f(2, str, i, i2, i3, i4);
    }

    @Override // defpackage.AbstractC2930hp1
    public void d(String str, int i) {
        f(4, str, i, 0, 0, 0);
    }

    @Override // defpackage.AbstractC2930hp1
    public void e(String str, int i, int i2, int i3, int i4) {
        f(3, str, i, i2, i3, i4);
    }

    public final void f(int i, String str, int i2, int i3, int i4, int i5) {
        this.f11431a.readLock().lock();
        try {
            boolean z = true;
            if (this.f != null) {
                i(i, str, i2, i3, i4, i5);
            } else {
                C4794sl slVar = (C4794sl) this.b.get(str);
                if (slVar == null) {
                    z = false;
                } else if (!slVar.a(i, str, i2, i3, i4, i5)) {
                    this.c.incrementAndGet();
                }
            }
            if (!z) {
                this.f11431a.writeLock().lock();
                try {
                    if (this.f == null) {
                        C4794sl slVar2 = (C4794sl) this.b.get(str);
                        if (slVar2 == null) {
                            if (this.b.size() >= 256) {
                                this.c.incrementAndGet();
                                return;
                            }
                            C4794sl slVar3 = new C4794sl(i, str, i3, i4, i5);
                            this.b.put(str, slVar3);
                            slVar2 = slVar3;
                        }
                        if (!slVar2.a(i, str, i2, i3, i4, i5)) {
                            this.c.incrementAndGet();
                        }
                        return;
                    }
                    this.f11431a.readLock().lock();
                    this.f11431a.writeLock().unlock();
                    try {
                        i(i, str, i2, i3, i4, i5);
                    } finally {
                        this.f11431a.readLock().unlock();
                    }
                } finally {
                    this.f11431a.writeLock().unlock();
                }
            }
        } finally {
            this.f11431a.readLock().unlock();
        }
    }

    public final void g(Map map, int i) {
        int size;
        int size2 = map.size();
        int i2 = 0;
        for (C4794sl slVar : map.values()) {
            AbstractC2930hp1 hp1 = this.f;
            synchronized (slVar) {
                int i3 = slVar.f11297a;
                if (i3 == 1) {
                    for (int i4 = 0; i4 < slVar.f.size(); i4++) {
                        hp1.a(slVar.b, ((Integer) slVar.f.get(i4)).intValue() != 0);
                    }
                } else if (i3 == 2) {
                    for (int i5 = 0; i5 < slVar.f.size(); i5++) {
                        hp1.c(slVar.b, ((Integer) slVar.f.get(i5)).intValue(), slVar.c, slVar.d, slVar.e);
                    }
                } else if (i3 == 3) {
                    for (int i6 = 0; i6 < slVar.f.size(); i6++) {
                        hp1.e(slVar.b, ((Integer) slVar.f.get(i6)).intValue(), slVar.c, slVar.d, slVar.e);
                    }
                } else if (i3 == 4) {
                    for (int i7 = 0; i7 < slVar.f.size(); i7++) {
                        hp1.d(slVar.b, ((Integer) slVar.f.get(i7)).intValue());
                    }
                }
                size = slVar.f.size();
                slVar.f.clear();
            }
            i2 += size;
        }
        AbstractC1220Ua0.d("CachingUmaRecorder", "Flushed %d samples from %d histograms.", Integer.valueOf(i2), Integer.valueOf(size2));
        this.f.c("UMA.JavaCachingRecorder.DroppedHistogramSampleCount", i, 1, 1000000, 50);
        this.f.c("UMA.JavaCachingRecorder.FlushedHistogramCount", size2, 1, 100000, 50);
        this.f.c("UMA.JavaCachingRecorder.InputHistogramSampleCount", i2 + i, 1, 1000000, 50);
    }

    public final void h(List list, int i) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            C4964tl tlVar = (C4964tl) it.next();
            this.f.b(tlVar.f11366a, tlVar.b);
        }
        this.f.c("UMA.JavaCachingRecorder.DroppedUserActionCount", i, 1, 1000, 50);
        this.f.c("UMA.JavaCachingRecorder.InputUserActionCount", list.size() + i, 1, 10000, 50);
    }

    public final void i(int i, String str, int i2, int i3, int i4, int i5) {
        boolean z = true;
        if (i == 1) {
            AbstractC2930hp1 hp1 = this.f;
            if (i2 == 0) {
                z = false;
            }
            hp1.a(str, z);
        } else if (i == 2) {
            this.f.c(str, i2, i3, i4, i5);
        } else if (i == 3) {
            this.f.e(str, i2, i3, i4, i5);
        } else if (i == 4) {
            this.f.d(str, i2);
        } else {
            throw new UnsupportedOperationException(AbstractC2531fV.w("Unknown histogram type ", i));
        }
    }
}
