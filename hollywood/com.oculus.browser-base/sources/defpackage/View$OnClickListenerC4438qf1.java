package defpackage;

import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: qf1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class View$OnClickListenerC4438qf1 implements View.OnClickListener {
    public final ChromeActivity F;

    public View$OnClickListenerC4438qf1(ChromeActivity chromeActivity) {
        this.F = chromeActivity;
    }

    public void onClick(View view) {
        ChromeActivity chromeActivity = this.F;
        C2535fX.a().b(chromeActivity, chromeActivity.getString(R.string.f52550_resource_name_obfuscated_RES_2131952572), Profile.b().c(), null);
    }
}
