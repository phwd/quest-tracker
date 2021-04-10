package defpackage;

import android.content.Context;
import android.view.ActionProvider;
import android.view.SubMenu;
import android.view.View;
import java.util.Objects;

/* renamed from: Oi0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC0878Oi0 extends H2 {
    public final ActionProvider c;
    public final /* synthetic */ MenuItemC1183Ti0 d;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AbstractC0878Oi0(MenuItemC1183Ti0 ti0, Context context, ActionProvider actionProvider) {
        super(context);
        this.d = ti0;
        this.c = actionProvider;
    }

    @Override // defpackage.H2
    public boolean a() {
        return this.c.hasSubMenu();
    }

    @Override // defpackage.H2
    public View c() {
        return this.c.onCreateActionView();
    }

    @Override // defpackage.H2
    public boolean e() {
        return this.c.onPerformDefaultAction();
    }

    @Override // defpackage.H2
    public void f(SubMenu subMenu) {
        ActionProvider actionProvider = this.c;
        Objects.requireNonNull(this.d);
        actionProvider.onPrepareSubMenu(subMenu);
    }
}
