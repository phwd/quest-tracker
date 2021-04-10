package X;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public final class hM<K, V> extends AbstractMap<K, V> implements Serializable {
    public static final Comparator<Comparable> A00 = new hR();
    public Comparator<? super K> comparator;
    public hM<K, V>.EntrySet entrySet;
    public final hN<K, V> header = new hN<>();
    public hM<K, V>.KeySet keySet;
    public int modCount = 0;
    public hN<K, V> root;
    public int size = 0;

    public final void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        hN<K, V> hNVar = this.header;
        hNVar.A03 = hNVar;
        hNVar.A01 = hNVar;
    }

    public final boolean containsKey(Object obj) {
        hN hNVar = null;
        if (obj != null) {
            try {
                hNVar = A00(this, obj, false);
            } catch (ClassCastException unused) {
            }
            if (hNVar == null) {
                return false;
            }
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V get(Object obj) {
        hN hNVar = null;
        if (obj != null) {
            try {
                hNVar = A00(this, obj, false);
            } catch (ClassCastException unused) {
            }
        }
        if (hNVar != null) {
            return hNVar.A04;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V remove(Object obj) {
        hN<K, V> hNVar = null;
        if (obj != null) {
            try {
                hNVar = A00(this, obj, false);
            } catch (ClassCastException unused) {
            }
        }
        if (hNVar != null) {
            A06(hNVar, true);
        }
        if (hNVar != null) {
            return hNVar.A04;
        }
        return null;
    }

    public hM() {
        Comparator<Comparable> comparator2 = A00;
        this.comparator = comparator2;
    }

    /* JADX WARN: Incorrect args count in method signature: (TK;Z)LX/hN<TK;TV;>; */
    /* JADX WARNING: Removed duplicated region for block: B:6:0x0011  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x001a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final X.hN A00(X.hM r7, java.lang.Object r8, boolean r9) {
        /*
        // Method dump skipped, instructions count: 119
        */
        throw new UnsupportedOperationException("Method not decompiled: X.hM.A00(X.hM, java.lang.Object, boolean):X.hN");
    }

    private void A01(hN<K, V> hNVar) {
        int i;
        int i2;
        hN<K, V> hNVar2 = hNVar.A05;
        hN<K, V> hNVar3 = hNVar.A06;
        hN<K, V> hNVar4 = hNVar3.A05;
        hN<K, V> hNVar5 = hNVar3.A06;
        hNVar.A06 = hNVar4;
        if (hNVar4 != null) {
            hNVar4.A02 = hNVar;
        }
        A03(hNVar, hNVar3);
        hNVar3.A05 = hNVar;
        hNVar.A02 = hNVar3;
        int i3 = 0;
        if (hNVar2 != null) {
            i = hNVar2.A00;
        } else {
            i = 0;
        }
        if (hNVar4 != null) {
            i2 = hNVar4.A00;
        } else {
            i2 = 0;
        }
        int max = Math.max(i, i2) + 1;
        hNVar.A00 = max;
        if (hNVar5 != null) {
            i3 = hNVar5.A00;
        }
        hNVar3.A00 = Math.max(max, i3) + 1;
    }

    private void A02(hN<K, V> hNVar) {
        int i;
        int i2;
        hN<K, V> hNVar2 = hNVar.A05;
        hN<K, V> hNVar3 = hNVar.A06;
        hN<K, V> hNVar4 = hNVar2.A05;
        hN<K, V> hNVar5 = hNVar2.A06;
        hNVar.A05 = hNVar5;
        if (hNVar5 != null) {
            hNVar5.A02 = hNVar;
        }
        A03(hNVar, hNVar2);
        hNVar2.A06 = hNVar;
        hNVar.A02 = hNVar2;
        int i3 = 0;
        if (hNVar3 != null) {
            i = hNVar3.A00;
        } else {
            i = 0;
        }
        if (hNVar5 != null) {
            i2 = hNVar5.A00;
        } else {
            i2 = 0;
        }
        int max = Math.max(i, i2) + 1;
        hNVar.A00 = max;
        if (hNVar4 != null) {
            i3 = hNVar4.A00;
        }
        hNVar2.A00 = Math.max(max, i3) + 1;
    }

    private void A03(hN<K, V> hNVar, hN<K, V> hNVar2) {
        hN<K, V> hNVar3 = hNVar.A02;
        hNVar.A02 = null;
        if (hNVar2 != null) {
            hNVar2.A02 = hNVar3;
        }
        if (hNVar3 == null) {
            this.root = hNVar2;
        } else if (hNVar3.A05 == hNVar) {
            hNVar3.A05 = hNVar2;
        } else {
            hNVar3.A06 = hNVar2;
        }
    }

    private void A04(hN<K, V> hNVar, boolean z) {
        int i;
        int i2;
        int i3;
        int i4;
        while (hNVar != null) {
            hN<K, V> hNVar2 = hNVar.A05;
            hN<K, V> hNVar3 = hNVar.A06;
            int i5 = 0;
            if (hNVar2 != null) {
                i = hNVar2.A00;
            } else {
                i = 0;
            }
            if (hNVar3 != null) {
                i2 = hNVar3.A00;
            } else {
                i2 = 0;
            }
            int i6 = i - i2;
            if (i6 == -2) {
                hN<K, V> hNVar4 = hNVar3.A05;
                hN<K, V> hNVar5 = hNVar3.A06;
                if (hNVar5 != null) {
                    i4 = hNVar5.A00;
                } else {
                    i4 = 0;
                }
                if (hNVar4 != null) {
                    i5 = hNVar4.A00;
                }
                int i7 = i5 - i4;
                if (i7 != -1 && (i7 != 0 || z)) {
                    A02(hNVar3);
                }
                A01(hNVar);
            } else if (i6 == 2) {
                hN<K, V> hNVar6 = hNVar2.A05;
                hN<K, V> hNVar7 = hNVar2.A06;
                if (hNVar7 != null) {
                    i3 = hNVar7.A00;
                } else {
                    i3 = 0;
                }
                if (hNVar6 != null) {
                    i5 = hNVar6.A00;
                }
                int i8 = i5 - i3;
                if (i8 != 1 && (i8 != 0 || z)) {
                    A01(hNVar2);
                }
                A02(hNVar);
            } else if (i6 == 0) {
                hNVar.A00 = i + 1;
            } else {
                hNVar.A00 = Math.max(i, i2) + 1;
                if (z) {
                    hNVar = hNVar.A02;
                } else {
                    return;
                }
            }
            if (!z) {
                hNVar = hNVar.A02;
            } else {
                return;
            }
        }
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }

    public final void A06(hN<K, V> hNVar, boolean z) {
        int i;
        if (z) {
            hN<K, V> hNVar2 = hNVar.A03;
            hNVar2.A01 = hNVar.A01;
            hNVar.A01.A03 = hNVar2;
        }
        hN<K, V> hNVar3 = hNVar.A05;
        hN<K, V> hNVar4 = hNVar.A06;
        hN<K, V> hNVar5 = hNVar.A02;
        int i2 = 0;
        if (hNVar3 != null) {
            if (hNVar4 != null) {
                if (hNVar3.A00 > hNVar4.A00) {
                    for (hN<K, V> hNVar6 = hNVar3.A06; hNVar6 != null; hNVar6 = hNVar6.A06) {
                        hNVar3 = hNVar6;
                    }
                } else {
                    hNVar3 = hNVar4;
                    for (hN<K, V> hNVar7 = hNVar4.A05; hNVar7 != null; hNVar7 = hNVar7.A05) {
                        hNVar3 = hNVar7;
                    }
                }
                A06(hNVar3, false);
                hN<K, V> hNVar8 = hNVar.A05;
                if (hNVar8 != null) {
                    i = hNVar8.A00;
                    hNVar3.A05 = hNVar8;
                    hNVar8.A02 = hNVar3;
                    hNVar.A05 = null;
                } else {
                    i = 0;
                }
                hN<K, V> hNVar9 = hNVar.A06;
                if (hNVar9 != null) {
                    i2 = hNVar9.A00;
                    hNVar3.A06 = hNVar9;
                    hNVar9.A02 = hNVar3;
                    hNVar.A06 = null;
                }
                hNVar3.A00 = Math.max(i, i2) + 1;
                A03(hNVar, hNVar3);
                return;
            }
            A03(hNVar, hNVar3);
            hNVar.A05 = null;
        } else if (hNVar4 != null) {
            A03(hNVar, hNVar4);
            hNVar.A06 = null;
        } else {
            A03(hNVar, null);
        }
        A04(hNVar5, false);
        this.size--;
        this.modCount++;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        hM<K, V>.EntrySet entrySet2 = this.entrySet;
        if (entrySet2 != null) {
            return entrySet2;
        }
        hQ hQVar = new hQ(this);
        this.entrySet = hQVar;
        return hQVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<K> keySet() {
        hM<K, V>.KeySet keySet2 = this.keySet;
        if (keySet2 != null) {
            return keySet2;
        }
        hP hPVar = new hP(this);
        this.keySet = hPVar;
        return hPVar;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final V put(K k, V v) {
        if (k != null) {
            hN A002 = A00(this, k, true);
            V v2 = A002.A04;
            A002.A04 = v;
            return v2;
        }
        throw new NullPointerException("key == null");
    }

    public final hN<K, V> A05(Map.Entry<?, ?> entry) {
        Object key = entry.getKey();
        hN<K, V> hNVar = null;
        if (key == null) {
            return null;
        }
        try {
            hNVar = A00(this, key, false);
        } catch (ClassCastException unused) {
        }
        if (hNVar == null) {
            return null;
        }
        V v = hNVar.A04;
        Object value = entry.getValue();
        if (v == value || (v != null && v.equals(value))) {
            return hNVar;
        }
        return null;
    }

    public final int size() {
        return this.size;
    }
}
