package defpackage;

import android.content.Context;
import android.net.Uri;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.browserservices.verification.OriginVerifier;
import org.chromium.content_public.browser.MessagePort;
import org.chromium.content_public.browser.WebContents;

/* renamed from: IE0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class IE0 implements AbstractC5159ut0 {

    /* renamed from: a  reason: collision with root package name */
    public final FE0 f8209a = new FE0(this);
    public final KE0 b;
    public WebContents c;
    public MessagePort[] d;
    public Uri e;

    public IE0(KE0 ke0) {
        this.b = ke0;
    }

    @Override // defpackage.AbstractC5159ut0
    public void a(String str, C4649rt0 rt0, boolean z, Boolean bool) {
        if (z) {
            char[] cArr = OriginVerifier.f10625a;
            StringBuilder i = AbstractC2531fV.i("android-app://");
            i.append(rt0.f11230a.getHost());
            i.append("/");
            i.append(str);
            this.e = Uri.parse(i.toString());
            WebContents webContents = this.c;
            if (webContents != null && !webContents.g()) {
                c(this.c);
            }
        }
    }

    public final void b() {
        MessagePort[] messagePortArr = this.d;
        if (messagePortArr != null) {
            boolean z = false;
            messagePortArr[0].close();
            this.d = null;
            this.c = null;
            KE0 ke0 = this.b;
            Context applicationContext = ContextUtils.getApplicationContext();
            if (ke0.c != null) {
                z = true;
            }
            if (z) {
                applicationContext.unbindService(ke0);
                ke0.c = null;
            }
        }
    }

    public final void c(WebContents webContents) {
        MessagePort[] b0 = webContents.b0();
        this.d = b0;
        b0[0].b(this.f8209a, null);
        webContents.d0("", this.e.toString(), "", new MessagePort[]{this.d[1]});
        KE0 ke0 = this.b;
        ke0.e = true;
        ke0.a(null);
    }
}
