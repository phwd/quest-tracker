package defpackage;

import android.app.Activity;
import android.text.style.ClickableSpan;
import android.view.View;
import com.oculus.browser.R;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.ui.PassphraseCreationDialogFragment;

/* renamed from: dx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2269dx0 extends ClickableSpan {
    public final /* synthetic */ Activity F;

    public C2269dx0(PassphraseCreationDialogFragment passphraseCreationDialogFragment, Activity activity) {
        this.F = activity;
    }

    public void onClick(View view) {
        C2535fX a2 = C2535fX.a();
        Activity activity = this.F;
        a2.b(activity, activity.getString(R.string.f52480_resource_name_obfuscated_RES_2131952565), Profile.b(), null);
    }
}
