package X;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* renamed from: X.0wN  reason: invalid class name and case insensitive filesystem */
public class HandlerC07990wN extends Handler {
    public final /* synthetic */ C07980wL A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HandlerC07990wN(C07980wL r1, Looper looper) {
        super(looper);
        this.A00 = r1;
    }

    public final void handleMessage(Message message) {
        if (message.what == 1) {
            C07980wL r1 = this.A00;
            C07980wL.A01(r1, new RunnableC08180wi(r1));
            C07980wL.A01(r1, new RunnableC08080wX(r1));
        }
    }
}
