package X;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class VD<K, V> extends AbstractMap<K, V> implements Serializable {
    public static final Comparator<Comparable> A00 = new C0191Uu();
    public Comparator<? super K> comparator;
    public VD<K, V>.EntrySet entrySet;
    public final VC<K, V> header = new VC<>();
    public VD<K, V>.KeySet keySet;
    public int modCount = 0;
    public VC<K, V> root;
    public int size = 0;

    public final void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        VC<K, V> vc = this.header;
        vc.A03 = vc;
        vc.A01 = vc;
    }

    public final boolean containsKey(Object obj) {
        VC vc = null;
        if (obj != null) {
            try {
                vc = A00(this, obj, false);
            } catch (ClassCastException unused) {
            }
            if (vc == null) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V get(Object obj) {
        VC vc = null;
        if (obj != null) {
            try {
                vc = A00(this, obj, false);
            } catch (ClassCastException unused) {
            }
        }
        if (vc != null) {
            return vc.A04;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        VC<K, V> vc = null;
        if (obj != null) {
            try {
                vc = A00(this, obj, false);
            } catch (ClassCastException unused) {
            }
        }
        if (vc != null) {
            A06(vc, true);
        }
        if (vc != null) {
            return vc.A04;
        }
        return null;
    }

    public VD() {
        Comparator<Comparable> comparator2 = A00;
        this.comparator = comparator2;
    }

    /* JADX WARN: Incorrect args count in method signature: (TK;Z)LX/VC<TK;TV;>; */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0011  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final X.VC A00(X.VD r7, java.lang.Object r8, boolean r9) {
        /*
        // Method dump skipped, instructions count: 119
        */
        throw new UnsupportedOperationException("Method not decompiled: X.VD.A00(X.VD, java.lang.Object, boolean):X.VC");
    }

    private void A01(VC<K, V> vc) {
        int i;
        int i2;
        VC<K, V> vc2 = vc.A05;
        VC<K, V> vc3 = vc.A06;
        VC<K, V> vc4 = vc3.A05;
        VC<K, V> vc5 = vc3.A06;
        vc.A06 = vc4;
        if (vc4 != null) {
            vc4.A02 = vc;
        }
        A03(vc, vc3);
        vc3.A05 = vc;
        vc.A02 = vc3;
        int i3 = 0;
        if (vc2 != null) {
            i = vc2.A00;
        } else {
            i = 0;
        }
        if (vc4 != null) {
            i2 = vc4.A00;
        } else {
            i2 = 0;
        }
        int max = Math.max(i, i2) + 1;
        vc.A00 = max;
        if (vc5 != null) {
            i3 = vc5.A00;
        }
        vc3.A00 = Math.max(max, i3) + 1;
    }

    private void A02(VC<K, V> vc) {
        int i;
        int i2;
        VC<K, V> vc2 = vc.A05;
        VC<K, V> vc3 = vc.A06;
        VC<K, V> vc4 = vc2.A05;
        VC<K, V> vc5 = vc2.A06;
        vc.A05 = vc5;
        if (vc5 != null) {
            vc5.A02 = vc;
        }
        A03(vc, vc2);
        vc2.A06 = vc;
        vc.A02 = vc2;
        int i3 = 0;
        if (vc3 != null) {
            i = vc3.A00;
        } else {
            i = 0;
        }
        if (vc5 != null) {
            i2 = vc5.A00;
        } else {
            i2 = 0;
        }
        int max = Math.max(i, i2) + 1;
        vc.A00 = max;
        if (vc4 != null) {
            i3 = vc4.A00;
        }
        vc2.A00 = Math.max(max, i3) + 1;
    }

    private void A03(VC<K, V> vc, VC<K, V> vc2) {
        VC<K, V> vc3 = vc.A02;
        vc.A02 = null;
        if (vc2 != null) {
            vc2.A02 = vc3;
        }
        if (vc3 == null) {
            this.root = vc2;
        } else if (vc3.A05 == vc) {
            vc3.A05 = vc2;
        } else {
            vc3.A06 = vc2;
        }
    }

    private void A04(VC<K, V> vc, boolean z) {
        int i;
        int i2;
        int i3;
        int i4;
        while (vc != null) {
            VC<K, V> vc2 = vc.A05;
            VC<K, V> vc3 = vc.A06;
            int i5 = 0;
            if (vc2 != null) {
                i = vc2.A00;
            } else {
                i = 0;
            }
            if (vc3 != null) {
                i2 = vc3.A00;
            } else {
                i2 = 0;
            }
            int i6 = i - i2;
            if (i6 == -2) {
                VC<K, V> vc4 = vc3.A05;
                VC<K, V> vc5 = vc3.A06;
                if (vc5 != null) {
                    i4 = vc5.A00;
                } else {
                    i4 = 0;
                }
                if (vc4 != null) {
                    i5 = vc4.A00;
                }
                int i7 = i5 - i4;
                if (i7 != -1 && (i7 != 0 || z)) {
                    A02(vc3);
                }
                A01(vc);
            } else if (i6 == 2) {
                VC<K, V> vc6 = vc2.A05;
                VC<K, V> vc7 = vc2.A06;
                if (vc7 != null) {
                    i3 = vc7.A00;
                } else {
                    i3 = 0;
                }
                if (vc6 != null) {
                    i5 = vc6.A00;
                }
                int i8 = i5 - i3;
                if (i8 != 1 && (i8 != 0 || z)) {
                    A01(vc2);
                }
                A02(vc);
            } else if (i6 == 0) {
                vc.A00 = i + 1;
            } else {
                vc.A00 = Math.max(i, i2) + 1;
                if (z) {
                    vc = vc.A02;
                } else {
                    return;
                }
            }
            if (!z) {
                vc = vc.A02;
            } else {
                return;
            }
        }
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }

    public final void A06(VC<K, V> vc, boolean z) {
        int i;
        if (z) {
            VC<K, V> vc2 = vc.A03;
            vc2.A01 = vc.A01;
            vc.A01.A03 = vc2;
        }
        VC<K, V> vc3 = vc.A05;
        VC<K, V> vc4 = vc.A06;
        VC<K, V> vc5 = vc.A02;
        int i2 = 0;
        if (vc3 != null) {
            if (vc4 != null) {
                if (vc3.A00 > vc4.A00) {
                    for (VC<K, V> vc6 = vc3.A06; vc6 != null; vc6 = vc6.A06) {
                        vc3 = vc6;
                    }
                } else {
                    vc3 = vc4;
                    for (VC<K, V> vc7 = vc4.A05; vc7 != null; vc7 = vc7.A05) {
                        vc3 = vc7;
                    }
                }
                A06(vc3, false);
                VC<K, V> vc8 = vc.A05;
                if (vc8 != null) {
                    i = vc8.A00;
                    vc3.A05 = vc8;
                    vc8.A02 = vc3;
                    vc.A05 = null;
                } else {
                    i = 0;
                }
                VC<K, V> vc9 = vc.A06;
                if (vc9 != null) {
                    i2 = vc9.A00;
                    vc3.A06 = vc9;
                    vc9.A02 = vc3;
                    vc.A06 = null;
                }
                vc3.A00 = Math.max(i, i2) + 1;
                A03(vc, vc3);
                return;
            }
            A03(vc, vc3);
            vc.A05 = null;
        } else if (vc4 != null) {
            A03(vc, vc4);
            vc.A06 = null;
        } else {
            A03(vc, null);
        }
        A04(vc5, false);
        this.size--;
        this.modCount++;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        VD<K, V>.EntrySet entrySet2 = this.entrySet;
        if (entrySet2 != null) {
            return entrySet2;
        }
        V1 v1 = new V1(this);
        this.entrySet = v1;
        return v1;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<K> keySet() {
        VD<K, V>.KeySet keySet2 = this.keySet;
        if (keySet2 != null) {
            return keySet2;
        }
        VA va = new VA(this);
        this.keySet = va;
        return va;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V put(K k, V v) {
        if (k != null) {
            VC A002 = A00(this, k, true);
            V v2 = A002.A04;
            A002.A04 = v;
            return v2;
        }
        throw new NullPointerException("key == null");
    }

    public final VC<K, V> A05(Map.Entry<?, ?> entry) {
        Object key = entry.getKey();
        VC<K, V> vc = null;
        if (key == null) {
            return null;
        }
        try {
            vc = A00(this, key, false);
        } catch (ClassCastException unused) {
        }
        if (vc == null) {
            return null;
        }
        V v = vc.A04;
        Object value = entry.getValue();
        if (v == value || (v != null && v.equals(value))) {
            return vc;
        }
        return null;
    }

    public final int size() {
        return this.size;
    }
}
