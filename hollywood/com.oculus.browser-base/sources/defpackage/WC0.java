package defpackage;

import J.N;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import org.chromium.chrome.browser.media.PictureInPictureActivity;

/* renamed from: WC0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WC0 extends BroadcastReceiver {
    public WC0(PictureInPictureActivity pictureInPictureActivity) {
    }

    public void onReceive(Context context, Intent intent) {
        if (PictureInPictureActivity.o0 != 0 && intent.getAction() != null && intent.getAction().equals("org.chromium.chrome.browser.media.PictureInPictureActivity.Play")) {
            N.Mg8bKPmu(PictureInPictureActivity.o0);
        }
    }
}
