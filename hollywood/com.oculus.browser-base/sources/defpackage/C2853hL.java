package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.Objects;
import org.chromium.chrome.browser.safe_browsing.settings.SafeBrowsingSettingsFragment;

/* renamed from: hL  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C2853hL extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C3536lL f10065a;

    public C2853hL(C3536lL lLVar) {
        this.f10065a = lLVar;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C3536lL lLVar = this.f10065a;
        View view = (View) obj;
        Objects.requireNonNull(lLVar);
        AbstractC3707mL.a(3);
        Context context = lLVar.f10339a;
        int i = SafeBrowsingSettingsFragment.G0;
        Bundle bundle = new Bundle();
        bundle.putInt("SafeBrowsingSettingsFragment.AccessPoint", 3);
        String name = SafeBrowsingSettingsFragment.class.getName();
        Intent l = AbstractC2531fV.l(context, XS0.class);
        if (!(context instanceof Activity)) {
            l.addFlags(268435456);
            l.addFlags(67108864);
        }
        l.putExtra("show_fragment", name);
        l.putExtra("show_fragment_args", bundle);
        U20.q(context, l);
    }
}
