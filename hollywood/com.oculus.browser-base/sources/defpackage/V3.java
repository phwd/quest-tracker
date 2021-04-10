package defpackage;

import android.os.Message;
import android.view.View;

/* renamed from: V3  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class V3 implements View.OnClickListener {
    public final /* synthetic */ C2120d4 F;

    public V3(C2120d4 d4Var) {
        this.F = d4Var;
    }

    public void onClick(View view) {
        Message message;
        Message message2;
        Message message3;
        Message message4;
        C2120d4 d4Var = this.F;
        if (view == d4Var.o && (message4 = d4Var.q) != null) {
            message = Message.obtain(message4);
        } else if (view != d4Var.s || (message3 = d4Var.u) == null) {
            message = (view != d4Var.w || (message2 = d4Var.y) == null) ? null : Message.obtain(message2);
        } else {
            message = Message.obtain(message3);
        }
        if (message != null) {
            message.sendToTarget();
        }
        C2120d4 d4Var2 = this.F;
        d4Var2.Q.obtainMessage(1, d4Var2.b).sendToTarget();
    }
}
