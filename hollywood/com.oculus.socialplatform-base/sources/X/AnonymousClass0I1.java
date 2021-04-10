package X;

import android.graphics.Bitmap;
import android.os.SystemClock;
import androidx.annotation.VisibleForTesting;
import com.facebook.imagepipeline.cache.CountingMemoryCache;
import com.facebook.infer.annotation.Nullsafe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.0I1  reason: invalid class name */
public final class AnonymousClass0I1<K, V> implements CountingMemoryCache<K, V>, AbstractC03450mg<K, V> {
    @GuardedBy("this")
    public AnonymousClass0PF A00;
    @GuardedBy("this")
    public long A01;
    @GuardedBy("this")
    @VisibleForTesting
    public final Map<Bitmap, Object> A02 = new WeakHashMap();
    @GuardedBy("this")
    @VisibleForTesting
    public final AnonymousClass0P8<K, AnonymousClass0P9<K, V>> A03;
    @GuardedBy("this")
    @VisibleForTesting
    public final AnonymousClass0P8<K, AnonymousClass0P9<K, V>> A04;
    public final AnonymousClass0PG<V> A05;
    public final AbstractC00750Ik<AnonymousClass0PF> A06;
    public final AnonymousClass0PE A07;

    private synchronized AbstractC00820Ju<V> A00(AnonymousClass0P9<K, V> r5) {
        boolean z = false;
        if (!r5.A01) {
            z = true;
        }
        C00740Ii.A03(z);
        r5.A00++;
        return AbstractC00820Ju.A01(r5.A02.A06(), new C03460mh(this, r5));
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0P9<TK;TV;>;)LX/0Ju<TV;>; */
    @Nullable
    public static synchronized AbstractC00820Ju A01(AnonymousClass0I1 r1, AnonymousClass0P9 r2) {
        AbstractC00820Ju<V> r0;
        synchronized (r1) {
            if (r2 == null) {
                throw null;
            } else if (!r2.A01 || r2.A00 != 0) {
                r0 = null;
            } else {
                r0 = r2.A02;
            }
        }
        return r0;
    }

    private synchronized void A02(AnonymousClass0P9<K, V> r4) {
        if (r4 != null) {
            boolean z = false;
            if (!r4.A01) {
                z = true;
            }
            C00740Ii.A03(z);
            r4.A01 = true;
        } else {
            throw null;
        }
    }

    public static final void A03(AnonymousClass0I1 r7) {
        ArrayList arrayList;
        K k;
        synchronized (r7) {
            AnonymousClass0PF r0 = r7.A00;
            int i = r0.A03;
            int i2 = r0.A00;
            AnonymousClass0P8<K, AnonymousClass0P9<K, V>> r6 = r7.A03;
            int A002 = r6.A00();
            AnonymousClass0P8<K, AnonymousClass0P9<K, V>> r3 = r7.A04;
            int min = Math.min(i, i2 - (A002 - r3.A00()));
            AnonymousClass0PF r02 = r7.A00;
            int min2 = Math.min(r02.A04, r02.A02 - (r6.A01() - r3.A01()));
            int max = Math.max(min, 0);
            int max2 = Math.max(min2, 0);
            if (r3.A00() > max || r3.A01() > max2) {
                arrayList = new ArrayList();
                while (true) {
                    if (r3.A00() > max || r3.A01() > max2) {
                        synchronized (r3) {
                            LinkedHashMap<K, V> linkedHashMap = r3.A02;
                            if (linkedHashMap.isEmpty()) {
                                k = null;
                            } else {
                                k = linkedHashMap.keySet().iterator().next();
                            }
                        }
                        if (k != null) {
                            r3.A02(k);
                            arrayList.add(r6.A02(k));
                        } else {
                            throw new IllegalStateException(String.format("key is null, but exclusiveEntries count: %d, size: %d", Integer.valueOf(r3.A00()), Integer.valueOf(r3.A01())));
                        }
                    } else {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            r7.A02((AnonymousClass0P9) it.next());
                        }
                    }
                }
            } else {
                arrayList = null;
            }
        }
        if (arrayList != null) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                AbstractC00820Ju.A03(A01(r7, (AnonymousClass0P9) it2.next()));
            }
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                it3.next();
            }
        }
    }

    public static synchronized void A04(AnonymousClass0I1 r5) {
        synchronized (r5) {
            if (r5.A01 + r5.A00.A05 <= SystemClock.uptimeMillis()) {
                r5.A01 = SystemClock.uptimeMillis();
                AnonymousClass0PF r1 = r5.A06.get();
                C00740Ii.A02(r1, "mMemoryCacheParamsSupplier returned null");
                r5.A00 = r1;
            }
        }
    }

    @Override // X.AbstractC03450mg
    @Nullable
    public final AbstractC00820Ju<V> A1u(K k, AbstractC00820Ju<V> r11) {
        AbstractC00820Ju<V> r6;
        AbstractC00820Ju r7 = null;
        if (k == null) {
            throw null;
        } else if (r11 != null) {
            A04(this);
            synchronized (this) {
                AnonymousClass0P8<K, AnonymousClass0P9<K, V>> r5 = this.A04;
                r5.A02(k);
                AnonymousClass0P8<K, AnonymousClass0P9<K, V>> r3 = this.A03;
                AnonymousClass0P9<K, V> A022 = r3.A02(k);
                r6 = null;
                if (A022 != null) {
                    A02(A022);
                    r7 = A01(this, A022);
                }
                int A4w = this.A05.A4w(r11.A06());
                if (A4w <= this.A00.A01) {
                    if (r3.A00() - r5.A00() <= this.A00.A00 - 1) {
                        if (r3.A01() - r5.A01() <= this.A00.A02 - A4w) {
                            AnonymousClass0P9<K, V> r0 = new AnonymousClass0P9<>(k, r11);
                            r3.A03(k, r0);
                            r6 = A00(r0);
                        }
                    }
                }
            }
            AbstractC00820Ju.A03(r7);
            A03(this);
            return r6;
        } else {
            throw null;
        }
    }

    @Override // X.AbstractC03450mg
    @Nullable
    public final AbstractC00820Ju<V> A3K(K k) {
        V v;
        AbstractC00820Ju<V> r0;
        if (k != null) {
            synchronized (this) {
                this.A04.A02(k);
                AnonymousClass0P8<K, AnonymousClass0P9<K, V>> r1 = this.A03;
                synchronized (r1) {
                    v = r1.A02.get(k);
                }
                V v2 = v;
                if (v2 != null) {
                    r0 = A00(v2);
                } else {
                    r0 = null;
                }
            }
            A04(this);
            A03(this);
            return r0;
        }
        throw null;
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/0PG<TV;>;LX/0PE;LX/0Ik<LX/0PF;>;Lcom/facebook/imagepipeline/cache/CountingMemoryCache$EntryStateObserver<TK;>;)V */
    public AnonymousClass0I1(AnonymousClass0PG r3, AnonymousClass0PE r4, AbstractC00750Ik r5) {
        this.A05 = r3;
        this.A04 = new AnonymousClass0P8<>(new C03470mi(this, r3));
        this.A03 = new AnonymousClass0P8<>(new C03470mi(this, r3));
        this.A07 = r4;
        this.A06 = r5;
        Object obj = r5.get();
        C00740Ii.A02(obj, "mMemoryCacheParamsSupplier returned null");
        this.A00 = (AnonymousClass0PF) obj;
        this.A01 = SystemClock.uptimeMillis();
    }
}
