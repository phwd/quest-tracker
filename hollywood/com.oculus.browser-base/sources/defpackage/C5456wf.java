package defpackage;

import java.util.regex.Pattern;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.tab.Tab;

/* renamed from: wf  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5456wf extends AbstractC5856yz {

    /* renamed from: a  reason: collision with root package name */
    public final ChromeActivity f11558a;
    public final boolean b;
    public final boolean c = AbstractC5686xz.c(6);
    public final float d;

    public C5456wf(C4700sA sAVar, int i) {
        float f;
        float f2 = sAVar.c;
        this.d = f2;
        ChromeActivity chromeActivity = sAVar.f11254a;
        this.f11558a = chromeActivity;
        Tab K0 = chromeActivity.K0();
        C1551Zj M0 = chromeActivity.M0();
        if (K0 == null) {
            f = 0.0f;
        } else {
            f = (((float) K0.b().getHeight()) - (((float) M0.M) + ((float) M0.R))) - (((float) M0.O) - ((float) M0.b()));
        }
        boolean z = false;
        if (f != 0.0f && ((float) i) * f2 >= (f * f2) - 56.0f) {
            z = true;
        }
        this.b = z;
    }

    @Override // defpackage.AbstractC5856yz
    public boolean a() {
        return this.c && this.b;
    }

    @Override // defpackage.AbstractC5856yz
    public boolean b() {
        return !this.c && this.b;
    }

    @Override // defpackage.AbstractC5856yz
    public void c() {
        if (this.c) {
            boolean z = this.b;
            Pattern pattern = AA.f7657a;
            AbstractC3100ip1.f10165a.a("Search.ContextualSearchBarOverlap", z);
        }
    }

    @Override // defpackage.AbstractC5856yz
    public void d(long j, long j2) {
        if (j2 > 0) {
            long j3 = j - j2;
            boolean z = this.b;
            Pattern pattern = AA.f7657a;
            AbstractC3364kK0.j(z ? "Search.ContextualSearchBarOverlap.PeekDuration" : "Search.ContextualSearchBarNoOverlap.PeekDuration", j3);
        }
    }

    @Override // defpackage.AbstractC5856yz
    public void e(AbstractC0486Hz hz) {
        ((C4017oA) hz).b(7, Boolean.valueOf(this.b));
    }

    @Override // defpackage.AbstractC5856yz
    public void g(boolean z, boolean z2) {
        int i = 5;
        if (AbstractC5686xz.c(5)) {
            boolean z3 = this.b;
            Pattern pattern = AA.f7657a;
            if (z2) {
                i = z3 ? !z ? 1 : 0 : z ? 2 : 3;
            } else if (!z3) {
                i = z ? 6 : 7;
            } else if (z) {
                i = 4;
            }
            AbstractC3364kK0.g("Search.ContextualSearchBarOverlapSeen", i, 8);
        }
    }
}
