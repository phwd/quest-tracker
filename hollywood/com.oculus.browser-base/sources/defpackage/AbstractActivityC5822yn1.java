package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/* renamed from: yn1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractActivityC5822yn1 extends AbstractActivityC2047cg {
    public static void r1(Context context, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        FB fb = new FB();
        intent.putExtra("android.support.customtabs.extra.TITLE_VISIBILITY", 1);
        int i = AbstractC1270Uv.e(context) ? 2 : 1;
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("Invalid value for the colorScheme argument");
        }
        intent.putExtra("androidx.browser.customtabs.extra.COLOR_SCHEME", i);
        if (!intent.hasExtra("android.support.customtabs.extra.SESSION")) {
            Bundle bundle = new Bundle();
            bundle.putBinder("android.support.customtabs.extra.SESSION", null);
            intent.putExtras(bundle);
        }
        intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
        Integer num = fb.f7999a;
        Bundle bundle2 = new Bundle();
        if (num != null) {
            bundle2.putInt("android.support.customtabs.extra.TOOLBAR_COLOR", num.intValue());
        }
        intent.putExtras(bundle2);
        intent.putExtra("androidx.browser.customtabs.extra.SHARE_STATE", 0);
        C4706sC sCVar = new C4706sC(intent, null);
        sCVar.f11256a.setData(Uri.parse(str));
        Intent d = Z60.d(context, sCVar.f11256a);
        d.setPackage(context.getPackageName());
        d.putExtra("org.chromium.chrome.browser.customtabs.EXTRA_UI_TYPE", 2);
        d.putExtra("com.android.browser.application_id", context.getPackageName());
        if (!(context instanceof Activity)) {
            d.addFlags(268435456);
        }
        S20.a(d);
        context.startActivity(d);
    }
}
