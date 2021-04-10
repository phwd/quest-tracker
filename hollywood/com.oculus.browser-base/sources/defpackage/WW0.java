package defpackage;

import org.chromium.base.Callback;
import org.chromium.components.messages.MessageBannerView;
import org.chromium.components.messages.MessageContainer;

/* renamed from: WW0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WW0 implements AbstractC4452qk0 {

    /* renamed from: a  reason: collision with root package name */
    public C3594lj0 f9152a;
    public MessageBannerView b;
    public final MessageContainer c;
    public final UH0 d;
    public final C0759Mj0 e;
    public C3081ij0 f;
    public final Q31 g;
    public final X h;
    public final Callback i;

    public WW0(MessageContainer messageContainer, UH0 uh0, C0759Mj0 mj0, Q31 q31, X x, Callback callback) {
        this.d = uh0;
        this.c = messageContainer;
        this.e = mj0;
        this.h = x;
        this.f = new C3081ij0(x.d() ? 30000 : 10000);
        this.g = q31;
        this.i = callback;
        uh0.m(AbstractC4619rj0.q, new PW0(this));
    }

    public void a(int i2) {
        this.f.a();
        Callback callback = (Callback) this.d.g(AbstractC4619rj0.l);
        if (callback != null) {
            callback.onResult(Integer.valueOf(i2));
        }
    }

    public void b(boolean z, Runnable runnable) {
        this.f.a();
        this.f9152a.f10366a.I.m(AbstractC4619rj0.p, null);
        SW0 sw0 = new SW0(this, runnable);
        C4449qj0 qj0 = this.f9152a.f10366a;
        qj0.a();
        if (!z) {
            qj0.I.k(AbstractC4619rj0.o, 0.0f);
            qj0.I.k(AbstractC4619rj0.n, (float) (-((Integer) qj0.f11158J.get()).intValue()));
            qj0.Q = 0;
        }
        if (qj0.Q == 0) {
            sw0.run();
        } else {
            qj0.c(true, (float) (-((Integer) qj0.f11158J.get()).intValue()), false, sw0);
        }
    }
}
