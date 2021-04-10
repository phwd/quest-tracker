package X;

import android.os.Message;
import android.view.View;

/* renamed from: X.1sy  reason: invalid class name and case insensitive filesystem */
public class View$OnClickListenerC11861sy implements View.OnClickListener {
    public final /* synthetic */ C11731sk A00;

    public View$OnClickListenerC11861sy(C11731sk r1) {
        this.A00 = r1;
    }

    public final void onClick(View view) {
        Message message;
        Message obtain;
        C11731sk r3 = this.A00;
        if (((view == r3.A0H && (message = r3.A03) != null) || ((view == r3.A0F && (message = r3.A01) != null) || (view == r3.A0G && (message = r3.A02) != null))) && (obtain = Message.obtain(message)) != null) {
            obtain.sendToTarget();
        }
        r3.A00.obtainMessage(1, r3.A0X).sendToTarget();
    }
}
