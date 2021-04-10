package X;

import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.FrameLayout;

/* renamed from: X.0eU  reason: invalid class name and case insensitive filesystem */
public class C04240eU extends FrameLayout implements AnonymousClass03E {
    public final CollapsibleActionView A00;

    @Override // X.AnonymousClass03E
    public final void onActionViewCollapsed() {
        this.A00.onActionViewCollapsed();
    }

    @Override // X.AnonymousClass03E
    public final void onActionViewExpanded() {
        this.A00.onActionViewExpanded();
    }

    public C04240eU(View view) {
        super(view.getContext());
        this.A00 = (CollapsibleActionView) view;
        addView(view);
    }
}
