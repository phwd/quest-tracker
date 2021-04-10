package defpackage;

import org.chromium.base.Callback;
import org.chromium.components.messages.MessageContainer;

/* renamed from: Nj0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0820Nj0 extends Ep1 implements AbstractC1406Xb0 {
    public final C3084ik0 F = new C3084ik0();
    public final MessageContainer G;
    public final Q31 H;
    public final X I;

    /* renamed from: J  reason: collision with root package name */
    public final Callback f8571J;

    public C0820Nj0(MessageContainer messageContainer, Q31 q31, X x, Callback callback) {
        this.G = messageContainer;
        this.H = q31;
        this.I = x;
        this.f8571J = callback;
    }

    public void j(int i) {
        C3084ik0 ik0 = this.F;
        AbstractC4452qk0 qk0 = ik0.c;
        if (qk0 != null) {
            ik0.f10159a.remove(qk0);
            ((WW0) ik0.c).b(false, new RunnableC2914hk0(ik0, i));
        }
        for (AbstractC4452qk0 qk02 : ik0.f10159a) {
            ((WW0) qk02).a(i);
        }
        ik0.b.clear();
        ik0.f10159a.clear();
    }

    public void k(UH0 uh0, int i) {
        C3084ik0 ik0 = this.F;
        AbstractC4452qk0 qk0 = (AbstractC4452qk0) ik0.b.get(uh0);
        if (qk0 != null) {
            ik0.b.remove(uh0);
            ik0.f10159a.remove(qk0);
            RunnableC2231dk0 dk0 = new RunnableC2231dk0(ik0, qk0, i);
            AbstractC4452qk0 qk02 = ik0.c;
            if (qk02 == qk0) {
                ((WW0) qk02).b(true, new RunnableC2401ek0(ik0, dk0));
                return;
            }
            dk0.run();
        }
    }
}
