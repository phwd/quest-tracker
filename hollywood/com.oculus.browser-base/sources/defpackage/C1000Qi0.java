package defpackage;

import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.FrameLayout;

/* renamed from: Qi0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1000Qi0 extends FrameLayout implements AbstractC0234Dv {
    public final CollapsibleActionView F;

    public C1000Qi0(View view) {
        super(view.getContext());
        this.F = (CollapsibleActionView) view;
        addView(view);
    }

    @Override // defpackage.AbstractC0234Dv
    public void b() {
        this.F.onActionViewExpanded();
    }

    @Override // defpackage.AbstractC0234Dv
    public void f() {
        this.F.onActionViewCollapsed();
    }
}
