package defpackage;

import J.N;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.password_manager.settings.PasswordUIView;

/* renamed from: sy0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4834sy0 implements AbstractC4323py0 {
    public PasswordUIView F;
    public final C1322Vq0 G = new C1322Vq0();

    public C4834sy0(AbstractC4494qy0 qy0) {
    }

    public void a(AbstractC4323py0 py0) {
        Object obj = ThreadUtils.f10596a;
        if (this.F == null) {
            this.F = new PasswordUIView(this);
        }
        this.G.b(py0);
    }

    public void b(AbstractC4323py0 py0) {
        Object obj = ThreadUtils.f10596a;
        this.G.c(py0);
        if (this.G.isEmpty()) {
            PasswordUIView passwordUIView = this.F;
            long j = passwordUIView.f10742a;
            if (j != 0) {
                N.MFhG46xL(j, passwordUIView);
                passwordUIView.f10742a = 0;
            }
            this.F = null;
        }
    }

    @Override // defpackage.AbstractC4323py0
    public void j(int i) {
        Object obj = ThreadUtils.f10596a;
        Iterator it = this.G.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC4323py0) uq0.next()).j(i);
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC4323py0
    public void l(int i) {
        Object obj = ThreadUtils.f10596a;
        Iterator it = this.G.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC4323py0) uq0.next()).l(i);
            } else {
                return;
            }
        }
    }
}
