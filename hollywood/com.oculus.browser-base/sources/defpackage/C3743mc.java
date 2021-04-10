package defpackage;

import J.N;
import android.text.style.ClickableSpan;
import android.view.View;
import org.chromium.chrome.browser.password_manager.AutoSigninFirstRunDialog;

/* renamed from: mc  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3743mc extends ClickableSpan {
    public final /* synthetic */ AutoSigninFirstRunDialog F;

    public C3743mc(AutoSigninFirstRunDialog autoSigninFirstRunDialog) {
        this.F = autoSigninFirstRunDialog;
    }

    public void onClick(View view) {
        AutoSigninFirstRunDialog autoSigninFirstRunDialog = this.F;
        N.MQjsefF9(autoSigninFirstRunDialog.M, autoSigninFirstRunDialog);
        this.F.N.dismiss();
    }
}
