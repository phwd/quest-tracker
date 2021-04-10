package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.util.Objects;

/* renamed from: Z60  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class Z60 implements R20 {
    public static Intent d(Context context, Intent intent) {
        Uri parse = Uri.parse(S20.m(intent));
        Intent intent2 = new Intent(intent);
        intent2.setAction("android.intent.action.VIEW");
        intent2.setData(parse);
        intent2.setClassName(context, AbstractActivityC5822yn1.class.getName());
        if (U20.d(intent, "android.support.customtabs.extra.LAUNCH_AS_TRUSTED_WEB_ACTIVITY", false)) {
            RS0 d = AbstractApplicationC3785mq.g().d();
            Objects.requireNonNull(d);
            if (context instanceof Activity) {
                C5859z.a(d.f8832a.get(((Activity) context).getTaskId()));
            }
        }
        boolean A = S20.A(intent);
        if (parse != null && "content".equals(parse.getScheme())) {
            context.grantUriPermission(context.getPackageName(), parse, 1);
        }
        if (AbstractC1575Zv.e().g("open-custom-tabs-in-new-task")) {
            intent2.setFlags(intent2.getFlags() | 268435456);
        }
        if (!((intent2.getFlags() & 268435456) == 0 && (intent2.getFlags() & 524288) == 0)) {
            intent2.setFlags(intent2.getFlags() & -8388609);
            intent2.addFlags(134217728);
            intent2.addFlags(524288);
        }
        if (!A) {
            try {
                intent2.removeExtra("org.chromium.chrome.browser.customtabs.IS_OPENED_BY_CHROME");
            } catch (Throwable unused) {
                AbstractC1220Ua0.a("IntentUtils", AbstractC2531fV.x("removeExtra failed on intent ", intent2), new Object[0]);
            }
        }
        return intent2;
    }

    public static boolean e(Intent intent) {
        if (intent == null) {
            return false;
        }
        return !(intent.getBooleanExtra("android.support.customtabs.extra.user_opt_out", false) && (intent.getFlags() & 268435456) != 0) && intent.hasExtra("android.support.customtabs.extra.SESSION") && S20.m(intent) != null;
    }
}
