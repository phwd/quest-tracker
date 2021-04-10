package X;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.RequiresApi;

@RequiresApi(16)
/* renamed from: X.0Mw  reason: invalid class name */
public class AnonymousClass0Mw extends AnonymousClass0eV implements ActionProvider.VisibilityListener {
    public AnonymousClass0AD A00;
    public final /* synthetic */ MenuItemC04230eT A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass0Mw(MenuItemC04230eT r1, Context context, ActionProvider actionProvider) {
        super(r1, context, actionProvider);
        this.A01 = r1;
    }

    @Override // X.AnonymousClass0AE
    public final View A01(MenuItem menuItem) {
        return ((AnonymousClass0eV) this).A00.onCreateActionView(menuItem);
    }

    @Override // X.AnonymousClass0AE
    public final void A03(AnonymousClass0AD r3) {
        this.A00 = r3;
        ActionProvider actionProvider = ((AnonymousClass0eV) this).A00;
        AnonymousClass0Mw r0 = null;
        if (r3 != null) {
            r0 = this;
        }
        actionProvider.setVisibilityListener(r0);
    }

    @Override // X.AnonymousClass0AE
    public final boolean A05() {
        return ((AnonymousClass0eV) this).A00.isVisible();
    }

    @Override // X.AnonymousClass0AE
    public final boolean A07() {
        return ((AnonymousClass0eV) this).A00.overridesItemVisibility();
    }

    public final void onActionProviderVisibilityChanged(boolean z) {
        AnonymousClass0AD r0 = this.A00;
        if (r0 != null) {
            r0.onActionProviderVisibilityChanged(z);
        }
    }
}
