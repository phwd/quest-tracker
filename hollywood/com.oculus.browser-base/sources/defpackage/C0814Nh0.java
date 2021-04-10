package defpackage;

import J.N;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.util.Collections;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;

/* renamed from: Nh0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0814Nh0 extends AbstractC6022zx1 {
    public final /* synthetic */ WebContents G;
    public final /* synthetic */ C0936Ph0 H;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public C0814Nh0(C0936Ph0 ph0, WebContents webContents, WebContents webContents2) {
        super(webContents);
        this.H = ph0;
        this.G = webContents2;
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFinishNavigation(NavigationHandle navigationHandle) {
        if (navigationHandle.f && navigationHandle.f10940a && !navigationHandle.c) {
            this.H.i = N.MeroQv$e(this.G.u().e().h());
            C0936Ph0 ph0 = this.H;
            ph0.g = null;
            ph0.f = null;
            ph0.n = null;
            ph0.o = ph0.f();
            this.H.p = Collections.emptySet();
            if (!this.H.i()) {
                C0936Ph0 ph02 = this.H;
                C0013Ae0 ae0 = ph02.k;
                ae0.c = ph02.i;
                ae0.g = ph02.g;
                ae0.i = ph02.f;
                ae0.f7683a = ph02.o;
                ae0.n = ph02.p;
                ph02.k();
            }
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void titleWasSet(String str) {
        String b = C0936Ph0.b(this.H, str);
        if (!TextUtils.equals(this.H.l, b)) {
            C0936Ph0 ph0 = this.H;
            ph0.l = b;
            C0936Ph0.a(ph0);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void wasShown() {
        int id = this.H.f8706a.f9100a.getId();
        C5624xe0 a2 = AbstractC0196De0.a(R.id.media_playback_notification);
        if (a2 != null) {
            a2.a(id);
        }
    }
}
