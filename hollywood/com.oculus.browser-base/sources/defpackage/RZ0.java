package defpackage;

import androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem;
import java.util.Arrays;
import java.util.List;

/* renamed from: RZ0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RZ0 {

    /* renamed from: a  reason: collision with root package name */
    public int[] f8839a;
    public List b;

    public void a() {
        int[] iArr = this.f8839a;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        this.b = null;
    }

    public void b(int i) {
        int[] iArr = this.f8839a;
        if (iArr == null) {
            int[] iArr2 = new int[(Math.max(i, 10) + 1)];
            this.f8839a = iArr2;
            Arrays.fill(iArr2, -1);
        } else if (i >= iArr.length) {
            int length = iArr.length;
            while (length <= i) {
                length *= 2;
            }
            int[] iArr3 = new int[length];
            this.f8839a = iArr3;
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            int[] iArr4 = this.f8839a;
            Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
        }
    }

    public StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem c(int i) {
        List list = this.b;
        if (list == null) {
            return null;
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = (StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) this.b.get(size);
            if (staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.F == i) {
                return staggeredGridLayoutManager$LazySpanLookup$FullSpanItem;
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0052  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int d(int r5) {
        /*
            r4 = this;
            int[] r0 = r4.f8839a
            r1 = -1
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            int r0 = r0.length
            if (r5 < r0) goto L_0x000a
            return r1
        L_0x000a:
            java.util.List r0 = r4.b
            if (r0 != 0) goto L_0x0010
        L_0x000e:
            r0 = r1
            goto L_0x0046
        L_0x0010:
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r0 = r4.c(r5)
            if (r0 == 0) goto L_0x001b
            java.util.List r2 = r4.b
            r2.remove(r0)
        L_0x001b:
            java.util.List r0 = r4.b
            int r0 = r0.size()
            r2 = 0
        L_0x0022:
            if (r2 >= r0) goto L_0x0034
            java.util.List r3 = r4.b
            java.lang.Object r3 = r3.get(r2)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r3 = (androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) r3
            int r3 = r3.F
            if (r3 < r5) goto L_0x0031
            goto L_0x0035
        L_0x0031:
            int r2 = r2 + 1
            goto L_0x0022
        L_0x0034:
            r2 = r1
        L_0x0035:
            if (r2 == r1) goto L_0x000e
            java.util.List r0 = r4.b
            java.lang.Object r0 = r0.get(r2)
            androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem r0 = (androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) r0
            java.util.List r3 = r4.b
            r3.remove(r2)
            int r0 = r0.F
        L_0x0046:
            if (r0 != r1) goto L_0x0052
            int[] r0 = r4.f8839a
            int r2 = r0.length
            java.util.Arrays.fill(r0, r5, r2, r1)
            int[] r5 = r4.f8839a
            int r5 = r5.length
            return r5
        L_0x0052:
            int r0 = r0 + 1
            int[] r2 = r4.f8839a
            int r2 = r2.length
            int r0 = java.lang.Math.min(r0, r2)
            int[] r2 = r4.f8839a
            java.util.Arrays.fill(r2, r5, r0, r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.RZ0.d(int):int");
    }

    public void e(int i, int i2) {
        int[] iArr = this.f8839a;
        if (iArr != null && i < iArr.length) {
            int i3 = i + i2;
            b(i3);
            int[] iArr2 = this.f8839a;
            System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
            Arrays.fill(this.f8839a, i, i3, -1);
            List list = this.b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = (StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) this.b.get(size);
                    int i4 = staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.F;
                    if (i4 >= i) {
                        staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.F = i4 + i2;
                    }
                }
            }
        }
    }

    public void f(int i, int i2) {
        int[] iArr = this.f8839a;
        if (iArr != null && i < iArr.length) {
            int i3 = i + i2;
            b(i3);
            int[] iArr2 = this.f8839a;
            System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
            int[] iArr3 = this.f8839a;
            Arrays.fill(iArr3, iArr3.length - i2, iArr3.length, -1);
            List list = this.b;
            if (list != null) {
                for (int size = list.size() - 1; size >= 0; size--) {
                    StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = (StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem) this.b.get(size);
                    int i4 = staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.F;
                    if (i4 >= i) {
                        if (i4 < i3) {
                            this.b.remove(size);
                        } else {
                            staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.F = i4 - i2;
                        }
                    }
                }
            }
        }
    }
}
