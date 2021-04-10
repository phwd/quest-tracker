package defpackage;

import android.content.Intent;
import android.util.SparseArray;
import com.oculus.browser.R;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.media.ui.ChromeMediaNotificationControllerServices$CastListenerService;
import org.chromium.chrome.browser.media.ui.ChromeMediaNotificationControllerServices$PlaybackListenerService;
import org.chromium.chrome.browser.media.ui.ChromeMediaNotificationControllerServices$PresentationListenerService;

/* renamed from: Vr  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1323Vr implements AbstractC4944te0 {

    /* renamed from: a  reason: collision with root package name */
    public static SparseArray f9110a;
    public int b;

    static {
        SparseArray sparseArray = new SparseArray();
        f9110a = sparseArray;
        sparseArray.put(R.id.media_playback_notification, new C1079Rr(ChromeMediaNotificationControllerServices$PlaybackListenerService.class, "MediaPlayback"));
        f9110a.put(R.id.presentation_notification, new C1079Rr(ChromeMediaNotificationControllerServices$PresentationListenerService.class, "MediaPresentation"));
        f9110a.put(R.id.remote_playback_notification, new C1079Rr(ChromeMediaNotificationControllerServices$CastListenerService.class, "MediaRemote"));
    }

    public C1323Vr(int i) {
        this.b = i;
    }

    public Intent a() {
        Class cls = ((C1079Rr) f9110a.get(this.b)).f8857a;
        if (cls != null) {
            return new Intent(ContextUtils.getApplicationContext(), cls);
        }
        return null;
    }
}
