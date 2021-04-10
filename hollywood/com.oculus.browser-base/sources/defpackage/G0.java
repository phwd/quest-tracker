package defpackage;

import J.N;
import android.text.style.ClickableSpan;
import android.view.View;
import org.chromium.chrome.browser.password_manager.AccountChooserDialog;

/* renamed from: G0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class G0 extends ClickableSpan {
    public final /* synthetic */ AccountChooserDialog F;

    public G0(AccountChooserDialog accountChooserDialog) {
        this.F = accountChooserDialog;
    }

    public void onClick(View view) {
        AccountChooserDialog accountChooserDialog = this.F;
        N.MOFoPxaU(accountChooserDialog.R, accountChooserDialog);
        this.F.S.dismiss();
    }
}
