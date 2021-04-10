package X;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* renamed from: X.0Ya  reason: invalid class name and case insensitive filesystem */
public class HandlerC01920Ya extends Handler {
    public volatile boolean A00;
    public final /* synthetic */ AbstractServiceC01930Yb A01;

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x000d */
    /* JADX WARNING: Removed duplicated region for block: B:4:0x000d A[LOOP:0: B:4:0x000d->B:15:0x000d, LOOP_START, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void A00() {
        /*
            r2 = this;
            r0 = 3
            android.os.Message r0 = r2.obtainMessage(r0)
            boolean r0 = r2.sendMessage(r0)
            if (r0 == 0) goto L_0x001a
            r1 = r2
            monitor-enter(r1)
        L_0x000d:
            boolean r0 = r2.A00     // Catch:{ all -> 0x0017 }
            if (r0 != 0) goto L_0x0015
            r2.wait()     // Catch:{ InterruptedException -> 0x000d }
            goto L_0x000d
        L_0x0015:
            monitor-exit(r1)
            return
        L_0x0017:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
        L_0x001a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.HandlerC01920Ya.A00():void");
    }

    public void A01() {
        sendMessage(obtainMessage(1));
    }

    public void A02(Intent intent, int i, int i2) {
        sendMessage(obtainMessage(2, i, i2, intent));
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HandlerC01920Ya(AbstractServiceC01930Yb r1, Looper looper) {
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
                    this.A01.A0E();
                    synchronized (this) {
                        this.A00 = true;
                        notifyAll();
                    }
                    return;
                }
                str = "Unsupported message";
            } else if (!this.A00) {
                this.A01.A0F((Intent) message.obj, message.arg1, message.arg2);
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
