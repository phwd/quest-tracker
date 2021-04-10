package X;

import android.os.Handler;
import android.os.Message;

/* renamed from: X.1ek  reason: invalid class name and case insensitive filesystem */
public final class C08501ek implements Handler.Callback {
    public final boolean handleMessage(Message message) {
        if (message.what != 1) {
            return false;
        }
        ((AnonymousClass1fR) message.obj).A8u();
        return true;
    }
}
