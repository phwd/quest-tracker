package defpackage;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.style.ClickableSpan;
import android.view.View;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.sync.ui.PassphraseDialogFragment;

/* renamed from: lx0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3636lx0 extends ClickableSpan {
    public final /* synthetic */ Context F;

    public C3636lx0(PassphraseDialogFragment passphraseDialogFragment, Context context) {
        this.F = context;
    }

    public void onClick(View view) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://www.google.com/settings/chrome/sync"));
        intent.setPackage(ContextUtils.getApplicationContext().getPackageName());
        U20.p(intent, "android.support.customtabs.extra.SESSION", null);
        this.F.startActivity(intent);
    }
}
