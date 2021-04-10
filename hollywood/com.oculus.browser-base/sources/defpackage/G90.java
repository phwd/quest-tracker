package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* renamed from: G90  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class G90 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ H90 f8069a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public G90(H90 h90, Looper looper) {
        super(looper);
        this.f8069a = h90;
    }

    public void handleMessage(Message message) {
        H90 h90 = this.f8069a;
        float f = h90.c + 0.1f;
        h90.c = f;
        h90.c = Math.min(1.0f, f);
        H90 h902 = this.f8069a;
        h902.f8140a.k(F90.b, h902.c);
        if (AbstractC4089od0.a(this.f8069a.c, 1.0f)) {
            this.f8069a.f8140a.l(F90.f7995a, 1);
        } else {
            sendEmptyMessageDelayed(1, 10);
        }
    }
}
