package defpackage;

import android.os.Handler;
import android.os.Message;

/* renamed from: Ob1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HandlerC0858Ob1 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C0919Pb1 f8634a;

    public HandlerC0858Ob1(C0919Pb1 pb1) {
        this.f8634a = pb1;
    }

    public void handleMessage(Message message) {
        C0980Qb1 qb1;
        boolean z;
        if (message != null && message.what == 1 && (z = (qb1 = this.f8634a.b).L) && z) {
            qb1.L = false;
            qb1.r();
        }
    }
}
