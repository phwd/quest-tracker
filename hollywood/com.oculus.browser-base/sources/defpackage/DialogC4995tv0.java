package defpackage;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;

/* renamed from: tv0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogC4995tv0 extends Dialog {
    public final /* synthetic */ C5505wv0 F;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DialogC4995tv0(C5505wv0 wv0, Context context) {
        super(context);
        this.F = wv0;
    }

    public final void a() {
        super.dismiss();
    }

    public void dismiss() {
        C5505wv0 wv0 = this.F;
        if (wv0.j) {
            Animator animator = wv0.i;
            if (animator != null && animator.isRunning()) {
                this.F.i.cancel();
            }
            this.F.c.removeCallbacks(null);
            super.dismiss();
            return;
        }
        C5505wv0.a(wv0, false, new RunnableC4655rv0(this)).start();
    }
}
