package org.chromium.chrome.browser.browserservices.ui.trustedwebactivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.Collections;
import java.util.HashSet;
import org.chromium.base.ContextUtils;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class DisclosureAcceptanceBroadcastReceiver extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final AbstractC0711Lp0 f10624a;
    public final C4109ok b;

    public DisclosureAcceptanceBroadcastReceiver() {
        C0771Mp0 mp0 = new C0771Mp0(ContextUtils.getApplicationContext());
        C4109ok okVar = new C4109ok(NU0.f8549a);
        this.f10624a = mp0;
        this.b = okVar;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent == null || !intent.hasExtra("TWADisclosureResp.tag_extra") || !intent.hasExtra("TWADisclosureResp.id_extra") || !intent.hasExtra("TWADisclosureResp.package_extra")) {
            AbstractC1220Ua0.f("TWADisclosureRec", "Started with null or incomplete Intent.", new Object[0]);
            return;
        }
        String stringExtra = intent.getStringExtra("TWADisclosureResp.tag_extra");
        int intExtra = intent.getIntExtra("TWADisclosureResp.id_extra", -1);
        String stringExtra2 = intent.getStringExtra("TWADisclosureResp.package_extra");
        ((C0771Mp0) this.f10624a).b.cancel(stringExtra, intExtra);
        this.b.f10571a.f8694a.a("trusted_web_activity_disclosure_accepted_packages");
        HashSet hashSet = new HashSet(AbstractC3983nz.f10523a.getStringSet("trusted_web_activity_disclosure_accepted_packages", Collections.emptySet()));
        hashSet.add(stringExtra2);
        AbstractC3983nz.f10523a.edit().putStringSet("trusted_web_activity_disclosure_accepted_packages", hashSet).apply();
    }
}
