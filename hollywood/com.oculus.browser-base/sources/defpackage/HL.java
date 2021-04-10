package defpackage;

import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;
import com.oculus.browser.R;
import org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.url.GURL;

/* renamed from: HL  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class HL extends WebContentsDelegateAndroid {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ IL f8153a;

    public HL(IL il) {
        this.f8153a = il;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public int getTopControlsHeight() {
        return this.f8153a.d;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void loadingStateChanged(boolean z) {
        WebContents webContents = this.f8153a.e;
        if (webContents != null && webContents.d()) {
            NL nl = this.f8153a.f;
            if (nl != null) {
                nl.w(0.0f);
                this.f8153a.f.x(true);
                return;
            }
            return;
        }
        new Handler().postDelayed(new GL(this), 64);
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void openNewTab(GURL gurl, String str, ResourceRequestBody resourceRequestBody, int i, boolean z) {
        this.f8153a.e.f().c(new LoadUrlParams(gurl.h(), 0));
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public boolean shouldCreateWebContents(GURL gurl) {
        this.f8153a.e.f().c(new LoadUrlParams(gurl.h(), 0));
        return false;
    }

    @Override // org.chromium.components.embedder_support.delegate.WebContentsDelegateAndroid
    public void visibleSSLStateChanged() {
        int i;
        IL il = this.f8153a;
        if (il.f != null) {
            int a2 = LR0.a(il.e);
            NL nl = this.f8153a.f;
            if (a2 != 0) {
                if (a2 == 3 || a2 == 4) {
                    i = R.drawable.f34340_resource_name_obfuscated_RES_2131231474;
                    ((ImageView) nl.f.findViewById(R.id.security_icon)).setImageResource(i);
                    IL il2 = this.f8153a;
                    ((TextView) il2.f.f.findViewById(R.id.origin)).setText(AbstractC1911br1.c(il2.e.u(), 1));
                }
                if (a2 == 5) {
                    i = R.drawable.f34360_resource_name_obfuscated_RES_2131231476;
                } else if (a2 != 6) {
                    i = 0;
                }
                ((ImageView) nl.f.findViewById(R.id.security_icon)).setImageResource(i);
                IL il22 = this.f8153a;
                ((TextView) il22.f.f.findViewById(R.id.origin)).setText(AbstractC1911br1.c(il22.e.u(), 1));
            }
            i = R.drawable.f34350_resource_name_obfuscated_RES_2131231475;
            ((ImageView) nl.f.findViewById(R.id.security_icon)).setImageResource(i);
            IL il222 = this.f8153a;
            ((TextView) il222.f.f.findViewById(R.id.origin)).setText(AbstractC1911br1.c(il222.e.u(), 1));
        }
    }
}
