package defpackage;

import android.os.Handler;
import android.os.Message;

/* renamed from: V21  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class V21 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ X21 f9062a;

    public V21(X21 x21, S21 s21) {
        this.f9062a = x21;
    }

    public void handleMessage(Message message) {
        int i = message.what;
        if (i == 1) {
            this.f9062a.d(true);
            ((D70) this.f9062a.b).s(null);
        } else if (i == 2) {
            ((D70) this.f9062a.b).s(null);
        }
    }
}
