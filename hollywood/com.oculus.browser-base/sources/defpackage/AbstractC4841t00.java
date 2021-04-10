package defpackage;

import android.app.NotificationManager;
import org.chromium.base.ContextUtils;

/* renamed from: t00  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4841t00 {
    public static void a() {
        ((NotificationManager) ContextUtils.getApplicationContext().getSystemService("notification")).cancel("incognito_tabs_open", 100);
    }
}
