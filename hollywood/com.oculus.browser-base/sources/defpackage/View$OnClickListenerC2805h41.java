package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import org.chromium.base.ContextUtils;

/* renamed from: h41  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class View$OnClickListenerC2805h41 implements View.OnClickListener {
    public final /* synthetic */ Context F;
    public final /* synthetic */ C2976i41 G;

    public View$OnClickListenerC2805h41(C2976i41 i41, Context context) {
        this.G = i41;
        this.F = context;
    }

    public void onClick(View view) {
        Intent intent = new Intent("org.chromium.chrome.browser.usage_stats.action.SHOW_WEBSITE_DETAILS");
        intent.setFlags(268435456);
        intent.putExtra("org.chromium.chrome.browser.usage_stats.extra.FULLY_QUALIFIED_DOMAIN_NAME", this.G.H);
        intent.putExtra("android.intent.extra.PACKAGE_NAME", ContextUtils.getApplicationContext().getPackageName());
        try {
            this.F.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            AbstractC1220Ua0.a("SuspendedTab", "No activity found for site details intent", e);
        }
    }
}
