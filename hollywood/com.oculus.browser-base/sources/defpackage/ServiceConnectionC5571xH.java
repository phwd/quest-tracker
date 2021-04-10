package defpackage;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import java.util.HashSet;
import java.util.Set;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.download.DownloadNotificationServiceObserver;

/* renamed from: xH  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ServiceConnectionC5571xH implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5911zH f11601a;

    public ServiceConnectionC5571xH(C5911zH zHVar) {
        this.f11601a = zHVar;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        AbstractC1220Ua0.f("DownloadFg", "onServiceConnected", new Object[0]);
        if (!(iBinder instanceof BinderC5061uH)) {
            AbstractC1220Ua0.f("DownloadFg", "Not from DownloadNotificationService, do not connect. Component name: " + componentName, new Object[0]);
            return;
        }
        this.f11601a.g = ((BinderC5061uH) iBinder).f11403a;
        Object obj = ThreadUtils.f10596a;
        Set a2 = AH.a();
        String name = DownloadNotificationServiceObserver.class.getName();
        if (!a2.contains(name)) {
            HashSet hashSet = new HashSet(a2);
            hashSet.add(name);
            NU0.f8549a.q("ForegroundServiceObservers", hashSet);
        }
        this.f11601a.c(true);
    }

    public void onServiceDisconnected(ComponentName componentName) {
        AbstractC1220Ua0.f("DownloadFg", "onServiceDisconnected", new Object[0]);
        this.f11601a.g = null;
    }
}
