package X;

import android.os.Handler;
import android.os.Message;

/* renamed from: X.1gh  reason: invalid class name */
public class AnonymousClass1gh implements Handler.Callback {
    public final /* synthetic */ AnonymousClass1g5 A00;

    public AnonymousClass1gh(AnonymousClass1g5 r1) {
        this.A00 = r1;
    }

    public final boolean handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            this.A00.A02((AnonymousClass1gV) message.obj);
            return true;
        } else if (i != 2) {
            return false;
        } else {
            this.A00.A0D.clear((AnonymousClass1gU) message.obj);
            return false;
        }
    }
}
