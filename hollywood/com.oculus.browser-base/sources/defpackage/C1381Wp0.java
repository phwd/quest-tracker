package defpackage;

import androidx.preference.Preference;
import org.chromium.chrome.browser.notifications.settings.NotificationSettings;

/* renamed from: Wp0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1381Wp0 implements XE0 {
    @Override // defpackage.XE0
    public boolean a(Preference preference, Object obj) {
        int i = NotificationSettings.G0;
        NU0.f8549a.m("prefetch_notification_enabled", ((Boolean) obj).booleanValue());
        return true;
    }
}
