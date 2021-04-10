package defpackage;

import android.app.Activity;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: hO0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC2860hO0 implements Runnable {
    public final /* synthetic */ C3372kO0 F;

    public RunnableC2860hO0(C3372kO0 ko0) {
        this.F = ko0;
    }

    public void run() {
        Activity activity = (Activity) this.F.F.f10773J.s0().get();
        C2535fX.a().b(activity, activity.getString(R.string.f52610_resource_name_obfuscated_RES_2131952578), Profile.a(this.F.F.L), null);
    }
}
