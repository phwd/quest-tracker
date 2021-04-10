package X;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;

/* renamed from: X.3N  reason: invalid class name */
public final class AnonymousClass3N extends Handler {
    public final /* synthetic */ AnonymousClass3Q A00;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass3N(AnonymousClass3Q r1, Looper looper) {
        super(looper);
        this.A00 = r1;
    }

    public final void handleMessage(Message message) {
        if (message.what != 1) {
            super.handleMessage(message);
            return;
        }
        AnonymousClass3Q r0 = this.A00;
        synchronized (r0.A02) {
            ArrayList arrayList = r0.A00;
            int size = arrayList.size();
            if (size > 0) {
                arrayList.toArray(new AnonymousClass3O[size]);
                arrayList.clear();
                throw new NullPointerException("receivers");
            }
        }
    }
}
