package com.google.common.collect;

import X.AbstractC06730pi;
import X.AnonymousClass0e5;
import X.AnonymousClass0e6;
import X.AnonymousClass0e7;
import X.AnonymousClass0e9;
import X.AnonymousClass0p2;
import X.C06710pf;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.j2objc.annotations.RetainedWith;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.compatqual.MonotonicNonNullDecl;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public final class HashBiMap<K, V> extends AbstractMap<K, V> implements AnonymousClass0p2<K, V>, Serializable {
    public transient int A00;
    @NullableDecl
    public transient int A01;
    public transient int A02;
    @RetainedWith
    @MonotonicNonNullDecl
    public transient AnonymousClass0p2<V, K> A03;
    public transient Set<V> A04;
    public transient int[] A05;
    public transient int[] A06;
    public transient int[] A07;
    public transient int[] A08;
    public transient int[] A09;
    public transient int[] A0A;
    public transient K[] A0B;
    public transient V[] A0C;
    @NullableDecl
    public transient int A0D;
    public transient Set<Map.Entry<K, V>> A0E;
    public transient Set<K> A0F;

    public static class Inverse<K, V> extends AbstractMap<V, K> implements AnonymousClass0p2<V, K>, Serializable {
        public transient Set<Map.Entry<V, K>> A00;
        public final HashBiMap<K, V> forward;

        @Override // X.AnonymousClass0p2
        @CanIgnoreReturnValue
        @NullableDecl
        public final K A2r(@NullableDecl V v, @NullableDecl K k) {
            return this.forward.A0D(v, k, true);
        }

        public final void clear() {
            this.forward.clear();
        }

        public final boolean containsKey(@NullableDecl Object obj) {
            return this.forward.containsValue(obj);
        }

        public final boolean containsValue(@NullableDecl Object obj) {
            return this.forward.containsKey(obj);
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final Set<Map.Entry<V, K>> entrySet() {
            Set<Map.Entry<V, K>> set = this.A00;
            if (set != null) {
                return set;
            }
            AnonymousClass0e7 r1 = new AnonymousClass0e7(this.forward);
            this.A00 = r1;
            return r1;
        }

        @Override // java.util.AbstractMap, java.util.Map
        @NullableDecl
        public final K get(@NullableDecl Object obj) {
            HashBiMap<K, V> hashBiMap = this.forward;
            int A0C = hashBiMap.A0C(obj, C06710pf.A02(obj));
            if (A0C == -1) {
                return null;
            }
            return hashBiMap.A0B[A0C];
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final Set<V> keySet() {
            HashBiMap<K, V> hashBiMap = this.forward;
            Set<V> set = hashBiMap.A04;
            if (set != null) {
                return set;
            }
            AnonymousClass0e5 r0 = new AnonymousClass0e5(hashBiMap);
            hashBiMap.A04 = r0;
            return r0;
        }

        @Override // java.util.AbstractMap, java.util.Map
        @CanIgnoreReturnValue
        @NullableDecl
        public final K put(@NullableDecl V v, @NullableDecl K k) {
            return this.forward.A0D(v, k, false);
        }

        @Override // java.util.AbstractMap, java.util.Map
        @CanIgnoreReturnValue
        @NullableDecl
        public final K remove(@NullableDecl Object obj) {
            HashBiMap<K, V> hashBiMap = this.forward;
            int A02 = C06710pf.A02(obj);
            int A0C = hashBiMap.A0C(obj, A02);
            if (A0C == -1) {
                return null;
            }
            K k = hashBiMap.A0B[A0C];
            hashBiMap.A0F(A0C, A02);
            return k;
        }

        public final int size() {
            return this.forward.A00;
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final Collection values() {
            return this.forward.keySet();
        }

        public Inverse(HashBiMap<K, V> hashBiMap) {
            this.forward = hashBiMap;
        }

        @GwtIncompatible("serialization")
        private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
            objectInputStream.defaultReadObject();
            this.forward.A03 = this;
        }

        @Override // X.AnonymousClass0p2
        public final AnonymousClass0p2<K, V> A4s() {
            return this.forward;
        }
    }

    private void A03(int i, int i2) {
        boolean z = false;
        if (i != -1) {
            z = true;
        }
        Preconditions.checkArgument(z);
        int[] iArr = this.A05;
        int length = i2 & (iArr.length - 1);
        int i3 = iArr[length];
        if (i3 == i) {
            int[] iArr2 = this.A07;
            iArr[length] = iArr2[i];
            iArr2[i] = -1;
            return;
        }
        int[] iArr3 = this.A07;
        int i4 = iArr3[i3];
        while (true) {
            i3 = i4;
            if (i4 == -1) {
                StringBuilder sb = new StringBuilder("Expected to find entry with key ");
                sb.append((Object) this.A0B[i]);
                throw new AssertionError(sb.toString());
            } else if (i4 == i) {
                iArr3[i3] = iArr3[i];
                iArr3[i] = -1;
                return;
            } else {
                i4 = iArr3[i4];
            }
        }
    }

    private void A04(int i, int i2) {
        boolean z = false;
        if (i != -1) {
            z = true;
        }
        Preconditions.checkArgument(z);
        int length = i2 & (this.A05.length - 1);
        int[] iArr = this.A06;
        int i3 = iArr[length];
        if (i3 == i) {
            int[] iArr2 = this.A08;
            iArr[length] = iArr2[i];
            iArr2[i] = -1;
            return;
        }
        int[] iArr3 = this.A08;
        int i4 = iArr3[i3];
        while (true) {
            i3 = i4;
            if (i4 == -1) {
                StringBuilder sb = new StringBuilder("Expected to find entry with value ");
                sb.append((Object) this.A0C[i]);
                throw new AssertionError(sb.toString());
            } else if (i4 == i) {
                iArr3[i3] = iArr3[i];
                iArr3[i] = -1;
                return;
            } else {
                i4 = iArr3[i4];
            }
        }
    }

    private void A05(int i, int i2) {
        boolean z = false;
        if (i != -1) {
            z = true;
        }
        Preconditions.checkArgument(z);
        int[] iArr = this.A05;
        int length = i2 & (iArr.length - 1);
        this.A07[i] = iArr[length];
        iArr[length] = i;
    }

    private void A06(int i, int i2) {
        boolean z = false;
        if (i != -1) {
            z = true;
        }
        Preconditions.checkArgument(z);
        int length = i2 & (this.A05.length - 1);
        int[] iArr = this.A08;
        int[] iArr2 = this.A06;
        iArr[i] = iArr2[length];
        iArr2[length] = i;
    }

    private void A07(int i, int i2) {
        if (i == -2) {
            this.A01 = i2;
        } else {
            this.A09[i] = i2;
        }
        if (i2 == -2) {
            this.A0D = i;
        } else {
            this.A0A[i2] = i;
        }
    }

    private void A08(int i, int i2, int i3) {
        boolean z = false;
        if (i != -1) {
            z = true;
        }
        Preconditions.checkArgument(z);
        A03(i, i2);
        A04(i, i3);
        int[] iArr = this.A0A;
        int i4 = iArr[i];
        int[] iArr2 = this.A09;
        A07(i4, iArr2[i]);
        int i5 = this.A00 - 1;
        if (i5 != i) {
            int i6 = iArr[i5];
            int i7 = iArr2[i5];
            A07(i6, i);
            A07(i, i7);
            K[] kArr = this.A0B;
            K k = kArr[i5];
            V[] vArr = this.A0C;
            V v = vArr[i5];
            kArr[i] = k;
            vArr[i] = v;
            int A022 = C06710pf.A02(k);
            int[] iArr3 = this.A05;
            int length = A022 & (iArr3.length - 1);
            int i8 = iArr3[length];
            if (i8 == i5) {
                iArr3[length] = i;
            } else {
                int[] iArr4 = this.A07;
                int i9 = iArr4[i8];
                while (true) {
                    i8 = i9;
                    if (i9 == i5) {
                        break;
                    }
                    i9 = iArr4[i9];
                }
                iArr4[i8] = i;
            }
            int[] iArr5 = this.A07;
            iArr5[i] = iArr5[i5];
            iArr5[i5] = -1;
            int A023 = C06710pf.A02(v) & (this.A05.length - 1);
            int[] iArr6 = this.A06;
            int i10 = iArr6[A023];
            if (i10 == i5) {
                iArr6[A023] = i;
            } else {
                int[] iArr7 = this.A08;
                int i11 = iArr7[i10];
                while (true) {
                    i10 = i11;
                    if (i11 == i5) {
                        break;
                    }
                    i11 = iArr7[i11];
                }
                iArr7[i10] = i;
            }
            int[] iArr8 = this.A08;
            iArr8[i] = iArr8[i5];
            iArr8[i5] = -1;
        }
        K[] kArr2 = this.A0B;
        int i12 = this.A00;
        int i13 = i12 - 1;
        kArr2[i13] = null;
        this.A0C[i13] = null;
        this.A00 = i12 - 1;
        this.A02++;
    }

    /* JADX WARN: Incorrect args count in method signature: (ITK;Z)V */
    /* JADX DEBUG: Multi-variable search result rejected for r0v10, resolved type: K[] */
    /* JADX WARN: Multi-variable type inference failed */
    public static void A09(HashBiMap hashBiMap, @NullableDecl int i, Object obj, boolean z) {
        int i2;
        boolean z2 = false;
        if (i != -1) {
            z2 = true;
        }
        Preconditions.checkArgument(z2);
        int A022 = C06710pf.A02(obj);
        int A0B2 = hashBiMap.A0B(obj, A022);
        int i3 = hashBiMap.A0D;
        if (A0B2 == -1) {
            i2 = -2;
        } else if (z) {
            i3 = hashBiMap.A0A[A0B2];
            i2 = hashBiMap.A09[A0B2];
            hashBiMap.A0E(A0B2, A022);
            if (i == hashBiMap.A00) {
                i = A0B2;
            }
        } else {
            StringBuilder sb = new StringBuilder("Key already present in map: ");
            sb.append(obj);
            throw new IllegalArgumentException(sb.toString());
        }
        if (i3 == i) {
            i3 = hashBiMap.A0A[i];
        } else if (i3 == hashBiMap.A00) {
            i3 = A0B2;
        }
        if (i2 == i) {
            A0B2 = hashBiMap.A09[i];
        } else if (i2 != hashBiMap.A00) {
            A0B2 = i2;
        }
        hashBiMap.A07(hashBiMap.A0A[i], hashBiMap.A09[i]);
        hashBiMap.A03(i, C06710pf.A02(hashBiMap.A0B[i]));
        hashBiMap.A0B[i] = obj;
        hashBiMap.A05(i, C06710pf.A02(obj));
        hashBiMap.A07(i3, i);
        hashBiMap.A07(i, A0B2);
    }

    /* JADX WARN: Incorrect args count in method signature: (ITV;Z)V */
    /* JADX DEBUG: Multi-variable search result rejected for r0v5, resolved type: V[] */
    /* JADX WARN: Multi-variable type inference failed */
    public static void A0A(HashBiMap hashBiMap, @NullableDecl int i, Object obj, boolean z) {
        boolean z2 = false;
        if (i != -1) {
            z2 = true;
        }
        Preconditions.checkArgument(z2);
        int A022 = C06710pf.A02(obj);
        int A0C2 = hashBiMap.A0C(obj, A022);
        if (A0C2 != -1) {
            if (z) {
                hashBiMap.A0F(A0C2, A022);
                if (i == hashBiMap.A00) {
                    i = A0C2;
                }
            } else {
                StringBuilder sb = new StringBuilder("Value already present in map: ");
                sb.append(obj);
                throw new IllegalArgumentException(sb.toString());
            }
        }
        hashBiMap.A04(i, C06710pf.A02(hashBiMap.A0C[i]));
        hashBiMap.A0C[i] = obj;
        hashBiMap.A06(i, A022);
    }

    @Override // X.AnonymousClass0p2
    @CanIgnoreReturnValue
    @NullableDecl
    public final V A2r(@NullableDecl K k, @NullableDecl V v) {
        return A00(k, v, true);
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    public final V put(@NullableDecl K k, @NullableDecl V v) {
        return A00(k, v, false);
    }

    private final void A01() {
        int A012 = C06710pf.A01(16, 1.0d);
        this.A00 = 0;
        this.A0B = (K[]) new Object[16];
        this.A0C = (V[]) new Object[16];
        int[] iArr = new int[A012];
        Arrays.fill(iArr, -1);
        this.A05 = iArr;
        int[] iArr2 = new int[A012];
        Arrays.fill(iArr2, -1);
        this.A06 = iArr2;
        int[] iArr3 = new int[16];
        Arrays.fill(iArr3, -1);
        this.A07 = iArr3;
        int[] iArr4 = new int[16];
        Arrays.fill(iArr4, -1);
        this.A08 = iArr4;
        this.A01 = -2;
        this.A0D = -2;
        int[] iArr5 = new int[16];
        Arrays.fill(iArr5, -1);
        this.A0A = iArr5;
        int[] iArr6 = new int[16];
        Arrays.fill(iArr6, -1);
        this.A09 = iArr6;
    }

    private void A02(int i) {
        int length = this.A07.length;
        if (length < i) {
            int A012 = AbstractC06730pi.A01(length, i);
            this.A0B = (K[]) Arrays.copyOf(this.A0B, A012);
            this.A0C = (V[]) Arrays.copyOf(this.A0C, A012);
            int[] iArr = this.A07;
            int length2 = iArr.length;
            int[] copyOf = Arrays.copyOf(iArr, A012);
            Arrays.fill(copyOf, length2, A012, -1);
            this.A07 = copyOf;
            int[] iArr2 = this.A08;
            int length3 = iArr2.length;
            int[] copyOf2 = Arrays.copyOf(iArr2, A012);
            Arrays.fill(copyOf2, length3, A012, -1);
            this.A08 = copyOf2;
            int[] iArr3 = this.A0A;
            int length4 = iArr3.length;
            int[] copyOf3 = Arrays.copyOf(iArr3, A012);
            Arrays.fill(copyOf3, length4, A012, -1);
            this.A0A = copyOf3;
            int[] iArr4 = this.A09;
            int length5 = iArr4.length;
            int[] copyOf4 = Arrays.copyOf(iArr4, A012);
            Arrays.fill(copyOf4, length5, A012, -1);
            this.A09 = copyOf4;
        }
        if (this.A05.length < i) {
            int A013 = C06710pf.A01(i, 1.0d);
            int[] iArr5 = new int[A013];
            Arrays.fill(iArr5, -1);
            this.A05 = iArr5;
            int[] iArr6 = new int[A013];
            Arrays.fill(iArr6, -1);
            this.A06 = iArr6;
            for (int i2 = 0; i2 < this.A00; i2++) {
                int A022 = C06710pf.A02(this.A0B[i2]);
                int[] iArr7 = this.A05;
                int length6 = A022 & (iArr7.length - 1);
                this.A07[i2] = iArr7[length6];
                iArr7[length6] = i2;
                int A023 = C06710pf.A02(this.A0C[i2]) & (this.A05.length - 1);
                int[] iArr8 = this.A08;
                int[] iArr9 = this.A06;
                iArr8[i2] = iArr9[A023];
                iArr9[A023] = i2;
            }
        }
    }

    public final int A0B(@NullableDecl Object obj, int i) {
        int[] iArr = this.A05;
        int[] iArr2 = this.A07;
        K[] kArr = this.A0B;
        for (int i2 = iArr[i & (iArr.length - 1)]; i2 != -1; i2 = iArr2[i2]) {
            if (Objects.equal(kArr[i2], obj)) {
                return i2;
            }
        }
        return -1;
    }

    public final int A0C(@NullableDecl Object obj, int i) {
        int[] iArr = this.A06;
        int[] iArr2 = this.A08;
        V[] vArr = this.A0C;
        for (int i2 = iArr[i & (this.A05.length - 1)]; i2 != -1; i2 = iArr2[i2]) {
            if (Objects.equal(vArr[i2], obj)) {
                return i2;
            }
        }
        return -1;
    }

    public final void A0E(int i, int i2) {
        A08(i, i2, C06710pf.A02(this.A0C[i]));
    }

    public final void A0F(int i, int i2) {
        A08(i, C06710pf.A02(this.A0B[i]), i2);
    }

    @Override // X.AnonymousClass0p2
    public final AnonymousClass0p2<V, K> A4s() {
        AnonymousClass0p2<V, K> r0 = this.A03;
        if (r0 != null) {
            return r0;
        }
        Inverse inverse = new Inverse(this);
        this.A03 = inverse;
        return inverse;
    }

    public final void clear() {
        Arrays.fill(this.A0B, 0, this.A00, (Object) null);
        Arrays.fill(this.A0C, 0, this.A00, (Object) null);
        Arrays.fill(this.A05, -1);
        Arrays.fill(this.A06, -1);
        Arrays.fill(this.A07, 0, this.A00, -1);
        Arrays.fill(this.A08, 0, this.A00, -1);
        Arrays.fill(this.A0A, 0, this.A00, -1);
        Arrays.fill(this.A09, 0, this.A00, -1);
        this.A00 = 0;
        this.A01 = -2;
        this.A0D = -2;
        this.A02++;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.A0E;
        if (set != null) {
            return set;
        }
        AnonymousClass0e9 r0 = new AnonymousClass0e9(this);
        this.A0E = r0;
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<K> keySet() {
        Set<K> set = this.A0F;
        if (set != null) {
            return set;
        }
        AnonymousClass0e6 r0 = new AnonymousClass0e6(this);
        this.A0F = r0;
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Collection values() {
        Set<V> set = this.A04;
        if (set != null) {
            return set;
        }
        AnonymousClass0e5 r0 = new AnonymousClass0e5(this);
        this.A04 = r0;
        return r0;
    }

    public HashBiMap() {
        A01();
    }

    @NullableDecl
    private final V A00(@NullableDecl K k, @NullableDecl V v, boolean z) {
        int A022 = C06710pf.A02(k);
        int A0B2 = A0B(k, A022);
        if (A0B2 != -1) {
            V v2 = this.A0C[A0B2];
            if (Objects.equal(v2, v)) {
                return v;
            }
            A0A(this, A0B2, v, z);
            return v2;
        }
        int A023 = C06710pf.A02(v);
        int A0C2 = A0C(v, A023);
        if (!z) {
            boolean z2 = false;
            if (A0C2 == -1) {
                z2 = true;
            }
            Preconditions.checkArgument(z2, "Value already present: %s", v);
        } else if (A0C2 != -1) {
            A0F(A0C2, A023);
        }
        A02(this.A00 + 1);
        K[] kArr = this.A0B;
        int i = this.A00;
        kArr[i] = k;
        this.A0C[i] = v;
        A05(i, A022);
        A06(this.A00, A023);
        int i2 = this.A0D;
        int i3 = this.A00;
        A07(i2, i3);
        A07(i3, -2);
        this.A00 = i3 + 1;
        this.A02++;
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.HashBiMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        A01();
        for (int i = 0; i < readInt; i++) {
            put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    @GwtIncompatible
    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        for (Map.Entry<K, V> entry : entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    @NullableDecl
    public final K A0D(@NullableDecl V v, @NullableDecl K k, boolean z) {
        int i;
        int A022 = C06710pf.A02(v);
        int A0C2 = A0C(v, A022);
        if (A0C2 != -1) {
            K k2 = this.A0B[A0C2];
            if (Objects.equal(k2, k)) {
                return k;
            }
            A09(this, A0C2, k, z);
            return k2;
        }
        int i2 = this.A0D;
        int A023 = C06710pf.A02(k);
        int A0B2 = A0B(k, A023);
        if (!z) {
            boolean z2 = false;
            if (A0B2 == -1) {
                z2 = true;
            }
            Preconditions.checkArgument(z2, "Key already present: %s", k);
        } else if (A0B2 != -1) {
            i2 = this.A0A[A0B2];
            A0E(A0B2, A023);
        }
        A02(this.A00 + 1);
        K[] kArr = this.A0B;
        int i3 = this.A00;
        kArr[i3] = k;
        this.A0C[i3] = v;
        A05(i3, A023);
        A06(this.A00, A022);
        if (i2 == -2) {
            i = this.A01;
        } else {
            i = this.A09[i2];
        }
        int i4 = this.A00;
        A07(i2, i4);
        A07(i4, i);
        this.A00 = i4 + 1;
        this.A02++;
        return null;
    }

    public final boolean containsKey(@NullableDecl Object obj) {
        if (A0B(obj, C06710pf.A02(obj)) != -1) {
            return true;
        }
        return false;
    }

    public final boolean containsValue(@NullableDecl Object obj) {
        if (A0C(obj, C06710pf.A02(obj)) != -1) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @NullableDecl
    public final V get(@NullableDecl Object obj) {
        int A0B2 = A0B(obj, C06710pf.A02(obj));
        if (A0B2 == -1) {
            return null;
        }
        return this.A0C[A0B2];
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    @NullableDecl
    public final V remove(@NullableDecl Object obj) {
        int A022 = C06710pf.A02(obj);
        int A0B2 = A0B(obj, A022);
        if (A0B2 == -1) {
            return null;
        }
        V v = this.A0C[A0B2];
        A0E(A0B2, A022);
        return v;
    }

    public final int size() {
        return this.A00;
    }
}
