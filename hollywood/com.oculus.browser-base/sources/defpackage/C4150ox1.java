package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Locale;
import org.chromium.content.browser.accessibility.WebContentsAccessibilityImpl;

/* renamed from: ox1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4150ox1 extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ WebContentsAccessibilityImpl f11038a;

    public C4150ox1(WebContentsAccessibilityImpl webContentsAccessibilityImpl) {
        this.f11038a = webContentsAccessibilityImpl;
    }

    public void onReceive(Context context, Intent intent) {
        this.f11038a.j0 = Locale.getDefault().toLanguageTag();
    }
}
