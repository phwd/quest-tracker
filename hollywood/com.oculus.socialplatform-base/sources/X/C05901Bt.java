package X;

import android.view.View;

/* renamed from: X.1Bt  reason: invalid class name and case insensitive filesystem */
public class C05901Bt {
    public boolean A00;
    public int A01;
    public int A02;
    public AbstractC05861Bm A03;
    public boolean A04;

    public final void A00() {
        this.A02 = -1;
        this.A01 = Integer.MIN_VALUE;
        this.A04 = false;
        this.A00 = false;
    }

    public final void A01(View view, int i) {
        int A0B;
        if (this.A04) {
            A0B = this.A03.A08(view) + this.A03.A07();
        } else {
            A0B = this.A03.A0B(view);
        }
        this.A01 = A0B;
        this.A02 = i;
    }

    public final void A02(View view, int i) {
        int min;
        int A07 = this.A03.A07();
        if (A07 >= 0) {
            A01(view, i);
            return;
        }
        this.A02 = i;
        if (this.A04) {
            int A022 = (this.A03.A02() - A07) - this.A03.A08(view);
            this.A01 = this.A03.A02() - A022;
            if (A022 > 0) {
                int A09 = this.A01 - this.A03.A09(view);
                int A05 = this.A03.A05();
                int min2 = A09 - (A05 + Math.min(this.A03.A0B(view) - A05, 0));
                if (min2 < 0) {
                    min = this.A01 + Math.min(A022, -min2);
                } else {
                    return;
                }
            } else {
                return;
            }
        } else {
            int A0B = this.A03.A0B(view);
            int A052 = A0B - this.A03.A05();
            this.A01 = A0B;
            if (A052 > 0) {
                int A023 = (this.A03.A02() - Math.min(0, (this.A03.A02() - A07) - this.A03.A08(view))) - (A0B + this.A03.A09(view));
                if (A023 < 0) {
                    min = this.A01 - Math.min(A052, -A023);
                } else {
                    return;
                }
            } else {
                return;
            }
        }
        this.A01 = min;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AnchorInfo{mPosition=");
        sb.append(this.A02);
        sb.append(", mCoordinate=");
        sb.append(this.A01);
        sb.append(", mLayoutFromEnd=");
        sb.append(this.A04);
        sb.append(", mValid=");
        sb.append(this.A00);
        sb.append('}');
        return sb.toString();
    }

    public C05901Bt() {
        A00();
    }
}
