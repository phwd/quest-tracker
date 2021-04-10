package X;

import android.view.Menu;
import android.view.View;
import android.view.Window;

/* renamed from: X.1rr  reason: invalid class name and case insensitive filesystem */
public class C11361rr extends Window$CallbackC11241rW {
    public final /* synthetic */ AnonymousClass1rR A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C11361rr(AnonymousClass1rR r1, Window.Callback callback) {
        super(callback);
        this.A00 = r1;
    }

    @Override // X.Window$CallbackC11241rW
    public final View onCreatePanelView(int i) {
        if (i == 0) {
            return new View(this.A00.A02.A3d());
        }
        return super.onCreatePanelView(i);
    }

    @Override // X.Window$CallbackC11241rW
    public final boolean onPreparePanel(int i, View view, Menu menu) {
        boolean onPreparePanel = super.onPreparePanel(i, view, menu);
        if (onPreparePanel) {
            AnonymousClass1rR r1 = this.A00;
            if (!r1.A00) {
                r1.A02.AA1();
                r1.A00 = true;
            }
        }
        return onPreparePanel;
    }
}
