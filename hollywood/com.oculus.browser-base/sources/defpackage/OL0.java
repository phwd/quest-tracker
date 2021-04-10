package defpackage;

import android.content.Intent;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.media.router.ChromeMediaRouterClient;

/* renamed from: OL0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class OL0 extends AbstractC5289vg {
    public OL0(AbstractC0018Ag ag) {
        super(ag);
        ag.e.add(this);
    }

    @Override // defpackage.AbstractC5289vg
    public Intent f() {
        Intent intent = new Intent(ContextUtils.getApplicationContext(), AbstractActivityC5644xl.class);
        intent.putExtra("org.chromium.chrome.browser.metrics.MediaNotificationUma.EXTRA_CLICK_SOURCE", 2);
        return intent;
    }

    @Override // defpackage.AbstractC5289vg
    public int g() {
        Objects.requireNonNull(ChromeMediaRouterClient.f10694a);
        return R.id.remote_playback_notification;
    }
}
