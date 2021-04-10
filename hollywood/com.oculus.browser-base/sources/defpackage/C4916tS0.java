package defpackage;

import android.os.Handler;
import android.os.Message;
import java.util.Objects;

/* renamed from: tS0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4916tS0 implements Handler.Callback {
    public final /* synthetic */ C5766yS0 F;

    public C4916tS0(C5766yS0 ys0) {
        this.F = ys0;
    }

    public boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            C5766yS0 ys0 = this.F;
            synchronized (ys0.f11682a) {
                if (!ys0.c.hasMessages(1)) {
                    ys0.b.quit();
                    ys0.b = null;
                    ys0.c = null;
                }
            }
            return true;
        } else if (i != 1) {
            return true;
        } else {
            C5766yS0 ys02 = this.F;
            Objects.requireNonNull(ys02);
            ((Runnable) message.obj).run();
            synchronized (ys02.f11682a) {
                ys02.c.removeMessages(0);
                Handler handler = ys02.c;
                handler.sendMessageDelayed(handler.obtainMessage(0), (long) 10000);
            }
            return true;
        }
    }
}
