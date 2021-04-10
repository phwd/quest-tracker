package X;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* renamed from: X.11Q  reason: invalid class name */
public class AnonymousClass11Q extends Handler {
    public volatile boolean A00;
    public final /* synthetic */ AnonymousClass11R A01;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass11Q(AnonymousClass11R r1, Looper looper) {
        super(looper);
        this.A01 = r1;
    }

    public final void handleMessage(Message message) {
        String str;
        if (message != null) {
            int i = message.what;
            if (i == 1) {
                this.A01.A0B();
                return;
            } else if (i != 2) {
                if (i == 3) {
                    this.A01.A0D();
                    synchronized (this) {
                        this.A00 = true;
                        notifyAll();
                    }
                    return;
                }
                str = "Unsupported message";
            } else if (!this.A00) {
                this.A01.A0C((Intent) message.obj, message.arg1, message.arg2);
                return;
            } else {
                return;
            }
        } else {
            str = "Message is null";
        }
        throw new IllegalStateException(str);
    }
}
