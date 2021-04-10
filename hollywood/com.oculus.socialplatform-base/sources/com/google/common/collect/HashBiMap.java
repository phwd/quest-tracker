package com.google.common.collect;

import X.AbstractC05100td;
import X.AbstractC05160uM;
import X.AnonymousClass0fW;
import X.AnonymousClass0fX;
import X.AnonymousClass0fY;
import X.AnonymousClass0th;
import X.C01590fa;
import X.C05150uI;
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
public final class HashBiMap<K, V> extends AbstractMap<K, V> implements AbstractC05100td<K, V>, Serializable {
    @NullableDecl
    public transient int A00;
    @NullableDecl
    public transient int A01;
    public transient int A02;
    public transient int A03;
    @RetainedWith
    @MonotonicNonNullDecl
    public transient AbstractC05100td<V, K> A04;
    public transient Set<V> A05;
    public transient int[] A06;
    public transient int[] A07;
    public transient int[] A08;
    public transient int[] A09;
    public transient int[] A0A;
    public transient int[] A0B;
    public transient K[] A0C;
    public transient V[] A0D;
    public transient Set<Map.Entry<K, V>> A0E;
    public transient Set<K> A0F;

    public static class Inverse<K, V> extends AbstractMap<V, K> implements AbstractC05100td<V, K>, Serializable {
        public transient Set<Map.Entry<V, K>> A00;
        public final HashBiMap<K, V> forward;

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
            AnonymousClass0fY r1 = new AnonymousClass0fY(this.forward);
            this.A00 = r1;
            return r1;
        }

        @Override // java.util.AbstractMap, java.util.Map
        @NullableDecl
        public final K get(@NullableDecl Object obj) {
            HashBiMap<K, V> hashBiMap = this.forward;
            int A0B = hashBiMap.A0B(obj, C05150uI.A02(obj));
            if (A0B == -1) {
                return null;
            }
            return hashBiMap.A0C[A0B];
        }

        @Override // java.util.AbstractMap, java.util.Map
        public final Set<V> keySet() {
            HashBiMap<K, V> hashBiMap = this.forward;
            Set<V> set = hashBiMap.A05;
            if (set != null) {
                return set;
            }
            AnonymousClass0fW r0 = new AnonymousClass0fW(hashBiMap);
            hashBiMap.A05 = r0;
            return r0;
        }

        @Override // java.util.AbstractMap, java.util.Map, X.AbstractC05100td
        @CanIgnoreReturnValue
        @NullableDecl
        public final K put(@NullableDecl V v, @NullableDecl K k) {
            return (K) this.forward.A0C(v, k);
        }

        @Override // java.util.AbstractMap, java.util.Map
        @CanIgnoreReturnValue
        @NullableDecl
        public final K remove(@NullableDecl Object obj) {
            HashBiMap<K, V> hashBiMap = this.forward;
            int A02 = C05150uI.A02(obj);
            int A0B = hashBiMap.A0B(obj, A02);
            if (A0B == -1) {
                return null;
            }
            K k = hashBiMap.A0C[A0B];
            HashBiMap.A07(hashBiMap, A0B, C05150uI.A02(k), A02);
            return k;
        }

        public final int size() {
            return this.forward.A03;
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
            this.forward.A04 = this;
        }

        @Override // X.AbstractC05100td
        public final AbstractC05100td<K, V> A5n() {
            return this.forward;
        }
    }

    private void A02(int i, int i2) {
        boolean z = false;
        if (i != -1) {
            z = true;
        }
        Preconditions.checkArgument(z);
        int[] iArr = this.A06;
        int length = i2 & (iArr.length - 1);
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
                StringBuilder sb = new StringBuilder("Expected to find entry with key ");
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

    private void A03(int i, int i2) {
        boolean z = false;
        if (i != -1) {
            z = true;
        }
        Preconditions.checkArgument(z);
        int length = i2 & (this.A06.length - 1);
        int[] iArr = this.A07;
        int i3 = iArr[length];
        if (i3 == i) {
            int[] iArr2 = this.A09;
            iArr[length] = iArr2[i];
            iArr2[i] = -1;
            return;
        }
        int[] iArr3 = this.A09;
        int i4 = iArr3[i3];
        while (true) {
            i3 = i4;
            if (i4 == -1) {
                StringBuilder sb = new StringBuilder("Expected to find entry with value ");
                sb.append((Object) this.A0D[i]);
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
        int[] iArr = this.A06;
        int length = i2 & (iArr.length - 1);
        this.A08[i] = iArr[length];
        iArr[length] = i;
    }

    private void A05(int i, int i2) {
        boolean z = false;
        if (i != -1) {
            z = true;
        }
        Preconditions.checkArgument(z);
        int length = i2 & (this.A06.length - 1);
        int[] iArr = this.A09;
        int[] iArr2 = this.A07;
        iArr[i] = iArr2[length];
        iArr2[length] = i;
    }

    private void A06(int i, int i2) {
        if (i == -2) {
            this.A00 = i2;
        } else {
            this.A0A[i] = i2;
        }
        if (i2 == -2) {
            this.A01 = i;
        } else {
            this.A0B[i2] = i;
        }
    }

    public static void A07(HashBiMap hashBiMap, int i, int i2, int i3) {
        boolean z = false;
        if (i != -1) {
            z = true;
        }
        Preconditions.checkArgument(z);
        hashBiMap.A02(i, i2);
        hashBiMap.A03(i, i3);
        int[] iArr = hashBiMap.A0B;
        int i4 = iArr[i];
        int[] iArr2 = hashBiMap.A0A;
        hashBiMap.A06(i4, iArr2[i]);
        int i5 = hashBiMap.A03 - 1;
        if (i5 != i) {
            int i6 = iArr[i5];
            int i7 = iArr2[i5];
            hashBiMap.A06(i6, i);
            hashBiMap.A06(i, i7);
            K[] kArr = hashBiMap.A0C;
            K k = kArr[i5];
            V[] vArr = hashBiMap.A0D;
            V v = vArr[i5];
            kArr[i] = k;
            vArr[i] = v;
            int A022 = C05150uI.A02(k);
            int[] iArr3 = hashBiMap.A06;
            int length = A022 & (iArr3.length - 1);
            int i8 = iArr3[length];
            if (i8 == i5) {
                iArr3[length] = i;
            } else {
                int[] iArr4 = hashBiMap.A08;
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
            int[] iArr5 = hashBiMap.A08;
            iArr5[i] = iArr5[i5];
            iArr5[i5] = -1;
            int A023 = C05150uI.A02(v) & (hashBiMap.A06.length - 1);
            int[] iArr6 = hashBiMap.A07;
            int i10 = iArr6[A023];
            if (i10 == i5) {
                iArr6[A023] = i;
            } else {
                int[] iArr7 = hashBiMap.A09;
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
            int[] iArr8 = hashBiMap.A09;
            iArr8[i] = iArr8[i5];
            iArr8[i5] = -1;
        }
        K[] kArr2 = hashBiMap.A0C;
        int i12 = hashBiMap.A03;
        int i13 = i12 - 1;
        kArr2[i13] = null;
        hashBiMap.A0D[i13] = null;
        hashBiMap.A03 = i12 - 1;
        hashBiMap.A02++;
    }

    /* JADX WARN: Incorrect types in method signature: (ITK;Z)V */
    /* JADX DEBUG: Multi-variable search result rejected for r0v11, resolved type: K[] */
    /* JADX WARN: Multi-variable type inference failed */
    public static void A08(HashBiMap hashBiMap, @NullableDecl int i, Object obj) {
        boolean z = false;
        if (i != -1) {
            z = true;
        }
        Preconditions.checkArgument(z);
        int A0A2 = hashBiMap.A0A(obj, C05150uI.A02(obj));
        int i2 = hashBiMap.A01;
        if (A0A2 != -1) {
            StringBuilder sb = new StringBuilder("Key already present in map: ");
            sb.append(obj);
            throw new IllegalArgumentException(sb.toString());
        }
        if (i2 == i) {
            i2 = hashBiMap.A0B[i];
        } else if (i2 == hashBiMap.A03) {
            i2 = -1;
        }
        if (-2 == i) {
            A0A2 = hashBiMap.A0A[i];
        } else if (-2 != hashBiMap.A03) {
            A0A2 = -2;
        }
        hashBiMap.A06(hashBiMap.A0B[i], hashBiMap.A0A[i]);
        hashBiMap.A02(i, C05150uI.A02(hashBiMap.A0C[i]));
        hashBiMap.A0C[i] = obj;
        hashBiMap.A04(i, C05150uI.A02(obj));
        hashBiMap.A06(i2, i);
        hashBiMap.A06(i, A0A2);
    }

    /* JADX WARN: Incorrect types in method signature: (ITV;Z)V */
    /* JADX DEBUG: Multi-variable search result rejected for r0v6, resolved type: V[] */
    /* JADX WARN: Multi-variable type inference failed */
    public static void A09(HashBiMap hashBiMap, @NullableDecl int i, Object obj) {
        boolean z = false;
        if (i != -1) {
            z = true;
        }
        Preconditions.checkArgument(z);
        int A022 = C05150uI.A02(obj);
        if (hashBiMap.A0B(obj, A022) != -1) {
            StringBuilder sb = new StringBuilder("Value already present in map: ");
            sb.append(obj);
            throw new IllegalArgumentException(sb.toString());
        }
        hashBiMap.A03(i, C05150uI.A02(hashBiMap.A0D[i]));
        hashBiMap.A0D[i] = obj;
        hashBiMap.A05(i, A022);
    }

    @Override // java.util.AbstractMap, java.util.Map, X.AbstractC05100td
    @CanIgnoreReturnValue
    public final V put(@NullableDecl K k, @NullableDecl V v) {
        boolean z = false;
        int A022 = C05150uI.A02(k);
        int A0A2 = A0A(k, A022);
        if (A0A2 != -1) {
            V v2 = this.A0D[A0A2];
            if (Objects.equal(v2, v)) {
                return v;
            }
            A09(this, A0A2, v);
            return v2;
        }
        int A023 = C05150uI.A02(v);
        if (A0B(v, A023) == -1) {
            z = true;
        }
        Preconditions.checkArgument(z, "Value already present: %s", v);
        A01(this.A03 + 1);
        K[] kArr = this.A0C;
        int i = this.A03;
        kArr[i] = k;
        this.A0D[i] = v;
        A04(i, A022);
        A05(this.A03, A023);
        int i2 = this.A01;
        int i3 = this.A03;
        A06(i2, i3);
        A06(i3, -2);
        this.A03 = i3 + 1;
        this.A02++;
        return null;
    }

    private final void A00() {
        AnonymousClass0th.A00(16, "expectedSize");
        int A012 = C05150uI.A01(16, 1.0d);
        this.A03 = 0;
        this.A0C = (K[]) new Object[16];
        this.A0D = (V[]) new Object[16];
        int[] iArr = new int[A012];
        Arrays.fill(iArr, -1);
        this.A06 = iArr;
        int[] iArr2 = new int[A012];
        Arrays.fill(iArr2, -1);
        this.A07 = iArr2;
        int[] iArr3 = new int[16];
        Arrays.fill(iArr3, -1);
        this.A08 = iArr3;
        int[] iArr4 = new int[16];
        Arrays.fill(iArr4, -1);
        this.A09 = iArr4;
        this.A00 = -2;
        this.A01 = -2;
        int[] iArr5 = new int[16];
        Arrays.fill(iArr5, -1);
        this.A0B = iArr5;
        int[] iArr6 = new int[16];
        Arrays.fill(iArr6, -1);
        this.A0A = iArr6;
    }

    private void A01(int i) {
        int length = this.A08.length;
        if (length < i) {
            int A012 = AbstractC05160uM.A01(length, i);
            this.A0C = (K[]) Arrays.copyOf(this.A0C, A012);
            this.A0D = (V[]) Arrays.copyOf(this.A0D, A012);
            int[] iArr = this.A08;
            int length2 = iArr.length;
            int[] copyOf = Arrays.copyOf(iArr, A012);
            Arrays.fill(copyOf, length2, A012, -1);
            this.A08 = copyOf;
            int[] iArr2 = this.A09;
            int length3 = iArr2.length;
            int[] copyOf2 = Arrays.copyOf(iArr2, A012);
            Arrays.fill(copyOf2, length3, A012, -1);
            this.A09 = copyOf2;
            int[] iArr3 = this.A0B;
            int length4 = iArr3.length;
            int[] copyOf3 = Arrays.copyOf(iArr3, A012);
            Arrays.fill(copyOf3, length4, A012, -1);
            this.A0B = copyOf3;
            int[] iArr4 = this.A0A;
            int length5 = iArr4.length;
            int[] copyOf4 = Arrays.copyOf(iArr4, A012);
            Arrays.fill(copyOf4, length5, A012, -1);
            this.A0A = copyOf4;
        }
        if (this.A06.length < i) {
            int A013 = C05150uI.A01(i, 1.0d);
            int[] iArr5 = new int[A013];
            Arrays.fill(iArr5, -1);
            this.A06 = iArr5;
            int[] iArr6 = new int[A013];
            Arrays.fill(iArr6, -1);
            this.A07 = iArr6;
            for (int i2 = 0; i2 < this.A03; i2++) {
                int A022 = C05150uI.A02(this.A0C[i2]);
                int[] iArr7 = this.A06;
                int length6 = A022 & (iArr7.length - 1);
                this.A08[i2] = iArr7[length6];
                iArr7[length6] = i2;
                int A023 = C05150uI.A02(this.A0D[i2]) & (this.A06.length - 1);
                int[] iArr8 = this.A09;
                int[] iArr9 = this.A07;
                iArr8[i2] = iArr9[A023];
                iArr9[A023] = i2;
            }
        }
    }

    public final int A0A(@NullableDecl Object obj, int i) {
        int[] iArr = this.A06;
        int[] iArr2 = this.A08;
        K[] kArr = this.A0C;
        for (int i2 = iArr[i & (iArr.length - 1)]; i2 != -1; i2 = iArr2[i2]) {
            if (Objects.equal(kArr[i2], obj)) {
                return i2;
            }
        }
        return -1;
    }

    public final int A0B(@NullableDecl Object obj, int i) {
        int[] iArr = this.A07;
        int[] iArr2 = this.A09;
        V[] vArr = this.A0D;
        for (int i2 = iArr[i & (this.A06.length - 1)]; i2 != -1; i2 = iArr2[i2]) {
            if (Objects.equal(vArr[i2], obj)) {
                return i2;
            }
        }
        return -1;
    }

    public final void A0D(int i, int i2) {
        A07(this, i, i2, C05150uI.A02(this.A0D[i]));
    }

    @Override // X.AbstractC05100td
    public final AbstractC05100td<V, K> A5n() {
        AbstractC05100td<V, K> r0 = this.A04;
        if (r0 != null) {
            return r0;
        }
        Inverse inverse = new Inverse(this);
        this.A04 = inverse;
        return inverse;
    }

    public final void clear() {
        Arrays.fill(this.A0C, 0, this.A03, (Object) null);
        Arrays.fill(this.A0D, 0, this.A03, (Object) null);
        Arrays.fill(this.A06, -1);
        Arrays.fill(this.A07, -1);
        Arrays.fill(this.A08, 0, this.A03, -1);
        Arrays.fill(this.A09, 0, this.A03, -1);
        Arrays.fill(this.A0B, 0, this.A03, -1);
        Arrays.fill(this.A0A, 0, this.A03, -1);
        this.A03 = 0;
        this.A00 = -2;
        this.A01 = -2;
        this.A02++;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = this.A0E;
        if (set != null) {
            return set;
        }
        C01590fa r0 = new C01590fa(this);
        this.A0E = r0;
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set<K> keySet() {
        Set<K> set = this.A0F;
        if (set != null) {
            return set;
        }
        AnonymousClass0fX r0 = new AnonymousClass0fX(this);
        this.A0F = r0;
        return r0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final /* bridge */ /* synthetic */ Collection values() {
        Set<V> set = this.A05;
        if (set != null) {
            return set;
        }
        AnonymousClass0fW r0 = new AnonymousClass0fW(this);
        this.A05 = r0;
        return r0;
    }

    public HashBiMap() {
        A00();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.collect.HashBiMap<K, V> */
    /* JADX WARN: Multi-variable type inference failed */
    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        A00();
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

    /* JADX WARN: Incorrect args count in method signature: (TV;TK;Z)TK; */
    /* JADX DEBUG: Multi-variable search result rejected for r0v4, resolved type: K[] */
    /* JADX DEBUG: Multi-variable search result rejected for r0v5, resolved type: V[] */
    /* JADX WARN: Multi-variable type inference failed */
    @NullableDecl
    public final Object A0C(@NullableDecl Object obj, @NullableDecl Object obj2) {
        int i;
        int A022 = C05150uI.A02(obj);
        int A0B2 = A0B(obj, A022);
        if (A0B2 != -1) {
            K k = this.A0C[A0B2];
            if (Objects.equal(k, obj2)) {
                return obj2;
            }
            A08(this, A0B2, obj2);
            return k;
        }
        int i2 = this.A01;
        int A023 = C05150uI.A02(obj2);
        boolean z = false;
        if (A0A(obj2, A023) == -1) {
            z = true;
        }
        Preconditions.checkArgument(z, "Key already present: %s", obj2);
        A01(this.A03 + 1);
        int i3 = this.A03;
        this.A0C[i3] = obj2;
        this.A0D[i3] = obj;
        A04(i3, A023);
        A05(this.A03, A022);
        if (i2 == -2) {
            i = this.A00;
        } else {
            i = this.A0A[i2];
        }
        int i4 = this.A03;
        A06(i2, i4);
        A06(i4, i);
        this.A03 = i4 + 1;
        this.A02++;
        return null;
    }

    public final boolean containsKey(@NullableDecl Object obj) {
        if (A0A(obj, C05150uI.A02(obj)) != -1) {
            return true;
        }
        return false;
    }

    public final boolean containsValue(@NullableDecl Object obj) {
        if (A0B(obj, C05150uI.A02(obj)) != -1) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    @NullableDecl
    public final V get(@NullableDecl Object obj) {
        int A0A2 = A0A(obj, C05150uI.A02(obj));
        if (A0A2 == -1) {
            return null;
        }
        return this.A0D[A0A2];
    }

    @Override // java.util.AbstractMap, java.util.Map
    @CanIgnoreReturnValue
    @NullableDecl
    public final V remove(@NullableDecl Object obj) {
        int A022 = C05150uI.A02(obj);
        int A0A2 = A0A(obj, A022);
        if (A0A2 == -1) {
            return null;
        }
        V v = this.A0D[A0A2];
        A0D(A0A2, A022);
        return v;
    }

    public final int size() {
        return this.A03;
    }
}
