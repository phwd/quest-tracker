package defpackage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import java.util.Objects;
import org.chromium.chrome.browser.AppHooks;
import org.chromium.chrome.browser.sync.settings.ManageSyncSettings;

/* renamed from: Sb0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1101Sb0 implements Runnable {
    public final ManageSyncSettings F;
    public final String G;

    public RunnableC1101Sb0(ManageSyncSettings manageSyncSettings, String str) {
        this.F = manageSyncSettings;
        this.G = str;
    }

    public void run() {
        ManageSyncSettings manageSyncSettings = this.F;
        Objects.requireNonNull(manageSyncSettings);
        Objects.requireNonNull(AppHooks.get());
        Activity u = manageSyncSettings.u();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://myaccount.google.com/activitycontrols/search"));
        intent.putExtra("com.android.browser.application_id", u.getPackageName());
        intent.putExtra("create_new_tab", true);
        intent.setPackage(u.getPackageName());
        u.startActivity(intent);
        AbstractC3535lK0.a("Signin_AccountSettings_GoogleActivityControlsClicked");
    }
}
