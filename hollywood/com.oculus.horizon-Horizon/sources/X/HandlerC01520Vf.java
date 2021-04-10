package X;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* renamed from: X.0Vf  reason: invalid class name and case insensitive filesystem */
public class HandlerC01520Vf extends Handler {
    public final /* synthetic */ C06570ne A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HandlerC01520Vf(C06570ne r1, Looper looper) {
        super(looper);
        this.A00 = r1;
    }

    public final void handleMessage(Message message) {
        if (message.what == 1) {
            C06570ne r1 = this.A00;
            C06570ne.A01(r1, new AnonymousClass0Vi(r1));
            C06570ne.A01(r1, new AnonymousClass0Vj(r1));
        }
    }
}
