package X;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/* renamed from: X.142  reason: invalid class name */
public final class AnonymousClass142<K, V> extends AbstractMap<K, V> implements Serializable {
    public static final Comparator<Comparable> A00 = new C057813x();
    public Comparator<? super K> comparator;
    public AnonymousClass142<K, V>.EntrySet entrySet;
    public final AnonymousClass141<K, V> header = new AnonymousClass141<>();
    public AnonymousClass142<K, V>.KeySet keySet;
    public int modCount = 0;
    public AnonymousClass141<K, V> root;
    public int size = 0;

    public final void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        AnonymousClass141<K, V> r0 = this.header;
        r0.A03 = r0;
        r0.A01 = r0;
    }

    public final boolean containsKey(Object obj) {
        AnonymousClass141 r1 = null;
        if (obj != null) {
            try {
                r1 = A00(this, obj, false);
            } catch (ClassCastException unused) {
            }
            if (r1 == null) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V get(Object obj) {
        AnonymousClass141 r1 = null;
        if (obj != null) {
            try {
                r1 = A00(this, obj, false);
            } catch (ClassCastException unused) {
            }
        }
        if (r1 != null) {
            return r1.A04;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        AnonymousClass141<K, V> r1 = null;
        if (obj != null) {
            try {
                r1 = A00(this, obj, false);
            } catch (ClassCastException unused) {
            }
        }
        if (r1 != null) {
            A06(r1, true);
        }
        if (r1 != null) {
            return r1.A04;
        }
        return null;
    }

    public AnonymousClass142() {
        Comparator<Comparable> comparator2 = A00;
        this.comparator = comparator2;
    }

    /* JADX WARN: Incorrect args count in method signature: (TK;Z)LX/141<TK;TV;>; */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0011  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final X.AnonymousClass141 A00(X.AnonymousClass142 r7, java.lang.Object r8, boolean r9) {
        /*
        // Method dump skipped, instructions count: 119
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass142.A00(X.142, java.lang.Object, boolean):X.141");
    }

    private void A01(AnonymousClass141<K, V> r6) {
        int i;
        int i2;
        AnonymousClass141<K, V> r1 = r6.A05;
        AnonymousClass141<K, V> r4 = r6.A06;
        AnonymousClass141<K, V> r0 = r4.A05;
        AnonymousClass141<K, V> r3 = r4.A06;
        r6.A06 = r0;
        if (r0 != null) {
            r0.A02 = r6;
        }
        A03(r6, r4);
        r4.A05 = r6;
        r6.A02 = r4;
        int i3 = 0;
        if (r1 != null) {
            i = r1.A00;
        } else {
            i = 0;
        }
        if (r0 != null) {
            i2 = r0.A00;
        } else {
            i2 = 0;
        }
        int max = Math.max(i, i2) + 1;
        r6.A00 = max;
        if (r3 != null) {
            i3 = r3.A00;
        }
        r4.A00 = Math.max(max, i3) + 1;
    }

    private void A02(AnonymousClass141<K, V> r6) {
        int i;
        int i2;
        AnonymousClass141<K, V> r4 = r6.A05;
        AnonymousClass141<K, V> r1 = r6.A06;
        AnonymousClass141<K, V> r3 = r4.A05;
        AnonymousClass141<K, V> r0 = r4.A06;
        r6.A05 = r0;
        if (r0 != null) {
            r0.A02 = r6;
        }
        A03(r6, r4);
        r4.A06 = r6;
        r6.A02 = r4;
        int i3 = 0;
        if (r1 != null) {
            i = r1.A00;
        } else {
            i = 0;
        }
        if (r0 != null) {
            i2 = r0.A00;
        } else {
            i2 = 0;
        }
        int max = Math.max(i, i2) + 1;
        r6.A00 = max;
        if (r3 != null) {
            i3 = r3.A00;
        }
        r4.A00 = Math.max(max, i3) + 1;
    }

    private void A03(AnonymousClass141<K, V> r3, AnonymousClass141<K, V> r4) {
        AnonymousClass141<K, V> r1 = r3.A02;
        r3.A02 = null;
        if (r4 != null) {
            r4.A02 = r1;
        }
        if (r1 == null) {
            this.root = r4;
        } else if (r1.A05 == r3) {
            r1.A05 = r4;
        } else {
            r1.A06 = r4;
        }
    }

    private void A04(AnonymousClass141<K, V> r8, boolean z) {
        int i;
        int i2;
        int i3;
        int i4;
        while (r8 != null) {
            AnonymousClass141<K, V> r6 = r8.A05;
            AnonymousClass141<K, V> r2 = r8.A06;
            int i5 = 0;
            if (r6 != null) {
                i = r6.A00;
            } else {
                i = 0;
            }
            if (r2 != null) {
                i2 = r2.A00;
            } else {
                i2 = 0;
            }
            int i6 = i - i2;
            if (i6 == -2) {
                AnonymousClass141<K, V> r1 = r2.A05;
                AnonymousClass141<K, V> r0 = r2.A06;
                if (r0 != null) {
                    i4 = r0.A00;
                } else {
                    i4 = 0;
                }
                if (r1 != null) {
                    i5 = r1.A00;
                }
                int i7 = i5 - i4;
                if (i7 != -1 && (i7 != 0 || z)) {
                    A02(r2);
                }
                A01(r8);
            } else if (i6 == 2) {
                AnonymousClass141<K, V> r12 = r6.A05;
                AnonymousClass141<K, V> r02 = r6.A06;
                if (r02 != null) {
                    i3 = r02.A00;
                } else {
                    i3 = 0;
                }
                if (r12 != null) {
                    i5 = r12.A00;
                }
                int i8 = i5 - i3;
                if (i8 != 1 && (i8 != 0 || z)) {
                    A01(r6);
                }
                A02(r8);
            } else if (i6 == 0) {
                r8.A00 = i + 1;
            } else {
                r8.A00 = Math.max(i, i2) + 1;
                if (z) {
                    r8 = r8.A02;
                } else {
                    return;
                }
            }
            if (!z) {
                r8 = r8.A02;
            } else {
                return;
            }
        }
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }

    public final void A06(AnonymousClass141<K, V> r7, boolean z) {
        int i;
        if (z) {
            AnonymousClass141<K, V> r1 = r7.A03;
            r1.A01 = r7.A01;
            r7.A01.A03 = r1;
        }
        AnonymousClass141<K, V> r4 = r7.A05;
        AnonymousClass141<K, V> r5 = r7.A06;
        AnonymousClass141<K, V> r0 = r7.A02;
        int i2 = 0;
        if (r4 != null) {
            if (r5 != null) {
                if (r4.A00 > r5.A00) {
                    for (AnonymousClass141<K, V> r12 = r4.A06; r12 != null; r12 = r12.A06) {
                        r4 = r12;
                    }
                } else {
                    r4 = r5;
                    for (AnonymousClass141<K, V> r13 = r5.A05; r13 != null; r13 = r13.A05) {
                        r4 = r13;
                    }
                }
                A06(r4, false);
                AnonymousClass141<K, V> r02 = r7.A05;
                if (r02 != null) {
                    i = r02.A00;
                    r4.A05 = r02;
                    r02.A02 = r4;
                    r7.A05 = null;
                } else {
                    i = 0;
                }
                AnonymousClass141<K, V> r03 = r7.A06;
                if (r03 != null) {
                    i2 = r03.A00;
                    r4.A06 = r03;
                    r03.A02 = r4;
                    r7.A06 = null;
                }
                r4.A00 = Math.max(i, i2) + 1;
                A03(r7, r4);
                return;
            }
            A03(r7, r4);
            r7.A05 = null;
        } else if (r5 != null) {
            A03(r7, r5);
            r7.A06 = null;
        } else {
            A03(r7, null);
        }
        A04(r0, false);
        this.size--;
        this.modCount++;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        AnonymousClass142<K, V>.EntrySet entrySet2 = this.entrySet;
        if (entrySet2 != null) {
            return entrySet2;
        }
        C057913y r0 = new C057913y(this);
        this.entrySet = r0;
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<K> keySet() {
        AnonymousClass142<K, V>.KeySet keySet2 = this.keySet;
        if (keySet2 != null) {
            return keySet2;
        }
        C058013z r0 = new C058013z(this);
        this.keySet = r0;
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V put(K k, V v) {
        if (k != null) {
            AnonymousClass141 A002 = A00(this, k, true);
            V v2 = A002.A04;
            A002.A04 = v;
            return v2;
        }
        throw new NullPointerException("key == null");
    }

    public final AnonymousClass141<K, V> A05(Map.Entry<?, ?> entry) {
        Object key = entry.getKey();
        AnonymousClass141<K, V> r2 = null;
        if (key == null) {
            return null;
        }
        try {
            r2 = A00(this, key, false);
        } catch (ClassCastException unused) {
        }
        if (r2 == null) {
            return null;
        }
        V v = r2.A04;
        Object value = entry.getValue();
        if (v == value || (v != null && v.equals(value))) {
            return r2;
        }
        return null;
    }

    public final int size() {
        return this.size;
    }
}
