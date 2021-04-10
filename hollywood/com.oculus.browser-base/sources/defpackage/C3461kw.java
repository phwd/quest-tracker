package defpackage;

import java.util.ArrayList;
import java.util.List;
import org.chromium.base.Callback;

/* renamed from: kw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3461kw extends AbstractC2742gk {

    /* renamed from: J  reason: collision with root package name */
    public final List f10315J = new ArrayList();
    public final Callback K = new C3290jw(this);
    public boolean L = true;

    public C3461kw(AbstractC2742gk... gkVarArr) {
        super(3);
        for (AbstractC2742gk gkVar : gkVarArr) {
            o(gkVar);
        }
        super.m(Integer.valueOf(p()));
    }

    @Override // defpackage.AbstractC2742gk
    public void n(Integer num) {
        if (!this.L) {
            super.m(num);
            return;
        }
        throw new IllegalStateException("Calling set on the composed delegate is not allowed.");
    }

    public void o(AbstractC2742gk gkVar) {
        this.f10315J.add(gkVar);
        gkVar.l(this.K);
    }

    public final int p() {
        int i = 0;
        boolean z = false;
        while (true) {
            boolean z2 = true;
            if (i < this.f10315J.size()) {
                int intValue = ((Integer) ((AbstractC2742gk) this.f10315J.get(i)).H).intValue();
                if (intValue == 2) {
                    return 2;
                }
                if (intValue != 1) {
                    z2 = false;
                }
                z |= z2;
                i++;
            } else if (z) {
                return 1;
            } else {
                return 3;
            }
        }
    }

    public final /* synthetic */ void q() {
        super.m(Integer.valueOf(p()));
    }
}
