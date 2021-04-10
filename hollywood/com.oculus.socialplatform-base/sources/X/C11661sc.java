package X;

import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1sc  reason: invalid class name and case insensitive filesystem */
public final class C11661sc extends BaseAdapter {
    public C11581sN A00;
    public boolean A01;
    public int A02 = -1;
    public final int A03;
    public final LayoutInflater A04;
    public final boolean A05;

    public final long getItemId(int i) {
        return (long) i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        if (r6 == r4) goto L_0x0030;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View getView(int r8, android.view.View r9, android.view.ViewGroup r10) {
        /*
            r7 = this;
            r5 = 0
            if (r9 != 0) goto L_0x000b
            android.view.LayoutInflater r1 = r7.A04
            int r0 = r7.A03
            android.view.View r9 = r1.inflate(r0, r10, r5)
        L_0x000b:
            X.1sP r0 = r7.getItem(r8)
            int r6 = r0.getGroupId()
            int r0 = r8 + -1
            if (r0 < 0) goto L_0x004f
            X.1sP r0 = r7.getItem(r0)
            int r4 = r0.getGroupId()
        L_0x001f:
            r3 = r9
            androidx.appcompat.view.menu.ListMenuItemView r3 = (androidx.appcompat.view.menu.ListMenuItemView) r3
            X.1sN r1 = r7.A00
            boolean r0 = r1 instanceof X.SubMenuC11621sV
            if (r0 != 0) goto L_0x0046
            boolean r0 = r1.A0A
        L_0x002a:
            r2 = 1
            if (r0 == 0) goto L_0x0030
            r0 = 1
            if (r6 != r4) goto L_0x0031
        L_0x0030:
            r0 = 0
        L_0x0031:
            r3.setGroupDividerEnabled(r0)
            r1 = r9
            X.1tg r1 = (X.AbstractC11971tg) r1
            boolean r0 = r7.A01
            if (r0 == 0) goto L_0x003e
            r3.setForceShowIcon(r2)
        L_0x003e:
            X.1sP r0 = r7.getItem(r8)
            r1.A5i(r0, r5)
            return r9
        L_0x0046:
            X.1sV r1 = (X.SubMenuC11621sV) r1
            X.1sN r0 = r1.A00
            boolean r0 = r0.A0H()
            goto L_0x002a
        L_0x004f:
            r4 = r6
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C11661sc.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    private final void A00() {
        C11581sN r0 = this.A00;
        C11601sP r4 = r0.A04;
        if (r4 != null) {
            r0.A07();
            ArrayList<C11601sP> arrayList = r0.A08;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (arrayList.get(i) == r4) {
                    this.A02 = i;
                    return;
                }
            }
        }
        this.A02 = -1;
    }

    /* renamed from: A01 */
    public final C11601sP getItem(int i) {
        ArrayList<C11601sP> A06;
        if (this.A05) {
            C11581sN r0 = this.A00;
            r0.A07();
            A06 = r0.A08;
        } else {
            A06 = this.A00.A06();
        }
        int i2 = this.A02;
        if (i2 >= 0 && i >= i2) {
            i++;
        }
        return A06.get(i);
    }

    public final int getCount() {
        ArrayList<C11601sP> A06;
        if (this.A05) {
            C11581sN r0 = this.A00;
            r0.A07();
            A06 = r0.A08;
        } else {
            A06 = this.A00.A06();
        }
        int i = this.A02;
        int size = A06.size();
        if (i >= 0) {
            return size - 1;
        }
        return size;
    }

    public C11661sc(C11581sN r2, LayoutInflater layoutInflater, boolean z, int i) {
        this.A05 = z;
        this.A04 = layoutInflater;
        this.A00 = r2;
        this.A03 = i;
        A00();
    }

    public final void notifyDataSetChanged() {
        A00();
        super.notifyDataSetChanged();
    }
}
