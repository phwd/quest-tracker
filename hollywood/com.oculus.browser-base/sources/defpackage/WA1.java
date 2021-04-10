package defpackage;

import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* renamed from: WA1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class WA1 extends ZB1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ VA1 f9132a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WA1(VA1 va1, Looper looper) {
        super(looper);
        this.f9132a = va1;
    }

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            VA1 va1 = this.f9132a;
            va1.b.lock();
            try {
                if (va1.o()) {
                    va1.n();
                }
            } finally {
                va1.b.unlock();
            }
        } else if (i != 2) {
            StringBuilder sb = new StringBuilder(31);
            sb.append("Unknown message id: ");
            sb.append(i);
            Log.w("GoogleApiClientImpl", sb.toString());
        } else {
            VA1.m(this.f9132a);
        }
    }
}
