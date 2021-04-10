package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* renamed from: wh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HandlerC5463wh0 extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AbstractC5803yh0 f11562a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public HandlerC5463wh0(AbstractC5803yh0 yh0, Looper looper) {
        super(looper);
        this.f11562a = yh0;
    }

    public void handleMessage(Message message) {
        AbstractC5973zh0 zh0;
        AbstractC5803yh0 yh0;
        HandlerC5463wh0 wh0;
        if (message.what == 1) {
            synchronized (this.f11562a.f11694a) {
                zh0 = (AbstractC5973zh0) this.f11562a.d.get();
                yh0 = this.f11562a;
                wh0 = yh0.e;
            }
            if (zh0 != null && yh0 == zh0.j() && wh0 != null) {
                zh0.d((C0997Qh0) message.obj);
                this.f11562a.a(zh0, wh0);
                zh0.d(null);
            }
        }
    }
}
