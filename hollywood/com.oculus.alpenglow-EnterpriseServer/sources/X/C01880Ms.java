package X;

import android.content.Context;
import android.view.View;
import com.oculus.alpenglow.R;

/* renamed from: X.0Ms  reason: invalid class name and case insensitive filesystem */
public class C01880Ms extends C04210eR {
    public final /* synthetic */ AnonymousClass0Mm A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C01880Ms(AnonymousClass0Mm r10, Context context, SubMenuC01890Mu r12, View view) {
        super(context, r12, view, false, R.attr.actionOverflowMenuStyle, 0);
        this.A00 = r10;
        if ((((C04250eW) r12.getItem()).A02 & 32) != 32) {
            View view2 = r10.A05;
            this.A01 = view2 == null ? (View) ((AbstractC04310ee) r10).A05 : view2;
        }
        A04(r10.A0D);
    }

    @Override // X.C04210eR
    public final void A02() {
        this.A00.A03 = null;
        super.A02();
    }
}
