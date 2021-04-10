package defpackage;

import android.content.Intent;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.chrome.browser.media.router.ChromeMediaRouterClient;

/* renamed from: Dl  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0214Dl extends AbstractC5289vg {
    public C0214Dl(AbstractC0018Ag ag) {
        super(ag);
        ag.e.add(this);
    }

    @Override // defpackage.AbstractC5289vg
    public Intent f() {
        ChromeMediaRouterClient chromeMediaRouterClient = ChromeMediaRouterClient.f10694a;
        int i = this.G.c.e;
        Objects.requireNonNull(chromeMediaRouterClient);
        Intent a2 = AbstractC0409Gr.a(i);
        a2.putExtra("org.chromium.chrome.browser.metrics.MediaNotificationUma.EXTRA_CLICK_SOURCE", 1);
        return a2;
    }

    @Override // defpackage.AbstractC5289vg
    public int g() {
        Objects.requireNonNull(ChromeMediaRouterClient.f10694a);
        return R.id.presentation_notification;
    }
}
