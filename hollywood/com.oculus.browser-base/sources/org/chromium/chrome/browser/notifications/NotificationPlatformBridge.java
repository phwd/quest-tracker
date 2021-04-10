package org.chromium.chrome.browser.notifications;

import J.N;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.oculus.browser.R;
import java.net.URISyntaxException;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.site_settings.SingleWebsiteSettings;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class NotificationPlatformBridge {

    /* renamed from: a  reason: collision with root package name */
    public static final int[] f10702a = new int[0];
    public static NotificationPlatformBridge b;
    public final long c;
    public final AbstractC0711Lp0 d = new C0771Mp0(ContextUtils.getApplicationContext());
    public long e;
    public C2414eo1 f;

    public NotificationPlatformBridge(long j) {
        this.c = j;
    }

    public static String c(Intent intent) {
        CharSequence charSequence;
        if (intent.getStringExtra("notification_reply") != null) {
            return intent.getStringExtra("notification_reply");
        }
        Bundle resultsFromIntent = RemoteInput.getResultsFromIntent(intent);
        if (resultsFromIntent == null || (charSequence = resultsFromIntent.getCharSequence("key_text_reply")) == null) {
            return null;
        }
        return charSequence.toString();
    }

    public static NotificationPlatformBridge create(long j) {
        if (b == null) {
            NotificationPlatformBridge notificationPlatformBridge = new NotificationPlatformBridge(j);
            b = notificationPlatformBridge;
            return notificationPlatformBridge;
        }
        throw new IllegalStateException("There must only be a single NotificationPlatformBridge.");
    }

    public static String d(String str) {
        if (str != null && str.startsWith("p#")) {
            String[] split = str.split("#");
            try {
                if (new Vo1(split[1]).d() != null) {
                    return split[1];
                }
                return null;
            } catch (URISyntaxException e2) {
                AbstractC1220Ua0.a("NotificationPlatformBridge", "Expected to find a valid url in the notification tag extra.", e2);
            }
        }
        return null;
    }

    public final C3444kq0 a(AbstractC5827yp0 yp0, String str, String str2, ActionInfo[] actionInfoArr, Bitmap bitmap) {
        String str3;
        Context applicationContext = ContextUtils.getApplicationContext();
        Resources resources = applicationContext.getResources();
        String name = SingleWebsiteSettings.class.getName();
        Bundle k1 = SingleWebsiteSettings.k1(str2);
        Intent l = AbstractC2531fV.l(applicationContext, XS0.class);
        if (!(applicationContext instanceof Activity)) {
            l.addFlags(268435456);
            l.addFlags(67108864);
        }
        l.putExtra("show_fragment", name);
        l.putExtra("show_fragment_args", k1);
        l.setData(f(str, str2, -1));
        int i = 0;
        PendingIntent activity = PendingIntent.getActivity(applicationContext, 0, l, 134217728);
        boolean z = actionInfoArr.length > 0;
        if (!z) {
            i = R.drawable.f34890_resource_name_obfuscated_RES_2131231529;
        }
        if (z) {
            str3 = resources.getString(R.string.f56200_resource_name_obfuscated_RES_2131952937);
        } else {
            str3 = resources.getString(R.string.f57280_resource_name_obfuscated_RES_2131953045);
        }
        Objects.requireNonNull(yp0);
        yp0.p = new C5657xp0(i, AbstractC5827yp0.i(str3), activity, 0, null, 12);
        return yp0.d(new C0832Np0(7, str, -1));
    }

    public final void b(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str2)) {
            if (Qw1.f8796a == null) {
                Qw1.f8796a = new Qw1();
            }
            Qw1 qw1 = Qw1.f8796a;
            qw1.b.a(ContextUtils.getApplicationContext(), str2, new Ow1(qw1, str, -1));
            return;
        }
        if (e().d(Uri.parse(str3))) {
            e().b(Uri.parse(str3), new Vn1(str, -1));
        }
        ((C0771Mp0) this.d).b.cancel(str, -1);
    }

    public final void closeNotification(String str, String str2, boolean z, String str3) {
        String c2;
        Px1 a2 = Px1.a();
        if (!a2.d() && !a2.g) {
            a2.g = true;
            a2.b("Close");
            a2.c("TimeToClose");
        }
        if (z || (c2 = AbstractC2612fx1.c(ContextUtils.getApplicationContext(), str2)) == null) {
            b(str, str3, str2);
        } else {
            AbstractC3626lu.a(c2, new C1137Sp0(this, str, c2, str2));
        }
    }

    public final void destroy() {
        b = null;
    }

    public final void displayNotification(String str, int i, String str2, String str3, String str4, Profile profile, String str5, String str6, Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, int[] iArr, long j, boolean z, boolean z2, ActionInfo[] actionInfoArr) {
        C5232vH0 vh0;
        boolean MzIXnlkD = N.MzIXnlkD(Wr1.a(Profile.b()).f10883a, "notifications.vibrate_enabled");
        boolean g = profile.g();
        String c2 = AbstractC2612fx1.c(ContextUtils.getApplicationContext(), str3);
        if (c2 == null) {
            vh0 = C5232vH0.c("");
        } else {
            C5232vH0 vh02 = new C5232vH0();
            AbstractC3626lu.a(c2, new C1015Qp0(vh02, c2));
            vh0 = vh02;
        }
        vh0.g(new C0954Pp0(this, str, i, str2, str3, str4, g, MzIXnlkD, str5, str6, bitmap, bitmap2, bitmap3, iArr, j, z, z2, actionInfoArr));
    }

    public final C2414eo1 e() {
        if (this.f == null) {
            this.f = AbstractApplicationC3785mq.g().e();
        }
        return this.f;
    }

    public final Uri f(String str, String str2, int i) {
        Uri.Builder buildUpon = Uri.parse(str2).buildUpon();
        return buildUpon.fragment(str + "," + i).build();
    }

    public final CB0 g(Context context, String str, String str2, int i, String str3, String str4, String str5, boolean z, String str6, int i2) {
        Intent intent = new Intent(str, f(str2, str3, i2));
        intent.setClass(context, NotificationServiceImpl$Receiver.class);
        intent.putExtra("notification_id", str2);
        intent.putExtra("notification_type", i);
        intent.putExtra("notification_info_origin", str3);
        intent.putExtra("notification_info_scope", str4);
        intent.putExtra("notification_info_profile_id", str5);
        intent.putExtra("notification_info_profile_incognito", z);
        intent.putExtra("notification_info_webapk_package", str6);
        intent.putExtra("notification_info_action_index", i2);
        intent.addFlags(268435456);
        return CB0.a(context, 0, intent, 134217728);
    }
}
