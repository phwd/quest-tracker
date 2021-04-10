package X;

import com.google.android.flexbox.FlexboxLayoutManager;

/* renamed from: X.1Bl  reason: invalid class name and case insensitive filesystem */
public class C05851Bl {
    public int A00;
    public int A01;
    public int A02 = 0;
    public int A03;
    public boolean A04;
    public boolean A05;
    public boolean A06;
    public final /* synthetic */ FlexboxLayoutManager A07;

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002b, code lost:
        if (r0 != r2) goto L_0x0021;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x001e, code lost:
        if (r3.A02 == 1) goto L_0x0020;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A01(X.C05851Bl r5) {
        /*
            r0 = -1
            r5.A03 = r0
            r5.A01 = r0
            r0 = -2147483648(0xffffffff80000000, float:-0.0)
            r5.A00 = r0
            r4 = 0
            r5.A06 = r4
            r5.A04 = r4
            com.google.android.flexbox.FlexboxLayoutManager r3 = r5.A07
            boolean r0 = r3.A65()
            r2 = 2
            r1 = 1
            if (r0 == 0) goto L_0x0024
            int r0 = r3.A03
            if (r0 != 0) goto L_0x002b
            int r0 = r3.A02
            if (r0 != r1) goto L_0x0021
        L_0x0020:
            r4 = 1
        L_0x0021:
            r5.A05 = r4
            return
        L_0x0024:
            int r0 = r3.A03
            if (r0 != 0) goto L_0x002b
            int r0 = r3.A02
            r2 = 3
        L_0x002b:
            if (r0 != r2) goto L_0x0021
            goto L_0x0020
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C05851Bl.A01(X.1Bl):void");
    }

    public C05851Bl(FlexboxLayoutManager flexboxLayoutManager) {
        this.A07 = flexboxLayoutManager;
    }

    public static void A00(C05851Bl r3) {
        int A022;
        FlexboxLayoutManager flexboxLayoutManager = r3.A07;
        if (flexboxLayoutManager.A65() || !flexboxLayoutManager.A0G) {
            if (!r3.A05) {
                A022 = flexboxLayoutManager.A08.A05();
            }
            A022 = flexboxLayoutManager.A08.A02();
        } else {
            if (!r3.A05) {
                A022 = flexboxLayoutManager.mWidth - flexboxLayoutManager.A08.A05();
            }
            A022 = flexboxLayoutManager.A08.A02();
        }
        r3.A00 = A022;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AnchorInfo{mPosition=");
        sb.append(this.A03);
        sb.append(", mFlexLinePosition=");
        sb.append(this.A01);
        sb.append(", mCoordinate=");
        sb.append(this.A00);
        sb.append(", mPerpendicularCoordinate=");
        sb.append(this.A02);
        sb.append(", mLayoutFromEnd=");
        sb.append(this.A05);
        sb.append(", mValid=");
        sb.append(this.A06);
        sb.append(", mAssignedFromSavedState=");
        sb.append(this.A04);
        sb.append('}');
        return sb.toString();
    }
}
