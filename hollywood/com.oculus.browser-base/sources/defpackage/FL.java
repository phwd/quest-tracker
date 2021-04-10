package defpackage;

import android.widget.TextView;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* renamed from: FL  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class FL extends AbstractC6022zx1 {
    public boolean G;
    public GURL H;
    public final /* synthetic */ IL I;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FL(IL il, WebContents webContents) {
        super(webContents);
        this.I = il;
    }

    @Override // defpackage.AbstractC6022zx1
    public void didFinishNavigation(NavigationHandle navigationHandle) {
        if (!navigationHandle.f10940a) {
            return;
        }
        if (navigationHandle.f) {
            this.G = navigationHandle.g;
            ((TextView) this.I.f.f.findViewById(R.id.origin)).setText(AbstractC1911br1.c(((WebContents) this.F.get()).u(), 1));
            return;
        }
        C1184Ti1.a(ContextUtils.getApplicationContext(), R.string.f51970_resource_name_obfuscated_RES_2131952514, 0).b.show();
        IL il = this.I;
        ((C5638xj) il.f8219a).p(il.f, true, 0);
    }

    @Override // defpackage.AbstractC6022zx1
    public void didStartNavigation(NavigationHandle navigationHandle) {
        Objects.requireNonNull(this.I.c);
        AbstractC3535lK0.a("EphemeralTab.NavigateLink");
        if (navigationHandle.f10940a && !navigationHandle.c) {
            GURL gurl = navigationHandle.e;
            if (!gurl.equals(this.H)) {
                if (!this.G || !AbstractC5154ur1.h(gurl)) {
                    this.H = gurl;
                    IL il = this.I;
                    CL cl = il.b;
                    EL el = new EL(this);
                    cl.b.c(il.i, gurl.h(), cl.c, new BL(cl, el));
                    return;
                }
                IL il2 = this.I;
                ((C5638xj) il2.f8219a).p(il2.f, true, 0);
                this.H = null;
            }
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void loadProgressChanged(float f) {
        NL nl = this.I.f;
        if (nl != null) {
            nl.w(f);
        }
    }

    @Override // defpackage.AbstractC6022zx1
    public void titleWasSet(String str) {
        ((TextView) this.I.f.f.findViewById(R.id.title)).setText(str);
    }
}
