package defpackage;

import android.os.Handler;
import android.os.Message;

/* renamed from: cg0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HandlerC2048cg0 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DialogC5460wg0 f9624a;

    public HandlerC2048cg0(DialogC5460wg0 wg0) {
        this.f9624a = wg0;
    }

    public void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            this.f9624a.n();
        } else if (i == 2) {
            DialogC5460wg0 wg0 = this.f9624a;
            if (wg0.Z != null) {
                wg0.Z = null;
                wg0.o();
            }
        }
    }
}
