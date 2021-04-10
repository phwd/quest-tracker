package defpackage;

import android.app.Dialog;
import android.content.Context;

/* renamed from: h40  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DialogC2804h40 extends Dialog {
    public final /* synthetic */ C3316k40 F;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DialogC2804h40(C3316k40 k40, Context context) {
        super(context);
        this.F = k40;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (!this.F.l && !z) {
            dismiss();
        }
        this.F.l = false;
    }
}
