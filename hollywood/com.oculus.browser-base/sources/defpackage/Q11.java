package defpackage;

import android.widget.ImageView;
import org.chromium.chrome.browser.omnibox.status.StatusView;

/* renamed from: Q11  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Q11 implements Runnable {
    public final R11 F;

    public Q11(R11 r11) {
        this.F = r11;
    }

    public void run() {
        R11 r11 = this.F;
        StatusView statusView = r11.F;
        ImageView imageView = statusView.K;
        statusView.setAlpha(1.0f);
        imageView.setAlpha(r11.H.e(A21.g));
        imageView.setVisibility(r11.H.h(A21.d) ? 0 : 8);
    }
}
