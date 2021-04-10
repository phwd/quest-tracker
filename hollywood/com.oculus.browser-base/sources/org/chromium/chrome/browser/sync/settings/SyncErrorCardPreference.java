package org.chromium.chrome.browser.sync.settings;

import J.N;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import androidx.preference.Preference;
import com.oculus.browser.R;
import java.util.Objects;
import org.chromium.base.ContextUtils;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.ui.SignOutDialogFragment;
import org.chromium.chrome.browser.sync.ProfileSyncService;
import org.chromium.chrome.browser.sync.ui.PassphraseDialogFragment;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.base.CoreAccountInfo;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SyncErrorCardPreference extends Preference implements AbstractC3526lH0, VG0 {
    public final WG0 t0;
    public AbstractC1783b51 u0;
    public int v0 = -1;

    public SyncErrorCardPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.t0 = WG0.V(context, R.drawable.f32760_resource_name_obfuscated_RES_2131231316);
        this.k0 = R.layout.f40630_resource_name_obfuscated_RES_2131624372;
    }

    @Override // androidx.preference.Preference
    public void A() {
        Z();
        this.t0.Y(this);
        ProfileSyncService b = ProfileSyncService.b();
        if (b != null) {
            b.q(this);
        }
    }

    @Override // defpackage.VG0
    public void D(String str) {
        c0();
    }

    public final void a0() {
        ManageSyncSettings manageSyncSettings = (ManageSyncSettings) this.u0;
        int i = manageSyncSettings.J0.v0;
        CoreAccountInfo b = C5949zZ.a().c(Profile.b()).b(1);
        if (i != 128) {
            switch (i) {
                case 0:
                    U20.q(manageSyncSettings.u(), new Intent("android.settings.SYNC_SETTINGS"));
                    return;
                case 1:
                    AccountManagerFacadeProvider.getInstance().b(CoreAccountInfo.a(b), manageSyncSettings.u(), null);
                    return;
                case 2:
                    C0317Fe fe = new C0317Fe(manageSyncSettings.W);
                    PassphraseDialogFragment passphraseDialogFragment = new PassphraseDialogFragment();
                    passphraseDialogFragment.b1(manageSyncSettings, -1);
                    passphraseDialogFragment.j1(fe, "enter_password");
                    return;
                case 3:
                case 4:
                    AbstractC4175p51.h(manageSyncSettings, b, 1);
                    return;
                case 5:
                    Intent intent = new Intent("android.intent.action.VIEW");
                    StringBuilder i2 = AbstractC2531fV.i("market://details?id=");
                    i2.append(ContextUtils.getApplicationContext().getPackageName());
                    intent.setData(Uri.parse(i2.toString()));
                    manageSyncSettings.c1(intent);
                    return;
                case 6:
                    ProfileSyncService profileSyncService = manageSyncSettings.H0;
                    N.MmphYbNU(profileSyncService.e, profileSyncService, true);
                    ProfileSyncService profileSyncService2 = manageSyncSettings.H0;
                    N.MlP9oGhJ(profileSyncService2.e, profileSyncService2, 2);
                    return;
                default:
                    return;
            }
        } else {
            SignOutDialogFragment l1 = SignOutDialogFragment.l1(0);
            l1.b1(manageSyncSettings, 0);
            l1.k1(manageSyncSettings.G(), "sign_out_dialog_tag");
        }
    }

    public final void b0() {
        ManageSyncSettings manageSyncSettings = (ManageSyncSettings) this.u0;
        Objects.requireNonNull(manageSyncSettings);
        C5949zZ.a().d(Profile.b()).u(3);
        manageSyncSettings.u().finish();
    }

    public final void c0() {
        if (N.M09VlOh_("MobileIdentityConsistency")) {
            int c = AbstractC4175p51.c();
            this.v0 = c;
            boolean z = c == 6 && ((ManageSyncSettings) this.u0).I0;
            if (c == -1 || z) {
                W(false);
                return;
            }
            W(true);
            s();
        }
    }

    @Override // defpackage.AbstractC3526lH0
    public void m() {
        c0();
    }

    @Override // androidx.preference.Preference
    public void v() {
        super.v();
        this.t0.U(this);
        ProfileSyncService b = ProfileSyncService.b();
        if (b != null) {
            b.a(this);
        }
        c0();
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00d0  */
    @Override // androidx.preference.Preference
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void x(defpackage.C4886tF0 r9) {
        /*
        // Method dump skipped, instructions count: 232
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.sync.settings.SyncErrorCardPreference.x(tF0):void");
    }
}
