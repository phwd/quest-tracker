package X;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.io.ByteArrayOutputStream;
import java.util.Collections;

/* renamed from: X.Gd  reason: case insensitive filesystem */
public class HandlerC0083Gd extends Handler {
    public final /* synthetic */ C0269Yt A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HandlerC0083Gd(C0269Yt yt, Looper looper) {
        super(looper);
        this.A00 = yt;
    }

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            C0269Yt yt = this.A00;
            HJ hj = new HJ(yt.A05, yt.A04.A02, Collections.singletonList(new C00248o((ByteArrayOutputStream) message.obj, yt.A03)).iterator(), new C0270Yu());
            while (hj.A00.hasNext()) {
                hj.A00();
            }
            return;
        }
        throw new IllegalArgumentException(AnonymousClass06.A01("Unknown what=", i));
    }
}
