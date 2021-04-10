package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import org.chromium.chrome.browser.datareduction.settings.DataReductionPreferenceFragment;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder;

/* renamed from: jY  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3220jY implements SimpleConfirmInfoBarBuilder.Listener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Tab f10211a;

    public C3220jY(Tab tab) {
        this.f10211a = tab;
    }

    @Override // org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder.Listener
    public boolean a(boolean z) {
        return false;
    }

    @Override // org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder.Listener
    public void b() {
        AbstractC3364kK0.g("SubresourceRedirect.ImageCompressionNotificationInfoBar", 1, 3);
    }

    @Override // org.chromium.chrome.browser.ui.messages.infobar.SimpleConfirmInfoBarBuilder.Listener
    public boolean c() {
        Bundle bundle = new Bundle();
        bundle.putBoolean("FromLiteModeHttpsImageCompressionInfoBar", true);
        Context context = this.f10211a.getContext();
        String name = DataReductionPreferenceFragment.class.getName();
        Intent l = AbstractC2531fV.l(context, XS0.class);
        if (!(context instanceof Activity)) {
            l.addFlags(268435456);
            l.addFlags(67108864);
        }
        l.putExtra("show_fragment", name);
        l.putExtra("show_fragment_args", bundle);
        U20.q(context, l);
        AbstractC3364kK0.g("SubresourceRedirect.ImageCompressionNotificationInfoBar", 2, 3);
        return true;
    }
}
