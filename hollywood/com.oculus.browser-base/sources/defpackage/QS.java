package defpackage;

import android.widget.FrameLayout;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: QS  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class QS implements AbstractC3841n80 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ DT f8761a;
    public final /* synthetic */ AbstractC1664aT b;

    public QS(AbstractC1664aT aTVar, DT dt) {
        this.b = aTVar;
        this.f8761a = dt;
    }

    @Override // defpackage.AbstractC3841n80
    public void a(AbstractC4524r80 r80, EnumC3157j80 j80) {
        if (!this.b.z()) {
            r80.Q().b(this);
            AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
            if (((FrameLayout) this.f8761a.G).isAttachedToWindow()) {
                this.b.x(this.f8761a);
            }
        }
    }
}
