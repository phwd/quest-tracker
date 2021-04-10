package X;

import android.content.Context;
import android.view.View;
import com.oculus.socialplatform.R;

/* renamed from: X.1sp  reason: invalid class name and case insensitive filesystem */
public class C11781sp extends AnonymousClass1sZ {
    public final /* synthetic */ C11591sO A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C11781sp(C11591sO r10, Context context, SubMenuC11621sV r12, View view) {
        super(context, r12, view, false, R.attr.actionOverflowMenuStyle, 0);
        this.A00 = r10;
        if ((((C11601sP) r12.getItem()).A02 & 32) != 32) {
            View view2 = r10.A06;
            this.A01 = view2 == null ? (View) ((AnonymousClass1sT) r10).A05 : view2;
        }
        A04(r10.A0D);
    }

    @Override // X.AnonymousClass1sZ
    public final void A02() {
        this.A00.A03 = null;
        super.A02();
    }
}
