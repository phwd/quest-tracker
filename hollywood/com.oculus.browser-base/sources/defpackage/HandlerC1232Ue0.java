package defpackage;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/* renamed from: Ue0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HandlerC1232Ue0 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C1293Ve0 f9038a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HandlerC1232Ue0(C1293Ve0 ve0) {
        super(Looper.getMainLooper());
        this.f9038a = ve0;
    }

    public void handleMessage(Message message) {
        String str;
        int i = message.what;
        int i2 = message.arg1;
        Object obj = message.obj;
        Bundle peekData = message.peekData();
        C4050oL0 ol0 = (C4050oL0) this.f9038a.j.get(i2);
        if (ol0 == null) {
            Log.w("MR2Provider", "Pending callback not found for control request.");
            return;
        }
        this.f9038a.j.remove(i2);
        if (i == 3) {
            ol0.b((Bundle) obj);
        } else if (i == 4) {
            if (peekData == null) {
                str = null;
            } else {
                str = peekData.getString("error");
            }
            ol0.a(str, (Bundle) obj);
        }
    }
}
