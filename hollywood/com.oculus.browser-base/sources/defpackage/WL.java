package defpackage;

import android.view.MotionEvent;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.EventForwarder;

/* renamed from: WL  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WL {

    /* renamed from: a  reason: collision with root package name */
    public final C1028Qw f9141a;

    public WL(C1028Qw qw) {
        this.f9141a = qw;
    }

    public void a() {
        C1028Qw qw = this.f9141a;
        D70 d70 = qw.b.K;
        if (d70 != null) {
            d70.o(qw.f8795a);
        }
        c(-qw.f8795a.top);
    }

    public final void b(MotionEvent motionEvent, boolean z) {
        int a2 = ZN0.a(motionEvent.getActionMasked());
        if (a2 == 0 || a2 == 9 || a2 == 7) {
            C1028Qw qw = this.f9141a;
            D70 d70 = qw.b.K;
            if (d70 != null) {
                d70.o(qw.f8795a);
            }
            c(-qw.f8795a.top);
        } else if (!z) {
        } else {
            if (a2 == 1 || a2 == 3 || a2 == 10) {
                c(0.0f);
            }
        }
    }

    public final void c(float f) {
        WebContents l;
        Tab tab = this.f9141a.b.f0;
        if (tab != null && (l = tab.l()) != null) {
            EventForwarder n0 = l.n0();
            n0.c = 0.0f;
            n0.d = f;
        }
    }
}
