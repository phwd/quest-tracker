package X;

import android.os.Handler;
import android.os.Message;

/* renamed from: X.06H  reason: invalid class name */
public class AnonymousClass06H implements Handler.Callback {
    public final /* synthetic */ AnonymousClass06M A00;

    public AnonymousClass06H(AnonymousClass06M r1) {
        this.A00 = r1;
    }

    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            AnonymousClass06M r2 = this.A00;
            synchronized (r2.A05) {
                if (!r2.A00.hasMessages(1)) {
                    r2.A01.quit();
                    r2.A01 = null;
                    r2.A00 = null;
                }
            }
        } else if (i == 1) {
            AnonymousClass06M r6 = this.A00;
            ((Runnable) message.obj).run();
            synchronized (r6.A05) {
                r6.A00.removeMessages(0);
                Handler handler = r6.A00;
                handler.sendMessageDelayed(handler.obtainMessage(0), (long) r6.A04);
            }
            return true;
        }
        return true;
    }
}
