package defpackage;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;

/* renamed from: wg  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HandlerC5459wg extends ZB1 {
    public HandlerC5459wg(Looper looper) {
        super(looper);
    }

    public void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            Pair pair = (Pair) message.obj;
            try {
                ((BM0) pair.first).a((AM0) pair.second);
            } catch (RuntimeException e) {
                throw e;
            }
        } else if (i != 2) {
            Log.wtf("BasePendingResult", AbstractC2531fV.s(45, "Don't know how to handle message: ", i), new Exception());
        } else {
            ((BasePendingResult) message.obj).h(Status.H);
        }
    }
}
