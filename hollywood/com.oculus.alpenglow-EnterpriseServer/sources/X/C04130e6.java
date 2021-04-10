package X;

import android.annotation.SuppressLint;
import android.view.View;

/* renamed from: X.0e6  reason: invalid class name and case insensitive filesystem */
public class C04130e6 extends AbstractView$OnAttachStateChangeListenerC003504l {
    public final /* synthetic */ AnonymousClass0Ml A00;
    public final /* synthetic */ C04110e4 A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C04130e6(C04110e4 r1, View view, AnonymousClass0Ml r3) {
        super(view);
        this.A01 = r1;
        this.A00 = r3;
    }

    @Override // X.AbstractView$OnAttachStateChangeListenerC003504l
    public final AbstractC000903e A01() {
        return this.A00;
    }

    @Override // X.AbstractView$OnAttachStateChangeListenerC003504l
    @SuppressLint({"SyntheticAccessor"})
    public final boolean A02() {
        C04110e4 r3 = this.A01;
        if (r3.A01.A5a()) {
            return true;
        }
        r3.A01.A8Q(r3.getTextDirection(), r3.getTextAlignment());
        return true;
    }
}
