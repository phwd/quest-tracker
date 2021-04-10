package X;

import android.view.Menu;
import android.view.View;
import android.view.Window;

/* renamed from: X.0en  reason: invalid class name and case insensitive filesystem */
public class C04380en extends AnonymousClass03L {
    public final /* synthetic */ C04370em A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C04380en(C04370em r1, Window.Callback callback) {
        super(callback);
        this.A00 = r1;
    }

    @Override // X.AnonymousClass03L
    public final View onCreatePanelView(int i) {
        if (i == 0) {
            return new View(this.A00.A02.A3F());
        }
        return super.onCreatePanelView(i);
    }

    @Override // X.AnonymousClass03L
    public final boolean onPreparePanel(int i, View view, Menu menu) {
        boolean onPreparePanel = super.onPreparePanel(i, view, menu);
        if (onPreparePanel) {
            C04370em r1 = this.A00;
            if (!r1.A00) {
                r1.A02.A81();
                r1.A00 = true;
            }
        }
        return onPreparePanel;
    }
}
