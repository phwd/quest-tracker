package X;

import android.os.Message;
import android.view.View;

/* renamed from: X.02b  reason: invalid class name */
public class AnonymousClass02b implements View.OnClickListener {
    public final /* synthetic */ AnonymousClass02i A00;

    public AnonymousClass02b(AnonymousClass02i r1) {
        this.A00 = r1;
    }

    public final void onClick(View view) {
        Message message;
        Message obtain;
        AnonymousClass02i r3 = this.A00;
        if (((view == r3.A0H && (message = r3.A03) != null) || ((view == r3.A0F && (message = r3.A01) != null) || (view == r3.A0G && (message = r3.A02) != null))) && (obtain = Message.obtain(message)) != null) {
            obtain.sendToTarget();
        }
        r3.A00.obtainMessage(1, r3.A0X).sendToTarget();
    }
}
