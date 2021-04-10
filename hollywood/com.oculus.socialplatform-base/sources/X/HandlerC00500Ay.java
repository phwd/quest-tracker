package X;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;

/* renamed from: X.0Ay  reason: invalid class name and case insensitive filesystem */
public class HandlerC00500Ay extends Handler {
    public final /* synthetic */ AnonymousClass0B1 A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HandlerC00500Ay(AnonymousClass0B1 r1, Looper looper) {
        super(looper);
        this.A00 = r1;
    }

    public final void handleMessage(Message message) {
        if (message.what != 1) {
            super.handleMessage(message);
            return;
        }
        AnonymousClass0B1 r0 = this.A00;
        synchronized (r0.A02) {
            ArrayList<AnonymousClass0Az> arrayList = r0.A00;
            int size = arrayList.size();
            if (size > 0) {
                arrayList.toArray(new AnonymousClass0Az[size]);
                arrayList.clear();
                throw new NullPointerException("receivers");
            }
        }
    }
}
