package X;

import android.view.View;

/* renamed from: X.0eM  reason: invalid class name */
public class AnonymousClass0eM extends AbstractView$OnAttachStateChangeListenerC003504l {
    public final /* synthetic */ C01850Mp A00;
    public final /* synthetic */ AnonymousClass0Mm A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0eM(C01850Mp r1, View view, AnonymousClass0Mm r3) {
        super(view);
        this.A00 = r1;
        this.A01 = r3;
    }

    @Override // X.AbstractView$OnAttachStateChangeListenerC003504l
    public final AbstractC000903e A01() {
        AnonymousClass0Mo r0 = this.A00.A00.A00;
        if (r0 == null) {
            return null;
        }
        return r0.A01();
    }

    @Override // X.AbstractView$OnAttachStateChangeListenerC003504l
    public final boolean A02() {
        this.A00.A00.A07();
        return true;
    }

    @Override // X.AbstractView$OnAttachStateChangeListenerC003504l
    public final boolean A03() {
        AnonymousClass0Mm r1 = this.A00.A00;
        if (r1.A04 != null) {
            return false;
        }
        r1.A05();
        return true;
    }
}
