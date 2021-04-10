package X;

import androidx.recyclerview.widget.StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem;
import java.util.Arrays;
import java.util.List;

/* renamed from: X.1Bv  reason: invalid class name */
public class AnonymousClass1Bv {
    public List<StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> A00;
    public int[] A01;

    public static final void A00(AnonymousClass1Bv r5, int i) {
        int[] iArr = r5.A01;
        if (iArr == null) {
            int[] iArr2 = new int[(Math.max(i, 10) + 1)];
            r5.A01 = iArr2;
            Arrays.fill(iArr2, -1);
            return;
        }
        int length = iArr.length;
        if (i >= length) {
            while (length <= i) {
                length <<= 1;
            }
            int[] iArr3 = new int[length];
            r5.A01 = iArr3;
            int length2 = iArr.length;
            System.arraycopy(iArr, 0, iArr3, 0, length2);
            int[] iArr4 = r5.A01;
            Arrays.fill(iArr4, length2, iArr4.length, -1);
        }
    }

    public final void A01() {
        int[] iArr = this.A01;
        if (iArr != null) {
            Arrays.fill(iArr, -1);
        }
        this.A00 = null;
    }

    public final void A02(int i, int i2) {
        int[] iArr = this.A01;
        if (iArr != null && i < iArr.length) {
            int i3 = i + i2;
            A00(this, i3);
            int[] iArr2 = this.A01;
            System.arraycopy(iArr2, i, iArr2, i3, (iArr2.length - i) - i2);
            Arrays.fill(this.A01, i, i3, -1);
            List<StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> list = this.A00;
            if (list != null) {
                int size = list.size();
                while (true) {
                    size--;
                    if (size >= 0) {
                        StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = this.A00.get(size);
                        int i4 = staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.A02;
                        if (i4 >= i) {
                            staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.A02 = i4 + i2;
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public final void A03(int i, int i2) {
        int[] iArr = this.A01;
        if (iArr != null && i < iArr.length) {
            int i3 = i + i2;
            A00(this, i3);
            int[] iArr2 = this.A01;
            System.arraycopy(iArr2, i3, iArr2, i, (iArr2.length - i) - i2);
            int[] iArr3 = this.A01;
            int length = iArr3.length;
            Arrays.fill(iArr3, length - i2, length, -1);
            List<StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem> list = this.A00;
            if (list != null) {
                int size = list.size();
                while (true) {
                    size--;
                    if (size >= 0) {
                        StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem staggeredGridLayoutManager$LazySpanLookup$FullSpanItem = this.A00.get(size);
                        int i4 = staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.A02;
                        if (i4 >= i) {
                            if (i4 < i3) {
                                this.A00.remove(size);
                            } else {
                                staggeredGridLayoutManager$LazySpanLookup$FullSpanItem.A02 = i4 - i2;
                            }
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }
}
