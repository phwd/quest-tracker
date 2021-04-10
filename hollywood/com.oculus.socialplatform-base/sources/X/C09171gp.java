package X;

import android.os.Handler;
import android.os.Message;

/* renamed from: X.1gp  reason: invalid class name and case insensitive filesystem */
public class C09171gp implements Handler.Callback {
    public final boolean handleMessage(Message message) {
        if (message.what != 1) {
            return false;
        }
        C09071gP r1 = (C09071gP) message.obj;
        r1.A00.clear(r1);
        return true;
    }
}
