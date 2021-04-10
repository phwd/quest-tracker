package X;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Locale;

/* renamed from: X.066  reason: invalid class name */
public class AnonymousClass066<K, V> {
    public int A00;
    public int A01;
    public int A02;
    public int A03;
    public int A04;
    public int A05;
    public final LinkedHashMap<K, V> A06 = new LinkedHashMap<>(0, 0.75f, true);

    /* JADX WARN: Incorrect return type in method signature: (TK;TV;)TV; */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0072, code lost:
        throw new java.lang.IllegalStateException(X.AnonymousClass006.A05(getClass().getName(), ".sizeOf() is reporting inconsistent results!"));
     */
    @androidx.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A01(@androidx.annotation.NonNull java.lang.Object r6, @androidx.annotation.NonNull java.lang.Object r7) {
        /*
        // Method dump skipped, instructions count: 129
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass066.A01(java.lang.Object, java.lang.Object):void");
    }

    public final synchronized String toString() {
        int i;
        int i2;
        int i3;
        i = this.A02;
        i2 = this.A04;
        int i4 = i + i2;
        if (i4 != 0) {
            i3 = (i * 100) / i4;
        } else {
            i3 = 0;
        }
        return String.format(Locale.US, "LruCache[maxSize=%d,hits=%d,misses=%d,hitRate=%d%%]", Integer.valueOf(this.A03), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3));
    }

    @Nullable
    public final V A00(@NonNull K k) {
        if (k != null) {
            synchronized (this) {
                V v = this.A06.get(k);
                if (v != null) {
                    this.A02++;
                    return v;
                }
                this.A04++;
                return null;
            }
        }
        throw new NullPointerException("key == null");
    }

    public AnonymousClass066(int i) {
        this.A03 = i;
    }
}
