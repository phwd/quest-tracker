package X;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;

/* renamed from: X.0eY  reason: invalid class name and case insensitive filesystem */
public final class DialogInterface$OnClickListenerC04270eY implements AbstractC000503a, DialogInterface.OnClickListener, DialogInterface.OnKeyListener, DialogInterface.OnDismissListener {
    public AnonymousClass0N6 A00;
    public C04290ea A01;
    public C04280eZ A02;

    @Override // X.AbstractC000503a
    public final void A5x(@NonNull C04280eZ r2, boolean z) {
        AnonymousClass0N6 r0;
        if ((z || r2 == this.A02) && (r0 = this.A00) != null) {
            r0.dismiss();
        }
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        C04280eZ r3 = this.A02;
        C04290ea r1 = this.A01;
        AnonymousClass03T r0 = r1.A05;
        if (r0 == null) {
            r0 = new AnonymousClass03T(r1);
            r1.A05 = r0;
        }
        r3.A0K((C04250eW) r0.getItem(i), null, 0);
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        this.A01.A5x(this.A02, true);
    }

    public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.A00.getWindow();
                if (!(window2 == null || (decorView2 = window2.getDecorView()) == null || (keyDispatcherState2 = decorView2.getKeyDispatcherState()) == null)) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.A00.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.A02.A0F(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.A02.performShortcut(i, keyEvent, 0);
    }

    public DialogInterface$OnClickListenerC04270eY(C04280eZ r1) {
        this.A02 = r1;
    }

    @Override // X.AbstractC000503a
    public final boolean A6L(@NonNull C04280eZ r2) {
        return false;
    }
}
