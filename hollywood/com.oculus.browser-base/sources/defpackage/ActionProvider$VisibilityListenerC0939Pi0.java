package defpackage;

import android.content.Context;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.view.View;

/* renamed from: Pi0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ActionProvider$VisibilityListenerC0939Pi0 extends AbstractC0878Oi0 implements ActionProvider.VisibilityListener {
    public C0756Mi0 e;

    public ActionProvider$VisibilityListenerC0939Pi0(MenuItemC1183Ti0 ti0, Context context, ActionProvider actionProvider) {
        super(ti0, context, actionProvider);
    }

    @Override // defpackage.H2
    public boolean b() {
        return this.c.isVisible();
    }

    @Override // defpackage.H2
    public View d(MenuItem menuItem) {
        return this.c.onCreateActionView(menuItem);
    }

    @Override // defpackage.H2
    public boolean g() {
        return this.c.overridesItemVisibility();
    }

    @Override // defpackage.H2
    public void h(C0756Mi0 mi0) {
        this.e = mi0;
        this.c.setVisibilityListener(this);
    }

    public void onActionProviderVisibilityChanged(boolean z) {
        C0756Mi0 mi0 = this.e;
        if (mi0 != null) {
            C4616ri0 ri0 = mi0.f8497a.n;
            ri0.i = true;
            ri0.p(true);
        }
    }
}
