package X;

import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.03U  reason: invalid class name */
public final class AnonymousClass03U extends BaseAdapter {
    public C04280eZ A00;
    public boolean A01;
    public int A02 = -1;
    public final int A03;
    public final LayoutInflater A04;
    public final boolean A05;

    public final long getItemId(int i) {
        return (long) i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x002c, code lost:
        if (r5 == r1) goto L_0x002e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View getView(int r7, android.view.View r8, android.view.ViewGroup r9) {
        /*
            r6 = this;
            r4 = 0
            if (r8 != 0) goto L_0x000b
            android.view.LayoutInflater r1 = r6.A04
            int r0 = r6.A03
            android.view.View r8 = r1.inflate(r0, r9, r4)
        L_0x000b:
            X.0eW r0 = r6.getItem(r7)
            int r5 = r0.getGroupId()
            int r0 = r7 + -1
            if (r0 < 0) goto L_0x0044
            X.0eW r0 = r6.getItem(r0)
            int r1 = r0.getGroupId()
        L_0x001f:
            r3 = r8
            androidx.appcompat.view.menu.ListMenuItemView r3 = (androidx.appcompat.view.menu.ListMenuItemView) r3
            X.0eZ r0 = r6.A00
            boolean r0 = r0.A0H()
            r2 = 1
            if (r0 == 0) goto L_0x002e
            r0 = 1
            if (r5 != r1) goto L_0x002f
        L_0x002e:
            r0 = 0
        L_0x002f:
            r3.setGroupDividerEnabled(r0)
            r1 = r8
            X.03c r1 = (X.AbstractC000703c) r1
            boolean r0 = r6.A01
            if (r0 == 0) goto L_0x003c
            r3.setForceShowIcon(r2)
        L_0x003c:
            X.0eW r0 = r6.getItem(r7)
            r1.A5G(r0, r4)
            return r8
        L_0x0044:
            r1 = r5
            goto L_0x001f
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass03U.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
    }

    private final void A00() {
        C04280eZ r0 = this.A00;
        C04250eW r4 = r0.A04;
        if (r4 != null) {
            r0.A07();
            ArrayList<C04250eW> arrayList = r0.A08;
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
    public final C04250eW getItem(int i) {
        ArrayList<C04250eW> A06;
        if (this.A05) {
            C04280eZ r0 = this.A00;
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
        ArrayList<C04250eW> A06;
        if (this.A05) {
            C04280eZ r0 = this.A00;
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

    public AnonymousClass03U(C04280eZ r2, LayoutInflater layoutInflater, boolean z, int i) {
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
