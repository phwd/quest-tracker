package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.media.ui.ChromeMediaNotificationControllerServices$PlaybackListenerService;

/* renamed from: Sr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1140Sr extends BroadcastReceiver {
    public C1140Sr(C1201Tr tr) {
    }

    public void onReceive(Context context, Intent intent) {
        if ("android.media.AUDIO_BECOMING_NOISY".equals(intent.getAction())) {
            SparseArray sparseArray = C1323Vr.f9110a;
            Intent intent2 = new Intent(ContextUtils.getApplicationContext(), ChromeMediaNotificationControllerServices$PlaybackListenerService.class);
            intent2.setAction(intent.getAction());
            ContextUtils.getApplicationContext().startService(intent2);
        }
    }
}
