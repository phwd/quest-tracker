package defpackage;

import android.app.Activity;
import java.util.Objects;
import org.chromium.chrome.browser.app.ChromeActivity;

/* renamed from: XN  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class XN implements Runnable {
    public final ZN F;
    public final Activity G;
    public final int H;
    public final int I;

    public XN(ZN zn, Activity activity, int i, int i2) {
        this.F = zn;
        this.G = activity;
        this.H = i;
        this.I = i2;
    }

    public void run() {
        ZN zn = this.F;
        Activity activity = this.G;
        int i = this.H;
        int i2 = this.I;
        Objects.requireNonNull(zn);
        ChromeActivity chromeActivity = (ChromeActivity) activity;
        if (i2 == -1) {
            zn.k.a(2);
        } else if (i2 == 0) {
            zn.k.a(3);
        }
        chromeActivity.onActivityResult(i, i2, null);
        if (i2 == -1) {
            zn.i(4);
        }
    }
}
