package defpackage;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import com.oculus.browser.R;
import java.util.LinkedList;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.chrome.browser.sync.TrustedVaultClient;
import org.chromium.components.signin.base.CoreAccountInfo;

/* renamed from: p51  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4175p51 {
    public static void a(boolean z) {
        ProfileSyncService b = ProfileSyncService.b();
        if (z != b.m()) {
            if (z) {
                N.MmphYbNU(b.e, b, true);
                return;
            }
            AbstractC3364kK0.g("Sync.StopSource", 3, 6);
            N.MmphYbNU(b.e, b, false);
        }
    }

    public static int b(int i) {
        return i != 1 ? i != 3 ? i != 7 ? R.string.f62710_resource_name_obfuscated_RES_2131953588 : R.string.f62720_resource_name_obfuscated_RES_2131953589 : R.string.f62690_resource_name_obfuscated_RES_2131953586 : R.string.f62700_resource_name_obfuscated_RES_2131953587;
    }

    public static int c() {
        ProfileSyncService b = ProfileSyncService.b();
        if (b == null) {
            return -1;
        }
        if (!b.k()) {
            return 0;
        }
        if (!b.m()) {
            return -1;
        }
        if (b.d() == 1) {
            return 1;
        }
        if (N.M3XV0Up2(b.e, b)) {
            return 5;
        }
        if (b.d() != 0 || N.MreEfroQ(b.e, b)) {
            return 128;
        }
        if (b.h() && b.j()) {
            return 2;
        }
        if (b.h() && b.n()) {
            return b.g() ? 3 : 4;
        }
        if (!b.i()) {
            return 6;
        }
        return -1;
    }

    public static String d(Context context, int i) {
        if (i == 128) {
            return context.getString(R.string.f52730_resource_name_obfuscated_RES_2131952590);
        }
        switch (i) {
            case 0:
                return context.getString(R.string.f52700_resource_name_obfuscated_RES_2131952587);
            case 1:
                return context.getString(R.string.f52750_resource_name_obfuscated_RES_2131952592);
            case 2:
                return context.getString(R.string.f52740_resource_name_obfuscated_RES_2131952591);
            case 3:
            case 4:
                return context.getString(N.M09VlOh_("MobileIdentityConsistency") ? R.string.f52760_resource_name_obfuscated_RES_2131952593 : R.string.f52770_resource_name_obfuscated_RES_2131952594);
            case 5:
                return context.getString(R.string.f52710_resource_name_obfuscated_RES_2131952588, AbstractC0456Hk.f8178a.b);
            case 6:
                return context.getString(N.M09VlOh_("MobileIdentityConsistency") ? R.string.f52780_resource_name_obfuscated_RES_2131952595 : R.string.f52790_resource_name_obfuscated_RES_2131952596);
            default:
                return null;
        }
    }

    public static Drawable e(Context context) {
        boolean M09VlOh_ = N.M09VlOh_("MobileIdentityConsistency");
        if (C5949zZ.a().c(Profile.b()).c()) {
            ProfileSyncService b = ProfileSyncService.b();
            if (b == null || !b.m()) {
                if (M09VlOh_) {
                    return AbstractC5544x8.a(context, R.drawable.f32800_resource_name_obfuscated_RES_2131231320);
                }
                return AbstractC2417ep1.f(context, R.drawable.f32790_resource_name_obfuscated_RES_2131231319, R.color.f11220_resource_name_obfuscated_RES_2131099812);
            } else if (b.l()) {
                if (M09VlOh_) {
                    return AbstractC5544x8.a(context, R.drawable.f32800_resource_name_obfuscated_RES_2131231320);
                }
                return AbstractC2417ep1.f(context, R.drawable.f32780_resource_name_obfuscated_RES_2131231318, R.color.f11220_resource_name_obfuscated_RES_2131099812);
            } else if (c() != -1) {
                if (M09VlOh_) {
                    return AbstractC5544x8.a(context, R.drawable.f32770_resource_name_obfuscated_RES_2131231317);
                }
                return AbstractC2417ep1.f(context, R.drawable.f32780_resource_name_obfuscated_RES_2131231318, R.color.f11410_resource_name_obfuscated_RES_2131099831);
            } else if (M09VlOh_) {
                return AbstractC5544x8.a(context, R.drawable.f32810_resource_name_obfuscated_RES_2131231321);
            } else {
                return AbstractC2417ep1.f(context, R.drawable.f32790_resource_name_obfuscated_RES_2131231319, R.color.f11190_resource_name_obfuscated_RES_2131099809);
            }
        } else if (M09VlOh_) {
            return AbstractC5544x8.a(context, R.drawable.f32800_resource_name_obfuscated_RES_2131231320);
        } else {
            return null;
        }
    }

    public static String f(Context context) {
        Resources resources = context.getResources();
        if (!C5949zZ.a().c(Profile.b()).c()) {
            return N.M09VlOh_("MobileIdentityConsistency") ? resources.getString(R.string.f62790_resource_name_obfuscated_RES_2131953596) : "";
        }
        ProfileSyncService b = ProfileSyncService.b();
        if (b == null) {
            return resources.getString(R.string.f62790_resource_name_obfuscated_RES_2131953596);
        }
        if (!b.k()) {
            return resources.getString(R.string.f62590_resource_name_obfuscated_RES_2131953576);
        }
        if (b.l()) {
            return resources.getString(R.string.f62800_resource_name_obfuscated_RES_2131953597);
        }
        if (!b.i()) {
            if (N.M09VlOh_("MobileIdentityConsistency")) {
                return resources.getString(R.string.f63020_resource_name_obfuscated_RES_2131953619);
            }
            return resources.getString(R.string.f63030_resource_name_obfuscated_RES_2131953620);
        } else if (b.d() != 0) {
            return resources.getString(b(b.d()));
        } else {
            if (N.M3XV0Up2(b.e, b)) {
                return resources.getString(R.string.f62730_resource_name_obfuscated_RES_2131953590, AbstractC0456Hk.f8178a.b);
            } else if (N.MreEfroQ(b.e, b)) {
                return resources.getString(R.string.f62710_resource_name_obfuscated_RES_2131953588);
            } else {
                if (!b.m()) {
                    if (N.M09VlOh_("MobileIdentityConsistency")) {
                        return resources.getString(R.string.f62640_resource_name_obfuscated_RES_2131953581);
                    }
                    return context.getString(R.string.f62790_resource_name_obfuscated_RES_2131953596);
                } else if (!N.M$BssAkU(b.e, b)) {
                    return resources.getString(R.string.f63050_resource_name_obfuscated_RES_2131953622);
                } else {
                    if (b.j()) {
                        return resources.getString(R.string.f62850_resource_name_obfuscated_RES_2131953602);
                    }
                    if (!b.n()) {
                        return context.getString(R.string.f62580_resource_name_obfuscated_RES_2131953575);
                    }
                    if (b.g()) {
                        return context.getString(R.string.f62680_resource_name_obfuscated_RES_2131953585);
                    }
                    return context.getString(R.string.f62970_resource_name_obfuscated_RES_2131953614);
                }
            }
        }
    }

    public static void g(Activity activity, String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.putExtra("android.support.customtabs.extra.TITLE_VISIBILITY", 0);
        if (!intent.hasExtra("android.support.customtabs.extra.SESSION")) {
            Bundle bundle = new Bundle();
            bundle.putBinder("android.support.customtabs.extra.SESSION", null);
            intent.putExtras(bundle);
        }
        intent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", true);
        intent.putExtras(new Bundle());
        intent.putExtra("androidx.browser.customtabs.extra.SHARE_STATE", 0);
        intent.setData(Uri.parse(str));
        Intent d = Z60.d(activity, intent);
        d.setPackage(activity.getPackageName());
        d.putExtra("org.chromium.chrome.browser.customtabs.EXTRA_UI_TYPE", 0);
        d.putExtra("com.android.browser.application_id", activity.getPackageName());
        S20.a(d);
        U20.q(activity, d);
    }

    public static void h(AbstractComponentCallbacksC3550lS lSVar, CoreAccountInfo coreAccountInfo, int i) {
        ProfileSyncService b = ProfileSyncService.b();
        N.Max0OuMD(b.e, b, 0);
        Objects.requireNonNull(TrustedVaultClient.a().b);
        new LinkedList();
        LinkedList<Callback> linkedList = new LinkedList();
        Thread.currentThread();
        Handler handler = new Handler();
        for (Callback callback : linkedList) {
            handler.post(callback.a(null));
        }
        linkedList.clear();
        handler.post(new RunnableC0884Ol(new C4004o51(), null));
    }
}
