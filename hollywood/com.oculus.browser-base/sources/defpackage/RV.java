package defpackage;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* renamed from: RV  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class RV extends ZB1 {

    /* renamed from: a  reason: collision with root package name */
    public final Context f8836a;
    public final /* synthetic */ SV b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RV(SV sv, Context context) {
        super(Looper.myLooper() == null ? Looper.getMainLooper() : Looper.myLooper());
        this.b = sv;
        this.f8836a = context.getApplicationContext();
    }

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i != 1) {
            StringBuilder sb = new StringBuilder(50);
            sb.append("Don't know how to handle this message: ");
            sb.append(i);
            Log.w("GoogleApiAvailability", sb.toString());
            return;
        }
        int e = this.b.e(this.f8836a);
        if (this.b.c(e)) {
            this.b.g(this.f8836a, e);
        }
    }
}
