package X;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import androidx.annotation.NonNull;

/* renamed from: X.1sh  reason: invalid class name */
public final class AnonymousClass1sh implements AbstractC11941tc, DialogInterface.OnClickListener, DialogInterface.OnKeyListener, DialogInterface.OnDismissListener {
    public DialogInterfaceC11721sj A00;
    public C11711si A01;
    public C11581sN A02;

    @Override // X.AbstractC11941tc
    public final void A6r(@NonNull C11581sN r2, boolean z) {
        DialogInterfaceC11721sj r0;
        if ((z || r2 == this.A02) && (r0 = this.A00) != null) {
            r0.dismiss();
        }
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        C11581sN r3 = this.A02;
        C11711si r1 = this.A01;
        C11761sn r0 = r1.A05;
        if (r0 == null) {
            r0 = new C11761sn(r1);
            r1.A05 = r0;
        }
        r3.A0K((C11601sP) r0.getItem(i), null, 0);
    }

    public final void onDismiss(DialogInterface dialogInterface) {
        this.A01.A6r(this.A02, true);
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

    public AnonymousClass1sh(C11581sN r1) {
        this.A02 = r1;
    }

    @Override // X.AbstractC11941tc
    public final boolean A7T(@NonNull C11581sN r2) {
        return false;
    }
}
