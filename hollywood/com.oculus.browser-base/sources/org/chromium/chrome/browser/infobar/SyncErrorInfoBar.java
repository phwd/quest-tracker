package org.chromium.chrome.browser.infobar;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.oculus.browser.R;
import java.util.concurrent.TimeUnit;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.chrome.browser.sync.settings.ManageSyncSettings;
import org.chromium.chrome.browser.sync.settings.SyncAndServicesSettings;
import org.chromium.components.infobars.ConfirmInfoBar;
import org.chromium.components.infobars.InfoBar;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SyncErrorInfoBar extends ConfirmInfoBar implements AbstractC3526lH0 {
    public static final long I = TimeUnit.MILLISECONDS.convert(24, TimeUnit.HOURS);

    /* renamed from: J  reason: collision with root package name */
    public static final /* synthetic */ int f10685J = 0;
    public final int K;

    public SyncErrorInfoBar(int i, String str, String str2, String str3) {
        super(R.drawable.f32780_resource_name_obfuscated_RES_2131231318, R.color.f11410_resource_name_obfuscated_RES_2131099831, null, str, null, str3, null);
        this.K = i;
        ProfileSyncService.b().a(this);
        AbstractC3983nz.f10523a.edit().putLong("sync_error_infobar_shown_shown_at_time", System.currentTimeMillis()).apply();
        l(i, 0);
    }

    public static int k() {
        int c = AbstractC4175p51.c();
        if (c == 1) {
            return 0;
        }
        if (c != 2) {
            return c != 6 ? -1 : 2;
        }
        return 1;
    }

    public static void l(int i, int i2) {
        AbstractC3364kK0.g(i != 0 ? i != 1 ? i != 2 ? "Signin.SyncErrorInfoBar." : "Signin.SyncErrorInfoBar.SyncSetupIncomplete" : "Signin.SyncErrorInfoBar.PassphraseRequired" : "Signin.SyncErrorInfoBar.AuthError", i2, 3);
    }

    public static InfoBar show() {
        String str;
        Context applicationContext = ContextUtils.getApplicationContext();
        int c = AbstractC4175p51.c();
        if (c == 6) {
            str = applicationContext.getString(R.string.f63040_resource_name_obfuscated_RES_2131953621);
        } else {
            str = AbstractC4175p51.d(applicationContext, c);
        }
        return new SyncErrorInfoBar(k(), applicationContext.getString(R.string.f62680_resource_name_obfuscated_RES_2131953585), str, applicationContext.getString(R.string.f56710_resource_name_obfuscated_RES_2131952988));
    }

    public final void accept() {
        ProfileSyncService.b().q(this);
        l(this.K, 2);
        if (N.M09VlOh_("MobileIdentityConsistency")) {
            Context applicationContext = ContextUtils.getApplicationContext();
            Bundle m1 = ManageSyncSettings.m1(false);
            String name = ManageSyncSettings.class.getName();
            Intent l = AbstractC2531fV.l(applicationContext, XS0.class);
            if (!(applicationContext instanceof Activity)) {
                l.addFlags(268435456);
                l.addFlags(67108864);
            }
            if (name != null) {
                l.putExtra("show_fragment", name);
            }
            l.putExtra("show_fragment_args", m1);
            U20.q(applicationContext, l);
            return;
        }
        Context applicationContext2 = ContextUtils.getApplicationContext();
        Bundle l1 = SyncAndServicesSettings.l1(false);
        String name2 = SyncAndServicesSettings.class.getName();
        Intent l2 = AbstractC2531fV.l(applicationContext2, XS0.class);
        if (!(applicationContext2 instanceof Activity)) {
            l2.addFlags(268435456);
            l2.addFlags(67108864);
        }
        if (name2 != null) {
            l2.putExtra("show_fragment", name2);
        }
        l2.putExtra("show_fragment_args", l1);
        U20.q(applicationContext2, l2);
    }

    public final void dismissed() {
        ProfileSyncService.b().q(this);
        l(this.K, 1);
    }

    @Override // defpackage.AbstractC3526lH0
    public void m() {
        if (this.K != k()) {
            j();
        }
    }
}
