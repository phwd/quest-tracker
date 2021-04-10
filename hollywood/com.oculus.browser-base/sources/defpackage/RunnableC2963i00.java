package defpackage;

import android.app.Activity;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.profiles.Profile;

/* renamed from: i00  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC2963i00 implements Runnable {
    public final C3304k00 F;

    public RunnableC2963i00(C3304k00 k00) {
        this.F = k00;
    }

    public void run() {
        C2792h00 h00 = this.F.b;
        Objects.requireNonNull(h00);
        Object obj = ThreadUtils.f10596a;
        C2535fX fXVar = h00.c;
        Activity activity = h00.f10041a;
        fXVar.b(activity, activity.getString(R.string.f52550_resource_name_obfuscated_RES_2131952572), Profile.b().c(), null);
    }
}
