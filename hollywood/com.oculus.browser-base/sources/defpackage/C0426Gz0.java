package defpackage;

import java.util.HashMap;
import java.util.Map;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.url.GURL;

/* renamed from: Gz0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0426Gz0 implements AbstractC0548Iz0 {

    /* renamed from: a  reason: collision with root package name */
    public C0730Lz0 f8126a;
    public C0609Jz0 b;
    public final WebContents c;
    public final ChromeActivity d;
    public final boolean e;
    public final UH0 f;

    public C0426Gz0(ChromeActivity chromeActivity, WebContents webContents, GURL gurl) {
        this.c = webContents;
        this.d = chromeActivity;
        Map c2 = UH0.c(AbstractC0670Kz0.i);
        QH0 qh0 = AbstractC0670Kz0.d;
        GH0 gh0 = new GH0(null);
        gh0.f8081a = true;
        HashMap hashMap = (HashMap) c2;
        hashMap.put(qh0, gh0);
        RH0 rh0 = AbstractC0670Kz0.c;
        IH0 ih0 = new IH0(null);
        ih0.f8214a = 0.05f;
        hashMap.put(rh0, ih0);
        SH0 sh0 = AbstractC0670Kz0.e;
        int b2 = MR0.b(0, this.e, false);
        JH0 jh0 = new JH0(null);
        jh0.f8282a = b2;
        hashMap.put(sh0, jh0);
        TH0 th0 = AbstractC0670Kz0.f;
        String string = chromeActivity.getResources().getString(MR0.a(0));
        LH0 lh0 = new LH0(null);
        lh0.f8415a = string;
        hashMap.put(th0, lh0);
        TH0 th02 = AbstractC0670Kz0.f8397a;
        LH0 lh02 = new LH0(null);
        lh02.f8415a = gurl;
        hashMap.put(th02, lh02);
        TH0 th03 = AbstractC0670Kz0.g;
        RunnableC0243Dz0 dz0 = new RunnableC0243Dz0(this);
        LH0 lh03 = new LH0(null);
        lh03.f8415a = dz0;
        hashMap.put(th03, lh03);
        UH0 uh0 = new UH0(c2, null);
        this.f = uh0;
        this.e = !DeviceFormFactor.a(chromeActivity);
        this.b = new C0609Jz0(uh0, webContents, this);
        this.f8126a = new C0730Lz0(chromeActivity);
        webContents.c0(this.b);
        ZH0.a(uh0, this.f8126a, new C0304Ez0());
    }
}
