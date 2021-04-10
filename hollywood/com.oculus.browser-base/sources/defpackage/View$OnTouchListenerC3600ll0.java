package defpackage;

import android.view.MotionEvent;
import android.view.View;
import org.chromium.components.browser_ui.modaldialog.ModalDialogView;

/* renamed from: ll0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnTouchListenerC3600ll0 implements View.OnTouchListener {
    public final ModalDialogView F;

    public View$OnTouchListenerC3600ll0(ModalDialogView modalDialogView) {
        this.F = modalDialogView;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return this.F.b(motionEvent);
    }
}
