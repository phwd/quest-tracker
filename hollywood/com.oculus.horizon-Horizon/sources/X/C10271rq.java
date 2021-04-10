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
/* renamed from: X.1rq  reason: invalid class name and case insensitive filesystem */
public final class C10271rq<K, V> implements CountingMemoryCache<K, V>, AbstractC10301rt<K, V> {
    @GuardedBy("this")
    public C10411sn A00;
    @GuardedBy("this")
    public long A01;
    @GuardedBy("this")
    @VisibleForTesting
    public final Map<Bitmap, Object> A02 = new WeakHashMap();
    @GuardedBy("this")
    @VisibleForTesting
    public final C10281rr<K, C10311ru<K, V>> A03;
    @GuardedBy("this")
    @VisibleForTesting
    public final C10281rr<K, C10311ru<K, V>> A04;
    public final AnonymousClass1tZ<V> A05;
    public final AnonymousClass0KW<C10411sn> A06;
    public final AbstractC10681un A07;

    private synchronized AnonymousClass1qa<V> A00(C10311ru<K, V> r5) {
        boolean z = false;
        if (!r5.A01) {
            z = true;
        }
        AnonymousClass0KU.A03(z);
        r5.A00++;
        return AnonymousClass1qa.A01(r5.A02.A04(), new C10291rs(this, r5));
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1ru<TK;TV;>;)LX/1qa<TV;>; */
    @Nullable
    public static synchronized AnonymousClass1qa A01(C10271rq r1, C10311ru r2) {
        AnonymousClass1qa<V> r0;
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

    private synchronized void A02(C10311ru<K, V> r4) {
        if (r4 != null) {
            boolean z = false;
            if (!r4.A01) {
                z = true;
            }
            AnonymousClass0KU.A03(z);
            r4.A01 = true;
        } else {
            throw null;
        }
    }

    public static final void A03(C10271rq r8) {
        ArrayList arrayList;
        K k;
        synchronized (r8) {
            C10411sn r0 = r8.A00;
            int i = r0.A03;
            int i2 = r0.A00;
            C10281rr<K, C10311ru<K, V>> r7 = r8.A03;
            int A002 = r7.A00();
            C10281rr<K, C10311ru<K, V>> r4 = r8.A04;
            int min = Math.min(i, i2 - (A002 - r4.A00()));
            C10411sn r02 = r8.A00;
            int min2 = Math.min(r02.A04, r02.A02 - (r7.A01() - r4.A01()));
            int max = Math.max(min, 0);
            int max2 = Math.max(min2, 0);
            if (r4.A00() > max || r4.A01() > max2) {
                arrayList = new ArrayList();
                while (true) {
                    if (r4.A00() > max || r4.A01() > max2) {
                        synchronized (r4) {
                            LinkedHashMap<K, V> linkedHashMap = r4.A02;
                            if (linkedHashMap.isEmpty()) {
                                k = null;
                            } else {
                                k = linkedHashMap.keySet().iterator().next();
                            }
                        }
                        if (k != null) {
                            r4.A02(k);
                            arrayList.add(r7.A02(k));
                        } else {
                            throw new IllegalStateException(String.format("key is null, but exclusiveEntries count: %d, size: %d", Integer.valueOf(r4.A00()), Integer.valueOf(r4.A01())));
                        }
                    } else {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            r8.A02((C10311ru) it.next());
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
                AnonymousClass1qa A012 = A01(r8, (C10311ru) it2.next());
                if (A012 != null) {
                    A012.close();
                }
            }
            Iterator it3 = arrayList.iterator();
            while (it3.hasNext()) {
                it3.next();
            }
        }
    }

    public static synchronized void A04(C10271rq r5) {
        synchronized (r5) {
            if (r5.A01 + r5.A00.A05 <= SystemClock.uptimeMillis()) {
                r5.A01 = SystemClock.uptimeMillis();
                C10411sn r1 = r5.A06.get();
                AnonymousClass0KU.A02(r1, "mMemoryCacheParamsSupplier returned null");
                r5.A00 = r1;
            }
        }
    }

    @Override // X.AbstractC10301rt
    @Nullable
    public final AnonymousClass1qa<V> A1X(K k, AnonymousClass1qa<V> r11) {
        AnonymousClass1qa<V> r6;
        AnonymousClass1qa r7 = null;
        if (k == null || r11 == null) {
            throw null;
        }
        A04(this);
        synchronized (this) {
            C10281rr<K, C10311ru<K, V>> r5 = this.A04;
            r5.A02(k);
            C10281rr<K, C10311ru<K, V>> r3 = this.A03;
            C10311ru<K, V> A022 = r3.A02(k);
            r6 = null;
            if (A022 != null) {
                A02(A022);
                r7 = A01(this, A022);
            }
            int A4N = this.A05.A4N(r11.A04());
            if (A4N <= this.A00.A01) {
                if (r3.A00() - r5.A00() <= this.A00.A00 - 1 && r3.A01() - r5.A01() <= this.A00.A02 - A4N) {
                    C10311ru<K, V> r0 = new C10311ru<>(k, r11);
                    r3.A03(k, r0);
                    r6 = A00(r0);
                }
            }
        }
        if (r7 != null) {
            r7.close();
        }
        A03(this);
        return r6;
    }

    @Override // X.AbstractC10301rt
    @Nullable
    public final AnonymousClass1qa<V> A2t(K k) {
        V v;
        AnonymousClass1qa<V> r0;
        if (k != null) {
            synchronized (this) {
                this.A04.A02(k);
                C10281rr<K, C10311ru<K, V>> r1 = this.A03;
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

    /* JADX WARN: Incorrect args count in method signature: (LX/1tZ<TV;>;LX/1un;LX/0KW<LX/1sn;>;Lcom/facebook/imagepipeline/cache/CountingMemoryCache$EntryStateObserver<TK;>;)V */
    public C10271rq(AnonymousClass1tZ r3, AbstractC10681un r4, AnonymousClass0KW r5) {
        this.A05 = r3;
        this.A04 = new C10281rr<>(new C10341sS(this, r3));
        this.A03 = new C10281rr<>(new C10341sS(this, r3));
        this.A07 = r4;
        this.A06 = r5;
        Object obj = r5.get();
        AnonymousClass0KU.A02(obj, "mMemoryCacheParamsSupplier returned null");
        this.A00 = (C10411sn) obj;
        this.A01 = SystemClock.uptimeMillis();
    }
}
