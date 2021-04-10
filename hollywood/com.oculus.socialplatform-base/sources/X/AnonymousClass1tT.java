package X;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.RequiresApi;

@RequiresApi(16)
/* renamed from: X.1tT  reason: invalid class name */
public class AnonymousClass1tT extends C11931tb implements ActionProvider.VisibilityListener {
    public AbstractC002806x A00;
    public final /* synthetic */ MenuItemC11611sU A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1tT(MenuItemC11611sU r1, Context context, ActionProvider actionProvider) {
        super(r1, context, actionProvider);
        this.A01 = r1;
    }

    @Override // X.AbstractC002906y
    public final View A01(MenuItem menuItem) {
        return ((C11931tb) this).A00.onCreateActionView(menuItem);
    }

    @Override // X.AbstractC002906y
    public final void A03(AbstractC002806x r3) {
        this.A00 = r3;
        ActionProvider actionProvider = ((C11931tb) this).A00;
        AnonymousClass1tT r0 = null;
        if (r3 != null) {
            r0 = this;
        }
        actionProvider.setVisibilityListener(r0);
    }

    @Override // X.AbstractC002906y
    public final boolean A05() {
        return ((C11931tb) this).A00.isVisible();
    }

    @Override // X.AbstractC002906y
    public final boolean A07() {
        return ((C11931tb) this).A00.overridesItemVisibility();
    }

    public final void onActionProviderVisibilityChanged(boolean z) {
        AbstractC002806x r0 = this.A00;
        if (r0 != null) {
            r0.onActionProviderVisibilityChanged(z);
        }
    }
}
