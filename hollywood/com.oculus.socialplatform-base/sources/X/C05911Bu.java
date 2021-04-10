package X;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/* renamed from: X.1Bu  reason: invalid class name and case insensitive filesystem */
public class C05911Bu {
    public int A00;
    public int A01 = 0;
    public int A02;
    public int A03;
    public int A04 = 0;
    public int A05;
    public int A06;
    public boolean A07;
    public boolean A08 = true;
    public int A09;
    public int A0A;
    public List<AnonymousClass1Ah> A0B = null;

    public static final void A00(C05911Bu r7, View view) {
        int i;
        int layoutPosition;
        int size = r7.A0B.size();
        View view2 = null;
        int i2 = Integer.MAX_VALUE;
        int i3 = 0;
        while (true) {
            if (i3 < size) {
                View view3 = r7.A0B.get(i3).itemView;
                C05831Bi r0 = (C05831Bi) view3.getLayoutParams();
                if (view3 != view) {
                    AnonymousClass1Ah r1 = r0.A01;
                    if (!r1.isRemoved() && (layoutPosition = (r1.getLayoutPosition() - r7.A09) * r7.A0A) >= 0 && layoutPosition < i2) {
                        view2 = view3;
                        if (layoutPosition == 0) {
                            break;
                        }
                        i2 = layoutPosition;
                    }
                }
                i3++;
            } else if (view2 == null) {
                i = -1;
            }
        }
        i = ((C05831Bi) view2.getLayoutParams()).A01.getLayoutPosition();
        r7.A09 = i;
    }

    public final View A01(AnonymousClass1Af r7) {
        List<AnonymousClass1Ah> list = this.A0B;
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                View view = this.A0B.get(i).itemView;
                AnonymousClass1Ah r3 = ((C05831Bi) view.getLayoutParams()).A01;
                if (!r3.isRemoved() && this.A09 == r3.getLayoutPosition()) {
                    A00(this, view);
                    return view;
                }
            }
            return null;
        }
        View view2 = r7.A04(this.A09, RecyclerView.FOREVER_NS).itemView;
        this.A09 += this.A0A;
        return view2;
    }
}
