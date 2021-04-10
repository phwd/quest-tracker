package X;

import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import java.lang.ref.WeakReference;

/* renamed from: X.02f  reason: invalid class name */
public final class AnonymousClass02f extends Handler {
    public WeakReference<DialogInterface> A00;

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == -3 || i == -2 || i == -1) {
            ((DialogInterface.OnClickListener) message.obj).onClick(this.A00.get(), i);
        } else if (i == 1) {
            ((DialogInterface) message.obj).dismiss();
        }
    }

    public AnonymousClass02f(DialogInterface dialogInterface) {
        this.A00 = new WeakReference<>(dialogInterface);
    }
}
