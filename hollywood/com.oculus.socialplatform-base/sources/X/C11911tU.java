package X;

import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.FrameLayout;

/* renamed from: X.1tU  reason: invalid class name and case insensitive filesystem */
public class C11911tU extends FrameLayout implements AbstractC12011tl {
    public final CollapsibleActionView A00;

    @Override // X.AbstractC12011tl
    public final void onActionViewCollapsed() {
        this.A00.onActionViewCollapsed();
    }

    @Override // X.AbstractC12011tl
    public final void onActionViewExpanded() {
        this.A00.onActionViewExpanded();
    }

    public C11911tU(View view) {
        super(view.getContext());
        this.A00 = (CollapsibleActionView) view;
        addView(view);
    }
}
