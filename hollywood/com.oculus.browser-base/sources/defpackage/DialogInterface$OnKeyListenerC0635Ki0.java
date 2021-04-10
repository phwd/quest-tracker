package defpackage;

import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;

/* renamed from: Ki0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogInterface$OnKeyListenerC0635Ki0 implements DialogInterface.OnKeyListener, DialogInterface.OnClickListener, DialogInterface.OnDismissListener, AbstractC1886bj0 {
    public C4616ri0 F;
    public DialogC2461f4 G;
    public C1614a90 H;

    public DialogInterface$OnKeyListenerC0635Ki0(C4616ri0 ri0) {
        this.F = ri0;
    }

    @Override // defpackage.AbstractC1886bj0
    public void a(C4616ri0 ri0, boolean z) {
        DialogC2461f4 f4Var;
        if ((z || ri0 == this.F) && (f4Var = this.G) != null) {
            f4Var.dismiss();
        }
    }

    @Override // defpackage.AbstractC1886bj0
    public boolean b(C4616ri0 ri0) {
        return false;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.F.q(((Z80) this.H.b()).getItem(i), 0);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        C1614a90 a90 = this.H;
        C4616ri0 ri0 = this.F;
        AbstractC1886bj0 bj0 = a90.f9412J;
        if (bj0 != null) {
            bj0.a(ri0, true);
        }
    }

    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        Window window;
        View decorView;
        KeyEvent.DispatcherState keyDispatcherState;
        View decorView2;
        KeyEvent.DispatcherState keyDispatcherState2;
        if (i == 82 || i == 4) {
            if (keyEvent.getAction() == 0 && keyEvent.getRepeatCount() == 0) {
                Window window2 = this.G.getWindow();
                if (!(window2 == null || (decorView2 = window2.getDecorView()) == null || (keyDispatcherState2 = decorView2.getKeyDispatcherState()) == null)) {
                    keyDispatcherState2.startTracking(keyEvent, this);
                    return true;
                }
            } else if (keyEvent.getAction() == 1 && !keyEvent.isCanceled() && (window = this.G.getWindow()) != null && (decorView = window.getDecorView()) != null && (keyDispatcherState = decorView.getKeyDispatcherState()) != null && keyDispatcherState.isTracking(keyEvent)) {
                this.F.c(true);
                dialogInterface.dismiss();
                return true;
            }
        }
        return this.F.performShortcut(i, keyEvent, 0);
    }
}
