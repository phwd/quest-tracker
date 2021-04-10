package defpackage;

import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.ProfileSyncService;

/* renamed from: cW0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2022cW0 {

    /* renamed from: a  reason: collision with root package name */
    public static C1851bW0 f9611a;

    public static C1851bW0 a() {
        if (f9611a == null) {
            Object obj = ThreadUtils.f10596a;
            if (!Y41.G) {
                if (ProfileSyncService.b() != null) {
                    Y41.F = new Y41();
                }
                Y41.G = true;
            }
            Profile b = Profile.b();
            f9611a = new C1851bW0(C5949zZ.a().d(b), C5949zZ.a().b(b), C4072oW0.f10556a);
        }
        return f9611a;
    }
}
